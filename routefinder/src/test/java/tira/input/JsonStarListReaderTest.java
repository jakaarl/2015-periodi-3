package tira.input;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import tira.domain.Coordinates;
import tira.domain.Star;
import tira.navigation.StarMap;

public class JsonStarListReaderTest {
	
    public static final String TEST_JSON_FILE = "starmap.json";
    
    private final JsonStarListReader jsonReader = new JsonStarListReader();
    private final TestHelper helper = TestHelper.INSTANCE;
    
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
	    List<Star> starList = jsonReader.readJson(helper.getFilePathForClasspathResource(TEST_JSON_FILE));
	    assertEquals(3, starList.size());
	}
	
	@Test
	public void shouldBuildStarMap() {
	    StarMap map = jsonReader.buildStarMap(helper.getFilePathForClasspathResource(TEST_JSON_FILE), 1000);
	    assertEquals(3, map.stars.size());
	}

}
