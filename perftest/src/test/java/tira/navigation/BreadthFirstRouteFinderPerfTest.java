package tira.navigation;

import static tira.input.Constants.MAX_CONNECTION_DISTANCE;
import static tira.input.Constants.NEAR_SPACE_JSON_FILE;
import static tira.input.Constants.RANDOM_10000_JSON_FILE;
import static tira.input.Constants.RANDOM_1000_JSON_FILE;
import static tira.input.Constants.RANDOM_100_JSON_FILE;
import static tira.perftest.ReportWriterFactory.reportWriterFor;

import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tira.collections.List;
import tira.input.JsonStarListReader;
import tira.perftest.PerformanceTestReporter;

public class BreadthFirstRouteFinderPerfTest {
	
	private static PerformanceTestReporter reporter;
	private final JsonStarListReader reader = new JsonStarListReader();
	private final RouteFinder routeFinder = new BreadthFirstRouteFinder();
	
	@BeforeClass
	public static void initializeReporter() throws IOException {
		Writer writer = reportWriterFor(BreadthFirstRouteFinderPerfTest.class);
		reporter = new PerformanceTestReporter(writer);
	}
	
	@Test
	public void testWithSmallStarMap() {
		final String jsonPath = this.getClass().getClassLoader().getResource(NEAR_SPACE_JSON_FILE).getFile();
		final StarMap map = reader.buildStarMap(jsonPath, MAX_CONNECTION_DISTANCE);
		reporter.executeTestMethod(createCallback(
				"testWithSmallStarMap",
				"Test finding route in a small map of Solar neighborhood.",
				map));
	}
	
	@Test
	public void testWithMediumStarMap() {
		final String jsonPath = this.getClass().getClassLoader().getResource(RANDOM_100_JSON_FILE).getFile();
		final StarMap map = reader.buildStarMap(jsonPath, MAX_CONNECTION_DISTANCE);
		reporter.executeTestMethod(createCallback(
				"testWithMediumStarMap",
				"Test finding route in a small map of 100 random stars.",
				map));
	}
	
	@Test
	public void testWithLargeStarMap() {
		final String jsonPath = this.getClass().getClassLoader().getResource(RANDOM_1000_JSON_FILE).getFile();
		final StarMap map = reader.buildStarMap(jsonPath, MAX_CONNECTION_DISTANCE);
		reporter.executeTestMethod(createCallback(
				"testWithLargeStarMap",
				"Test finding route in a small map of 1000 random stars.",
				map));
	}
	
	@Test
	//@Ignore("Long-running test, enable explicitly when needed.")
	public void testWithEnormousStarMap() {
		final String jsonPath = this.getClass().getClassLoader().getResource(RANDOM_10000_JSON_FILE).getFile();
		final StarMap map = reader.buildStarMap(jsonPath, MAX_CONNECTION_DISTANCE);
		reporter.executeTestMethod(createCallback(
				"testWithEnormousStarMap",
				"Test finding route in a small map of 10000 random stars.",
				map));
	}
	
	private PerformanceTestReporter.TestMethodCallback createCallback(final String name, final String description, final StarMap map) {
		return new PerformanceTestReporter.TestMethodCallback() {
			@Override
			public String name() {
				return name;
			}
			
			@Override
			public void invoke() {
				Random random = new Random();
				NavigationNode from = map.stars.get(random.nextInt(map.stars.size()));
				NavigationNode to = map.stars.get(random.nextInt(map.stars.size()));
				routeFinder.findRoute(from, to);
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
