package tira.input;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import tira.domain.Coordinates;
import tira.domain.Star;

public class JsonStarListReaderTest {
	
	@Test
	public void shouldDeserializeSampleStar() {
		String json = "[{ \"name\": \"Sample Star\", \"location\": { \"x\": 1, \"y\": 2, \"z\": 3}, \"planets\": []}]";
		Reader input = new StringReader(json);
		JsonStarListReader jsonReader = new JsonStarListReader();
		List<Star> starList = jsonReader.deserialize(input);
		assertEquals(1, starList.size());
		Star star = starList.get(0);
		assertEquals("Sample Star", star.name);
		assertEquals(new Coordinates(1, 2, 3), star.location);
	}

}
