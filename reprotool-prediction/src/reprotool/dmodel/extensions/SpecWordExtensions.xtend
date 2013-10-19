package reprotool.dmodel.extensions

import java.util.List
import spec.SpecSentence
import spec.SpecWord
import java.util.ArrayList

class SpecWordExtensions {
	def static getRelativeWordInSentence(SpecWord word, int idxOffset) {
		val parentSentence = word.eContainer as SpecSentence
		val index = parentSentence.words.indexOf(word) + idxOffset
		
		if(index > -1 && index < parentSentence.words.size)
			parentSentence.words.get(index)
	}
	
	def static toIntParam(List<String> param, int index) {
		toIntParam(param, index, 0)
	}
	
	def static toIntParam(List<String> param, int index, int defaultValue) {
		if(index >= param.size)
			return defaultValue
		else
			return Integer.parseInt(param.get(index))
	}
		
	def static checkMin(int x, int minValue) {
		if(x < minValue)
			throw new Exception('''Value check failed because min=«minValue» but we got «x»''')
		return x
	}
	
	def static checkMax(int x, int maxValue) {
		if(x > maxValue)
			throw new Exception('''Value check failed because max=«maxValue» but we got «x»''')
		return x
	}

	def static toStringParam(List<String> param, int index) {
		toStringParam(param, index, "")
	}
	
	def static toStringParam(List<String> param, int index, String defaultValue) {
		if(index >= param.size)
			return defaultValue
		else
			return param.get(index)
	}
	
	def static pathFromWordToRoot(SpecWord w) {
		var current = w
		val result = new ArrayList<SpecWord>
		do {
			result += current
			current = current.semanticParent
		} while (current != null)
		return result as Iterable<SpecWord>
	}
	
}