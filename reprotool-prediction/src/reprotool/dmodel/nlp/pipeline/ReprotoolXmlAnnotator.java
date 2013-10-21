package reprotool.dmodel.nlp.pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.AfterAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.BeforeAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.DocDateAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.ForcedSentenceEndAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.OriginalTextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.XmlContextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.XMLUtils;
import edu.stanford.nlp.util.XMLUtils.XMLTag;

/**
 * The original CleanXmlAnnotator modified for Reprotool.
 * 
 * @author Viliam Simko
 */
public class ReprotoolXmlAnnotator implements Annotator {

	/**
	 * A regular expression telling us where to look for tokens we care about
	 */
	private final Pattern xmlTagMatcher;

	public static final String DEFAULT_XML_TAGS = ".*";

	/**
	 * This regular expression tells us which tags end a sentence... for
	 * example, &lt;p&gt; would be a great candidate
	 */
	private final Pattern sentenceEndingTagMatcher;

	public static final String DEFAULT_SENTENCE_ENDERS = "";

	/**
	 * This tells us which XML tags wrap document date
	 */
	private final Pattern dateTagMatcher;

	public static final String DEFAULT_DATE_TAGS = "datetime|date";

	/**
	 * This setting allows handling of flawed XML. For example, a lot of the
	 * news articles we parse go: <br>
	 * &lt;text&gt; <br>
	 * &lt;turn&gt; <br>
	 * &lt;turn&gt; <br>
	 * &lt;turn&gt; <br>
	 * &lt;/text&gt; <br>
	 * ... eg, no closing &lt;/turn&gt; tags.
	 */
	private final boolean allowFlawedXml;

	public static final String DEFAULT_SKIPPED_TAGS_REGEX = "";
	public static final boolean DEFAULT_ALLOW_FLAWS = true;

	/**
	 * Content of these tags will be skipped and no tokens will be produced for them.
	 * However, the content will be appended to the before/after annotations of the surrounding tags.
	 */
	private String skippedTagsRegex;

	public ReprotoolXmlAnnotator() {
		this(	DEFAULT_XML_TAGS,
				DEFAULT_SENTENCE_ENDERS,
				DEFAULT_DATE_TAGS,
				DEFAULT_SKIPPED_TAGS_REGEX,
				DEFAULT_ALLOW_FLAWS );
	}

	public ReprotoolXmlAnnotator(
			final String xmlTagsToRemove,
			final String sentenceEndingTags,
			final String dateTags,
			final String skippedTagsRegex,
			boolean allowFlawedXml) {
		
		this.skippedTagsRegex = skippedTagsRegex;
		this.allowFlawedXml = allowFlawedXml;
		
		if (xmlTagsToRemove != null) {
			xmlTagMatcher = Pattern.compile(xmlTagsToRemove);
			if (sentenceEndingTags != null && sentenceEndingTags.length() > 0) {
				sentenceEndingTagMatcher = Pattern.compile(sentenceEndingTags);
			} else {
				sentenceEndingTagMatcher = null;
			}
		} else {
			xmlTagMatcher = null;
			sentenceEndingTagMatcher = null;
		}

		if (dateTags != null) {
			dateTagMatcher = Pattern
					.compile(dateTags, Pattern.CASE_INSENSITIVE);
		} else {
			dateTagMatcher = null;
		}
	}

	public void annotate(final Annotation annotation) {
		if (annotation.has(TokensAnnotation.class)) {
			final List<CoreLabel> tokens = annotation
					.get(TokensAnnotation.class);
			final List<CoreLabel> dateTokens = new ArrayList<CoreLabel>();
			final List<CoreLabel> newTokens = process(tokens, dateTokens);
			
			// We assume that if someone is using this annotator, they don't
			// want the old tokens any more and get rid of them
			annotation.set(TokensAnnotation.class, newTokens);

			// if the doc date was found, save it. it is used by SUTime (inside
			// the "ner" annotator)
			if (dateTokens.size() > 0) {
				final StringBuffer os = new StringBuffer();
				boolean first = true;
				for (CoreLabel t : dateTokens) {
					if (!first)
						os.append(" ");
					os.append(t.word());
					first = false;
				}
				// System.err.println("DOC DATE IS: " + os.toString());
				annotation.set(DocDateAnnotation.class, os.toString());
			}
		}
	}

	public List<CoreLabel> process(final List<CoreLabel> tokens) {
		return process(tokens, null);
	}

	/**
	 * Utility function.
	 * @param tags
	 * @return
	 */
	private List<String> getTagNames(List<XMLTag> tags) {
		final List<String> newList = new ArrayList<>(tags.size());
		for (XMLTag tag : tags) {
			newList.add(tag.name);
		}
		return Collections.unmodifiableList(newList);
	}
	
	/**
	 * Utility function.
	 * @param tags
	 * @param desiredTagName
	 * @return
	 */
	private boolean containsMatchingTagName(final List<XMLTag> tags, final String tagNameRegex) {
		for (XMLTag tag : tags) {
			if(tag.name.matches(tagNameRegex))
				return true;
		}
		return false;
	}
	
	/**
	 * Used in REPROTOOL to store the XMLTag object for
	 * the innermost element in which this token was contained.
	 */
	public static class InnermostXmlTagAnnotation implements CoreAnnotation<XMLTag> {
		public Class<XMLTag> getType() {
			return XMLTag.class;
		}
	}
	
	public List<CoreLabel> process(final List<CoreLabel> tokens, final List<CoreLabel> dateTokens) {

		// As we are processing, this stack keeps track of which tags we
		// are currently inside
		final Stack<XMLTag> enclosingTags = new Stack<>();

		// here we keep track of the current enclosingTags
		// this lets multiple tokens reuse the same tag stack
		List<String> currentTagSet = null;
		// How many matching tags we've seen
		int matchDepth = 0;
		// stores the filtered tags as we go
		final List<CoreLabel> newTokens = new ArrayList<CoreLabel>();

		// we use this to store the before & after annotations if the
		// tokens were tokenized for "invertible"
		StringBuilder removedText = new StringBuilder();
		// we keep track of this so we can look at the last tag after
		// we're outside the loop

		for (CoreLabel token : tokens) {
			final String word = token.word().trim();
			final XMLUtils.XMLTag tag = XMLUtils.parseTag(word);

			// If it's not a tag, we do manipulations such as unescaping
			if (tag == null) {
				
				// by Viliam Simko
				if(!enclosingTags.empty()) {
					if( containsMatchingTagName(enclosingTags, skippedTagsRegex) ) {
						String currentRemoval = token.get(BeforeAnnotation.class);
						if (currentRemoval != null)
							removedText.append(currentRemoval);

						currentRemoval = token.get(OriginalTextAnnotation.class);
						if (currentRemoval != null)
							removedText.append(currentRemoval);
						
						currentRemoval = token.get(AfterAnnotation.class);
						if (currentRemoval != null)
							removedText.append(currentRemoval);
						continue;
					}
				}
				
				// TODO: put this into the lexer instead of here
				token.setWord(XMLUtils.unescapeStringForXML(token.word()));
				// TODO: was there another annotation that also represents the
				// word?
				if (matchDepth > 0 || xmlTagMatcher == null
						|| xmlTagMatcher.matcher("").matches()) {
					newTokens.add(token);
				}
								
				// if we removed any text, and the tokens are "invertible" and
				// therefore keep track of their before/after text, append
				// what we removed to the appropriate tokens
				if (removedText.length() > 0) {
					boolean added = false;
					final String before = token.get(BeforeAnnotation.class);
					if (before != null) {
						token.set(BeforeAnnotation.class, removedText + before);
						added = true;
					}
					if (added && newTokens.size() > 1) {
						final CoreLabel previous = newTokens.get(newTokens.size() - 2);
						final String after = previous.get(AfterAnnotation.class);
						if (after != null)
							previous.set(AfterAnnotation.class, after + removedText);
						else
							previous.set(AfterAnnotation.class,	removedText.toString());
					}
					removedText = new StringBuilder();
				}
				if (currentTagSet == null) {
					// We wrap the list in an unmodifiable list because we reuse
					// the same list object many times. We don't want to
					// let someone modify one list and screw up all the others.
					currentTagSet = getTagNames(enclosingTags);
				}
				
				token.set(XmlContextAnnotation.class, currentTagSet);
				
				// by Viliam Simko: stores the innermost XML Tag in the token
				// this is useful when we want to annotate multiple words using HTML tags
				if(!enclosingTags.empty()) {
					token.set(InnermostXmlTagAnnotation.class, enclosingTags.lastElement());
				}

				// is this token part of the doc date sequence?
				if (dateTagMatcher != null
						&& currentTagSet.size() > 0
						&& dateTagMatcher.matcher(
								currentTagSet.get(currentTagSet.size() - 1))
								.matches()) {
					dateTokens.add(token);
				}

				continue;
			}

			// At this point, we know we have a tag
				
			// we are removing a token and its associated text...
			// keep track of that
			String currentRemoval = token.get(BeforeAnnotation.class);
			if (currentRemoval != null)
				removedText.append(currentRemoval);
			
			currentRemoval = token.get(OriginalTextAnnotation.class);
			if (currentRemoval != null)
				removedText.append(currentRemoval);
			
			if (token == tokens.get(tokens.size() - 1)) {
				currentRemoval = token.get(AfterAnnotation.class);
				if (currentRemoval != null)
					removedText.append(currentRemoval);
			}

			// If the tag matches the sentence ending tags, and we have some
			// existing words, mark that word as being somewhere we want
			// to end the sentence.
			if (sentenceEndingTagMatcher != null
					&& sentenceEndingTagMatcher.matcher(tag.name).matches()
					&& newTokens.size() > 0) {
				CoreLabel previous = newTokens.get(newTokens.size() - 1);
				previous.set(ForcedSentenceEndAnnotation.class, true);
			}

			if (xmlTagMatcher == null)
				continue;

			if (tag.isSingleTag) {
				continue;
			}
			// at this point, we can't reuse the "currentTagSet" vector
			// any more, since the current tag set has changed
			currentTagSet = null;
			if (tag.isEndTag) {
				while (true) {
					if (enclosingTags.size() == 0) {
						throw new IllegalArgumentException("Got a close tag "
								+ tag.name + "which does not match "
								+ "any open tag");
					}

					final String lastTag = enclosingTags.pop().name;

					if (xmlTagMatcher.matcher(lastTag).matches()) {
						--matchDepth;
					}
					if (lastTag.equals(tag.name))
						break;
					if (!allowFlawedXml)
						throw new IllegalArgumentException(
								"Mismatched tags... " + tag.name + " closed a "
										+ lastTag + " tag.");
				}
				if (matchDepth < 0) {
					// this should be impossible, since we already assert that
					// the tags match up correctly
					throw new AssertionError(
							"Programming error?  We think there "
									+ "have been more close tags than open tags");
				}
			} else if(tag.name.startsWith("!")) {
				System.out.println("XML COMMENT:" + tag);
			} else {
			
				// open tag, since all other cases are exhausted
				enclosingTags.push(tag);

				if (xmlTagMatcher.matcher(tag.name).matches())
					matchDepth++;
			}
		}

		if (enclosingTags.size() > 0 && !allowFlawedXml) {
			throw new IllegalArgumentException("Unclosed tags, starting with "
					+ enclosingTags.pop());
		}

		// If we ended with a string of xml tokens, that text needs to be
		// appended to the "AfterAnnotation" of one of the tokens...
		// Note that we clear removedText when we see a real token, so
		// if removedText is not empty, that must be because we just
		// dropped an xml tag. Therefore we ignore that old After
		// annotation, since that text was already absorbed in the Before
		// annotation of the xml tag we threw away
		if (newTokens.size() > 0 && removedText.length() > 0) {
			CoreLabel lastToken = newTokens.get(newTokens.size() - 1);
			// sometimes AfterAnnotation seems to be null even when we are
			// collecting before & after annotations, but OriginalTextAnnotation
			// is only non-null if we are invertible. Hopefully.
			if (lastToken.get(OriginalTextAnnotation.class) != null) {
				lastToken.set(AfterAnnotation.class, removedText.toString());
			}
		}

		return newTokens;
	}
}
