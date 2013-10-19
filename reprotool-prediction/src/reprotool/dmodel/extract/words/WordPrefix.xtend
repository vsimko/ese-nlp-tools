package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import spec.SpecWord

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*
import reprotool.dmodel.api.Feature

@Feature("wprefix")
class WordPrefix extends AbstractFeatureExtractor {
	
	private var int prefixSize
	override onNewParams() {
		prefixSize = params.toIntParam(0,1).checkMin(1).checkMax(5)
	}

	def dispatch visit(SpecWord word) {
		val wordStr = word.corefRepOrSelf.original
		val wlen = wordStr.length
		return wordStr.substring(0, Math.min(prefixSize, wlen))
	}
	
	override getDocumentation() '''
	- Returns a prefix of the given word
	- Coreference resolution:
	  It uses the representative coreference mention (if there is any).
	  If there are no coreferences, the original SpecWord is used instead.
	- applied on SpecWord
	- param[0] is an optional parameter representing the prefix size 1..5 (default=1)
	- examples: wprefix wprefix:2
	'''
}