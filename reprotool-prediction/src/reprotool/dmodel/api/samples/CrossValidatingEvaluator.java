package reprotool.dmodel.api.samples;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import reprotool.dmodel.api.classifiers.ContingencyTable;
import reprotool.dmodel.api.classifiers.IClassifier;
import reprotool.dmodel.api.samples.KFoldSplitter.SplitHolder;

public class CrossValidatingEvaluator {
	
	final private int numFolds;
	final private Set<String> positiveAnswers = new HashSet<>();
	
	public CrossValidatingEvaluator(final int numFolds,final String outcomeFeatureName, final Set<String> positiveOutcomeValues) {
		this.numFolds = numFolds;

		for (final String outcomeValue : positiveOutcomeValues) {
			positiveAnswers.add(outcomeFeatureName + "=" + outcomeValue);
		}
	}
	
	/**
	 * K-fold cross validation.
	 * Trains a classifier k times and evaluates its prediction performance.
	 * Using the given {@link Classifier}, the method tries to predict the outcome from {@code goldSamples}.
	 * Then it compares the predicted outcome with the original outcome and computes the F1-measure.
	 */
	final public ContingencyTable[] evaluate(final IClassifier classifier, final Iterable<FeatureEvent> goldSamples) throws Exception {
		
		final KFoldSplitter splitter = new KFoldSplitter(goldSamples, numFolds);
		final ContingencyTable[] results = new ContingencyTable[numFolds];
		
		final Iterator<SplitHolder> iterator = splitter.iterator();
		for(int i = 0; i < numFolds; ++i) {
			final SplitHolder split = iterator.next();
			results[i] = evalSingleFold(classifier, split.getTrainSamples(), split.getTestSamples());
		}
		
		return results;
	}
	
	final private ContingencyTable evalSingleFold(final IClassifier classifier, final Iterable<FeatureEvent> trainSamples, final Iterable<FeatureEvent> testSamples) {
		
		// train the classifier
		classifier.train(trainSamples);
		
		final ContingencyTable contingencyTable = new ContingencyTable(positiveAnswers);
		
		final Iterable<FeatureEvent> gold = testSamples; // just renaming the variable
		
		final Iterator<FeatureEvent> goldIterator = gold.iterator();
		final Iterator<FeatureEvent> guessIterator = classifier.predictIterator(gold);
		
		while(goldIterator.hasNext()) {
			contingencyTable.updateSample(guessIterator.next().getOutcome(), goldIterator.next().getOutcome());
		}
		
		return contingencyTable;
	}
}
