package reprotool.dmodel.ctxgen

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EcoreFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import spec.SpecFactory
import spec.SpecSentence
import spec.Specification

import static extension reprotool.dmodel.extensions.Combinations.*

class ClassRelationsFromSentenceCtxGen implements IContextGenerator<RelationContext> {

	private val Specification specModel
	new(Specification specModel) {
		this.specModel = specModel
	}

	override iterator() {
		return specModel.documents.map[
			sentences.map[ sentence |
				sentence.entityLinks.map[linkedEntity].filter(EClass).generateVariationsPairs.map[
					val eclassA = key as EClass
					val eclassB = value as EClass
					new RelationContext(sentence, eclassA, eclassB)
				]
			].flatten
		].flatten.iterator
	}
}

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