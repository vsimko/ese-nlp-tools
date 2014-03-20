package modellinker

import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration
import org.eclipse.gmt.modisco.java.ClassDeclaration
import org.eclipse.gmt.modisco.java.InterfaceDeclaration
import org.eclipse.gmt.modisco.java.Model
import org.eclipse.gmt.modisco.java.Package
import org.eclipse.gmt.modisco.java.emf.JavaPackage
import org.eclipse.emf.ecore.EObject

class Utils {
	def static EObject loadModel(String fileName) {
		// init model
		val instance = JavaPackage::eINSTANCE
		
		val reg = Resource.Factory.Registry::INSTANCE
		val map = reg.extensionToFactoryMap
		map.put("*", new XMIResourceFactoryImpl)
		
		val resourceSet = new ResourceSetImpl
		val resource = resourceSet.getResource(URI::createURI(fileName), true)
		
		resource.contents.head
	}
	
	def static Package getInitPackage(Model model, String[] path) {
		var packagesToSearch = model.ownedElements
		var Package lastPackage = null
		
		for (packageName : path) {
			lastPackage = packagesToSearch.findFirst[it.name == packageName]
			if (lastPackage == null) {
				return null
			}
			packagesToSearch = lastPackage.ownedPackages
		}
		
		lastPackage
	}
	
	static def List<AbstractTypeDeclaration> collectTypeDeclarations(Package pkg) {
		val retVal = newArrayList
		
		pkg.ownedElements.forEach[
			if (it instanceof ClassDeclaration || it instanceof InterfaceDeclaration)
				retVal += it		
		]
		
		pkg.ownedPackages.forEach[
			retVal += collectTypeDeclarations(it) 
		]
		
		retVal
	}
}