/**
 */
package spec.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import spec.DomainEntityType;
import spec.EntityLink;
import spec.SpecPackage;
import spec.SpecSentence;
import spec.SpecWord;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link spec.impl.EntityLinkImpl#getEntLabel <em>Ent Label</em>}</li>
 *   <li>{@link spec.impl.EntityLinkImpl#getLinkedWords <em>Linked Words</em>}</li>
 *   <li>{@link spec.impl.EntityLinkImpl#getLinkedEntity <em>Linked Entity</em>}</li>
 *   <li>{@link spec.impl.EntityLinkImpl#getEntType <em>Ent Type</em>}</li>
 *   <li>{@link spec.impl.EntityLinkImpl#getSentence <em>Sentence</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EntityLinkImpl extends MinimalEObjectImpl.Container implements EntityLink
{
	/**
	 * The default value of the '{@link #getEntLabel() <em>Ent Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String ENT_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEntLabel() <em>Ent Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntLabel()
	 * @generated
	 * @ordered
	 */
	protected String entLabel = ENT_LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLinkedWords() <em>Linked Words</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedWords()
	 * @generated
	 * @ordered
	 */
	protected EList<SpecWord> linkedWords;

	/**
	 * The cached value of the '{@link #getLinkedEntity() <em>Linked Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkedEntity()
	 * @generated
	 * @ordered
	 */
	protected ENamedElement linkedEntity;

	/**
	 * The default value of the '{@link #getEntType() <em>Ent Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntType()
	 * @generated
	 * @ordered
	 */
	protected static final DomainEntityType ENT_TYPE_EDEFAULT = DomainEntityType.OTHER;

	/**
	 * The cached value of the '{@link #getEntType() <em>Ent Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntType()
	 * @generated
	 * @ordered
	 */
	protected DomainEntityType entType = ENT_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityLinkImpl()
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
		return SpecPackage.Literals.ENTITY_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEntLabel()
	{
		return entLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntLabel(String newEntLabel)
	{
		String oldEntLabel = entLabel;
		entLabel = newEntLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.ENTITY_LINK__ENT_LABEL, oldEntLabel, entLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecWord> getLinkedWords()
	{
		if (linkedWords == null)
		{
			linkedWords = new EObjectWithInverseResolvingEList<SpecWord>(SpecWord.class, this, SpecPackage.ENTITY_LINK__LINKED_WORDS, SpecPackage.SPEC_WORD__RELATED_ENTITY_LINK);
		}
		return linkedWords;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ENamedElement getLinkedEntity()
	{
		if (linkedEntity != null && ((EObject)linkedEntity).eIsProxy())
		{
			InternalEObject oldLinkedEntity = (InternalEObject)linkedEntity;
			linkedEntity = (ENamedElement)eResolveProxy(oldLinkedEntity);
			if (linkedEntity != oldLinkedEntity)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecPackage.ENTITY_LINK__LINKED_ENTITY, oldLinkedEntity, linkedEntity));
			}
		}
		return linkedEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ENamedElement basicGetLinkedEntity()
	{
		return linkedEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkedEntity(ENamedElement newLinkedEntity)
	{
		ENamedElement oldLinkedEntity = linkedEntity;
		linkedEntity = newLinkedEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.ENTITY_LINK__LINKED_ENTITY, oldLinkedEntity, linkedEntity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainEntityType getEntType()
	{
		return entType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntType(DomainEntityType newEntType)
	{
		DomainEntityType oldEntType = entType;
		entType = newEntType == null ? ENT_TYPE_EDEFAULT : newEntType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.ENTITY_LINK__ENT_TYPE, oldEntType, entType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecSentence getSentence()
	{
		if (eContainerFeatureID() != SpecPackage.ENTITY_LINK__SENTENCE) return null;
		return (SpecSentence)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSentence(SpecSentence newSentence, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newSentence, SpecPackage.ENTITY_LINK__SENTENCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSentence(SpecSentence newSentence)
	{
		if (newSentence != eInternalContainer() || (eContainerFeatureID() != SpecPackage.ENTITY_LINK__SENTENCE && newSentence != null))
		{
			if (EcoreUtil.isAncestor(this, newSentence))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSentence != null)
				msgs = ((InternalEObject)newSentence).eInverseAdd(this, SpecPackage.SPEC_SENTENCE__ENTITY_LINKS, SpecSentence.class, msgs);
			msgs = basicSetSentence(newSentence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.ENTITY_LINK__SENTENCE, newSentence, newSentence));
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
			case SpecPackage.ENTITY_LINK__LINKED_WORDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLinkedWords()).basicAdd(otherEnd, msgs);
			case SpecPackage.ENTITY_LINK__SENTENCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSentence((SpecSentence)otherEnd, msgs);
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
			case SpecPackage.ENTITY_LINK__LINKED_WORDS:
				return ((InternalEList<?>)getLinkedWords()).basicRemove(otherEnd, msgs);
			case SpecPackage.ENTITY_LINK__SENTENCE:
				return basicSetSentence(null, msgs);
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
			case SpecPackage.ENTITY_LINK__SENTENCE:
				return eInternalContainer().eInverseRemove(this, SpecPackage.SPEC_SENTENCE__ENTITY_LINKS, SpecSentence.class, msgs);
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
			case SpecPackage.ENTITY_LINK__ENT_LABEL:
				return getEntLabel();
			case SpecPackage.ENTITY_LINK__LINKED_WORDS:
				return getLinkedWords();
			case SpecPackage.ENTITY_LINK__LINKED_ENTITY:
				if (resolve) return getLinkedEntity();
				return basicGetLinkedEntity();
			case SpecPackage.ENTITY_LINK__ENT_TYPE:
				return getEntType();
			case SpecPackage.ENTITY_LINK__SENTENCE:
				return getSentence();
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
			case SpecPackage.ENTITY_LINK__ENT_LABEL:
				setEntLabel((String)newValue);
				return;
			case SpecPackage.ENTITY_LINK__LINKED_WORDS:
				getLinkedWords().clear();
				getLinkedWords().addAll((Collection<? extends SpecWord>)newValue);
				return;
			case SpecPackage.ENTITY_LINK__LINKED_ENTITY:
				setLinkedEntity((ENamedElement)newValue);
				return;
			case SpecPackage.ENTITY_LINK__ENT_TYPE:
				setEntType((DomainEntityType)newValue);
				return;
			case SpecPackage.ENTITY_LINK__SENTENCE:
				setSentence((SpecSentence)newValue);
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
			case SpecPackage.ENTITY_LINK__ENT_LABEL:
				setEntLabel(ENT_LABEL_EDEFAULT);
				return;
			case SpecPackage.ENTITY_LINK__LINKED_WORDS:
				getLinkedWords().clear();
				return;
			case SpecPackage.ENTITY_LINK__LINKED_ENTITY:
				setLinkedEntity((ENamedElement)null);
				return;
			case SpecPackage.ENTITY_LINK__ENT_TYPE:
				setEntType(ENT_TYPE_EDEFAULT);
				return;
			case SpecPackage.ENTITY_LINK__SENTENCE:
				setSentence((SpecSentence)null);
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
			case SpecPackage.ENTITY_LINK__ENT_LABEL:
				return ENT_LABEL_EDEFAULT == null ? entLabel != null : !ENT_LABEL_EDEFAULT.equals(entLabel);
			case SpecPackage.ENTITY_LINK__LINKED_WORDS:
				return linkedWords != null && !linkedWords.isEmpty();
			case SpecPackage.ENTITY_LINK__LINKED_ENTITY:
				return linkedEntity != null;
			case SpecPackage.ENTITY_LINK__ENT_TYPE:
				return entType != ENT_TYPE_EDEFAULT;
			case SpecPackage.ENTITY_LINK__SENTENCE:
				return getSentence() != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (entLabel: ");
		result.append(entLabel);
		result.append(", entType: ");
		result.append(entType);
		result.append(')');
		return result.toString();
	}

} //EntityLinkImpl
