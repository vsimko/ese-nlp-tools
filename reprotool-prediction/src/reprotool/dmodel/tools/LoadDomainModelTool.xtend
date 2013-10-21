package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import reprotool.dmodel.api.ITool
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.smloader.SpecModelLoader

@Component
class LoadDomainModelTool implements ITool {
	
	override getUsage() '''
	This tool loads an existing domain model (any Ecore file) which is serialized in an XMI file
	into and existing specification model (also an XMI file).
	The newly loaded domain model will replace any previously loaded domain model.

		[specModelFile]   = XMI file containing an existing specification model
		[domainModelFile] = XMI file containing a domain model (any Ecore file)
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
		if(args.size != 2) {
			println(usage)
			return
		}
		
		val specModelFileName = args.get(0)
		val domainModelFileName = args.get(1)
		
		val specModel = loader.loadSpecificationModel(specModelFileName)
		if(specModel.domainModel != null)
			throw new Exception("This specification already contains a domain model")
			
		specModel.domainModel = loader.loadDomainModel(domainModelFileName)
		loader.saveSpecificationModel(specModel, specModelFileName)
		
		'''Successfully loaded domain model from file «domainModelFileName» to specification model «specModelFileName»'''.info
		println("done. see logs")
	}
}