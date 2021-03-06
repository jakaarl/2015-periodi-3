package tira.input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import flexjson.JSONDeserializer;
import tira.collections.ArrayList;
import tira.domain.Star;
import tira.navigation.NavigationNode;
import tira.navigation.StarMap;

/**
 * A utility for reading a JSON star list and constructing a {@link StarMap} from it.
 * The expected JSON format is like this:
 * <pre>
 * [
 *   { "name": "Star Name",
 *     "location": { "x": 1, "y": 2, "z": 3},
 *     "planets": [ { "habitable": false, "minerals": true }, { "habitable": true, "minerals": true }, ... ]
 *   },
 *   ...
 * ]
 * </pre>
 */
public class JsonStarListReader {
	
	private JSONDeserializer<List<Star>> deserializer = new JSONDeserializer<>();
	{
		deserializer.use("values", Star.class);
	}
	
	List<Star> deserialize(Reader input) {
		return deserializer.deserialize(input);
	}
	
	public List<Star> readJson(String fileName) throws IllegalArgumentException {
	    try {
	        FileReader input = new FileReader(fileName);
	        List<Star> stars = deserialize(input);
	        return stars;
	    } catch (FileNotFoundException fnfe) {
	        throw new IllegalArgumentException("Failed to read JSON input file: " + fileName, fnfe);
	    }
	}
	
	public StarMap buildStarMap(String fileName, int maxDistance) {
	    List<Star> stars = readJson(fileName);
	    tira.collections.List<NavigationNode> nodes = new ArrayList<>(stars.size());
	    for (Star star : stars) {
	        nodes.add(new NavigationNode(star));
	    }
	    StarMap map = StarMap.build(nodes, maxDistance);
	    return map;
	}

}
