package reprotool.dmodel.ctxgen

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcoreFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import spec.SpecFactory
import spec.SpecSentence

@Data
class RelationContext {
	SpecSentence sentence
	EClass srcEClass
	EClass destEClass
}

class ClassRelationsFromSentenceCtxGenTest {
	
	val factory = SpecFactory.eINSTANCE
	val ecorefact = EcoreFactory.eINSTANCE
	val dummySpecModel = factory.createSpecification


	@Before def void init() {
		
		val eclassA = ecorefact.createEClass => [name = "A"]
		val eclassB = ecorefact.createEClass => [name = "B"]
		val eclassC = ecorefact.createEClass => [name = "C"]
		
		dummySpecModel => [
			documents += factory.createSpecDocument => [
				sentences += factory.createSpecSentence => [
					entityLinks += factory.createEntityLink => [linkedEntity = eclassA]
					entityLinks += factory.createEntityLink => [linkedEntity = eclassB]
					entityLinks += factory.createEntityLink => [linkedEntity = eclassC]
				]
				sentences += factory.createSpecSentence => [
					entityLinks += factory.createEntityLink => [linkedEntity = eclassB]
					entityLinks += factory.createEntityLink => [linkedEntity = eclassA]
				]
				sentences += factory.createSpecSentence => [
					entityLinks += factory.createEntityLink => [linkedEntity = eclassC]
				]
			]
		]
	}
	
	@Test def void testIterator() {
		val ctxgen = new ClassRelationsFromSentenceCtxGen(dummySpecModel)
		// println(ctxgen.map[srcEClass.name + destEClass.name])
		
		Assert.assertArrayEquals(
			"Should correctly generate all pairs",
			'''
				AA AB AC
				BA BB BC
				CA CB CC
				
				BB BA
				AB AA
				
				CC
			'''.toString.split("\\s+"),
			ctxgen.map[srcEClass.name + destEClass.name]
		)
	}
}