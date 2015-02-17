package tira.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedListQueueTest {
    
    @Test
    public void shouldBeEmpty() {
        LinkedListQueue<Boolean> queue = new LinkedListQueue<>();
        assertTrue(queue.isEmpty());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void dequeueShouldThrowIfEmpty() {
        LinkedListQueue<Boolean> queue = new LinkedListQueue<>();
        queue.dequeue();
    }
    
    @Test
    public void dequeueShouldReturnQueuedElement() {
        LinkedListQueue<Object> queue = new LinkedListQueue<>();
        Object element = new Object();
        queue.enqueue(element);
        Object returned = queue.dequeue();
        assertEquals(element, returned);
    }
    
    @Test
    public void dequeueShouldReturnElementsInProperOrder() {
        LinkedListQueue<Object> queue = new LinkedListQueue<>();
        Object first = new Object();
        Object second = new Object();
        queue.enqueue(first);
        queue.enqueue(second);
        assertEquals(first, queue.dequeue());
        assertEquals(second, queue.dequeue());
    }

}
