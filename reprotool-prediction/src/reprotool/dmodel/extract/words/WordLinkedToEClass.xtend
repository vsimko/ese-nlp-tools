package reprotool.dmodel.extract.words

import org.eclipse.emf.ecore.EClass
import reprotool.dmodel.api.AbstractFeatureExtractor
import reprotool.dmodel.api.Feature
import spec.SpecWord

@Feature("iseclass")
class WordLinkedToEClass extends AbstractFeatureExtractor {
	
	def dispatch visit(SpecWord word) {
		return (word.relatedEntityLink?.linkedEntity instanceof EClass).toString
	}
	
}