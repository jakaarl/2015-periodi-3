package tira.navigation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tira.domain.Coordinates;

public class DistanceCalculatorTest {
	
	private DistanceCalculator calculator = new DistanceCalculator();
	
	@Test
	public void shouldReturnZeroBetweenPointsAtOrigo() {
		Coordinates one = new Coordinates(0, 0, 0);
		Coordinates other = new Coordinates(0, 0, 0);
		assertEquals(0, calculator.distance(one, other));
	}
	
	@Test
	public void shouldReturnZeroBetweenCoLocatedPoints() {
		Coordinates one = new Coordinates(1000, 2000, 300);
		Coordinates other = new Coordinates(1000, 2000, 300);
		assertEquals(0, calculator.distance(one, other));
	}
	
	@Test
	public void shouldReturnCorrectDistanceBetweenArbitraryPoints() {
		Coordinates one = new Coordinates(1, 2, 3);
		Coordinates other = new Coordinates(3, 2, 1);
		int expectedDistance = 3; // sqrt((1 - 3)^2 + (2 - 2)^2 + (3 - 1)^2) = sqrt(4 + 0 + 4) = 3 (rounded)
		assertEquals(expectedDistance, calculator.distance(one, other));
	}

}
