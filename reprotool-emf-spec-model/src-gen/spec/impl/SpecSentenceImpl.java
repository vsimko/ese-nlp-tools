/**
 */
package spec.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import spec.EntityLink;
import spec.SpecPackage;
import spec.SpecSentence;
import spec.SpecWord;
import spec.WordDependency;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sentence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link spec.impl.SpecSentenceImpl#getWords <em>Words</em>}</li>
 *   <li>{@link spec.impl.SpecSentenceImpl#getTypedDependencies <em>Typed Dependencies</em>}</li>
 *   <li>{@link spec.impl.SpecSentenceImpl#getEntityLinks <em>Entity Links</em>}</li>
 *   <li>{@link spec.impl.SpecSentenceImpl#getSemanticRootWord <em>Semantic Root Word</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpecSentenceImpl extends MinimalEObjectImpl.Container implements SpecSentence
{
	/**
	 * The cached value of the '{@link #getWords() <em>Words</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWords()
	 * @generated
	 * @ordered
	 */
	protected EList<SpecWord> words;

	/**
	 * The cached value of the '{@link #getTypedDependencies() <em>Typed Dependencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypedDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<WordDependency> typedDependencies;

	/**
	 * The cached value of the '{@link #getEntityLinks() <em>Entity Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntityLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityLink> entityLinks;

	/**
	 * The cached value of the '{@link #getSemanticRootWord() <em>Semantic Root Word</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSemanticRootWord()
	 * @generated
	 * @ordered
	 */
	protected SpecWord semanticRootWord;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecSentenceImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return SpecPackage.Literals.SPEC_SENTENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecWord> getWords()
	{
		if (words == null)
		{
			words = new EObjectContainmentWithInverseEList<SpecWord>(SpecWord.class, this, SpecPackage.SPEC_SENTENCE__WORDS, SpecPackage.SPEC_WORD__SENTENCE);
		}
		return words;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WordDependency> getTypedDependencies()
	{
		if (typedDependencies == null)
		{
			typedDependencies = new EObjectContainmentEList<WordDependency>(WordDependency.class, this, SpecPackage.SPEC_SENTENCE__TYPED_DEPENDENCIES);
		}
		return typedDependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EntityLink> getEntityLinks()
	{
		if (entityLinks == null)
		{
			entityLinks = new EObjectContainmentWithInverseEList<EntityLink>(EntityLink.class, this, SpecPackage.SPEC_SENTENCE__ENTITY_LINKS, SpecPackage.ENTITY_LINK__SENTENCE);
		}
		return entityLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord getSemanticRootWord()
	{
		if (semanticRootWord != null && semanticRootWord.eIsProxy())
		{
			InternalEObject oldSemanticRootWord = (InternalEObject)semanticRootWord;
			semanticRootWord = (SpecWord)eResolveProxy(oldSemanticRootWord);
			if (semanticRootWord != oldSemanticRootWord)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecPackage.SPEC_SENTENCE__SEMANTIC_ROOT_WORD, oldSemanticRootWord, semanticRootWord));
			}
		}
		return semanticRootWord;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord basicGetSemanticRootWord()
	{
		return semanticRootWord;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSemanticRootWord(SpecWord newSemanticRootWord)
	{
		SpecWord oldSemanticRootWord = semanticRootWord;
		semanticRootWord = newSemanticRootWord;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_SENTENCE__SEMANTIC_ROOT_WORD, oldSemanticRootWord, semanticRootWord));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case SpecPackage.SPEC_SENTENCE__WORDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWords()).basicAdd(otherEnd, msgs);
			case SpecPackage.SPEC_SENTENCE__ENTITY_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEntityLinks()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case SpecPackage.SPEC_SENTENCE__WORDS:
				return ((InternalEList<?>)getWords()).basicRemove(otherEnd, msgs);
			case SpecPackage.SPEC_SENTENCE__TYPED_DEPENDENCIES:
				return ((InternalEList<?>)getTypedDependencies()).basicRemove(otherEnd, msgs);
			case SpecPackage.SPEC_SENTENCE__ENTITY_LINKS:
				return ((InternalEList<?>)getEntityLinks()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case SpecPackage.SPEC_SENTENCE__WORDS:
				return getWords();
			case SpecPackage.SPEC_SENTENCE__TYPED_DEPENDENCIES:
				return getTypedDependencies();
			case SpecPackage.SPEC_SENTENCE__ENTITY_LINKS:
				return getEntityLinks();
			case SpecPackage.SPEC_SENTENCE__SEMANTIC_ROOT_WORD:
				if (resolve) return getSemanticRootWord();
				return basicGetSemanticRootWord();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case SpecPackage.SPEC_SENTENCE__WORDS:
				getWords().clear();
				getWords().addAll((Collection<? extends SpecWord>)newValue);
				return;
			case SpecPackage.SPEC_SENTENCE__TYPED_DEPENDENCIES:
				getTypedDependencies().clear();
				getTypedDependencies().addAll((Collection<? extends WordDependency>)newValue);
				return;
			case SpecPackage.SPEC_SENTENCE__ENTITY_LINKS:
				getEntityLinks().clear();
				getEntityLinks().addAll((Collection<? extends EntityLink>)newValue);
				return;
			case SpecPackage.SPEC_SENTENCE__SEMANTIC_ROOT_WORD:
				setSemanticRootWord((SpecWord)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case SpecPackage.SPEC_SENTENCE__WORDS:
				getWords().clear();
				return;
			case SpecPackage.SPEC_SENTENCE__TYPED_DEPENDENCIES:
				getTypedDependencies().clear();
				return;
			case SpecPackage.SPEC_SENTENCE__ENTITY_LINKS:
				getEntityLinks().clear();
				return;
			case SpecPackage.SPEC_SENTENCE__SEMANTIC_ROOT_WORD:
				setSemanticRootWord((SpecWord)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case SpecPackage.SPEC_SENTENCE__WORDS:
				return words != null && !words.isEmpty();
			case SpecPackage.SPEC_SENTENCE__TYPED_DEPENDENCIES:
				return typedDependencies != null && !typedDependencies.isEmpty();
			case SpecPackage.SPEC_SENTENCE__ENTITY_LINKS:
				return entityLinks != null && !entityLinks.isEmpty();
			case SpecPackage.SPEC_SENTENCE__SEMANTIC_ROOT_WORD:
				return semanticRootWord != null;
		}
		return super.eIsSet(featureID);
	}

} //SpecSentenceImpl
