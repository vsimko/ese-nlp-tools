package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("lemma")
class WordLemma extends AbstractFeatureExtractor {
	
	private var int wordOffsetInSentence
	override onNewParams() {
		wordOffsetInSentence = params.toIntParam(0)
	}
	
	def dispatch visit(SpecWord word) {
		return word.getRelativeWordInSentence(wordOffsetInSentence)?.corefRepOrSelf.lemma
	}

	override getDocumentation() '''
	- applies on SpecWord
	- retrieves the lemma form of the word
	- param[0] is an optional parameter representing the word offset in the sentence, e.g. lemma:-1 is the previous word
	- examples: lemma lemma:1 lemma:-1 lemma:2 lemma:-2
	'''
}