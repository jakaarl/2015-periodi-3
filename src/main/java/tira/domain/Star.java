package tira.domain;

import java.util.List;

public class Star implements Comparable<Star> {
	
	public final String name; // irrelevant for all practical purposes
	public final Coordinates location; // assumed unique, practically determines star "identity"
	public final List<Planet> planets;
	
	public Star(String name, Coordinates location, List<Planet> planets) {
		this.name = name;
		this.location = location;
		this.planets = planets;
	}

	@Override
	public int compareTo(Star o) {
		return location.compareTo(o.location);
	}
	
	@Override
	public int hashCode() {
		return location.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Star) {
			return equals((Star) o);
		} else {
			return false;
		}
	}
	
	public boolean equals(Star o) {
		return location.equals(o.location);
	}
}
