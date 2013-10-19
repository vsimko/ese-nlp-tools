package reprotool.dmodel.nlp;

import java.util.ArrayList;
import java.util.List;

/**
 * This can generate context for a token on <b>i-th</b> position
 * that includes features from <b>j-th</b> token to the left/right from the given <b>i-th</b> token.
 * 
 * <p>
 * A context is made of features.
 * Individual features are generated in methods prefixed by "feat_".
 * Everything is combined in the {@code extractContextOf()} method.
 * Therefore, if you want to add a new feature to the context generator,
 * you should create a new "feat_" method and call it from the {@code extractContextOf()} method.
 * </p>
 * 
 * Note: I'm not sure if newlines can be used as features for generating the context
 * because we use newlines as delimiters of sentences in our training data.
 * 
 * @author Viliam Simko
 */
public class MaxentSSplitCtxGen {
	
	final private String[] tokens;
	final private int tokensLen;

	/**
	 * @param tokens Tokens should be trimmed before they can be used.
	 */
	public MaxentSSplitCtxGen(final String[] tokens) {
		this.tokens = tokens;
		this.tokensLen = tokens.length;
		
		// sanity check
		for(int i=0; i<tokens.length; ++i) {
			final String tok = tokens[i];
			if( !tok.equals(tok.trim()))
				throw new RuntimeException(String.format("The token '%s' on position %d should be trimmed.", tok, i));
		}
	}
	
	// these values were selected just because they performed reasonably well
	// maybe other values are better
	final static private int CONTEXT_DEPTH = 3;
	final static private int SUFFIX_MAXLEN = 2;
	final static private int PREFIX_MAXLEN = 2;
	
	/**
	 * This is the most important function where the context is generated.
	 * @param i The index of the requested token
	 * @return The generated context as a vector of features
	 */
	final public String[] extractContextOf(final int i) {

		final List<String> context = new ArrayList<>();

		// the outer cycle is for including surrounding tokens to the context
		for(int offset = -CONTEXT_DEPTH; offset <= CONTEXT_DEPTH; ++offset) {
			context.add(feat_HasDigit(i+offset, i));
			context.add(feat_LengthCut(i+offset, i));
			
			// these cycles are for adding prefixes/suffixes of different lengths
			// - this cycle is for prefixes
			for(int len=1; len<=PREFIX_MAXLEN; ++len) {
				context.add(feat_Prefix(i+offset, i, len));
			}
			// - this cycle is for suffixes
			for(int len=1; len<=SUFFIX_MAXLEN; ++len) {
				context.add(feat_Suffix(i+offset, i, len));
			}
		}
		
		// filtering out empty contexts
		final List<String> filteredContext = new ArrayList<>();
		for (String f : context) {
			if(f != null && !f.isEmpty()) {
				filteredContext.add(f);
			}
		}
		return filteredContext.toArray(new String[filteredContext.size()]);
	}

	/**
	 * Computes the value of a token on the i-th position, however ignores newlines.
	 */
	private String tokenValue(final int i) {
		return tokens[i];
	}

	/**
	 * Feature: Does the word on i index contain a digit ?
	 * @param j position of a word near to the current word <b>i</b>
	 * @param i position of the current word
	 * @return dig.INTEGER=[true|false]
	 */
	private String feat_HasDigit(final int j, final int i) {
		if(j < 0 || j >= tokensLen) return null;
		return "dig." + (i-j) + "=" + tokenValue(j).matches("[0-9]");
	}
	
	final static private int LENGTH_CUT_MAX = 4;
	
	/**
	 * Feature: The length of the i-th word.
	 * However, if the length exceeds LENGTH_CUT_MAX, we just use the value LENGTH_CUT_MAX.
	 * @param j position of a word near to the current word <b>i</b>
	 * @param i position of the current word
	 * @return len4.INTEGER=INTEGER
	 */
	private String feat_LengthCut(final int j, final int i) {
		if(j < 0 || j >= tokensLen) return null;
		return "len4." + (i-j) + "=" + Math.min(tokenValue(j).length(), LENGTH_CUT_MAX);
	}
	
	/**
	 * Feature: Prefix taken from the i-th word.
	 * @param j position of a word near to the current word <b>i</b>
	 * @param i position of the current word
	 * @param prefixLen How many characters will be used as a prefix
	 * @return p.INTEGER.INTEGER=STRING
	 */
	private String feat_Prefix(final int j, final int i, int prefixLen) {
		if(j < 0 || j >= tokensLen) return null;
		final String txt = tokenValue(j);
		if(txt.isEmpty()) return null;
		if(prefixLen > txt.length()) return null;
		
		return "p." + prefixLen + "." + (i-j) + "=" + txt.substring(0, prefixLen);
	}

	/**
	 * Feature: Suffix taken from the i-th word.
	 * @param j position of a word near to the current word <b>i</b>
	 * @param i position of the current word
	 * @param suffixLen How many characters will be used as a suffix
	 * @return s.INTEGER.INTEGER=STRING
	 */
	private String feat_Suffix(final int j, final int i, int suffixLen) {
		if(j < 0 || j >= tokensLen) return null;
		final String txt = tokenValue(j);
		if(txt.isEmpty()) return null;
		if(suffixLen > txt.length()) return null;
		
		return "s." + suffixLen + "." + (i-j) + "=" + txt.substring(txt.length() - suffixLen, txt.length());
	}
}
