package tira.navigation;

import static tira.input.Constants.*;
import static tira.perftest.ReportWriterFactory.reportWriterFor;

import java.io.IOException;
import java.io.Writer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import tira.collections.ArrayList;
import tira.collections.List;
import tira.domain.Star;
import tira.input.JsonStarListReader;
import tira.perftest.PerformanceTestReporter;

public class StarMapPerfTest {
    
    private static PerformanceTestReporter reporter;
    
    @BeforeClass
    public static void initializeReporter() throws IOException {
        Writer writer = reportWriterFor(StarMapPerfTest.class);
        reporter = new PerformanceTestReporter(writer);
    }
    
    @Test
    public void testWithMediumStarMap() {
        final List<NavigationNode> nodes = readNodes(RANDOM_100_JSON_FILE);
        reporter.executeTestMethod(new PerformanceTestReporter.TestMethodCallback() {
            
            @Override
            public String name() {
                return "testWithMediumStarMap";
            }
            
            @Override
            public void invoke() {
                StarMap.build(nodes, MAX_CONNECTION_DISTANCE);
            }
            
            @Override
            public String describe() {
                return "Test creating a StarMap from medium list of stars";
            }
        });
    }
    
    @Test
    public void testWithLargeStarMap() {
        final List<NavigationNode> nodes = readNodes(RANDOM_1000_JSON_FILE);
        reporter.executeTestMethod(new PerformanceTestReporter.TestMethodCallback() {
            
            @Override
            public String name() {
                return "testWithLargeStarMap";
            }
            
            @Override
            public void invoke() {
                StarMap.build(nodes, MAX_CONNECTION_DISTANCE);
            }
            
            @Override
            public String describe() {
                return "Test creating a StarMap from large list of stars";
            }
        });
    }
    
    @Test
    @Ignore("Long-running test, enable explicitly when needed.")
    public void testWithEnormousStarMap() {
        final List<NavigationNode> nodes = readNodes(RANDOM_10000_JSON_FILE);
        reporter.executeTestMethod(new PerformanceTestReporter.TestMethodCallback() {
            
            @Override
            public String name() {
                return "testWithEnormousStarMap";
            }
            
            @Override
            public void invoke() {
                StarMap.build(nodes, MAX_CONNECTION_DISTANCE);
            }
            
            @Override
            public String describe() {
                return "Test creating a StarMap from enormous list of stars";
            }
        });
    }
    
    private List<NavigationNode> readNodes(String jsonResource) {
        String jsonPath = StarMapPerfTest.class.getClassLoader().getResource(jsonResource).getFile();
        JsonStarListReader reader = new JsonStarListReader();
        java.util.List<Star> stars = reader.readJson(jsonPath);
        List<NavigationNode> nodes = new ArrayList<>(stars.size());
        for (Star star : stars) {
            nodes.add(new NavigationNode(star));
        }
        return nodes;
    }
    
    @AfterClass
    public static void closeReporter() throws IOException {
        reporter.done();
    }

}
