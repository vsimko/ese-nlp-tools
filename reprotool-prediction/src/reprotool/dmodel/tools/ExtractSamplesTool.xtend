package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.io.BufferedWriter
import java.io.FileWriter
import reprotool.dmodel.api.FeatureExtractorFactory
import reprotool.dmodel.api.ITool
import reprotool.dmodel.api.samples.ExtractedSamples
import reprotool.predict.logging.ReprotoolLogger
import reprotool.prediction.api.loaders.SpecModelLoader

@Component
class ExtractSamplesTool implements ITool {

	override getUsage() '''
	Uses a context generator + list of feature extractors to extract samples from a Specification model.
		[specModelFile]	= XMI containing the specification model
		[samplesFile]	= Name of a file to which the extacted features will be stored as samples
		[ctxgen]		= Name of the context generator, e.g. "words"
		[f1] ... [fn]	= List of feature extractors
	'''

	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}
	
	private SpecModelLoader loader
	@Reference def void setLoader(SpecModelLoader loader) {
		this.loader = loader
	}

	private FeatureExtractorFactory fexFactory
	@Reference def void setFexFactory(FeatureExtractorFactory factory) {
		this.fexFactory = factory
	}

	override execute(String[] args) {

		// check arguments
		if(args.size < 4) {
			println(usage)
			return
		}

		val allFeatureNames		= args.drop(3)
		val specModelFileName	= args.get(0)
		val samplesFileName		= args.get(1)
		val ctxGenName			= args.get(2)
		val contextFeatureNames	= allFeatureNames.take(allFeatureNames.size - 1)
		val outcomeFeatureName	= allFeatureNames.last

		val specModel	= loader.loadSpecificationModel(specModelFileName)
		val samples		= new ExtractedSamples(fexFactory, specModel, ctxGenName, contextFeatureNames, outcomeFeatureName)
		val writer		= new BufferedWriter(new FileWriter(samplesFileName))
		
		println('''Writing samples...''')
		
		samples.forEach[sample, numWrittenSamples |

			// simple progress indicator
			if(numWrittenSamples % 100 == 0)
				println('''Written «numWrittenSamples» samples''')
				
			writer.write(sample.toSampleStr)
			writer.newLine
		]

		writer.close
		
		'''All samples written to file "«samplesFileName»"'''.info
		println("done. see logs")
	}
}