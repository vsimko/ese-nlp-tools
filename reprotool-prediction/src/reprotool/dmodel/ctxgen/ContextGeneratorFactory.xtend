package reprotool.dmodel.ctxgen

import spec.Specification

class ContextGeneratorFactory {
	
	def static IContextGenerator<?> getContextGenerator(String ctxGenName, Specification specModel) {
		return switch ctxGenName {
			case "words"		: new WordsCtxGen(specModel)
			case "relations"	: new ClassRelationsFromSentenceCtxGen(specModel)
			default				: throw new Exception('''Unknown context generator "«ctxGenName»"''')
		}
	}
}