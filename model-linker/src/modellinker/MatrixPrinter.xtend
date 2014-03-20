package modellinker

import com.google.common.base.Charsets
import com.google.common.base.Strings
import com.google.common.io.Files
import java.io.File
import java.util.List
import java.util.TreeMap
import net.ricecode.similarity.JaroWinklerStrategy
import net.ricecode.similarity.StringSimilarityServiceImpl

class MatrixPrinter {
	val static ENDL = "\r?\n"
	
	def static void main(String[] args) {
		
		val predicted = loadWords("data-atm/predicted.txt");
		val actual = loadWords("data-atm/actual.txt");
		
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
	
	private def static loadWords(String fileName) {
		Files::toString(new File(fileName), Charsets::UTF_8).split(ENDL)
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
		
		for (predNorm : predicted) {
			val pred = predNorm.toLowerCase
			val list = <Pair<String, Double>>newArrayList
			map.put(predNorm, list)
			
			for (actNorm : actual) {
				val act = actNorm.toLowerCase

//				val actRev = new StringBuilder(act).reverse.toString
//				val predRev = new StringBuilder(pred).reverse.toString
				
				var score = service.score(act, pred)
//				val scoreRev = service.score(actRev, predRev)

				if (act.endsWith(pred)) {
					val suffLen = Strings.commonSuffix(act, pred).length
					val num = suffLen / pred.length
					score = score + 1.0 * num * (1.0 - score)   
				}
				
//				list.add(new Pair(actNorm, 0.5 * (score + scoreRev)))
				list.add(new Pair(actNorm, score))
			}
		}
		
		map
	}
	
}