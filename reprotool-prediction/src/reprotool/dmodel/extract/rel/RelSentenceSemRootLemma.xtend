package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

@Feature("rel_semrootlemma")
class RelSentenceSemRootLemma extends AbstractFeatureExtractor {
	
	def dispatch visit(RelationContext rel) {
		rel.sentence.semanticRootWord.corefRepOrSelf.lemma.toLowerCase
	}

	override getDocumentation() '''
	- applies on RelationContext
	- What is the lemma form of the word which is the root of the semantic graph (stanford typed dependencies representation)
	'''
	
}