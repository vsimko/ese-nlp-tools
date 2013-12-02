package reprotool.dmodel.nlp.pipeline;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import aQute.bnd.annotation.component.Reference;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.io.Files;
import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefChain.CorefMention;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.NERClassifierCombiner;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations.IndexAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.XmlContextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.AnnotatorPool;
import edu.stanford.nlp.pipeline.DefaultPaths;
import edu.stanford.nlp.pipeline.DeterministicCorefAnnotator;
import edu.stanford.nlp.pipeline.MorphaAnnotator;
import edu.stanford.nlp.pipeline.NERCombinerAnnotator;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.PTBTokenizerAnnotator;
import edu.stanford.nlp.pipeline.ParserAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.XMLUtils.XMLTag;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import opennlp.model.MaxentModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.MapExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import reprotool.dmodel.api.classifiers.MaxentClassifier;
import reprotool.dmodel.nlp.pipeline.ReprotoolXmlAnnotator;
import reprotool.dmodel.nlp.pipeline.ReprotoolXmlAnnotator.InnermostXmlTagAnnotation;
import reprotool.dmodel.nlp.pipeline.StanfordPoolHackAnnotatorFactory;
import reprotool.dmodel.nlp.ssplit.MaxentSSplitAnnotator;
import reprotool.predict.logging.ReprotoolLogger;
import spec.EntityLink;
import spec.SpecDocument;
import spec.SpecFactory;
import spec.SpecSentence;
import spec.SpecWord;
import spec.WordDependency;

@Component(provide = ReprotoolLinguisticPipeline.class, immediate = true)
@SuppressWarnings("all")
public class ReprotoolLinguisticPipeline extends AnnotationPipeline {
  private boolean beVerbose = false;
  
  private final AnnotatorPool hackedAnnotatorPool = new Function0<AnnotatorPool>() {
    public AnnotatorPool apply() {
      AnnotatorPool _annotatorPool = new AnnotatorPool();
      return _annotatorPool;
    }
  }.apply();
  
  @Extension
  private ReprotoolLogger logger;
  
  @Reference
  public void setLogger(final ReprotoolLogger logger) {
    this.logger = logger;
  }
  
  public List<String> getXmlContext(final CoreLabel token) {
    List<String> _get = token.<List<String>, XmlContextAnnotation>get(XmlContextAnnotation.class);
    return _get;
  }
  
  public Map<Integer,CorefChain> getCorefChain(final Annotation document) {
    Map<Integer,CorefChain> _get = document.<Map<Integer,CorefChain>, CorefChainAnnotation>get(CorefChainAnnotation.class);
    return _get;
  }
  
  public boolean addAnnotator(final String name, final Annotator a) {
    boolean _xblockexpression = false;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Adding \"");
      _builder.append(name, "");
      _builder.append("\" annotator to the pipeline");
      this.logger.info(_builder);
      this.addAnnotator(a);
      StanfordPoolHackAnnotatorFactory _stanfordPoolHackAnnotatorFactory = new StanfordPoolHackAnnotatorFactory(a);
      boolean _register = this.hackedAnnotatorPool.register(name, _stanfordPoolHackAnnotatorFactory);
      _xblockexpression = (_register);
    }
    return _xblockexpression;
  }
  
  private Thread activationThread;
  
  public void checkActivationInterrupted() {
    try {
      Thread _currentThread = Thread.currentThread();
      boolean _isInterrupted = _currentThread.isInterrupted();
      if (_isInterrupted) {
        InterruptedException _interruptedException = new InterruptedException("Activation thread interrupted");
        throw _interruptedException;
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Registers all annotators relevant for Reprotool
   */
  @Activate
  public void activate(final BundleContext bundleContext) {
    this.logger.info("REPROTOOL Linguistic Pipeline - Activation Started in a background thread");
    final Runnable _function = new Runnable() {
      public void run() {
        try {
          Field _declaredField = StanfordCoreNLP.class.getDeclaredField("pool");
          final Procedure1<Field> _function = new Procedure1<Field>() {
            public void apply(final Field it) {
              try {
                it.setAccessible(true);
                it.set(null, ReprotoolLinguisticPipeline.this.hackedAnnotatorPool);
              } catch (Throwable _e) {
                throw Exceptions.sneakyThrow(_e);
              }
            }
          };
          ObjectExtensions.<Field>operator_doubleArrow(_declaredField, _function);
          final int MAXSENTENCELEN = 256;
          PTBTokenizerAnnotator _pTBTokenizerAnnotator = new PTBTokenizerAnnotator(
            ReprotoolLinguisticPipeline.this.beVerbose, 
            "tokenizeNLs,invertible,ptb3Escaping=true");
          ReprotoolLinguisticPipeline.this.addAnnotator("tokenize", _pTBTokenizerAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
          ReprotoolXmlAnnotator _reprotoolXmlAnnotator = new ReprotoolXmlAnnotator(
            ".*", 
            "[hH][123456]|p|P|ul|UL|ol|OL", 
            "datetime|date", 
            "style|STYLE|script|SCRIPT|head|HEAD", 
            true);
          ReprotoolLinguisticPipeline.this.addAnnotator("cleanxml", _reprotoolXmlAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
          Bundle _bundle = bundleContext.getBundle();
          URL _resource = _bundle.getResource("reprotool/predict/models/ssplit/ssplit.maxent.gz");
          final InputStream ssplitStream = _resource.openStream();
          final MaxentModel ssplitModel = MaxentClassifier.loadMaxentModel(ssplitStream);
          ssplitStream.close();
          MaxentSSplitAnnotator _maxentSSplitAnnotator = new MaxentSSplitAnnotator();
          final MaxentSSplitAnnotator ssplitAnnotator = _maxentSSplitAnnotator;
          ssplitAnnotator.setMaxentModel(ssplitModel);
          ReprotoolLinguisticPipeline.this.addAnnotator("ssplit", ssplitAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
          Bundle _bundle_1 = bundleContext.getBundle();
          URL _resource_1 = _bundle_1.getResource("edu/stanford/nlp/models/pos-tagger/wsj-bidirectional/wsj-0-18-bidirectional-distsim.tagger");
          String _externalForm = _resource_1.toExternalForm();
          POSTaggerAnnotator _pOSTaggerAnnotator = new POSTaggerAnnotator(_externalForm, 
            ReprotoolLinguisticPipeline.this.beVerbose, MAXSENTENCELEN);
          ReprotoolLinguisticPipeline.this.addAnnotator("pos", _pOSTaggerAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
          MorphaAnnotator _morphaAnnotator = new MorphaAnnotator(ReprotoolLinguisticPipeline.this.beVerbose);
          ReprotoolLinguisticPipeline.this.addAnnotator("lemma", _morphaAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
          AbstractSequenceClassifier<CoreLabel> _prepareCRFClassifier = ReprotoolLinguisticPipeline.this.prepareCRFClassifier(bundleContext, DefaultPaths.DEFAULT_NER_THREECLASS_MODEL);
          AbstractSequenceClassifier<CoreLabel> _prepareCRFClassifier_1 = ReprotoolLinguisticPipeline.this.prepareCRFClassifier(bundleContext, DefaultPaths.DEFAULT_NER_MUC_MODEL);
          AbstractSequenceClassifier<CoreLabel> _prepareCRFClassifier_2 = ReprotoolLinguisticPipeline.this.prepareCRFClassifier(bundleContext, DefaultPaths.DEFAULT_NER_CONLL_MODEL);
          NERClassifierCombiner _nERClassifierCombiner = new NERClassifierCombiner(
            NERClassifierCombiner.APPLY_NUMERIC_CLASSIFIERS_DEFAULT, 
            false, _prepareCRFClassifier, _prepareCRFClassifier_1, _prepareCRFClassifier_2);
          NERCombinerAnnotator _nERCombinerAnnotator = new NERCombinerAnnotator(_nERClassifierCombiner, 
            ReprotoolLinguisticPipeline.this.beVerbose);
          ReprotoolLinguisticPipeline.this.addAnnotator("ner", _nERCombinerAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
          ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("-retainTmpSubcategories");
          ParserAnnotator _parserAnnotator = new ParserAnnotator(
            DefaultPaths.DEFAULT_PARSER_MODEL, 
            ReprotoolLinguisticPipeline.this.beVerbose, MAXSENTENCELEN, 
            ((String[]) ((String[])Conversions.unwrapArray(_newArrayList, String.class))));
          ReprotoolLinguisticPipeline.this.addAnnotator("parse", _parserAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
          Properties _properties = new Properties();
          DeterministicCorefAnnotator _deterministicCorefAnnotator = new DeterministicCorefAnnotator(_properties);
          ReprotoolLinguisticPipeline.this.addAnnotator("dcoref", _deterministicCorefAnnotator);
          ReprotoolLinguisticPipeline.this.checkActivationInterrupted();
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception e = (Exception)_t;
            String _message = e.getMessage();
            ReprotoolLinguisticPipeline.this.logger.error(_message);
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
        ReprotoolLinguisticPipeline.this.logger.info("REPROTOOL Linguistic Pipeline - background thread finished");
      }
    };
    Thread _thread = new Thread(_function);
    this.activationThread = _thread;
    this.activationThread.start();
  }
  
  @Deactivate
  public void deactivate(final BundleContext bundleContext) {
    try {
      this.activationThread.interrupt();
      this.activationThread.join();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private AbstractSequenceClassifier<CoreLabel> prepareCRFClassifier(final BundleContext bundleContext, final String resourceName) {
    try {
      CRFClassifier<CoreLabel> _xblockexpression = null;
      {
        Bundle _bundle = bundleContext.getBundle();
        URL _resource = _bundle.getResource(resourceName);
        final InputStream inputStream = _resource.openStream();
        SeqClassifierFlags _seqClassifierFlags = new SeqClassifierFlags();
        CRFClassifier<CoreLabel> _cRFClassifier = new CRFClassifier<CoreLabel>(_seqClassifierFlags);
        final Procedure1<CRFClassifier<CoreLabel>> _function = new Procedure1<CRFClassifier<CoreLabel>>() {
          public void apply(final CRFClassifier<CoreLabel> it) {
            try {
              GZIPInputStream _gZIPInputStream = new GZIPInputStream(inputStream);
              it.loadClassifier(_gZIPInputStream);
            } catch (Throwable _e) {
              throw Exceptions.sneakyThrow(_e);
            }
          }
        };
        CRFClassifier<CoreLabel> _doubleArrow = ObjectExtensions.<CRFClassifier<CoreLabel>>operator_doubleArrow(_cRFClassifier, _function);
        _xblockexpression = (_doubleArrow);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Performs analysis by reading the text from file
   */
  public Annotation analyzeTextFromFile(final String fileName) {
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
  public Annotation analyzeText(final String text) {
    Annotation _annotation = new Annotation(text);
    final Annotation document = _annotation;
    this.annotate(document);
    return document;
  }
  
  public CoreLabel toToken(final CorefMention mention, final Annotation document) {
    final int sentIndex = (mention.sentNum - 1);
    final int tokenIndex = (mention.headIndex - 1);
    List<CoreMap> _sentences = this.getSentences(document);
    final CoreMap sentence = _sentences.get(sentIndex);
    final Tree tree = sentence.<Tree, TreeAnnotation>get(TreeAnnotation.class);
    final List<CoreLabel> nodes = tree.taggedLabeledYield();
    final CoreLabel token = nodes.get(tokenIndex);
    boolean _and = false;
    int _sentIndex = token.sentIndex();
    boolean _greaterEqualsThan = (_sentIndex >= 0);
    if (!_greaterEqualsThan) {
      _and = false;
    } else {
      int _sentIndex_1 = token.sentIndex();
      boolean _notEquals = (_sentIndex_1 != sentIndex);
      _and = (_greaterEqualsThan && _notEquals);
    }
    if (_and) {
      RuntimeException _runtimeException = new RuntimeException("Sentence indexes do not match");
      throw _runtimeException;
    }
    boolean _and_1 = false;
    int _index = token.index();
    boolean _greaterEqualsThan_1 = (_index >= 0);
    if (!_greaterEqualsThan_1) {
      _and_1 = false;
    } else {
      int _index_1 = token.index();
      boolean _notEquals_1 = (_index_1 != tokenIndex);
      _and_1 = (_greaterEqualsThan_1 && _notEquals_1);
    }
    if (_and_1) {
      RuntimeException _runtimeException_1 = new RuntimeException("Token indexes do not match");
      throw _runtimeException_1;
    }
    token.setSentIndex(sentIndex);
    token.setIndex(tokenIndex);
    return token;
  }
  
  public HashMap<CoreLabel,CoreLabel> getCoreferences(final Annotation document) {
    HashMap<CoreLabel,CoreLabel> _hashMap = new HashMap<CoreLabel, CoreLabel>();
    final HashMap<CoreLabel,CoreLabel> result = _hashMap;
    Map<Integer,CorefChain> _corefChain = this.getCorefChain(document);
    boolean _notEquals = (!Objects.equal(_corefChain, null));
    if (_notEquals) {
      Map<Integer,CorefChain> _corefChain_1 = this.getCorefChain(document);
      Set<Entry<Integer,CorefChain>> _entrySet = _corefChain_1.entrySet();
      final Function1<Entry<Integer,CorefChain>,CorefChain> _function = new Function1<Entry<Integer,CorefChain>,CorefChain>() {
        public CorefChain apply(final Entry<Integer,CorefChain> it) {
          CorefChain _value = it.getValue();
          return _value;
        }
      };
      Iterable<CorefChain> _map = IterableExtensions.<Entry<Integer,CorefChain>, CorefChain>map(_entrySet, _function);
      for (final CorefChain entry : _map) {
        List<CorefMention> _mentionsInTextualOrder = entry.getMentionsInTextualOrder();
        boolean _isEmpty = _mentionsInTextualOrder.isEmpty();
        boolean _not = (!_isEmpty);
        if (_not) {
          CorefMention _representativeMention = entry.getRepresentativeMention();
          final CoreLabel rtoken = this.toToken(_representativeMention, document);
          List<CorefMention> _mentionsInTextualOrder_1 = entry.getMentionsInTextualOrder();
          for (final CorefMention corefMention : _mentionsInTextualOrder_1) {
            {
              final CoreLabel mtoken = this.toToken(corefMention, document);
              result.put(mtoken, rtoken);
            }
          }
        }
      }
    }
    return result;
  }
  
  public List<CoreMap> getSentences(final Annotation document) {
    List<CoreMap> _get = document.<List<CoreMap>, SentencesAnnotation>get(SentencesAnnotation.class);
    return _get;
  }
  
  public SemanticGraph getCollapsedCCProcessedDependencies(final CoreMap sentence) {
    SemanticGraph _get = sentence.<SemanticGraph, CollapsedCCProcessedDependenciesAnnotation>get(CollapsedCCProcessedDependenciesAnnotation.class);
    return _get;
  }
  
  public List<CoreLabel> getTokens(final CoreMap sentence) {
    List<CoreLabel> _get = sentence.<List<CoreLabel>, TokensAnnotation>get(TokensAnnotation.class);
    return _get;
  }
  
  public String getWordStr(final CoreLabel token) {
    String _get = token.<String, TextAnnotation>get(TextAnnotation.class);
    return _get;
  }
  
  public XMLTag getInnermostXmlTag(final CoreLabel token) {
    XMLTag _get = token.<XMLTag, InnermostXmlTagAnnotation>get(InnermostXmlTagAnnotation.class);
    return _get;
  }
  
  public Integer getWordIndex(final CoreLabel token) {
    Integer _get = token.<Integer, IndexAnnotation>get(IndexAnnotation.class);
    return _get;
  }
  
  public EntityLink toEntityLink(final XMLTag tag, final Map<XMLTag,EntityLink> map) {
    boolean _equalsIgnoreCase = "a".equalsIgnoreCase(tag.name);
    boolean _not = (!_equalsIgnoreCase);
    if (_not) {
      return null;
    }
    EntityLink entlink = map.get(tag);
    boolean _equals = Objects.equal(entlink, null);
    if (_equals) {
      String href = tag.attributes.get("href");
      boolean _equals_1 = Objects.equal(href, null);
      if (_equals_1) {
        String _get = tag.attributes.get("HREF");
        href = _get;
      }
      boolean _equals_2 = Objects.equal(href, null);
      if (_equals_2) {
        return null;
      }
      boolean _startsWith = href.startsWith("#");
      boolean _not_1 = (!_startsWith);
      if (_not_1) {
        return null;
      }
      String _substring = href.substring(1);
      href = _substring;
      EntityLink _createEntityLink = SpecFactory.eINSTANCE.createEntityLink();
      entlink = _createEntityLink;
      entlink.setEntLabel(href);
      map.put(tag, entlink);
    }
    return entlink;
  }
  
  /**
   * Convert analyzed document into a specification model
   */
  public SpecDocument analyzedDocToSpecDoc(final Annotation document) {
    final SpecDocument specdoc = SpecFactory.eINSTANCE.createSpecDocument();
    HashMap<XMLTag,EntityLink> _hashMap = new HashMap<XMLTag, EntityLink>();
    final HashMap<XMLTag,EntityLink> tag2entlink = _hashMap;
    HashMap<Pair<Integer,Integer>,SpecWord> _hashMap_1 = new HashMap<Pair<Integer, Integer>, SpecWord>();
    final HashMap<Pair<Integer,Integer>,SpecWord> idx2word = _hashMap_1;
    List<CoreMap> _sentences = this.getSentences(document);
    final Procedure2<CoreMap,Integer> _function = new Procedure2<CoreMap,Integer>() {
      public void apply(final CoreMap sentence, final Integer sentIndex) {
        final SpecSentence specSentence = SpecFactory.eINSTANCE.createSpecSentence();
        EList<SpecSentence> _sentences = specdoc.getSentences();
        _sentences.add(specSentence);
        List<CoreLabel> _tokens = ReprotoolLinguisticPipeline.this.getTokens(sentence);
        final Procedure2<CoreLabel,Integer> _function = new Procedure2<CoreLabel,Integer>() {
          public void apply(final CoreLabel token, final Integer tokenIndex) {
            final SpecWord specWord = SpecFactory.eINSTANCE.createSpecWord();
            EList<SpecWord> _words = specSentence.getWords();
            _words.add(specWord);
            Pair<Integer,Integer> _of = Pair.<Integer, Integer>of(sentIndex, tokenIndex);
            idx2word.put(_of, specWord);
            String _wordStr = ReprotoolLinguisticPipeline.this.getWordStr(token);
            specWord.setOriginal(_wordStr);
            String _tag = token.tag();
            specWord.setPosTag(_tag);
            String _lemma = token.lemma();
            specWord.setLemma(_lemma);
            EntityLink _entityLink = null;
            XMLTag _innermostXmlTag = ReprotoolLinguisticPipeline.this.getInnermostXmlTag(token);
            if (_innermostXmlTag!=null) {
              _entityLink=ReprotoolLinguisticPipeline.this.toEntityLink(_innermostXmlTag, tag2entlink);
            }
            final EntityLink entLink = _entityLink;
            boolean _notEquals = (!Objects.equal(entLink, null));
            if (_notEquals) {
              EList<SpecWord> _linkedWords = entLink.getLinkedWords();
              _linkedWords.add(specWord);
              EList<EntityLink> _entityLinks = specSentence.getEntityLinks();
              _entityLinks.add(entLink);
            }
          }
        };
        IterableExtensions.<CoreLabel>forEach(_tokens, _function);
        SemanticGraph _collapsedCCProcessedDependencies = ReprotoolLinguisticPipeline.this.getCollapsedCCProcessedDependencies(sentence);
        IndexedWord _firstRoot = null;
        if (_collapsedCCProcessedDependencies!=null) {
          _firstRoot=_collapsedCCProcessedDependencies.getFirstRoot();
        }
        final IndexedWord root = _firstRoot;
        boolean _notEquals = (!Objects.equal(root, null));
        if (_notEquals) {
          int _index = root.index();
          int _minus = (_index - 1);
          Pair<Integer,Integer> _mappedTo = Pair.<Integer, Integer>of(sentIndex, Integer.valueOf(_minus));
          final SpecWord word = idx2word.get(_mappedTo);
          SpecSentence _sentence = word.getSentence();
          _sentence.setSemanticRootWord(word);
        }
        SemanticGraph _collapsedCCProcessedDependencies_1 = ReprotoolLinguisticPipeline.this.getCollapsedCCProcessedDependencies(sentence);
        Iterable<SemanticGraphEdge> _edgeIterable = null;
        if (_collapsedCCProcessedDependencies_1!=null) {
          _edgeIterable=_collapsedCCProcessedDependencies_1.edgeIterable();
        }
        if (_edgeIterable!=null) {
          final Procedure1<SemanticGraphEdge> _function_1 = new Procedure1<SemanticGraphEdge>() {
            public void apply(final SemanticGraphEdge edge) {
              EList<WordDependency> _typedDependencies = specSentence.getTypedDependencies();
              WordDependency _createWordDependency = SpecFactory.eINSTANCE.createWordDependency();
              final Procedure1<WordDependency> _function = new Procedure1<WordDependency>() {
                public void apply(final WordDependency it) {
                  GrammaticalRelation _relation = edge.getRelation();
                  String _string = _relation.toString();
                  it.setLabel(_string);
                  IndexedWord _governor = edge.getGovernor();
                  int _index = _governor.index();
                  int _minus = (_index - 1);
                  Pair<Integer,Integer> _mappedTo = Pair.<Integer, Integer>of(sentIndex, Integer.valueOf(_minus));
                  SpecWord _get = idx2word.get(_mappedTo);
                  it.setLinkGov(_get);
                  IndexedWord _dependent = edge.getDependent();
                  int _index_1 = _dependent.index();
                  int _minus_1 = (_index_1 - 1);
                  Pair<Integer,Integer> _mappedTo_1 = Pair.<Integer, Integer>of(sentIndex, Integer.valueOf(_minus_1));
                  SpecWord _get_1 = idx2word.get(_mappedTo_1);
                  it.setLinkDep(_get_1);
                }
              };
              WordDependency _doubleArrow = ObjectExtensions.<WordDependency>operator_doubleArrow(_createWordDependency, _function);
              _typedDependencies.add(_doubleArrow);
            }
          };
          IterableExtensions.<SemanticGraphEdge>forEach(_edgeIterable, _function_1);
        }
      }
    };
    IterableExtensions.<CoreMap>forEach(_sentences, _function);
    HashMap<CoreLabel,CoreLabel> _coreferences = this.getCoreferences(document);
    final Procedure2<CoreLabel,CoreLabel> _function_1 = new Procedure2<CoreLabel,CoreLabel>() {
      public void apply(final CoreLabel token1, final CoreLabel token2) {
        int _sentIndex = token1.sentIndex();
        Integer _wordIndex = ReprotoolLinguisticPipeline.this.getWordIndex(token1);
        Pair<Integer,Integer> _mappedTo = Pair.<Integer, Integer>of(Integer.valueOf(_sentIndex), _wordIndex);
        final SpecWord word1 = idx2word.get(_mappedTo);
        int _sentIndex_1 = token2.sentIndex();
        Integer _wordIndex_1 = ReprotoolLinguisticPipeline.this.getWordIndex(token2);
        Pair<Integer,Integer> _mappedTo_1 = Pair.<Integer, Integer>of(Integer.valueOf(_sentIndex_1), _wordIndex_1);
        final SpecWord word2 = idx2word.get(_mappedTo_1);
        word1.setCorefRepMention(word2);
      }
    };
    MapExtensions.<CoreLabel, CoreLabel>forEach(_coreferences, _function_1);
    return specdoc;
  }
}
