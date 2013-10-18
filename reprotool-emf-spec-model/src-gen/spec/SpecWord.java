/**
 */
package spec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Word</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link spec.SpecWord#getOriginal <em>Original</em>}</li>
 *   <li>{@link spec.SpecWord#getLemma <em>Lemma</em>}</li>
 *   <li>{@link spec.SpecWord#getPosTag <em>Pos Tag</em>}</li>
 *   <li>{@link spec.SpecWord#getSemanticParent <em>Semantic Parent</em>}</li>
 *   <li>{@link spec.SpecWord#getInvLinkDep <em>Inv Link Dep</em>}</li>
 *   <li>{@link spec.SpecWord#getSemanticParentRelation <em>Semantic Parent Relation</em>}</li>
 *   <li>{@link spec.SpecWord#getCorefRepMention <em>Coref Rep Mention</em>}</li>
 *   <li>{@link spec.SpecWord#getCorefMentions <em>Coref Mentions</em>}</li>
 *   <li>{@link spec.SpecWord#getRelatedEntityLink <em>Related Entity Link</em>}</li>
 *   <li>{@link spec.SpecWord#getSentence <em>Sentence</em>}</li>
 *   <li>{@link spec.SpecWord#getSemanticChildren <em>Semantic Children</em>}</li>
 *   <li>{@link spec.SpecWord#getInvLinkGov <em>Inv Link Gov</em>}</li>
 *   <li>{@link spec.SpecWord#getCorefRepOrSelf <em>Coref Rep Or Self</em>}</li>
 * </ul>
 * </p>
 *
 * @see spec.SpecPackage#getSpecWord()
 * @model
 * @generated
 */
public interface SpecWord extends EObject
{
	/**
	 * Returns the value of the '<em><b>Original</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original</em>' attribute.
	 * @see #setOriginal(String)
	 * @see spec.SpecPackage#getSpecWord_Original()
	 * @model required="true"
	 * @generated
	 */
	String getOriginal();

	/**
	 * Sets the value of the '{@link spec.SpecWord#getOriginal <em>Original</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original</em>' attribute.
	 * @see #getOriginal()
	 * @generated
	 */
	void setOriginal(String value);

	/**
	 * Returns the value of the '<em><b>Lemma</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lemma</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lemma</em>' attribute.
	 * @see #setLemma(String)
	 * @see spec.SpecPackage#getSpecWord_Lemma()
	 * @model required="true"
	 * @generated
	 */
	String getLemma();

	/**
	 * Sets the value of the '{@link spec.SpecWord#getLemma <em>Lemma</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lemma</em>' attribute.
	 * @see #getLemma()
	 * @generated
	 */
	void setLemma(String value);

	/**
	 * Returns the value of the '<em><b>Pos Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pos Tag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pos Tag</em>' attribute.
	 * @see #setPosTag(String)
	 * @see spec.SpecPackage#getSpecWord_PosTag()
	 * @model required="true"
	 * @generated
	 */
	String getPosTag();

	/**
	 * Sets the value of the '{@link spec.SpecWord#getPosTag <em>Pos Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pos Tag</em>' attribute.
	 * @see #getPosTag()
	 * @generated
	 */
	void setPosTag(String value);

	/**
	 * Returns the value of the '<em><b>Semantic Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Semantic Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semantic Parent</em>' reference.
	 * @see spec.SpecPackage#getSpecWord_SemanticParent()
	 * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	SpecWord getSemanticParent();

	/**
	 * Returns the value of the '<em><b>Inv Link Dep</b></em>' reference list.
	 * The list contents are of type {@link spec.WordDependency}.
	 * It is bidirectional and its opposite is '{@link spec.WordDependency#getLinkDep <em>Link Dep</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inv Link Dep</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inv Link Dep</em>' reference list.
	 * @see spec.SpecPackage#getSpecWord_InvLinkDep()
	 * @see spec.WordDependency#getLinkDep
	 * @model opposite="linkDep"
	 * @generated
	 */
	EList<WordDependency> getInvLinkDep();

	/**
	 * Returns the value of the '<em><b>Semantic Parent Relation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Semantic Parent Relation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semantic Parent Relation</em>' reference.
	 * @see spec.SpecPackage#getSpecWord_SemanticParentRelation()
	 * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	WordDependency getSemanticParentRelation();

	/**
	 * Returns the value of the '<em><b>Coref Rep Mention</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link spec.SpecWord#getCorefMentions <em>Coref Mentions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coref Rep Mention</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coref Rep Mention</em>' reference.
	 * @see #setCorefRepMention(SpecWord)
	 * @see spec.SpecPackage#getSpecWord_CorefRepMention()
	 * @see spec.SpecWord#getCorefMentions
	 * @model opposite="corefMentions"
	 * @generated
	 */
	SpecWord getCorefRepMention();

	/**
	 * Sets the value of the '{@link spec.SpecWord#getCorefRepMention <em>Coref Rep Mention</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coref Rep Mention</em>' reference.
	 * @see #getCorefRepMention()
	 * @generated
	 */
	void setCorefRepMention(SpecWord value);

	/**
	 * Returns the value of the '<em><b>Coref Mentions</b></em>' reference list.
	 * The list contents are of type {@link spec.SpecWord}.
	 * It is bidirectional and its opposite is '{@link spec.SpecWord#getCorefRepMention <em>Coref Rep Mention</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coref Mentions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coref Mentions</em>' reference list.
	 * @see spec.SpecPackage#getSpecWord_CorefMentions()
	 * @see spec.SpecWord#getCorefRepMention
	 * @model opposite="corefRepMention"
	 * @generated
	 */
	EList<SpecWord> getCorefMentions();

	/**
	 * Returns the value of the '<em><b>Related Entity Link</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link spec.EntityLink#getLinkedWords <em>Linked Words</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Related Entity Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Related Entity Link</em>' reference.
	 * @see #setRelatedEntityLink(EntityLink)
	 * @see spec.SpecPackage#getSpecWord_RelatedEntityLink()
	 * @see spec.EntityLink#getLinkedWords
	 * @model opposite="linkedWords"
	 * @generated
	 */
	EntityLink getRelatedEntityLink();

	/**
	 * Sets the value of the '{@link spec.SpecWord#getRelatedEntityLink <em>Related Entity Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Related Entity Link</em>' reference.
	 * @see #getRelatedEntityLink()
	 * @generated
	 */
	void setRelatedEntityLink(EntityLink value);

	/**
	 * Returns the value of the '<em><b>Sentence</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link spec.SpecSentence#getWords <em>Words</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sentence</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sentence</em>' container reference.
	 * @see #setSentence(SpecSentence)
	 * @see spec.SpecPackage#getSpecWord_Sentence()
	 * @see spec.SpecSentence#getWords
	 * @model opposite="words" required="true" transient="false"
	 * @generated
	 */
	SpecSentence getSentence();

	/**
	 * Sets the value of the '{@link spec.SpecWord#getSentence <em>Sentence</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sentence</em>' container reference.
	 * @see #getSentence()
	 * @generated
	 */
	void setSentence(SpecSentence value);

	/**
	 * Returns the value of the '<em><b>Semantic Children</b></em>' reference list.
	 * The list contents are of type {@link spec.SpecWord}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Semantic Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semantic Children</em>' reference list.
	 * @see spec.SpecPackage#getSpecWord_SemanticChildren()
	 * @model resolveProxies="false" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList<SpecWord> getSemanticChildren();

	/**
	 * Returns the value of the '<em><b>Inv Link Gov</b></em>' reference list.
	 * The list contents are of type {@link spec.WordDependency}.
	 * It is bidirectional and its opposite is '{@link spec.WordDependency#getLinkGov <em>Link Gov</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inv Link Gov</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inv Link Gov</em>' reference list.
	 * @see spec.SpecPackage#getSpecWord_InvLinkGov()
	 * @see spec.WordDependency#getLinkGov
	 * @model opposite="linkGov"
	 * @generated
	 */
	EList<WordDependency> getInvLinkGov();

	/**
	 * Returns the value of the '<em><b>Coref Rep Or Self</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coref Rep Or Self</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coref Rep Or Self</em>' reference.
	 * @see spec.SpecPackage#getSpecWord_CorefRepOrSelf()
	 * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	SpecWord getCorefRepOrSelf();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isRepresentativeCoref();

} // SpecWord
