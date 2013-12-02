package reprotool.predict.smloader;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import reprotool.dmodel.extensions.ReprotoolEcoreExtensions;
import reprotool.dmodel.extensions.StatisticalExtensions;
import reprotool.predict.logging.ReprotoolLogger;
import spec.DomainEntityType;
import spec.DomainModel;
import spec.EntityLink;
import spec.SpecDocument;
import spec.SpecFactory;
import spec.SpecPackage;
import spec.SpecSentence;
import spec.Specification;

@Component(provide = SpecModelLoader.class)
@SuppressWarnings("all")
public class SpecModelLoader {
  @Extension
  private ReprotoolLogger logger;
  
  @Reference
  public void setLogger(final ReprotoolLogger logger) {
    this.logger = logger;
  }
  
  public Specification loadSpecificationModel(final String fileName) {
    try {
      SpecPackage.eINSTANCE.getEPackage();
      XMIResourceImpl _xMIResourceImpl = new XMIResourceImpl();
      final XMIResourceImpl resource = _xMIResourceImpl;
      try {
        FileInputStream _fileInputStream = new FileInputStream(fileName);
        resource.load(_fileInputStream, null);
      } catch (final Throwable _t) {
        if (_t instanceof FileNotFoundException) {
          final FileNotFoundException e = (FileNotFoundException)_t;
          return null;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      EList<EObject> _contents = resource.getContents();
      EObject _head = IterableExtensions.<EObject>head(_contents);
      return ((Specification) _head);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void saveSpecificationModel(final Specification specModel, final String fileName) {
    XMIResourceImpl _xMIResourceImpl = new XMIResourceImpl();
    final Procedure1<XMIResourceImpl> _function = new Procedure1<XMIResourceImpl>() {
      public void apply(final XMIResourceImpl it) {
        try {
          EList<EObject> _contents = it.getContents();
          _contents.add(specModel);
          FileOutputStream _fileOutputStream = new FileOutputStream(fileName);
          it.save(_fileOutputStream, null);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    ObjectExtensions.<XMIResourceImpl>operator_doubleArrow(_xMIResourceImpl, _function);
  }
  
  public void saveDomainModel(final Specification specModel, final String outFileName) {
    this.removeEAnnotationsFromDomainModel(specModel);
    XMIResourceImpl _xMIResourceImpl = new XMIResourceImpl();
    final Procedure1<XMIResourceImpl> _function = new Procedure1<XMIResourceImpl>() {
      public void apply(final XMIResourceImpl it) {
        try {
          EList<EObject> _contents = it.getContents();
          DomainModel _domainModel = specModel.getDomainModel();
          EPackage _modelPackage = _domainModel.getModelPackage();
          _contents.add(_modelPackage);
          FileOutputStream _fileOutputStream = new FileOutputStream(outFileName);
          it.save(_fileOutputStream, null);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    ObjectExtensions.<XMIResourceImpl>operator_doubleArrow(_xMIResourceImpl, _function);
  }
  
  /**
   * Loads a domain model to the specification from XMI file.
   */
  public DomainModel loadDomainModel(final String fileName) {
    DomainModel _createDomainModel = SpecFactory.eINSTANCE.createDomainModel();
    final Procedure1<DomainModel> _function = new Procedure1<DomainModel>() {
      public void apply(final DomainModel it) {
        try {
          it.loadFromXmiFile(fileName);
          it.rebuildNameIndex();
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    };
    DomainModel _doubleArrow = ObjectExtensions.<DomainModel>operator_doubleArrow(_createDomainModel, _function);
    return _doubleArrow;
  }
  
  /**
   * Rebuilds name index in the domain model and resolves entity links
   * in all documents within the specification.
   */
  public void resolveEntityLinks(final Specification specModel) {
    final DomainModel domainModel = specModel.getDomainModel();
    domainModel.rebuildNameIndex();
    EList<SpecDocument> _documents = specModel.getDocuments();
    final Procedure1<SpecDocument> _function = new Procedure1<SpecDocument>() {
      public void apply(final SpecDocument it) {
        EList<SpecSentence> _sentences = it.getSentences();
        final Procedure1<SpecSentence> _function = new Procedure1<SpecSentence>() {
          public void apply(final SpecSentence it) {
            SpecModelLoader.this.resolveEntityLinks(it, domainModel);
          }
        };
        IterableExtensions.<SpecSentence>forEach(_sentences, _function);
      }
    };
    IterableExtensions.<SpecDocument>forEach(_documents, _function);
  }
  
  private void removeEAnnotationsFromDomainModel(final Specification specModel) {
    DomainModel _domainModel = specModel.getDomainModel();
    EPackage _modelPackage = _domainModel.getModelPackage();
    TreeIterator<EObject> _eAllContents = _modelPackage.eAllContents();
    Iterator<ENamedElement> _filter = Iterators.<ENamedElement>filter(_eAllContents, ENamedElement.class);
    List<ENamedElement> _list = IteratorExtensions.<ENamedElement>toList(_filter);
    final Procedure1<ENamedElement> _function = new Procedure1<ENamedElement>() {
      public void apply(final ENamedElement it) {
        EList<EAnnotation> _eAnnotations = it.getEAnnotations();
        boolean _isEmpty = _eAnnotations.isEmpty();
        boolean _not = (!_isEmpty);
        if (_not) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Removed ");
          EList<EAnnotation> _eAnnotations_1 = it.getEAnnotations();
          int _size = _eAnnotations_1.size();
          _builder.append(_size, "");
          _builder.append(" EAnnotation(s) containing ");
          EList<EAnnotation> _eAnnotations_2 = it.getEAnnotations();
          final Function1<EAnnotation,Integer> _function = new Function1<EAnnotation,Integer>() {
            public Integer apply(final EAnnotation it) {
              EList<EObject> _references = it.getReferences();
              int _size = _references.size();
              return Integer.valueOf(_size);
            }
          };
          List<Integer> _map = ListExtensions.<EAnnotation, Integer>map(_eAnnotations_2, _function);
          int _sum = StatisticalExtensions.sum(_map, 0);
          _builder.append(_sum, "");
          _builder.append(" reference(s) from \"");
          String _name = it.getName();
          _builder.append(_name, "");
          _builder.append("\" ");
          SpecModelLoader.this.logger.debug(_builder);
          EList<EAnnotation> _eAnnotations_3 = it.getEAnnotations();
          _eAnnotations_3.clear();
        }
      }
    };
    IterableExtensions.<ENamedElement>forEach(_list, _function);
  }
  
  private void resolveEntityLinks(final SpecSentence sentence, final DomainModel domainModel) {
    EList<EntityLink> _entityLinks = sentence.getEntityLinks();
    final Procedure1<EntityLink> _function = new Procedure1<EntityLink>() {
      public void apply(final EntityLink it) {
        SpecModelLoader.this.resolveEntityLinks(it, domainModel);
      }
    };
    IterableExtensions.<EntityLink>forEach(_entityLinks, _function);
  }
  
  private void resolveEntityLinks(final EntityLink entityLink, final DomainModel domainModel) {
    final Procedure1<EntityLink> _function = new Procedure1<EntityLink>() {
      public void apply(final EntityLink it) {
        String _entLabel = it.getEntLabel();
        ENamedElement _namedElement = domainModel.getNamedElement(_entLabel);
        it.setLinkedEntity(_namedElement);
        ENamedElement _linkedEntity = it.getLinkedEntity();
        boolean _equals = Objects.equal(_linkedEntity, null);
        if (_equals) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("WARNING: could not find domain entity ");
          String _entLabel_1 = it.getEntLabel();
          _builder.append(_entLabel_1, "");
          SpecModelLoader.this.logger.warn(_builder);
        } else {
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("Resolving entityLink: ");
          String _entLabel_2 = it.getEntLabel();
          _builder_1.append(_entLabel_2, "");
          _builder_1.append(":");
          DomainEntityType _entType = it.getEntType();
          _builder_1.append(_entType, "");
          SpecModelLoader.this.logger.debug(_builder_1);
          DomainEntityType _switchResult = null;
          ENamedElement _linkedEntity_1 = it.getLinkedEntity();
          final ENamedElement getLinkedEntity = _linkedEntity_1;
          boolean _matched = false;
          if (!_matched) {
            if (getLinkedEntity instanceof EClass) {
              final EClass _eClass = (EClass)getLinkedEntity;
              _matched=true;
              _switchResult = DomainEntityType.CLASS;
            }
          }
          if (!_matched) {
            if (getLinkedEntity instanceof EReference) {
              final EReference _eReference = (EReference)getLinkedEntity;
              _matched=true;
              _switchResult = DomainEntityType.REFERENCE;
            }
          }
          if (!_matched) {
            if (getLinkedEntity instanceof EAttribute) {
              final EAttribute _eAttribute = (EAttribute)getLinkedEntity;
              _matched=true;
              _switchResult = DomainEntityType.ATTRIBUTE;
            }
          }
          if (!_matched) {
            if (getLinkedEntity instanceof EOperation) {
              final EOperation _eOperation = (EOperation)getLinkedEntity;
              _matched=true;
              _switchResult = DomainEntityType.OPERATION;
            }
          }
          if (!_matched) {
            _switchResult = null;
          }
          it.setEntType(_switchResult);
          ENamedElement _linkedEntity_2 = it.getLinkedEntity();
          EAnnotation eannot = ReprotoolEcoreExtensions.getEAnnotationOrCreate(_linkedEntity_2, "backlinks");
          EList<EObject> _references = eannot.getReferences();
          _references.add(entityLink);
        }
      }
    };
    ObjectExtensions.<EntityLink>operator_doubleArrow(entityLink, _function);
  }
}
