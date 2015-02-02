package tira.collections;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayListTest {
	
	@Test
	public void emptyListShouldHaveZeroSize() {
		ArrayList<Integer> list = new ArrayList<>();
		assertEquals(0, list.size());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getByNegativeIndexShouldThrow() {
	    ArrayList<Integer> list = new ArrayList<>();
	    list.get(-1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getByZeroIndexShouldThrowIfEmpty() {
        ArrayList<Integer> list = new ArrayList<>();
        list.get(0);
    }
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getByIndexEqualingSizeShouldThrow() {
        ArrayList<Integer> list = new ArrayList<>();
        list.get(list.size());
    }
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getByIndexExceedingSizeShouldThrow() {
        ArrayList<Integer> list = new ArrayList<>();
        list.get(list.size() + 1);
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
	public void addingElementAtShouldThrowOnNegativeIndex() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(-1, one);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void addingElementAtIndexShouldThrowOnIndexExceedingSize() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(list.size() + 1, one);
	}
	
	@Test
	public void addingElementAtEndShouldSucceed() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(list.size(), one);
        assertEquals(1, list.size());
	}
	
	@Test
	public void addingElementAtExistingEntryShouldReplaceIt() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        assertEquals(1, list.size());
        Integer two = 2;
        list.add(0, two);
        assertEquals(1, list.size());
        assertEquals(two, list.get(0));
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
	
	@Test
	public void shouldBeEmptyAfterClear() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        assertEquals(false, list.isEmpty());
        list.clear();
        assertEquals(true, list.isEmpty());
	}

}
