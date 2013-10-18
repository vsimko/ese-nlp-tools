package spec.impl

import org.eclipse.emf.common.util.BasicEList

class SpecWordImplCustom extends SpecWordImpl {
	
	override getSemanticParentRelation() {
		getInvLinkDep.findFirst[ ! label.startsWith("prep_") ]
	}
	
	override getSemanticParent() {
		semanticParentRelation?.linkGov
	}
	
	override getSemanticChildren() {
		if(invLinkGov == null)
			return new BasicEList
			
		new BasicEList(invLinkGov.map[linkDep])
	}
		
	override isRepresentativeCoref() {
		! corefMentions.empty
	}
	
	override getCorefRepOrSelf() {
		if( corefRepMention == null ) this else corefRepMention
	}
	
	override toString() '''«original»/«posTag»'''
	
}