package reprotool.dmodel.api.classifiers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import opennlp.maxent.GIS;
import opennlp.maxent.io.GISModelReader;
import opennlp.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.model.AbstractDataIndexer;
import opennlp.model.AbstractModel;
import opennlp.model.AbstractModelWriter;
import opennlp.model.ComparableEvent;
import opennlp.model.DataReader;
import opennlp.model.Event;
import opennlp.model.EventStream;
import opennlp.model.MaxentModel;
import opennlp.model.OnePassDataIndexer;
import opennlp.model.PlainTextFileDataReader;
import reprotool.dmodel.api.samples.FeatureEvent;

/**
 * Facade for the Maximum Entropy Classifier.
 * It supports prediction and training of maxent models.
 * @author Viliam Simko
 */
public class MaxentClassifier implements IClassifier {

	private MaxentModel maxentModel;

	/**
	 * Use static factory methods for creating new instances
	 */
	private MaxentClassifier() {}

	final public static MaxentClassifier createEmptyClassifier() {
		return new MaxentClassifier();
	}
	
//	@Deprecated
//	final public static MaxentClassifier createFromFile(final String fileName) throws IOException {
//		final AbstractModelReader modelReader = new SuffixSensitiveGISModelReader(new File(fileName));
//		final MaxentClassifier classifier = new MaxentClassifier();
//		classifier.maxentModel = modelReader.getModel();
//		return classifier;
//	}
	
	final public static MaxentClassifier createFromModel(final MaxentModel model) {
		final MaxentClassifier classifier = new MaxentClassifier();
		classifier.maxentModel = model;
		return classifier;
	}
	
	/**
	 * Locate the Maxent model on classpath.
	 * We presume that the Maxent model is a plain-text model compressed as GZIP file.
	 * Before this implemetation, we used the SuffixSensitiveGISModelReader to load the model
	 * as follows:
	 * 
	 * new SuffixSensitiveGISModelReader(new File(trainedModelsDir + File::separator + maxentModelName)).model
	 * 
	 * The problem was that we couldn't load the model when located inside a JAR.
	 * 
	 * @param maxentModelName
	 * @throws Exception
	 */
	final public static MaxentModel loadMaxentModel(final String maxentModelName) throws Exception {
		
		final InputStream inputStream = MaxentClassifier.class.getClassLoader().getResourceAsStream(maxentModelName);
		if(inputStream == null)
			throw new Exception( String.format("Could not find the Maxent model '%s' on classpath", maxentModelName));
		
		return loadMaxentModel(inputStream);
	}
	
	/**
	 * This will prepare the MaxEnt model if we already have an InputStream
	 * @param inputStream
	 * @throws IOException
	 */
	final public static MaxentModel loadMaxentModel(final InputStream inputStream) throws IOException {
			
		final InputStream decodedInputStream = new GZIPInputStream(inputStream);
		final DataReader modelReader = new PlainTextFileDataReader(decodedInputStream);
		final MaxentModel maxentModel = new GISModelReader(modelReader).getModel();
		
		return maxentModel;
	}
	
	
	
	// these constants can be redefined in some Guice configuration module
	int gisIterations = 100;
	boolean gisVerbose = false;
	boolean gisSmoothing = true;
	int gisCutoff = 5;
	boolean dataIndexerSort = true;
	
	@Override
	final public void train(final Iterable<FeatureEvent> trainSamples) {
		
		maxentModel = GIS.trainModel(
				gisIterations,
				new SilentOnePassDataIndexer( new FeatureEventStream(trainSamples), gisCutoff, dataIndexerSort),
				gisVerbose,		// print messages
				gisSmoothing,	// smoothing
				null,			// prior
				gisCutoff		// cutoff
			);
	}
	
	final public void saveModelToFile(final String outFileName) throws IOException {
		final File outFile = new File(outFileName);
		final AbstractModel model = (AbstractModel) maxentModel;
		final AbstractModelWriter writer = new SuffixSensitiveGISModelWriter(model, outFile);
		writer.persist();
	}

	@Override
	final public FeatureEvent predict(final FeatureEvent sample) {

		final String[] context = sample.getContext();
		
		// the classification happens here using the old context
		final double[] outcomeProbs = maxentModel.eval(context);
		final String outcome = maxentModel.getBestOutcome(outcomeProbs);
		
		// constructing a new feature event with the old context and new outcome
		return FeatureEvent.createFrom(outcome, context, sample.getAttachment());
	}

	@Override
	final public Iterator<FeatureEvent> predictIterator(final Iterable<FeatureEvent> samples) {
		return new Iterator<FeatureEvent>() {
			
			final private Iterator<FeatureEvent> otherIterator = samples.iterator();

			@Override
			public void remove() {
				otherIterator.remove();
			}
			
			@Override
			public FeatureEvent next() {
				return predict(otherIterator.next());
			}
			
			@Override
			public boolean hasNext() {
				return otherIterator.hasNext();
			}
		};
	}

	/**
	 * Replaces the original OnePassDataIndexer from OpenNLP project
	 * because the original implementation printed debug messages directly
	 * to the standard output.
	 * @author Viliam Simko
	 */
	private class SilentOnePassDataIndexer extends AbstractDataIndexer {

		/**
		 * @see OnePassDataIndexer#OnePassDataIndexer(EventStream, int, boolean)
		 */
		public SilentOnePassDataIndexer(final EventStream eventStream, final int cutoff, final boolean sort) {
			try {
				final Map<String, Integer> predicateIndex = new HashMap<>();
				final LinkedList<Event> events = computeEventCounts(eventStream, predicateIndex, cutoff);
				final List<ComparableEvent> eventsToCompare = index(events, predicateIndex);
				sortAndMerge(eventsToCompare, sort);
			} catch(final IOException e) {
				// silently ignored
			}
		}

		/**
		 * @see OnePassDataIndexer#computeEventCounts(EventStream, Map, int)
		 */
		private LinkedList<Event> computeEventCounts(final EventStream eventStream, final Map<String, Integer> predicatesInOut, final int cutoff) throws IOException {
			final Set<String> predicateSet = new HashSet<>();
			final Map<String, Integer> counter = new HashMap<String, Integer>();
			final LinkedList<Event> events = new LinkedList<Event>();
			while (eventStream.hasNext()) {
				Event ev = eventStream.next();
				events.addLast(ev);
				update(ev.getContext(), predicateSet, counter, cutoff);
			}
			predCounts = new int[predicateSet.size()];
			int index = 0;
			for (Iterator<String> pi = predicateSet.iterator(); pi.hasNext(); index++) {
				final String predicate = pi.next();
				predCounts[index] = counter.get(predicate);
				predicatesInOut.put(predicate, index);
			}
			return events;
		}

		/**
		 * @see OnePassDataIndexer#index(LinkedList, Map)
		 */
		protected List<ComparableEvent> index( final LinkedList<Event> events, final Map<String, Integer> predicateIndex) {

			final Map<String, Integer> omap = new HashMap<String, Integer>();

			final int numEvents = events.size();
			final List<ComparableEvent> eventsToCompare = new ArrayList<>(numEvents);
			final List<Integer> indexedContext = new ArrayList<Integer>();

			int outcomeCount = 0;
			
			for (int eventIndex = 0; eventIndex < numEvents; eventIndex++) {
				final Event ev = events.removeFirst();
				final String[] econtext = ev.getContext();
				ComparableEvent ce;

				int ocID;
				final String oc = ev.getOutcome();

				if (omap.containsKey(oc)) {
					ocID = omap.get(oc);
				} else {
					ocID = outcomeCount++;
					omap.put(oc, ocID);
				}

				for (int i = 0; i < econtext.length; i++) {
					final String pred = econtext[i];
					if (predicateIndex.containsKey(pred)) {
						indexedContext.add(predicateIndex.get(pred));
					}
				}

				// drop events with no active features
				if (indexedContext.size() > 0) {
					final int[] cons = new int[indexedContext.size()];
					for (int ci = 0; ci < cons.length; ci++) {
						cons[ci] = indexedContext.get(ci);
					}
					ce = new ComparableEvent(ocID, cons);
					eventsToCompare.add(ce);
				}
				
				// recycle the TIntArrayList
				indexedContext.clear();
			}
			outcomeLabels = toIndexedStringArray(omap);
			predLabels = toIndexedStringArray(predicateIndex);
			return eventsToCompare;
		}
	}

	/**
	 * Adapts {@link Iterable}<{@link FeatureEvent}> to {@link EventStream} because OpenNLP classes work with {@code EventStream}.
	 * @author Viliam Simko
	 */
	private class FeatureEventStream implements EventStream {

		private Iterator<FeatureEvent> iterator;

		public FeatureEventStream(final Iterable<FeatureEvent> iterable) {
			iterator = iterable.iterator();
		}
		
		@Override
		public Event next() throws IOException {
			return iterator.next();
		}

		@Override
		public boolean hasNext() throws IOException {
			return iterator.hasNext();
		}
	}

}
