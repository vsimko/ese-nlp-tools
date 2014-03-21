package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

@Feature("sprel")
class SemParentRelation extends AbstractFeatureExtractor {
	def dispatch visit(SpecWord word) {
		word?.semanticParentRelation?.label
	}
}

@Feature("sprel2")
class SemParentRelation2 extends AbstractFeatureExtractor {
	def dispatch visit(SpecWord word) {
		word?.semanticParent?.semanticParentRelation?.label
	}
}