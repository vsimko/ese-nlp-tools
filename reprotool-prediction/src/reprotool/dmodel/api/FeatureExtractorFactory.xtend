package reprotool.dmodel.api

import java.util.HashMap
import java.util.Map
import org.apache.log4j.Logger
import org.junit.Assert
import org.junit.Test

class FeatureExtractorFactory {
	private val logger = Logger.getLogger(FeatureExtractorFactory)
	
	public val static INSTANCE = new FeatureExtractorFactory => [
		logger.info("feature extractor factory initialized")
		loadFromPackage("reprotool.dmodel.extract.words")
		loadFromPackage("reprotool.dmodel.extract.rel")
		loadFromPackage("reprotool.dmodel.extract.mwent")
	]
	
	// Private constructor is used because this class is a singleton
	private new() {}
	
	private val classLoader = getClass.classLoader
	
	private val Map<String, Class<? extends FeatureExtractor>> mapExtractorNameToClass = newHashMap
	
	def getLoadedExtractors() {
		mapExtractorNameToClass.keySet
	}
	
	def loadFromPackage(String packageName) {
		for(className : packageName.toClassNames) {
			loadFromClass(packageName + "." + className)
		}
	}
	
	def loadFromClass(String canonicalName) {
		classLoader.loadClass(canonicalName).loadFromClass
	}

	def loadFromClass(Class<?> clazz) {
		val annot = clazz.getAnnotation(Feature)
		if(annot != null ) {
			val extractorName = annot.value
			val fexclazz = clazz.asSubclass(FeatureExtractor)
			
			// sanity check
			if(mapExtractorNameToClass.containsKey(extractorName)) {
				val storedClazz = mapExtractorNameToClass.get(extractorName)
				logger.warn('''Feature extractor "«extractorName»" («storedClazz») already registered in the factory''')
				
				if(storedClazz != fexclazz)
					throw new Exception('''The feature extractor "«extractorName»" is already registered with a different class («storedClazz.simpleName» != «fexclazz.simpleName»).''' )
			}
			
			mapExtractorNameToClass.put(extractorName, fexclazz)
		}
	}
	
	def Iterable<String> toClassNames(String packageName) {

// disabled because when using OSGi, the URL was a bundleresource:// which is not compoatible with File
//		val packagePath = packageName.replace(".", "/")
//		val filenameFilter = new PatternFilenameFilter("[A-Za-z0-9]+\\.class")
//		
//		val resources = classLoader.getResources(packagePath).list
//		if(resources.empty)
//			throw new FileNotFoundException(packageName)
//			
//		return resources.map[ pkgUrl |
//			val connection = pkgUrl.openConnection
//			switch connection {
//				JarURLConnection : connection.jarFile.entries.list.map[name]
//				default : new File(pkgUrl.toURI).list(filenameFilter).toList
//			}
//		]
//		.flatten
//		.map[replaceFirst("\\.class$", "")] // class names from the package
		
		val pkgmap = new HashMap<String, String>
		pkgmap.put("reprotool.dmodel.extract.words",
		'''
			SemParentPosTag
			SemParentRelation
			WordHasCapitalLetter
			WordHasDigit
			WordLemma
			WordLinkedToEClass
			WordLinkType
			WordMinLength
			WordPosTag
			WordPosTagTest
			WordPrefix
			WordSuffix
		'''.toString)
		pkgmap.put("reprotool.dmodel.extract.rel",
		'''
			ExistsContainmentBetweenClasses
			ExistsRelationBetweenClasses
			RelationContextDebug
			RelClassDepOnSentenceRoot
			RelIntersectionVerbFeature
			RelSentenceRootInPassive
			RelSentenceSemRootLemma
			TypeOfRelationBetweenClasses
		'''.toString)
		pkgmap.put("reprotool.dmodel.extract.mwent",
		'''
			RoleInLink
		'''.toString)

		return pkgmap.get(packageName).split("\n").map[trim]
	}
	
	def getFeatureExtractor(String featureName) {
		
		if(featureName.matches("[=\\s]"))
			throw new Exception("Feature names cannot contain white spaces or '=' character.")
		
		// constructing a new extractor
		val chunks = featureName.split(":")
		val extractorName = chunks.head
		val params = chunks.tail

		val clazz = mapExtractorNameToClass.get(extractorName)

		if(clazz == null)
			throw new Exception('''Could not find the feature extractor "«featureName»" on class path. Maybe you forgot to annotate it with @Feature("«extractorName»").''')
			
		val instance = clazz.newInstance
		instance.setParams(params)
		return instance
	}
	
	def getFeatureExtractors(Iterable<String> featureNames) {
		featureNames.map[featureExtractor]
	}
}

class FeatureExtractorFactoryTest {

	extension FeatureExtractorFactory = FeatureExtractorFactory.INSTANCE
	
	@Test
	def void testLoadingExtractorFromPackage() {
	
		getClass.canonicalName.replaceFirst("\\.[^.]+$", "").loadFromPackage
		Assert.assertTrue("Contains the mock extractor", loadedExtractors.contains("mock"))
		
		val f_mock = "mock:1".featureExtractor
		Assert.assertEquals("mock:1", f_mock.featureName)
		Assert.assertEquals("mock", f_mock.extractorName)
	}
}

@Feature("mock")
class MockFeatureExtractor extends AbstractFeatureExtractor {}