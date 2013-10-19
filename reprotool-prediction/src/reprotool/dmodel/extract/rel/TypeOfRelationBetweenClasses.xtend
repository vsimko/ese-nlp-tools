package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

import static extension reprotool.dmodel.extensions.ReprotoolEcoreExtensions.*
import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("reltype")
class TypeOfRelationBetweenClasses extends AbstractFeatureExtractor {
	
	public static val OUTCOME_NONE = "NONE"
	public static val OUTCOME_CONTAINMENT = "CON"
	public static val OUTCOME_RELATION = "REL"
	
//	extension Logger = Logger.rootLogger
	
	def dispatch visit(RelationContext rel) {
		
		if(rel.srcEClass == rel.destEClass)
			return OUTCOME_NONE
		
		val srcRelatedWords = rel.srcEClass
			.backlinks.filter[sentence == rel.sentence] // backlinks, but only in a specific sentence
			.map[linkedWords].flatten.toSet // all words from backlinks
			
		val destRelatedWords = rel.destEClass
			.backlinks.filter[sentence == rel.sentence] // backlinks, but only in a specific sentence
			.map[linkedWords].flatten.toSet // all words from backlinks

		val srcWordsOnPathToRoot  = srcRelatedWords.map[pathFromWordToRoot].flatten
		val destWordsOnPathToRoot = destRelatedWords.map[pathFromWordToRoot].flatten
		
		val intersection = srcWordsOnPathToRoot.toSet
		intersection.retainAll(destWordsOnPathToRoot.toSet) // intersection operation happens here
//		'''INTERSECT : «rel.srcEClass.name»->«rel.destEClass.name» = «intersection.map[original].join(", ")»'''.debug
		
		val verbs = intersection.filter[posTag.startsWith("VB") || posTag == "JJ"]
//		'''VERBS : [«verbs.join(", ")»][root=«rel.sentence.semanticRootWord»]'''.debug
		
		if(verbs.empty)
			return OUTCOME_NONE
		
		val relations = rel.srcEClass.EReferences.filter[EReferenceType == rel.destEClass]

		if(relations.empty)
			return OUTCOME_NONE
		
		if(relations.exists[containment])
			return OUTCOME_CONTAINMENT

		return OUTCOME_RELATION
	}

	override getDocumentation() '''
	- Retrieves the type of relation between two classes A, B
	  - "«OUTCOME_NONE»" : there is no EReference A -> B
	  - "«OUTCOME_CONTAINMENT»" : there is containmnet relation A ─◄► B
	  - "«OUTCOME_RELATION»" : there is other than contianment relation A -> B
	'''
}