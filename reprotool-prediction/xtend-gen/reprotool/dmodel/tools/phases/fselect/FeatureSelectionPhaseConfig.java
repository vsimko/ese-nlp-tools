package reprotool.dmodel.tools.phases.fselect;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class FeatureSelectionPhaseConfig {
  public final String projectDir;
  
  public final String resultDir;
  
  public final String specModelFileName;
  
  public final String resultFileName;
  
  public final String outcomeFeature;
  
  public final Set<String> positiveOutcomes;
  
  public final List<String> contextFeatures;
  
  public final int crossValidationFolds;
  
  public final int maxSamples;
  
  public final String contextGeneratorName;
  
  public FeatureSelectionPhaseConfig(final Properties config) {
    String _property = config.getProperty("projectdir");
    this.projectDir = _property;
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(this.projectDir, "");
    _builder.append("/");
    String _property_1 = config.getProperty("resultdir");
    _builder.append(_property_1, "");
    String _string = _builder.toString();
    this.resultDir = _string;
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(this.projectDir, "");
    _builder_1.append("/");
    String _property_2 = config.getProperty("specfile");
    _builder_1.append(_property_2, "");
    String _string_1 = _builder_1.toString();
    this.specModelFileName = _string_1;
    String _property_3 = config.getProperty("outcome");
    this.outcomeFeature = _property_3;
    String _property_4 = config.getProperty("positives");
    String[] _split = _property_4.split("[,;\\s]+");
    Set<String> _set = IterableExtensions.<String>toSet(((Iterable<String>)Conversions.doWrapArray(_split)));
    this.positiveOutcomes = _set;
    String _property_5 = config.getProperty("context");
    String[] _split_1 = _property_5.split("[,;\\s]+");
    this.contextFeatures = ((List<String>)Conversions.doWrapArray(_split_1));
    String _property_6 = config.getProperty("crossvalidation");
    int _parseInt = Integer.parseInt(_property_6);
    this.crossValidationFolds = _parseInt;
    String _property_7 = config.getProperty("maxsamples");
    int _parseInt_1 = Integer.parseInt(_property_7);
    this.maxSamples = _parseInt_1;
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append(this.resultDir, "");
    _builder_2.append("/");
    _builder_2.append(this.outcomeFeature, "");
    _builder_2.append("-eval.csv");
    String _string_2 = _builder_2.toString();
    this.resultFileName = _string_2;
    String _property_8 = config.getProperty("generator");
    this.contextGeneratorName = _property_8;
  }
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("projectDir           = ");
    _builder.append(this.projectDir, "");
    _builder.newLineIfNotEmpty();
    _builder.append("resultDir            = ");
    _builder.append(this.resultDir, "");
    _builder.newLineIfNotEmpty();
    _builder.append("specModelFileName    = ");
    _builder.append(this.specModelFileName, "");
    _builder.newLineIfNotEmpty();
    _builder.append("resultFileName       = ");
    _builder.append(this.resultFileName, "");
    _builder.newLineIfNotEmpty();
    _builder.append("outcomeFeature       = ");
    _builder.append(this.outcomeFeature, "");
    _builder.newLineIfNotEmpty();
    _builder.append("positiveOutcomes     = ");
    String _join = IterableExtensions.join(this.positiveOutcomes, ", ");
    _builder.append(_join, "");
    _builder.newLineIfNotEmpty();
    _builder.append("contextFeatures      = ");
    String _join_1 = IterableExtensions.join(this.contextFeatures, ", ");
    _builder.append(_join_1, "");
    _builder.newLineIfNotEmpty();
    _builder.append("crossValidationFolds = ");
    _builder.append(this.crossValidationFolds, "");
    _builder.newLineIfNotEmpty();
    _builder.append("maxSamples           = ");
    _builder.append(this.maxSamples, "");
    _builder.newLineIfNotEmpty();
    _builder.append("contextGeneratorName = ");
    _builder.append(this.contextGeneratorName, "");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
}
