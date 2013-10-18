/**
 */
package spec.impl;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import spec.DomainModel;
import spec.SpecPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link spec.impl.DomainModelImpl#getModelPackage <em>Model Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainModelImpl extends MinimalEObjectImpl.Container implements DomainModel
{
	/**
	 * The cached value of the '{@link #getModelPackage() <em>Model Package</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage modelPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainModelImpl()
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
		return SpecPackage.Literals.DOMAIN_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getModelPackage()
	{
		return modelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModelPackage(EPackage newModelPackage, NotificationChain msgs)
	{
		EPackage oldModelPackage = modelPackage;
		modelPackage = newModelPackage;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE, oldModelPackage, newModelPackage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelPackage(EPackage newModelPackage)
	{
		if (newModelPackage != modelPackage)
		{
			NotificationChain msgs = null;
			if (modelPackage != null)
				msgs = ((InternalEObject)modelPackage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE, null, msgs);
			if (newModelPackage != null)
				msgs = ((InternalEObject)newModelPackage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE, null, msgs);
			msgs = basicSetModelPackage(newModelPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE, newModelPackage, newModelPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ENamedElement getNamedElement(String elementName)
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
	public void rebuildNameIndex()
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
	public void loadFromXmiFile(String fileName) throws IOException
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE:
				return basicSetModelPackage(null, msgs);
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
			case SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE:
				return getModelPackage();
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
			case SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE:
				setModelPackage((EPackage)newValue);
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
			case SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE:
				setModelPackage((EPackage)null);
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
			case SpecPackage.DOMAIN_MODEL__MODEL_PACKAGE:
				return modelPackage != null;
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
			case SpecPackage.DOMAIN_MODEL___GET_NAMED_ELEMENT__STRING:
				return getNamedElement((String)arguments.get(0));
			case SpecPackage.DOMAIN_MODEL___REBUILD_NAME_INDEX:
				rebuildNameIndex();
				return null;
			case SpecPackage.DOMAIN_MODEL___LOAD_FROM_XMI_FILE__STRING:
				try
				{
					loadFromXmiFile((String)arguments.get(0));
					return null;
				}
				catch (Throwable throwable)
				{
					throw new InvocationTargetException(throwable);
				}
		}
		return super.eInvoke(operationID, arguments);
	}

} //DomainModelImpl
