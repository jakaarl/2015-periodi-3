package tira.collections;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayListTest {
	
	@Test
	public void emptyListShouldHaveZeroSize() {
		ArrayList<Integer> list = new ArrayList<>();
		assertEquals(0, list.size());
	}
	
	@Test
	public void addingElementShouldIncrementSize() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(one);
		assertEquals(1, list.size());
	}

}
