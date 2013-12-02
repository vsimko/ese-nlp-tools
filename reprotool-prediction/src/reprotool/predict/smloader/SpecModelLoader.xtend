package reprotool.predict.smloader

import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Reference
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.ENamedElement
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl
import reprotool.predict.logging.ReprotoolLogger
import spec.DomainEntityType
import spec.DomainModel
import spec.EntityLink
import spec.SpecFactory
import spec.SpecPackage
import spec.SpecSentence
import spec.Specification

import static extension reprotool.dmodel.extensions.ReprotoolEcoreExtensions.*
import static extension reprotool.dmodel.extensions.StatisticalExtensions.*

@Component(provide=SpecModelLoader)
class SpecModelLoader {
	
	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}
	
	def loadSpecificationModel(String fileName) {
		
		// initializes the meta-model, otherwise the models would complain about unknown URIs
		SpecPackage.eINSTANCE.EPackage
		
		val resource = new XMIResourceImpl
		try {
			resource.load(new FileInputStream(fileName), null)
		} catch(FileNotFoundException e) { return null }
		return resource.contents.head as Specification
	}
	
	def void saveSpecificationModel(Specification specModel, String fileName) {
		new XMIResourceImpl => [
			contents.add(specModel)
			save(new FileOutputStream(fileName), null)
		]
	}
	
	def void saveDomainModel(Specification specModel, String outFileName) {
		specModel.removeEAnnotationsFromDomainModel
		
		// TODO: currently, we assume that generated package name=nsPrefix=nsURI 
		specModel.domainModel.modelPackage => [
			setNsPrefix(name)
			setNsURI(name)
		]
		
		new XMIResourceImpl => [
			contents.add(specModel.domainModel.modelPackage)
			save(new FileOutputStream(outFileName), null)
		]
	}
	
	/**
	 * Loads a domain model to the specification from XMI file.
	 */
	def loadDomainModel(String fileName) {
		SpecFactory.eINSTANCE.createDomainModel => [
			loadFromXmiFile(fileName)
			rebuildNameIndex
		]
	}
	
	/**
	 * Rebuilds name index in the domain model and resolves entity links
	 * in all documents within the specification. 
	 */
	def void resolveEntityLinks(Specification specModel) {
		val domainModel = specModel.domainModel
		domainModel.rebuildNameIndex
		specModel.documents.forEach[
			sentences.forEach[resolveEntityLinks(domainModel)]
		]
	}
	
	// extension method
	def private void removeEAnnotationsFromDomainModel(Specification specModel) {
		// removing all "backlinks" EAnnotations because they point to objects that will not be serialized
		specModel.domainModel.modelPackage.eAllContents
			.filter(ENamedElement)
			.toList // copy the collection because we are going to modify it during iteration
			.forEach[
				if( ! EAnnotations.empty) {
					'''Removed «EAnnotations.size» EAnnotation(s) containing «EAnnotations.map[references.size].sum(0)» reference(s) from "«name»" '''.debug
					EAnnotations.clear
				}
			]
	}

	// extension method
	def private void resolveEntityLinks(SpecSentence sentence, DomainModel domainModel) {
		sentence.entityLinks.forEach[resolveEntityLinks(domainModel)]
	}

	//extension method
	def private void resolveEntityLinks(EntityLink entityLink, DomainModel domainModel) {
		entityLink => [
			linkedEntity = domainModel.getNamedElement(entLabel)
			if(linkedEntity == null) {
				'''WARNING: could not find domain entity «entLabel»'''.warn
			} else {
				'''Resolving entityLink: «entLabel»:«entType»'''.debug
				
				// prepare the entity type ( used by a classifier before we established links to real entities in the domain model)
				entType = switch linkedEntity {
					EClass		: DomainEntityType.CLASS
					EReference	: DomainEntityType.REFERENCE
					EAttribute	: DomainEntityType.ATTRIBUTE
					EOperation	: DomainEntityType.OPERATION
					default		: null
				}
				
				// we use EAnnotation attached to the domain model element, e.g. EClass, to store backlinks to the EntityLink objects
				var eannot = linkedEntity.getEAnnotationOrCreate("backlinks")
				eannot.references += entityLink
			}
		]
	}
	
}