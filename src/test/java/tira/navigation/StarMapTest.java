package tira.navigation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import tira.domain.Coordinates;
import tira.domain.Planet;
import tira.domain.Star;

public class StarMapTest {
	
	private static final List<Planet> EMPTY_PLANETS = Collections.emptyList();
	private static final Star SOL = new Star("Sol", new Coordinates(0, 0, 0), EMPTY_PLANETS);
	private static final Star PROXIMA_CENTAURI =
			new Star("Proxima Centauri", new Coordinates(-304,292,-14), EMPTY_PLANETS);
	private static final Star ALPHA_CENTAURI = new Star("Alpha Centauri", new Coordinates(-307,315,-5), EMPTY_PLANETS);
	private static final Star BARNARDS_STAR = new Star("Barnard's Star", new Coordinates(297,494,145), EMPTY_PLANETS);
	private static final Star WOLF_359 = new Star("Wolf 359", new Coordinates(-391,-190,647), EMPTY_PLANETS);
	private static final Star LALANDE_21185 = new Star("Lalande 21185", new Coordinates(-31,-345,756), EMPTY_PLANETS);
	private static final Star SIRIUS = new Star("Sirius", new Coordinates(-623,-577,-133), EMPTY_PLANETS);
	private static final Star EPSILON_ERIDANI =
			new Star("Epsilon Eridani", new Coordinates(-192,-674,-782), EMPTY_PLANETS);
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionOnNullStarList() {
		StarMap.build(null, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionOnEmptyStarList() {
		StarMap.build(Arrays.asList(new Star[0]), 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionOnZeroMaxDistance() {
		StarMap.build(Arrays.asList(SOL), 0);
	}
	
	@Test
	public void shouldHaveCorrectConnections() {
		List<Star> stars = Arrays.asList(SOL, PROXIMA_CENTAURI, ALPHA_CENTAURI, BARNARDS_STAR);
		StarMap map = StarMap.build(stars, 500);
		assertFalse(map.stars.isEmpty());
		NavigationNode sol = map.stars.get(0);
		assertTrue(sol.star.equals(SOL));
		assertTrue(sol.connections.contains(PROXIMA_CENTAURI));
		assertTrue(sol.connections.contains(ALPHA_CENTAURI));
		assertFalse(sol.connections.contains(BARNARDS_STAR));
	}

}
