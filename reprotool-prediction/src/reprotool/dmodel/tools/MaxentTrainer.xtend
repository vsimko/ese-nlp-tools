package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import reprotool.dmodel.api.classifiers.MaxentClassifier
import reprotool.dmodel.api.samples.FileBasedSamples
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.exectoolapi.IExecutableTool

@Component
class MaxentTrainer implements IExecutableTool {

	override getUsage() '''
		This tool is used for training a Maximum Entropy classifier.
		
		[trainSamples]   = input file containing training samples
		[outcomeFeature] = name of the outcome feature e.g. "linktype"
		[outputModel]    = output MaxEnt model file
	'''

	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}
	
	override execute(String[] args) {

		// check arguments
		if(args.size != 3) {
			println(usage)
			return
		}
		
		val trainSamplesFileName = args.get(0)
		val outcomeFeatureName = args.get(1)
		val outputModelFileName = args.get(2)
		
		MaxentClassifier.createEmptyClassifier => [
			'''Training Maximum Entropy model from sample "«trainSamplesFileName»"'''.info
			train(new FileBasedSamples(trainSamplesFileName, outcomeFeatureName))
			
			'''Saving the trained model to "«outputModelFileName»"'''.info
			saveModelToFile(outputModelFileName)
		]
		
		println("done. see logs")
	}
}