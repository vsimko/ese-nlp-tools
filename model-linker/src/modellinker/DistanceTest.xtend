package modellinker

import net.ricecode.similarity.JaroWinklerStrategy
import net.ricecode.similarity.StringSimilarityServiceImpl
import net.ricecode.similarity.DiceCoefficientStrategy
import net.ricecode.similarity.LevenshteinDistanceStrategy
import net.ricecode.similarity.JaroStrategy

class DistanceTest {
	def static void main(String[] args) {
		val strategies = #[ 
			new LevenshteinDistanceStrategy, 
			new JaroStrategy, 
			new DiceCoefficientStrategy, 
			new JaroWinklerStrategy
		]
		
		val words = #[
//			"Scanner" -> "ScannerController",
//			"Scanner" -> "ScannerControllerEventHandlerIf",
//			"Scanner" -> "ScannerControllerEventHandlerImpl",
//			"ProductAvailable" -> "Product",
//			"ProductAvailable" -> "ProductNotAvailableException",
//			"ProductAvailable" -> "ProductTO"
			"Bank" -> "SimBank",
			"Bank" -> "SiBank",
			"knaB" -> "knaBmiS",
			"SimulatedBank" -> "Bank",
			"knaBdetalumiS" -> "knaB"
		]
		
		for (strategy : strategies) {
			//val service = new StringSimilarityServiceImpl(strategy)
			println(strategy.toString)
			for (pair : words) {
				println('''«pair.key» «pair.value»: «strategy.score(pair.key, pair.value)»''')
			}
			println
		}
		
		
		println()
	}
}