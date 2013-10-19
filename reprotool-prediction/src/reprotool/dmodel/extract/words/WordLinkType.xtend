package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

@Feature("linktype")
class WordLinkType extends AbstractFeatureExtractor {
	
	def dispatch visit(SpecWord word) {
		val link = word.relatedEntityLink
		return if(link == null) "none" else link.entType.literal
	}
	
	override getDocumentation() '''
	- Returns the type of a domain entity to which the given word is linked
	- Applied on SpecWord
	- The value "none" means that the word is not linked to an entity
	- examples: linktype
	'''
}