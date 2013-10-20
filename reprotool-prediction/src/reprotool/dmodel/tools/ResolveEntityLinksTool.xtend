package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import org.apache.log4j.Logger
import reprotool.dmodel.api.ITool
import reprotool.prediction.api.loaders.SpecModelLoader

@Component
class ResolveEntityLinksTool implements ITool {

	override getUsage() '''
	This tool requires that the input specification model contained a domain model
	and also documents processed by the linguistic pipeline.
	If the documents contained annotations representing the links from the
	document to the domain model, this tool creates for each of the an EntityLink object
	that encapsulates the link.

		[specModelFile] = XMI file containing an existing specification model
	'''

	override execute(String[] args) {

		// check arguments
		if(args.size != 1) {
			println(usage)
			return
		}
		
		val specModelFileName = args.get(0)
		
		val loader = new SpecModelLoader
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