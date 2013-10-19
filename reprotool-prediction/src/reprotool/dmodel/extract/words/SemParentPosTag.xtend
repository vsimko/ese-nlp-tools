package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

@Feature("sppos")
class SemParentPosTag extends AbstractFeatureExtractor {
	def dispatch visit(SpecWord word) {
		word?.semanticParent?.posTag
	}
}