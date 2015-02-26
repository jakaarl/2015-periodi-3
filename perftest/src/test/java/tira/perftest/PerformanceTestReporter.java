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
	
	public static final int DEFAULT_TEST_ITERATIONS = 10;
	public static final int MAX_TEST_ITERATIONS = 1000;
	static final String OUTPUT_FILE_PROPERTY = "performanceTest.outputFile";
	static final String TEST_ITERATIONS_PROPERTY = "performanceTest.iterations";
	
	private final Writer resultWriter;
	private final int iterations;
	
	/**
	 * Creates a test reporter with default writer and iterations. Writer or output file and number iterations can be
	 * set by using alternative constructors, or by system properties:
	 * <ul>
	 *   <li>{@value #OUTPUT_FILE_PROPERTY} - path to output file</li>
	 *   <li>{@value #TEST_ITERATIONS_PROPERTY - iteration count</li>
	 * </ul>
	 * The defaults are: writing to <code>System.out</code>, {@value #DEFAULT_TEST_ITERATIONS} iterations.
	 * 
	 *  @see	#PerformanceTestReporter(Writer)
	 *  @see	#PerformanceTestReporter(Writer, int)
	 */
	public PerformanceTestReporter() {
		this(determineWriter(), determineIterations());
	}
	
	/**
	 * Creates a test reporter with given writer. Iteration count is the default, unless system
	 * property {@value #TEST_ITERATIONS_PROPERTY} is set.
	 * 
	 * @param resultWriter	output writer.
	 * 
	 * @see	#PerformanceTestReporter()
	 */
	public PerformanceTestReporter(Writer resultWriter) {
		this(resultWriter, determineIterations());
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
	
	static int determineIterations() {
		String iterationProperty = System.getProperty(
				TEST_ITERATIONS_PROPERTY, String.valueOf(DEFAULT_TEST_ITERATIONS));
		try {
			return Integer.parseInt(iterationProperty);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Invalid iteration count property: " + iterationProperty, nfe);
		}
	}
	
	/**
	 * Creates a test reporter with given writer and iteration count.
	 * 
	 * @param resultWriter
	 * @param iterations
	 */
	public PerformanceTestReporter(Writer resultWriter, int iterations) {
		if (iterations < 1 || iterations > MAX_TEST_ITERATIONS) {
			throw new IllegalArgumentException("Invalid test iteration count: " + iterations);
		}
		this.resultWriter = resultWriter;
		this.iterations = iterations;
	}
	
	/**
	 * Executes test method using given callback.
	 * 
	 * @param callback	test method callback.
	 */
	public void executeTestMethod(TestMethodCallback callback) {
		writeToResults(System.lineSeparator() + "Running test: " + callback.name());
		writeToResults(" -- " + callback.describe());
		long totalDuration = 0;
		for (int i = 0; i < iterations; i++) {
			long startTime = System.currentTimeMillis();
			callback.invoke();
			long duration = System.currentTimeMillis() - startTime;
			totalDuration += duration;
		}
		long averageDuration = Math.round((double) totalDuration / iterations);
		writeToResults(" -- Test ran on average in " + averageDuration + " milliseconds.");
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
	
	/**
	 * Ends test reporting. While failing to invoke this method is by no means a catastrophe,
	 * invoking it is highly recommended in order to avoid leaving a dangling, open writer.
	 */
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
		 * Invokes the actual test code. Iterated several times, as determined by {@link PerformanceTestReporter}.
		 */
		public void invoke();
	}
}
