package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.smloader.SpecModelLoader
import reprotool.predict.exectoolapi.IExecutableTool

@Component
class ExportDomainModelTool implements IExecutableTool {
	
	override getUsage() '''
	Exports a domain model stored inside a Specification model into a separate ecore XMI file.
		[specFile]	= input XMI file containing the specification model
		[outFile]	= output *.ecore (also XMI) file where the domain model will be exported 
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
		
		val String specFileName = args.get(0)
		val String outFileName = args.get(1)
		
		if(!outFileName.endsWith(".ecore"))
			throw new Exception("The output filename should contain .ecore suffix")
			
		val specModel = loader.loadSpecificationModel(specFileName)
		
		if(specModel.domainModel?.modelPackage == null)
			throw new Exception('''No domain model found in the specification "«specFileName»"''')
		
		// Now we can finally save the XMI file
		loader.saveDomainModel(specModel, outFileName)
		
		'''Successfully exported domain model from "«specFileName»" to "«outFileName»"'''.info
		println("done. see logs")
	}
}