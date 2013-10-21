package reprotool.dmodel.api.samples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import au.com.bytecode.opencsv.CSVReader;

public class CSVFileBasedSamples implements Iterable<FeatureEvent> {

	final private File file;
	final private String outcomeFeatureName;

	/**
	 * @param file
	 * @param outcomeFeatureName
	 */
	public CSVFileBasedSamples(final File file, final String outcomeFeatureName) {
		this.file = file;
		this.outcomeFeatureName = outcomeFeatureName;
	}
	
	public CSVFileBasedSamples(final String fileName, final String outcomeFeatureName) {
		this.file = new File(fileName);
		this.outcomeFeatureName = outcomeFeatureName;
	}
	
	
	
	@Override
	public Iterator<FeatureEvent> iterator() {
		return new Iterator<FeatureEvent>() {

			private CSVReader reader = null;
			{
				try {
					reader = new CSVReader(new FileReader(file));
					getCurrentAndAdvance();
				} catch(FileNotFoundException e) {}
			}
			
			// first line is the table header
			final private String[] columnNames = getCurrentAndAdvance();

			private String[] bufferedLine;

			private String[] getCurrentAndAdvance() {
				final String[] previousLine = bufferedLine;
				bufferedLine = null;
				try {
					bufferedLine = reader.readNext();
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
			public FeatureEvent next(){
				final String[] listOfFeatures = new String[columnNames.length];
				final String[] csvLine = getCurrentAndAdvance();

				// sanity check - lines with wrong number of columns are skipped
				if(listOfFeatures.length != csvLine.length) {
					return null; // Fond a line in CSV file which has a different number of columns than the header
				}
				
				for(int i=0; i<listOfFeatures.length; i++) {
					listOfFeatures[i] = columnNames[i] + "=" + csvLine[i];
				}
				
				return FeatureEvent.createFrom(listOfFeatures, outcomeFeatureName, null);
			}

			@Override
			public void remove() {
				new UnsupportedOperationException();
			}
		};
	}

}
