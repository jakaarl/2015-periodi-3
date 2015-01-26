package tira.navigation;

import tira.domain.Coordinates;

public class DistanceCalculator {
	
	/**
	 * Calculates the distance between two points in 3D space.
	 * 
	 * @param from	starting point.
	 * @param to	destination.
	 * 
	 * @return	distance between <code>from</code> and <code>to</code>.
	 * 
	 * @see	http://en.wikipedia.org/wiki/Euclidean_distance
	 */
	public int distance(Coordinates from, Coordinates to) {
		return roundedSqrt(Math.pow(from.x - to.x, 2) + Math.pow(from.y - to.y, 2) + Math.pow(from.z - to.z, 2));
	}
	
	private static int roundedSqrt(double value) {
		return (int) Math.ceil(Math.sqrt(value));
	}

}
