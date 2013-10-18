/**
 */
package spec.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import spec.EntityLink;
import spec.SpecPackage;
import spec.SpecSentence;
import spec.SpecWord;
import spec.WordDependency;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Word</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link spec.impl.SpecWordImpl#getOriginal <em>Original</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getLemma <em>Lemma</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getPosTag <em>Pos Tag</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getSemanticParent <em>Semantic Parent</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getInvLinkDep <em>Inv Link Dep</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getSemanticParentRelation <em>Semantic Parent Relation</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getCorefRepMention <em>Coref Rep Mention</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getCorefMentions <em>Coref Mentions</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getRelatedEntityLink <em>Related Entity Link</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getSentence <em>Sentence</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getSemanticChildren <em>Semantic Children</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getInvLinkGov <em>Inv Link Gov</em>}</li>
 *   <li>{@link spec.impl.SpecWordImpl#getCorefRepOrSelf <em>Coref Rep Or Self</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpecWordImpl extends MinimalEObjectImpl.Container implements SpecWord
{
	/**
	 * The default value of the '{@link #getOriginal() <em>Original</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginal()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginal() <em>Original</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginal()
	 * @generated
	 * @ordered
	 */
	protected String original = ORIGINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getLemma() <em>Lemma</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLemma()
	 * @generated
	 * @ordered
	 */
	protected static final String LEMMA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLemma() <em>Lemma</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLemma()
	 * @generated
	 * @ordered
	 */
	protected String lemma = LEMMA_EDEFAULT;

	/**
	 * The default value of the '{@link #getPosTag() <em>Pos Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosTag()
	 * @generated
	 * @ordered
	 */
	protected static final String POS_TAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPosTag() <em>Pos Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosTag()
	 * @generated
	 * @ordered
	 */
	protected String posTag = POS_TAG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInvLinkDep() <em>Inv Link Dep</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvLinkDep()
	 * @generated
	 * @ordered
	 */
	protected EList<WordDependency> invLinkDep;

	/**
	 * The cached value of the '{@link #getCorefRepMention() <em>Coref Rep Mention</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorefRepMention()
	 * @generated
	 * @ordered
	 */
	protected SpecWord corefRepMention;

	/**
	 * The cached value of the '{@link #getCorefMentions() <em>Coref Mentions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorefMentions()
	 * @generated
	 * @ordered
	 */
	protected EList<SpecWord> corefMentions;

	/**
	 * The cached value of the '{@link #getRelatedEntityLink() <em>Related Entity Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedEntityLink()
	 * @generated
	 * @ordered
	 */
	protected EntityLink relatedEntityLink;

	/**
	 * The cached value of the '{@link #getInvLinkGov() <em>Inv Link Gov</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvLinkGov()
	 * @generated
	 * @ordered
	 */
	protected EList<WordDependency> invLinkGov;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecWordImpl()
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
		return SpecPackage.Literals.SPEC_WORD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOriginal()
	{
		return original;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOriginal(String newOriginal)
	{
		String oldOriginal = original;
		original = newOriginal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__ORIGINAL, oldOriginal, original));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLemma()
	{
		return lemma;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLemma(String newLemma)
	{
		String oldLemma = lemma;
		lemma = newLemma;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__LEMMA, oldLemma, lemma));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPosTag()
	{
		return posTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosTag(String newPosTag)
	{
		String oldPosTag = posTag;
		posTag = newPosTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__POS_TAG, oldPosTag, posTag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord getSemanticParent()
	{
		// TODO: implement this method to return the 'Semantic Parent' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WordDependency> getInvLinkDep()
	{
		if (invLinkDep == null)
		{
			invLinkDep = new EObjectWithInverseResolvingEList<WordDependency>(WordDependency.class, this, SpecPackage.SPEC_WORD__INV_LINK_DEP, SpecPackage.WORD_DEPENDENCY__LINK_DEP);
		}
		return invLinkDep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WordDependency getSemanticParentRelation()
	{
		// TODO: implement this method to return the 'Semantic Parent Relation' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord getCorefRepMention()
	{
		if (corefRepMention != null && corefRepMention.eIsProxy())
		{
			InternalEObject oldCorefRepMention = (InternalEObject)corefRepMention;
			corefRepMention = (SpecWord)eResolveProxy(oldCorefRepMention);
			if (corefRepMention != oldCorefRepMention)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecPackage.SPEC_WORD__COREF_REP_MENTION, oldCorefRepMention, corefRepMention));
			}
		}
		return corefRepMention;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord basicGetCorefRepMention()
	{
		return corefRepMention;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCorefRepMention(SpecWord newCorefRepMention, NotificationChain msgs)
	{
		SpecWord oldCorefRepMention = corefRepMention;
		corefRepMention = newCorefRepMention;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__COREF_REP_MENTION, oldCorefRepMention, newCorefRepMention);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorefRepMention(SpecWord newCorefRepMention)
	{
		if (newCorefRepMention != corefRepMention)
		{
			NotificationChain msgs = null;
			if (corefRepMention != null)
				msgs = ((InternalEObject)corefRepMention).eInverseRemove(this, SpecPackage.SPEC_WORD__COREF_MENTIONS, SpecWord.class, msgs);
			if (newCorefRepMention != null)
				msgs = ((InternalEObject)newCorefRepMention).eInverseAdd(this, SpecPackage.SPEC_WORD__COREF_MENTIONS, SpecWord.class, msgs);
			msgs = basicSetCorefRepMention(newCorefRepMention, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__COREF_REP_MENTION, newCorefRepMention, newCorefRepMention));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecWord> getCorefMentions()
	{
		if (corefMentions == null)
		{
			corefMentions = new EObjectWithInverseResolvingEList<SpecWord>(SpecWord.class, this, SpecPackage.SPEC_WORD__COREF_MENTIONS, SpecPackage.SPEC_WORD__COREF_REP_MENTION);
		}
		return corefMentions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityLink getRelatedEntityLink()
	{
		if (relatedEntityLink != null && relatedEntityLink.eIsProxy())
		{
			InternalEObject oldRelatedEntityLink = (InternalEObject)relatedEntityLink;
			relatedEntityLink = (EntityLink)eResolveProxy(oldRelatedEntityLink);
			if (relatedEntityLink != oldRelatedEntityLink)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK, oldRelatedEntityLink, relatedEntityLink));
			}
		}
		return relatedEntityLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityLink basicGetRelatedEntityLink()
	{
		return relatedEntityLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelatedEntityLink(EntityLink newRelatedEntityLink, NotificationChain msgs)
	{
		EntityLink oldRelatedEntityLink = relatedEntityLink;
		relatedEntityLink = newRelatedEntityLink;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK, oldRelatedEntityLink, newRelatedEntityLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelatedEntityLink(EntityLink newRelatedEntityLink)
	{
		if (newRelatedEntityLink != relatedEntityLink)
		{
			NotificationChain msgs = null;
			if (relatedEntityLink != null)
				msgs = ((InternalEObject)relatedEntityLink).eInverseRemove(this, SpecPackage.ENTITY_LINK__LINKED_WORDS, EntityLink.class, msgs);
			if (newRelatedEntityLink != null)
				msgs = ((InternalEObject)newRelatedEntityLink).eInverseAdd(this, SpecPackage.ENTITY_LINK__LINKED_WORDS, EntityLink.class, msgs);
			msgs = basicSetRelatedEntityLink(newRelatedEntityLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK, newRelatedEntityLink, newRelatedEntityLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecSentence getSentence()
	{
		if (eContainerFeatureID() != SpecPackage.SPEC_WORD__SENTENCE) return null;
		return (SpecSentence)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSentence(SpecSentence newSentence, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newSentence, SpecPackage.SPEC_WORD__SENTENCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSentence(SpecSentence newSentence)
	{
		if (newSentence != eInternalContainer() || (eContainerFeatureID() != SpecPackage.SPEC_WORD__SENTENCE && newSentence != null))
		{
			if (EcoreUtil.isAncestor(this, newSentence))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSentence != null)
				msgs = ((InternalEObject)newSentence).eInverseAdd(this, SpecPackage.SPEC_SENTENCE__WORDS, SpecSentence.class, msgs);
			msgs = basicSetSentence(newSentence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPEC_WORD__SENTENCE, newSentence, newSentence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecWord> getSemanticChildren()
	{
		// TODO: implement this method to return the 'Semantic Children' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WordDependency> getInvLinkGov()
	{
		if (invLinkGov == null)
		{
			invLinkGov = new EObjectWithInverseResolvingEList<WordDependency>(WordDependency.class, this, SpecPackage.SPEC_WORD__INV_LINK_GOV, SpecPackage.WORD_DEPENDENCY__LINK_GOV);
		}
		return invLinkGov;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord getCorefRepOrSelf()
	{
		// TODO: implement this method to return the 'Coref Rep Or Self' reference
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRepresentativeCoref()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case SpecPackage.SPEC_WORD__INV_LINK_DEP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInvLinkDep()).basicAdd(otherEnd, msgs);
			case SpecPackage.SPEC_WORD__COREF_REP_MENTION:
				if (corefRepMention != null)
					msgs = ((InternalEObject)corefRepMention).eInverseRemove(this, SpecPackage.SPEC_WORD__COREF_MENTIONS, SpecWord.class, msgs);
				return basicSetCorefRepMention((SpecWord)otherEnd, msgs);
			case SpecPackage.SPEC_WORD__COREF_MENTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCorefMentions()).basicAdd(otherEnd, msgs);
			case SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK:
				if (relatedEntityLink != null)
					msgs = ((InternalEObject)relatedEntityLink).eInverseRemove(this, SpecPackage.ENTITY_LINK__LINKED_WORDS, EntityLink.class, msgs);
				return basicSetRelatedEntityLink((EntityLink)otherEnd, msgs);
			case SpecPackage.SPEC_WORD__SENTENCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSentence((SpecSentence)otherEnd, msgs);
			case SpecPackage.SPEC_WORD__INV_LINK_GOV:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInvLinkGov()).basicAdd(otherEnd, msgs);
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
			case SpecPackage.SPEC_WORD__INV_LINK_DEP:
				return ((InternalEList<?>)getInvLinkDep()).basicRemove(otherEnd, msgs);
			case SpecPackage.SPEC_WORD__COREF_REP_MENTION:
				return basicSetCorefRepMention(null, msgs);
			case SpecPackage.SPEC_WORD__COREF_MENTIONS:
				return ((InternalEList<?>)getCorefMentions()).basicRemove(otherEnd, msgs);
			case SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK:
				return basicSetRelatedEntityLink(null, msgs);
			case SpecPackage.SPEC_WORD__SENTENCE:
				return basicSetSentence(null, msgs);
			case SpecPackage.SPEC_WORD__INV_LINK_GOV:
				return ((InternalEList<?>)getInvLinkGov()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch (eContainerFeatureID())
		{
			case SpecPackage.SPEC_WORD__SENTENCE:
				return eInternalContainer().eInverseRemove(this, SpecPackage.SPEC_SENTENCE__WORDS, SpecSentence.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case SpecPackage.SPEC_WORD__ORIGINAL:
				return getOriginal();
			case SpecPackage.SPEC_WORD__LEMMA:
				return getLemma();
			case SpecPackage.SPEC_WORD__POS_TAG:
				return getPosTag();
			case SpecPackage.SPEC_WORD__SEMANTIC_PARENT:
				return getSemanticParent();
			case SpecPackage.SPEC_WORD__INV_LINK_DEP:
				return getInvLinkDep();
			case SpecPackage.SPEC_WORD__SEMANTIC_PARENT_RELATION:
				return getSemanticParentRelation();
			case SpecPackage.SPEC_WORD__COREF_REP_MENTION:
				if (resolve) return getCorefRepMention();
				return basicGetCorefRepMention();
			case SpecPackage.SPEC_WORD__COREF_MENTIONS:
				return getCorefMentions();
			case SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK:
				if (resolve) return getRelatedEntityLink();
				return basicGetRelatedEntityLink();
			case SpecPackage.SPEC_WORD__SENTENCE:
				return getSentence();
			case SpecPackage.SPEC_WORD__SEMANTIC_CHILDREN:
				return getSemanticChildren();
			case SpecPackage.SPEC_WORD__INV_LINK_GOV:
				return getInvLinkGov();
			case SpecPackage.SPEC_WORD__COREF_REP_OR_SELF:
				return getCorefRepOrSelf();
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
			case SpecPackage.SPEC_WORD__ORIGINAL:
				setOriginal((String)newValue);
				return;
			case SpecPackage.SPEC_WORD__LEMMA:
				setLemma((String)newValue);
				return;
			case SpecPackage.SPEC_WORD__POS_TAG:
				setPosTag((String)newValue);
				return;
			case SpecPackage.SPEC_WORD__INV_LINK_DEP:
				getInvLinkDep().clear();
				getInvLinkDep().addAll((Collection<? extends WordDependency>)newValue);
				return;
			case SpecPackage.SPEC_WORD__COREF_REP_MENTION:
				setCorefRepMention((SpecWord)newValue);
				return;
			case SpecPackage.SPEC_WORD__COREF_MENTIONS:
				getCorefMentions().clear();
				getCorefMentions().addAll((Collection<? extends SpecWord>)newValue);
				return;
			case SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK:
				setRelatedEntityLink((EntityLink)newValue);
				return;
			case SpecPackage.SPEC_WORD__SENTENCE:
				setSentence((SpecSentence)newValue);
				return;
			case SpecPackage.SPEC_WORD__INV_LINK_GOV:
				getInvLinkGov().clear();
				getInvLinkGov().addAll((Collection<? extends WordDependency>)newValue);
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
			case SpecPackage.SPEC_WORD__ORIGINAL:
				setOriginal(ORIGINAL_EDEFAULT);
				return;
			case SpecPackage.SPEC_WORD__LEMMA:
				setLemma(LEMMA_EDEFAULT);
				return;
			case SpecPackage.SPEC_WORD__POS_TAG:
				setPosTag(POS_TAG_EDEFAULT);
				return;
			case SpecPackage.SPEC_WORD__INV_LINK_DEP:
				getInvLinkDep().clear();
				return;
			case SpecPackage.SPEC_WORD__COREF_REP_MENTION:
				setCorefRepMention((SpecWord)null);
				return;
			case SpecPackage.SPEC_WORD__COREF_MENTIONS:
				getCorefMentions().clear();
				return;
			case SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK:
				setRelatedEntityLink((EntityLink)null);
				return;
			case SpecPackage.SPEC_WORD__SENTENCE:
				setSentence((SpecSentence)null);
				return;
			case SpecPackage.SPEC_WORD__INV_LINK_GOV:
				getInvLinkGov().clear();
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
			case SpecPackage.SPEC_WORD__ORIGINAL:
				return ORIGINAL_EDEFAULT == null ? original != null : !ORIGINAL_EDEFAULT.equals(original);
			case SpecPackage.SPEC_WORD__LEMMA:
				return LEMMA_EDEFAULT == null ? lemma != null : !LEMMA_EDEFAULT.equals(lemma);
			case SpecPackage.SPEC_WORD__POS_TAG:
				return POS_TAG_EDEFAULT == null ? posTag != null : !POS_TAG_EDEFAULT.equals(posTag);
			case SpecPackage.SPEC_WORD__SEMANTIC_PARENT:
				return getSemanticParent() != null;
			case SpecPackage.SPEC_WORD__INV_LINK_DEP:
				return invLinkDep != null && !invLinkDep.isEmpty();
			case SpecPackage.SPEC_WORD__SEMANTIC_PARENT_RELATION:
				return getSemanticParentRelation() != null;
			case SpecPackage.SPEC_WORD__COREF_REP_MENTION:
				return corefRepMention != null;
			case SpecPackage.SPEC_WORD__COREF_MENTIONS:
				return corefMentions != null && !corefMentions.isEmpty();
			case SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK:
				return relatedEntityLink != null;
			case SpecPackage.SPEC_WORD__SENTENCE:
				return getSentence() != null;
			case SpecPackage.SPEC_WORD__SEMANTIC_CHILDREN:
				return !getSemanticChildren().isEmpty();
			case SpecPackage.SPEC_WORD__INV_LINK_GOV:
				return invLinkGov != null && !invLinkGov.isEmpty();
			case SpecPackage.SPEC_WORD__COREF_REP_OR_SELF:
				return getCorefRepOrSelf() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case SpecPackage.SPEC_WORD___IS_REPRESENTATIVE_COREF:
				return isRepresentativeCoref();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (original: ");
		result.append(original);
		result.append(", lemma: ");
		result.append(lemma);
		result.append(", posTag: ");
		result.append(posTag);
		result.append(')');
		return result.toString();
	}

} //SpecWordImpl
