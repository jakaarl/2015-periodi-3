package tira.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tira.domain.Star;

public class StarMap {
	
	public final List<NavigationNode> stars;

	/**
	 * Constructs a star map for holding navigation nodes.
	 * 
	 * @param homeworld	home world (star to start from).
	 */
	private StarMap(List<NavigationNode> stars) {
		this.stars = stars;
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
	public static StarMap build(List<Star> stars, int maxDistance) {
		if (stars == null || stars.isEmpty()) {
			throw new IllegalArgumentException("Null or empty star list");
		}
		if (maxDistance <= 0) {
			throw new IllegalArgumentException("Zero or negative maximum distance");
		}
		return new StarMap(buildNodes(stars, maxDistance));
	}
	
	private static List<NavigationNode> buildNodes(List<Star> stars, int maxDistance) {
		List<NavigationNode> nodes = new ArrayList<>();
		for (Iterator<Star> iterator = stars.iterator(); iterator.hasNext(); ) {
			NavigationNode currentNode = new NavigationNode(iterator.next());
			addConnections(currentNode, stars, maxDistance);
			nodes.add(currentNode);
		}
		return nodes;
	}
	
	private static void addConnections(NavigationNode currentNode, List<Star> stars, int maxDistance) {
		// TODO: redesign to avoid instantiating calculator and passing many args
		// TODO: perhaps create nodes in advance, add both ends of connections and remove already processed ones?
		DistanceCalculator calculator = new DistanceCalculator();
		Star currentStar = currentNode.star;
		for (Iterator<Star> iterator = stars.iterator(); iterator.hasNext(); ) {
			Star otherStar = iterator.next();
			if (currentStar.equals(otherStar)) {
				continue;
			}
			int distance = calculator.distance(currentStar.location, otherStar.location);
			if (distance <= maxDistance) {
				currentNode.connections.add(otherStar);
			}
		}
	}
}
