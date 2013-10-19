package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import org.apache.log4j.Logger
import reprotool.dmodel.api.ITool
import reprotool.dmodel.nlp.ReprotoolLinguisticPipeline
import reprotool.prediction.api.loaders.SpecModelLoader

@Component
class LoadAnnotatedDocumentTool implements ITool {
	
	extension Logger = Logger.getLogger(LoadAnnotatedDocumentTool)

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

	override execute(String[] args) {
		// check arguments
		if(args.size != 2) {
			usage.warn
			return
		}
		
		val specModelFileName = args.get(0)
		val annotatedDocFileName = args.get(1)
		
		val loader = new SpecModelLoader
		
		val specModel = loader.loadSpecificationModel(specModelFileName)
		
		val pipeline = new ReprotoolLinguisticPipeline
		val analyzed = pipeline.analyzeTextFromFile(annotatedDocFileName)

		specModel.documents += pipeline.analyzedDocToSpecDoc(analyzed)
		"Successfully loaded annotated document to specification".info
		
		loader.saveSpecificationModel(specModel, specModelFileName)
		'''Saved updated specification back to file: "«specModelFileName»"'''.info
	}
}