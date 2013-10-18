package spec.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class DomainModelImplCustom extends DomainModelImpl {
	
	private Map<String, ENamedElement> mapStr2NamedElement = new Hashtable<>();
	
	@Override
	public ENamedElement getNamedElement(final String elementName) {
		if(mapStr2NamedElement.isEmpty())
			throw new RuntimeException("Name index was not created. Rebuild the index first.");

		return mapStr2NamedElement.get(elementName);
	}
	
	@Override
	public void rebuildNameIndex() {
		mapStr2NamedElement.clear();
		
		final TreeIterator<EObject> iterator = getModelPackage().eAllContents();
		while (iterator.hasNext()) {
			final EObject eobj = (EObject) iterator.next();
			
			// create index for classes
			if(eobj instanceof EClass) {
				final EClass eclass = (EClass) eobj;
				mapStr2NamedElement.put(eclass.getName(), eclass);
				
			// create index for attributes and references (they are structural features)
			} else if(eobj instanceof EStructuralFeature) {
				final EStructuralFeature efeature = (EStructuralFeature) eobj;
				
				final EClass containingClass = efeature.getEContainingClass();
				final String parentClassName = containingClass.getName();
				final String key = parentClassName + "." + efeature.getName();
				
				mapStr2NamedElement.put(key, efeature);
				
			// create index for operations (functions)
			} else if(eobj instanceof EOperation) {
				final EOperation eoperation = (EOperation) eobj;
				final EClass containingClass = eoperation.getEContainingClass();
				final String parentClassName = containingClass.getName();
				final String key = parentClassName + "." + eoperation.getName();
				
				mapStr2NamedElement.put(key, eoperation);
			}
			
		}
	}
	
	@Override
	public void loadFromXmiFile(final String fileName) throws IOException {

		// load the EPackage from ecore file
		final XMIResource resource = new XMIResourceImpl();
		final InputStream inputStream = new FileInputStream(fileName);
		resource.load(inputStream, null);
		final EPackage pkg = (EPackage) resource.getContents().get(0);
		
		// add the loaded EPackage to domainModel and rebuild the name index
		setModelPackage(pkg);
		rebuildNameIndex();
	}
}
