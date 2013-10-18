/**
 */
package spec;

import java.io.IOException;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a domain model stored as an EPackage.
 * The EPackage can be loaded from an XMI file created by the default Ecore model editor.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.DomainModel#getModelPackage <em>Model Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getDomainModel()
 * @model
 * @generated
 */
public interface DomainModel extends EObject
{
	/**
	 * Returns the value of the '<em><b>Model Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Package</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Package</em>' containment reference.
	 * @see #setModelPackage(EPackage)
	 * @see spec.SpecPackage#getDomainModel_ModelPackage()
	 * @model type="spec.EPackage" containment="true" required="true"
	 * @generated
	 */
	EPackage getModelPackage();

	/**
	 * Sets the value of the '{@link spec.DomainModel#getModelPackage <em>Model Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Package</em>' containment reference.
	 * @see #getModelPackage()
	 * @generated
	 */
	void setModelPackage(EPackage value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ENamedElement getNamedElement(String elementName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void rebuildNameIndex();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method loads an EPackage from XMI file.
	 * An already loaded Epackage will be replaced by this newly loaded version.
	 * <!-- end-model-doc -->
	 * @model exceptions="spec.IOException"
	 * @generated
	 */
	void loadFromXmiFile(String fileName) throws IOException;

} // DomainModel
