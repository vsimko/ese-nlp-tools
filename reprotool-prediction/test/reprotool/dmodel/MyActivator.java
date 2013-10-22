package reprotool.dmodel;

import java.net.URL;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class MyActivator implements BundleActivator {

	private BundleContext bundleContext;

	private void findResource(final String resourceName) {
		final URL resource = bundleContext.getBundle().getResource(resourceName);
		System.out.println(" - Resource: " + resourceName + " : " + resource);
	}

	@Override
	public void start(BundleContext context) throws Exception {
		this.bundleContext = context;
		System.out.println("starting my activator");
		System.out.println("Looking for resources:");

//		findResource("models/reprotool-maxent-ssplit.gz");
//		findResource("models/linktype.maxent.gz");
//		findResource("models/relcl.maxent.gz");
//		findResource("models/roleInLink.maxent.gz");
		
		findResource("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		findResource("edu/stanford/nlp/models/dcoref/gender.data.gz");
		findResource("edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz");
		findResource("edu/stanford/nlp/models/pos-tagger/english-bidirectional/english-bidirectional-distsim.tagger");
		findResource("edu/stanford/nlp/pipeline/StanfordCoreNLP.properties");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("stopping my activator");
	}

}
