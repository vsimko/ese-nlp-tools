package reprotool.dmodel.extensions

import java.util.List
import spec.SpecSentence
import spec.SpecWord
import java.util.ArrayList

class SpecWordExtensions {
	
	def static getPositionInSentence(SpecWord w) {
		return w.sentence.words.indexOf(w)
	}
	
	def static getWordAtPosition(SpecSentence s, int index) {
		if(index > -1 && index < s.words.size)
			s.words.get(index)
	}
	
	def static getRelativeWordInSentence(SpecWord word, int idxOffset) {
		val index = word.getPositionInSentence + idxOffset
		return word.sentence.getWordAtPosition(index)
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
		if(w == null) return null
		
		var current = w
		val result = new ArrayList<SpecWord>
		do {
			result += current
			current = current.semanticParent
		} while (current != null)
		return result as Iterable<SpecWord>
	}
	
	def static getNearestCommonSemanticParent(SpecWord w1, SpecWord w2) {
		val path1 = pathFromWordToRoot(w1)
		val path2 = pathFromWordToRoot(w2)

		if(path1 == null || path2 == null) return null

		val lookup = path1.toSet
		return path2.findFirst[lookup.contains(it)]
	}
	
	def static getNearestCommonSemanticParent(Iterable<SpecWord> wlist) {
		wlist.reduce[w1, w2 | getNearestCommonSemanticParent(w1,w2)]
	}
}