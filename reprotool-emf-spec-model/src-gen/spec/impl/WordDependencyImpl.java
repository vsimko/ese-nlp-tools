/**
 */
package spec.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import spec.SpecPackage;
import spec.SpecWord;
import spec.WordDependency;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Word Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link spec.impl.WordDependencyImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link spec.impl.WordDependencyImpl#getLinkDep <em>Link Dep</em>}</li>
 *   <li>{@link spec.impl.WordDependencyImpl#getLinkGov <em>Link Gov</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WordDependencyImpl extends MinimalEObjectImpl.Container implements WordDependency
{
	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLinkDep() <em>Link Dep</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkDep()
	 * @generated
	 * @ordered
	 */
	protected SpecWord linkDep;

	/**
	 * The cached value of the '{@link #getLinkGov() <em>Link Gov</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkGov()
	 * @generated
	 * @ordered
	 */
	protected SpecWord linkGov;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WordDependencyImpl()
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
		return SpecPackage.Literals.WORD_DEPENDENCY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel)
	{
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.WORD_DEPENDENCY__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord getLinkDep()
	{
		if (linkDep != null && linkDep.eIsProxy())
		{
			InternalEObject oldLinkDep = (InternalEObject)linkDep;
			linkDep = (SpecWord)eResolveProxy(oldLinkDep);
			if (linkDep != oldLinkDep)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecPackage.WORD_DEPENDENCY__LINK_DEP, oldLinkDep, linkDep));
			}
		}
		return linkDep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord basicGetLinkDep()
	{
		return linkDep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLinkDep(SpecWord newLinkDep, NotificationChain msgs)
	{
		SpecWord oldLinkDep = linkDep;
		linkDep = newLinkDep;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecPackage.WORD_DEPENDENCY__LINK_DEP, oldLinkDep, newLinkDep);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkDep(SpecWord newLinkDep)
	{
		if (newLinkDep != linkDep)
		{
			NotificationChain msgs = null;
			if (linkDep != null)
				msgs = ((InternalEObject)linkDep).eInverseRemove(this, SpecPackage.SPEC_WORD__INV_LINK_DEP, SpecWord.class, msgs);
			if (newLinkDep != null)
				msgs = ((InternalEObject)newLinkDep).eInverseAdd(this, SpecPackage.SPEC_WORD__INV_LINK_DEP, SpecWord.class, msgs);
			msgs = basicSetLinkDep(newLinkDep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.WORD_DEPENDENCY__LINK_DEP, newLinkDep, newLinkDep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord getLinkGov()
	{
		if (linkGov != null && linkGov.eIsProxy())
		{
			InternalEObject oldLinkGov = (InternalEObject)linkGov;
			linkGov = (SpecWord)eResolveProxy(oldLinkGov);
			if (linkGov != oldLinkGov)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SpecPackage.WORD_DEPENDENCY__LINK_GOV, oldLinkGov, linkGov));
			}
		}
		return linkGov;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord basicGetLinkGov()
	{
		return linkGov;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLinkGov(SpecWord newLinkGov, NotificationChain msgs)
	{
		SpecWord oldLinkGov = linkGov;
		linkGov = newLinkGov;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecPackage.WORD_DEPENDENCY__LINK_GOV, oldLinkGov, newLinkGov);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkGov(SpecWord newLinkGov)
	{
		if (newLinkGov != linkGov)
		{
			NotificationChain msgs = null;
			if (linkGov != null)
				msgs = ((InternalEObject)linkGov).eInverseRemove(this, SpecPackage.SPEC_WORD__INV_LINK_GOV, SpecWord.class, msgs);
			if (newLinkGov != null)
				msgs = ((InternalEObject)newLinkGov).eInverseAdd(this, SpecPackage.SPEC_WORD__INV_LINK_GOV, SpecWord.class, msgs);
			msgs = basicSetLinkGov(newLinkGov, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.WORD_DEPENDENCY__LINK_GOV, newLinkGov, newLinkGov));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case SpecPackage.WORD_DEPENDENCY__LINK_DEP:
				if (linkDep != null)
					msgs = ((InternalEObject)linkDep).eInverseRemove(this, SpecPackage.SPEC_WORD__INV_LINK_DEP, SpecWord.class, msgs);
				return basicSetLinkDep((SpecWord)otherEnd, msgs);
			case SpecPackage.WORD_DEPENDENCY__LINK_GOV:
				if (linkGov != null)
					msgs = ((InternalEObject)linkGov).eInverseRemove(this, SpecPackage.SPEC_WORD__INV_LINK_GOV, SpecWord.class, msgs);
				return basicSetLinkGov((SpecWord)otherEnd, msgs);
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
			case SpecPackage.WORD_DEPENDENCY__LINK_DEP:
				return basicSetLinkDep(null, msgs);
			case SpecPackage.WORD_DEPENDENCY__LINK_GOV:
				return basicSetLinkGov(null, msgs);
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
			case SpecPackage.WORD_DEPENDENCY__LABEL:
				return getLabel();
			case SpecPackage.WORD_DEPENDENCY__LINK_DEP:
				if (resolve) return getLinkDep();
				return basicGetLinkDep();
			case SpecPackage.WORD_DEPENDENCY__LINK_GOV:
				if (resolve) return getLinkGov();
				return basicGetLinkGov();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case SpecPackage.WORD_DEPENDENCY__LABEL:
				setLabel((String)newValue);
				return;
			case SpecPackage.WORD_DEPENDENCY__LINK_DEP:
				setLinkDep((SpecWord)newValue);
				return;
			case SpecPackage.WORD_DEPENDENCY__LINK_GOV:
				setLinkGov((SpecWord)newValue);
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
			case SpecPackage.WORD_DEPENDENCY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SpecPackage.WORD_DEPENDENCY__LINK_DEP:
				setLinkDep((SpecWord)null);
				return;
			case SpecPackage.WORD_DEPENDENCY__LINK_GOV:
				setLinkGov((SpecWord)null);
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
			case SpecPackage.WORD_DEPENDENCY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SpecPackage.WORD_DEPENDENCY__LINK_DEP:
				return linkDep != null;
			case SpecPackage.WORD_DEPENDENCY__LINK_GOV:
				return linkGov != null;
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
		result.append(" (label: ");
		result.append(label);
		result.append(')');
		return result.toString();
	}

} //WordDependencyImpl
