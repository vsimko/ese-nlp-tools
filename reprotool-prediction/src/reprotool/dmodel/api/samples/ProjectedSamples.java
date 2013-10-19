package reprotool.dmodel.api.samples;

import java.util.Iterator;
import java.util.Set;

/**
 * Converts samples {@link Iterable}<{@link FeatureEvent}> into new samples {@link Iterable}<{@link FeatureEvent}> containing only a selected features. 
 * @author Viliam Simko
 */
public class ProjectedSamples implements Iterable<FeatureEvent> {

	final private Iterable<FeatureEvent> otherIterable;
	final private Set<String> retainedFeatureNames;
	

	public ProjectedSamples(final Iterable<FeatureEvent> otherIterable, final Set<String> retainedFeatureNames) {
		this.otherIterable = otherIterable;
		this.retainedFeatureNames = retainedFeatureNames;
	}
	
	@Override
	public Iterator<FeatureEvent> iterator() {
		return new Iterator<FeatureEvent>() {
			
			final Iterator<FeatureEvent> otherIterator = otherIterable.iterator();
			
			@Override
			public void remove() {
				otherIterator.remove();
			}
			
			@Override
			public FeatureEvent next() {
				final FeatureEvent event = otherIterator.next();
				return event.createProjection(retainedFeatureNames);
			}
			
			@Override
			public boolean hasNext() {
				return otherIterator.hasNext();
			}
		};
	}

}
