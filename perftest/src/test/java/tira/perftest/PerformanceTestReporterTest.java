package tira.perftest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.Test;

public class PerformanceTestReporterTest {
	
	@Test
	public void determineWriterShouldReturnFileWriter() throws IOException {
		String testOutput = "Hi mom!";
		Path tempFile = Files.createTempFile("perf", "temp");
		String initialProperty = System.getProperty(PerformanceTestReporter.OUTPUT_FILE_PROPERTY);
		System.setProperty(PerformanceTestReporter.OUTPUT_FILE_PROPERTY, tempFile.toString());
		Writer writer = PerformanceTestReporter.determineWriter();
		if (initialProperty == null) {
			System.clearProperty(PerformanceTestReporter.OUTPUT_FILE_PROPERTY);
		} else {
			System.setProperty(PerformanceTestReporter.OUTPUT_FILE_PROPERTY, initialProperty);
		}
		
		writer.write(testOutput);
		writer.flush();
		List<String> lines = Files.readAllLines(tempFile, Charset.defaultCharset());
		assertEquals(1, lines.size());
		assertEquals(testOutput, lines.get(0));
		tempFile.toFile().deleteOnExit();
	}
	
	@Test
	public void determineWriterShouldReturnSystemOutWriter() throws IOException {
		String testOutput = "I'm (system) outta here!";
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outStream));
		Writer writer = PerformanceTestReporter.determineWriter();
		writer.write(testOutput);
		writer.flush();
		String output = outStream.toString();
		assertEquals(testOutput, output);
		System.setOut(originalOut);
	}
	
	@Test
	public void determineIterationsShouldReturnDefault() {
		int iterations = PerformanceTestReporter.determineIterations();
		assertEquals(PerformanceTestReporter.DEFAULT_TEST_ITERATIONS, iterations);
	}
	
	@Test
	public void determineIterationsShouldReturnConfiguredCount() {
		int expected = 2;
		String initialProperty = System.getProperty(PerformanceTestReporter.TEST_ITERATIONS_PROPERTY);
		System.setProperty(PerformanceTestReporter.TEST_ITERATIONS_PROPERTY, String.valueOf(expected));
		int actual = PerformanceTestReporter.determineIterations();
		if (initialProperty == null) {
			System.clearProperty(PerformanceTestReporter.TEST_ITERATIONS_PROPERTY);
		} else {
			System.setProperty(PerformanceTestReporter.TEST_ITERATIONS_PROPERTY, initialProperty);
		}
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReportNameAndDescription() throws IOException {
		final String testName = "A test";
		final String testDescription = "tests performance test reporter";
		StringWriter writer = new StringWriter();
		PerformanceTestReporter reporter = new PerformanceTestReporter(writer, 1);
		reporter.executeTestMethod(new PerformanceTestReporter.TestMethodCallback() {
			@Override
			public String name() {
				return testName;
			}
			
			@Override
			public String describe() {
				return testDescription;
			}
			
			@Override
			public void invoke() {
				// no-op
			}
		});
		String output = writer.toString();
		assertTrue(output.contains(testName));
		assertTrue(output.contains(testDescription));
	}

}
