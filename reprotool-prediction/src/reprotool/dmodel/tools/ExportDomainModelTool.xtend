package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import reprotool.dmodel.api.ITool
import reprotool.prediction.api.loaders.SpecModelLoader

@Component
class ExportDomainModelTool implements ITool {
	
	override getUsage() '''
	Exports a domain model stored inside a Specification model into a separate ecore XMI file.
		[specFile]	= input XMI file containing the specification model
		[outFile]	= output *.ecore (also XMI) file where the domain model will be exported 
	'''
	
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
			
		val loader = new SpecModelLoader
		val specModel = loader.loadSpecificationModel(specFileName)
		
		if(specModel.domainModel?.modelPackage == null)
			throw new Exception('''No domain model found in the specification "«specFileName»"''')
		
		// Now we can finally save the XMI file
		loader.saveDomainModel(specModel, outFileName)
		
		println('''Successfully exported domain model from "«specFileName»" to "«outFileName»"''')
	}
}