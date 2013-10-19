package reprotool.dmodel.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import opennlp.model.MaxentModel;

import org.apache.log4j.Logger;

import reprotool.dmodel.api.classifiers.MaxentClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetBeginAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetEndAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.ForcedSentenceEndAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.util.CoreMap;

/**
 * Sentence splitter based on the MaxEnt approach.
 * The code is inspired by the OpenNLP sentence splitter which uses
 * Maximum Entropy models to predict the boundaries of sentences.
 * 
 * Used annotations:
 * - {@link TokensAnnotation} is assumed as the input
 * - {@link SentencesAnnotation} is generated for each sentence
 * - {@link ForcedSentenceEndAnnotation} causes a forced sentence split
 * 
 * @author Viliam Simko
 */
public class MaxentSSplitAnnotator implements Annotator {

	final static private Logger logger = Logger.getLogger(MaxentSSplitAnnotator.class);
	
	final static public Pattern EOS_PREFILTER_PATTERN = Pattern.compile("[.?!]");
	final static public String EOS_POSITIVE_OUTCOME = "S";
	final static public String EOS_NEGATIVE_OUTCOME = "N";
	final static public String NEWLINE_TOKEN = "*NL*";

	final private MaxentModel model;
	
	public MaxentSSplitAnnotator(MaxentModel model) {
		this.model = model;
	}
	
	public MaxentSSplitAnnotator(final String maxentModelFileName) throws Exception {
		logger.info("Reding a Maxent model for sentence splitter");
		model = MaxentClassifier.loadMaxentModel(maxentModelFileName);
	}
	
	@Override
	public void annotate(final Annotation annotation) {
		if (annotation.has(CoreAnnotations.TokensAnnotation.class)) {

			// get text and tokens from the document
			final String text = annotation.get(CoreAnnotations.TextAnnotation.class);
			final List<CoreLabel> tokens = annotation.get(CoreAnnotations.TokensAnnotation.class);

			// assemble the sentence annotations
			int tokenOffset = 0;
			final List<CoreMap> sentences = new ArrayList<CoreMap>();
			
			for (final List<CoreLabel> sentenceTokens : processTokens(tokens)) {

				if (sentenceTokens.isEmpty())
					throw new RuntimeException("unexpected empty sentence: " + sentenceTokens);

				// get the sentence text from the first and last character offsets
				final int begin = sentenceTokens.get(0).get(CharacterOffsetBeginAnnotation.class);
				final int last = sentenceTokens.size() - 1;
				final int end = sentenceTokens.get(last).get(CharacterOffsetEndAnnotation.class);
				final String sentenceText = text.substring(begin, end);

				// create a sentence annotation with text and token offsets
				final Annotation sentence = new Annotation(sentenceText);
				sentence.set(CharacterOffsetBeginAnnotation.class, begin);
				sentence.set(CharacterOffsetEndAnnotation.class, end);
				sentence.set(CoreAnnotations.TokensAnnotation.class, sentenceTokens);
				sentence.set(CoreAnnotations.TokenBeginAnnotation.class, tokenOffset);
				tokenOffset += sentenceTokens.size();
				sentence.set(CoreAnnotations.TokenEndAnnotation.class, tokenOffset);

				// add the sentence to the list
				sentences.add(sentence);
			}

			// add the sentences annotations to the document
			annotation.set(CoreAnnotations.SentencesAnnotation.class, sentences);

		} else
			throw new RuntimeException("unable to find words/tokens in: "
					+ annotation);
	}
	
	/**
	 * Preparing an array containing the trimmed version of the original text
	 * stored in the tokens it will be used by the context generator later.
	 * @param tokens
	 * @return an array of trimmed text from tokens
	 */
	final static public String[] getTrimmedTokens(final List<CoreLabel> tokens) {
		final String[] trimmedStrTokens = new String[tokens.size()];
		for(int i=0; i<trimmedStrTokens.length; ++i) {
			trimmedStrTokens[i] = tokens.get(i).originalText().trim();
		}
		return trimmedStrTokens;
	}
	
	private List<List<CoreLabel>> processTokens(final List<CoreLabel> tokens) {

		final MaxentSSplitCtxGen ctxgen = new MaxentSSplitCtxGen( getTrimmedTokens(tokens) );
		final List<List<CoreLabel>> sentences = new ArrayList<>(); // result
		List<CoreLabel> currentSentence = new ArrayList<>(); // the sentence being built
		
		// using a variable should be more efficient than calling the size() method multiple times
		final int tokensLen = tokens.size();
		for(int i=0; i<tokensLen; ++i) {
			
			final CoreLabel tok = tokens.get(i); // iterating over tokens
			final String tokText = tok.originalText();

			boolean shouldSplitNow = false;
			
			// handling forced split indicated by ForcedSentenceEndAnnotation
			final Boolean forcedSplit = tok.get(ForcedSentenceEndAnnotation.class);
			shouldSplitNow  |=  forcedSplit!=null  &&  forcedSplit.booleanValue();
			
			// predict the end of sentence
			if( EOS_PREFILTER_PATTERN.matcher(tokText).matches() ) {
				//System.out.println("Trying to predict:"+tokText);
				final String[] context = ctxgen.extractContextOf(i);
				shouldSplitNow |= predictEOS(context);
			}
			
			// add the token to the current sentence
			if(! tok.value().equals(NEWLINE_TOKEN))
				currentSentence.add(tok);
			
			// now applying the split operation
			if(shouldSplitNow && ! currentSentence.isEmpty()) {
				sentences.add(currentSentence);
				currentSentence = new ArrayList<>(); // new sentence
			}
		}
		
		// flush the last sentence
		if(!currentSentence.isEmpty())
			sentences.add(currentSentence);
		
		return sentences;
	}
	
	/**
	 * This is the actual method, where the classification takes place.
	 * Given the context features, the classifier uses a MaxEnt model to predict the outcome feature.
	 */
	private boolean predictEOS(final String[] context) {
		
		// computing probabilities of the possible outcome classes
		// in our case, this is just either EOS_POSITIVE_OUTCOME or  
		final double[] probs = model.eval(context);
		final String outcome = model.getBestOutcome(probs);	// using the probabilities to get the best outcome
		return outcome.equals(EOS_POSITIVE_OUTCOME);
	}
}
