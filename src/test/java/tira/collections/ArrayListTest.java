package tira.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class ArrayListTest {
	
	@Test
	public void emptyListShouldHaveZeroSize() {
		ArrayList<Integer> list = new ArrayList<>();
		assertEquals(0, list.size());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getByNegativeIndexShouldThrow() {
	    ArrayList<Integer> list = new ArrayList<>();
	    list.get(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
    public void getByZeroIndexShouldThrowIfEmpty() {
        ArrayList<Integer> list = new ArrayList<>();
        list.get(0);
    }
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getByIndexEqualingSizeShouldThrow() {
        ArrayList<Integer> list = new ArrayList<>();
        list.get(list.size());
    }
	
	@Test(expected = IndexOutOfBoundsException.class)
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
	public void removingElementAtIndexShouldDecrementSize() {
		ArrayList<Integer> list = new ArrayList<>();
		Integer one = 1;
		list.add(one);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	@Test
	public void removingElementAtIndexShouldShiftRemainingElements() {
	    ArrayList<Integer> list = new ArrayList<>();
	    Integer one = 1;
	    Integer two = 2;
	    list.add(one);
	    list.add(two);
	    list.remove(0);
	    assertEquals(two, list.get(0));
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
	public void shouldContainElement() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        assertTrue(list.contains(one));
	}
	
	@Test
    public void shouldNotContainElement() {
        ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        Integer two = 2;
        assertFalse(list.contains(two));
    }
	
	@Test
	public void shouldBeEmptyAfterClear() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
	}
	
	@Test
	public void shouldHandleReversingEmptyList() {
	    ArrayList<Integer> list = new ArrayList<>();
	    list.reverse();
	}
	
	@Test
	public void shouldHandleReversingOneElemList() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        list.reverse();
        assertEquals(one, list.get(0));
	}
	
	@Test
	public void shouldReverseEvenNumberOffElements() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        Integer two = 2;
        list.add(one);
        list.add(two);
        list.reverse();
        assertEquals(2, list.size());
        assertEquals(two, list.get(0));
        assertEquals(one, list.get(1));
	}
	
	@Test
    public void shouldReverseOddNumberOffElements() {
        ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        Integer two = 2;
        Integer three = 3;
        list.add(one);
        list.add(two);
        list.add(three);
        list.reverse();
        assertEquals(3, list.size());
        assertEquals(three, list.get(0));
        assertEquals(two, list.get(1));
        assertEquals(one, list.get(2));
	}
	
	@Test
	public void shouldBeIterableInProperOrder() {
		ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
        	list.add(i);
        }
        Integer expected = -1;
        for (Integer elem : list) {
            expected++;
        	assertEquals(expected, elem);
        }
        assertEquals(3, expected.intValue()); // make sure all elems were iterated 
	}
	
	@Test(expected = IllegalStateException.class)
	public void shouldThrowExceptionOnDoubleRemove() {
		ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
        	iterator.next();
        	iterator.remove();
        	iterator.remove();
        }
	}

}
