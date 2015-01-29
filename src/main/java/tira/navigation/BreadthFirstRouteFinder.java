package tira.navigation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * An implementation of {@link RouteFinder} using Breadth First Search.
 * 
 * @see	http://en.wikipedia.org/wiki/Breadth-first_search
 */
public class BreadthFirstRouteFinder implements RouteFinder {

	@Override
	public List<NavigationNode> findRoute(NavigationNode from, NavigationNode to) {
		Queue<NavigationNode> queue = new ArrayDeque<>();
		List<NavigationNode> discovered = new ArrayList<>();
		Map<NavigationNode, NavigationNode> nodeToPrevious = new HashMap<NavigationNode, NavigationNode>();
		
		queue.add(from);
		discovered.add(from);
		nodeToPrevious.put(from, null);
		while (!queue.isEmpty()) {
			NavigationNode current = queue.poll();
			if (current.equals(to)) {
				break;
			}
			for (NavigationNode neighbor : current.connections) {
				if (!discovered.contains(neighbor)) {
					queue.add(neighbor);
					discovered.add(neighbor);
					nodeToPrevious.put(neighbor, current);
				}
			}
		}
		
		return reconstructRoute(nodeToPrevious, to);
	}
	
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
			Collections.reverse(route);
			return route;
		} else {
			return Collections.emptyList();
		}
	}

}
