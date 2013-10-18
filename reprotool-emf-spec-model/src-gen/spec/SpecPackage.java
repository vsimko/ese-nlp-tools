/**
 */
package spec;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see spec.SpecFactory
 * @model kind="package"
 * @generated
 */
public interface SpecPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "spec";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "spec";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "spec";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SpecPackage eINSTANCE = spec.impl.SpecPackageImpl.init();

	/**
	 * The meta object id for the '{@link spec.impl.SpecificationImpl <em>Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.SpecificationImpl
	 * @see spec.impl.SpecPackageImpl#getSpecification()
	 * @generated
	 */
	int SPECIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Documents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__DOCUMENTS = 0;

	/**
	 * The feature id for the '<em><b>Glossary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__GLOSSARY = 1;

	/**
	 * The feature id for the '<em><b>Domain Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__DOMAIN_MODEL = 2;

	/**
	 * The number of structural features of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link spec.impl.SpecSentenceImpl <em>Sentence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.SpecSentenceImpl
	 * @see spec.impl.SpecPackageImpl#getSpecSentence()
	 * @generated
	 */
	int SPEC_SENTENCE = 1;

	/**
	 * The feature id for the '<em><b>Words</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_SENTENCE__WORDS = 0;

	/**
	 * The feature id for the '<em><b>Typed Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_SENTENCE__TYPED_DEPENDENCIES = 1;

	/**
	 * The feature id for the '<em><b>Entity Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_SENTENCE__ENTITY_LINKS = 2;

	/**
	 * The feature id for the '<em><b>Semantic Root Word</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_SENTENCE__SEMANTIC_ROOT_WORD = 3;

	/**
	 * The number of structural features of the '<em>Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_SENTENCE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_SENTENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link spec.impl.SpecWordImpl <em>Word</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.SpecWordImpl
	 * @see spec.impl.SpecPackageImpl#getSpecWord()
	 * @generated
	 */
	int SPEC_WORD = 2;

	/**
	 * The feature id for the '<em><b>Original</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__ORIGINAL = 0;

	/**
	 * The feature id for the '<em><b>Lemma</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__LEMMA = 1;

	/**
	 * The feature id for the '<em><b>Pos Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__POS_TAG = 2;

	/**
	 * The feature id for the '<em><b>Semantic Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__SEMANTIC_PARENT = 3;

	/**
	 * The feature id for the '<em><b>Inv Link Dep</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__INV_LINK_DEP = 4;

	/**
	 * The feature id for the '<em><b>Semantic Parent Relation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__SEMANTIC_PARENT_RELATION = 5;

	/**
	 * The feature id for the '<em><b>Coref Rep Mention</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__COREF_REP_MENTION = 6;

	/**
	 * The feature id for the '<em><b>Coref Mentions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__COREF_MENTIONS = 7;

	/**
	 * The feature id for the '<em><b>Related Entity Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__RELATED_ENTITY_LINK = 8;

	/**
	 * The feature id for the '<em><b>Sentence</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__SENTENCE = 9;

	/**
	 * The feature id for the '<em><b>Semantic Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__SEMANTIC_CHILDREN = 10;

	/**
	 * The feature id for the '<em><b>Inv Link Gov</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__INV_LINK_GOV = 11;

	/**
	 * The feature id for the '<em><b>Coref Rep Or Self</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD__COREF_REP_OR_SELF = 12;

	/**
	 * The number of structural features of the '<em>Word</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD_FEATURE_COUNT = 13;

	/**
	 * The operation id for the '<em>Is Representative Coref</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD___IS_REPRESENTATIVE_COREF = 0;

	/**
	 * The number of operations of the '<em>Word</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_WORD_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link spec.impl.SpecDocumentImpl <em>Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.SpecDocumentImpl
	 * @see spec.impl.SpecPackageImpl#getSpecDocument()
	 * @generated
	 */
	int SPEC_DOCUMENT = 3;

	/**
	 * The feature id for the '<em><b>Sentences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_DOCUMENT__SENTENCES = 0;

	/**
	 * The number of structural features of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_DOCUMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPEC_DOCUMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link spec.impl.GlossaryImpl <em>Glossary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.GlossaryImpl
	 * @see spec.impl.SpecPackageImpl#getGlossary()
	 * @generated
	 */
	int GLOSSARY = 4;

	/**
	 * The feature id for the '<em><b>Terms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOSSARY__TERMS = 0;

	/**
	 * The number of structural features of the '<em>Glossary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOSSARY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Glossary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOSSARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link spec.impl.GlossaryTermImpl <em>Glossary Term</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.GlossaryTermImpl
	 * @see spec.impl.SpecPackageImpl#getGlossaryTerm()
	 * @generated
	 */
	int GLOSSARY_TERM = 5;

	/**
	 * The feature id for the '<em><b>Aliases</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOSSARY_TERM__ALIASES = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOSSARY_TERM__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Glossary Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOSSARY_TERM_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Glossary Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOSSARY_TERM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link spec.impl.WordDependencyImpl <em>Word Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.WordDependencyImpl
	 * @see spec.impl.SpecPackageImpl#getWordDependency()
	 * @generated
	 */
	int WORD_DEPENDENCY = 6;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_DEPENDENCY__LABEL = 0;

	/**
	 * The feature id for the '<em><b>Link Dep</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_DEPENDENCY__LINK_DEP = 1;

	/**
	 * The feature id for the '<em><b>Link Gov</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_DEPENDENCY__LINK_GOV = 2;

	/**
	 * The number of structural features of the '<em>Word Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_DEPENDENCY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Word Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_DEPENDENCY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link spec.impl.EntityLinkImpl <em>Entity Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.EntityLinkImpl
	 * @see spec.impl.SpecPackageImpl#getEntityLink()
	 * @generated
	 */
	int ENTITY_LINK = 7;

	/**
	 * The feature id for the '<em><b>Ent Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_LINK__ENT_LABEL = 0;

	/**
	 * The feature id for the '<em><b>Linked Words</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_LINK__LINKED_WORDS = 1;

	/**
	 * The feature id for the '<em><b>Linked Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_LINK__LINKED_ENTITY = 2;

	/**
	 * The feature id for the '<em><b>Ent Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_LINK__ENT_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Sentence</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_LINK__SENTENCE = 4;

	/**
	 * The number of structural features of the '<em>Entity Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_LINK_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Entity Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.ecore.EPackage <em>EPackage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage
	 * @see spec.impl.SpecPackageImpl#getEPackage()
	 * @generated
	 */
	int EPACKAGE = 8;

	/**
	 * The number of structural features of the '<em>EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACKAGE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPACKAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.ecore.ENamedElement <em>Domain Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @see spec.impl.SpecPackageImpl#getDomainEntity()
	 * @generated
	 */
	int DOMAIN_ENTITY = 9;

	/**
	 * The number of structural features of the '<em>Domain Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ENTITY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Domain Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ENTITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link spec.impl.DomainModelImpl <em>Domain Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.impl.DomainModelImpl
	 * @see spec.impl.SpecPackageImpl#getDomainModel()
	 * @generated
	 */
	int DOMAIN_MODEL = 10;

	/**
	 * The feature id for the '<em><b>Model Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL__MODEL_PACKAGE = 0;

	/**
	 * The number of structural features of the '<em>Domain Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Get Named Element</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL___GET_NAMED_ELEMENT__STRING = 0;

	/**
	 * The operation id for the '<em>Rebuild Name Index</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL___REBUILD_NAME_INDEX = 1;

	/**
	 * The operation id for the '<em>Load From Xmi File</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL___LOAD_FROM_XMI_FILE__STRING = 2;

	/**
	 * The number of operations of the '<em>Domain Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_MODEL_OPERATION_COUNT = 3;

	/**
	 * The meta object id for the '{@link spec.DomainEntityType <em>Domain Entity Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see spec.DomainEntityType
	 * @see spec.impl.SpecPackageImpl#getDomainEntityType()
	 * @generated
	 */
	int DOMAIN_ENTITY_TYPE = 11;

	/**
	 * The meta object id for the '<em>IO Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.io.IOException
	 * @see spec.impl.SpecPackageImpl#getIOException()
	 * @generated
	 */
	int IO_EXCEPTION = 12;


	/**
	 * Returns the meta object for class '{@link spec.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification</em>'.
	 * @see spec.Specification
	 * @generated
	 */
	EClass getSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link spec.Specification#getDocuments <em>Documents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documents</em>'.
	 * @see spec.Specification#getDocuments()
	 * @see #getSpecification()
	 * @generated
	 */
	EReference getSpecification_Documents();

	/**
	 * Returns the meta object for the containment reference '{@link spec.Specification#getGlossary <em>Glossary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Glossary</em>'.
	 * @see spec.Specification#getGlossary()
	 * @see #getSpecification()
	 * @generated
	 */
	EReference getSpecification_Glossary();

	/**
	 * Returns the meta object for the containment reference '{@link spec.Specification#getDomainModel <em>Domain Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Domain Model</em>'.
	 * @see spec.Specification#getDomainModel()
	 * @see #getSpecification()
	 * @generated
	 */
	EReference getSpecification_DomainModel();

	/**
	 * Returns the meta object for class '{@link spec.SpecSentence <em>Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sentence</em>'.
	 * @see spec.SpecSentence
	 * @generated
	 */
	EClass getSpecSentence();

	/**
	 * Returns the meta object for the containment reference list '{@link spec.SpecSentence#getWords <em>Words</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Words</em>'.
	 * @see spec.SpecSentence#getWords()
	 * @see #getSpecSentence()
	 * @generated
	 */
	EReference getSpecSentence_Words();

	/**
	 * Returns the meta object for the containment reference list '{@link spec.SpecSentence#getTypedDependencies <em>Typed Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Typed Dependencies</em>'.
	 * @see spec.SpecSentence#getTypedDependencies()
	 * @see #getSpecSentence()
	 * @generated
	 */
	EReference getSpecSentence_TypedDependencies();

	/**
	 * Returns the meta object for the containment reference list '{@link spec.SpecSentence#getEntityLinks <em>Entity Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entity Links</em>'.
	 * @see spec.SpecSentence#getEntityLinks()
	 * @see #getSpecSentence()
	 * @generated
	 */
	EReference getSpecSentence_EntityLinks();

	/**
	 * Returns the meta object for the reference '{@link spec.SpecSentence#getSemanticRootWord <em>Semantic Root Word</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Semantic Root Word</em>'.
	 * @see spec.SpecSentence#getSemanticRootWord()
	 * @see #getSpecSentence()
	 * @generated
	 */
	EReference getSpecSentence_SemanticRootWord();

	/**
	 * Returns the meta object for class '{@link spec.SpecWord <em>Word</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Word</em>'.
	 * @see spec.SpecWord
	 * @generated
	 */
	EClass getSpecWord();

	/**
	 * Returns the meta object for the attribute '{@link spec.SpecWord#getOriginal <em>Original</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Original</em>'.
	 * @see spec.SpecWord#getOriginal()
	 * @see #getSpecWord()
	 * @generated
	 */
	EAttribute getSpecWord_Original();

	/**
	 * Returns the meta object for the attribute '{@link spec.SpecWord#getLemma <em>Lemma</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lemma</em>'.
	 * @see spec.SpecWord#getLemma()
	 * @see #getSpecWord()
	 * @generated
	 */
	EAttribute getSpecWord_Lemma();

	/**
	 * Returns the meta object for the attribute '{@link spec.SpecWord#getPosTag <em>Pos Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos Tag</em>'.
	 * @see spec.SpecWord#getPosTag()
	 * @see #getSpecWord()
	 * @generated
	 */
	EAttribute getSpecWord_PosTag();

	/**
	 * Returns the meta object for the reference '{@link spec.SpecWord#getSemanticParent <em>Semantic Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Semantic Parent</em>'.
	 * @see spec.SpecWord#getSemanticParent()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_SemanticParent();

	/**
	 * Returns the meta object for the reference list '{@link spec.SpecWord#getInvLinkDep <em>Inv Link Dep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inv Link Dep</em>'.
	 * @see spec.SpecWord#getInvLinkDep()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_InvLinkDep();

	/**
	 * Returns the meta object for the reference '{@link spec.SpecWord#getSemanticParentRelation <em>Semantic Parent Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Semantic Parent Relation</em>'.
	 * @see spec.SpecWord#getSemanticParentRelation()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_SemanticParentRelation();

	/**
	 * Returns the meta object for the reference '{@link spec.SpecWord#getCorefRepMention <em>Coref Rep Mention</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Coref Rep Mention</em>'.
	 * @see spec.SpecWord#getCorefRepMention()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_CorefRepMention();

	/**
	 * Returns the meta object for the reference list '{@link spec.SpecWord#getCorefMentions <em>Coref Mentions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Coref Mentions</em>'.
	 * @see spec.SpecWord#getCorefMentions()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_CorefMentions();

	/**
	 * Returns the meta object for the reference '{@link spec.SpecWord#getRelatedEntityLink <em>Related Entity Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Related Entity Link</em>'.
	 * @see spec.SpecWord#getRelatedEntityLink()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_RelatedEntityLink();

	/**
	 * Returns the meta object for the container reference '{@link spec.SpecWord#getSentence <em>Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Sentence</em>'.
	 * @see spec.SpecWord#getSentence()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_Sentence();

	/**
	 * Returns the meta object for the reference list '{@link spec.SpecWord#getSemanticChildren <em>Semantic Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Semantic Children</em>'.
	 * @see spec.SpecWord#getSemanticChildren()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_SemanticChildren();

	/**
	 * Returns the meta object for the reference list '{@link spec.SpecWord#getInvLinkGov <em>Inv Link Gov</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inv Link Gov</em>'.
	 * @see spec.SpecWord#getInvLinkGov()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_InvLinkGov();

	/**
	 * Returns the meta object for the reference '{@link spec.SpecWord#getCorefRepOrSelf <em>Coref Rep Or Self</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Coref Rep Or Self</em>'.
	 * @see spec.SpecWord#getCorefRepOrSelf()
	 * @see #getSpecWord()
	 * @generated
	 */
	EReference getSpecWord_CorefRepOrSelf();

	/**
	 * Returns the meta object for the '{@link spec.SpecWord#isRepresentativeCoref() <em>Is Representative Coref</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Representative Coref</em>' operation.
	 * @see spec.SpecWord#isRepresentativeCoref()
	 * @generated
	 */
	EOperation getSpecWord__IsRepresentativeCoref();

	/**
	 * Returns the meta object for class '{@link spec.SpecDocument <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document</em>'.
	 * @see spec.SpecDocument
	 * @generated
	 */
	EClass getSpecDocument();

	/**
	 * Returns the meta object for the containment reference list '{@link spec.SpecDocument#getSentences <em>Sentences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sentences</em>'.
	 * @see spec.SpecDocument#getSentences()
	 * @see #getSpecDocument()
	 * @generated
	 */
	EReference getSpecDocument_Sentences();

	/**
	 * Returns the meta object for class '{@link spec.Glossary <em>Glossary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Glossary</em>'.
	 * @see spec.Glossary
	 * @generated
	 */
	EClass getGlossary();

	/**
	 * Returns the meta object for the containment reference list '{@link spec.Glossary#getTerms <em>Terms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Terms</em>'.
	 * @see spec.Glossary#getTerms()
	 * @see #getGlossary()
	 * @generated
	 */
	EReference getGlossary_Terms();

	/**
	 * Returns the meta object for class '{@link spec.GlossaryTerm <em>Glossary Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Glossary Term</em>'.
	 * @see spec.GlossaryTerm
	 * @generated
	 */
	EClass getGlossaryTerm();

	/**
	 * Returns the meta object for the attribute list '{@link spec.GlossaryTerm#getAliases <em>Aliases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Aliases</em>'.
	 * @see spec.GlossaryTerm#getAliases()
	 * @see #getGlossaryTerm()
	 * @generated
	 */
	EAttribute getGlossaryTerm_Aliases();

	/**
	 * Returns the meta object for the attribute '{@link spec.GlossaryTerm#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see spec.GlossaryTerm#getDescription()
	 * @see #getGlossaryTerm()
	 * @generated
	 */
	EAttribute getGlossaryTerm_Description();

	/**
	 * Returns the meta object for class '{@link spec.WordDependency <em>Word Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Word Dependency</em>'.
	 * @see spec.WordDependency
	 * @generated
	 */
	EClass getWordDependency();

	/**
	 * Returns the meta object for the attribute '{@link spec.WordDependency#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see spec.WordDependency#getLabel()
	 * @see #getWordDependency()
	 * @generated
	 */
	EAttribute getWordDependency_Label();

	/**
	 * Returns the meta object for the reference '{@link spec.WordDependency#getLinkDep <em>Link Dep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link Dep</em>'.
	 * @see spec.WordDependency#getLinkDep()
	 * @see #getWordDependency()
	 * @generated
	 */
	EReference getWordDependency_LinkDep();

	/**
	 * Returns the meta object for the reference '{@link spec.WordDependency#getLinkGov <em>Link Gov</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link Gov</em>'.
	 * @see spec.WordDependency#getLinkGov()
	 * @see #getWordDependency()
	 * @generated
	 */
	EReference getWordDependency_LinkGov();

	/**
	 * Returns the meta object for class '{@link spec.EntityLink <em>Entity Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity Link</em>'.
	 * @see spec.EntityLink
	 * @generated
	 */
	EClass getEntityLink();

	/**
	 * Returns the meta object for the attribute '{@link spec.EntityLink#getEntLabel <em>Ent Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ent Label</em>'.
	 * @see spec.EntityLink#getEntLabel()
	 * @see #getEntityLink()
	 * @generated
	 */
	EAttribute getEntityLink_EntLabel();

	/**
	 * Returns the meta object for the reference list '{@link spec.EntityLink#getLinkedWords <em>Linked Words</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Linked Words</em>'.
	 * @see spec.EntityLink#getLinkedWords()
	 * @see #getEntityLink()
	 * @generated
	 */
	EReference getEntityLink_LinkedWords();

	/**
	 * Returns the meta object for the reference '{@link spec.EntityLink#getLinkedEntity <em>Linked Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Linked Entity</em>'.
	 * @see spec.EntityLink#getLinkedEntity()
	 * @see #getEntityLink()
	 * @generated
	 */
	EReference getEntityLink_LinkedEntity();

	/**
	 * Returns the meta object for the attribute '{@link spec.EntityLink#getEntType <em>Ent Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ent Type</em>'.
	 * @see spec.EntityLink#getEntType()
	 * @see #getEntityLink()
	 * @generated
	 */
	EAttribute getEntityLink_EntType();

	/**
	 * Returns the meta object for the container reference '{@link spec.EntityLink#getSentence <em>Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Sentence</em>'.
	 * @see spec.EntityLink#getSentence()
	 * @see #getEntityLink()
	 * @generated
	 */
	EReference getEntityLink_Sentence();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.EPackage <em>EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EPackage</em>'.
	 * @see org.eclipse.emf.ecore.EPackage
	 * @model instanceClass="org.eclipse.emf.ecore.EPackage"
	 * @generated
	 */
	EClass getEPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.ENamedElement <em>Domain Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Entity</em>'.
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @model instanceClass="org.eclipse.emf.ecore.ENamedElement"
	 * @generated
	 */
	EClass getDomainEntity();

	/**
	 * Returns the meta object for class '{@link spec.DomainModel <em>Domain Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Model</em>'.
	 * @see spec.DomainModel
	 * @generated
	 */
	EClass getDomainModel();

	/**
	 * Returns the meta object for the containment reference '{@link spec.DomainModel#getModelPackage <em>Model Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Model Package</em>'.
	 * @see spec.DomainModel#getModelPackage()
	 * @see #getDomainModel()
	 * @generated
	 */
	EReference getDomainModel_ModelPackage();

	/**
	 * Returns the meta object for the '{@link spec.DomainModel#getNamedElement(java.lang.String) <em>Get Named Element</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Named Element</em>' operation.
	 * @see spec.DomainModel#getNamedElement(java.lang.String)
	 * @generated
	 */
	EOperation getDomainModel__GetNamedElement__String();

	/**
	 * Returns the meta object for the '{@link spec.DomainModel#rebuildNameIndex() <em>Rebuild Name Index</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Rebuild Name Index</em>' operation.
	 * @see spec.DomainModel#rebuildNameIndex()
	 * @generated
	 */
	EOperation getDomainModel__RebuildNameIndex();

	/**
	 * Returns the meta object for the '{@link spec.DomainModel#loadFromXmiFile(java.lang.String) <em>Load From Xmi File</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Load From Xmi File</em>' operation.
	 * @see spec.DomainModel#loadFromXmiFile(java.lang.String)
	 * @generated
	 */
	EOperation getDomainModel__LoadFromXmiFile__String();

	/**
	 * Returns the meta object for enum '{@link spec.DomainEntityType <em>Domain Entity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Domain Entity Type</em>'.
	 * @see spec.DomainEntityType
	 * @generated
	 */
	EEnum getDomainEntityType();

	/**
	 * Returns the meta object for data type '{@link java.io.IOException <em>IO Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IO Exception</em>'.
	 * @see java.io.IOException
	 * @model instanceClass="java.io.IOException" serializeable="false"
	 * @generated
	 */
	EDataType getIOException();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SpecFactory getSpecFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link spec.impl.SpecificationImpl <em>Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.SpecificationImpl
		 * @see spec.impl.SpecPackageImpl#getSpecification()
		 * @generated
		 */
		EClass SPECIFICATION = eINSTANCE.getSpecification();

		/**
		 * The meta object literal for the '<em><b>Documents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFICATION__DOCUMENTS = eINSTANCE.getSpecification_Documents();

		/**
		 * The meta object literal for the '<em><b>Glossary</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFICATION__GLOSSARY = eINSTANCE.getSpecification_Glossary();

		/**
		 * The meta object literal for the '<em><b>Domain Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFICATION__DOMAIN_MODEL = eINSTANCE.getSpecification_DomainModel();

		/**
		 * The meta object literal for the '{@link spec.impl.SpecSentenceImpl <em>Sentence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.SpecSentenceImpl
		 * @see spec.impl.SpecPackageImpl#getSpecSentence()
		 * @generated
		 */
		EClass SPEC_SENTENCE = eINSTANCE.getSpecSentence();

		/**
		 * The meta object literal for the '<em><b>Words</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_SENTENCE__WORDS = eINSTANCE.getSpecSentence_Words();

		/**
		 * The meta object literal for the '<em><b>Typed Dependencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_SENTENCE__TYPED_DEPENDENCIES = eINSTANCE.getSpecSentence_TypedDependencies();

		/**
		 * The meta object literal for the '<em><b>Entity Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_SENTENCE__ENTITY_LINKS = eINSTANCE.getSpecSentence_EntityLinks();

		/**
		 * The meta object literal for the '<em><b>Semantic Root Word</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_SENTENCE__SEMANTIC_ROOT_WORD = eINSTANCE.getSpecSentence_SemanticRootWord();

		/**
		 * The meta object literal for the '{@link spec.impl.SpecWordImpl <em>Word</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.SpecWordImpl
		 * @see spec.impl.SpecPackageImpl#getSpecWord()
		 * @generated
		 */
		EClass SPEC_WORD = eINSTANCE.getSpecWord();

		/**
		 * The meta object literal for the '<em><b>Original</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEC_WORD__ORIGINAL = eINSTANCE.getSpecWord_Original();

		/**
		 * The meta object literal for the '<em><b>Lemma</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEC_WORD__LEMMA = eINSTANCE.getSpecWord_Lemma();

		/**
		 * The meta object literal for the '<em><b>Pos Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPEC_WORD__POS_TAG = eINSTANCE.getSpecWord_PosTag();

		/**
		 * The meta object literal for the '<em><b>Semantic Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__SEMANTIC_PARENT = eINSTANCE.getSpecWord_SemanticParent();

		/**
		 * The meta object literal for the '<em><b>Inv Link Dep</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__INV_LINK_DEP = eINSTANCE.getSpecWord_InvLinkDep();

		/**
		 * The meta object literal for the '<em><b>Semantic Parent Relation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__SEMANTIC_PARENT_RELATION = eINSTANCE.getSpecWord_SemanticParentRelation();

		/**
		 * The meta object literal for the '<em><b>Coref Rep Mention</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__COREF_REP_MENTION = eINSTANCE.getSpecWord_CorefRepMention();

		/**
		 * The meta object literal for the '<em><b>Coref Mentions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__COREF_MENTIONS = eINSTANCE.getSpecWord_CorefMentions();

		/**
		 * The meta object literal for the '<em><b>Related Entity Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__RELATED_ENTITY_LINK = eINSTANCE.getSpecWord_RelatedEntityLink();

		/**
		 * The meta object literal for the '<em><b>Sentence</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__SENTENCE = eINSTANCE.getSpecWord_Sentence();

		/**
		 * The meta object literal for the '<em><b>Semantic Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__SEMANTIC_CHILDREN = eINSTANCE.getSpecWord_SemanticChildren();

		/**
		 * The meta object literal for the '<em><b>Inv Link Gov</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__INV_LINK_GOV = eINSTANCE.getSpecWord_InvLinkGov();

		/**
		 * The meta object literal for the '<em><b>Coref Rep Or Self</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_WORD__COREF_REP_OR_SELF = eINSTANCE.getSpecWord_CorefRepOrSelf();

		/**
		 * The meta object literal for the '<em><b>Is Representative Coref</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SPEC_WORD___IS_REPRESENTATIVE_COREF = eINSTANCE.getSpecWord__IsRepresentativeCoref();

		/**
		 * The meta object literal for the '{@link spec.impl.SpecDocumentImpl <em>Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.SpecDocumentImpl
		 * @see spec.impl.SpecPackageImpl#getSpecDocument()
		 * @generated
		 */
		EClass SPEC_DOCUMENT = eINSTANCE.getSpecDocument();

		/**
		 * The meta object literal for the '<em><b>Sentences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPEC_DOCUMENT__SENTENCES = eINSTANCE.getSpecDocument_Sentences();

		/**
		 * The meta object literal for the '{@link spec.impl.GlossaryImpl <em>Glossary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.GlossaryImpl
		 * @see spec.impl.SpecPackageImpl#getGlossary()
		 * @generated
		 */
		EClass GLOSSARY = eINSTANCE.getGlossary();

		/**
		 * The meta object literal for the '<em><b>Terms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLOSSARY__TERMS = eINSTANCE.getGlossary_Terms();

		/**
		 * The meta object literal for the '{@link spec.impl.GlossaryTermImpl <em>Glossary Term</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.GlossaryTermImpl
		 * @see spec.impl.SpecPackageImpl#getGlossaryTerm()
		 * @generated
		 */
		EClass GLOSSARY_TERM = eINSTANCE.getGlossaryTerm();

		/**
		 * The meta object literal for the '<em><b>Aliases</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOSSARY_TERM__ALIASES = eINSTANCE.getGlossaryTerm_Aliases();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOSSARY_TERM__DESCRIPTION = eINSTANCE.getGlossaryTerm_Description();

		/**
		 * The meta object literal for the '{@link spec.impl.WordDependencyImpl <em>Word Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.WordDependencyImpl
		 * @see spec.impl.SpecPackageImpl#getWordDependency()
		 * @generated
		 */
		EClass WORD_DEPENDENCY = eINSTANCE.getWordDependency();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORD_DEPENDENCY__LABEL = eINSTANCE.getWordDependency_Label();

		/**
		 * The meta object literal for the '<em><b>Link Dep</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORD_DEPENDENCY__LINK_DEP = eINSTANCE.getWordDependency_LinkDep();

		/**
		 * The meta object literal for the '<em><b>Link Gov</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORD_DEPENDENCY__LINK_GOV = eINSTANCE.getWordDependency_LinkGov();

		/**
		 * The meta object literal for the '{@link spec.impl.EntityLinkImpl <em>Entity Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.EntityLinkImpl
		 * @see spec.impl.SpecPackageImpl#getEntityLink()
		 * @generated
		 */
		EClass ENTITY_LINK = eINSTANCE.getEntityLink();

		/**
		 * The meta object literal for the '<em><b>Ent Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY_LINK__ENT_LABEL = eINSTANCE.getEntityLink_EntLabel();

		/**
		 * The meta object literal for the '<em><b>Linked Words</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_LINK__LINKED_WORDS = eINSTANCE.getEntityLink_LinkedWords();

		/**
		 * The meta object literal for the '<em><b>Linked Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_LINK__LINKED_ENTITY = eINSTANCE.getEntityLink_LinkedEntity();

		/**
		 * The meta object literal for the '<em><b>Ent Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY_LINK__ENT_TYPE = eINSTANCE.getEntityLink_EntType();

		/**
		 * The meta object literal for the '<em><b>Sentence</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_LINK__SENTENCE = eINSTANCE.getEntityLink_Sentence();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.ecore.EPackage <em>EPackage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.EPackage
		 * @see spec.impl.SpecPackageImpl#getEPackage()
		 * @generated
		 */
		EClass EPACKAGE = eINSTANCE.getEPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.ecore.ENamedElement <em>Domain Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.ENamedElement
		 * @see spec.impl.SpecPackageImpl#getDomainEntity()
		 * @generated
		 */
		EClass DOMAIN_ENTITY = eINSTANCE.getDomainEntity();

		/**
		 * The meta object literal for the '{@link spec.impl.DomainModelImpl <em>Domain Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.impl.DomainModelImpl
		 * @see spec.impl.SpecPackageImpl#getDomainModel()
		 * @generated
		 */
		EClass DOMAIN_MODEL = eINSTANCE.getDomainModel();

		/**
		 * The meta object literal for the '<em><b>Model Package</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_MODEL__MODEL_PACKAGE = eINSTANCE.getDomainModel_ModelPackage();

		/**
		 * The meta object literal for the '<em><b>Get Named Element</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOMAIN_MODEL___GET_NAMED_ELEMENT__STRING = eINSTANCE.getDomainModel__GetNamedElement__String();

		/**
		 * The meta object literal for the '<em><b>Rebuild Name Index</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOMAIN_MODEL___REBUILD_NAME_INDEX = eINSTANCE.getDomainModel__RebuildNameIndex();

		/**
		 * The meta object literal for the '<em><b>Load From Xmi File</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOMAIN_MODEL___LOAD_FROM_XMI_FILE__STRING = eINSTANCE.getDomainModel__LoadFromXmiFile__String();

		/**
		 * The meta object literal for the '{@link spec.DomainEntityType <em>Domain Entity Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see spec.DomainEntityType
		 * @see spec.impl.SpecPackageImpl#getDomainEntityType()
		 * @generated
		 */
		EEnum DOMAIN_ENTITY_TYPE = eINSTANCE.getDomainEntityType();

		/**
		 * The meta object literal for the '<em>IO Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.io.IOException
		 * @see spec.impl.SpecPackageImpl#getIOException()
		 * @generated
		 */
		EDataType IO_EXCEPTION = eINSTANCE.getIOException();

	}

} //SpecPackage
