package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("minoccurred")
class MoreOccurancesInSentence extends AbstractFeatureExtractor {

	private var int howmany = 2
	override onNewParams() {
		howmany = params.toIntParam(0)
	}

	def dispatch visit(SpecWord word) {
		return (word.sentence.words.filter[lemma == word.lemma].size >= howmany).toString
	}
	
	override getDocumentation() '''
	Decides whether the word occured at least n-times within the sentence.
	'''
	
}