package reprotool.dmodel.tools.phases.elicit

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.io.File
import java.util.HashMap
import java.util.List
import opennlp.model.IndexHashTable
import opennlp.model.MaxentModel
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EcoreFactory
import reprotool.dmodel.api.FeatureExtractorFactory
import reprotool.dmodel.api.classifiers.MaxentClassifier
import reprotool.dmodel.api.samples.ExtractedSamples
import reprotool.dmodel.ctxgen.RelationContext
import reprotool.dmodel.extract.mwent.RoleInLink
import reprotool.dmodel.extract.words.WordLinkType
import reprotool.predict.exectoolapi.IExecutableTool
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.mloaders.SpecModelLoader
import spec.DomainEntityType
import spec.SpecFactory
import spec.SpecSentence
import spec.SpecWord
import spec.Specification

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

import static extension reprotool.dmodel.extensions.ReprotoolEcoreExtensions.*

@Component
class ElicitationPhase implements IExecutableTool {
	
	override getUsage() '''
		The elicitation phase requires a XMI file containing documents processed
		by the linguistic pipeline.
		Then it tries to predict the domain model based on the linguistic features
		from the prerprocessed document. The result domain model is stored again
		within the same XMI file and it can be exported by the ExportDomainModel tool.
		
			[specxmi] = XMI file containing the preprocessed specification model
	'''
	
	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}
	
	private SpecModelLoader loader
	@Reference def void setLoader(SpecModelLoader loader) {
		this.loader = loader
	}

	private FeatureExtractorFactory fexFactory
	@Reference def void setFexFactory(FeatureExtractorFactory factory) {
		this.fexFactory = factory
	}
	
	// See WordLinkType class for the list of supported outcome values
	private MaxentModel linktypeModel
	@Reference(target="(model=linktype)")
	def void set_linktypeModel(MaxentModel model) {
		this.linktypeModel = model
	}
	
	// See RoleInLink class for the list of supported outcome values
	private MaxentModel roleinlinkModel
	@Reference(target="(model=roleInLink)")
	def void set_roleinlinkModel(MaxentModel model) {
		this.roleinlinkModel = model
	}
	
	// See ExistsRelationBetweenClasses for the list of supported outcome values
	private MaxentModel relclModel
	@Reference(target="(model=relcl)")
	def void set_relclModel(MaxentModel model) {
		this.relclModel = model
	}
	
	private static val DOMAIN_MODEL_PACKAGE_NAME = "domainmodel"
	private var List<EClassifier> domainModel
	private var Specification specModel
	private var String projectDir

	override execute(String[] args) {
		
		// check arguments
		if(args.size != 1) {
			println(usage)
			return
		}
		
		val specModelFileName = args.get(0)

		projectDir = new File(specModelFileName).absoluteFile.parent
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
		removeEntityLinksWithoutLabel
		convertEntityLinksToEClasses	// requires EntityLinks with names
		fillBacklinksEAnnotations		// requires EClasses
		mergeEClassesWithSameName		// requires "backlinks"
		predictRelations				// requires "backlinks"
		removeSpacesFromDomainModel
		saveResults
		
		// ------------------------
		println("done. see logs")
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
		
		// We need to convert the EMF "specModel" into a list of samples
		// Here, a sample corresponds to an instance of SpecWord class.
		// The sample contains features: "contextFeatureNames" + "outcomeFeatureName"
		val samples = new ExtractedSamples(
			fexFactory,	// this factory provides the necessary feature extractors 
			specModel,	// this is the huge EMF model (Specification Model)
			"words",	// we are extracting samples corresponding to words (SpecWord instances)
			linktypeModel.getContextFeatureNames,	// list of context features
			linktypeModel.getOutcomeFeatureName		// the outcome 
		)
		
		// Now, we need a classifier that processes the samples
		val classifier = MaxentClassifier.createFromModel(linktypeModel)

		// The classifier is implemented as an iterator
		// that converts a list of input samples to a list of predictions.
		classifier.predictIterator(samples).forEach[ event |

			// in this lambda function, we handle a single prediction
			val outcome = event.outcomeFeatureValue
			val attachedWord = event.attachment as SpecWord
			val sentence = attachedWord.eContainer as SpecSentence

//			if(attachedWord.original.toLowerCase.contains("product")) {
//				println('''lemma=«attachedWord.lemma», orig=«attachedWord.original», pos=«attachedWord.posTag»''')
//			}
			
			// our "linktypeModel" classification model predicted  
			switch outcome {
				case outcome == WordLinkType.OUTCOME_CLASS  || outcome == WordLinkType.OUTCOME_ATTRIBUTE : {
					sentence.entityLinks += SpecFactory.eINSTANCE.createEntityLink => [
						linkedWords += attachedWord
						entType = DomainEntityType.CLASS
					]
				}
				case WordLinkType.OUTCOME_REFERENCE : {
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
		
		val samples = new ExtractedSamples(
			fexFactory,
			specModel,
			"words",
			roleinlinkModel.getContextFeatureNames,
			roleinlinkModel.getOutcomeFeatureName
		)
		val classifier = MaxentClassifier.createFromModel(roleinlinkModel)
		
		
		var SpecWord lastWord
		for( event: classifier.predictIterator(samples).toIterable) {
			val outcome = event.outcomeFeatureValue
			val attachedWord = event.attachment as SpecWord
			
			switch outcome {
				case RoleInLink.OUTCOME_CONT : {
					if(lastWord != null) lastWord.mergeFrom(attachedWord)
					lastWord = attachedWord
				}
				case RoleInLink.OUTCOME_LAST : {
					if(lastWord != null) lastWord.mergeFrom(attachedWord)
					lastWord = null
				}
				case RoleInLink.OUTCOME_HEAD : {
					lastWord = attachedWord
				}
				default: lastWord = null
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
			wordToMerge.sentence.entityLinks.remove(linkToDelete)
		} else {
			wordToPreserve.relatedEntityLink.linkedWords += wordToMerge
		}
	}
	
	private def void deriveNamesForEntityLinks() {
		"TASK : Deriving names for entity links based on the words they contain".info
		
		for(entlink : specModel.allEntityLinks.filter[entLabel == null || entLabel.empty]) {
			
			val commonRoot = getNearestCommonSemanticParent(entlink.linkedWords.filter[posTag.matches("^(NN|JJ).*")])
			if(commonRoot != null) {
				
				val words = commonRoot.semanticChildren.toList
				words += commonRoot
				
				val nouns = words.filter[posTag.matches("^NN.*")] //.sortBy[positionInSentence]
//				entlink.entLabel = nouns.map[corefRepOrSelf.lemma.toLowerCase.toFirstUpper].toSet.join("") + "---" + words.join(",")
				entlink.entLabel = nouns.map[corefRepOrSelf.lemma.toLowerCase.toFirstUpper].toSet.join("")
			}
		}
			
		'''All EntityLinks found in the specification'''.debug
	}
	
	private def void removeEntityLinksWithoutLabel() {
		"TASK : Removing entity links without a label".info
		val entLinksToRemove = specModel.allEntityLinks.filter[entLabel == null || entLabel.empty].toList // materialize the list
		entLinksToRemove.forEach[
			linkedWords.clear
			setLinkedEntity(null)
			sentence.entityLinks.remove(it)
		]
		'''Removed «entLinksToRemove.size» entity links without label'''.debug
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
		'''TASK : Filling backlinks from EClasses (in the domain model) to EntityLinks (in the document)'''.info
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

		val samples = new ExtractedSamples(fexFactory, specModel, "relations", relclModel.contextFeatureNames, relclModel.getOutcomeFeatureName)
		val classifier = MaxentClassifier.createFromModel(relclModel)

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
	
	private def void removeSpacesFromDomainModel() {
		"TASK : Removing spaces from class names in the domain model".info
		domainModel.filter(EClass).filter[name != null].forEach[name = name.replaceAll(" ", "")]
	}
}