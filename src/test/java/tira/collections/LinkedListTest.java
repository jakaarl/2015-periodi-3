package tira.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LinkedListTest {
    
    @Test
    public void addingShouldIncrementSize() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.add("Hauki on kala.");
        assertEquals(1, list.size());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void getShouldThrowIfIndexOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        list.get(0);
    }
    
    @Test
    public void getShouldReturnAddedElement() {
        String added = "Look ma, I was added to a list!";
        LinkedList<String> list = new LinkedList<>();
        list.add(added);
        String returned = list.get(0);
        assertEquals(added, returned);
    }
    
    @Test
    public void shouldContainAddedElement() {
        String added = "I am content to be contained in the list.";
        LinkedList<String> list = new LinkedList<>();
        list.add(added);
        assertTrue(list.contains(added));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void removingShouldThrowIndexOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        list.remove(0);
    }
    
    @Test
    public void removingShouldDecrementSize() {
        LinkedList<String> list = new LinkedList<>();
        list.add("foo");
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }
    
    @Test
    public void removedElementShouldNotBeContained() {
        String added = "added";
        LinkedList<String> list = new LinkedList<>();
        list.add(added);
        assertTrue(list.contains(added));
        list.remove(added);
        assertFalse(list.contains(added));
    }
    
    @Test
    public void clearShouldEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("I'm outta here!");
        list.clear();
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void shouldIterateInProperOrder() {
        String[] elems = new String[] { "1st", "2nd", "3rd" };
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < elems.length; i++) {
            list.add(elems[i]);
        }
        int index = 0;
        for (String elem : list) {
            assertEquals(elems[index], elem);
            index++;
        }
        assertEquals(3, index);
    }
    
    @Test
    public void shouldReverseOrder() {
        String[] elems = new String[] { "1st", "2nd", "3rd" };
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < elems.length; i++) {
            list.add(elems[i]);
        }
        list.reverse();
        int index = 2;
        for (String elem : list) {
            assertEquals(elems[index], elem);
            index--;
        }
        assertEquals(-1, index);
    }

}
