package reprotool.dmodel.extract.words

import org.junit.Assert
import org.junit.Test
import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.api.FeatureExtractorFactory
import spec.SpecWord

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("pos")
class WordPosTag extends AbstractFeatureExtractor {
	
	override getDocumentation() '''
	- retrieves the Part-of-speech (POS) tag of the word
	- applied on SpecWord
	- param[0] is an optional parameter representing the word offset in the sentence, e.g. pos:-1 is the previous word
	- examples: pos pos:1 pos:-1 pos:2 pos:-2
	'''
	
	private var int wordOffsetInSentence
	override onNewParams() {
		wordOffsetInSentence = params.toIntParam(0)
	}
	
	def dispatch visit(SpecWord word) {
		word.getRelativeWordInSentence(wordOffsetInSentence)?.posTag
	}
	
}

class WordPosTagTest {
	
	extension FeatureExtractorFactory = FeatureExtractorFactory.INSTANCE
	@Test
	def test1() {
		
		val f = "pos:-1".featureExtractor
		
		Assert.assertEquals("pos", f.extractorName)
		Assert.assertEquals("pos:-1", f.featureName)
	}
}