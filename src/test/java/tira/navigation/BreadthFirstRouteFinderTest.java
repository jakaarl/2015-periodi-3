package tira.navigation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static tira.navigation.Stars.*;

import java.util.List;

import org.junit.Test;

public class BreadthFirstRouteFinderTest {
	
	@Test
	public void shouldReturnEmptyListIfNoRoute() {
		NavigationNode from = new NavigationNode(SOL);
		NavigationNode to = new NavigationNode(PROXIMA_CENTAURI);
		assertTrue(from.connections.isEmpty());
		assertTrue(to.connections.isEmpty());
		BreadthFirstRouteFinder routeFinder = new BreadthFirstRouteFinder();
		List<NavigationNode> route = routeFinder.findRoute(from, to);
		assertTrue(route.isEmpty());
	}
	
	@Test
	public void shouldReturnOrderedRoute() {
		NavigationNode from = new NavigationNode(SOL);
		NavigationNode to = new NavigationNode(PROXIMA_CENTAURI);
		from.connections.add(to);
		to.connections.add(from);
		BreadthFirstRouteFinder routeFinder = new BreadthFirstRouteFinder();
		List<NavigationNode> route = routeFinder.findRoute(from, to);
		assertEquals(2, route.size());
		assertEquals(from, route.get(0));
		assertEquals(to, route.get(1));
	}
	
	@Test
	public void shouldReturnCorrectRoute() {
		NavigationNode sol = new NavigationNode(SOL);
		NavigationNode proxima = new NavigationNode(PROXIMA_CENTAURI);
		NavigationNode alpha = new NavigationNode(ALPHA_CENTAURI);
		NavigationNode barnards = new NavigationNode(BARNARDS_STAR);
		/* Construct a network:
		 *       proxima
		 *     /     \
		 * sol --- alpha --- barnards
		 */
		sol.connections.add(proxima);
		sol.connections.add(alpha);
		proxima.connections.add(sol);
		proxima.connections.add(alpha);
		alpha.connections.add(sol);
		alpha.connections.add(proxima);
		alpha.connections.add(barnards);
		barnards.connections.add(alpha);
		BreadthFirstRouteFinder routeFinder = new BreadthFirstRouteFinder();
		List<NavigationNode> route = routeFinder.findRoute(sol, barnards);
		assertEquals(3, route.size());
		assertEquals(sol, route.get(0));
		assertEquals(alpha, route.get(1));
		assertEquals(barnards, route.get(2));
	}

}
