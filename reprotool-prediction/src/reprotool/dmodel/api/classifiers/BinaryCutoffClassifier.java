package reprotool.dmodel.api.classifiers;

import java.util.Iterator;
import java.util.Set;

import reprotool.dmodel.api.samples.FeatureEvent;

public class BinaryCutoffClassifier implements IClassifier {

	final private int cutoffValue;
	final private String positiveValue;
	final private Set<String> contextFeatures;
	final private String negativeValue;

	public BinaryCutoffClassifier(final int cutoffValue, final Set<String> contextFeatures, final String positiveValue, final String negativeValue) {
		this.cutoffValue = cutoffValue;
		this.contextFeatures = contextFeatures;
		this.positiveValue = positiveValue;
		this.negativeValue = negativeValue;
	}
	
	@Override
	final public void train(final Iterable<FeatureEvent> samples) {
		// ignored in this classifier
	}

	@Override
	final public FeatureEvent predict(final FeatureEvent sample) {
		
		int totalPositiveContexts = 0;
		for(final String ctxFeature : sample.getContext()) {

			final String[] split = ctxFeature.split("=", 2);
			final String ctxName = split[0];
			
			if(split.length > 1 && contextFeatures.contains(ctxName)) {
				final String ctxValue = split[1];
				if(positiveValue.equals(ctxValue)) {
					totalPositiveContexts++;
				}
			}
		}
		final String outcome = totalPositiveContexts > cutoffValue
				? sample.getOutcomeFeatureName() + "=" + positiveValue
				: sample.getOutcomeFeatureName() + "=" + negativeValue;
		
//		System.out.println("total:" + totalPositiveContexts + " cut:" + cutoffValue + " outcome:" + outcome);
		return FeatureEvent.createFrom(outcome, sample.getContext(), sample.getAttachment());
	}

	@Override
	final public Iterator<FeatureEvent> predictIterator(final Iterable<FeatureEvent> samples) {
		return new Iterator<FeatureEvent>() {

			final private Iterator<FeatureEvent> otherIterator = samples.iterator();

			@Override
			public boolean hasNext() {
				return otherIterator.hasNext();
			}

			@Override
			public FeatureEvent next() {
				return predict(otherIterator.next());
			}

			@Override
			public void remove() {
				otherIterator.remove();
			}
		};
	}

}
