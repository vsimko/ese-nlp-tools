package reprotool.dmodel

import reprotool.predict.mloaders.SpecModelLoader
import spec.SpecSentence
import spec.SpecDocument

class ExtractEntityToDocument {
	def static void main(String[] args) {
		
		println("Entity DocIdx")
		
		val loader = new SpecModelLoader
		val spec = loader.loadSpecificationModel("/home/vlx/Work/nlp-papers/paper-reew2014/data/atm/spec/docfragments/predicted-specification.xmi");
		spec.eAllContents.filter(SpecSentence).forEach[ sentence |
			sentence.entityLinks.forEach[
				val doc = it.sentence.eContainer as SpecDocument
				println('''«entLabel» «spec.documents.indexOf(doc)»''')
			]
		]
	}
	
}