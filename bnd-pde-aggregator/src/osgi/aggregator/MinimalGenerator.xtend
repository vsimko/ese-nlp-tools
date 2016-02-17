package osgi.aggregator

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class MinimalGenerator {

	def static void main(String[] args) {

		val copyToLoc = args.head
		println('''Generated bundles will be copied into «copyToLoc»''')
		
		for(projectLoc : args.tail) {
			
			
			
			println('''Project: «projectLoc»''')
			val genDir = new File('''«projectLoc»/generated''')
			
			println("Aggregating generated files...")
			if(genDir.directory) {
				val jarfiles = genDir.listFiles([dir,fname|fname.matches(".*\\.jar$")])
				for(srcpath:jarfiles.map[toPath]) {
					val dstpath = new File('''«copyToLoc»/«srcpath.fileName»''').toPath
					//println(''' copy «srcpath» -> «dstpath»''')
					Files.copy(srcpath, dstpath, StandardCopyOption.REPLACE_EXISTING)
				}
				println('''- copied «jarfiles.size» bundle(s).''')
			}
		}
	}
}