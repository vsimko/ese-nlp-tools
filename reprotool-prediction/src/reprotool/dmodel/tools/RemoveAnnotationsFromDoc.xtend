package reprotool.dmodel.tools

import aQute.bnd.annotation.component.Component
import java.io.FileReader
import java.io.FileWriter
import org.apache.log4j.Logger
import reprotool.dmodel.api.ITool

import static extension com.google.common.io.CharStreams.*

@Component
class RemoveAnnotationsFromDoc implements ITool {

	extension Logger = Logger.getLogger(RemoveAnnotationsFromDoc)

	override getUsage() '''
	Removes annotations (HTML "a" tags) from a given document.
		[doc] = the file containing annotated document
	'''


	override execute(String[] args) {
		// check arguments
		if(args.size != 1) {
			usage.warn
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
	}
	
}