package tira.navigation;

import tira.collections.List;
import tira.collections.ListWrapper;
import tira.domain.Star;

/**
 * A node describing the &quot;connections&quot; of a star. Each star is considered connected to all
 * stars within the maximum travel distance.
 */
public class NavigationNode implements Comparable<NavigationNode> {
	
	public final Star star;
	public final List<Star> connections;
	
	public NavigationNode(Star star) {
		this.star = star;
		this.connections = new ListWrapper<>();
	}

	@Override
	public int compareTo(NavigationNode o) {
		return star.location.compareTo(o.star.location);
	}
	
	@Override
	public int hashCode() {
		return star.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return star.equals(o);
	}
	
}