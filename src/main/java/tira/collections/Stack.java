package tira.collections;

import java.util.NoSuchElementException;

/**
 * A simplified interface for a stack, ie. a LIFO queue.
 * 
 * @see http://en.wikipedia.org/wiki/Stack_(abstract_data_type)
 */
public interface Stack<E> {
    
    /**
     * Pushes (adds) an element to top of the stack.
     * 
     * @param elem  element to add.
     */
    void push(E elem);
    
    /**
     * Pops (removes) the element on top of the stack.
     * 
     * @return  the element on top of the stack.
     * 
     * @throws NoSuchElementException   if stack is empty.
     */
    E pop() throws NoSuchElementException;
    
    /**
     * Checks if the stack is empty.
     *  
     * @return  <code>true</code>, if stack is empty.
     */
    boolean isEmpty();
    

}
