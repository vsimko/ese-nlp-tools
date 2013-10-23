package reprotool.predict.launcher

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.util.Map
import java.util.concurrent.ConcurrentHashMap
import reprotool.predict.logging.ReprotoolLogger
import reprotool.predict.exectoolapi.IExecutableTool

@Component(
	immediate=true,
	provide=LauncherComponent,
	properties = #[
		"osgi.command.scope:String=reprotool",
		"osgi.command.function:String=showtools",
		"osgi.command.function:String=runtool"
	]
)
class LauncherComponent {
		
	var String[] args;
	
	@Reference(target="(launcher.arguments=*)")
	def void setArgs(Object object, Map<String, Object> map) {
		args = map.get("launcher.arguments") as String[];
	}
	
	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}
	
	// we use a dynamic+optional+multiple reference and therefore multiple threads can access the toolMap
	val toolMap = new ConcurrentHashMap<String, IExecutableTool>

	@Reference(multiple=true, optional=true, dynamic=true)
	def void addTool(IExecutableTool tool) {
		val toolName = tool.class.simpleName
		val previousItem = toolMap.putIfAbsent(toolName, tool)
		if(previousItem != null)
			throw new Exception('''Tool already registered : «toolName»''')
	}
	
	def void removeTool(IExecutableTool tool) {
		val toolName = tool.class.simpleName
		toolMap.remove(toolName)
	}
	
	// GOGO COMMAND
	def showtools() '''
		Available tools are:
		 «FOR toolName:toolMap.keySet»
		 - «toolName»
		 «ENDFOR»
	'''
	
	// GOGO COMMAND
	def void runtool(String[] args) {
		
		if(args.empty) {
			println("No tool specified")
			println(showtools)
			return
		}
		
		val toolName = args.get(0)
		val toolArgs = args.tail
		val toolInstance = toolMap.get(toolName)
		
		if(toolInstance == null) {
			println('''Unknown tool specified: «toolName»''')
			println(showtools)
			return
		}
		toolInstance.execute(toolArgs)
	}
}
