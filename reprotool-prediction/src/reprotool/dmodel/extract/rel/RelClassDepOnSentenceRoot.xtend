package reprotool.dmodel.extract.rel

import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import reprotool.dmodel.ctxgen.RelationContext

import static extension reprotool.dmodel.extensions.ReprotoolEcoreExtensions.*
import static extension reprotool.dmodel.extensions.SpecWordExtensions.*

@Feature("rel_depOnRoot")
class RelClassDepOnSentenceRoot extends AbstractFeatureExtractor {
	
	override getDocumentation() '''
	- The semantic root of a sentence has a direct dependant that is linked to the class (src/dest) of the relation
	- An optional parameter defines which class will be used from the relations "src" (default) or "dest"
	- Examples: rel_depOnRoot:src rel_depOnRoot:dest
	'''
	
	private var String whichClass
	override onNewParams() {
		whichClass = params.toStringParam(0, "src")
	}
	
	def dispatch visit(RelationContext rel) {
		
		val classToCheck = switch whichClass {
			case "dest"	: rel.destEClass
			case "src"	: rel.srcEClass
			default		: throw new RuntimeException("Unknown parameter: " + whichClass)
		}

		val link = classToCheck.backlinks.findFirst[sentence == rel.sentence]
		
		if(link == null)
			return null
		
		return rel.sentence.semanticRootWord.invLinkGov.findFirst[ linkDep.relatedEntityLink?.linkedEntity == classToCheck]?.label
	}
}