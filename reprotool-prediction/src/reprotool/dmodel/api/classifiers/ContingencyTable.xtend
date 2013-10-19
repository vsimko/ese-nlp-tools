package reprotool.dmodel.api.classifiers

import org.eclipse.xtend.lib.Property
import org.junit.Assert
import org.junit.Test
import java.util.Set

class ContingencyTable {
	
	private val Set<String> positiveAnswers
	
	@Property private int truePositive = 0
	@Property private int trueNegative = 0
	@Property private int falsePositive = 0
	@Property private int falseNegative = 0
	@Property private int total = 0
	
	new(String... positiveAnswers) {
		this(newHashSet(positiveAnswers))
	}
	
	new(Set<String> positiveAnswers) {
		this.positiveAnswers = positiveAnswers
	}

	new(double f1) {
		positiveAnswers = newHashSet("n/a")
		falsePositive = 1000000
		falseNegative = falsePositive
		truePositive = Math.round( f1*falsePositive / (1 - f1) ) as int
	}
	
	/**
	 * Adds a new sample to the F1 measure computation.
	 * @param guess
	 * @param ans
	 */
	def void updateSample(String guess, String ans) {
		total = total + 1
		
		if (guess == ans) {
			if(positiveAnswers.contains(guess))
				truePositive = truePositive + 1
			else
				trueNegative = trueNegative + 1
		} else {
			if(positiveAnswers.contains(guess))
				falsePositive = falsePositive + 1
			else
				falseNegative = falseNegative + 1 
		}
	}
	
	/**
	 * Helper method which adds multiple samples from two equally sized lists.
	 * @param guess
	 * @param ans
	 */
	def void updateMultipleSamples(Iterable<String> guess, Iterable<String> ans) {
		
		val guessIter = guess.iterator
		val ansIter = ans.iterator
		
		while(guessIter.hasNext) {
			if(! ansIter.hasNext)
				throw new Exception("The set of guesses and gold samples must have equal size")
				
			updateSample(guessIter.next, ansIter.next);
		}
	}
	
	/**
	 * aka "Positive predictive value (PPV)"
	 * @return Precision = TP / (TP + FP)
	 */
	def getPrecision() {
		if(truePositive + falsePositive == 0)
			return Double.NaN

		truePositive / (truePositive + falsePositive) as double
	}
	
	def getPrecision100() {
		(precision * 100) as int
	}
	
	def getAccuracy() {
		if(total == 0)
			return Double.NaN

		return (truePositive + trueNegative) / (total as double)
	}
	
	def getAccuracy100() {
		(accuracy * 100) as int
	}

	/**
	 * aka "False positive rate (FPR)"
	 */
	def getFallOut() {
//		if(falsePositive + trueNegative == 0)
//			return Double.NaN
//		
//		return falsePositive / (falsePositive + trueNegative) as double
		return 1 - specificity
	}
	
	def getFallOut100() {
		(fallOut * 100) as int
	}
	
	/**
	 * @return Specificity = TN / (TN + FP)
	 */
	def getSpecificity() {
		if(trueNegative + falsePositive == 0)
			return Double.NaN
			
		return trueNegative / (trueNegative + falsePositive) as double
	}
	
	def getSpecificity100() {
		(specificity * 100) as int
	}
	
	/**
	 * aka "Sensitivity" in some literature.
	 * aka "True positive rate (TPR)"
	 * aka "Hit rate"
	 * @return Recall = TP / (TP + FN)
	 */
	def getRecall() {
		if(truePositive + falseNegative == 0)
			return Double.NaN
		
		return truePositive / (truePositive + falseNegative) as double
	}

	def getRecall100() {
		(recall * 100) as int
	}
	
	/**
	 * @return F1 = 2 * Precision * Recall / (Precision + Recall)
	 */
	def getF1Measure() {
		val P = precision
		val R = recall
		return (2 * P * R) / (P + R)
	}
	
	def getF1Measure100() {
		(f1Measure * 100) as int
	}
	
	/**
	 * Matthews correlation coefficient.
	 * 
	 * aka "phi coefficient"
	 * aka "mean square contingency coefficient"
	 * 
	 * The Matthews correlation coefficient is used in machine learning
	 * as a measure of the quality of binary (two-class) classifications.
	 * It takes into account true and false positives and negatives and
	 * is generally regarded as a balanced measure which can be used even
	 * if the classes are of very different sizes.
	 * The MCC is in essence a correlation coefficient between the observed
	 * and predicted binary classifications.
	 * 
	 * @see http://en.wikipedia.org/wiki/Matthews_correlation_coefficient
	 * 
	 * @return value between −1 and +1.
	 * 	A coefficient of +1 represents a perfect prediction,
	 * 	0 no better than random prediction and −1 indicates
	 * 	total disagreement between prediction and observation. 
	 */
	def getMCC() {
		
		val d1 = truePositive + falsePositive
		val d2 = truePositive + falseNegative
		val d3 = trueNegative + falsePositive
		val d4 = trueNegative + falseNegative
		
		val double denominator = if(d1 == 0 || d2 == 0 || d3 == 0 || d4 == 0) 1 else d1*d2*d3*d4
		
		return ( truePositive*trueNegative - falsePositive*falseNegative ) / denominator
	}

	override toString() '''
		Positive answers: «positiveAnswers.join(", ")»
		Total Samples:   «total»
		TP:«truePositive» FP:«falsePositive»
		FN:«falseNegative» TN:«falseNegative»

		Precision:   «precision * 100»
		Recall:      «recall * 100»
		F1-measure:  «f1Measure * 100»
		Specificity: «specificity * 100»
	'''
}

class ContingencyTableTest {

	@Test def void testDummyContingencyTable() {
		newArrayList(0.5, 0.913, 0.1246).forEach[ d |
			new ContingencyTable(d) => [
				Assert.assertEquals(d, f1Measure, 0.01)
				Assert.assertEquals(d, precision, 0.01)
				Assert.assertEquals(d, recall,    0.01)
				
				//println(it)
			]
		]
	}

	@Test def void testContingencyTable() {
		new ContingencyTable("c=A") => [

			updateSample("c=A", "c=A")
			Assert.assertEquals(1, total)
			Assert.assertEquals(100, precision100)
			Assert.assertEquals(100, recall100)
			Assert.assertEquals(100, f1Measure100)
			Assert.assertEquals(0, specificity100)
			Assert.assertEquals(0, fallOut100)
			Assert.assertEquals(100, accuracy100)
			
			
			updateSample("c=A", "c=B")
			Assert.assertEquals(2, total)
			Assert.assertEquals(50, precision100)
			Assert.assertEquals(100, recall100)
			Assert.assertEquals(66, f1Measure100)
			Assert.assertEquals(0, specificity100)
			Assert.assertEquals(100, fallOut100)
			Assert.assertEquals(50, accuracy100)
			
			updateSample("c=B", "c=A")
			Assert.assertEquals(3, total)
			Assert.assertEquals(50, precision100)
			Assert.assertEquals(50, recall100)
			Assert.assertEquals(50, f1Measure100)
			Assert.assertEquals(0, specificity100)
			Assert.assertEquals(100, fallOut100)
			Assert.assertEquals(33, accuracy100)
			
			updateSample("c=B", "c=B")
			Assert.assertEquals(4, total)
			Assert.assertEquals(50, precision100)
			Assert.assertEquals(50, recall100)
			Assert.assertEquals(50, f1Measure100)
			Assert.assertEquals(50, specificity100)
			Assert.assertEquals(50, fallOut100)
			Assert.assertEquals(50, accuracy100)

			updateSample("c=B", "c=A")
			Assert.assertEquals(5, total)
			Assert.assertEquals(50, precision100)
			Assert.assertEquals(33, recall100)
			Assert.assertEquals(40, f1Measure100)
			Assert.assertEquals(50, specificity100)
			Assert.assertEquals(50, fallOut100)
			Assert.assertEquals(40, accuracy100)
			
			//println(it)
		]
	}

}
