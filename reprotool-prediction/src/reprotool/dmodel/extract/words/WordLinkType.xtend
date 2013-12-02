package reprotool.dmodel.extract.words

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

@Feature("linktype")
class WordLinkType extends AbstractFeatureExtractor {
	
	public static final val OUTCOME_CLASS = "class"
	public static final val OUTCOME_OPERATION = "operation"
	public static final val OUTCOME_ATTRIBUTE = "attribute"
	public static final val OUTCOME_REFERENCE = "reference"
	public static final val OUTCOME_OTHER = "other"
	public static final val OUTCOME_NONE = "none"
	
	public static final val SUPPORTED_OUTCOMES = #[
		OUTCOME_CLASS, OUTCOME_OPERATION, OUTCOME_ATTRIBUTE, OUTCOME_REFERENCE,
		OUTCOME_OTHER, OUTCOME_NONE
	]
	
	def dispatch visit(SpecWord word) {
		val link = word.relatedEntityLink
		
		if(link == null)
			return OUTCOME_NONE

		val type = link.entType.literal
		if(SUPPORTED_OUTCOMES.contains(type))
			return type
		
		throw new RuntimeException('''Unsupported linktype detected: "«type»"''')
	}
	
	override getDocumentation() '''
	- Returns the type of a domain entity to which the given word is linked
	- Applied on SpecWord
	- The value "none" means that the word is not linked to an entity
	- examples: linktype
	'''
}