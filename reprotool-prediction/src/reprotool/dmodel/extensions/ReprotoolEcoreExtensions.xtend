package reprotool.dmodel.extensions

import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.ENamedElement
import org.eclipse.emf.ecore.EcoreFactory
import spec.EntityLink
import spec.SpecWord
import spec.Specification

class ReprotoolEcoreExtensions {
	
	/**
	 * Retrieves EAnnotation by its name from EModelElement or creates an empty EAnnotation if it does not already exist. 
	 */
	def static getEAnnotationOrCreate(EModelElement eModelElement, String source) {
		
		val eAnnotation = eModelElement.getEAnnotation(source)
		if(eAnnotation != null)
			return eAnnotation
		
		return EcoreFactory.eINSTANCE.createEAnnotation => [
			it.source = source
			eModelElement.EAnnotations += it
		]
	}
	
	// extension method
	def static SpecWord getCorefRepOrSelf(SpecWord word) {
		if(word.corefRepMention == null) word else word.corefRepMention
	}

	// extension method
	def static allEntityLinks(Specification specModel) {
		specModel.documents.map[sentences.map[entityLinks].flatten].flatten
	}
	
	/**
	 * Retrieves the backlinks from domain model to entity links in the specification.
	 */
	def static getBacklinks(ENamedElement e) {
		return e.getEAnnotation("backlinks").references.filter(EntityLink)
	}
}