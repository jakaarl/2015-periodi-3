package tira.navigation;

import java.util.List;

/**
 * Route finder.
 */
public interface RouteFinder {
	
	/**
	 * Finds the shortest route between <code>from</code> and <code>to</code>. The returned list of nodes
	 * should be:
	 * <ul>
	 *   <li>empty, if no route exists</li>
	 *   <li>contain <code>from</code>, <code>to</code> and intermediate steps, if a route exists</li>
	 *   <li>be ordered, in order of travelsal</li>
	 * </ul>
	 * If there are several routes with the same length exist, any one may be returned.
	 * 
	 * @param from	starting node.
	 * @param to	goal node.
	 * 
	 * @return the route as an ordered list of nodes.
	 */
	public List<NavigationNode> findRoute(NavigationNode from, NavigationNode to);
	
}
