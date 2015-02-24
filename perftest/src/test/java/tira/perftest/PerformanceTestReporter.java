package tira.perftest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * A utility for timing tests and writing a report file.
 */
public class PerformanceTestReporter {
	
	static final String OUTPUT_FILE_PROPERTY = "performanceTest.outputFile";
	private final Writer resultWriter; 
	
	public PerformanceTestReporter() {
		this(determineWriter());
	}
	
	static Writer determineWriter() {
		Writer outputWriter = null;
		String fileProperty = System.getProperty(OUTPUT_FILE_PROPERTY);
		if (fileProperty == null) {
			outputWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		} else {
			try {
				outputWriter = new BufferedWriter(new FileWriter(fileProperty));
			} catch (IOException ioe) {
				throw new IllegalArgumentException("Could not open result writer output file", ioe);
			}
		}
		return outputWriter;
	}
	
	public PerformanceTestReporter(Writer resultWriter) {
		this.resultWriter = resultWriter;
	}
	
	public void executeTestMethod(TestMethodCallback callback) {
		writeToResults(System.lineSeparator() + "Running test: " + callback.name());
		writeToResults(" -- " + callback.describe());
		long startTime = System.currentTimeMillis();
		callback.invoke();
		long duration = System.currentTimeMillis() - startTime;
		writeToResults(" -- Finished in " + duration + " milliseconds.");
		try {
			resultWriter.flush();
		} catch (IOException ioe) {
			throw new IllegalStateException("Failed to flush result writer", ioe);
		}
	}
	
	void writeToResults(String text) {
		try {
			resultWriter.write(text + System.lineSeparator());
		} catch (IOException ioe) {
			throw new IllegalStateException("Unable to write to result writer", ioe);
		}
	}
	
	public void done() {
		try {
			resultWriter.close();
		} catch (IOException ioe) {
			System.err.println("WARNING: failed to close result writer cleanly.");
			ioe.printStackTrace(System.err);
		}
	}

	/**
	 * Interface for tests to implement for performance test reporting.
	 */
	public interface TestMethodCallback {
		
		/***
		 * Gets the name of the test.
		 * 
		 * @return	name of the test.
		 */
		public String name();
		
		/**
		 * Gets a description of what the test does.
		 * 
		 * @return	description of the test.
		 */
		public String describe();
		
		/**
		 * Invokes the actual test code.
		 */
		public void invoke();
	}
}
