package reprotool.dmodel.api.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import opennlp.model.Event;

/**
 * Represents a single sample containing multiple features.
 * @author Viliam Simko
 */
public class FeatureEvent extends Event {

	private Object attachment;
	
	/**
	 * An optional reference to Object.  
	 * @return
	 */
	final public Object getAttachment() {
		return attachment;
	}

	/**
	 * The constructor is intentionally private.
	 * Use static factory methods instead.
	 * @param outcome
	 * @param context
	 */
	private FeatureEvent(final String outcome, final String[] context) {
		super(outcome, context);
	}
	
	final static public FeatureEvent createFrom(final String outcome, final String[] context, final Object attachement) {
		final FeatureEvent featureEvent = new FeatureEvent(outcome, context);
		featureEvent.attachment = attachement;
		return featureEvent;
	}

	final static public FeatureEvent createFrom(final String outcome, final List<String> context, final Object attachment) {
		final FeatureEvent featureEvent = new FeatureEvent(outcome, context.toArray(new String[context.size()]));
		featureEvent.attachment = attachment;
		return featureEvent;
	}
	
	final static public FeatureEvent createFrom(final String[] allFeatures, final String outcomeFeatureName, final Object attachment) {
		String outcome = null;
		final List<String> context = new ArrayList<>();
		
		for(int i=0; i<allFeatures.length; ++i) {
			
			final String feature = allFeatures[i];
			final String[] featSplit = feature.split("=", 2);
			final String featureName = featSplit[0];
			
			if(outcomeFeatureName.equals(featureName)) {
				if(outcome != null)
					throw new RuntimeException("there are multiple outcome features");
				
				outcome = feature;
				
			} else {
				context.add(feature);
			}
		}
		
		// if the outcome==null the implementation of Event actually uses "null" string
		return createFrom(outcome, context, attachment);
	}
	
	final public String getOutcomeFeatureName() {
		return getOutcome().replaceFirst("=.*$", "");
	}
	
	final public String getOutcomeFeatureValue() {
		return getOutcome().replaceFirst("^[^=]*=", "");
	}
	
	final public FeatureEvent createProjection(final Set<String> retainedFeatureNames) {
		
		// this method should be efficient therefore we don't use any iterator and just plain old arrays
		final String[] oldContext = getContext();
		final String[] newContext = new String[oldContext.length];
		int newContextLen = 0;
		
		for(int i = 0; i<oldContext.length; ++i) {
			final String feature = oldContext[i];
			final String[] featSplit = feature.split("=", 2);
			final String featName = featSplit[0];
			if(retainedFeatureNames.contains(featName)) {
				newContext[newContextLen] = feature;
				newContextLen++;
			}
		}
		
		return createFrom(getOutcome(), Arrays.copyOf(newContext, newContextLen), getAttachment());
	}
	
	final public String toSampleStr() {
		final String[] ctx = getContext();
		final StringBuffer buf = new StringBuffer();
		
		for(int i=0; i<ctx.length; ++i) {
			buf.append(ctx[i]);
			buf.append(" ");
		}
		
		buf.append(getOutcome());
		
		return buf.toString();
	}
	
	@Override
	public String toString() {
		final Object attach = getAttachment();
		if( attach == null)
			return super.toString();

		return super.toString() + " attachment:" + attach;
	}
}
