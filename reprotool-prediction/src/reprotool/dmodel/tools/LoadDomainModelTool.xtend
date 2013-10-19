package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import reprotool.dmodel.api.ITool
import reprotool.prediction.api.loaders.SpecModelLoader

@Component
class LoadDomainModelTool implements ITool {
	
	override getUsage() '''
	This tool loads an existing domain model (any Ecore file) which is serialized in an XMI file
	into and existing specification model (also an XMI file).
	The newly loaded domain model will replace any previously loaded domain model.

		[specModelFile]   = XMI file containing an existing specification model
		[domainModelFile] = XMI file containing a domain model (any Ecore file)
	'''

	override execute(String[] args) {
		// check arguments
		if(args.size != 2) {
			println(usage)
			return
		}
		
		val specModelFileName = args.get(0)
		val domainModelFileName = args.get(1)
		
		val loader = new SpecModelLoader
		
		val specModel = loader.loadSpecificationModel(specModelFileName)
		if(specModel.domainModel != null)
			throw new Exception("This specification already contains a domain model")
			
		specModel.domainModel = loader.loadDomainModel(domainModelFileName)
		
		loader.saveSpecificationModel(specModel, specModelFileName)
		
		println('''Successfully loaded domain model from file «domainModelFileName» to specification model «specModelFileName»''')
	}	
}