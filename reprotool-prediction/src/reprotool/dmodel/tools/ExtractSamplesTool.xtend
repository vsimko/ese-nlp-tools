package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import java.io.BufferedWriter
import java.io.FileWriter
import org.apache.log4j.Logger
import reprotool.dmodel.api.ITool
import reprotool.dmodel.api.samples.ExtractedSamples
import reprotool.prediction.api.loaders.SpecModelLoader

@Component
class ExtractSamplesTool implements ITool {

	extension Logger = Logger.getLogger(ExtractSamplesTool)

	override getUsage() '''
	Uses a context generator + list of feature extractors to extract samples from a Specification model.
		[specModelFile]	= XMI containing the specification model
		[samplesFile]	= Name of a file to which the extacted features will be stored as samples
		[ctxgen]		= Name of the context generator, e.g. "words"
		[f1] ... [fn]	= List of feature extractors
	'''

	override execute(String[] args) {

		// check arguments
		if(args.size < 4) {
			System.err.println(usage)
			System.exit(1)
		}

		new ExtractSamplesTool => [
			val allFeatureNames = args.drop(3)
			run (
				args.get(0),
				args.get(1),
				args.get(2),
				allFeatureNames.take(allFeatureNames.size - 1),
				allFeatureNames.last
			)
		]
	}

	def void run(String specModelFileName, String samplesFileName, String ctxGenName, Iterable<String> contextFeatureNames, String outcomeFeatureName) {
		val loader		= new SpecModelLoader
		val specModel	= loader.loadSpecificationModel(specModelFileName)
		val samples		= new ExtractedSamples(specModel, ctxGenName, contextFeatureNames, outcomeFeatureName)
		val writer		= new BufferedWriter(new FileWriter(samplesFileName))
		
		'''Writing samples...'''.info
		samples.forEach[sample, numWrittenSamples |
			if(numWrittenSamples % 100 == 0)
				'''Written «numWrittenSamples» samples'''.info // simple progress indicator
				
			writer.write(sample.toSampleStr)
			writer.newLine
		]

		writer.close
		
		'''All samples written to file "«samplesFileName»"'''.info
		'''done.'''.info
	}
}