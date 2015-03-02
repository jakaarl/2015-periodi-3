package tira.navigation;

import tira.collections.List;
import tira.domain.Coordinates;
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
	 * @param stars    list of stars to build a map of.
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
	 * Calculates the bounding cube for this star map.
	 * 
	 * @return	star map bounding cube.
	 */
	public BoundingCube calculateBoundingCube() {
		int leftEdge = Integer.MAX_VALUE;
		int topEdge = Integer.MIN_VALUE;
		int rightEdge = Integer.MIN_VALUE;
		int bottomEdge = Integer.MAX_VALUE;
		int nearEdge = Integer.MIN_VALUE;
		int farEdge = Integer.MAX_VALUE;
		for (NavigationNode node : stars) {
			if (leftEdge > node.star.location.x) {
				leftEdge = node.star.location.x;
			}
			if (topEdge < node.star.location.y) {
				topEdge = node.star.location.y;
			}
			if (rightEdge < node.star.location.x) {
				rightEdge = node.star.location.x;
			}
			if (bottomEdge > node.star.location.y) {
				bottomEdge = node.star.location.y;
			}
			if (nearEdge < node.star.location.z) {
				nearEdge = node.star.location.z;
			}
			if (farEdge > node.star.location.z) {
				farEdge = node.star.location.z;
			}
		}
		return new BoundingCube(
				new Coordinates(leftEdge, topEdge, nearEdge),
				new Coordinates(rightEdge, bottomEdge, farEdge));
	}
	
	/**
	 * Builds a new star map. Notice, that indexed access to the list is heavily used, making
	 * an array list or similar implementation highly preferred over, say, linked list.
	 * 
	 * @param stars			list of stars.
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
	    for (int i = 0; i < stars.size(); i++) {
	        NavigationNode currentNode = stars.get(i);
	        Star currentStar = currentNode.star;
	        for (int j = i + 1; j < stars.size(); j++) {
	            NavigationNode otherNode = stars.get(j);
	            int distance = CALCULATOR.distance(currentStar.location, otherNode.star.location);
	            if (distance <= maxDistance) {
	                currentNode.connections.add(otherNode);
	                otherNode.connections.add(currentNode);
	            }
	        }
	    }
		return stars;
	}
	
}
