package reprotool.dmodel.api.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Reads samples from file and populates them as {@link Iterable}<{@link FeatureEvent}>.
 * @author Viliam Simko
 */
public class FileBasedSamples implements Iterable<FeatureEvent> {

	final private File file;
	final private String outcomeFeatureName;

	/**
	 * @param file
	 * @param outcomeFeatureName
	 */
	public FileBasedSamples(final File file, final String outcomeFeatureName) {
		this.file = file;
		this.outcomeFeatureName = outcomeFeatureName;
	}
	
	public FileBasedSamples(final String fileName, final String outcomeFeatureName) {
		this.file = new File(fileName);
		this.outcomeFeatureName = outcomeFeatureName;
	}
	
	
	@Override
	public Iterator<FeatureEvent> iterator() {
		return new Iterator<FeatureEvent>() {

			private BufferedReader reader = null;
			{
				try {
					reader = new BufferedReader(new FileReader(file));
					getCurrentAndAdvance();
				} catch(FileNotFoundException e) {}
			}

			private String bufferedLine;

			private String getCurrentAndAdvance() {
				final String previousLine = bufferedLine;
				bufferedLine = null;
				try {
					bufferedLine = reader.readLine();
				} catch (IOException e) {}
				return previousLine;
			}

			@Override
			protected void finalize() throws Throwable {
				reader.close();
				super.finalize();
			}

			@Override
			public boolean hasNext() {
				if(reader == null) return false;
				
				if(bufferedLine == null) {
					try {
						reader.close();
					} catch (IOException e) {}
					return false;
				}
				return true;
			}

			@Override
			public FeatureEvent next() {
				final String[] listOfFeatures = getCurrentAndAdvance().split("\\s+");
				return FeatureEvent.createFrom(listOfFeatures, outcomeFeatureName, null);
			}

			@Override
			public void remove() {
				new UnsupportedOperationException();
			}
		};
	}

}
