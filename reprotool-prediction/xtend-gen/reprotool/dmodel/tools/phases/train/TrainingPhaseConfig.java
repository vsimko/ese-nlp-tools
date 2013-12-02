package reprotool.dmodel.tools.phases.train;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class TrainingPhaseConfig {
  public final String projectDir;
  
  public final String outputDir;
  
  public final Set<String> outcomes;
  
  public final Map<String,String> generators = new Function0<Map<String,String>>() {
    public Map<String,String> apply() {
      HashMap<String,String> _newHashMap = CollectionLiterals.<String, String>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  public final Map<String,Set<String>> contexts = new Function0<Map<String,Set<String>>>() {
    public Map<String,Set<String>> apply() {
      HashMap<String,Set<String>> _newHashMap = CollectionLiterals.<String, Set<String>>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  public final static String FIELD_PROJECTDIR = "projectdir";
  
  private final static String FIELD_OUTPUTDIR = "outdir";
  
  private final static String FIELD_OUTCOMES = "outcomes";
  
  private final static String SUBFIELD_GENERATOR = "generator";
  
  private final static String SUBFIELD_CONTEXT = "context";
  
  public TrainingPhaseConfig(final Properties config) {
    String _property = config.getProperty(TrainingPhaseConfig.FIELD_PROJECTDIR);
    this.projectDir = _property;
    final String configOutDir = config.getProperty(TrainingPhaseConfig.FIELD_OUTPUTDIR);
    String _xifexpression = null;
    boolean _startsWith = configOutDir.startsWith("/");
    if (_startsWith) {
      _xifexpression = configOutDir;
    } else {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(this.projectDir, "");
      _builder.append("/");
      _builder.append(configOutDir, "");
      String _string = _builder.toString();
      _xifexpression = _string;
    }
    this.outputDir = _xifexpression;
    String _property_1 = config.getProperty(TrainingPhaseConfig.FIELD_OUTCOMES);
    String[] _split = _property_1.split("[,;\\s]+");
    Set<String> _set = IterableExtensions.<String>toSet(((Iterable<String>)Conversions.doWrapArray(_split)));
    this.outcomes = _set;
    final Procedure1<String> _function = new Procedure1<String>() {
      public void apply(final String it) {
        String _plus = (it + ".");
        String _plus_1 = (_plus + TrainingPhaseConfig.SUBFIELD_GENERATOR);
        String _property = config.getProperty(_plus_1);
        TrainingPhaseConfig.this.generators.put(it, _property);
      }
    };
    IterableExtensions.<String>forEach(this.outcomes, _function);
    final Procedure1<String> _function_1 = new Procedure1<String>() {
      public void apply(final String it) {
        String _plus = (it + ".");
        String _plus_1 = (_plus + TrainingPhaseConfig.SUBFIELD_CONTEXT);
        String _property = config.getProperty(_plus_1);
        String[] _split = _property.split("[,;\\s]+");
        Set<String> _set = IterableExtensions.<String>toSet(((Iterable<String>)Conversions.doWrapArray(_split)));
        TrainingPhaseConfig.this.contexts.put(it, _set);
      }
    };
    IterableExtensions.<String>forEach(this.outcomes, _function_1);
    int _size = this.outcomes.size();
    Collection<String> _values = this.generators.values();
    Iterable<String> _filterNull = IterableExtensions.<String>filterNull(_values);
    int _size_1 = IterableExtensions.size(_filterNull);
    boolean _equals = (_size == _size_1);
    this.checkInvariant("Same number of outcomes as generators", _equals);
    int _size_2 = this.outcomes.size();
    Collection<Set<String>> _values_1 = this.contexts.values();
    Iterable<Set<String>> _filterNull_1 = IterableExtensions.<Set<String>>filterNull(_values_1);
    int _size_3 = IterableExtensions.size(_filterNull_1);
    boolean _equals_1 = (_size_2 == _size_3);
    this.checkInvariant("Same number of outcomes as contexts", _equals_1);
    boolean _notEquals = (!Objects.equal(configOutDir, null));
    this.checkInvariant("outdir cannot be empty", _notEquals);
  }
  
  private void checkInvariant(final String errorMessage, final boolean predicate) {
    try {
      boolean _not = (!predicate);
      if (_not) {
        String _plus = ("Expecting: " + errorMessage);
        Exception _exception = new Exception(_plus);
        throw _exception;
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(TrainingPhaseConfig.FIELD_PROJECTDIR, "");
    _builder.append(" = ");
    _builder.append(this.projectDir, "");
    _builder.newLineIfNotEmpty();
    _builder.append(TrainingPhaseConfig.FIELD_OUTPUTDIR, "");
    _builder.append("  = ");
    _builder.append(this.outputDir, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append(TrainingPhaseConfig.FIELD_OUTCOMES, "");
    _builder.append(":");
    _builder.newLineIfNotEmpty();
    {
      for(final String outcome : this.outcomes) {
        _builder.append("\t");
        _builder.append("outcome   = ");
        _builder.append(outcome, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(TrainingPhaseConfig.SUBFIELD_GENERATOR, "	");
        _builder.append(" = ");
        String _get = this.generators.get(outcome);
        _builder.append(_get, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(TrainingPhaseConfig.SUBFIELD_CONTEXT, "	");
        _builder.append("   = ");
        Set<String> _get_1 = this.contexts.get(outcome);
        String _join = IterableExtensions.join(_get_1, ", ");
        _builder.append(_join, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        IntegerRange _upTo = new IntegerRange(1, 30);
        final Function1<Integer,String> _function = new Function1<Integer,String>() {
          public String apply(final Integer it) {
            return "-";
          }
        };
        Iterable<String> _map = IterableExtensions.<Integer, String>map(_upTo, _function);
        String _join_1 = IterableExtensions.join(_map);
        _builder.append(_join_1, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
