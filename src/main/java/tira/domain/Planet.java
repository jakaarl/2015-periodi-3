package tira.domain;

public class Planet {
	
	private final boolean habitable;
	private final boolean minerals;
	
	public Planet(boolean habitable, boolean minerals) {
		this.habitable = habitable;
		this.minerals = minerals;
	}
	
	public boolean isHabitable() {
		return habitable;
	}
	
	public boolean hasMinerals() {
		return minerals;
	}
}
