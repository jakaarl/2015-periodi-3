package tira.collections;

import java.util.Iterator;

/**
 * A simplified list interface.
 */
public interface List<E> extends Iterable<E> {
    
    /**
     * Adds an element to the end of this list.
     * 
     * @param elem  element to add.
     */
    void add(E elem);
    
    /**
     * Gets the element at given index.
     * 
     * @param index index of the element to get.
     * 
     * @return  element at the given index.
     * 
     * @throws IndexOutOfBoundsException    if index is negative or equal or greater than {@link #size()}.
     */
    E get(int index) throws IndexOutOfBoundsException;
    
    /**
     * Removes the given element from this list.
     * 
     * @param elem  element to remove.
     * 
     * @return  <code>true</code>, if element was removed, <code>false</code> if the list did not contain it.
     */
    boolean remove(E elem);
    
    /**
     * Removes an element at given index. Specifying a negative index, or index equal or higher
     * than list size causes an exception. Any elements after the removed one will be
     * shifted accordingly.
     * 
     * @param index index to remove at.
     * 
     * @return  the removed element.
     * 
     * @throws  IndexOutOfBoundsException   if the given index is out of bounds.
     */
    E remove(int index) throws IndexOutOfBoundsException;
    
    /**
     * Clears this list.
     */
    void clear();
    
    /**
     * Reverses the ordering of this list.
     */
    void reverse();
    
    
    /**
     * Checks if this list is empty.
     * 
     * @return  <code>true</code>, if empty.
     */
    boolean isEmpty();
    
    /**
     * Gets the current size of this list.
     * 
     * @return  current list size.
     */
    int size();
    
    /**
     * Gets an iterator over this list.
     * 
     * @return  an iterator.
     */
    Iterator<E> iterator();
    
    /**
     * Checks if this list contains the given element.
     * 
     * @param elem  element to check for.
     * 
     * @return  <code>true</code>, if element is contained in the list.
     */
    boolean contains(E elem);

}
