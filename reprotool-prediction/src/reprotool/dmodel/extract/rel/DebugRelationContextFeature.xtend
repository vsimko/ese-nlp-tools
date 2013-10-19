package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

@Feature("reldebug")
class RelationContextDebug extends AbstractFeatureExtractor {
	
	def dispatch visit(RelationContext rel) {
		return '''[«rel.srcEClass.name.encodeForSample»]-[«rel.destEClass.name.encodeForSample»]'''.toString
	}

	override getDocumentation() '''
	This feature is intended just for debugging. It passes useful information to the sample.
	'''
	
	def encodeForSample(String s) {
		return s.replaceAll("[=\\s]+", "_")
	}
}