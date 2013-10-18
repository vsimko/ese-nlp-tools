/**
 */
package spec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Glossary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.Glossary#getTerms <em>Terms</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getGlossary()
 * @model
 * @generated
 */
public interface Glossary extends EObject
{
	/**
	 * Returns the value of the '<em><b>Terms</b></em>' containment reference list.
	 * The list contents are of type {@link spec.GlossaryTerm}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Terms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Terms</em>' containment reference list.
	 * @see spec.SpecPackage#getGlossary_Terms()
	 * @model containment="true"
	 * @generated
	 */
	EList<GlossaryTerm> getTerms();

} // Glossary
