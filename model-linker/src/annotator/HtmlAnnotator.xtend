package annotator

import com.google.common.base.Preconditions

class HtmlAnnotator {
	
	def static void main(String[] args) {
		Preconditions::checkArgument(args.size == 2, "usage: program file-with-entities.txt file-to-annotate.html")
		
		
	}
	
}