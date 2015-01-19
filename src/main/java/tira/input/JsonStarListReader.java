package tira.input;

import java.io.Reader;
import java.util.List;

import flexjson.JSONDeserializer;
import tira.domain.Star;
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

}
