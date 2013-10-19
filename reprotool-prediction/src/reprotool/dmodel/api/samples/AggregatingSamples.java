package reprotool.dmodel.api.samples;

import java.util.Iterator;

/**
 * Combines multiple {@link Iterable}<{@link FeatureEvent}> into a single iterable.
 * @author Viliam Simko
 */
public class AggregatingSamples implements Iterable<FeatureEvent> {

	final private Iterable<FeatureEvent>[] listOfIterables;

	public AggregatingSamples(final Iterable<FeatureEvent>[] listOfIterables) {
		this.listOfIterables = listOfIterables;
	}
	
	@Override
	public Iterator<FeatureEvent> iterator() {
		return new Iterator<FeatureEvent>() {
			
			private int currentIterableIdx = 0;
			private Iterator<FeatureEvent> currentIterator = createCurrentIterator();
			
			private Iterator<FeatureEvent> createCurrentIterator() {
				if(currentIterableIdx < listOfIterables.length) {
					return listOfIterables[currentIterableIdx++].iterator();
				}
				
				return null;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public FeatureEvent next() {
				return currentIterator.next();
			}
			
			@Override
			public boolean hasNext() {
				while(true) {
					if(currentIterator == null)
						return false;
					
					if(currentIterator.hasNext())
						return true;
					
					currentIterator = createCurrentIterator();
				}
			}
		};
	}

}
