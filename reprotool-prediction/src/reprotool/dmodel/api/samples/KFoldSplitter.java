package reprotool.dmodel.api.samples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides an iterator which implements the k-fold splitting of samples {@link Iterable}<{@link FeatureEvent}>.
 * In each iteration, the samples will be divided into {@code trainSamples} and {@code testSamples} stored as {@link KFoldSplitter.SplitHolder}.
 * @author Viliam Simko
 */
public class KFoldSplitter implements Iterable<KFoldSplitter.SplitHolder> {

	public class SplitHolder {
		
		final private Iterable<FeatureEvent> trainSamples;
		final private Iterable<FeatureEvent> testSamples;
		final private int chunkNumber;
		
		public SplitHolder(final Iterable<FeatureEvent> trainSamples, final Iterable<FeatureEvent> testSamples, final int chunkNumber) {
			this.trainSamples = trainSamples;
			this.testSamples = testSamples;
			this.chunkNumber = chunkNumber;
		}
		
		final public Iterable<FeatureEvent> getTestSamples() {
			return testSamples;
		}
		
		final public Iterable<FeatureEvent> getTrainSamples() {
			return trainSamples;
		}
		
		final public int getChunkNumber() {
			return chunkNumber;
		}
	}
	
	final private List<FeatureEvent> originalList = new ArrayList<>();
	final private int chunkSize;
	final private int k;
	
	public KFoldSplitter(final Iterable<FeatureEvent> originalIterable, final int k) {
		for (FeatureEvent item : originalIterable) {
			originalList.add(item);
		}
		this.k = k;
		chunkSize = originalList.size() / k;
	}
	

	@Override
	public Iterator<SplitHolder> iterator() {
		return new Iterator<SplitHolder>() {
			private int chunkNumber = 0;
			
			@Override
			public boolean hasNext() {
				return chunkNumber < k;
			}

			@Override
			public SplitHolder next() {
				final List<FeatureEvent> trainSamples = new ArrayList<>(chunkSize * k);
				final List<FeatureEvent> testSamples = new ArrayList<>(chunkSize);

				for(int i=0; i<originalList.size(); ++i) {
					final FeatureEvent sample = originalList.get(i);
					final int thisStart = chunkNumber * chunkSize;
					final int nextStart = chunkNumber == k-1 ? originalList.size() : thisStart+chunkSize;
					if(i>=thisStart && i<nextStart)
						testSamples.add(sample);
					else
						trainSamples.add(sample);
				}
				
				chunkNumber++;
				return new SplitHolder(trainSamples, testSamples, chunkNumber);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
}
