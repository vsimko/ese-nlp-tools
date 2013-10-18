/**
 */
package spec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.SpecSentence#getWords <em>Words</em>}</li>
 *   <li>{@link spec.SpecSentence#getTypedDependencies <em>Typed Dependencies</em>}</li>
 *   <li>{@link spec.SpecSentence#getEntityLinks <em>Entity Links</em>}</li>
 *   <li>{@link spec.SpecSentence#getSemanticRootWord <em>Semantic Root Word</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getSpecSentence()
 * @model
 * @generated
 */
public interface SpecSentence extends EObject
{
	/**
	 * Returns the value of the '<em><b>Words</b></em>' containment reference list.
	 * The list contents are of type {@link spec.SpecWord}.
	 * It is bidirectional and its opposite is '{@link spec.SpecWord#getSentence <em>Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Words</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Words</em>' containment reference list.
	 * @see spec.SpecPackage#getSpecSentence_Words()
	 * @see spec.SpecWord#getSentence
	 * @model opposite="sentence" containment="true"
	 * @generated
	 */
	EList<SpecWord> getWords();

	/**
	 * Returns the value of the '<em><b>Typed Dependencies</b></em>' containment reference list.
	 * The list contents are of type {@link spec.WordDependency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typed Dependencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typed Dependencies</em>' containment reference list.
	 * @see spec.SpecPackage#getSpecSentence_TypedDependencies()
	 * @model containment="true"
	 * @generated
	 */
	EList<WordDependency> getTypedDependencies();

	/**
	 * Returns the value of the '<em><b>Entity Links</b></em>' containment reference list.
	 * The list contents are of type {@link spec.EntityLink}.
	 * It is bidirectional and its opposite is '{@link spec.EntityLink#getSentence <em>Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Links</em>' containment reference list.
	 * @see spec.SpecPackage#getSpecSentence_EntityLinks()
	 * @see spec.EntityLink#getSentence
	 * @model opposite="sentence" containment="true"
	 * @generated
	 */
	EList<EntityLink> getEntityLinks();

	/**
	 * Returns the value of the '<em><b>Semantic Root Word</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Semantic Root Word</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semantic Root Word</em>' reference.
	 * @see #setSemanticRootWord(SpecWord)
	 * @see spec.SpecPackage#getSpecSentence_SemanticRootWord()
	 * @model required="true"
	 * @generated
	 */
	SpecWord getSemanticRootWord();

	/**
	 * Sets the value of the '{@link spec.SpecSentence#getSemanticRootWord <em>Semantic Root Word</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Semantic Root Word</em>' reference.
	 * @see #getSemanticRootWord()
	 * @generated
	 */
	void setSemanticRootWord(SpecWord value);

} // SpecSentence
