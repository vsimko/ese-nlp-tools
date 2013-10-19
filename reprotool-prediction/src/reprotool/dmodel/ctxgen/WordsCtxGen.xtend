package reprotool.dmodel.ctxgen

import spec.Specification
import spec.SpecWord

class WordsCtxGen implements IContextGenerator<SpecWord> {
	
	private val Specification specModel
	new(Specification specModel) {
		this.specModel = specModel
	}
	
	override iterator() {
		return specModel.documents.map[
			sentences.map[words].flatten
		].flatten.iterator
	}
	
}