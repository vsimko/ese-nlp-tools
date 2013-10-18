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
import org.eclipse.emf.ecore.util.InternalEList;

import spec.DomainModel;
import spec.Glossary;
import spec.SpecDocument;
import spec.SpecPackage;
import spec.Specification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link spec.impl.SpecificationImpl#getDocuments <em>Documents</em>}</li>
 *   <li>{@link spec.impl.SpecificationImpl#getGlossary <em>Glossary</em>}</li>
 *   <li>{@link spec.impl.SpecificationImpl#getDomainModel <em>Domain Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpecificationImpl extends MinimalEObjectImpl.Container implements Specification
{
	/**
	 * The cached value of the '{@link #getDocuments() <em>Documents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocuments()
	 * @generated
	 * @ordered
	 */
	protected EList<SpecDocument> documents;

	/**
	 * The cached value of the '{@link #getGlossary() <em>Glossary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlossary()
	 * @generated
	 * @ordered
	 */
	protected Glossary glossary;

	/**
	 * The cached value of the '{@link #getDomainModel() <em>Domain Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainModel()
	 * @generated
	 * @ordered
	 */
	protected DomainModel domainModel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecificationImpl()
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
		return SpecPackage.Literals.SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpecDocument> getDocuments()
	{
		if (documents == null)
		{
			documents = new EObjectContainmentEList<SpecDocument>(SpecDocument.class, this, SpecPackage.SPECIFICATION__DOCUMENTS);
		}
		return documents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Glossary getGlossary()
	{
		return glossary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGlossary(Glossary newGlossary, NotificationChain msgs)
	{
		Glossary oldGlossary = glossary;
		glossary = newGlossary;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecPackage.SPECIFICATION__GLOSSARY, oldGlossary, newGlossary);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGlossary(Glossary newGlossary)
	{
		if (newGlossary != glossary)
		{
			NotificationChain msgs = null;
			if (glossary != null)
				msgs = ((InternalEObject)glossary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecPackage.SPECIFICATION__GLOSSARY, null, msgs);
			if (newGlossary != null)
				msgs = ((InternalEObject)newGlossary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecPackage.SPECIFICATION__GLOSSARY, null, msgs);
			msgs = basicSetGlossary(newGlossary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPECIFICATION__GLOSSARY, newGlossary, newGlossary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainModel getDomainModel()
	{
		return domainModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDomainModel(DomainModel newDomainModel, NotificationChain msgs)
	{
		DomainModel oldDomainModel = domainModel;
		domainModel = newDomainModel;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SpecPackage.SPECIFICATION__DOMAIN_MODEL, oldDomainModel, newDomainModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainModel(DomainModel newDomainModel)
	{
		if (newDomainModel != domainModel)
		{
			NotificationChain msgs = null;
			if (domainModel != null)
				msgs = ((InternalEObject)domainModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SpecPackage.SPECIFICATION__DOMAIN_MODEL, null, msgs);
			if (newDomainModel != null)
				msgs = ((InternalEObject)newDomainModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SpecPackage.SPECIFICATION__DOMAIN_MODEL, null, msgs);
			msgs = basicSetDomainModel(newDomainModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecPackage.SPECIFICATION__DOMAIN_MODEL, newDomainModel, newDomainModel));
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
			case SpecPackage.SPECIFICATION__DOCUMENTS:
				return ((InternalEList<?>)getDocuments()).basicRemove(otherEnd, msgs);
			case SpecPackage.SPECIFICATION__GLOSSARY:
				return basicSetGlossary(null, msgs);
			case SpecPackage.SPECIFICATION__DOMAIN_MODEL:
				return basicSetDomainModel(null, msgs);
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
			case SpecPackage.SPECIFICATION__DOCUMENTS:
				return getDocuments();
			case SpecPackage.SPECIFICATION__GLOSSARY:
				return getGlossary();
			case SpecPackage.SPECIFICATION__DOMAIN_MODEL:
				return getDomainModel();
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
			case SpecPackage.SPECIFICATION__DOCUMENTS:
				getDocuments().clear();
				getDocuments().addAll((Collection<? extends SpecDocument>)newValue);
				return;
			case SpecPackage.SPECIFICATION__GLOSSARY:
				setGlossary((Glossary)newValue);
				return;
			case SpecPackage.SPECIFICATION__DOMAIN_MODEL:
				setDomainModel((DomainModel)newValue);
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
			case SpecPackage.SPECIFICATION__DOCUMENTS:
				getDocuments().clear();
				return;
			case SpecPackage.SPECIFICATION__GLOSSARY:
				setGlossary((Glossary)null);
				return;
			case SpecPackage.SPECIFICATION__DOMAIN_MODEL:
				setDomainModel((DomainModel)null);
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
			case SpecPackage.SPECIFICATION__DOCUMENTS:
				return documents != null && !documents.isEmpty();
			case SpecPackage.SPECIFICATION__GLOSSARY:
				return glossary != null;
			case SpecPackage.SPECIFICATION__DOMAIN_MODEL:
				return domainModel != null;
		}
		return super.eIsSet(featureID);
	}

} //SpecificationImpl
