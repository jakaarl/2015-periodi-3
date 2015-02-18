package tira.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HashMapTest {

	@Test
	public void shouldBeEmpty() {
		HashMap<String, Integer> map = new HashMap<>();
		assertEquals(0, map.size());
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void shouldIncrementSizeWhenAddingEntry() {
		HashMap<String, String> map = new HashMap<>();
		map.put("foo", "bar");
		assertEquals(1, map.size());
	}
	
	@Test
	public void getShouldReturnValue() {
		Integer key = 1;
		String value = "aVeryImportantPieceOfData";
		HashMap<Integer, String> map = new HashMap<>();
		map.put(key, value);
		String returned = map.get(key);
		assertEquals(value, returned);
	}
	
	@Test
	public void getShouldReturnNull() {
		String key = "totally not in the map";
		HashMap<String, Object> map = new HashMap<>();
		assertFalse(map.contains(key));
	}
	
	@Test
	public void shouldContainAddedEntry() {
		String key = "abloy";
		Integer value = 1;
		HashMap<String, Integer> map = new HashMap<>();
		map.put(key, value);
		assertTrue(map.contains(key));
	}
	
	@Test
	public void removeShouldRemoveAndReturnValue() {
		Integer key = 1;
		String value = "aVeryImportantPieceOfData";
		HashMap<Integer, String> map = new HashMap<>();
		map.put(key, value);
		String returned = map.remove(key);
		assertEquals(value, returned);
		assertFalse(map.contains(key));
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void shouldSurviveHashCollisions() {
		ObjectWithWorstHashCodeEver thing = new ObjectWithWorstHashCodeEver("thing");
		Integer thingValue = 1;
		ObjectWithWorstHashCodeEver other = new ObjectWithWorstHashCodeEver("other");
		Integer otherValue = 2;
		HashMap<ObjectWithWorstHashCodeEver, Integer> map = new HashMap<>();
		map.put(thing, thingValue);
		map.put(other, otherValue);
		assertEquals(2, map.size());
		assertEquals(thingValue, map.get(thing));
		assertEquals(otherValue, map.get(other));
	}
	
	private static class ObjectWithWorstHashCodeEver {
		
		private final String thingy;
		
		private ObjectWithWorstHashCodeEver(String thingy) {
			this.thingy = thingy;
		}
		
		@Override
		public boolean equals(Object other) {
			if (this == other) return true;
			if (other instanceof ObjectWithWorstHashCodeEver) {
				ObjectWithWorstHashCodeEver cast = (ObjectWithWorstHashCodeEver) other;
				return thingy.equals(cast.thingy);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return 1;
		}
		
	}
}
