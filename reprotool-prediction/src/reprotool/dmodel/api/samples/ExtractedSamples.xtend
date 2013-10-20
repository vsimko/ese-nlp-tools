package reprotool.dmodel.api.samples

import edu.stanford.nlp.ling.tokensregex.types.Value
import reprotool.dmodel.api.FeatureExtractor
import reprotool.dmodel.api.FeatureExtractorFactory
import reprotool.dmodel.ctxgen.ContextGeneratorFactory
import reprotool.dmodel.ctxgen.IContextGenerator
import spec.Specification

class ExtractedSamples implements Iterable<FeatureEvent> {

	val IContextGenerator<?> generator
	val Iterable<FeatureExtractor> contextFeatureExtractors
	val FeatureExtractor outcomeFeatureExtractor

	new(FeatureExtractorFactory factory, Specification specModel, String ctxGenName, Iterable<String> contextFeatures, String outcomeFeature) {
		generator = ContextGeneratorFactory.getContextGenerator(ctxGenName, specModel)
		contextFeatureExtractors = factory.getFeatureExtractors(contextFeatures)
		outcomeFeatureExtractor = factory.getFeatureExtractor(outcomeFeature)
	}
	
	override iterator() {
		generator.map[ element |
			val outcome = outcomeFeatureExtractor.featureName + "=" + outcomeFeatureExtractor.visit(element)
			val context = contextFeatureExtractors.map[ featureName -> visit(element)].filter[Value != null].map[key + "=" + Value].toList
			if(outcome == null && context.empty)
				null
			else
				FeatureEvent.createFrom(outcome, context, element);
		].filterNull.iterator
	}
}