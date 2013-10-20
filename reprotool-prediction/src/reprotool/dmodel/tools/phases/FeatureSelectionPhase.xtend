package reprotool.dmodel.tools.phases

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.io.File
import java.io.FileReader
import java.io.PrintWriter
import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.Properties
import java.util.Set
import reprotool.dmodel.api.FeatureExtractorFactory
import reprotool.dmodel.api.ITool
import reprotool.dmodel.api.classifiers.MaxentClassifier
import reprotool.dmodel.api.samples.CrossValidatingEvaluator
import reprotool.dmodel.api.samples.ExtractedSamples
import reprotool.dmodel.extensions.Combinations
import reprotool.predict.logging.ReprotoolLogger
import reprotool.prediction.api.loaders.SpecModelLoader
import spec.Specification

import static extension reprotool.dmodel.extensions.StatisticalExtensions.*

@Component
class FeatureSelectionPhase implements ITool {
	
	override getUsage() '''
	The feature selection phase requires a single configuration file which drives
	the selection process. This phase assumes that we already have a preprocessed
	specification model which contains:
	- documents analyzed by the linguistic pipeline
	- domain model (Ecore file)
	- resolved entity links between the documents and the domain model
		
		[config] = Configuration file describing the feature sets to be evaluated
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
		if(args.size < 1) {
			println(usage)
			return
		}
		
		val configFileName = args.get(0)
		
		'''Loading configuration file "«configFileName»"'''.info
		val config = new FeatureSelectionPhaseConfig(new Properties => [
			load(new FileReader(configFileName))
			setProperty("projectdir", getProperty("projectdir", new File(configFileName).absoluteFile.parent))
		])

		'''Using project directory "«config.projectDir»"'''.debug
		
		'''Loading the specification model from XMI file "«config.specModelFileName»"'''.info
		val specModel = loader.loadSpecificationModel(config.specModelFileName)
		
		'''# of documents: «specModel.documents.size»'''.info
		'''# of domain model elements: «specModel.domainModel.modelPackage.eAllContents.size»'''.info
		
		val contextsToEvaluate = config.contextFeatures.generateRandomCombinations(config.maxSamples).reverseView

		'''Creating any necessary directories, where the results will be stored: «config.resultDir» ...'''.info
		new File(config.resultDir).mkdirs
		'''The result directory should be ready now.'''.info
		
		val writerAggreg = new PrintWriter(config.resultFileName)
		writerAggreg.println("FsetID;FallOut;F1;F1-ci;Precision;Precision-ci;Recall;Recall-ci;SP;SP-ci;MCC;MCC-ci;Context;Outcome;CtxSize")

		contextsToEvaluate.forEach[ ctx, currentRunId |
			val immutableContext = ctx.immutableCopy // just to make sure we use an immutable copy
			
			'''We need to generate a new result based on the context: «immutableContext»'''.info
			val newResult = generateNewResult(specModel, immutableContext, config)
			writerAggreg.println('''set«currentRunId»;«newResult»''')
			
			// we need to flush data to the output to see intermediate results
			writerAggreg.flush
		]
		writerAggreg.close
		println("done. see logs")
	}

	def generateNewResult(Specification specModel, List<String> immutableContext, FeatureSelectionPhaseConfig config) {

		// samples generation
		val samples = new ExtractedSamples(fexFactory, specModel, config.contextGeneratorName, immutableContext, config.outcomeFeature)
		val randsamples = samples.toList
		Collections.shuffle(randsamples)
		
		// cross-validation
		val evaluator = new CrossValidatingEvaluator(config.crossValidationFolds, config.outcomeFeature, config.positiveOutcomes)
		val results = evaluator.evaluate(MaxentClassifier.createEmptyClassifier, randsamples)

		// computing descriptive statistics
		val precision_list	= results.map[precision].nanToZero
		val precision_mean	= precision_list.mean
		val precision_ci	= precision_list.confidence

		val recall_list		= results.map[recall].nanToZero
		val recall_mean		= recall_list.mean
		val recall_ci		= recall_list.confidence
		
		val f1_list			= results.map[f1Measure].nanToZero
		val f1_mean			= f1_list.mean
		val f1_ci			= f1_list.confidence

		val specificity_list	= results.map[specificity].nanToZero
		val specificity_mean	= specificity_list.mean
		val specificity_ci		= specificity_list.confidence
		
		val mcc_list		= results.map[MCC].nanToZero
		val mcc_mean		= mcc_list.mean
		val mcc_ci			= mcc_list.confidence

		// writing a single row to the output
		newArrayList(
			
			(1-specificity_mean), // Fall out = 1 - Specificity
			
			f1_mean,			f1_ci,
			precision_mean,		precision_ci,
			recall_mean,		recall_ci,
			specificity_mean,	specificity_ci,
			mcc_mean,			mcc_ci,
			
			immutableContext.join(" "),		// context features
			config.outcomeFeature,			// outcome feature
			immutableContext.size
		).join(";")
	}

//	def private generateCombinations(List<String> list, int percentualVariability) {
//		
//		val int maxsize = list.size
//		val float x = 1 - percentualVariability / 100f
//		val int minsize = Math.round(list.size * x)
//		
//		(minsize .. maxsize).map[ size |
//			val vec = Factory.createVector(list)
//			val gen = Factory.createSimpleCombinationGenerator(vec, size) as Iterable<ICombinatoricsVector<String>>
//			gen.map[vector]
//		].flatten.toList
//		.reverseView // from bigger to smaller sample sets
//	}
	
	def private generateRandomCombinations(List<String> list, int howmany) {
		val result = new ArrayList<List<String>>
		for(subsetSize : (1..list.size)) {
			for( comb : Combinations.randomCombinationsAsList(list.size, subsetSize, howmany)) {
				result.add(comb.map[list.get(it)])
			}
		}
		return result
	}
}

class FeatureSelectionPhaseConfig {
	
	public val String projectDir
	public val String resultDir
	public val String specModelFileName
	public val String resultFileName
	public val String outcomeFeature
	public val Set<String> positiveOutcomes
	public val List<String> contextFeatures
	public val int crossValidationFolds
	public val int maxSamples
	public val String contextGeneratorName
	
	new(Properties config) {
		projectDir = config.getProperty("projectdir")
		resultDir = '''«projectDir»/«config.getProperty("resultdir")»'''.toString
		specModelFileName = '''«projectDir»/«config.getProperty("specfile")»'''.toString
		outcomeFeature = config.getProperty("outcome")
		positiveOutcomes = config.getProperty("positives").split("[,;\\s]+").toSet
		contextFeatures = config.getProperty("context").split("[,;\\s]+")
		crossValidationFolds = Integer.parseInt( config.getProperty("crossvalidation") )
		maxSamples = Integer.parseInt( config.getProperty("maxsamples") )
		resultFileName = '''«resultDir»/«outcomeFeature»-eval.csv'''.toString
		contextGeneratorName = config.getProperty("generator")
	}
	
	override toString() '''
		projectDir           = «projectDir»
		resultDir            = «resultDir»
		specModelFileName    = «specModelFileName»
		resultFileName       = «resultFileName»
		outcomeFeature       = «outcomeFeature»
		positiveOutcomes     = «positiveOutcomes.join(", ")»
		contextFeatures      = «contextFeatures.join(", ")»
		crossValidationFolds = «crossValidationFolds»
		maxSamples           = «maxSamples»
		contextGeneratorName = «contextGeneratorName»
	'''
}
