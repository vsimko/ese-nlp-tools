package reprotool.dmodel.tools.phases.preproc;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import edu.stanford.nlp.pipeline.Annotation;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import reprotool.dmodel.nlp.pipeline.ReprotoolLinguisticPipeline;
import reprotool.predict.exectoolapi.IExecutableTool;
import reprotool.predict.logging.ReprotoolLogger;
import reprotool.predict.mloaders.SpecModelLoader;
import spec.SpecDocument;
import spec.Specification;

@Component
@SuppressWarnings("all")
public class LoadTextDocument implements IExecutableTool {
  public String getUsage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("This tool performs a thorough linguistic analysis on the selected document and");
    _builder.newLine();
    _builder.append("included all the generated linguistic artefacts into an existing specification model.");
    _builder.newLine();
    _builder.append("You can load multiple documents into a single specification model.");
    _builder.newLine();
    _builder.append("The linguistic analysis uses the Stanford CoreNLP framework with custom modifications");
    _builder.newLine();
    _builder.append("to the pipeline. The input document can be a plain text file or an HTML document.");
    _builder.newLine();
    _builder.append("Best results can be achieved with the HTML because we use XML tags as evidence for");
    _builder.newLine();
    _builder.append("splitting the sentences.");
    _builder.newLine();
    _builder.append("The document may also contain annotations (links from text to the domain model) that");
    _builder.newLine();
    _builder.append("are encoded as hypertext references: <a href=\"#DomainEntityName\">some text</a>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("[specmodel] = XMI file containing an existing specification model");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("[document]  = A file containing the text annotated with links to the domain model (HTML/XHTML/XML/TXT)");
    _builder.newLine();
    return _builder.toString();
  }
  
  private ReprotoolLinguisticPipeline pipeline;
  
  @Reference
  public void setLinguisticPipeline(final ReprotoolLinguisticPipeline pipeline) {
    this.pipeline = pipeline;
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
  
  public void execute(final String[] args) {
    int _size = ((List<String>)Conversions.doWrapArray(args)).size();
    boolean _notEquals = (_size != 2);
    if (_notEquals) {
      String _usage = this.getUsage();
      InputOutput.<String>println(_usage);
      return;
    }
    final String specModelFileName = args[0];
    final String annotatedDocFileName = args[1];
    final Specification specModel = this.loader.loadSpecificationModel(specModelFileName);
    final Annotation analyzed = this.pipeline.analyzeTextFromFile(annotatedDocFileName);
    EList<SpecDocument> _documents = specModel.getDocuments();
    SpecDocument _analyzedDocToSpecDoc = this.pipeline.analyzedDocToSpecDoc(analyzed);
    _documents.add(_analyzedDocToSpecDoc);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Successfully loaded annotated document \"");
    _builder.append(annotatedDocFileName, "");
    _builder.append("\" to specification \"");
    _builder.append(specModelFileName, "");
    _builder.append("\"");
    this.logger.info(_builder);
    this.loader.saveSpecificationModel(specModel, specModelFileName);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Saved updated specification back to file: \"");
    _builder_1.append(specModelFileName, "");
    _builder_1.append("\"");
    this.logger.info(_builder_1);
    InputOutput.<String>println("done. see logs");
  }
}
