package reprotool.dmodel.tools.phases.train;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class TrainingPhaseConfig {
  public final String projectDir;
  
  public final String outputDir;
  
  public final Set<String> outcomes;
  
  public final Map<String, String> generators = CollectionLiterals.<String, String>newHashMap();
  
  public final Map<String, Set<String>> contexts = CollectionLiterals.<String, Set<String>>newHashMap();
  
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
      _xifexpression = _builder.toString();
    }
    this.outputDir = _xifexpression;
    String _property_1 = config.getProperty(TrainingPhaseConfig.FIELD_OUTCOMES);
    String[] _split = _property_1.split("[,;\\s]+");
    Set<String> _set = IterableExtensions.<String>toSet(((Iterable<String>)Conversions.doWrapArray(_split)));
    this.outcomes = _set;
    final Consumer<String> _function = new Consumer<String>() {
      public void accept(final String it) {
        String _property = config.getProperty(((it + ".") + TrainingPhaseConfig.SUBFIELD_GENERATOR));
        TrainingPhaseConfig.this.generators.put(it, _property);
      }
    };
    this.outcomes.forEach(_function);
    final Consumer<String> _function_1 = new Consumer<String>() {
      public void accept(final String it) {
        String _property = config.getProperty(((it + ".") + TrainingPhaseConfig.SUBFIELD_CONTEXT));
        String[] _split = _property.split("[,;\\s]+");
        Set<String> _set = IterableExtensions.<String>toSet(((Iterable<String>)Conversions.doWrapArray(_split)));
        TrainingPhaseConfig.this.contexts.put(it, _set);
      }
    };
    this.outcomes.forEach(_function_1);
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
      if ((!predicate)) {
        throw new Exception(("Expecting: " + errorMessage));
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
        _builder.append(outcome, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(TrainingPhaseConfig.SUBFIELD_GENERATOR, "\t");
        _builder.append(" = ");
        String _get = this.generators.get(outcome);
        _builder.append(_get, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(TrainingPhaseConfig.SUBFIELD_CONTEXT, "\t");
        _builder.append("   = ");
        Set<String> _get_1 = this.contexts.get(outcome);
        String _join = IterableExtensions.join(_get_1, ", ");
        _builder.append(_join, "\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        IntegerRange _upTo = new IntegerRange(1, 30);
        final Function1<Integer, String> _function = new Function1<Integer, String>() {
          public String apply(final Integer it) {
            return "-";
          }
        };
        Iterable<String> _map = IterableExtensions.<Integer, String>map(_upTo, _function);
        String _join_1 = IterableExtensions.join(_map);
        _builder.append(_join_1, "\t");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
