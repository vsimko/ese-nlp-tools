package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

@Feature("rel_passroot")
class RelSentenceRootInPassive extends AbstractFeatureExtractor {
	
	def dispatch visit(RelationContext rel) {
		rel.sentence.semanticRootWord.invLinkGov.exists[label=="nsubjpass"].toString
	}

	override getDocumentation() '''
	Decides whether the sentence in RelationContext is in passive voice.
	'''
}