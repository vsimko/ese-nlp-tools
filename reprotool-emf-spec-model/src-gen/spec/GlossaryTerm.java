/**
 */
package spec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Glossary Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.GlossaryTerm#getAliases <em>Aliases</em>}</li>
 *   <li>{@link spec.GlossaryTerm#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getGlossaryTerm()
 * @model
 * @generated
 */
public interface GlossaryTerm extends EObject
{
	/**
	 * Returns the value of the '<em><b>Aliases</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aliases</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aliases</em>' attribute list.
	 * @see spec.SpecPackage#getGlossaryTerm_Aliases()
	 * @model required="true"
	 * @generated
	 */
	EList<String> getAliases();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see spec.SpecPackage#getGlossaryTerm_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link spec.GlossaryTerm#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // GlossaryTerm
