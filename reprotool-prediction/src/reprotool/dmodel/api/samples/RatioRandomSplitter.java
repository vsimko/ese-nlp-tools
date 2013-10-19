package reprotool.dmodel.api.samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.naming.directory.InvalidAttributeValueException;

/**
 * Splits data randomly into two parts.
 * <ol>
 * 	<li>first call the {@code shuffleAndSplit}</li>
 * 	<li>then get the first portion of samples using {@code getFirstPart}</li>
 * 	<li>finally get the rest using {@code getSecondPart}</li>
 * </ol>
 * @author Viliam Simko
 * @param <T> type of the element within the list
 */
public class RatioRandomSplitter<T> {

	private Iterable<T> originalIterable;
	private List<T> shuffledList;
	private int splitIndex; // the index if the first element in the second list

	public RatioRandomSplitter(final Iterable<T> originalIterable) {
		this.originalIterable = originalIterable;
	}
	
	/**
	 * Items from the original iterable will be copied to a separate list,
	 * shuffled and the shuffled list will be later split into two parts.
	 * @param firstPortion a value between 0 and 1 representing the first portion.
	 * @throws InvalidAttributeValueException
	 */
	final public void shuffleAndSplit(final double firstPortion) throws InvalidAttributeValueException {

		if(firstPortion < 0 || firstPortion > 1)
			throw new InvalidAttributeValueException("The ratio must be between 0 and 1 (double)");
		
		// converting iterable to list before we can shuffle it
		shuffledList = new ArrayList<>();
		for (T element : originalIterable) {
			shuffledList.add(element);
		}

		// now we can shuffle the list
		Collections.shuffle(shuffledList);
		
		splitIndex = (int) (shuffledList.size() * firstPortion);
	}
	
	/**
	 * First portion is from index 0 to splitIndex-1.
	 * @return Iterable over the first shuffled portion of the original iterable.
	 */
	final public Iterable<T> getFirstPart() {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					
					private int currentIndex = 0;

					@Override
					public boolean hasNext() {
						return currentIndex < splitIndex;
					}

					@Override
					public T next() {
						return shuffledList.get(currentIndex++);
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	
	/**
	 * Second portion is from index splitIndex to the last index.
	 * @return Iterable over the second shuffled portion of the original iterable.
	 */
	final public Iterable<T> getSecondPart() {
		return new Iterable<T>() {
			
			@Override
			public Iterator<T> iterator() {
				return shuffledList.listIterator(splitIndex);
			}
		};
	}
}
