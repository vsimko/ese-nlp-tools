package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import spec.SpecWord
import static extension reprotool.dmodel.extensions.SpecWordExtensions.*
import reprotool.dmodel.api.Feature

@Feature("wminlen")
class WordMinLength extends AbstractFeatureExtractor {
	
	private var int wordOffsetInSentence
	private var int minLen
	
	override onNewParams() {
		minLen = params.toIntParam(0)
		wordOffsetInSentence = params.toIntParam(1)
	}
	
	def dispatch visit(SpecWord word) {
		val crword = word.getRelativeWordInSentence(wordOffsetInSentence).corefRepOrSelf
		return (crword.lemma.length >= minLen).toString
	}
	
	override getDocumentation() '''
	- Decides whether the lemma form of the word contains more than "minLen" characters.
	- Coreference resolution:
	  It uses the representative coreference mention (if there is any).
	  If there are no coreferences, the original SpecWord is used instead.
	- param[0] is an optional parameter representing the word offset in the sentence, e.g. wminlen:-1 is the previous word
	- examples: wminlen wminlen:1 wminlen:-1 wminlen:2 wminlen:-2
	'''
}