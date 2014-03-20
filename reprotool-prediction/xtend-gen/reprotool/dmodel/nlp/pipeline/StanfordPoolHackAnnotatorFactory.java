package reprotool.dmodel.nlp.pipeline;

import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.AnnotatorFactory;
import java.util.Properties;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class StanfordPoolHackAnnotatorFactory extends AnnotatorFactory {
  private Annotator _annotator;
  
  public Annotator getAnnotator() {
    return this._annotator;
  }
  
  public void setAnnotator(final Annotator annotator) {
    this._annotator = annotator;
  }
  
  public StanfordPoolHackAnnotatorFactory() {
    super(new Function0<Properties>() {
      public Properties apply() {
        Properties _properties = new Properties();
        return _properties;
      }
    }.apply());
  }
  
  public StanfordPoolHackAnnotatorFactory(final Annotator a) {
    this();
    this.setAnnotator(a);
  }
  
  public Annotator create() {
    Annotator _annotator = this.getAnnotator();
    return _annotator;
  }
  
  public String signature() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("hacked");
    return _builder.toString();
  }
}
