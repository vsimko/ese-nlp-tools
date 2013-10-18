/**
 */
package spec.impl;

import java.io.IOException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import spec.DomainEntityType;
import spec.DomainModel;
import spec.EntityLink;
import spec.Glossary;
import spec.GlossaryTerm;
import spec.SpecDocument;
import spec.SpecFactory;
import spec.SpecPackage;
import spec.SpecSentence;
import spec.SpecWord;
import spec.Specification;
import spec.WordDependency;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpecPackageImpl extends EPackageImpl implements SpecPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specSentenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specWordEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specDocumentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass glossaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass glossaryTermEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wordDependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ePackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainEntityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum domainEntityTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType ioExceptionEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see spec.SpecPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SpecPackageImpl()
	{
		super(eNS_URI, SpecFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SpecPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SpecPackage init()
	{
		if (isInited) return (SpecPackage)EPackage.Registry.INSTANCE.getEPackage(SpecPackage.eNS_URI);

		// Obtain or create and register package
		SpecPackageImpl theSpecPackage = (SpecPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SpecPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SpecPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theSpecPackage.createPackageContents();

		// Initialize created meta-data
		theSpecPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSpecPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SpecPackage.eNS_URI, theSpecPackage);
		return theSpecPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecification()
	{
		return specificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecification_Documents()
	{
		return (EReference)specificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecification_Glossary()
	{
		return (EReference)specificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecification_DomainModel()
	{
		return (EReference)specificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecSentence()
	{
		return specSentenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecSentence_Words()
	{
		return (EReference)specSentenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecSentence_TypedDependencies()
	{
		return (EReference)specSentenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecSentence_EntityLinks()
	{
		return (EReference)specSentenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecSentence_SemanticRootWord()
	{
		return (EReference)specSentenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecWord()
	{
		return specWordEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecWord_Original()
	{
		return (EAttribute)specWordEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecWord_Lemma()
	{
		return (EAttribute)specWordEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpecWord_PosTag()
	{
		return (EAttribute)specWordEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_SemanticParent()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_InvLinkDep()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_SemanticParentRelation()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_CorefRepMention()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_CorefMentions()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_RelatedEntityLink()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_Sentence()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_SemanticChildren()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_InvLinkGov()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecWord_CorefRepOrSelf()
	{
		return (EReference)specWordEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getSpecWord__IsRepresentativeCoref()
	{
		return specWordEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpecDocument()
	{
		return specDocumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpecDocument_Sentences()
	{
		return (EReference)specDocumentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGlossary()
	{
		return glossaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGlossary_Terms()
	{
		return (EReference)glossaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGlossaryTerm()
	{
		return glossaryTermEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlossaryTerm_Aliases()
	{
		return (EAttribute)glossaryTermEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGlossaryTerm_Description()
	{
		return (EAttribute)glossaryTermEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWordDependency()
	{
		return wordDependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWordDependency_Label()
	{
		return (EAttribute)wordDependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWordDependency_LinkDep()
	{
		return (EReference)wordDependencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWordDependency_LinkGov()
	{
		return (EReference)wordDependencyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityLink()
	{
		return entityLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityLink_EntLabel()
	{
		return (EAttribute)entityLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityLink_LinkedWords()
	{
		return (EReference)entityLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityLink_LinkedEntity()
	{
		return (EReference)entityLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntityLink_EntType()
	{
		return (EAttribute)entityLinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityLink_Sentence()
	{
		return (EReference)entityLinkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEPackage()
	{
		return ePackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainEntity()
	{
		return domainEntityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainModel()
	{
		return domainModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainModel_ModelPackage()
	{
		return (EReference)domainModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDomainModel__GetNamedElement__String()
	{
		return domainModelEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDomainModel__RebuildNameIndex()
	{
		return domainModelEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getDomainModel__LoadFromXmiFile__String()
	{
		return domainModelEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDomainEntityType()
	{
		return domainEntityTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getIOException()
	{
		return ioExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpecFactory getSpecFactory()
	{
		return (SpecFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		specificationEClass = createEClass(SPECIFICATION);
		createEReference(specificationEClass, SPECIFICATION__DOCUMENTS);
		createEReference(specificationEClass, SPECIFICATION__GLOSSARY);
		createEReference(specificationEClass, SPECIFICATION__DOMAIN_MODEL);

		specSentenceEClass = createEClass(SPEC_SENTENCE);
		createEReference(specSentenceEClass, SPEC_SENTENCE__WORDS);
		createEReference(specSentenceEClass, SPEC_SENTENCE__TYPED_DEPENDENCIES);
		createEReference(specSentenceEClass, SPEC_SENTENCE__ENTITY_LINKS);
		createEReference(specSentenceEClass, SPEC_SENTENCE__SEMANTIC_ROOT_WORD);

		specWordEClass = createEClass(SPEC_WORD);
		createEAttribute(specWordEClass, SPEC_WORD__ORIGINAL);
		createEAttribute(specWordEClass, SPEC_WORD__LEMMA);
		createEAttribute(specWordEClass, SPEC_WORD__POS_TAG);
		createEReference(specWordEClass, SPEC_WORD__SEMANTIC_PARENT);
		createEReference(specWordEClass, SPEC_WORD__INV_LINK_DEP);
		createEReference(specWordEClass, SPEC_WORD__SEMANTIC_PARENT_RELATION);
		createEReference(specWordEClass, SPEC_WORD__COREF_REP_MENTION);
		createEReference(specWordEClass, SPEC_WORD__COREF_MENTIONS);
		createEReference(specWordEClass, SPEC_WORD__RELATED_ENTITY_LINK);
		createEReference(specWordEClass, SPEC_WORD__SENTENCE);
		createEReference(specWordEClass, SPEC_WORD__SEMANTIC_CHILDREN);
		createEReference(specWordEClass, SPEC_WORD__INV_LINK_GOV);
		createEReference(specWordEClass, SPEC_WORD__COREF_REP_OR_SELF);
		createEOperation(specWordEClass, SPEC_WORD___IS_REPRESENTATIVE_COREF);

		specDocumentEClass = createEClass(SPEC_DOCUMENT);
		createEReference(specDocumentEClass, SPEC_DOCUMENT__SENTENCES);

		glossaryEClass = createEClass(GLOSSARY);
		createEReference(glossaryEClass, GLOSSARY__TERMS);

		glossaryTermEClass = createEClass(GLOSSARY_TERM);
		createEAttribute(glossaryTermEClass, GLOSSARY_TERM__ALIASES);
		createEAttribute(glossaryTermEClass, GLOSSARY_TERM__DESCRIPTION);

		wordDependencyEClass = createEClass(WORD_DEPENDENCY);
		createEAttribute(wordDependencyEClass, WORD_DEPENDENCY__LABEL);
		createEReference(wordDependencyEClass, WORD_DEPENDENCY__LINK_DEP);
		createEReference(wordDependencyEClass, WORD_DEPENDENCY__LINK_GOV);

		entityLinkEClass = createEClass(ENTITY_LINK);
		createEAttribute(entityLinkEClass, ENTITY_LINK__ENT_LABEL);
		createEReference(entityLinkEClass, ENTITY_LINK__LINKED_WORDS);
		createEReference(entityLinkEClass, ENTITY_LINK__LINKED_ENTITY);
		createEAttribute(entityLinkEClass, ENTITY_LINK__ENT_TYPE);
		createEReference(entityLinkEClass, ENTITY_LINK__SENTENCE);

		ePackageEClass = createEClass(EPACKAGE);

		domainEntityEClass = createEClass(DOMAIN_ENTITY);

		domainModelEClass = createEClass(DOMAIN_MODEL);
		createEReference(domainModelEClass, DOMAIN_MODEL__MODEL_PACKAGE);
		createEOperation(domainModelEClass, DOMAIN_MODEL___GET_NAMED_ELEMENT__STRING);
		createEOperation(domainModelEClass, DOMAIN_MODEL___REBUILD_NAME_INDEX);
		createEOperation(domainModelEClass, DOMAIN_MODEL___LOAD_FROM_XMI_FILE__STRING);

		// Create enums
		domainEntityTypeEEnum = createEEnum(DOMAIN_ENTITY_TYPE);

		// Create data types
		ioExceptionEDataType = createEDataType(IO_EXCEPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(specificationEClass, Specification.class, "Specification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecification_Documents(), this.getSpecDocument(), null, "documents", null, 0, -1, Specification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecification_Glossary(), this.getGlossary(), null, "glossary", null, 1, 1, Specification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecification_DomainModel(), this.getDomainModel(), null, "domainModel", null, 1, 1, Specification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(specSentenceEClass, SpecSentence.class, "SpecSentence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecSentence_Words(), this.getSpecWord(), this.getSpecWord_Sentence(), "words", null, 0, -1, SpecSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecSentence_TypedDependencies(), this.getWordDependency(), null, "typedDependencies", null, 0, -1, SpecSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecSentence_EntityLinks(), this.getEntityLink(), this.getEntityLink_Sentence(), "entityLinks", null, 0, -1, SpecSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecSentence_SemanticRootWord(), this.getSpecWord(), null, "semanticRootWord", null, 1, 1, SpecSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(specWordEClass, SpecWord.class, "SpecWord", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpecWord_Original(), ecorePackage.getEString(), "original", null, 1, 1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecWord_Lemma(), ecorePackage.getEString(), "lemma", null, 1, 1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecWord_PosTag(), ecorePackage.getEString(), "posTag", null, 1, 1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_SemanticParent(), this.getSpecWord(), null, "semanticParent", null, 0, 1, SpecWord.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_InvLinkDep(), this.getWordDependency(), this.getWordDependency_LinkDep(), "invLinkDep", null, 0, -1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_SemanticParentRelation(), this.getWordDependency(), null, "semanticParentRelation", null, 0, 1, SpecWord.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_CorefRepMention(), this.getSpecWord(), this.getSpecWord_CorefMentions(), "corefRepMention", null, 0, 1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_CorefMentions(), this.getSpecWord(), this.getSpecWord_CorefRepMention(), "corefMentions", null, 0, -1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_RelatedEntityLink(), this.getEntityLink(), this.getEntityLink_LinkedWords(), "relatedEntityLink", null, 0, 1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_Sentence(), this.getSpecSentence(), this.getSpecSentence_Words(), "sentence", null, 1, 1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_SemanticChildren(), this.getSpecWord(), null, "semanticChildren", null, 0, -1, SpecWord.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_InvLinkGov(), this.getWordDependency(), this.getWordDependency_LinkGov(), "invLinkGov", null, 0, -1, SpecWord.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSpecWord_CorefRepOrSelf(), this.getSpecWord(), null, "corefRepOrSelf", null, 1, 1, SpecWord.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEOperation(getSpecWord__IsRepresentativeCoref(), ecorePackage.getEBoolean(), "isRepresentativeCoref", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(specDocumentEClass, SpecDocument.class, "SpecDocument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecDocument_Sentences(), this.getSpecSentence(), null, "sentences", null, 0, -1, SpecDocument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(glossaryEClass, Glossary.class, "Glossary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGlossary_Terms(), this.getGlossaryTerm(), null, "terms", null, 0, -1, Glossary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(glossaryTermEClass, GlossaryTerm.class, "GlossaryTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGlossaryTerm_Aliases(), ecorePackage.getEString(), "aliases", null, 1, -1, GlossaryTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGlossaryTerm_Description(), ecorePackage.getEString(), "description", null, 0, 1, GlossaryTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wordDependencyEClass, WordDependency.class, "WordDependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWordDependency_Label(), ecorePackage.getEString(), "label", null, 1, 1, WordDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWordDependency_LinkDep(), this.getSpecWord(), this.getSpecWord_InvLinkDep(), "linkDep", null, 1, 1, WordDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWordDependency_LinkGov(), this.getSpecWord(), this.getSpecWord_InvLinkGov(), "linkGov", null, 1, 1, WordDependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityLinkEClass, EntityLink.class, "EntityLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntityLink_EntLabel(), ecorePackage.getEString(), "entLabel", null, 0, 1, EntityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityLink_LinkedWords(), this.getSpecWord(), this.getSpecWord_RelatedEntityLink(), "linkedWords", null, 1, -1, EntityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityLink_LinkedEntity(), this.getDomainEntity(), null, "linkedEntity", null, 1, 1, EntityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntityLink_EntType(), this.getDomainEntityType(), "entType", null, 0, 1, EntityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityLink_Sentence(), this.getSpecSentence(), this.getSpecSentence_EntityLinks(), "sentence", null, 1, 1, EntityLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ePackageEClass, EPackage.class, "EPackage", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(domainEntityEClass, ENamedElement.class, "DomainEntity", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(domainModelEClass, DomainModel.class, "DomainModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainModel_ModelPackage(), this.getEPackage(), null, "modelPackage", null, 1, 1, DomainModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getDomainModel__GetNamedElement__String(), ecorePackage.getENamedElement(), "getNamedElement", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "elementName", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getDomainModel__RebuildNameIndex(), null, "rebuildNameIndex", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getDomainModel__LoadFromXmiFile__String(), null, "loadFromXmiFile", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "fileName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getIOException());

		// Initialize enums and add enum literals
		initEEnum(domainEntityTypeEEnum, DomainEntityType.class, "DomainEntityType");
		addEEnumLiteral(domainEntityTypeEEnum, DomainEntityType.OTHER);
		addEEnumLiteral(domainEntityTypeEEnum, DomainEntityType.CLASS);
		addEEnumLiteral(domainEntityTypeEEnum, DomainEntityType.REFERENCE);
		addEEnumLiteral(domainEntityTypeEEnum, DomainEntityType.ATTRIBUTE);
		addEEnumLiteral(domainEntityTypeEEnum, DomainEntityType.OPERATION);

		// Initialize data types
		initEDataType(ioExceptionEDataType, IOException.class, "IOException", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //SpecPackageImpl
