package reprotool.dmodel.nlp.pipeline

import aQute.bnd.annotation.component.Activate
import aQute.bnd.annotation.component.Component
import aQute.bnd.annotation.component.Deactivate
import aQute.bnd.annotation.component.Reference
import com.google.common.base.Charsets
import com.google.common.io.Files
import edu.stanford.nlp.dcoref.CorefChain
import edu.stanford.nlp.dcoref.CorefChain.CorefMention
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation
import edu.stanford.nlp.ie.AbstractSequenceClassifier
import edu.stanford.nlp.ie.NERClassifierCombiner
import edu.stanford.nlp.ie.crf.CRFClassifier
import edu.stanford.nlp.ling.CoreAnnotations.IndexAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation
import edu.stanford.nlp.ling.CoreAnnotations.XmlContextAnnotation
import edu.stanford.nlp.ling.CoreLabel
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.AnnotationPipeline
import edu.stanford.nlp.pipeline.Annotator
import edu.stanford.nlp.pipeline.AnnotatorFactory
import edu.stanford.nlp.pipeline.AnnotatorPool
import edu.stanford.nlp.pipeline.DefaultPaths
import edu.stanford.nlp.pipeline.DeterministicCorefAnnotator
import edu.stanford.nlp.pipeline.MorphaAnnotator
import edu.stanford.nlp.pipeline.NERCombinerAnnotator
import edu.stanford.nlp.pipeline.POSTaggerAnnotator
import edu.stanford.nlp.pipeline.PTBTokenizerAnnotator
import edu.stanford.nlp.pipeline.ParserAnnotator
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.sequences.SeqClassifierFlags
import edu.stanford.nlp.trees.Tree
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation
import edu.stanford.nlp.trees.semgraph.SemanticGraph
import edu.stanford.nlp.trees.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation
import edu.stanford.nlp.util.CoreMap
import edu.stanford.nlp.util.XMLUtils.XMLTag
import java.io.File
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Properties
import java.util.zip.GZIPInputStream
import org.eclipse.xtext.xbase.lib.Pair
import org.osgi.framework.BundleContext
import reprotool.dmodel.api.classifiers.MaxentClassifier
import reprotool.dmodel.nlp.ssplit.MaxentSSplitAnnotator
import reprotool.predict.logging.ReprotoolLogger
import spec.EntityLink
import spec.SpecDocument
import spec.SpecFactory
import spec.SpecWord

class StanfordPoolHackAnnotatorFactory extends AnnotatorFactory {
	@Property Annotator annotator
	new() {
		super(new Properties)
	}
	new(Annotator a) { this(); annotator = a }
	override create() { annotator }
	override signature() '''hacked'''
}

@Component(provide=ReprotoolLinguisticPipeline, immediate=true)
class ReprotoolLinguisticPipeline extends AnnotationPipeline {
	
	boolean beVerbose = false
	val hackedAnnotatorPool = new AnnotatorPool
	 
	private extension ReprotoolLogger logger
	@Reference def void setLogger(ReprotoolLogger logger) {
		this.logger = logger
	}

	def List<String> getXmlContext(CoreLabel token) {
		token.get(XmlContextAnnotation)
	}
	
	def Map<Integer, CorefChain> getCorefChain(Annotation document) {
		document.get(CorefChainAnnotation)
	}
	
	def addAnnotator(String name, Annotator a) {
		'''Adding "«name»" annotator to the pipeline'''.info
		addAnnotator(a)
		hackedAnnotatorPool.register(name, new StanfordPoolHackAnnotatorFactory(a))
	}
	
	var Thread activationThread
	
	def void checkActivationInterrupted() {
		if(Thread.currentThread.isInterrupted)
			throw new InterruptedException("Activation thread interrupted")
	}
	
	/** Registers all annotators relevant for Reprotool */
	@Activate def void activate(BundleContext bundleContext) {
		
		"REPROTOOL Linguistic Pipeline - Activation Started in a background thread".info
		
		activationThread = new Thread([|
			
			try {
				
				// hacking the poor Stanford implementation which refers to this static field from multiple places in the code
				StanfordCoreNLP.getDeclaredField("pool") => [
					accessible = true
					set(null, hackedAnnotatorPool)
				]
		
				val MAXSENTENCELEN = 256
				
				// TOKENIZE: Identifies tokens using edu.stanford.nlp.process.PTBTokenizer
				// ========================================================================
				"tokenize".addAnnotator(new PTBTokenizerAnnotator(
					beVerbose,
					"tokenizeNLs,invertible,ptb3Escaping=true" // options
				))
		      	checkActivationInterrupted
				
				// CLEAN: Removes XML tags possibly selectively keeps the text between them.
				// ========================================================================
				"cleanxml".addAnnotator(new ReprotoolXmlAnnotator(
					".*", // clean xml tags
					"[hH][123456]|p|P|ul|UL|ol|OL",	// sentence ending tags
					"datetime|date", // date tags
					"style|STYLE|script|SCRIPT|head|HEAD", // don't create tokens from text within these tags
					true // allow flaws
				))
		      	checkActivationInterrupted
		
				// SSPLIT: Sentence splitter (based on the OpenNLP MaxEnt classifier)
				// ========================================================================
				val ssplitStream = bundleContext.bundle.getResource("reprotool/predict/models/ssplit/ssplit.maxent.gz").openStream
				val ssplitModel = MaxentClassifier.loadMaxentModel(ssplitStream)
				ssplitStream.close
				val ssplitAnnotator = new MaxentSSplitAnnotator
				ssplitAnnotator.setMaxentModel(ssplitModel)
				"ssplit".addAnnotator(ssplitAnnotator)
		      	checkActivationInterrupted
				
				// POSTAG: Maximum Entropy POS-tagger
				// ========================================================================
				"pos".addAnnotator(new POSTaggerAnnotator(
					//DefaultPaths.DEFAULT_POS_MODEL, // POS model location (on classpath)
					//"reprotool/dmodel/nlp/models/wsj-0-18-bidirectional-distsim.tagger",
					bundleContext.bundle.getResource("edu/stanford/nlp/models/pos-tagger/wsj-bidirectional/wsj-0-18-bidirectional-distsim.tagger").toExternalForm,
					beVerbose,
					MAXSENTENCELEN // maximum sentence length
				))
		      	checkActivationInterrupted
		
				// LEMMATIZER:
				// ========================================================================
				"lemma".addAnnotator(new MorphaAnnotator(beVerbose))
		      	checkActivationInterrupted
		
				// NER: Stanford NER
				// ========================================================================
				"ner".addAnnotator(new NERCombinerAnnotator(
					new NERClassifierCombiner(
						NERClassifierCombiner.APPLY_NUMERIC_CLASSIFIERS_DEFAULT,
		                false, // NumberSequenceClassifier.USE_SUTIME_DEFAULT couldn't be used because jollytime uses the default Class.getClassLoader instead of OSGi 
		                prepareCRFClassifier(bundleContext, DefaultPaths.DEFAULT_NER_THREECLASS_MODEL),
		                prepareCRFClassifier(bundleContext, DefaultPaths.DEFAULT_NER_MUC_MODEL),
		                prepareCRFClassifier(bundleContext, DefaultPaths.DEFAULT_NER_CONLL_MODEL)
		            ),
					beVerbose
				))
		      	checkActivationInterrupted
		
				// PARSER: Standard Lexicalized Parser of sentences
				// ========================================================================
				"parse".addAnnotator(new ParserAnnotator(
					DefaultPaths.DEFAULT_PARSER_MODEL, // trained model
					beVerbose,
					MAXSENTENCELEN, // maximum sentence length
					newArrayList("-retainTmpSubcategories") as String[] // flags
		      	))
		      	checkActivationInterrupted
		      	
				// COREF: Stanford Deterministic Coreference Resolution System
				// ========================================================================
				"dcoref".addAnnotator(new DeterministicCorefAnnotator(new Properties))
		      	checkActivationInterrupted
		      	
			} catch(Exception e) {
				e.message.error
			}
			
			"REPROTOOL Linguistic Pipeline - background thread finished".info
		])
		
		activationThread.start
	}
	
	@Deactivate def void deactivate(BundleContext bundleContext) {
		activationThread.interrupt
		activationThread.join
	}
	
	def private AbstractSequenceClassifier<CoreLabel> prepareCRFClassifier(BundleContext bundleContext, String resourceName) {
		val inputStream = bundleContext.bundle.getResource(resourceName).openStream

		new CRFClassifier(new SeqClassifierFlags) => [
			loadClassifier(new GZIPInputStream(inputStream))
		]
	}
	
	/** Performs analysis by reading the text from file */
	def Annotation analyzeTextFromFile(String fileName) {
//		val in = new ByteArrayInputStream(fileName.toString.bytes)
//		val out = new ByteArrayOutputStream
		
		return Files.toString(new File(fileName), Charsets.UTF_8).analyzeText

//		new Tidy => [
//			XHTML = true
//			breakBeforeBR = true
//			//hideComments = true
//			indentAttributes = false
//			smartIndent = false
//			wraplen = 0
//			dropEmptyParas = true			
//			wrapAttVals = false
//			parse(in, out)
//		]
	}
	
	/**
	 * Performs analysis of text which contains multiple sentences.
	 */
	def Annotation analyzeText(String text) {
		// create an empty Annotation just with the given text
		val document = new Annotation(text)

		// run all Annotators on this text
		annotate(document);
		
		return document
	}
	
	def toToken(CorefMention mention, Annotation document) {
		val sentIndex = mention.sentNum-1
		val tokenIndex = mention.headIndex-1
		val sentence = document.sentences.get(sentIndex)

		val Tree tree = sentence.get(TreeAnnotation)
		val nodes = tree.taggedLabeledYield

		val token = nodes.get(tokenIndex)
		
		// sanity checks
		if(token.sentIndex >= 0 && token.sentIndex != sentIndex) throw new RuntimeException("Sentence indexes do not match")
		if(token.index >= 0 && token.index != tokenIndex) throw new RuntimeException("Token indexes do not match")

		// saving important indexes into the token
		token.setSentIndex(sentIndex)
		token.setIndex(tokenIndex)
		
		return token
	}
	
	def getCoreferences(Annotation document) {
		val result = new HashMap<CoreLabel, CoreLabel>
		
		if(document.corefChain != null) {
			for(entry : document.corefChain.entrySet.map[value]) {
				if( ! entry.mentionsInTextualOrder.empty ) {
	
					// representative mention
					val rtoken = entry.representativeMention.toToken(document)
					
					// looking for the other mentions
					for( corefMention : entry.getMentionsInTextualOrder) {
						val mtoken = corefMention.toToken(document)
						result.put(mtoken, rtoken)
					}
				}
			}
		}
			
		return result
	}
	
	def List<CoreMap> getSentences(Annotation document) {
		document.get(SentencesAnnotation) 
	}
	
	def SemanticGraph getCollapsedCCProcessedDependencies(CoreMap sentence) {
		sentence.get(CollapsedCCProcessedDependenciesAnnotation)
	}

	def List<CoreLabel> getTokens(CoreMap sentence) {
		sentence.get(TokensAnnotation)
	}
	
	def String getWordStr(CoreLabel token) {
		token.get(TextAnnotation)
	}
	
	def XMLTag getInnermostXmlTag(CoreLabel token) {
		token.get(ReprotoolXmlAnnotator.InnermostXmlTagAnnotation)
	}
	
	def Integer getWordIndex(CoreLabel token) {
		token.get(IndexAnnotation)
	}
	
	def toEntityLink(XMLTag tag, Map<XMLTag,EntityLink> map) {

		if( ! "a".equalsIgnoreCase(tag.name))
			return null
			
		var entlink = map.get(tag)
		if(entlink == null) {
			
			// resolving the "href" attribute which represents the domain entity name
			var href = tag.attributes.get("href")
			if(href == null)			href = tag.attributes.get("HREF")
			if(href == null)			return null
			if(!href.startsWith("#"))	return null
			href = href.substring(1);
			// we have a valid href value at this point
			
			entlink = SpecFactory.eINSTANCE.createEntityLink
			entlink.entLabel = href

			map.put(tag, entlink)
		}
		return entlink
	}
	
	/** Convert analyzed document into a specification model */
	def SpecDocument analyzedDocToSpecDoc(Annotation document) {
		val specdoc = SpecFactory.eINSTANCE.createSpecDocument

		val tag2entlink = new HashMap<XMLTag, EntityLink>
		val idx2word = new HashMap<Pair<Integer, Integer>, SpecWord>

		document.sentences.forEach[ sentence, sentIndex |
			
			val specSentence = SpecFactory.eINSTANCE.createSpecSentence
			specdoc.sentences += specSentence

			// tokens to SpecWords including POS, LEMMA, ENTLINKS
			sentence.tokens.forEach[token, tokenIndex |
				val specWord = SpecFactory.eINSTANCE.createSpecWord
				specSentence.words += specWord

				// remember the position in the document and in the original sentence
				idx2word.put(Pair.of(sentIndex, tokenIndex), specWord)
				
				specWord.original = token.wordStr
				specWord.posTag = token.tag
				specWord.lemma = token.lemma
				
				// is it linked to a domain entity (annotated by XMLTag <a>)
				val entLink = token.innermostXmlTag?.toEntityLink(tag2entlink)
				if(entLink != null) {
					entLink.linkedWords += specWord
					specSentence.entityLinks += entLink
				}
			]
			
			
			// this is the ROOT word of Stanford dependency graph of the current sentence
			val root = sentence.collapsedCCProcessedDependencies?.firstRoot
			if(root != null) {
				val word = idx2word.get( sentIndex -> root.index - 1 )
				word.sentence.semanticRootWord = word
			}
			
			// this is the Stanford dependency graph of the current sentence
			sentence.collapsedCCProcessedDependencies?.edgeIterable?.forEach[ edge |
				specSentence.typedDependencies += SpecFactory.eINSTANCE.createWordDependency => [
					setLabel(edge.relation.toString)
					setLinkGov(idx2word.get( sentIndex -> edge.governor.index-1  ))
					setLinkDep(idx2word.get( sentIndex -> edge.dependent.index-1 ))
				]
			]
		]

		document.coreferences.forEach[ token1, token2 |
			val word1 = idx2word.get( token1.sentIndex -> token1.wordIndex )
			val word2 = idx2word.get( token2.sentIndex -> token2.wordIndex )
			word1.corefRepMention = word2
		]
		
		return specdoc
	}
	
}

//class ReprotoolLinguisticPipelineTest {
//	extension ReprotoolLinguisticPipeline = new ReprotoolLinguisticPipeline
//	
//	@Test
//	def void testPipeline() {
//		
//		val text = '''
//		<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
//		<HTML>
//		<HEAD>
//			<META HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=utf-8">
//			<TITLE></TITLE>
//			<META NAME="GENERATOR" CONTENT="LibreOffice 4.0.0.3 (Linux)">
//			<META NAME="CREATED" CONTENT="0;0">
//			<META NAME="CHANGEDBY" CONTENT="Viliam Simko">
//			<META NAME="CHANGED" CONTENT="20130213;23105600">
//			<META NAME="CHANGEDBY" CONTENT="Viliam Simko">
//			<META NAME="CHANGEDBY" CONTENT="Viliam Simko">
//			<META NAME="CHANGEDBY" CONTENT="Viliam Simko">
//			<META NAME="CHANGEDBY" CONTENT="Viliam Simko">
//			<STYLE TYPE="text/css">
//			<!--
//				P { margin-bottom: 0.21cm; font-variant: normal }
//				P.western { font-style: normal }
//				H1 { margin-bottom: 0.21cm }
//				H1.ctl { font-family: "Lohit Hindi" }
//				H2 { margin-bottom: 0.21cm }
//				H2.ctl { font-family: "Lohit Hindi" }
//				H3 { margin-bottom: 0.21cm }
//				H3.ctl { font-family: "Lohit Hindi" }
//				H3.use-case-western { color: #800000 }
//				H3.use-case-cjk { color: #800000 }
//				H3.use-case-ctl { color: #800000; font-family: "Lohit Hindi" }
//				P.foam-use-case-summary-western { border: 1px solid #000000; padding: 0.25cm; font-style: italic }
//				P.foam-use-case-summary-cjk { border: 1px solid #000000; padding: 0.25cm }
//				P.foam-use-case-summary-ctl { border: 1px solid #000000; padding: 0.25cm }
//				KBD { color: #0000ff; background: transparent }
//				KBD.western { font-size: 10pt; font-weight: bold }
//				KBD.ctl { font-family: "Lohit Hindi", monospace }
//				A:link { color: #000000; background: #ffffcc; text-decoration: none }
//			-->
//			</STYLE>
//		</HEAD>
//		<BODY LANG="en-GB" LINK="#000000" DIR="LTR">
//		<!-- test -->
//		<H1 CLASS="western">Library System Specification</H1>
//		<H2 CLASS="western">1. Objective</H2>
//		<P CLASS="western">Some Media and <A HREF="#User">user</A> of a <A HREF="#Library">library</A> are managed by the <A HREF="#Library">library system</A>. 
//		</P>
//		<H2 CLASS="western">2. Operational Area</H2>
//		<P CLASS="western">The <A HREF="#Library">library system</A> is operated by the <A HREF="#Librarian">library staff (librarian)</A> and <A HREF="#User">library user (user)</A> through <A HREF="#Terminal">terminals</A>.
//		</P>
//		<H2 CLASS="western">3. Product overview</H2>
//		<P CLASS="western">The <A HREF="#Library">library system</A> contains
//		a <A HREF="#UserAdministration">user administration</A> and a <A HREF="#MediaAdministration">media
//		administration</A>. 
//		</P>
//		<H3 CLASS="western">3.1 User administration</H3>
//		<P CLASS="western">The <A HREF="#UserAdministration">user administration</A> contains a <A HREF="#UserAccount">user account</A> for each <A HREF="#User">user</A> which contains all <A HREF="#User">user</A>
//		data.
//		A <A HREF="#Librarian">librarian</A> is able to create a new <A HREF="#UserAccount">user account</A>, to edit and to delete an existing <A HREF="#UserAccount">user account</A>.
//		A <A HREF="#User">user</A> is able to register at the <A HREF="#Library">system</A> with his <A HREF="#UserAccount.userNumber">user number</A>, to manage his <A HREF="#UserAccount">useraccount</A>, and to extend the <A HREF="#Instance.rentalPeriod">media's rental period</A>.
//		A password is not necessary because the <A HREF="#IdentificationCard.userNumber">user number</A> on the <A HREF="#IdentificationCard">identification card</A> is read with a <A HREF="#BarCodeScanner">bar code scanner</A>.
//		</P>
//		</BODY>
//		</HTML>
//		'''
//		
//		val in = new ByteArrayInputStream(text.toString.bytes)
//		val out = new ByteArrayOutputStream
//		new Tidy => [
//			XHTML = true
//			breakBeforeBR = true
//			//hideComments = true
//			indentAttributes = false
//			smartIndent = false
//			wraplen = 0
//			dropEmptyParas = true			
//			wrapAttVals = false
//			parse(in, out)
//		]
//		
//		val specDoc = out.toString.analyzeText.analyzedDocToSpecDoc
//		specDoc.sentences.forEach[println(it)]
//
//		new XMIResourceImpl => [
//			contents += specDoc
//			save(new FileOutputStream("out2.xmi"), null)
////			save(System.out, null)
//		]
//		println("done")
//	}
//}