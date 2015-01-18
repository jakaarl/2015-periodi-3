package tira.domain;

import java.util.List;

public class Star {
	
	public final String name;
	public final Coordinates location;
	public final List<Planet> planets;
	
	public Star(String name, Coordinates location, List<Planet> planets) {
		this.name = name;
		this.location = location;
		this.planets = planets;
	}
	
}
