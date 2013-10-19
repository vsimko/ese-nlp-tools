package reprotool.prediction.api.loaders;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;

import spec.DomainModel;
import spec.SpecFactory;

@Deprecated
public class DomainModelLoader {

	/**
	 * @param fileName
	 * @return the EPackage representing the domain model
	 * @throws IOException
	 */
	final public DomainModel loadDomainModel(String fileName) throws IOException {
		
		// add the loaded EPackage to domainModel
		final DomainModel domainModel = SpecFactory.eINSTANCE.createDomainModel();
		domainModel.loadFromXmiFile(fileName);
		
		return domainModel;
	}
	
	/**
	 * Just for testing
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {

		final DomainModelLoader loader = new DomainModelLoader();
		final DomainModel domainModel = loader.loadDomainModel("../reprotool.example.LibrarySystem/libsys.dmodel.ecore");
		
		final EList<EObject> contents = domainModel.getModelPackage().eContents();
		
		for (EObject eObject : contents) {
			
			System.out.println("─────────────────────────────────────────────");
			if(eObject instanceof EClass) {
				EClass eClass = (EClass) eObject;
				System.out.println("CLASS: " + eClass.getName());
			
				for (EAttribute eAttr : eClass.getEAllAttributes()) {
					System.out.println(String.format("  + %1$s [%2$s..%3$s] : %4$s",
							eAttr.getName(),
							eAttr.getLowerBound() == -1 ? "*" : eAttr.getLowerBound(),
							eAttr.getUpperBound() == -1 ? "*" : eAttr.getUpperBound(),
							eAttr.getEAttributeType() == null ? "null" : eAttr.getEAttributeType().getName()
							));
				}
				
				for (EOperation eOperation : eClass.getEAllOperations()) {
					System.out.println(
							String.format("  ⚙ %1$s()",
									eOperation.getName()
									));
					
				}
				
				for (EReference eReference : eClass.getEAllReferences()) {
					System.out.println(
							String.format("  %4$s──╢ %1$s [%2$s..%3$s] ╟──> %5$s",
									eReference.getName(),
									eReference.getLowerBound() == -1 ? "*" : eReference.getLowerBound(),
									eReference.getUpperBound() == -1 ? "*" : eReference.getUpperBound(),
									eReference.isContainment() ? "◀▶" : "──",
									eReference.getEReferenceType().getName()
									));
				}
				for (EClass eSuperClass : eClass.getESuperTypes()) {
					System.out.println(
							String.format("  ────▷ %1$s",
									eSuperClass.getName()
									));
				}
				
				
			} else if (eObject instanceof EEnum) {
				EEnum eEnum = (EEnum) eObject;
				System.out.println("ENUM: " + eEnum.getName());
			} else {
				System.out.println("UNKNOWN: " + eObject.getClass());
			}
			System.out.println();
		}
				
		//System.out.println(contents);
		
//		// initializing specification object
//		final Specification specification = SpecFactory.eINSTANCE.createSpecification();
//		final DomainModel domainModel = SpecFactory.eINSTANCE.createDomainModel();
//		specification.setDomainModel(domainModel);
//		final Glossary glossary = SpecFactory.eINSTANCE.createGlossary();
//		specification.setGlossary(glossary);
//		
//		final DomainModelLoader loader = new DomainModelLoader(specification);
//		loader.loadDomainModel("txtspecbank/libsys.dmodel");
//
//		// save XMI resource
//		final XMIResource resource = new XMIResourceImpl();
//		resource.getContents().add(specification);
////		resource.save(System.out, null);
//		resource.save( new FileOutputStream("test.xmi"), null);
	}


}
