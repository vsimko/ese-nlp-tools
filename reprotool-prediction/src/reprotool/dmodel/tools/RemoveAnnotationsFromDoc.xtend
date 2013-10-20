package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.io.FileReader
import java.io.FileWriter
import reprotool.dmodel.api.ITool
import reprotool.predict.logging.ReprotoolLogger

import static extension com.google.common.io.CharStreams.*

@Component
class RemoveAnnotationsFromDoc implements ITool {

	override getUsage() '''
	Removes annotations (HTML "a" tags) from a given document.
		[doc] = the file containing annotated document
	'''

	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}

	override execute(String[] args) {
		// check arguments
		if(args.size != 1) {
			println(usage)
			return
		}
		
		val docFileName = args.get(0)
		val outFileName = docFileName + ".out"
		
		val content = new FileReader(docFileName).readLines.join("\n")
		val processedContent = content.replaceAll("(<[Aa][^>]*>([^<]+)</[Aa]>)", "$2")
		
		val writer = new FileWriter(outFileName)
		writer.write( processedContent)
		writer.close
		
		'''Successfuly transformed to "«outFileName»"'''.info
		println("done. see logs")
	}
	
}