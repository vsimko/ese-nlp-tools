package reprotool.dmodel.api;

public interface FeatureExtractor {
	
	String getExtractorName();
	String getFeatureName();
	String getDocumentation();

	String visit(Object obj);
	
	void setParams(String[] params);
}
