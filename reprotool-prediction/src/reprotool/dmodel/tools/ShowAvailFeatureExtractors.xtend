package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import reprotool.dmodel.api.FeatureExtractorFactory
import reprotool.dmodel.api.ITool

@Component
class ShowAvailFeatureExtractors implements ITool {

	override getUsage() '''
	Lists all avaiable feature extractors registered in the system.
	'''
	
	override execute(String[] args) {
		println(usage)
		println("Here is the list:")
		val factory = FeatureExtractorFactory.INSTANCE
		factory.loadedExtractors.forEach[
			val fex = factory.getFeatureExtractor(it)
			println('''«fex»\n«fex.documentation»''')
		]
	}
}