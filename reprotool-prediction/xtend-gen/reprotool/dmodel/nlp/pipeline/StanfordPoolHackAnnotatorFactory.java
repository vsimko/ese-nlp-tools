package reprotool.dmodel.nlp.pipeline;

import org.eclipse.xtend.lib.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class StanfordPoolHackAnnotatorFactory /* implements AnnotatorFactory  */{
  @Property
  private /* Annotator */Object _annotator;
  
  public StanfordPoolHackAnnotatorFactory() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method super is undefined for the type StanfordPoolHackAnnotatorFactory");
  }
  
  public StanfordPoolHackAnnotatorFactory(final /* Annotator */Object a) {
    this();
    this.setAnnotator(a);
  }
  
  public Annotator create() {
    return this.getAnnotator();
  }
  
  public CharSequence signature() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("hacked");
    return _builder;
  }
  
  @Pure
  public Annotator getAnnotator() {
    return this._annotator;
  }
  
  public void setAnnotator(final Annotator annotator) {
    this._annotator = annotator;
  }
}
