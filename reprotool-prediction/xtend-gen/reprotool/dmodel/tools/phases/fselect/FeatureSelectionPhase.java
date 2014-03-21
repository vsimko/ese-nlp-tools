package reprotool.dmodel.tools.phases.fselect;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
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
import spec.DomainModel;
import spec.SpecDocument;
import spec.Specification;

@Component
@SuppressWarnings("all")
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
  
  @Reference
  public void setLogger(final ReprotoolLogger logger) {
    this.logger = logger;
  }
  
  private SpecModelLoader loader;
  
  @Reference
  public void setLoader(final SpecModelLoader loader) {
    this.loader = loader;
  }
  
  private FeatureExtractorFactory fexFactory;
  
  @Reference
  public void setFexFactory(final FeatureExtractorFactory factory) {
    this.fexFactory = factory;
  }
  
  public void execute(final String[] args) {
    try {
      int _size = ((List<String>)Conversions.doWrapArray(args)).size();
      boolean _lessThan = (_size < 1);
      if (_lessThan) {
        String _usage = this.getUsage();
        InputOutput.<String>println(_usage);
        return;
      }
      final String configFileName = args[0];
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Loading configuration file \"");
      _builder.append(configFileName, "");
      _builder.append("\"");
      this.logger.info(_builder);
      Properties _properties = new Properties();
      final Procedure1<Properties> _function = new Procedure1<Properties>() {
        public void apply(final Properties it) {
          try {
            FileReader _fileReader = new FileReader(configFileName);
            it.load(_fileReader);
            File _file = new File(configFileName);
            File _absoluteFile = _file.getAbsoluteFile();
            String _parent = _absoluteFile.getParent();
            String _property = it.getProperty("projectdir", _parent);
            it.setProperty("projectdir", _property);
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      Properties _doubleArrow = ObjectExtensions.<Properties>operator_doubleArrow(_properties, _function);
      FeatureSelectionPhaseConfig _featureSelectionPhaseConfig = new FeatureSelectionPhaseConfig(_doubleArrow);
      final FeatureSelectionPhaseConfig config = _featureSelectionPhaseConfig;
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Using project directory \"");
      _builder_1.append(config.projectDir, "");
      _builder_1.append("\"");
      this.logger.debug(_builder_1);
      StringConcatenation _builder_2 = new StringConcatenation();
      _builder_2.append("Loading the specification model from XMI file \"");
      _builder_2.append(config.specModelFileName, "");
      _builder_2.append("\"");
      this.logger.info(_builder_2);
      final Specification specModel = this.loader.loadSpecificationModel(config.specModelFileName);
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("# of documents: ");
      EList<SpecDocument> _documents = specModel.getDocuments();
      int _size_1 = _documents.size();
      _builder_3.append(_size_1, "");
      this.logger.info(_builder_3);
      StringConcatenation _builder_4 = new StringConcatenation();
      _builder_4.append("# of domain model elements: ");
      DomainModel _domainModel = specModel.getDomainModel();
      EPackage _modelPackage = _domainModel.getModelPackage();
      TreeIterator<EObject> _eAllContents = _modelPackage.eAllContents();
      int _size_2 = IteratorExtensions.size(_eAllContents);
      _builder_4.append(_size_2, "");
      this.logger.info(_builder_4);
      ArrayList<List<String>> _generateRandomCombinations = this.generateRandomCombinations(config.contextFeatures, config.maxSamples);
      final List<List<String>> contextsToEvaluate = ListExtensions.<List<String>>reverseView(_generateRandomCombinations);
      StringConcatenation _builder_5 = new StringConcatenation();
      _builder_5.append("Creating any necessary directories, where the results will be stored: ");
      _builder_5.append(config.resultDir, "");
      _builder_5.append(" ...");
      this.logger.info(_builder_5);
      File _file = new File(config.resultDir);
      _file.mkdirs();
      StringConcatenation _builder_6 = new StringConcatenation();
      _builder_6.append("The result directory should be ready now.");
      this.logger.info(_builder_6);
      PrintWriter _printWriter = new PrintWriter(config.resultFileName);
      final PrintWriter writerAggreg = _printWriter;
      writerAggreg.println("FsetID;FallOut;F1;F1-ci;Precision;Precision-ci;Recall;Recall-ci;SP;SP-ci;MCC;MCC-ci;Context;Outcome;CtxSize");
      final Procedure2<List<String>,Integer> _function_1 = new Procedure2<List<String>,Integer>() {
        public void apply(final List<String> ctx, final Integer currentRunId) {
          final List<String> immutableContext = ImmutableList.<String>copyOf(ctx);
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("We need to generate a new result based on the context: ");
          _builder.append(immutableContext, "");
          FeatureSelectionPhase.this.logger.info(_builder);
          final String newResult = FeatureSelectionPhase.this.generateNewResult(specModel, immutableContext, config);
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("set");
          _builder_1.append(currentRunId, "");
          _builder_1.append(";");
          _builder_1.append(newResult, "");
          writerAggreg.println(_builder_1);
          writerAggreg.flush();
        }
      };
      IterableExtensions.<List<String>>forEach(contextsToEvaluate, _function_1);
      writerAggreg.close();
      InputOutput.<String>println("done. see logs");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public String generateNewResult(final Specification specModel, final List<String> immutableContext, final FeatureSelectionPhaseConfig config) {
    try {
      String _xblockexpression = null;
      {
        ExtractedSamples _extractedSamples = new ExtractedSamples(this.fexFactory, specModel, config.contextGeneratorName, immutableContext, config.outcomeFeature);
        final ExtractedSamples samples = _extractedSamples;
        final List<FeatureEvent> randsamples = IterableExtensions.<FeatureEvent>toList(samples);
        Collections.shuffle(randsamples);
        CrossValidatingEvaluator _crossValidatingEvaluator = new CrossValidatingEvaluator(config.crossValidationFolds, config.outcomeFeature, config.positiveOutcomes);
        final CrossValidatingEvaluator evaluator = _crossValidatingEvaluator;
        MaxentClassifier _createEmptyClassifier = MaxentClassifier.createEmptyClassifier();
        final ContingencyTable[] results = evaluator.evaluate(_createEmptyClassifier, randsamples);
        final Function1<ContingencyTable,Double> _function = new Function1<ContingencyTable,Double>() {
          public Double apply(final ContingencyTable it) {
            double _precision = it.getPrecision();
            return Double.valueOf(_precision);
          }
        };
        List<Double> _map = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function);
        final Iterable<Double> precision_list = StatisticalExtensions.nanToZero(_map);
        final double precision_mean = StatisticalExtensions.mean(precision_list);
        final double precision_ci = StatisticalExtensions.confidence(precision_list);
        final Function1<ContingencyTable,Double> _function_1 = new Function1<ContingencyTable,Double>() {
          public Double apply(final ContingencyTable it) {
            double _recall = it.getRecall();
            return Double.valueOf(_recall);
          }
        };
        List<Double> _map_1 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_1);
        final Iterable<Double> recall_list = StatisticalExtensions.nanToZero(_map_1);
        final double recall_mean = StatisticalExtensions.mean(recall_list);
        final double recall_ci = StatisticalExtensions.confidence(recall_list);
        final Function1<ContingencyTable,Double> _function_2 = new Function1<ContingencyTable,Double>() {
          public Double apply(final ContingencyTable it) {
            double _f1Measure = it.getF1Measure();
            return Double.valueOf(_f1Measure);
          }
        };
        List<Double> _map_2 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_2);
        final Iterable<Double> f1_list = StatisticalExtensions.nanToZero(_map_2);
        final double f1_mean = StatisticalExtensions.mean(f1_list);
        final double f1_ci = StatisticalExtensions.confidence(f1_list);
        final Function1<ContingencyTable,Double> _function_3 = new Function1<ContingencyTable,Double>() {
          public Double apply(final ContingencyTable it) {
            double _specificity = it.getSpecificity();
            return Double.valueOf(_specificity);
          }
        };
        List<Double> _map_3 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_3);
        final Iterable<Double> specificity_list = StatisticalExtensions.nanToZero(_map_3);
        final double specificity_mean = StatisticalExtensions.mean(specificity_list);
        final double specificity_ci = StatisticalExtensions.confidence(specificity_list);
        final Function1<ContingencyTable,Double> _function_4 = new Function1<ContingencyTable,Double>() {
          public Double apply(final ContingencyTable it) {
            double _mCC = it.getMCC();
            return Double.valueOf(_mCC);
          }
        };
        List<Double> _map_4 = ListExtensions.<ContingencyTable, Double>map(((List<ContingencyTable>)Conversions.doWrapArray(results)), _function_4);
        final Iterable<Double> mcc_list = StatisticalExtensions.nanToZero(_map_4);
        final double mcc_mean = StatisticalExtensions.mean(mcc_list);
        final double mcc_ci = StatisticalExtensions.confidence(mcc_list);
        double _minus = (1 - specificity_mean);
        String _join = IterableExtensions.join(immutableContext, " ");
        int _size = immutableContext.size();
        ArrayList<Object> _newArrayList = CollectionLiterals.<Object>newArrayList(Double.valueOf(_minus), Double.valueOf(f1_mean), Double.valueOf(f1_ci), Double.valueOf(precision_mean), Double.valueOf(precision_ci), Double.valueOf(recall_mean), Double.valueOf(recall_ci), Double.valueOf(specificity_mean), Double.valueOf(specificity_ci), Double.valueOf(mcc_mean), Double.valueOf(mcc_ci), _join, 
          config.outcomeFeature, Integer.valueOf(_size));
        String _join_1 = IterableExtensions.join(_newArrayList, ";");
        _xblockexpression = (_join_1);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private ArrayList<List<String>> generateRandomCombinations(final List<String> list, final int howmany) {
    ArrayList<List<String>> _arrayList = new ArrayList<List<String>>();
    final ArrayList<List<String>> result = _arrayList;
    int _size = list.size();
    IntegerRange _upTo = new IntegerRange(1, _size);
    for (final Integer subsetSize : _upTo) {
      int _size_1 = list.size();
      List<List<Integer>> _randomCombinationsAsList = Combinations.randomCombinationsAsList(_size_1, (subsetSize).intValue(), howmany);
      for (final List<Integer> comb : _randomCombinationsAsList) {
        final Function1<Integer,String> _function = new Function1<Integer,String>() {
          public String apply(final Integer it) {
            String _get = list.get((it).intValue());
            return _get;
          }
        };
        List<String> _map = ListExtensions.<Integer, String>map(comb, _function);
        result.add(_map);
      }
    }
    return result;
  }
}
