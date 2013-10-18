/**
 */
package spec.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import spec.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpecFactoryImpl extends EFactoryImpl implements SpecFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SpecFactory init()
	{
		try
		{
			SpecFactory theSpecFactory = (SpecFactory)EPackage.Registry.INSTANCE.getEFactory(SpecPackage.eNS_URI);
			if (theSpecFactory != null)
			{
				return theSpecFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SpecFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID())
		{
			case SpecPackage.SPECIFICATION: return createSpecification();
			case SpecPackage.SPEC_SENTENCE: return createSpecSentence();
			case SpecPackage.SPEC_WORD: return createSpecWord();
			case SpecPackage.SPEC_DOCUMENT: return createSpecDocument();
			case SpecPackage.GLOSSARY: return createGlossary();
			case SpecPackage.GLOSSARY_TERM: return createGlossaryTerm();
			case SpecPackage.WORD_DEPENDENCY: return createWordDependency();
			case SpecPackage.ENTITY_LINK: return createEntityLink();
			case SpecPackage.DOMAIN_MODEL: return createDomainModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID())
		{
			case SpecPackage.DOMAIN_ENTITY_TYPE:
				return createDomainEntityTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID())
		{
			case SpecPackage.DOMAIN_ENTITY_TYPE:
				return convertDomainEntityTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Specification createSpecification()
	{
		SpecificationImpl specification = new SpecificationImpl();
		return specification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecSentence createSpecSentence()
	{
		SpecSentenceImplCustom specSentence = new SpecSentenceImplCustom();
		return specSentence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecWord createSpecWord()
	{
		SpecWordImplCustom specWord = new SpecWordImplCustom();
		return specWord;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecDocument createSpecDocument()
	{
		SpecDocumentImpl specDocument = new SpecDocumentImpl();
		return specDocument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Glossary createGlossary()
	{
		GlossaryImpl glossary = new GlossaryImpl();
		return glossary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GlossaryTerm createGlossaryTerm()
	{
		GlossaryTermImpl glossaryTerm = new GlossaryTermImpl();
		return glossaryTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WordDependency createWordDependency()
	{
		WordDependencyImpl wordDependency = new WordDependencyImpl();
		return wordDependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityLink createEntityLink()
	{
		EntityLinkImplCustom entityLink = new EntityLinkImplCustom();
		return entityLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainModel createDomainModel()
	{
		DomainModelImplCustom domainModel = new DomainModelImplCustom();
		return domainModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainEntityType createDomainEntityTypeFromString(EDataType eDataType, String initialValue)
	{
		DomainEntityType result = DomainEntityType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDomainEntityTypeToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecPackage getSpecPackage()
	{
		return (SpecPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SpecPackage getPackage()
	{
		return SpecPackage.eINSTANCE;
	}

} //SpecFactoryImpl
