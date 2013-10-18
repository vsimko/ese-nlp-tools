/**
 */
package spec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.Specification#getDocuments <em>Documents</em>}</li>
 *   <li>{@link spec.Specification#getGlossary <em>Glossary</em>}</li>
 *   <li>{@link spec.Specification#getDomainModel <em>Domain Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getSpecification()
 * @model
 * @generated
 */
public interface Specification extends EObject
{
	/**
	 * Returns the value of the '<em><b>Documents</b></em>' containment reference list.
	 * The list contents are of type {@link spec.SpecDocument}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documents</em>' containment reference list.
	 * @see spec.SpecPackage#getSpecification_Documents()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpecDocument> getDocuments();

	/**
	 * Returns the value of the '<em><b>Glossary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Glossary</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Glossary</em>' containment reference.
	 * @see #setGlossary(Glossary)
	 * @see spec.SpecPackage#getSpecification_Glossary()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Glossary getGlossary();

	/**
	 * Sets the value of the '{@link spec.Specification#getGlossary <em>Glossary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Glossary</em>' containment reference.
	 * @see #getGlossary()
	 * @generated
	 */
	void setGlossary(Glossary value);

	/**
	 * Returns the value of the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Model</em>' containment reference.
	 * @see #setDomainModel(DomainModel)
	 * @see spec.SpecPackage#getSpecification_DomainModel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DomainModel getDomainModel();

	/**
	 * Sets the value of the '{@link spec.Specification#getDomainModel <em>Domain Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Model</em>' containment reference.
	 * @see #getDomainModel()
	 * @generated
	 */
	void setDomainModel(DomainModel value);

} // Specification
