package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.smloader.SpecModelLoader
import reprotool.predict.exectoolapi.IExecutableTool

@Component
class ResolveEntityLinksTool implements IExecutableTool {

	override getUsage() '''
	This tool requires that the input specification model contained a domain model
	and also documents processed by the linguistic pipeline.
	If the documents contained annotations representing the links from the
	document to the domain model, this tool creates for each of the an EntityLink object
	that encapsulates the link.

		[specModelFile] = XMI file containing an existing specification model
	'''

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
		if(args.size != 1) {
			println(usage)
			return
		}
		
		val specModelFileName = args.get(0)
		
		val specModel = loader.loadSpecificationModel(specModelFileName)
		
		if(specModel.domainModel == null)
			throw new Exception("The specification does not contain a domain model.")

		if(specModel.documents.empty)
			throw new Exception("The specification does not contain documents")

		loader.resolveEntityLinks(specModel)
		loader.saveSpecificationModel(specModel, specModelFileName)
		
		println("Saved updated specification back to file")
	}
}