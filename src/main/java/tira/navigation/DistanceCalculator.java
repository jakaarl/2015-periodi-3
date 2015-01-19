package tira.navigation;

import tira.domain.Coordinates;

public class DistanceCalculator {
	
	public int distance(Coordinates from, Coordinates to) {
		return roundedSqrt(Math.pow(from.x - to.x, 2) + Math.pow(from.y - to.y, 2) + Math.pow(from.z - to.z, 2));
	}
	
	private static int roundedSqrt(double value) {
		return (int) Math.ceil(Math.sqrt(value));
	}

}
