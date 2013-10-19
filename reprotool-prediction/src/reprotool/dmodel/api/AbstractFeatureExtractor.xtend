package reprotool.dmodel.api

import java.util.List
import org.eclipse.emf.ecore.EObject

/**
 * For convenience, this class predefines behavior for generic EMF and non-EMF objects.
 * You should define your own dispatch method for visiting a specific type.
 * 
 * @example <code>
 *   def dispatch visit(MyModelElement e) { ... some code ... }
 * </code>
 */
abstract class AbstractFeatureExtractor implements FeatureExtractor {

	private var List<String>params
	
	def protected onNewParams() {}
	final def protected getParams() {params}
	final override setParams(String[] params){
		if(this.params != null)
			throw new Exception("Changing the parameters of a feature extractor is not allowed")
			
		this.params = params.immutableCopy
		onNewParams
	}

	final override getExtractorName() {
		getClass.getAnnotation(Feature).value
	}
	
	final override getFeatureName() {
		if(params.empty)	extractorName
		else				extractorName + ":" + params.join(":")
	}
	
	/**
	 * We return null for all EMF objects that are not explicitly handled by
	 * the feature extractor.
	 */
	def dispatch String visit(EObject eobj) {
		null
	}
	
	/**
	 * We throw an exception for other non-EMF objects.
	 */
	def dispatch String visit(Object obj) {
		throw new Exception("Visiting non-EMF objects is not supported")
	}
	
	final override toString() '''«featureName» : «getClass.simpleName»'''
	override getDocumentation() {}
}