package reprotool.dmodel.api.samples;

import java.util.Iterator;

/**
 * Converts {@link String}s to {@link FeatureEvent}s.
 * @author Viliam Simko
 */
public class StringBasedSamples implements Iterable<FeatureEvent> {

	final private Iterable<String> otherIterable;
	final private String outcomeFeatureName;

	public StringBasedSamples(final Iterable<String> otherIterable, final String outcomeFeatureName) {
		this.otherIterable = otherIterable;
		this.outcomeFeatureName = outcomeFeatureName;
	}
	
	@Override
	public Iterator<FeatureEvent> iterator() {
		return new Iterator<FeatureEvent>() {

			private Iterator<String> otherIterator = otherIterable.iterator();
			
			@Override
			public boolean hasNext() {
				return otherIterator.hasNext();
			}

			@Override
			public FeatureEvent next() {
				final String str = otherIterator.next();
				return FeatureEvent.createFrom(str.split(" "), outcomeFeatureName, null);
			}

			@Override
			public void remove() {
				otherIterator.remove();
			}
		};
	}

}