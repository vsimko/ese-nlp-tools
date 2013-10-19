package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

@Feature("whasdigit")
class WordHasDigit extends AbstractFeatureExtractor {

	def dispatch visit(SpecWord word) {
		word.original.matches(".*[0-9].*").toString
	}
	
	override getDocumentation() '''
	- determines whether the given word contains at least one digit
	- applied on SpecWord
	- examples: whasdigit
	'''
}