package tira.collections;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

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
	public void shouldContainElement() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        assertEquals(true, list.contains(one));
	}
	
	@Test
    public void shouldNotContainElement() {
        ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        Integer two = 2;
        assertEquals(false, list.contains(two));
    }
	
	@Test
	public void shouldContainAllElements() {
	    ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        assertEquals(true, list.containsAll(Arrays.asList(one)));
	}
	
	@Test
    public void shouldNotContainAllElements() {
        ArrayList<Integer> list = new ArrayList<>();
        Integer one = 1;
        list.add(one);
        Integer two = 2;
        assertEquals(false, list.containsAll(Arrays.asList(one, two)));
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
	
	@Test
	public void shouldBeIterableInProperOrder() {
		ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
        	list.add(i);
        }
        Integer expected = 0;
        for (Integer elem : list) {
        	assertEquals(expected, elem);
        	expected = new Integer(expected.intValue() + 1);
        }
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
