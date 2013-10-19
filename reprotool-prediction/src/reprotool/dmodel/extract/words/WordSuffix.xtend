package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.api.FeatureExtractor
import spec.SpecWord

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("wordsuffix")
class WordSuffix extends AbstractFeatureExtractor implements FeatureExtractor {
	
	override getDocumentation() '''
	- Returns a suffix of the given word
	- applied on SpecWord
	- Coreference resolution:
	  It uses the representative coreference mention (if there is any).
	  If there are no coreferences, the original SpecWord is used instead.
	- param[0] is an optional parameter representing the suffix size 1..5 (default=1)
	- examples: wsuffix wsuffix:2
	'''
	
	private var int suffixSize
	override onNewParams() {
		suffixSize = params.toIntParam(0,1).checkMin(1).checkMax(5)
	}

	def dispatch visit(SpecWord word) {
		val wordStr = word.corefRepOrSelf.original
		val wlen = wordStr.length
		return wordStr.substring(wlen - Math.min(suffixSize, wlen), wlen)
	}
	
}