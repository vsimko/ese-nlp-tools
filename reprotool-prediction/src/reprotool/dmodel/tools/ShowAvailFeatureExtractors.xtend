package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import reprotool.dmodel.api.FeatureExtractorFactory
import reprotool.dmodel.api.ITool
import aQute.bnd.annotation.component.Reference

@Component
class ShowAvailFeatureExtractors implements ITool {

	override getUsage() '''
	Lists all avaiable feature extractors registered in the system.
	'''
	
	private FeatureExtractorFactory factory
	@Reference def void setFexFactory(FeatureExtractorFactory factory) {
		this.factory = factory
	}
	
	override execute(String[] args) {

		println(usage)
		println("Here is the list:")

		factory.loadedExtractors.forEach[
			val fex = factory.getFeatureExtractor(it)
			println('''«fex»\n«fex.documentation»''')
		]
	}
}