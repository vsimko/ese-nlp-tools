package reprotool.dmodel.ctxgen

import spec.Specification

/**
 * TODO: Currently hard-coded in current implementation
 * Only "words" and "relations" are supported at the moment.
 */
class ContextGeneratorFactory {
	
	def static IContextGenerator<?> getContextGenerator(String ctxGenName, Specification specModel) {
		return switch ctxGenName {
			case "words"		: new WordsCtxGen(specModel)
			case "relations"	: new ClassRelationsFromSentenceCtxGen(specModel)
			default				: throw new Exception('''Unknown context generator "«ctxGenName»"''')
		}
	}
}