package reprotool.dmodel.tools.phases.elicit;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import opennlp.model.IndexHashTable;
import opennlp.model.MaxentModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
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
import org.eclipse.xtext.xbase.lib.StringExtensions;
import reprotool.dmodel.api.FeatureExtractorFactory;
import reprotool.dmodel.api.classifiers.MaxentClassifier;
import reprotool.dmodel.api.samples.ExtractedSamples;
import reprotool.dmodel.api.samples.FeatureEvent;
import reprotool.dmodel.ctxgen.RelationContext;
import reprotool.dmodel.extensions.ReprotoolEcoreExtensions;
import reprotool.dmodel.extract.mwent.RoleInLink;
import reprotool.dmodel.extract.words.WordLinkType;
import reprotool.predict.exectoolapi.IExecutableTool;
import reprotool.predict.logging.ReprotoolLogger;
import reprotool.predict.smloader.SpecModelLoader;
import spec.DomainEntityType;
import spec.DomainModel;
import spec.EntityLink;
import spec.SpecFactory;
import spec.SpecSentence;
import spec.SpecWord;
import spec.Specification;

@Component
@SuppressWarnings("all")
public class ElicitationPhase implements IExecutableTool {
  public String getUsage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("The elicitation phase requires a XMI file containing documents processed");
    _builder.newLine();
    _builder.append("by the linguistic pipeline.");
    _builder.newLine();
    _builder.append("Then it tries to predict the domain model based on the linguistic features");
    _builder.newLine();
    _builder.append("from the prerprocessed document. The result domain model is stored again");
    _builder.newLine();
    _builder.append("within the same XMI file and it can be exported by the ExportDomainModel tool.");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("[specxmi] = XMI file containing the preprocessed specification model");
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
  
  private MaxentModel linktypeModel;
  
  @Reference(target = "(model=linktype)")
  public void set_linktypeModel(final MaxentModel model) {
    this.linktypeModel = model;
  }
  
  private MaxentModel roleinlinkModel;
  
  @Reference(target = "(model=roleInLink)")
  public void set_roleinlinkModel(final MaxentModel model) {
    this.roleinlinkModel = model;
  }
  
  private MaxentModel relclModel;
  
  @Reference(target = "(model=relcl)")
  public void set_relclModel(final MaxentModel model) {
    this.relclModel = model;
  }
  
  private final static String DOMAIN_MODEL_PACKAGE_NAME = "domainmodel";
  
  private List<EClassifier> domainModel;
  
  private Specification specModel;
  
  private String projectDir;
  
  public void execute(final String[] args) {
    int _size = ((List<String>)Conversions.doWrapArray(args)).size();
    boolean _notEquals = (_size != 1);
    if (_notEquals) {
      String _usage = this.getUsage();
      InputOutput.<String>println(_usage);
      return;
    }
    final String specModelFileName = args[0];
    File _file = new File(specModelFileName);
    File _absoluteFile = _file.getAbsoluteFile();
    String _parent = _absoluteFile.getParent();
    this.projectDir = _parent;
    Specification _loadSpecificationModel = this.loader.loadSpecificationModel(specModelFileName);
    this.specModel = _loadSpecificationModel;
    DomainModel _domainModel = this.specModel.getDomainModel();
    boolean _equals = Objects.equal(_domainModel, null);
    if (_equals) {
      DomainModel _createDomainModel = SpecFactory.eINSTANCE.createDomainModel();
      final Procedure1<DomainModel> _function = new Procedure1<DomainModel>() {
        public void apply(final DomainModel it) {
          EPackage _createEPackage = EcoreFactory.eINSTANCE.createEPackage();
          final Procedure1<EPackage> _function = new Procedure1<EPackage>() {
            public void apply(final EPackage it) {
              it.setName(ElicitationPhase.DOMAIN_MODEL_PACKAGE_NAME);
            }
          };
          EPackage _doubleArrow = ObjectExtensions.<EPackage>operator_doubleArrow(_createEPackage, _function);
          it.setModelPackage(_doubleArrow);
        }
      };
      DomainModel _doubleArrow = ObjectExtensions.<DomainModel>operator_doubleArrow(_createDomainModel, _function);
      this.specModel.setDomainModel(_doubleArrow);
    }
    DomainModel _domainModel_1 = this.specModel.getDomainModel();
    EPackage _modelPackage = _domainModel_1.getModelPackage();
    EList<EClassifier> _eClassifiers = _modelPackage.getEClassifiers();
    this.domainModel = _eClassifiers;
    this.predictDomEntCandidates();
    this.predictMultiWordEntities();
    this.deriveNamesForEntityLinks();
    this.convertEntityLinksToEClasses();
    this.fillBacklinksEAnnotations();
    this.mergeEClassesWithSameName();
    this.predictRelations();
    this.removeSpacesFromDomainModel();
    this.saveResults();
    InputOutput.<String>println("done. see logs");
  }
  
  private void saveResults() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(this.projectDir, "");
    _builder.append("/predicted-specification.xmi");
    this.loader.saveSpecificationModel(this.specModel, _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(this.projectDir, "");
    _builder_1.append("/predicted-domainmodel.ecore");
    this.loader.saveDomainModel(this.specModel, _builder_1.toString());
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("Results saved.");
    this.logger.info(_builder_2);
  }
  
  private Iterable<String> getContextFeatureNames(final MaxentModel maxentModel) {
    Set<String> _xblockexpression = null;
    {
      Object[] _dataStructures = maxentModel.getDataStructures();
      Object _get = _dataStructures[1];
      final IndexHashTable<String> contextMap = ((IndexHashTable<String>) _get);
      int _size = contextMap.size();
      IntegerRange _upTo = new IntegerRange(1, _size);
      final Function1<Integer,String> _function = new Function1<Integer,String>() {
        public String apply(final Integer it) {
          return ((String) null);
        }
      };
      Iterable<String> _map = IterableExtensions.<Integer, String>map(_upTo, _function);
      final String[] allContexts = contextMap.toArray(((String[])Conversions.unwrapArray(_map, String.class)));
      final Function1<String,String> _function_1 = new Function1<String,String>() {
        public String apply(final String it) {
          String[] _split = it.split("=");
          String _get = _split[0];
          return _get;
        }
      };
      List<String> _map_1 = ListExtensions.<String, String>map(((List<String>)Conversions.doWrapArray(allContexts)), _function_1);
      List<String> _sort = IterableExtensions.<String>sort(_map_1);
      Set<String> _set = IterableExtensions.<String>toSet(_sort);
      _xblockexpression = (_set);
    }
    return _xblockexpression;
  }
  
  private String getOutcomeFeatureName(final MaxentModel maxentModel) {
    String _xblockexpression = null;
    {
      Object[] _dataStructures = maxentModel.getDataStructures();
      Object _get = _dataStructures[2];
      final String[] allOutcomes = ((String[]) _get);
      String _get_1 = allOutcomes[0];
      String[] _split = _get_1.split("=");
      String _get_2 = _split[0];
      _xblockexpression = (_get_2);
    }
    return _xblockexpression;
  }
  
  private void predictDomEntCandidates() {
    this.logger.info("TASK : Predicting which words represent domain entities");
    Iterable<String> _contextFeatureNames = this.getContextFeatureNames(this.linktypeModel);
    String _outcomeFeatureName = this.getOutcomeFeatureName(this.linktypeModel);
    ExtractedSamples _extractedSamples = new ExtractedSamples(
      this.fexFactory, 
      this.specModel, 
      "words", _contextFeatureNames, _outcomeFeatureName);
    final ExtractedSamples samples = _extractedSamples;
    final MaxentClassifier classifier = MaxentClassifier.createFromModel(this.linktypeModel);
    Iterator<FeatureEvent> _predictIterator = classifier.predictIterator(samples);
    final Procedure1<FeatureEvent> _function = new Procedure1<FeatureEvent>() {
      public void apply(final FeatureEvent event) {
        final String outcome = event.getOutcomeFeatureValue();
        boolean _matched = false;
        if (!_matched) {
          if (Objects.equal(outcome,WordLinkType.OUTCOME_CLASS)) {
            _matched=true;
            Object _attachment = event.getAttachment();
            final SpecWord attachedWord = ((SpecWord) _attachment);
            EObject _eContainer = attachedWord.eContainer();
            final SpecSentence sentence = ((SpecSentence) _eContainer);
            EList<EntityLink> _entityLinks = sentence.getEntityLinks();
            EntityLink _createEntityLink = SpecFactory.eINSTANCE.createEntityLink();
            final Procedure1<EntityLink> _function = new Procedure1<EntityLink>() {
              public void apply(final EntityLink it) {
                EList<SpecWord> _linkedWords = it.getLinkedWords();
                _linkedWords.add(attachedWord);
                it.setEntType(DomainEntityType.CLASS);
              }
            };
            EntityLink _doubleArrow = ObjectExtensions.<EntityLink>operator_doubleArrow(_createEntityLink, _function);
            _entityLinks.add(_doubleArrow);
          }
        }
        if (!_matched) {
          if (Objects.equal(outcome,WordLinkType.OUTCOME_REFERENCE)) {
            _matched=true;
            Object _attachment_1 = event.getAttachment();
            final SpecWord attachedWord_1 = ((SpecWord) _attachment_1);
            EObject _eContainer_1 = attachedWord_1.eContainer();
            final SpecSentence sentence_1 = ((SpecSentence) _eContainer_1);
            EList<EntityLink> _entityLinks_1 = sentence_1.getEntityLinks();
            EntityLink _createEntityLink_1 = SpecFactory.eINSTANCE.createEntityLink();
            final Procedure1<EntityLink> _function_1 = new Procedure1<EntityLink>() {
              public void apply(final EntityLink it) {
                EList<SpecWord> _linkedWords = it.getLinkedWords();
                _linkedWords.add(attachedWord_1);
                it.setEntType(DomainEntityType.REFERENCE);
              }
            };
            EntityLink _doubleArrow_1 = ObjectExtensions.<EntityLink>operator_doubleArrow(_createEntityLink_1, _function_1);
            _entityLinks_1.add(_doubleArrow_1);
          }
        }
      }
    };
    IteratorExtensions.<FeatureEvent>forEach(_predictIterator, _function);
  }
  
  private void predictMultiWordEntities() {
    this.logger.info("TASK : Predicting which words represent entities composed of multiple words");
    Iterable<String> _contextFeatureNames = this.getContextFeatureNames(this.roleinlinkModel);
    String _outcomeFeatureName = this.getOutcomeFeatureName(this.roleinlinkModel);
    ExtractedSamples _extractedSamples = new ExtractedSamples(
      this.fexFactory, 
      this.specModel, 
      "words", _contextFeatureNames, _outcomeFeatureName);
    final ExtractedSamples samples = _extractedSamples;
    final MaxentClassifier classifier = MaxentClassifier.createFromModel(this.roleinlinkModel);
    SpecWord lastWord = null;
    Iterator<FeatureEvent> _predictIterator = classifier.predictIterator(samples);
    Iterable<FeatureEvent> _iterable = IteratorExtensions.<FeatureEvent>toIterable(_predictIterator);
    for (final FeatureEvent event : _iterable) {
      {
        final String outcome = event.getOutcomeFeatureValue();
        Object _attachment = event.getAttachment();
        final SpecWord attachedWord = ((SpecWord) _attachment);
        boolean _matched = false;
        if (!_matched) {
          if (Objects.equal(outcome,RoleInLink.OUTCOME_HEAD)) {
            _matched=true;
            lastWord = attachedWord;
          }
        }
        if (!_matched) {
          if (Objects.equal(outcome,RoleInLink.OUTCOME_CONT)) {
            _matched=true;
            this.mergeFrom(lastWord, attachedWord);
            lastWord = attachedWord;
          }
        }
      }
    }
  }
  
  private void mergeFrom(final SpecWord wordToPreserve, final SpecWord wordToMerge) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("merging ");
    _builder.append(wordToPreserve, "");
    _builder.append(" ");
    {
      EntityLink _relatedEntityLink = wordToPreserve.getRelatedEntityLink();
      boolean _equals = Objects.equal(_relatedEntityLink, null);
      if (_equals) {
        _builder.append("[NOLINK]");
      }
    }
    _builder.append(" <- ");
    _builder.append(wordToMerge, "");
    _builder.append(" ");
    {
      EntityLink _relatedEntityLink_1 = wordToMerge.getRelatedEntityLink();
      boolean _equals_1 = Objects.equal(_relatedEntityLink_1, null);
      if (_equals_1) {
        _builder.append("[NOLINK]");
      }
    }
    this.logger.debug(_builder);
    EntityLink _relatedEntityLink_2 = wordToPreserve.getRelatedEntityLink();
    boolean _equals_2 = Objects.equal(_relatedEntityLink_2, null);
    if (_equals_2) {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("We predicted merging words ");
      _builder_1.append(wordToPreserve, "");
      _builder_1.append(" <- ");
      _builder_1.append(wordToMerge, "");
      _builder_1.append(", however, the word to be preserved is not related to EntityLink");
      this.logger.warn(_builder_1);
      return;
    }
    final EntityLink linkToDelete = wordToMerge.getRelatedEntityLink();
    boolean _notEquals = (!Objects.equal(linkToDelete, null));
    if (_notEquals) {
      EntityLink _relatedEntityLink_3 = wordToPreserve.getRelatedEntityLink();
      EList<SpecWord> _linkedWords = _relatedEntityLink_3.getLinkedWords();
      EList<SpecWord> _linkedWords_1 = linkToDelete.getLinkedWords();
      Iterables.<SpecWord>addAll(_linkedWords, _linkedWords_1);
      EObject _eContainer = wordToMerge.eContainer();
      final SpecSentence sentence = ((SpecSentence) _eContainer);
      EList<EntityLink> _entityLinks = sentence.getEntityLinks();
      _entityLinks.remove(linkToDelete);
    } else {
      EntityLink _relatedEntityLink_4 = wordToPreserve.getRelatedEntityLink();
      EList<SpecWord> _linkedWords_2 = _relatedEntityLink_4.getLinkedWords();
      _linkedWords_2.add(wordToMerge);
    }
  }
  
  private void deriveNamesForEntityLinks() {
    this.logger.info("TASK : Deriving names for entity links based on the words they contain");
    Iterable<EntityLink> _allEntityLinks = ReprotoolEcoreExtensions.allEntityLinks(this.specModel);
    final Function1<EntityLink,Boolean> _function = new Function1<EntityLink,Boolean>() {
      public Boolean apply(final EntityLink it) {
        boolean _or = false;
        String _entLabel = it.getEntLabel();
        boolean _equals = Objects.equal(_entLabel, null);
        if (_equals) {
          _or = true;
        } else {
          String _entLabel_1 = it.getEntLabel();
          boolean _isEmpty = _entLabel_1.isEmpty();
          _or = (_equals || _isEmpty);
        }
        return Boolean.valueOf(_or);
      }
    };
    Iterable<EntityLink> _filter = IterableExtensions.<EntityLink>filter(_allEntityLinks, _function);
    final Procedure1<EntityLink> _function_1 = new Procedure1<EntityLink>() {
      public void apply(final EntityLink it) {
        EList<SpecWord> _linkedWords = it.getLinkedWords();
        final Function1<SpecWord,String> _function = new Function1<SpecWord,String>() {
          public String apply(final SpecWord it) {
            SpecWord _corefRepOrSelf = it.getCorefRepOrSelf();
            String _lemma = _corefRepOrSelf.getLemma();
            String _lowerCase = _lemma.toLowerCase();
            String _firstUpper = StringExtensions.toFirstUpper(_lowerCase);
            return _firstUpper;
          }
        };
        List<String> _map = ListExtensions.<SpecWord, String>map(_linkedWords, _function);
        String _join = IterableExtensions.join(_map, " ");
        it.setEntLabel(_join);
      }
    };
    IterableExtensions.<EntityLink>forEach(_filter, _function_1);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("All EntityLinks found in the specification: ");
    Iterable<EntityLink> _allEntityLinks_1 = ReprotoolEcoreExtensions.allEntityLinks(this.specModel);
    final Function1<EntityLink,String> _function_2 = new Function1<EntityLink,String>() {
      public String apply(final EntityLink it) {
        String _entLabel = it.getEntLabel();
        return _entLabel;
      }
    };
    Iterable<String> _map = IterableExtensions.<EntityLink, String>map(_allEntityLinks_1, _function_2);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    this.logger.debug(_builder);
  }
  
  private void convertEntityLinksToEClasses() {
    this.logger.info("TASK : Converting entity links to EClasses in the domain model");
    Iterable<EntityLink> _allEntityLinks = ReprotoolEcoreExtensions.allEntityLinks(this.specModel);
    final Function1<EntityLink,Boolean> _function = new Function1<EntityLink,Boolean>() {
      public Boolean apply(final EntityLink it) {
        DomainEntityType _entType = it.getEntType();
        boolean _equals = Objects.equal(_entType, DomainEntityType.CLASS);
        return Boolean.valueOf(_equals);
      }
    };
    Iterable<EntityLink> _filter = IterableExtensions.<EntityLink>filter(_allEntityLinks, _function);
    final Procedure1<EntityLink> _function_1 = new Procedure1<EntityLink>() {
      public void apply(final EntityLink entlink) {
        EClass _createEClass = EcoreFactory.eINSTANCE.createEClass();
        final Procedure1<EClass> _function = new Procedure1<EClass>() {
          public void apply(final EClass it) {
            String _entLabel = entlink.getEntLabel();
            it.setName(_entLabel);
          }
        };
        final EClass newEClass = ObjectExtensions.<EClass>operator_doubleArrow(_createEClass, _function);
        entlink.setLinkedEntity(newEClass);
        ElicitationPhase.this.domainModel.add(newEClass);
      }
    };
    IterableExtensions.<EntityLink>forEach(_filter, _function_1);
  }
  
  private void fillBacklinksEAnnotations() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Filling backlinks from EClasses (in the domain model) to EntityLinks (in the document)");
    this.logger.info(_builder);
    Iterable<EntityLink> _allEntityLinks = ReprotoolEcoreExtensions.allEntityLinks(this.specModel);
    final Function1<EntityLink,Boolean> _function = new Function1<EntityLink,Boolean>() {
      public Boolean apply(final EntityLink it) {
        ENamedElement _linkedEntity = it.getLinkedEntity();
        boolean _notEquals = (!Objects.equal(_linkedEntity, null));
        return Boolean.valueOf(_notEquals);
      }
    };
    Iterable<EntityLink> _filter = IterableExtensions.<EntityLink>filter(_allEntityLinks, _function);
    final Procedure1<EntityLink> _function_1 = new Procedure1<EntityLink>() {
      public void apply(final EntityLink it) {
        ENamedElement _linkedEntity = it.getLinkedEntity();
        EAnnotation _eAnnotationOrCreate = ReprotoolEcoreExtensions.getEAnnotationOrCreate(_linkedEntity, "backlinks");
        EList<EObject> _references = _eAnnotationOrCreate.getReferences();
        _references.add(it);
      }
    };
    IterableExtensions.<EntityLink>forEach(_filter, _function_1);
  }
  
  /**
   * Requires: "backlinks" EAnnotation for each EClass
   */
  private void mergeEClassesWithSameName() {
    try {
      this.logger.info("TASK : Merge EClasses with the same name");
      Iterable<EClass> _filter = Iterables.<EClass>filter(this.domainModel, EClass.class);
      final Function1<EClass,Boolean> _function = new Function1<EClass,Boolean>() {
        public Boolean apply(final EClass it) {
          EAnnotation _eAnnotation = it.getEAnnotation("backlinks");
          boolean _notEquals = (!Objects.equal(_eAnnotation, null));
          return Boolean.valueOf(_notEquals);
        }
      };
      boolean _exists = IterableExtensions.<EClass>exists(_filter, _function);
      boolean _not = (!_exists);
      if (_not) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("There are no \"backlinks\" EAnnotations in the domain model attached to EClasses");
        Exception _exception = new Exception(_builder.toString());
        throw _exception;
      }
      HashMap<String,EClass> _hashMap = new HashMap<String, EClass>();
      final HashMap<String,EClass> mapByName = _hashMap;
      Iterable<EClass> _filter_1 = Iterables.<EClass>filter(this.domainModel, EClass.class);
      List<EClass> _list = IterableExtensions.<EClass>toList(_filter_1);
      final Procedure1<EClass> _function_1 = new Procedure1<EClass>() {
        public void apply(final EClass it) {
          String _name = it.getName();
          boolean _containsKey = mapByName.containsKey(_name);
          if (_containsKey) {
            String _name_1 = it.getName();
            final EClass preservedEntity = mapByName.get(_name_1);
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("Merging EClasses ");
            String _name_2 = preservedEntity.getName();
            _builder.append(_name_2, "");
            _builder.append(" <- ");
            String _name_3 = it.getName();
            _builder.append(_name_3, "");
            ElicitationPhase.this.logger.debug(_builder);
            final Iterable<EntityLink> backlinksToUpdate = ReprotoolEcoreExtensions.getBacklinks(it);
            final Procedure1<EntityLink> _function = new Procedure1<EntityLink>() {
              public void apply(final EntityLink it) {
                it.setLinkedEntity(preservedEntity);
              }
            };
            IterableExtensions.<EntityLink>forEach(backlinksToUpdate, _function);
            EAnnotation _eAnnotation = preservedEntity.getEAnnotation("backlinks");
            EList<EObject> _references = _eAnnotation.getReferences();
            Iterables.<EObject>addAll(_references, backlinksToUpdate);
            ElicitationPhase.this.domainModel.remove(it);
          } else {
            String _name_4 = it.getName();
            mapByName.put(_name_4, it);
          }
        }
      };
      IterableExtensions.<EClass>forEach(_list, _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void predictRelations() {
    try {
      this.logger.info("TASK : Predict relations");
      Iterable<EClass> _filter = Iterables.<EClass>filter(this.domainModel, EClass.class);
      final Function1<EClass,Boolean> _function = new Function1<EClass,Boolean>() {
        public Boolean apply(final EClass it) {
          EAnnotation _eAnnotation = it.getEAnnotation("backlinks");
          boolean _notEquals = (!Objects.equal(_eAnnotation, null));
          return Boolean.valueOf(_notEquals);
        }
      };
      boolean _exists = IterableExtensions.<EClass>exists(_filter, _function);
      boolean _not = (!_exists);
      if (_not) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("There are no \"backlinks\" EAnnotations in the domain model attached to EClasses");
        Exception _exception = new Exception(_builder.toString());
        throw _exception;
      }
      Iterable<String> _contextFeatureNames = this.getContextFeatureNames(this.relclModel);
      String _outcomeFeatureName = this.getOutcomeFeatureName(this.relclModel);
      ExtractedSamples _extractedSamples = new ExtractedSamples(this.fexFactory, this.specModel, "relations", _contextFeatureNames, _outcomeFeatureName);
      final ExtractedSamples samples = _extractedSamples;
      final MaxentClassifier classifier = MaxentClassifier.createFromModel(this.relclModel);
      Iterator<FeatureEvent> _predictIterator = classifier.predictIterator(samples);
      final Procedure1<FeatureEvent> _function_1 = new Procedure1<FeatureEvent>() {
        public void apply(final FeatureEvent event) {
          final String outcome = event.getOutcomeFeatureValue();
          Object _attachment = event.getAttachment();
          final RelationContext attachedRelationContext = ((RelationContext) _attachment);
          boolean _matched = false;
          if (!_matched) {
            if (Objects.equal(outcome,"true")) {
              _matched=true;
              final EClass src = attachedRelationContext.getSrcEClass();
              final EClass dest = attachedRelationContext.getDestEClass();
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("Predicted relation: ");
              String _name = src.getName();
              _builder.append(_name, "");
              _builder.append(" -> ");
              String _name_1 = dest.getName();
              _builder.append(_name_1, "");
              ElicitationPhase.this.logger.debug(_builder);
              EList<EStructuralFeature> _eStructuralFeatures = src.getEStructuralFeatures();
              EReference _createEReference = EcoreFactory.eINSTANCE.createEReference();
              final Procedure1<EReference> _function = new Procedure1<EReference>() {
                public void apply(final EReference it) {
                  SpecSentence _sentence = attachedRelationContext.getSentence();
                  SpecWord _semanticRootWord = _sentence.getSemanticRootWord();
                  String _lemma = _semanticRootWord.getLemma();
                  String _lowerCase = _lemma.toLowerCase();
                  it.setName(_lowerCase);
                  it.setEType(dest);
                }
              };
              EReference _doubleArrow = ObjectExtensions.<EReference>operator_doubleArrow(_createEReference, _function);
              _eStructuralFeatures.add(_doubleArrow);
            }
          }
        }
      };
      IteratorExtensions.<FeatureEvent>forEach(_predictIterator, _function_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void removeSpacesFromDomainModel() {
    this.logger.info("TASK : Removing spaces from class names in the domain model");
    Iterable<EClass> _filter = Iterables.<EClass>filter(this.domainModel, EClass.class);
    final Procedure1<EClass> _function = new Procedure1<EClass>() {
      public void apply(final EClass it) {
        String _name = it.getName();
        String _replaceAll = _name.replaceAll(" ", "");
        it.setName(_replaceAll);
      }
    };
    IterableExtensions.<EClass>forEach(_filter, _function);
  }
}
