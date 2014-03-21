package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

@Feature("sppos")
class SemParentPosTag extends AbstractFeatureExtractor {
	def dispatch visit(SpecWord word) {
		word?.semanticParent?.posTag
	}
	
	override getDocumentation() '''
	POS-tag of the parent
	'''
}

@Feature("sppos2")
class SemParentPosTag2 extends AbstractFeatureExtractor {
	def dispatch visit(SpecWord word) {
		word?.semanticParent?.semanticParent?.posTag
	}
	
	override getDocumentation() '''
	POS-tag of the parent's parent.
	'''
	
}