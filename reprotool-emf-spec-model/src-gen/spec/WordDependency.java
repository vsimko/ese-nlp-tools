/**
 */
package spec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Word Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.WordDependency#getLabel <em>Label</em>}</li>
 *   <li>{@link spec.WordDependency#getLinkDep <em>Link Dep</em>}</li>
 *   <li>{@link spec.WordDependency#getLinkGov <em>Link Gov</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getWordDependency()
 * @model
 * @generated
 */
public interface WordDependency extends EObject
{
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see spec.SpecPackage#getWordDependency_Label()
	 * @model required="true"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link spec.WordDependency#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Link Dep</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link spec.SpecWord#getInvLinkDep <em>Inv Link Dep</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link Dep</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Dep</em>' reference.
	 * @see #setLinkDep(SpecWord)
	 * @see spec.SpecPackage#getWordDependency_LinkDep()
	 * @see spec.SpecWord#getInvLinkDep
	 * @model opposite="invLinkDep" required="true"
	 * @generated
	 */
	SpecWord getLinkDep();

	/**
	 * Sets the value of the '{@link spec.WordDependency#getLinkDep <em>Link Dep</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Dep</em>' reference.
	 * @see #getLinkDep()
	 * @generated
	 */
	void setLinkDep(SpecWord value);

	/**
	 * Returns the value of the '<em><b>Link Gov</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link spec.SpecWord#getInvLinkGov <em>Inv Link Gov</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link Gov</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Gov</em>' reference.
	 * @see #setLinkGov(SpecWord)
	 * @see spec.SpecPackage#getWordDependency_LinkGov()
	 * @see spec.SpecWord#getInvLinkGov
	 * @model opposite="invLinkGov" required="true"
	 * @generated
	 */
	SpecWord getLinkGov();

	/**
	 * Sets the value of the '{@link spec.WordDependency#getLinkGov <em>Link Gov</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Gov</em>' reference.
	 * @see #getLinkGov()
	 * @generated
	 */
	void setLinkGov(SpecWord value);

} // WordDependency
