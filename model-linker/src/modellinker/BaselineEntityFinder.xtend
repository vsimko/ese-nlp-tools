package modellinker

import com.google.common.base.Preconditions
import org.eclipse.emf.ecore.util.EcoreUtil
import spec.WordDependency
import spec.SpecPackage
import spec.impl.SpecPackageImpl
import spec.SpecFactory
import org.eclipse.emf.ecore.impl.EClassImpl
import java.util.Collections
import org.eclipse.emf.ecore.EObject
import java.util.List
import spec.SpecWord
import spec.SpecSentence
import java.util.Arrays

class BaselineEntityFinder {
	
	def static void main(String[] args) {
		Preconditions.checkArgument(args.length == 1)
		
		val pkg = SpecPackage::eINSTANCE
		val model = Utils.loadModel(args.get(0))
		
		val list = getDependencyEntities(model).toSet.toList.sort
//		println(Arrays.toString(list))
		list.forEach[println(it)]
	}
	
	private static def List<String> getDependencyEntities(EObject root) {
		val contents = EcoreUtil.getAllContents(root, true)
		val wordDeps = contents.filter(typeof(WordDependency))
		
		val nounTypedDeps = #[
			"subj", "nsubj", "nsubjpass", "csubj", "csubjpass",
			"obj", "dobj", "iobj", "pobj"
		]
		
		// get only interesting words
		val nouns = wordDeps.filter[
			nounTypedDeps.contains(it.label) 
			&& it.linkDep.posTag == "NN"
		].map[it.linkDep]
		
//		wordDeps.filter[
//			nounTypedDeps.contains(it.label) 
//			//&& it.linkDep.posTag == "NN"
//		].forEach[println('''«label» «it.linkDep.original» «it.linkGov.original»''')]
		
		// join noun + nns
		nouns.map[noun |
			val list = <String>newArrayList()
			val nnsLemmas = getNns(noun).map[it.linkDep.lemma]
			list += nnsLemmas
			list += noun.lemma
			concatCamel(list)
		].toList
	}
	
	private static def getNns(SpecWord word) {
		(word.eContainer as SpecSentence).typedDependencies.filter[
			it.label == "nn" && it.linkGov == word
		]
	}
	
	private static def concatCamel(Iterable<String> iterable) {
		iterable.fold("", [acc, s | acc + s.toFirstUpper])
	}
}