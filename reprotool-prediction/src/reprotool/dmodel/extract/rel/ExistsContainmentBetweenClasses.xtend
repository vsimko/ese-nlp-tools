package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

@Feature("relclc")
class ExistsContainmentBetweenClasses extends AbstractFeatureExtractor {
	
	def dispatch visit(RelationContext rel) {
		rel.srcEClass.EReferences.exists[EReferenceType == rel.destEClass && containment].toString
	}

	override getDocumentation() '''
	- Should be used as outcome feature
	- Decides whether two classes A,B linked from the same sentence contain a containment reference A->B
	'''
	
}