package reprotool.dmodel.tools.phases.train;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import reprotool.dmodel.api.FeatureExtractorFactory;
import reprotool.dmodel.api.classifiers.MaxentClassifier;
import reprotool.dmodel.api.samples.ExtractedSamples;
import reprotool.dmodel.tools.phases.train.TrainingPhaseConfig;
import reprotool.predict.exectoolapi.IExecutableTool;
import reprotool.predict.logging.ReprotoolLogger;
import reprotool.predict.smloader.SpecModelLoader;
import spec.Specification;

@Component
@SuppressWarnings("all")
public class TrainingPhase implements IExecutableTool {
  public String getUsage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("The training phase requires a configuration file and a fully");
    _builder.newLine();
    _builder.append("populated specification model which should contain a loaded domain model");
    _builder.newLine();
    _builder.append("and a document already analysed using the linguistic pipeline.");
    _builder.newLine();
    _builder.append("Also entity links should have already been resolved.");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("[specfile] = XMI file containing an existing specification model");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("[config]   = configuration file (Java Properties format)");
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
    int _size = ((List<String>)Conversions.doWrapArray(args)).size();
    boolean _notEquals = (_size != 2);
    if (_notEquals) {
      String _usage = this.getUsage();
      InputOutput.<String>println(_usage);
      return;
    }
    final String specModelFileName = args[0];
    final String configFileName = args[1];
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Loading specification file from XMI \"");
    _builder.append(specModelFileName, "");
    _builder.append("\"");
    this.logger.info(_builder);
    final Specification specModel = this.loader.loadSpecificationModel(specModelFileName);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Loading configuration file \"");
    _builder_1.append(configFileName, "");
    _builder_1.append("\"");
    this.logger.info(_builder_1);
    Properties _properties = new Properties();
    final Procedure1<Properties> _function = new Procedure1<Properties>() {
      public void apply(final Properties it) {
        try {
          File _file = new File(configFileName);
          File _absoluteFile = _file.getAbsoluteFile();
          String _parent = _absoluteFile.getParent();
          it.setProperty(TrainingPhaseConfig.FIELD_PROJECTDIR, _parent);
          FileReader _fileReader = new FileReader(configFileName);
          it.load(_fileReader);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    Properties _doubleArrow = ObjectExtensions.<Properties>operator_doubleArrow(_properties, _function);
    TrainingPhaseConfig _trainingPhaseConfig = new TrainingPhaseConfig(_doubleArrow);
    final TrainingPhaseConfig config = _trainingPhaseConfig;
    String _string = config.toString();
    this.logger.debug(_string);
    for (final String outcomeFeature : config.outcomes) {
      {
        final String generatorName = config.generators.get(outcomeFeature);
        final Set<String> contextFeatures = config.contexts.get(outcomeFeature);
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("Training the model for predicting \"");
        _builder_2.append(outcomeFeature, "");
        _builder_2.append("\" extracted by \"");
        _builder_2.append(generatorName, "");
        _builder_2.append("\" from: ");
        String _join = IterableExtensions.join(contextFeatures, ", ");
        _builder_2.append(_join, "");
        this.logger.info(_builder_2);
        ExtractedSamples _extractedSamples = new ExtractedSamples(this.fexFactory, specModel, generatorName, contextFeatures, outcomeFeature);
        final ExtractedSamples extractedSamples = _extractedSamples;
        StringConcatenation _builder_3 = new StringConcatenation();
        _builder_3.append("Creating any necessary directories, where the trained models will be stored: ");
        _builder_3.append(config.outputDir, "");
        _builder_3.append(" ...");
        this.logger.info(_builder_3);
        File _file = new File(config.outputDir);
        _file.mkdirs();
        StringConcatenation _builder_4 = new StringConcatenation();
        _builder_4.append("The result directory should be ready now.");
        this.logger.info(_builder_4);
        StringConcatenation _builder_5 = new StringConcatenation();
        _builder_5.append(config.outputDir, "");
        _builder_5.append("/");
        _builder_5.append(outcomeFeature, "");
        _builder_5.append(".maxent.gz");
        final String outModelFileName = _builder_5.toString();
        MaxentClassifier _createEmptyClassifier = MaxentClassifier.createEmptyClassifier();
        final Procedure1<MaxentClassifier> _function_1 = new Procedure1<MaxentClassifier>() {
          public void apply(final MaxentClassifier it) {
            try {
              it.train(extractedSamples);
              it.saveModelToFile(outModelFileName);
            } catch (Throwable _e) {
              throw Exceptions.sneakyThrow(_e);
            }
          }
        };
        ObjectExtensions.<MaxentClassifier>operator_doubleArrow(_createEmptyClassifier, _function_1);
        StringConcatenation _builder_6 = new StringConcatenation();
        _builder_6.append("Trained model saved to file \"");
        _builder_6.append(outModelFileName, "");
        _builder_6.append("\"");
        this.logger.info(_builder_6);
      }
    }
    InputOutput.<String>println("done. see logs");
  }
}
