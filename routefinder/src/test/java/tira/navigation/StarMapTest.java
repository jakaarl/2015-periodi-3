package tira.navigation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static tira.navigation.Stars.*;

import org.junit.Test;

import tira.collections.ArrayList;
import tira.collections.List;
import tira.domain.Coordinates;

public class StarMapTest {
	
	private static final NavigationNode SOL_NODE = new NavigationNode(SOL);
	private static final NavigationNode PROXIMA_CENTAURI_NODE = new NavigationNode(PROXIMA_CENTAURI);
	private static final NavigationNode ALPHA_CENTAURI_NODE = new NavigationNode(ALPHA_CENTAURI);
	private static final NavigationNode BARNARDS_STAR_NODE = new NavigationNode(BARNARDS_STAR);
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionOnNullStarList() {
		StarMap.build(null, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionOnEmptyStarList() {
		StarMap.build(new ArrayList<>(new NavigationNode[0]), 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionOnZeroMaxDistance() {
		StarMap.build(new ArrayList<>(new NavigationNode[] { SOL_NODE }), 0);
	}
	
	@Test
	public void shouldHaveCorrectConnections() {
	    // wrap in new list, since the one returned by Arrays.asList() does not
	    // support item removal, as needed by StarMap.build()
		List<NavigationNode> stars = new ArrayList<>(
				new NavigationNode[] { SOL_NODE, PROXIMA_CENTAURI_NODE, ALPHA_CENTAURI_NODE, BARNARDS_STAR_NODE });
		StarMap map = StarMap.build(stars, 500);
		assertFalse(map.stars.isEmpty());
		NavigationNode sol = map.stars.get(0);
		assertTrue(sol.star.equals(SOL_NODE.star));
		assertTrue(sol.connections.contains(PROXIMA_CENTAURI_NODE));
		assertTrue(sol.connections.contains(ALPHA_CENTAURI_NODE));
		assertFalse(sol.connections.contains(BARNARDS_STAR_NODE));
	}
	
	@Test
	public void shouldFindStarByName() {
		List<NavigationNode> stars = new ArrayList<>(new NavigationNode[] { SOL_NODE, PROXIMA_CENTAURI_NODE });
		StarMap map = StarMap.build(stars, 500);
		NavigationNode sol = map.findStar(SOL_NODE.star.name);
		assertNotNull(sol);
		assertEquals(SOL_NODE.star.name, sol.star.name);
	}
	
	@Test
	public void shouldReturnNullWhenNoStarFound() {
		List<NavigationNode> stars = new ArrayList<>(new NavigationNode[] { PROXIMA_CENTAURI_NODE });
		StarMap map = StarMap.build(stars, 500);
		NavigationNode sol = map.findStar(SOL_NODE.star.name);
		assertNull(sol);
	}
	
	@Test
	public void oneStarMapShouldHavePointLikeBoundingCube() {
		List<NavigationNode> stars = new ArrayList<>(new NavigationNode[] { SOL_NODE });
		StarMap map = StarMap.build(stars, 500);
		BoundingCube boundingCube = map.calculateBoundingCube();
		assertEquals(SOL.location, boundingCube.nearTopLeft);
		assertEquals(SOL.location, boundingCube.farBottomRight);
		assertTrue(boundingCube.contains(SOL.location));
	}
	
	@Test
	public void twoStarMapShouldHaveStarLocationsAsBoundingCubeCorners() {
		List<NavigationNode> stars = new ArrayList<>(new NavigationNode[] { SOL_NODE, PROXIMA_CENTAURI_NODE });
		StarMap map = StarMap.build(stars, 500);
		// Sol is located at 0,0,0 and Proxima Centauri at -304,292,-14
		// thus the bounding cube should be defined by:
		// - Proxima at top left
		// - Sol at right bottom
		// - Sol.z near, Proxima.z far
		Coordinates nearTopLeft = new Coordinates(
		        PROXIMA_CENTAURI.location.x, PROXIMA_CENTAURI.location.y, SOL.location.z);
		Coordinates farBottomRight = new Coordinates(
		        SOL.location.x, SOL.location.y, PROXIMA_CENTAURI.location.z);
		BoundingCube boundingCube = map.calculateBoundingCube();
		assertEquals(nearTopLeft, boundingCube.nearTopLeft);
		assertEquals(farBottomRight, boundingCube.farBottomRight);
		assertTrue(boundingCube.contains(SOL.location));
		assertTrue(boundingCube.contains(PROXIMA_CENTAURI.location));
	}
	
	@Test
	public void boundingCubeShouldNotContainGivenCoordinates() {
		List<NavigationNode> stars = new ArrayList<>(new NavigationNode[] { SOL_NODE, PROXIMA_CENTAURI_NODE });
		StarMap map = StarMap.build(stars, 500);
		BoundingCube boundingCube = map.calculateBoundingCube();
		assertFalse(boundingCube.contains(BARNARDS_STAR.location));
	}

}
