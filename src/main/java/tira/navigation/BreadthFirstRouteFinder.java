package tira.navigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tira.collections.ArrayListStack;
import tira.collections.Stack;

/**
 * An implementation of {@link RouteFinder} using Breadth First Search.
 * 
 * @see	http://en.wikipedia.org/wiki/Breadth-first_search
 */
public class BreadthFirstRouteFinder implements RouteFinder {

	@Override
	public List<NavigationNode> findRoute(NavigationNode from, NavigationNode to) {
	    /* Stack holding nodes to inspect. */
		Stack<NavigationNode> awaitingInspection = new ArrayListStack<>();
		/* List of nodes discovered, so we don't inspect them more than once. */
		List<NavigationNode> discovered = new ArrayList<>();
		/* Map for tracking our route; keeps track of where we arrived to each node from.  */
		Map<NavigationNode, NavigationNode> nodeToPrevious = new HashMap<NavigationNode, NavigationNode>();
		
		awaitingInspection.push(from);
		discovered.add(from);
		nodeToPrevious.put(from, null); // mark starting point of the route
		while (!awaitingInspection.isEmpty()) {
			NavigationNode current = awaitingInspection.pop();
			if (current.equals(to)) { // bingo! no need to look further
				break;
			}
			for (NavigationNode neighbor : current.connections) {
			    // queue any neighbors we haven't discovered yet
				if (!discovered.contains(neighbor)) {
					awaitingInspection.push(neighbor);
					discovered.add(neighbor);
					nodeToPrevious.put(neighbor, current);
				}
			}
		}
		
		return reconstructRoute(nodeToPrevious, to);
	}
	
	/**
	 * Reconstructs the route.
	 * 
	 * @param nodeToPrevious   the route tracking map.
	 * @param destination      the final node.
	 * 
	 * @return a list of nodes we traveled, <code>from</code> to <code>to</code>.
	 */
	private List<NavigationNode> reconstructRoute(Map<NavigationNode, NavigationNode> nodeToPrevious,
			NavigationNode destination) {
		List<NavigationNode> route;
		if (nodeToPrevious.containsKey(destination)) {
			route = new ArrayList<>();
			route.add(destination);
			for (NavigationNode current = nodeToPrevious.get(destination)
					; current != null
					; current = nodeToPrevious.get(current)) {
				route.add(current);
			}
			Collections.reverse(route); // order would be to-from without reversal
			return route;
		} else {
			return Collections.emptyList();
		}
	}

}
