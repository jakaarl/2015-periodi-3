package tira.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	 * Calculates the bounding cube for this star map.
	 * 
	 * @return	star map bounding cube.
	 */
	public BoundingCube calculateBoundingCube() {
		int leftEdge = Integer.MAX_VALUE;
		int topEdge = Integer.MIN_VALUE;
		int rightEdge = Integer.MIN_VALUE;
		int bottomEdge = Integer.MAX_VALUE;
		int nearEdge = Integer.MAX_VALUE;
		int farEdge = Integer.MIN_VALUE;
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
			if (nearEdge > node.star.location.z) {
				nearEdge = node.star.location.z;
			}
			if (farEdge < node.star.location.z) {
				farEdge = node.star.location.z;
			}
		}
		return new BoundingCube(
				new Coordinates(leftEdge, topEdge, nearEdge),
				new Coordinates(rightEdge, bottomEdge, farEdge));
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
	
	/**
	 * A value class depicting a bounding cube.
	 */
	public static class BoundingCube {
		
		/** Near top left corner: the least x and z, most y. */
		public final Coordinates nearTopLeft;
		/** Far bottom right corner: the most x and z, least y. */ 
		public final Coordinates farBottomRight;
		
		public BoundingCube(Coordinates nearTopLeft, Coordinates farBottomRight) {
			this.nearTopLeft = nearTopLeft;
			this.farBottomRight = farBottomRight;
		}
	}
}
