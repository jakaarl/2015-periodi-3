package tira.input;

import java.io.PrintStream;
import java.util.List;

import tira.navigation.BreadthFirstRouteFinder;
import tira.navigation.NavigationNode;
import tira.navigation.RouteFinder;
import tira.navigation.StarMap;
import tira.visualization.starmap.StarMapLauncher;

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
    
    public static void main(String[] args) {
        if (args.length != 3) {
            printUsage(System.err);
            System.exit(1);
        }
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
    
    static void printUsage(PrintStream printStream) {
        printStream.println("Required arguments: <jsonFile> <fromStar> <toStar>");
    }
    
    static StarMap loadStarMap(String jsonPath) {
        // TODO: pull up default settings and their modification?
        int maxJumpDistance = Integer.valueOf(System.getProperty(
                StarMapLauncher.MAX_JUMP_DISTANCE_KEY, String.valueOf(StarMapLauncher.DEFAULT_MAP_JUMP_DISTANCE)));
        JsonStarListReader reader = new JsonStarListReader();
        return reader.buildStarMap(jsonPath, maxJumpDistance);
    }
    
    private static NavigationNode findNavigationNode(StarMap starMap, String starName) {
        NavigationNode node = starMap.findStar(starName);
        if (node == null) {
            throw new IllegalArgumentException("No such star: " + starName);
        }
        return node;
    }
    
    private static void printResult(List<NavigationNode> route) {
        if (route.isEmpty()) {
            System.out.println("No route found.");
        } else {
            for (NavigationNode node : route) {
                System.out.println(node.star);
            }
        }
    }
    
}
