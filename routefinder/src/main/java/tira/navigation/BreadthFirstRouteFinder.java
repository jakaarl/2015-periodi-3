package tira.navigation;

import tira.collections.ArrayList;
import tira.collections.HashMap;
import tira.collections.LinkedListQueue;
import tira.collections.List;
import tira.collections.Map;
import tira.collections.Queue;

/**
 * An implementation of {@link RouteFinder} using Breadth First Search.
 * 
 * @see	http://en.wikipedia.org/wiki/Breadth-first_search
 */
public class BreadthFirstRouteFinder implements RouteFinder {

	@Override
	public List<NavigationNode> findRoute(NavigationNode from, NavigationNode to) {
	    /* Stack holding nodes to inspect. */
		Queue<NavigationNode> awaitingInspection = new LinkedListQueue<>();
		/* List of nodes discovered, so we don't inspect them more than once. */
		List<NavigationNode> discovered = new ArrayList<>();
		/* Map for tracking our route; keeps track of where we arrived to each node from.  */
		Map<NavigationNode, NavigationNode> nodeToPrevious = new HashMap<NavigationNode, NavigationNode>();
		
		awaitingInspection.enqueue(from);
		discovered.add(from);
		nodeToPrevious.put(from, null); // mark starting point of the route
		while (!awaitingInspection.isEmpty()) {
			NavigationNode current = awaitingInspection.dequeue();
			if (current.equals(to)) { // bingo! no need to look further
				break;
			}
			for (NavigationNode neighbor : current.connections) {
			    // queue any neighbors we haven't discovered yet
				if (!discovered.contains(neighbor)) {
					awaitingInspection.enqueue(neighbor);
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
		if (nodeToPrevious.contains(destination)) {
			route = new ArrayList<>();
			route.add(destination);
			for (NavigationNode current = nodeToPrevious.get(destination)
					; current != null
					; current = nodeToPrevious.get(current)) {
				route.add(current);
			}
			route.reverse(); // order would be to-from without reversal
			return route; 
		} else {
			return new ArrayList<>(0);
		}
	}

}
