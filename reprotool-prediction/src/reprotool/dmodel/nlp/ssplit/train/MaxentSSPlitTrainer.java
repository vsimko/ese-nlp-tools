package reprotool.dmodel.nlp.ssplit.train;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import reprotool.dmodel.api.classifiers.MaxentClassifier;
import reprotool.dmodel.api.samples.FeatureEvent;
import reprotool.dmodel.nlp.ssplit.MaxentSSplitAnnotator;
import reprotool.dmodel.nlp.ssplit.MaxentSSplitCtxGen;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;

public class MaxentSSPlitTrainer {

	final public Iterable<FeatureEvent> generateTrainingSamples(final String trainingTextStr) throws IOException {
		return generateTrainingSamples(new StringReader(trainingTextStr));
	}
	
	final public Iterable<FeatureEvent> generateTrainingSamples(final Reader reader) throws IOException {

		final Tokenizer<CoreLabel> tokenizer = new PTBTokenizer<>(reader,
				new CoreLabelTokenFactory(),
				"tokenizeNLs,invertible,ptb3Escaping=true");

		final List<CoreLabel> tokens = tokenizer.tokenize();

		// filter out *NL* and mark previous chars as sentence splitting token
		final List<CoreLabel> filteredTokens = new ArrayList<>();
		final List<Boolean> filteredTokensMark = new ArrayList<>();

		for (int i = 0; i < tokens.size(); ++i) {
			final CoreLabel tok = tokens.get(i);
			final String nextTokenValue = i + 1 < tokens.size() ? tokens.get(i + 1).value() : null;

			if (!MaxentSSplitAnnotator.NEWLINE_TOKEN.equals(tok.value())) {
				filteredTokens.add(tok);
				filteredTokensMark.add(MaxentSSplitAnnotator.NEWLINE_TOKEN.equals(nextTokenValue));
			}
		}

		// sanity checks
		if (filteredTokens.size() != filteredTokensMark.size())
			throw new RuntimeException("Nasty programming error");

		final String[] trimmedTokens = MaxentSSplitAnnotator.getTrimmedTokens(filteredTokens);
		final MaxentSSplitCtxGen ctxgen = new MaxentSSplitCtxGen( trimmedTokens );

		final List<FeatureEvent> samples = new ArrayList<>();

		for (int i = 0; i < trimmedTokens.length; ++i) {
			if (MaxentSSplitAnnotator.EOS_PREFILTER_PATTERN.matcher(trimmedTokens[i]).matches()) {
				final String[] context = ctxgen.extractContextOf(i);
				final String outcome = filteredTokensMark.get(i)
						? MaxentSSplitAnnotator.EOS_POSITIVE_OUTCOME
						: MaxentSSplitAnnotator.EOS_NEGATIVE_OUTCOME;

				samples.add(FeatureEvent.createFrom(outcome, context, null));
			}
		}

		return samples;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		if(args.length != 1) {
			System.err.println("USAGE: This tools requires a single parameter [training-file].");
			System.exit(1);
		}
		
		final String inTrainingFileName = args[0];
		final String outModelFileName = inTrainingFileName.replaceFirst("\\.[^.]+$", "") + ".gz";
		
		if(inTrainingFileName.equals(outModelFileName))
			throw new RuntimeException("Use a different suffix for the input file because we want to save the model to: " + outModelFileName);
		
		System.out.println("Using the training data from file: " + inTrainingFileName);
		final Reader reader = new FileReader(inTrainingFileName);
	
		final MaxentClassifier classifier = MaxentClassifier.createEmptyClassifier();
		classifier.train(new MaxentSSPlitTrainer().generateTrainingSamples(reader));
		classifier.saveModelToFile(outModelFileName);
		
		System.out.println("DONE. The trained MaxEnt model was saved to: " + outModelFileName);
	}
}
