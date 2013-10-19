package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import org.apache.log4j.Logger
import reprotool.dmodel.api.ITool
import reprotool.dmodel.api.classifiers.MaxentClassifier
import reprotool.dmodel.api.samples.FileBasedSamples

@Component
class MaxentTrainerTool implements ITool {

	extension Logger = Logger.getLogger(MaxentTrainerTool)

	override getUsage() '''
		USAGE: java -jar «MaxentTrainerTool.simpleName».jar [trainSamples] [outcomeFeature] [outputModel]
		
		  trainSamples   = input file containing training samples
		  outcomeFeature = name of the outcome feature e.g. "isdoment"
		  outputModel    = output MaxEnt model file
	'''

	override execute(String[] args) {

		// check arguments
		if(args.size != 3) {
			usage.warn
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
		
		"done.".info
	}
}