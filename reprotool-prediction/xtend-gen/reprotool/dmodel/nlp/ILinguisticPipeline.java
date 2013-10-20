package reprotool.dmodel.nlp;

import edu.stanford.nlp.pipeline.Annotation;
import spec.SpecDocument;

@SuppressWarnings("all")
public interface ILinguisticPipeline {
  /**
   * Performs analysis by reading the text from file
   */
  public abstract Annotation analyzeTextFromFile(final String fileName);
  
  /**
   * Convert analyzed document into a specification model
   */
  public abstract SpecDocument analyzedDocToSpecDoc(final Annotation document);
}
