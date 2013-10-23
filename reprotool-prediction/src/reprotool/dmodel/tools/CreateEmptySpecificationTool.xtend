package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.smloader.SpecModelLoader
import spec.SpecFactory
import reprotool.predict.exectoolapi.IExecutableTool

@Component
class CreateEmptySpecificationTool implements IExecutableTool {
	
	override getUsage() '''
	This tool creates an empty specification model stored in an XMI file.
		[filename]	= XMI file to be created
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
		
		val saveToFileName = args.head
		
		loader.saveSpecificationModel(SpecFactory.eINSTANCE.createSpecification, saveToFileName)
		
		'''empty specification model created and saved to file: «saveToFileName»'''.info
		println("done. see logs")
	}
}