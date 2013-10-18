import java.io.File
import org.eclipse.emf.mwe.utils.StandaloneSetup
import org.eclipse.emf.mwe2.ecore.EcoreGenerator

class MinimalGenerator {
	
	def static void main(String[] args) {

		val cd = new File(System.getProperty("user.dir")).name
		val GENFILE = '''platform:/resource/«cd»/model/«cd».genmodel'''

		new StandaloneSetup => [
			setScanClassPath(true)
			addRegisterGenModelFile(GENFILE)
		]

		new EcoreGenerator => [
			setGenModel(GENFILE)
			addSrcPath("src")
			addSrcPath("xtend-gen")
			invoke(null)
		]
		
		new File("plugin.xml").delete
		new File("plugin.properties").delete
		new File("build.properties").delete
		
		println("done. don't forget to refresh your workspace")
	}
}