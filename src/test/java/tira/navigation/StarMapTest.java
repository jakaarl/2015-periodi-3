package tira.navigation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static tira.navigation.Stars.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tira.domain.Coordinates;
import tira.navigation.StarMap.BoundingCube;

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
		StarMap.build(Arrays.asList(new NavigationNode[0]), 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionOnZeroMaxDistance() {
		StarMap.build(Arrays.asList(new NavigationNode[] { SOL_NODE }), 0);
	}
	
	@Test
	public void shouldHaveCorrectConnections() {
	    // wrap in new list, since the one returned by Arrays.asList() does not
	    // support item removal, as needed by StarMap.build()
		List<NavigationNode> stars =
		        new ArrayList<>(Arrays.asList(SOL_NODE, PROXIMA_CENTAURI_NODE, ALPHA_CENTAURI_NODE, BARNARDS_STAR_NODE));
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
		List<NavigationNode> stars = new ArrayList<>(Arrays.asList(SOL_NODE, PROXIMA_CENTAURI_NODE));
		int listSize = stars.size();
		StarMap map = StarMap.build(stars, 500);
		assertEquals(listSize, map.stars.size());
		NavigationNode sol = map.findStar(SOL_NODE.star.name);
		assertNotNull(sol);
		assertEquals(SOL_NODE.star.name, sol.star.name);
	}
	
	@Test
	public void shouldReturnNullWhenNoStarFound() {
		List<NavigationNode> stars = new ArrayList<>(Arrays.asList(PROXIMA_CENTAURI_NODE));
		int listSize = stars.size();
		StarMap map = StarMap.build(stars, 500);
		assertEquals(listSize, map.stars.size());
		NavigationNode sol = map.findStar(SOL_NODE.star.name);
		assertNull(sol);
	}
	
	@Test
	public void shouldReturnBoundingCube() {
		List<NavigationNode> stars = new ArrayList<>(Arrays.asList(SOL_NODE, PROXIMA_CENTAURI_NODE));
		int listSize = stars.size();
		StarMap map = StarMap.build(stars, 500);
		assertEquals(listSize, map.stars.size());
		// Sol is located at 0,0,0 and Proxima Centauri at -304,292,-14
		// thus the bounding cube should be defined by Proxima at near top left
		// and Sol at far right bottom:
		Coordinates nearTopLeft = PROXIMA_CENTAURI.location;
		Coordinates farBottomRight = SOL.location;
		BoundingCube boundingCube = map.calculateBoundingCube();
		assertEquals(nearTopLeft, boundingCube.nearTopLeft);
		assertEquals(farBottomRight, boundingCube.farBottomRight);
	}

}
