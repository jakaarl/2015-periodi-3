package tira.collections;

import java.util.NoSuchElementException;

/**
 * A simplified interface for a (first-in-first-out, FIFO) queue.
 */
public interface Queue<E> {
    
    /**
     * Adds an element to the tail of the queue.
     * 
     * @param elem  element to add.
     */
    void enqueue(E elem);
    
    /**
     * Removes an element from the head of the queue.
     * 
     * @return  element at the head of the queue.
     * 
     * @throws NoSuchElementException   if the queue is empty.
     */
    E dequeue() throws NoSuchElementException;
    
    /**
     * Checks if the queue is empty.
     * 
     * @return  <code>true</code> if empty.
     */
    boolean isEmpty();

}
