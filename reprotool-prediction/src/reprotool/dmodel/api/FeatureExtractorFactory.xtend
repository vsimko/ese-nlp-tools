package reprotool.dmodel.api

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.util.Map
import reprotool.dmodel.extract.mwent.RoleInLink
import reprotool.dmodel.extract.rel.ExistsContainmentBetweenClasses
import reprotool.dmodel.extract.rel.ExistsRelationBetweenClasses
import reprotool.dmodel.extract.rel.RelClassDepOnSentenceRoot
import reprotool.dmodel.extract.rel.RelIntersectionVerbFeature
import reprotool.dmodel.extract.rel.RelSentenceRootInPassive
import reprotool.dmodel.extract.rel.RelSentenceSemRootLemma
import reprotool.dmodel.extract.rel.RelationContextDebug
import reprotool.dmodel.extract.rel.TypeOfRelationBetweenClasses
import reprotool.dmodel.extract.words.SemParentPosTag
import reprotool.dmodel.extract.words.SemParentRelation
import reprotool.dmodel.extract.words.WordHasCapitalLetter
import reprotool.dmodel.extract.words.WordHasDigit
import reprotool.dmodel.extract.words.WordLemma
import reprotool.dmodel.extract.words.WordLinkType
import reprotool.dmodel.extract.words.WordLinkedToEClass
import reprotool.dmodel.extract.words.WordMinLength
import reprotool.dmodel.extract.words.WordPosTag
import reprotool.dmodel.extract.words.WordPrefix
import reprotool.dmodel.extract.words.WordSuffix
import reprotool.predict.logging.ReprotoolLogger

@Component(immediate=true, provide=FeatureExtractorFactory)
class FeatureExtractorFactory {
	
	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}

	private val avaiableExtractors = newArrayList(
		SemParentPosTag,
		SemParentRelation,
		WordHasCapitalLetter,
		WordHasDigit,
		WordLemma,
		WordLinkedToEClass,
		WordLinkType,
		WordMinLength,
		WordPosTag,
		WordPrefix,
		WordSuffix,
		// -------------------------------
		ExistsContainmentBetweenClasses,
		ExistsRelationBetweenClasses,
		RelationContextDebug,
		RelClassDepOnSentenceRoot,
		RelIntersectionVerbFeature,
		RelSentenceRootInPassive,
		RelSentenceSemRootLemma,
		TypeOfRelationBetweenClasses,
		// -------------------------------
		RoleInLink	
	)
	
	new() {
		avaiableExtractors.forEach[loadFromClass]
	}

	private def loadFromClass(Class<?> clazz) {
		val annot = clazz.getAnnotation(Feature)
		if(annot != null ) {
			val extractorName = annot.value
			val fexclazz = clazz.asSubclass(FeatureExtractor)
			
			// sanity check
			if(mapExtractorNameToClass.containsKey(extractorName)) {
				val storedClazz = mapExtractorNameToClass.get(extractorName)
				'''Feature extractor "«extractorName»" («storedClazz») already registered in the factory'''.warn
				
				if(storedClazz != fexclazz)
					throw new Exception('''The feature extractor "«extractorName»" is already registered with a different class («storedClazz.simpleName» != «fexclazz.simpleName»).''' )
			}
			
			mapExtractorNameToClass.put(extractorName, fexclazz)
		}
	}

	private val Map<String, Class<? extends FeatureExtractor>> mapExtractorNameToClass = newHashMap
	
	def getLoadedExtractors() {
		mapExtractorNameToClass.keySet
	}
	
	def getFeatureExtractor(String featureName) {
		
		if(featureName.matches("[=\\s]"))
			throw new Exception("Feature names cannot contain white spaces or '=' character.")
		
		// constructing a new extractor
		val chunks = featureName.split(":")
		val extractorName = chunks.head
		val params = chunks.tail

		val clazz = mapExtractorNameToClass.get(extractorName)

		if(clazz == null)
			throw new Exception('''Could not find the feature extractor "«featureName»" on class path. Maybe you forgot to annotate it with @Feature("«extractorName»").''')
			
		val instance = clazz.newInstance
		instance.setParams(params)
		return instance
	}
	
	def getFeatureExtractors(Iterable<String> featureNames) {
		featureNames.map[featureExtractor]
	}
}