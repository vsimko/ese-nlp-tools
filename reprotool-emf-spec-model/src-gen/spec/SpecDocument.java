/**
 */
package spec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.SpecDocument#getSentences <em>Sentences</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getSpecDocument()
 * @model
 * @generated
 */
public interface SpecDocument extends EObject
{
	/**
	 * Returns the value of the '<em><b>Sentences</b></em>' containment reference list.
	 * The list contents are of type {@link spec.SpecSentence}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sentences</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sentences</em>' containment reference list.
	 * @see spec.SpecPackage#getSpecDocument_Sentences()
	 * @model containment="true"
	 * @generated
	 */
	EList<SpecSentence> getSentences();

} // SpecDocument
