package tira.commandline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import tira.input.JsonStarListReaderTest;
import tira.input.TestHelper;

public class RouteFinderLauncherTest {
    
    @Test
    public void shouldPrintUsageToErrAndExitOnInvalidArguments() {
        ByteArrayOutputStream errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        final int[] statusCodeHolder = new int[] { -1 }; 
        RouteFinderLauncher launcher = new RouteFinderLauncher(System.out, err) {
            void exit(int statusCode) {
                statusCodeHolder[0] = statusCode;
            }
        };
        launcher.launch(new String[0]);
        String errorMessage = errStream.toString();
        assertEquals(RouteFinderLauncher.USAGE + System.lineSeparator(), errorMessage);
        assertEquals(1, statusCodeHolder[0]);
    }
    
    @Test
    public void shouldPrintRoute() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        RouteFinderLauncher launcher = new RouteFinderLauncher(out, System.err);
                
        String jsonPath = TestHelper.INSTANCE.getFilePathForClasspathResource(JsonStarListReaderTest.TEST_JSON_FILE);
        String from = "Star One";
        String to = "Star Two";
        launcher.launch(new String[] { jsonPath, from, to });
        String output = outStream.toString();
        assertTrue(output.contains("Star One"));
        assertTrue(output.contains("Star Two"));
        assertFalse(output.contains("Distant Star"));
    }
    
    @Test
    public void shouldReportNoRouteFound() {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        RouteFinderLauncher launcher = new RouteFinderLauncher(out, System.err);
                
        String jsonPath = TestHelper.INSTANCE.getFilePathForClasspathResource(JsonStarListReaderTest.TEST_JSON_FILE);
        String from = "Star One";
        String to = "Distant Star";
        launcher.launch(new String[] { jsonPath, from, to });
        String output = outStream.toString();
        String expected = RouteFinderLauncher.NO_ROUTE + System.lineSeparator();
        assertEquals(expected, output);
    }

}
