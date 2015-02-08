package tira.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tira.domain.Star;

/**
 * A utility for generating and holding a list of {@link NavigationNode}s.
 */
public class StarMap {
	
	public final List<NavigationNode> stars;
	private static final DistanceCalculator CALCULATOR = new DistanceCalculator();

	/**
	 * Constructs a star map for holding navigation nodes.
	 * 
	 * @param homeworld	home world (star to start from).
	 */
	private StarMap(List<NavigationNode> stars) {
		this.stars = stars;
	}
	
	/**
	 * Finds a star by name.
	 * 
	 * @param name star name.
	 * 
	 * @return navigation node for the named star, or <code>null</code> if not found.
	 */
	public NavigationNode findStar(String name) {
	    // TODO: perhaps should have a sorted map for name->star?
	    for (NavigationNode node : stars) {
	        if (name.equals(node.star.name)) {
	            return node;
	        }
	    }
	    return null;
	}
	
	/**
	 * Builds a new star map. <b>Important:</b> the given list of stars needs to be
	 * modifiable and will be modified - provide a defensive copy if this is a problem.
	 * 
	 * @param stars			list of (other) stars (has to be modifiable, will be modified!).
	 * @param maxDistance	maximum connection distance between stars.
	 * 
	 * @return	a star map.
	 */
	public static StarMap build(List<NavigationNode> stars, int maxDistance) {
		if (stars == null || stars.isEmpty()) {
			throw new IllegalArgumentException("Null or empty star list");
		}
		if (maxDistance <= 0) {
			throw new IllegalArgumentException("Zero or negative maximum distance");
		}
		return new StarMap(buildNodes(stars, maxDistance));
	}
	
	private static List<NavigationNode> buildNodes(List<NavigationNode> stars, int maxDistance) {
		List<NavigationNode> nodes = new ArrayList<>();
		// umm, kind of redundant creating another list?
		for (Iterator<NavigationNode> iterator = stars.iterator(); iterator.hasNext(); ) {
			NavigationNode currentNode = iterator.next();
			iterator.remove();
			addConnections(currentNode, stars, maxDistance);
			nodes.add(currentNode);
		}
		return nodes;
	}
	
	private static void addConnections(NavigationNode currentNode, List<NavigationNode> stars, int maxDistance) {
		Star currentStar = currentNode.star;
		for (Iterator<NavigationNode> iterator = stars.iterator(); iterator.hasNext(); ) {
			NavigationNode otherNode = iterator.next();
			int distance = CALCULATOR.distance(currentStar.location, otherNode.star.location);
			if (distance <= maxDistance) {
				currentNode.connections.add(otherNode);
				otherNode.connections.add(currentNode);
			}
		}
	}
}
