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