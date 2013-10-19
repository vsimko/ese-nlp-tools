package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import reprotool.dmodel.api.ITool
import reprotool.prediction.api.loaders.SpecModelLoader
import spec.SpecFactory

@Component
class CreateEmptySpecificationTool implements ITool {
	
	override getUsage() '''
	This tool creates an empty specification model stored in an XMI file.
		[filename]	= XMI file to be created
	'''
	
	override execute(String[] args) {
		
		// check arguments
		if(args.size != 1) {
			println(usage)
			return
		}
		
		val saveToFileName = args.head
		
		new SpecModelLoader => [
			saveSpecificationModel(SpecFactory.eINSTANCE.createSpecification, saveToFileName)
		]
		
		println('''empty specification model created and saved to file: «saveToFileName»''')
	}
}