package tira.input;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import tira.domain.Coordinates;
import tira.domain.Star;
import tira.navigation.StarMap;

public class JsonStarListReaderTest {
	
    private static final String TEST_JSON_FILE = "stars.json";
    
    private JsonStarListReader jsonReader = new JsonStarListReader();
    
	@Test
	public void shouldDeserializeSampleStar() {
		String json = "[{ \"name\": \"Sample Star\", \"location\": { \"x\": 1, \"y\": 2, \"z\": 3}, \"planets\": []}]";
		Reader input = new StringReader(json);
		List<Star> starList = jsonReader.deserialize(input);
		assertEquals(1, starList.size());
		Star star = starList.get(0);
		assertEquals("Sample Star", star.name);
		assertEquals(new Coordinates(1, 2, 3), star.location);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnInvalidFile() {
        jsonReader.readJson("nonExistentFile");
	}
	
	@Test
	public void shouldReadFromFile() {
	    URL resourceUrl = this.getClass().getClassLoader().getResource(TEST_JSON_FILE);
	    List<Star> starList = jsonReader.readJson(resourceUrl.getFile());
	    assertEquals(2, starList.size());
	}
	
	@Test
	public void shouldBuildStarMap() {
	    URL resourceUrl = this.getClass().getClassLoader().getResource(TEST_JSON_FILE);
	    StarMap map = jsonReader.buildStarMap(resourceUrl.getFile(), 20);
	    assertEquals(2, map.stars.size());
	}

}
