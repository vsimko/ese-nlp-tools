package reprotool.dmodel.tools.phases.fselect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import reprotool.dmodel.api.FeatureExtractorFactory;
import reprotool.dmodel.api.classifiers.ContingencyTable;
import reprotool.dmodel.api.classifiers.MaxentClassifier;
import reprotool.dmodel.api.samples.CrossValidatingEvaluator;
import reprotool.dmodel.api.samples.ExtractedSamples;
import reprotool.dmodel.api.samples.FeatureEvent;
import reprotool.dmodel.extensions.Combinations;
import reprotool.dmodel.extensions.StatisticalExtensions;
import reprotool.dmodel.tools.phases.fselect.FeatureSelectionPhaseConfig;
import reprotool.predict.exectoolapi.IExecutableTool;
import reprotool.predict.logging.ReprotoolLogger;
import reprotool.predict.mloaders.SpecModelLoader;

/* @Component
 */@SuppressWarnings("all")
public class FeatureSelectionPhase implements IExecutableTool {
  public String getUsage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("The feature selection phase requires a single configuration file which drives");
    _builder.newLine();
    _builder.append("the selection process. This phase assumes that we already have a preprocessed");
    _builder.newLine();
    _builder.append("specification model which contains:");
    _builder.newLine();
    _builder.append("- documents analyzed by the linguistic pipeline");
    _builder.newLine();
    _builder.append("- domain model (Ecore file)");
    _builder.newLine();
    _builder.append("- resolved entity links between the documents and the domain model");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("[config] = Configuration file describing the feature sets to be evaluated");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Extension
  private ReprotoolLogger logger;
  
  /* @Reference
   */public void setLogger(final ReprotoolLogger logger) {
    this.logger = logger;
  }
  
  private SpecModelLoader loader;
  
  /* @Reference
   */public void setLoader(final SpecModelLoader loader) {
    this.loader = loader;
  }
  
  private FeatureExtractorFactory fexFactory;
  
  /* @Reference
   */public void setFexFactory(final FeatureExtractorFactory factory) {
    this.fexFactory = factory;
  }
  
  public void execute(final String[] args) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method documents is undefined for the type FeatureSelectionPhase"
      + "\nThe method domainModel is undefined for the type FeatureSelectionPhase"
      + "\nsize cannot be resolved"
      + "\nmodelPackage cannot be resolved"
      + "\neAllContents cannot be resolved"
      + "\nsize cannot be resolved");
  }
  
  public String generateNewResult(final /* Specification */Object specModel, final List<String> immutableContext, final FeatureSelectionPhaseConfig config) {
    try {
      String _xblockexpression = null;
      {
        final ExtractedSamples samples = new ExtractedSamples(this.fexFactory, specModel, config.contextGeneratorName, immutableContext, config.outcomeFeature);
        final List<FeatureEvent> randsamples = IterableExtensions.<FeatureEvent>toList(samples);
        Collections.shuffle(randsamples);
        final CrossValidatingEvaluator evaluator = new CrossValidatingEvaluator(config.crossValidationFolds, config.outcomeFeature, config.positiveOutcomes);
        MaxentClassifier _createEmptyClassifier = MaxentClassifier.createEmptyClassifier();
        final ContingencyTable[] results = evaluator.evaluate(_createEmptyClassifier, randsamples);
        final Function1<ContingencyTable, Double> _function = new Function1<ContingencyTable, Double>() {
          public Double apply(final ContingencyTable it) {
            return Double.valueOf(it.getPrecision());
          }
        };
        List<Double> _map = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function);
        final Iterable<Double> precision_list = StatisticalExtensions.nanToZero(_map);
        final double precision_mean = StatisticalExtensions.mean(precision_list);
        final double precision_ci = StatisticalExtensions.confidence(precision_list);
        final Function1<ContingencyTable, Double> _function_1 = new Function1<ContingencyTable, Double>() {
          public Double apply(final ContingencyTable it) {
            return Double.valueOf(it.getRecall());
          }
        };
        List<Double> _map_1 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_1);
        final Iterable<Double> recall_list = StatisticalExtensions.nanToZero(_map_1);
        final double recall_mean = StatisticalExtensions.mean(recall_list);
        final double recall_ci = StatisticalExtensions.confidence(recall_list);
        final Function1<ContingencyTable, Double> _function_2 = new Function1<ContingencyTable, Double>() {
          public Double apply(final ContingencyTable it) {
            return Double.valueOf(it.getF1Measure());
          }
        };
        List<Double> _map_2 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_2);
        final Iterable<Double> f1_list = StatisticalExtensions.nanToZero(_map_2);
        final double f1_mean = StatisticalExtensions.mean(f1_list);
        final double f1_ci = StatisticalExtensions.confidence(f1_list);
        final Function1<ContingencyTable, Double> _function_3 = new Function1<ContingencyTable, Double>() {
          public Double apply(final ContingencyTable it) {
            return Double.valueOf(it.getSpecificity());
          }
        };
        List<Double> _map_3 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_3);
        final Iterable<Double> specificity_list = StatisticalExtensions.nanToZero(_map_3);
        final double specificity_mean = StatisticalExtensions.mean(specificity_list);
        final double specificity_ci = StatisticalExtensions.confidence(specificity_list);
        final Function1<ContingencyTable, Double> _function_4 = new Function1<ContingencyTable, Double>() {
          public Double apply(final ContingencyTable it) {
            return Double.valueOf(it.getMCC());
          }
        };
        List<Double> _map_4 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_4);
        final Iterable<Double> mcc_list = StatisticalExtensions.nanToZero(_map_4);
        final double mcc_mean = StatisticalExtensions.mean(mcc_list);
        final double mcc_ci = StatisticalExtensions.confidence(mcc_list);
        String _join = IterableExtensions.join(immutableContext, " ");
        int _size = immutableContext.size();
        ArrayList<Object> _newArrayList = CollectionLiterals.<Object>newArrayList(Double.valueOf((1 - specificity_mean)), Double.valueOf(f1_mean), Double.valueOf(f1_ci), Double.valueOf(precision_mean), Double.valueOf(precision_ci), Double.valueOf(recall_mean), Double.valueOf(recall_ci), Double.valueOf(specificity_mean), Double.valueOf(specificity_ci), Double.valueOf(mcc_mean), Double.valueOf(mcc_ci), _join, 
          config.outcomeFeature, Integer.valueOf(_size));
        _xblockexpression = IterableExtensions.join(_newArrayList, ";");
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private ArrayList<List<String>> generateRandomCombinations(final List<String> list, final int howmany) {
    final ArrayList<List<String>> result = new ArrayList<List<String>>();
    int _size = list.size();
    IntegerRange _upTo = new IntegerRange(1, _size);
    for (final Integer subsetSize : _upTo) {
      int _size_1 = list.size();
      List<List<Integer>> _randomCombinationsAsList = Combinations.randomCombinationsAsList(_size_1, (subsetSize).intValue(), howmany);
      for (final List<Integer> comb : _randomCombinationsAsList) {
        final Function1<Integer, String> _function = new Function1<Integer, String>() {
          public String apply(final Integer it) {
            return list.get((it).intValue());
          }
        };
        List<String> _map = ListExtensions.<Integer, String>map(comb, _function);
        result.add(_map);
      }
    }
    return result;
  }
}
