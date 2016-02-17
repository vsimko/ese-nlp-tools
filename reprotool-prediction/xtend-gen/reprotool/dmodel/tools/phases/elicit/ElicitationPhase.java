package reprotool.dmodel.tools.phases.elicit;

import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import reprotool.dmodel.api.FeatureExtractorFactory;
import reprotool.predict.exectoolapi.IExecutableTool;
import reprotool.predict.logging.ReprotoolLogger;
import reprotool.predict.mloaders.SpecModelLoader;

/* @Component
 */@SuppressWarnings("all")
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
  
  /* @Reference
   */public void setLogger(final ReprotoolLogger logger) {
    this.logger = logger;
  }
  
  private SpecModelLoader loader;
  
  /* @Reference
   */public void setLoader(final SpecModelLoader loader) {
    this.loader = loader;
  }
  
  private FeatureExtractorFactory fexFactory;
  
  /* @Reference
   */public void setFexFactory(final FeatureExtractorFactory factory) {
    this.fexFactory = factory;
  }
  
  private /* MaxentModel */Object linktypeModel;
  
  /* @Reference()
   */public void set_linktypeModel(final /* MaxentModel */Object model) {
    this.linktypeModel = model;
  }
  
  private /* MaxentModel */Object roleinlinkModel;
  
  /* @Reference()
   */public void set_roleinlinkModel(final /* MaxentModel */Object model) {
    this.roleinlinkModel = model;
  }
  
  private /* MaxentModel */Object relclModel;
  
  /* @Reference()
   */public void set_relclModel(final /* MaxentModel */Object model) {
    this.relclModel = model;
  }
  
  private final static String DOMAIN_MODEL_PACKAGE_NAME = "domainmodel";
  
  private /* List<EClassifier> */Object domainModel;
  
  private /* Specification */Object specModel;
  
  private String projectDir;
  
  public void execute(final String[] args) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field SpecFactory is undefined for the type ElicitationPhase"
      + "\nThe method setModelPackage is undefined for the type ElicitationPhase"
      + "\nThe method or field EcoreFactory is undefined for the type ElicitationPhase"
      + "\nThe method setName is undefined for the type ElicitationPhase"
      + "\ndomainModel cannot be resolved"
      + "\n== cannot be resolved"
      + "\nsetDomainModel cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateDomainModel cannot be resolved"
      + "\n=> cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateEPackage cannot be resolved"
      + "\n=> cannot be resolved"
      + "\ndomainModel cannot be resolved"
      + "\nmodelPackage cannot be resolved"
      + "\nEClassifiers cannot be resolved");
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
  
  private Iterable<String> getContextFeatureNames(final /* MaxentModel */Object maxentModel) {
    throw new Error("Unresolved compilation problems:"
      + "\nIndexHashTable cannot be resolved to a type."
      + "\nThe method split is undefined for the type ElicitationPhase"
      + "\ndataStructures cannot be resolved"
      + "\nget cannot be resolved"
      + "\ntoArray cannot be resolved"
      + "\nsize cannot be resolved"
      + "\nmap cannot be resolved"
      + "\nget cannot be resolved"
      + "\nsort cannot be resolved"
      + "\ntoSet cannot be resolved");
  }
  
  private String getOutcomeFeatureName(final /* MaxentModel */Object maxentModel) {
    throw new Error("Unresolved compilation problems:"
      + "\ndataStructures cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  private void predictDomEntCandidates() {
    throw new Error("Unresolved compilation problems:"
      + "\nSpecWord cannot be resolved to a type."
      + "\nSpecSentence cannot be resolved to a type."
      + "\nThe method createFromModel is undefined for the type ElicitationPhase"
      + "\nThe method outcomeFeatureValue is undefined for the type ElicitationPhase"
      + "\nThe method attachment is undefined for the type ElicitationPhase"
      + "\nThe method or field SpecFactory is undefined for the type ElicitationPhase"
      + "\nThe method or field linkedWords is undefined for the type ElicitationPhase"
      + "\nThe method or field entType is undefined for the type ElicitationPhase"
      + "\nThe method or field DomainEntityType is undefined for the type ElicitationPhase"
      + "\nThe method or field SpecFactory is undefined for the type ElicitationPhase"
      + "\nThe method or field linkedWords is undefined for the type ElicitationPhase"
      + "\nThe method or field entType is undefined for the type ElicitationPhase"
      + "\nThe method or field DomainEntityType is undefined for the type ElicitationPhase"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\ngetContextFeatureNames cannot be resolved"
      + "\ngetOutcomeFeatureName cannot be resolved"
      + "\npredictIterator cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\neContainer cannot be resolved"
      + "\n== cannot be resolved"
      + "\n|| cannot be resolved"
      + "\n== cannot be resolved"
      + "\nentityLinks cannot be resolved"
      + "\n+= cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateEntityLink cannot be resolved"
      + "\n=> cannot be resolved"
      + "\n+= cannot be resolved"
      + "\nCLASS cannot be resolved"
      + "\nentityLinks cannot be resolved"
      + "\n+= cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateEntityLink cannot be resolved"
      + "\n=> cannot be resolved"
      + "\n+= cannot be resolved"
      + "\nREFERENCE cannot be resolved");
  }
  
  private void predictMultiWordEntities() {
    throw new Error("Unresolved compilation problems:"
      + "\nSpecWord cannot be resolved to a type."
      + "\nSpecWord cannot be resolved to a type."
      + "\nThe method createFromModel is undefined for the type ElicitationPhase"
      + "\ngetContextFeatureNames cannot be resolved"
      + "\ngetOutcomeFeatureName cannot be resolved"
      + "\npredictIterator cannot be resolved"
      + "\ntoIterable cannot be resolved"
      + "\noutcomeFeatureValue cannot be resolved"
      + "\nattachment cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nmergeFrom cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nmergeFrom cannot be resolved");
  }
  
  private void mergeFrom(final /* SpecWord */Object wordToPreserve, final /* SpecWord */Object wordToMerge) {
    throw new Error("Unresolved compilation problems:"
      + "\nrelatedEntityLink cannot be resolved"
      + "\n== cannot be resolved"
      + "\nrelatedEntityLink cannot be resolved"
      + "\n== cannot be resolved"
      + "\nrelatedEntityLink cannot be resolved"
      + "\n== cannot be resolved"
      + "\nrelatedEntityLink cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nrelatedEntityLink cannot be resolved"
      + "\nlinkedWords cannot be resolved"
      + "\n+= cannot be resolved"
      + "\nlinkedWords cannot be resolved"
      + "\nsentence cannot be resolved"
      + "\nentityLinks cannot be resolved"
      + "\nremove cannot be resolved"
      + "\nrelatedEntityLink cannot be resolved"
      + "\nlinkedWords cannot be resolved"
      + "\n+= cannot be resolved");
  }
  
  private void deriveNamesForEntityLinks() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field entLabel is undefined for the type ElicitationPhase"
      + "\nThe method or field entLabel is undefined for the type ElicitationPhase"
      + "\nThe method or field posTag is undefined for the type ElicitationPhase"
      + "\nThe method or field posTag is undefined for the type ElicitationPhase"
      + "\nInvalid number of arguments. The method getCorefRepOrSelf(SpecWord) is not applicable without arguments"
      + "\nallEntityLinks cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\n== cannot be resolved"
      + "\n|| cannot be resolved"
      + "\nempty cannot be resolved"
      + "\nlinkedWords cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\nmatches cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nsemanticChildren cannot be resolved"
      + "\ntoList cannot be resolved"
      + "\n+= cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\nmatches cannot be resolved"
      + "\nentLabel cannot be resolved"
      + "\nmap cannot be resolved"
      + "\nlemma cannot be resolved"
      + "\ntoLowerCase cannot be resolved"
      + "\ntoFirstUpper cannot be resolved"
      + "\ntoSet cannot be resolved"
      + "\njoin cannot be resolved");
  }
  
  private void removeEntityLinksWithoutLabel() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field entLabel is undefined for the type ElicitationPhase"
      + "\nThe method or field entLabel is undefined for the type ElicitationPhase"
      + "\nThe method or field linkedWords is undefined for the type ElicitationPhase"
      + "\nThe method setLinkedEntity is undefined for the type ElicitationPhase"
      + "\nThe method or field sentence is undefined for the type ElicitationPhase"
      + "\nallEntityLinks cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\n== cannot be resolved"
      + "\n|| cannot be resolved"
      + "\nempty cannot be resolved"
      + "\ntoList cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\nclear cannot be resolved"
      + "\nentityLinks cannot be resolved"
      + "\nremove cannot be resolved"
      + "\nsize cannot be resolved");
  }
  
  private void convertEntityLinksToEClasses() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field entType is undefined for the type ElicitationPhase"
      + "\nThe method or field DomainEntityType is undefined for the type ElicitationPhase"
      + "\nThe method or field EcoreFactory is undefined for the type ElicitationPhase"
      + "\nThe method setName is undefined for the type ElicitationPhase"
      + "\nThe method entLabel is undefined for the type ElicitationPhase"
      + "\nThe method or field linkedEntity is undefined for the type ElicitationPhase"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nallEntityLinks cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\n== cannot be resolved"
      + "\nCLASS cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateEClass cannot be resolved"
      + "\n=> cannot be resolved");
  }
  
  private void fillBacklinksEAnnotations() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field linkedEntity is undefined for the type ElicitationPhase"
      + "\nThe method or field linkedEntity is undefined for the type ElicitationPhase"
      + "\nallEntityLinks cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\ngetEAnnotationOrCreate cannot be resolved"
      + "\nreferences cannot be resolved"
      + "\n+= cannot be resolved");
  }
  
  /**
   * Requires: "backlinks" EAnnotation for each EClass
   */
  private void mergeEClassesWithSameName() {
    throw new Error("Unresolved compilation problems:"
      + "\nEClass cannot be resolved to a type."
      + "\nThe method or field EClass is undefined for the type ElicitationPhase"
      + "\nThe method getEAnnotation is undefined for the type ElicitationPhase"
      + "\nThe method or field EClass is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\nThe method backlinks is undefined for the type ElicitationPhase"
      + "\nThe method or field linkedEntity is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\n!= cannot be resolved"
      + "\nname cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\ngetEAnnotation cannot be resolved"
      + "\nreferences cannot be resolved"
      + "\n+= cannot be resolved");
  }
  
  private void predictRelations() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field EClass is undefined for the type ElicitationPhase"
      + "\nThe method getEAnnotation is undefined for the type ElicitationPhase"
      + "\nThe method createFromModel is undefined for the type ElicitationPhase"
      + "\nThe method outcomeFeatureValue is undefined for the type ElicitationPhase"
      + "\nThe method attachment is undefined for the type ElicitationPhase"
      + "\nThe method or field EcoreFactory is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\nThe method setEType is undefined for the type ElicitationPhase"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\n!= cannot be resolved"
      + "\ncontextFeatureNames cannot be resolved"
      + "\ngetOutcomeFeatureName cannot be resolved"
      + "\npredictIterator cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\nname cannot be resolved"
      + "\nname cannot be resolved"
      + "\nEStructuralFeatures cannot be resolved"
      + "\n+= cannot be resolved"
      + "\neINSTANCE cannot be resolved"
      + "\ncreateEReference cannot be resolved"
      + "\n=> cannot be resolved"
      + "\nsemanticRootWord cannot be resolved"
      + "\nlemma cannot be resolved"
      + "\ntoLowerCase cannot be resolved");
  }
  
  private void removeSpacesFromDomainModel() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field EClass is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\nThe method or field name is undefined for the type ElicitationPhase"
      + "\n!= cannot be resolved"
      + "\nreplaceAll cannot be resolved");
  }
}
