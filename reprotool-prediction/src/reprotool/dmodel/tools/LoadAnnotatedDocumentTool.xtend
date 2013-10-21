package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import reprotool.dmodel.api.ITool
import reprotool.dmodel.nlp.pipeline.ReprotoolLinguisticPipeline
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.smloader.SpecModelLoader

@Component
class LoadAnnotatedDocumentTool implements ITool {
	
	override getUsage() '''
		This tool performs a thorough linguistic analysis on the selected document and
		included all the generated linguistic artefacts into an existing specification model.
		You can load multiple documents into a single specification model.
		The linguistic analysis uses the Stanford CoreNLP framework with custom modifications
		to the pipeline. The input document can be a plain text file or an HTML document.
		Best results can be achieved with the HTML because we use XML tags as evidence for
		splitting the sentences.
		The document may also contain annotations (links from text to the domain model) that
		are encoded as hypertext references: <a href="#DomainEntityName">some text</a>
		
			[specmodel] = XMI file containing an existing specification model
			[document]  = A file containing the text annotated with links to the domain model (HTML/XHTML/XML/TXT)
	'''

	private ReprotoolLinguisticPipeline pipeline
	@Reference def void setLinguisticPipeline(ReprotoolLinguisticPipeline pipeline) {
		this.pipeline = pipeline
	}

	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}
	
	private SpecModelLoader loader
	@Reference def void setLoader(SpecModelLoader loader) {
		this.loader = loader
	}
	
	override execute(String[] args) {
		// check arguments
		if(args.size != 2) {
			println(usage)
			return
		}
		
		val specModelFileName = args.get(0)
		val annotatedDocFileName = args.get(1)
		
		val specModel = loader.loadSpecificationModel(specModelFileName)
		val analyzed = pipeline.analyzeTextFromFile(annotatedDocFileName)

		specModel.documents += pipeline.analyzedDocToSpecDoc(analyzed)
		'''Successfully loaded annotated document "«annotatedDocFileName»" to specification "«specModelFileName»"'''.info
		
		loader.saveSpecificationModel(specModel, specModelFileName)
		'''Saved updated specification back to file: "«specModelFileName»"'''.info
		
		println("done. see logs")
	}
}