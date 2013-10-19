package reprotool.dmodel.tools.phases

import aQute.bnd.annotation.component.Component
import java.io.File
import java.util.HashMap
import java.util.List
import opennlp.model.IndexHashTable
import opennlp.model.MaxentModel
import org.apache.log4j.Logger
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EcoreFactory
import reprotool.dmodel.api.ITool
import reprotool.dmodel.api.classifiers.MaxentClassifier
import reprotool.dmodel.api.samples.ExtractedSamples
import reprotool.dmodel.ctxgen.RelationContext
import reprotool.dmodel.extract.mwent.RoleInLink
import reprotool.prediction.api.loaders.SpecModelLoader
import spec.DomainEntityType
import spec.SpecFactory
import spec.SpecSentence
import spec.SpecWord
import spec.Specification

import static extension reprotool.dmodel.extensions.ReprotoolEcoreExtensions.*

@Component
class ElicitationPhase implements ITool {
	
	extension Logger = Logger.getLogger(ElicitationPhase)

	private static val DOMAIN_MODEL_PACKAGE_NAME = "domainmodel"
	private var List<EClassifier> domainModel
	private var SpecModelLoader loader
	private var Specification specModel
	private var String projectDir

	override getUsage() '''
	The elicitation phase requires a XMI file containing documents processed
	by the linguistic pipeline.
	Then it tries to predict the domain model based on the linguistic features
	from the prerprocessed document. The result domain model is stored again
	within the same XMI file and it can be exported by the ExportDomainModel tool.
	
		[specxmi] = XMI file containing the preprocessed specification model
	'''
	
	override execute(String[] args) {
		
		// check arguments
		if(args.size != 1) {
			usage.warn
			return
		}
		
		val specModelFileName = args.get(0)

		projectDir = new File(specModelFileName).absoluteFile.parent

		loader = new SpecModelLoader
		specModel = loader.loadSpecificationModel(specModelFileName)

		// ensure we have at least empty domain model
		if(specModel.domainModel == null) {
			specModel.setDomainModel(SpecFactory.eINSTANCE.createDomainModel => [
				setModelPackage(EcoreFactory.eINSTANCE.createEPackage => [
					setName(DOMAIN_MODEL_PACKAGE_NAME)
				])
			])
		}
		// now we have at least empty model package where we can create new domain model entities
		domainModel = specModel.domainModel.modelPackage.EClassifiers

		// ------------------------
		
		predictDomEntCandidates
		predictMultiWordEntities		// requires single-word EntityLinks
		deriveNamesForEntityLinks		// requires multi-word EntityLinks with empty name
		convertEntityLinksToEClasses	// requires EntityLinks with names
		fillBacklinksEAnnotations		// requires EClasses
		mergeEClassesWithSameName		// requires "backlinks"
		predictRelations				// requires "backlinks"
		saveResults
	}
		
	private def void saveResults() {
		loader.saveSpecificationModel(specModel, '''«projectDir»/predicted-specification.xmi''')
		loader.saveDomainModel(specModel, '''«projectDir»/predicted-domainmodel.ecore''')
		'''Results saved.'''.info
	}
	
	private def Iterable<String> getContextFeatureNames(MaxentModel maxentModel) {

		// converting the OpenNLP data structures to a usable form
		val contextMap = maxentModel.dataStructures.get(1) as IndexHashTable<String>
		val allContexts  = contextMap.toArray((1 .. contextMap.size).map[null as String])
		
		// we are only interested in a unique collection of the feature names
		allContexts.map[split("=").get(0)].sort.toSet
	}
	
	private def String getOutcomeFeatureName(MaxentModel maxentModel) {
		val allOutcomes = maxentModel.dataStructures.get(2) as String[]
		allOutcomes.get(0).split("=").get(0)
	}
	
	private def void predictDomEntCandidates() {
		"TASK : Predicting which words represent domain entities".info
		
		val maxentModel = MaxentClassifier.loadMaxentModel("models/linktype.maxent.gz")
		val samples = new ExtractedSamples(specModel, "words", maxentModel.contextFeatureNames, maxentModel.getOutcomeFeatureName)
		val classifier = MaxentClassifier.createFromModel(maxentModel)

		classifier.predictIterator(samples).forEach[ event |
			val outcome = event.outcomeFeatureValue
			switch outcome {
				case "class" : {
					val attachedWord = event.attachment as SpecWord
					val sentence = attachedWord.eContainer as SpecSentence
					sentence.entityLinks += SpecFactory.eINSTANCE.createEntityLink => [
						linkedWords += attachedWord
						entType = DomainEntityType.CLASS
					]
				}
				case "ref" : {
					val attachedWord = event.attachment as SpecWord
					val sentence = attachedWord.eContainer as SpecSentence
					sentence.entityLinks += SpecFactory.eINSTANCE.createEntityLink => [
						linkedWords += attachedWord
						entType = DomainEntityType.REFERENCE
					]
				}
			}
		]
	}
	
	private def void predictMultiWordEntities() {
		"TASK : Predicting which words represent entities composed of multiple words".info
		
		val maxentModel = MaxentClassifier.loadMaxentModel("models/roleInLink.maxent.gz")
		val samples = new ExtractedSamples(specModel, "words", maxentModel.contextFeatureNames, maxentModel.getOutcomeFeatureName)
		val classifier = MaxentClassifier.createFromModel(maxentModel)
		
		
		var SpecWord lastWord
		for( event: classifier.predictIterator(samples).toIterable) {
			val outcome = event.outcomeFeatureValue
			val attachedWord = event.attachment as SpecWord
			
			switch outcome { // there is no fall through in Xtend's switch
				case RoleInLink.OUTCOME_HEAD :
					lastWord = attachedWord

				case RoleInLink.OUTCOME_CONT : {
					lastWord.mergeFrom(attachedWord)
					lastWord = attachedWord
				}
			}
		}
	}
	
	// extension method
	private def void mergeFrom(SpecWord wordToPreserve, SpecWord wordToMerge) {
		'''merging «wordToPreserve» «IF wordToPreserve.relatedEntityLink == null»[NOLINK]«ENDIF» <- «wordToMerge» «IF wordToMerge.relatedEntityLink == null»[NOLINK]«ENDIF»'''.debug
		
		if(wordToPreserve.relatedEntityLink == null) {
			'''We predicted merging words «wordToPreserve» <- «wordToMerge», however, the word to be preserved is not related to EntityLink'''.warn
			return
		}
		
		val linkToDelete = wordToMerge.relatedEntityLink
		if(linkToDelete != null) {
			wordToPreserve.relatedEntityLink.linkedWords += linkToDelete.linkedWords
			val sentence = wordToMerge.eContainer as SpecSentence
			sentence.entityLinks.remove(linkToDelete)
		} else {
			wordToPreserve.relatedEntityLink.linkedWords += wordToMerge
		}
	}
	
	private def void deriveNamesForEntityLinks() {
		"TASK : Deriving names for entity links based on the words they contain".info
		
		specModel.allEntityLinks
			.filter[entLabel == null || entLabel.empty]
			.forEach[
				entLabel = linkedWords.map[getCorefRepOrSelf.lemma.toLowerCase.toFirstUpper].join(" ")
			]
			
		'''All EntityLinks found in the specification: «specModel.allEntityLinks.map[entLabel].join(", ")»'''.debug
	}
	
	private def void convertEntityLinksToEClasses() {
		"TASK : Converting entity links to EClasses in the domain model".info
		
		specModel.allEntityLinks
			.filter[entType == DomainEntityType.CLASS]
			.forEach[ entlink |
				val newEClass = EcoreFactory.eINSTANCE.createEClass => [
					setName(entlink.entLabel)
				]
				entlink.linkedEntity = newEClass
				domainModel += newEClass
			]
	}
	
	private def void fillBacklinksEAnnotations() {
		'''Filling backlinks from EClasses (in the domain model) to EntityLinks (in the document)'''.info
		specModel.allEntityLinks
			.filter[linkedEntity != null] // some EntityLinks might not yet be connected to the domain model 
			.forEach[
				linkedEntity.getEAnnotationOrCreate("backlinks").references += it
			]
	}

	/**
	 * Requires: "backlinks" EAnnotation for each EClass
	 */
	private def void mergeEClassesWithSameName() {
		"TASK : Merge EClasses with the same name".info
		
		// sanity check
		if( ! domainModel.filter(EClass).exists[getEAnnotation("backlinks") != null] )
			throw new Exception('''There are no "backlinks" EAnnotations in the domain model attached to EClasses''')
		
		val mapByName = new HashMap<String, EClass> // for checking duplicate names
		domainModel
			.filter(EClass)
			.toList // the collection is updated during iteration thus we need a copy
			.forEach[
				if(mapByName.containsKey(name)) {

					// now we know that "it" is the duplicate
					val preservedEntity = mapByName.get(name)
					'''Merging EClasses «preservedEntity.name» <- «name»'''.debug
					
					val backlinksToUpdate = it.backlinks
					backlinksToUpdate.forEach[linkedEntity = preservedEntity] // update EntityLink -> EClass 
					preservedEntity.getEAnnotation("backlinks").references += backlinksToUpdate // update EClass -> EntityLink 

					domainModel.remove(it) // now we can remove the duplicate
					
				} else {
					mapByName.put(name, it)
				}
			]
	}
	
	private def void predictRelations() {
		"TASK : Predict relations".info

		// sanity check
		if( ! domainModel.filter(EClass).exists[getEAnnotation("backlinks") != null] )
			throw new Exception('''There are no "backlinks" EAnnotations in the domain model attached to EClasses''')

		val maxentModel = MaxentClassifier.loadMaxentModel("models/relcl.maxent.gz")
		val samples = new ExtractedSamples(specModel, "relations", maxentModel.contextFeatureNames, maxentModel.getOutcomeFeatureName)
		val classifier = MaxentClassifier.createFromModel(maxentModel)

		classifier.predictIterator(samples).forEach[ event |
			val outcome = event.outcomeFeatureValue
			val attachedRelationContext = event.attachment as RelationContext
			switch outcome {
				case "true" : {
					val src = attachedRelationContext.srcEClass
					val dest = attachedRelationContext.destEClass
					'''Predicted relation: «src.name» -> «dest.name»'''.debug
					src.EStructuralFeatures += EcoreFactory.eINSTANCE.createEReference => [
						name = attachedRelationContext.sentence.semanticRootWord.lemma.toLowerCase
						setEType(dest)
					]
				}
			}
		]
	}
}