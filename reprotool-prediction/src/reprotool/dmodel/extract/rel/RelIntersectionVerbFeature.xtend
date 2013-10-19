package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

import static extension reprotool.dmodel.extensions.ReprotoolEcoreExtensions.*
import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("rel_ivf")
class RelIntersectionVerbFeature extends AbstractFeatureExtractor {
	
	public static val FTYPE_LEMMA = "lemma"
	public static val FTYPE_POS = "pos"
	var ftype = FTYPE_LEMMA
	
	override onNewParams() {
		ftype = params.toStringParam(0, FTYPE_LEMMA)
	}
	
	def dispatch visit(RelationContext rel) {
		
		if(rel.srcEClass == rel.destEClass)
			return null
		
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
		
		val firstVerb = verbs?.head?.corefRepOrSelf
		if(firstVerb == null)
			return null
		
		return switch ftype {
			case FTYPE_LEMMA : firstVerb.lemma
			case FTYPE_POS   : firstVerb.posTag
		}
	}

	override getDocumentation() '''
	- Examples: rel_ivf:lemma, rel_ivf:pos
	'''
}