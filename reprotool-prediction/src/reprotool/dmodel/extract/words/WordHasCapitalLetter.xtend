package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("whascl")
class WordHasCapitalLetter extends AbstractFeatureExtractor {
	private var int wordOffsetInSentence
	override onNewParams() {
		wordOffsetInSentence = params.toIntParam(0)
	}

	def dispatch visit(SpecWord word) {
		word.getRelativeWordInSentence(wordOffsetInSentence)?.original.matches(".*[A-Z].*").toString
	}
	
	override getDocumentation() '''
	- determines whether the given word contains at least one capital letter
	- applied on SpecWord
	- param[0] is an optional parameter representing the word offset in the sentence, e.g. whascl:-1 is the previous word
	- examples: whascl whascl:-1 whascl:1
	'''
}