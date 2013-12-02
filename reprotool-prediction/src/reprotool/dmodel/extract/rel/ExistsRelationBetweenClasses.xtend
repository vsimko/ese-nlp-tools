package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

@Feature("relcl")
class ExistsRelationBetweenClasses extends AbstractFeatureExtractor {
	
	public static final val SUPPORTED_OUTCOMES = #[true.toString, false.toString]

	def dispatch visit(RelationContext rel) {
		rel.srcEClass.EReferences.exists[EReferenceType == rel.destEClass].toString
	}

	override getDocumentation() '''
	- Should be used as outcome feature
	- Decides whether there is some EReference A -> B between classes A,B
	'''
}