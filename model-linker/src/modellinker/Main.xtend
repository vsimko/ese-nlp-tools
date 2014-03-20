package modellinker

import com.google.common.base.Charsets
import com.google.common.collect.Sets
import com.google.common.io.Files
import java.io.File
import java.util.List
import java.util.TreeMap
import net.ricecode.similarity.JaroWinklerStrategy
import net.ricecode.similarity.StringSimilarityServiceImpl
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration
import org.eclipse.gmt.modisco.java.ClassDeclaration
import org.eclipse.gmt.modisco.java.InterfaceDeclaration
import org.eclipse.gmt.modisco.java.Model
import org.eclipse.gmt.modisco.java.Package
import org.eclipse.gmt.modisco.java.emf.JavaPackage
import org.eclipse.xtext.xbase.lib.Pair

class Main {
	val static ENDL = "\r?\n"
	val static THRESHOLD = 0.84
	
	def static void main(String[] args) {
		
		val predicted = loadWords("data/predicted.txt");
		val actual = loadWords("data/actual.txt");
		val gold = loadGoldSet("data/goldset.txt")

		// Iterate trough MoDisco classes and interfaces and compute
		// word similarity with predicted domain entities.
		//
		// Each domain entity can probably have multiple classes/interfaces
		// associated but one class/interface cannot be associated to more than
		// one domain entity.
		//
		// 1) take each class/interface and try to added to domain model that has highest 
		//    similarity score
		//
		// 2) sort values by score and cut off at selected limit
		//
		// 3) compare with gold set and print precision/recall values
		
//		val map = scoreAndMatch(predicted, actual)
//		 
//		filter(map, THRESHOLD)
		
//		println('''actual («map.keySet.size» predicted, «map.values.flatten.size» implementation classes''')
//		for (entry : map.entrySet) {
//			entry.value.sortInplaceBy[-it.value]
//			println('''«entry.key» «entry.value»''')
//		}
//
//		println
//		println('''gold («gold.keySet.size» predicted, «gold.values.flatten.size» implementation classes''')
//		for (entry : gold.entrySet) {
//			println('''«entry.key» «entry.value»''')
//		}
		
//		evaluate(map, gold)
		
		val matrix = similarityMatrix(predicted, actual)
		printMatrix(matrix)
	}
	
	def static printMatrix(TreeMap<String, List<Pair<String, Double>>> map) {
		
		// header
		println('''X,«map.firstEntry.value.map[key].join(",")»''')
		
		// values
		for (row : map.entrySet) {
			println('''«row.key»,«row.value.map[value].join(",")»''')
		}
	}
	
	def static evaluate(TreeMap<String, List<Pair<String, Double>>> actual, TreeMap<String, List<String>> gold) {
		val actualPairs = <Pair<String, String>>newHashSet
		val goldPairs = <Pair<String, String>>newHashSet
		
		for (entry : actual.entrySet) {
			entry.value.forEach[actualPairs.add(new Pair(entry.key, it.key))]
		}
		
		for (entry : gold.entrySet) {
			entry.value.forEach[goldPairs.add(new Pair(entry.key, it))]
		}
		
		// true-positive 	- # pairs in both sets (intersection)
		val truePos = Sets::intersection(actualPairs, goldPairs).size
		
		// false-positive 	- # pairs in actual not in gold
		val falsePos = Sets::difference(actualPairs, goldPairs).size
		
		// false-negative	- # pairs in gold not in actual (missed)
		val falseNeg = Sets::difference(goldPairs, actualPairs).size
		
		val goldPredicted = gold.keySet
		val actualPredicted = actual.keySet
		
		
		val precision = precision(truePos, falsePos)
		val recall = recall(truePos, falseNeg)
		val f1 = f1(precision, recall)
		
		println
		println('''threshold: «THRESHOLD»''')
		println('''found (predicted classes): «Sets::intersection(goldPredicted, actualPredicted).size»/«goldPredicted.size» ''')
		println('''gold pairs: «goldPairs.size»''')
		println('''actual all pairs: «actualPairs.size»''')
		println('''actual correct pairs: «truePos»''')
		println('''precision (pairs):«precision»''')
		println('''recall (pairs):   «recall»''')		
		println('''f1 (pairs):       «f1»''')
	}
	
	def static double precision(double truePositive, double falsePositive) {
		truePositive / (truePositive + falsePositive)
	}
	
	def static double recall(double truePositive, double falseNegative) {
		truePositive / (truePositive + falseNegative)
	}
	
	def static double f1(double precision, double recall) {
		2 * precision * recall / (precision + recall)
	}
	
	private def static loadWords(String fileName) {
		Files::toString(new File(fileName), Charsets::UTF_8).split(ENDL)
	}
	
	private def static TreeMap<String, List<String>> loadGoldSet(String fileName) {
		val lines = loadWords(fileName)
		val map = new TreeMap<String, List<String>>
		
		for (line : lines) {
			val words = line.split("\\W+")
			val key = words.head
			val value = words.tail.sort
			
			map.put(key, value)
		}
		
		map
	}
	
	private def static filter(TreeMap<String, List<Pair<String, Double>>> map, double threshold) {
		// remove values from map with similarity smaller than threshold
		map.entrySet.forEach[entry | 
			
			val toRemove = <Pair<String, Double>>newArrayList
			entry.value.forEach[
				if (it.value < threshold)
					toRemove += it
			]
			
			entry.value.removeAll(toRemove)
		]
		
		// remove entries with no values
		val emptyKeys = <String>newHashSet
		map.entrySet.forEach[
			if (it.value.empty) {
				emptyKeys += it.key
			}
		]
		map.keySet.removeAll(emptyKeys)
	}
	
	private def static TreeMap<String, List<Pair<String, Double>>> scoreAndMatch(String[] predicted, 
		String[] actual
	) {
//		val strategy = new LevenshteinDistanceStrategy
//		val strategy = new JaroStrategy
		val strategy = new JaroWinklerStrategy
//		val strategy = new DiceCoefficientStrategy
		
		val service = new StringSimilarityServiceImpl(strategy)
		
		val map = new TreeMap<String, List<Pair<String, Double>>>
		
		for (pred : predicted) {
			map.put(pred, newArrayList)
		}
		
		for (act : actual) {
			var max = -1.0
			var maxEntity = ""
			
			for (pred : predicted) {
				val score = service.score(act, pred)
				if (score >= max) {
					max = score
					maxEntity = pred
				}
			}
			
			val list = map.get(maxEntity)
			list.add(new Pair(act, max))
		}
		
		map
	}
	
	private def static TreeMap<String, List<Pair<String, Double>>> similarityMatrix(String[] predicted, 
		String[] actual
	) {
//		val strategy = new LevenshteinDistanceStrategy
//		val strategy = new JaroStrategy
		val strategy = new JaroWinklerStrategy
//		val strategy = new DiceCoefficientStrategy
		
		val service = new StringSimilarityServiceImpl(strategy)
		
		val map = new TreeMap<String, List<Pair<String, Double>>>
		
		for (pred : predicted) {
			val list = <Pair<String, Double>>newArrayList
			map.put(pred, list)
			
			for (act : actual) {
				val score = service.score(act, pred)
				list.add(new Pair(act, score))
			}
		}
		
		map
	}
	
	private def static Model loadModel(String fileName) {
		// init model
		val instance = JavaPackage::eINSTANCE
		
		val reg = Resource.Factory.Registry::INSTANCE
		val map = reg.extensionToFactoryMap
		map.put("*", new XMIResourceFactoryImpl)
		
		val resourceSet = new ResourceSetImpl
		val resource = resourceSet.getResource(URI::createURI(fileName), true)
		
		val Model model = resource.contents.head as Model
		return model
	}
	
	private def static Package getInitPackage(Model model, String[] path) {
		var packagesToSearch = model.ownedElements
		var Package lastPackage = null
		
		for (packageName : path) {
			lastPackage = packagesToSearch.findFirst[it.name == packageName]
			if (lastPackage == null) {
				return null
			}
			packagesToSearch = lastPackage.ownedPackages
		}
		
		lastPackage
	}
	
	private static def List<AbstractTypeDeclaration> collectTypeDeclarations(Package pkg) {
		val retVal = newArrayList
		
		pkg.ownedElements.forEach[
			if (it instanceof ClassDeclaration || it instanceof InterfaceDeclaration)
				retVal += it		
		]
		
		pkg.ownedPackages.forEach[
			retVal += collectTypeDeclarations(it) 
		]
		
		retVal
	}
}