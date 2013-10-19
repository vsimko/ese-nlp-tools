package reprotool.dmodel.extract.mwent

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecSentence
import spec.SpecWord

import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("roleInLink")
class RoleInLink extends AbstractFeatureExtractor {
	
	public static val OUTCOME_NONE = "NONE"
	public static val OUTCOME_HEAD = "HEAD"
	public static val OUTCOME_CONT = "CONT"
	public static val OUTCOME_LAST = "LAST"

	private var int wordOffsetInSentence
	override onNewParams() {
		wordOffsetInSentence = params.toIntParam(0)
	}

	def dispatch visit(SpecWord word) {
		val relWord = word.getRelativeWordInSentence(wordOffsetInSentence)
		if(relWord == null)
			return OUTCOME_NONE
			
		val sentence = relWord.eContainer as SpecSentence
		val entlink = sentence.entityLinks.findFirst[linkedWords.contains(relWord)]
		
		if(entlink == null) return OUTCOME_NONE
		if(entlink.linkedWords.head == word) return OUTCOME_HEAD
		if(entlink.linkedWords.last == word) return OUTCOME_LAST
		
		return OUTCOME_CONT
	}
}