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
	
	@Test
	public void addingElementAtShouldIncrementSize() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(0, one);
		assertEquals(1, list.size());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void addingElementAtShouldFailOnNegativeIndex() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(-1, one);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void addingElementAtShouldFailOnIndexExceedingSize() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(list.size() + 1, one);
	}
	
	@Test
	public void removingElementAtIndexShouldDecrementSize() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(one);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	@Test
	public void removingElementShouldDecrementSize() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(one);
		assertEquals(1, list.size());
		list.remove(one);
		assertEquals(0, list.size());
	}

}
