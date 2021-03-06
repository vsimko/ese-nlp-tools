package reprotool.dmodel.tools.phases.train

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.io.File
import java.io.FileReader
import java.util.Map
import java.util.Properties
import java.util.Set
import reprotool.dmodel.api.FeatureExtractorFactory
import reprotool.dmodel.api.classifiers.MaxentClassifier
import reprotool.dmodel.api.samples.ExtractedSamples
import reprotool.predict.exectoolapi.IExecutableTool
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.mloaders.SpecModelLoader

@Component
class TrainingPhase implements IExecutableTool {

	override getUsage() '''
	The training phase requires a configuration file and a fully
	populated specification model which should contain a loaded domain model
	and a document already analysed using the linguistic pipeline.
	Also entity links should have already been resolved.
			
		[specfile] = XMI file containing an existing specification model
		[config]   = configuration file (Java Properties format)
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
		if(args.size != 2) {
			println(usage)
			return
		}
		
		val specModelFileName = args.get(0)
		val configFileName = args.get(1)
		
		'''Loading specification file from XMI "«specModelFileName»"'''.info
		val specModel = loader.loadSpecificationModel(specModelFileName)
		
		'''Loading configuration file "«configFileName»"'''.info
		
		val config = new TrainingPhaseConfig(new Properties => [
			setProperty(TrainingPhaseConfig.FIELD_PROJECTDIR, new File(configFileName).absoluteFile.parent)
			load(new FileReader(configFileName))
		])
		
		config.toString.debug
		
		for(outcomeFeature : config.outcomes) {
			val generatorName = config.generators.get(outcomeFeature)
			val contextFeatures = config.contexts.get(outcomeFeature)
			

			'''Training the model for predicting "«outcomeFeature»" extracted by "«generatorName»" from: «contextFeatures.join(", ")»'''.info
			val extractedSamples = new ExtractedSamples(fexFactory, specModel, generatorName, contextFeatures, outcomeFeature)

			'''Creating any necessary directories, where the trained models will be stored: «config.outputDir» ...'''.info
			new File(config.outputDir).mkdirs
			'''The result directory should be ready now.'''.info

			val outModelFileName = '''«config.outputDir»/«outcomeFeature».maxent.gz'''.toString
			MaxentClassifier.createEmptyClassifier => [
				train(extractedSamples)
				saveModelToFile(outModelFileName)
			]
			'''Trained model saved to file "«outModelFileName»"'''.info
		}
		
		println("done. see logs")
	}
}

class TrainingPhaseConfig {
	
	public val String projectDir
	public val String outputDir
	public val Set<String> outcomes
	public val Map<String, String> generators = newHashMap
	public val Map<String, Set<String>> contexts = newHashMap
	
	public static val FIELD_PROJECTDIR = "projectdir"
	private static val FIELD_OUTPUTDIR = "outdir"
	private static val FIELD_OUTCOMES = "outcomes"
	private static val SUBFIELD_GENERATOR = "generator"
	private static val SUBFIELD_CONTEXT = "context"
	
	new(Properties config) {
		projectDir = config.getProperty(FIELD_PROJECTDIR)
		
		val configOutDir = config.getProperty(FIELD_OUTPUTDIR)
		outputDir = if(configOutDir.startsWith("/")) configOutDir else '''«projectDir»/«configOutDir»'''.toString
		outcomes = config.getProperty(FIELD_OUTCOMES).split("[,;\\s]+").toSet
		
		outcomes.forEach[generators.put(it, config.getProperty(it + "." + SUBFIELD_GENERATOR))]
		outcomes.forEach[contexts.put(it, config.getProperty(it + "." + SUBFIELD_CONTEXT).split("[,;\\s]+").toSet)]
		
		// sanity checks
		"Same number of outcomes as generators".checkInvariant(outcomes.size == generators.values.filterNull.size)
		"Same number of outcomes as contexts".checkInvariant(outcomes.size == contexts.values.filterNull.size)
		"outdir cannot be empty".checkInvariant(configOutDir != null)
	}
	
	// extension method
	def private checkInvariant(String errorMessage, boolean predicate) {
		if( ! predicate ) throw new Exception("Expecting: " + errorMessage)
	}
	
	override toString() '''
		«FIELD_PROJECTDIR» = «projectDir»
		«FIELD_OUTPUTDIR»  = «outputDir»
		
		«FIELD_OUTCOMES»:
			«FOR outcome : outcomes»
			outcome   = «outcome»
			«SUBFIELD_GENERATOR» = «generators.get(outcome)»
			«SUBFIELD_CONTEXT»   = «contexts.get(outcome).join(", ")»
			«(1..30).map["-"].join»
			«ENDFOR»
	'''
}
