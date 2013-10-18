package spec.loaders;

import java.io.IOException;

import spec.DomainModel;
import spec.SpecFactory;

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
}
