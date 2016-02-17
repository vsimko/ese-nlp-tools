package reprotool.dmodel.nlp.pipeline;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import reprotool.predict.logging.ReprotoolLogger;

/* @Component(, )
 */@SuppressWarnings("all")
public class ReprotoolLinguisticPipeline /* implements AnnotationPipeline  */{
  private boolean beVerbose = false;
  
  private final Object hackedAnnotatorPool /* Skipped initializer because of errors */;
  
  @Extension
  private ReprotoolLogger logger;
  
  /* @Reference
   */public void setLogger(final ReprotoolLogger logger) {
    this.logger = logger;
  }
  
  public List<String> getXmlContext(final /* CoreLabel */Object token) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field XmlContextAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nget cannot be resolved");
  }
  
  public /* Map<Integer, CorefChain> */Object getCorefChain(final /* Annotation */Object document) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field CorefChainAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nget cannot be resolved");
  }
  
  public Object addAnnotator(final String name, final /* Annotator */Object a) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert implicit first argument from ReprotoolLinguisticPipeline to String"
      + "\nregister cannot be resolved");
  }
  
  private Thread activationThread;
  
  public void checkActivationInterrupted() {
    try {
      Thread _currentThread = Thread.currentThread();
      boolean _isInterrupted = _currentThread.isInterrupted();
      if (_isInterrupted) {
        throw new InterruptedException("Activation thread interrupted");
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Registers all annotators relevant for Reprotool
   * TODO: split annotators into multiple OSGi services and load them lazily.
   */
  /* @Activate
   */public void activate(final /* BundleContext */Object bundleContext) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field StanfordCoreNLP is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method or field accessible is undefined for the type ReprotoolLinguisticPipeline"
      + "\nPTBTokenizerAnnotator cannot be resolved."
      + "\nThe method loadMaxentModel is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method setMaxentModel is undefined for the type ReprotoolLinguisticPipeline"
      + "\nPOSTaggerAnnotator cannot be resolved."
      + "\nMorphaAnnotator cannot be resolved."
      + "\nNERCombinerAnnotator cannot be resolved."
      + "\nNERClassifierCombiner cannot be resolved."
      + "\nThe method or field NERClassifierCombiner is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method or field DefaultPaths is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method or field DefaultPaths is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method or field DefaultPaths is undefined for the type ReprotoolLinguisticPipeline"
      + "\nParserAnnotator cannot be resolved."
      + "\nThe method or field DefaultPaths is undefined for the type ReprotoolLinguisticPipeline"
      + "\nDeterministicCorefAnnotator cannot be resolved."
      + "\nType mismatch: cannot convert implicit first argument from Object to byte[]"
      + "\nType mismatch: cannot convert from null to int"
      + "\ngetDeclaredField cannot be resolved"
      + "\n=> cannot be resolved"
      + "\nbundle cannot be resolved"
      + "\ngetResource cannot be resolved"
      + "\nopenStream cannot be resolved"
      + "\nclose cannot be resolved"
      + "\nbundle cannot be resolved"
      + "\ngetResource cannot be resolved"
      + "\ntoExternalForm cannot be resolved"
      + "\nAPPLY_NUMERIC_CLASSIFIERS_DEFAULT cannot be resolved"
      + "\nDEFAULT_NER_THREECLASS_MODEL cannot be resolved"
      + "\nDEFAULT_NER_MUC_MODEL cannot be resolved"
      + "\nDEFAULT_NER_CONLL_MODEL cannot be resolved"
      + "\nDEFAULT_PARSER_MODEL cannot be resolved");
  }
  
  /* @Deactivate
   */public void deactivate(final /* BundleContext */Object bundleContext) {
    try {
      this.activationThread.interrupt();
      this.activationThread.join();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private /* AbstractSequenceClassifier<CoreLabel> */Object prepareCRFClassifier(final /* BundleContext */Object bundleContext, final String resourceName) {
    throw new Error("Unresolved compilation problems:"
      + "\nCRFClassifier cannot be resolved."
      + "\nSeqClassifierFlags cannot be resolved."
      + "\nThe method loadClassifier is undefined for the type ReprotoolLinguisticPipeline"
      + "\nbundle cannot be resolved"
      + "\ngetResource cannot be resolved"
      + "\nopenStream cannot be resolved"
      + "\n=> cannot be resolved");
  }
  
  /**
   * Performs analysis by reading the text from file
   */
  public /* Annotation */Object analyzeTextFromFile(final String fileName) {
    try {
      File _file = new File(fileName);
      String _string = Files.toString(_file, Charsets.UTF_8);
      return this.analyzeText(_string);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Performs analysis of text which contains multiple sentences.
   */
  public /* Annotation */Object analyzeText(final String text) {
    throw new Error("Unresolved compilation problems:"
      + "\nAnnotation cannot be resolved."
      + "\nThe method annotate is undefined for the type ReprotoolLinguisticPipeline");
  }
  
  public Object toToken(final /* CorefMention */Object mention, final /* Annotation */Object document) {
    throw new Error("Unresolved compilation problems:"
      + "\nTree cannot be resolved to a type."
      + "\nThe method or field TreeAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nsentNum cannot be resolved"
      + "\n- cannot be resolved"
      + "\nheadIndex cannot be resolved"
      + "\n- cannot be resolved"
      + "\nsentences cannot be resolved"
      + "\nget cannot be resolved"
      + "\nget cannot be resolved"
      + "\ntaggedLabeledYield cannot be resolved"
      + "\nget cannot be resolved"
      + "\nsentIndex cannot be resolved"
      + "\n>= cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nsentIndex cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nindex cannot be resolved"
      + "\n>= cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nindex cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nsetSentIndex cannot be resolved"
      + "\nsetIndex cannot be resolved");
  }
  
  public /* HashMap<CoreLabel, CoreLabel> */Object getCoreferences(final /* Annotation */Object document) {
    throw new Error("Unresolved compilation problems:"
      + "\nCoreLabel cannot be resolved to a type."
      + "\nCoreLabel cannot be resolved to a type."
      + "\nThe method or field value is undefined for the type ReprotoolLinguisticPipeline"
      + "\ncorefChain cannot be resolved"
      + "\n!= cannot be resolved"
      + "\ncorefChain cannot be resolved"
      + "\nentrySet cannot be resolved"
      + "\nmap cannot be resolved"
      + "\nmentionsInTextualOrder cannot be resolved"
      + "\nempty cannot be resolved"
      + "\n! cannot be resolved"
      + "\nrepresentativeMention cannot be resolved"
      + "\ntoToken cannot be resolved"
      + "\ngetMentionsInTextualOrder cannot be resolved"
      + "\ntoToken cannot be resolved");
  }
  
  public /* List<CoreMap> */Object getSentences(final /* Annotation */Object document) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field SentencesAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nget cannot be resolved");
  }
  
  public /* SemanticGraph */Object getCollapsedCCProcessedDependencies(final /* CoreMap */Object sentence) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field CollapsedCCProcessedDependenciesAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nget cannot be resolved");
  }
  
  public /* List<CoreLabel> */Object getTokens(final /* CoreMap */Object sentence) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field TokensAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nget cannot be resolved");
  }
  
  public String getWordStr(final /* CoreLabel */Object token) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field TextAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nget cannot be resolved");
  }
  
  public /* XMLTag */Object getInnermostXmlTag(final /* CoreLabel */Object token) {
    throw new Error("Unresolved compilation problems:"
      + "\nget cannot be resolved");
  }
  
  public Integer getWordIndex(final /* CoreLabel */Object token) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field IndexAnnotation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nget cannot be resolved");
  }
  
  public Object toEntityLink(final /* XMLTag */Object tag, final /* Map<XMLTag, EntityLink> */Object map) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field SpecFactory is undefined for the type ReprotoolLinguisticPipeline"
      + "\nname cannot be resolved"
      + "\n== cannot be resolved"
      + "\nattributes cannot be resolved"
      + "\nget cannot be resolved"
      + "\n== cannot be resolved"
      + "\nattributes cannot be resolved"
      + "\nget cannot be resolved"
      + "\n== cannot be resolved"
      + "\nstartsWith cannot be resolved"
      + "\nsubstring cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateEntityLink cannot be resolved"
      + "\nentLabel cannot be resolved");
  }
  
  /**
   * Convert analyzed document into a specification model
   */
  public /* SpecDocument */Object analyzedDocToSpecDoc(final /* Annotation */Object document) {
    throw new Error("Unresolved compilation problems:"
      + "\nXMLTag cannot be resolved to a type."
      + "\nEntityLink cannot be resolved to a type."
      + "\nSpecWord cannot be resolved to a type."
      + "\nThe method or field SpecFactory is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method or field SpecFactory is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method or field SpecFactory is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method or field SpecFactory is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method setLabel is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method relation is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method setLinkGov is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method governor is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method setLinkDep is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method dependent is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method sentIndex is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThe method sentIndex is undefined for the type ReprotoolLinguisticPipeline"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\neINSTANCE cannot be resolved"
      + "\ncreateSpecDocument cannot be resolved"
      + "\nsentences cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateSpecSentence cannot be resolved"
      + "\nsentences cannot be resolved"
      + "\n+= cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateSpecWord cannot be resolved"
      + "\nwords cannot be resolved"
      + "\n+= cannot be resolved"
      + "\noriginal cannot be resolved"
      + "\nwordStr cannot be resolved"
      + "\nposTag cannot be resolved"
      + "\ntag cannot be resolved"
      + "\nlemma cannot be resolved"
      + "\nlemma cannot be resolved"
      + "\ninnermostXmlTag cannot be resolved"
      + "\ntoEntityLink cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nlinkedWords cannot be resolved"
      + "\n+= cannot be resolved"
      + "\nentityLinks cannot be resolved"
      + "\n+= cannot be resolved"
      + "\nempty cannot be resolved"
      + "\n! cannot be resolved"
      + "\nfirstRoot cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nindex cannot be resolved"
      + "\n- cannot be resolved"
      + "\nsentence cannot be resolved"
      + "\nsemanticRootWord cannot be resolved"
      + "\nedgeIterable cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\ntypedDependencies cannot be resolved"
      + "\n+= cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateWordDependency cannot be resolved"
      + "\n=> cannot be resolved"
      + "\ntoString cannot be resolved"
      + "\nindex cannot be resolved"
      + "\n- cannot be resolved"
      + "\nindex cannot be resolved"
      + "\n- cannot be resolved"
      + "\ncoreferences cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\n-> cannot be resolved"
      + "\n-> cannot be resolved"
      + "\ncorefRepMention cannot be resolved");
  }
}
