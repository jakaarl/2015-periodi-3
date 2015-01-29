package tira.navigation;

import java.util.Collections;
import java.util.List;

import tira.domain.Coordinates;
import tira.domain.Planet;
import tira.domain.Star;

public class Stars {
	
	private static final List<Planet> EMPTY_PLANETS = Collections.emptyList();
	public static final Star SOL = new Star("Sol", new Coordinates(0, 0, 0), EMPTY_PLANETS);
	public static final Star PROXIMA_CENTAURI =
			new Star("Proxima Centauri", new Coordinates(-304,292,-14), EMPTY_PLANETS);
	public static final Star ALPHA_CENTAURI = new Star("Alpha Centauri", new Coordinates(-307,315,-5), EMPTY_PLANETS);
	public static final Star BARNARDS_STAR = new Star("Barnard's Star", new Coordinates(297,494,145), EMPTY_PLANETS);
	
}
