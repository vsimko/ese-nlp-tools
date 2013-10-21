package reprotool.predict.launcher

import aQute.bnd.annotation.component.Activate
import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.util.HashMap
import java.util.Map
import org.osgi.framework.BundleContext
import org.osgi.service.log.LogService
import reprotool.dmodel.api.ITool

@Component(
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
	
	private LogService logService

	@Reference
	def void setLogService(LogService logService){
		this.logService = logService
	}
	
	val toolMap = new HashMap<String, ITool>

	def INFO(String msg) {
		logService.log(LogService.LOG_INFO, msg)
	}

	@Reference(multiple=true, optional=true, dynamic=true)
	def void addTool(ITool tool) {
		val toolName = tool.class.simpleName
		if(toolMap.containsKey(toolName))
			throw new Exception('''Tool already registered : «toolName»''')
		toolMap.put( toolName, tool )
	}
	
	def void removeTool(ITool tool) {
		val toolName = tool.class.simpleName
		toolMap.remove(toolName)

	}
	
	def showtools() '''
		Available tools are:
		 «FOR toolName:toolMap.keySet»
		 - «toolName»
		 «ENDFOR»
	'''
	
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
	
	var BundleContext bundleContext
	
	@Activate def void activate(BundleContext context) {
		bundleContext = context
		INFO('''reprotool launcher activated with «toolMap.size» tools''')
//		context.getBundle(0).stop

//		// first argument specifies the tool, other args will be passed to the tool
//		val toolName = if(args.empty) "empty" else args.head
//		val toolArgs = args.tail
//		
//		println('''TOOL: «toolName»''')
//		println('''ARGS: «toolArgs.join(", ")»''')
		
//		if(toolmap.containsKey(toolName)) {
//			val clazz = toolmap.get(toolName)
//			val method = clazz.getMethod("main", typeof(String[]))
//
//			val invokeArgs = newArrayList
//			val String[] argsAsArray = toolArgs.toList
//			invokeArgs.add(argsAsArray)
//			
//			method.invoke(null, invokeArgs.toArray)
//		} else {
//			println('''
//				===========================================================
//				Prediction framework (by Viliam Simko 2013)
//				===========================================================
//				Unknown tool : «toolName»
//				Available tools are:
//				«FOR availToolName : toolmap.keySet»
//					- «availToolName»
//				«ENDFOR»
//			''')
//		}
	}

	
//
//
//
//		val args = context.getArguments.get("application.args") as String[]
//		
//		// first argument specifies the tool, other args will be passed to the tool
//		val toolName = if(args.empty) "empty" else args.head
//		val toolArgs = args.tail
//
//		val toolmap = newHashMap(
//
//			//tools
//			"ShowExtractors"	-> "reprotool.predict.dmodel.cli.ShowAvailFeatureExtractors",
//			"CreateEmptySpec"	-> "reprotool.predict.dmodel.cli.CreateEmptySpecificationTool",
//			"ExportDomainModel"	-> "reprotool.predict.dmodel.cli.ExportDomainModelTool",
//			"ExtractSamples"	-> "reprotool.predict.dmodel.cli.ExtractSamplesTool",
//			"LoadDocument"		-> "reprotool.predict.dmodel.cli.LoadAnnotatedDocumentTool",
//			"LoadDomainModel"	-> "reprotool.predict.dmodel.cli.LoadDomainModelTool",
//			"MaxentTrainer"		-> "reprotool.predict.dmodel.cli.MaxentTrainerTool",
//			"CleanAnnotatedDoc"	-> "reprotool.predict.dmodel.cli.RemoveAnnotationsFromDoc",
//			"ResolveLinks"		-> "reprotool.predict.dmodel.cli.ResolveEntityLinksTool",
//			
//			// phases
//			"ElicitationPhase"		-> "reprotool.predict.dmodel.cli.phases.ElicitationPhase",
//			"FeatureSelectionPhase"	-> "reprotool.predict.dmodel.cli.phases.FeatureSelectionPhase",
//			"TrainingPhase"			-> "reprotool.predict.dmodel.cli.phases.TrainingPhase"
//		)
//		
//		if(toolmap.containsKey(toolName)) {
//			val className = toolmap.get(toolName)
//			val clazz = super.class.classLoader.loadClass(className)
//			val method = clazz.getMethod("main", typeof(String[]))
//
//			val invokeArgs = newArrayList
//			val String[] argsAsArray = toolArgs.toList
//			invokeArgs.add(argsAsArray)
//			
//			method.invoke(null, invokeArgs.toArray)
//		} else {
//			println('''
//				===========================================================
//				Prediction framework (by Viliam Simko 2013)
//				===========================================================
//				Unknown tool : «toolName»
//				Available tools are:
//				«FOR availToolName : toolmap.keySet»
//					- «availToolName»
//				«ENDFOR»
//			''')
//		}
//		
//		return IApplication::EXIT_OK
//	}
}
