package spec.impl

import spec.impl.SpecSentenceImpl

class SpecSentenceImplCustom extends SpecSentenceImpl {
	
	override toString() '''
		SENTENCE $«hashCode» : «words?.map[original].join(" ")»
			WORDS[«words.size»]: «words»
			COREFERENCES:
				«FOR coref : words?.map[it -> corefMentions].filter[value.size > 1]»
				«coref»
				«ENDFOR»
			«IF entityLinks != null»
			LINKS[«entityLinks.size»]:
				«FOR elink : entityLinks»
				LINK:«elink.entLabel» -- «elink.linkedWords»
				«ENDFOR»
			«ENDIF»
			«IF typedDependencies != null»
			SEMANTIC GRAPH:
				«FOR dep : typedDependencies»
				«dep.label» («dep.linkGov», «dep.linkDep») «dep»
				«ENDFOR»
			«ENDIF»
	'''
}