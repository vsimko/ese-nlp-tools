/**
 */
package spec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.EntityLink#getEntLabel <em>Ent Label</em>}</li>
 *   <li>{@link spec.EntityLink#getLinkedWords <em>Linked Words</em>}</li>
 *   <li>{@link spec.EntityLink#getLinkedEntity <em>Linked Entity</em>}</li>
 *   <li>{@link spec.EntityLink#getEntType <em>Ent Type</em>}</li>
 *   <li>{@link spec.EntityLink#getSentence <em>Sentence</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getEntityLink()
 * @model
 * @generated
 */
public interface EntityLink extends EObject
{
	/**
	 * Returns the value of the '<em><b>Ent Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ent Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ent Label</em>' attribute.
	 * @see #setEntLabel(String)
	 * @see spec.SpecPackage#getEntityLink_EntLabel()
	 * @model
	 * @generated
	 */
	String getEntLabel();

	/**
	 * Sets the value of the '{@link spec.EntityLink#getEntLabel <em>Ent Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ent Label</em>' attribute.
	 * @see #getEntLabel()
	 * @generated
	 */
	void setEntLabel(String value);

	/**
	 * Returns the value of the '<em><b>Linked Words</b></em>' reference list.
	 * The list contents are of type {@link spec.SpecWord}.
	 * It is bidirectional and its opposite is '{@link spec.SpecWord#getRelatedEntityLink <em>Related Entity Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked Words</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linked Words</em>' reference list.
	 * @see spec.SpecPackage#getEntityLink_LinkedWords()
	 * @see spec.SpecWord#getRelatedEntityLink
	 * @model opposite="relatedEntityLink" required="true"
	 * @generated
	 */
	EList<SpecWord> getLinkedWords();

	/**
	 * Returns the value of the '<em><b>Linked Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linked Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linked Entity</em>' reference.
	 * @see #setLinkedEntity(ENamedElement)
	 * @see spec.SpecPackage#getEntityLink_LinkedEntity()
	 * @model type="spec.DomainEntity" required="true"
	 * @generated
	 */
	ENamedElement getLinkedEntity();

	/**
	 * Sets the value of the '{@link spec.EntityLink#getLinkedEntity <em>Linked Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked Entity</em>' reference.
	 * @see #getLinkedEntity()
	 * @generated
	 */
	void setLinkedEntity(ENamedElement value);

	/**
	 * Returns the value of the '<em><b>Ent Type</b></em>' attribute.
	 * The literals are from the enumeration {@link spec.DomainEntityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ent Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ent Type</em>' attribute.
	 * @see spec.DomainEntityType
	 * @see #setEntType(DomainEntityType)
	 * @see spec.SpecPackage#getEntityLink_EntType()
	 * @model
	 * @generated
	 */
	DomainEntityType getEntType();

	/**
	 * Sets the value of the '{@link spec.EntityLink#getEntType <em>Ent Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ent Type</em>' attribute.
	 * @see spec.DomainEntityType
	 * @see #getEntType()
	 * @generated
	 */
	void setEntType(DomainEntityType value);

	/**
	 * Returns the value of the '<em><b>Sentence</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link spec.SpecSentence#getEntityLinks <em>Entity Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sentence</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sentence</em>' container reference.
	 * @see #setSentence(SpecSentence)
	 * @see spec.SpecPackage#getEntityLink_Sentence()
	 * @see spec.SpecSentence#getEntityLinks
	 * @model opposite="entityLinks" required="true" transient="false"
	 * @generated
	 */
	SpecSentence getSentence();

	/**
	 * Sets the value of the '{@link spec.EntityLink#getSentence <em>Sentence</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sentence</em>' container reference.
	 * @see #getSentence()
	 * @generated
	 */
	void setSentence(SpecSentence value);

} // EntityLink
