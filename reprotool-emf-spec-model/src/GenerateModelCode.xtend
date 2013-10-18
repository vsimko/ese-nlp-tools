import org.eclipse.emf.mwe.utils.DirectoryCleaner
import org.eclipse.emf.mwe.utils.StandaloneSetup
import org.eclipse.emf.mwe2.ecore.EcoreGenerator
import org.eclipse.emf.mwe2.runtime.workflow.Workflow
import java.io.File

class GenerateModelCode {
	
	def static void main(String[] args) {

		val cd = new File(System.getProperty("user.dir")).name
		val GENFILE = '''platform:/resource/«cd»/model/«cd».genmodel'''
		
		new Workflow => [
				
			addBean(new StandaloneSetup => [
				setScanClassPath(true)
				addRegisterGenModelFile(GENFILE)
			])
			
			addComponent(new DirectoryCleaner => [
				setDirectory("src-gen")
			])
			
			addComponent(new EcoreGenerator => [
				setGenModel(GENFILE)
				addSrcPath("src")
				addSrcPath("xtend-gen")
			])
			
			run(null)
		]
		
		new File("plugin.xml").delete
		new File("plugin.properties").delete
		new File("build.properties").delete
	}
}