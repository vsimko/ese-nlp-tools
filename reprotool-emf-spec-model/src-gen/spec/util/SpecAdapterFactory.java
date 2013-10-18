/**
 */
package spec.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import spec.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see spec.SpecPackage
 * @generated
 */
public class SpecAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SpecPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecAdapterFactory()
	{
		if (modelPackage == null)
		{
			modelPackage = SpecPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if (object == modelPackage)
		{
			return true;
		}
		if (object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecSwitch<Adapter> modelSwitch =
		new SpecSwitch<Adapter>()
		{
			@Override
			public Adapter caseSpecification(Specification object)
			{
				return createSpecificationAdapter();
			}
			@Override
			public Adapter caseSpecSentence(SpecSentence object)
			{
				return createSpecSentenceAdapter();
			}
			@Override
			public Adapter caseSpecWord(SpecWord object)
			{
				return createSpecWordAdapter();
			}
			@Override
			public Adapter caseSpecDocument(SpecDocument object)
			{
				return createSpecDocumentAdapter();
			}
			@Override
			public Adapter caseGlossary(Glossary object)
			{
				return createGlossaryAdapter();
			}
			@Override
			public Adapter caseGlossaryTerm(GlossaryTerm object)
			{
				return createGlossaryTermAdapter();
			}
			@Override
			public Adapter caseWordDependency(WordDependency object)
			{
				return createWordDependencyAdapter();
			}
			@Override
			public Adapter caseEntityLink(EntityLink object)
			{
				return createEntityLinkAdapter();
			}
			@Override
			public Adapter caseEPackage(EPackage object)
			{
				return createEPackageAdapter();
			}
			@Override
			public Adapter caseDomainEntity(ENamedElement object)
			{
				return createDomainEntityAdapter();
			}
			@Override
			public Adapter caseDomainModel(DomainModel object)
			{
				return createDomainModelAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object)
			{
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link spec.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.Specification
	 * @generated
	 */
	public Adapter createSpecificationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.SpecSentence <em>Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.SpecSentence
	 * @generated
	 */
	public Adapter createSpecSentenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.SpecWord <em>Word</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.SpecWord
	 * @generated
	 */
	public Adapter createSpecWordAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.SpecDocument <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.SpecDocument
	 * @generated
	 */
	public Adapter createSpecDocumentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.Glossary <em>Glossary</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.Glossary
	 * @generated
	 */
	public Adapter createGlossaryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.GlossaryTerm <em>Glossary Term</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.GlossaryTerm
	 * @generated
	 */
	public Adapter createGlossaryTermAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.WordDependency <em>Word Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.WordDependency
	 * @generated
	 */
	public Adapter createWordDependencyAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.EntityLink <em>Entity Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.EntityLink
	 * @generated
	 */
	public Adapter createEntityLinkAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EPackage
	 * @generated
	 */
	public Adapter createEPackageAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>Domain Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @generated
	 */
	public Adapter createDomainEntityAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link spec.DomainModel <em>Domain Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see spec.DomainModel
	 * @generated
	 */
	public Adapter createDomainModelAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

} //SpecAdapterFactory
