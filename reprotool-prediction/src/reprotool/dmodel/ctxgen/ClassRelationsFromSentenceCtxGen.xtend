package reprotool.dmodel.ctxgen

import org.eclipse.emf.ecore.EClass
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
