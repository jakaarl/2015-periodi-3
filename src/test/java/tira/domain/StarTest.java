package tira.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import tira.collections.List;
import tira.collections.ListWrapper;

public class StarTest {
	
	private static final List<Planet> EMPTY_PLANET_LIST = new ListWrapper<>();
	
	@Test
	public void equalObjectIdentityShouldImplyEquality() {
		Star one = new Star("Sol", new Coordinates(0, 0, 0), EMPTY_PLANET_LIST);
		Star other = one;
		assertEquals(one, other);
		assertEquals(one.hashCode(), other.hashCode());
		assertEquals(0, one.compareTo(other));
	}
	
	@Test
	public void equalLocationShouldImplyEquality() {
		Star one = new Star("Foo", new Coordinates(0, 0, 0), EMPTY_PLANET_LIST);
		Star other = new Star("Bar", new Coordinates(0, 0, 0), EMPTY_PLANET_LIST);
		assertEquals(one, other);
		assertEquals(one.hashCode(), other.hashCode());
		assertEquals(0, one.compareTo(other));
	}
	
	@Test
	public void inequalLocationShouldImplyInequality() {
		Star one = new Star("Foo", new Coordinates(1, 2, 3), EMPTY_PLANET_LIST);
		Star other = new Star("Bar", new Coordinates(3, 2, 1), EMPTY_PLANET_LIST);
		assertFalse(one.equals(other));
		assertFalse(one.hashCode() == other.hashCode());
		assertFalse(0 == one.compareTo(other));
	}

}
