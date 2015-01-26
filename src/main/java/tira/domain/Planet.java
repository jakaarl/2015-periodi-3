package tira.domain;

/**
 * A very simple representation of a planet. Indicates whether or not the planet has:
 * <ul>
 *   <li>habitable conditions</li>
 *   <li>useful minerals</li>
 * </ul>
 */
public class Planet {
	
	private final boolean habitable;
	private final boolean minerals;
	
	public Planet(boolean habitable, boolean minerals) {
		this.habitable = habitable;
		this.minerals = minerals;
	}
	
	@SuppressWarnings("unused")
	private Planet() {
		this.habitable = false;
		this.minerals = false;
	}
	
	public boolean isHabitable() {
		return habitable;
	}
	
	public boolean hasMinerals() {
		return minerals;
	}
}
