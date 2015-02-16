package tira.commandline;

import java.io.PrintStream;

import tira.collections.List;
import tira.input.JsonStarListReader;
import tira.navigation.BreadthFirstRouteFinder;
import tira.navigation.NavigationNode;
import tira.navigation.RouteFinder;
import tira.navigation.StarMap;

/**
 * Runs route finder from the command line, printing out a route.
 * Requires arguments <code>jsonFile</code>, <code>fromStar</code>, <code>toStar</code>:
 * <ul>
 *   <li>jsonFile - path to star map JSON file</li>
 *   <li>fromStar - name of the starting star</li>
 *   <li>toStar - name of the destination star</li>
 * </ul>
 * 
 * @see {@link JsonStarListReader}
 * @see {@link RouteFinder}
 */
public class RouteFinderLauncher {
    
    static final String USAGE = "Required arguments: <jsonFile> <fromStar> <toStar>";
    static final String NO_ROUTE = "No route found.";
    
    private final PrintStream out;
    private final PrintStream err;
    
    public RouteFinderLauncher() {
        this(System.out, System.err);
    }
    
    RouteFinderLauncher(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }
    
    public void launch(String[] args) {
        if (args.length != 3) {
            err.println(USAGE);
            exit(1);
        } else {
            String jsonPath = args[0];
            String fromStarName = args[1];
            String toStarName = args[2];
            StarMap starMap = loadStarMap(jsonPath);
            NavigationNode fromNode = findNavigationNode(starMap, fromStarName);
            NavigationNode toNode = findNavigationNode(starMap, toStarName);
            RouteFinder routeFinder = new BreadthFirstRouteFinder();
            List<NavigationNode> route = routeFinder.findRoute(fromNode, toNode);
            printResult(route);
        }
    }
    
    public static void main(String[] args) {
        RouteFinderLauncher launcher = new RouteFinderLauncher();
        launcher.launch(args);
    }
    
    void printUsage(PrintStream printStream) {
        printStream.println("Required arguments: <jsonFile> <fromStar> <toStar>");
    }
    
    StarMap loadStarMap(String jsonPath) {
        // TODO: pull up default settings and their modification?
        int maxJumpDistance = Integer.valueOf(System.getProperty(
                StarMapLauncher.MAX_JUMP_DISTANCE_KEY, String.valueOf(StarMapLauncher.DEFAULT_MAP_JUMP_DISTANCE)));
        JsonStarListReader reader = new JsonStarListReader();
        return reader.buildStarMap(jsonPath, maxJumpDistance);
    }
    
    NavigationNode findNavigationNode(StarMap starMap, String starName) {
        NavigationNode node = starMap.findStar(starName);
        if (node == null) {
            throw new IllegalArgumentException("No such star: " + starName);
        }
        return node;
    }
    
    void printResult(List<NavigationNode> route) {
        if (route.isEmpty()) {
            out.println(NO_ROUTE);
        } else {
            for (NavigationNode node : route) {
                out.println(node.star);
            }
        }
    }
    
    void exit(int statusCode) {
        System.exit(statusCode);
    }
}
