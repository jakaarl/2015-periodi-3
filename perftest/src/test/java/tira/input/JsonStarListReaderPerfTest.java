package tira.input;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tira.perftest.PerformanceTestReporter;

public class JsonStarListReaderPerfTest {
	
	private static final String NEAR_SPACE_JSON_FILE = "near-space.json";
	private static final String RANDOM_100_JSON_FILE = "random-100.json";
	private static final String RANDOM_1000_JSON_FILE = "random-1000.json";
	private static final int MAX_CONNECTION_DISTANCE = 750;
	private static PerformanceTestReporter reporter;
	private final JsonStarListReader reader = new JsonStarListReader();
	
	@BeforeClass
	public static void initializeReporter() throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(
				Paths.get(System.getProperty("user.dir"), JsonStarListReaderPerfTest.class.getSimpleName()),
				Charset.defaultCharset());
		reporter = new PerformanceTestReporter(writer);
	}
	
	@Test
	public void testWithSmallStarMap() {
		final String jsonPath = this.getClass().getClassLoader().getResource(NEAR_SPACE_JSON_FILE).getFile();
		reporter.executeTestMethod(createCallback(
				"testWithSmallStarMap",
				"Test creating star map with a small sample of Solar neighborhood.",
				jsonPath));
	}
	
	@Test
	public void testWithLargeStarMap() {
		final String jsonPath = this.getClass().getClassLoader().getResource(RANDOM_100_JSON_FILE).getFile();
		reporter.executeTestMethod(createCallback(
				"testWithLargeStarMap",
				"Test creating star map from a list of 100 random stars.",
				jsonPath));
	}
	
	@Test
	public void testWithEnormousStarMap() {
		final String jsonPath = this.getClass().getClassLoader().getResource(RANDOM_1000_JSON_FILE).getFile();
		reporter.executeTestMethod(createCallback(
				"testWithEnormousStarMap",
				"Test creating star map from a list of 1000 random stars.",
				jsonPath));
	}
	
	private PerformanceTestReporter.TestMethodCallback createCallback(final String name, final String description, final String jsonPath) {
		return new PerformanceTestReporter.TestMethodCallback() {
			@Override
			public String name() {
				return name;
			}
			
			@Override
			public void invoke() {
				reader.buildStarMap(jsonPath, MAX_CONNECTION_DISTANCE);
			}
			
			@Override
			public String describe() {
				return description;
			}
		};
	}
	
	@AfterClass
	public static void closeReporter() throws IOException {
		reporter.done();
	}

}
