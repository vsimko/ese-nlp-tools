package reprotool.dmodel.api.classifiers;

import java.util.Iterator;

import reprotool.dmodel.api.samples.FeatureEvent;

public interface IClassifier {
	FeatureEvent predict(FeatureEvent sample);
	Iterator<FeatureEvent> predictIterator(Iterable<FeatureEvent> samples);
	void train(Iterable<FeatureEvent> samples);
}
