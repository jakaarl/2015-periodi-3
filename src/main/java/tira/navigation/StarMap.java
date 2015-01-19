package tira.navigation;

import tira.collections.List;
import tira.domain.Star;

public class StarMap {
	
	public final NavigationNode homeworld;

	/**
	 * Constructs a star map for holding navigation nodes.
	 * 
	 * @param homeworld	home world (star to start from).
	 * @param stars		list of stars.
	 */
	public StarMap(Star homeworld, List<Star> stars) {
		if (homeworld == null) {
			throw new IllegalArgumentException("No homeworld given");
		}
		if (stars.isEmpty()) {
			throw new IllegalArgumentException("Empty star list");
		}
		this.homeworld = new NavigationNode(homeworld);
		// TODO: build network of navigation nodes
	}
}
