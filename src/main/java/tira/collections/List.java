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
     * Adds an element to this list at the given index. Index has to be zero,
     * or up to {@link #size()} - ie. appending to the list is allowed, but
     * only right after the current tail.
     * 
     * @param elem  element to add.
     * @param index insertion index (starting from zero).
     * 
     * @return  the element previously at given index, or <code>null</code> if none.
     * 
     * @throws  IndexOutOfBoundsException   if given index is negative, or more than {@link #size()}. 
     */
    E add(E elem, int index) throws IndexOutOfBoundsException;
    
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
     * than list size causes an exception.
     * 
     * @param index index to remove at.
     * 
     * @return  the removed element.
     * 
     * @throws  IndexOutOfBoundsException   if the given index is out of bounds.
     */
    E remove(int index) throws IndexOutOfBoundsException;
    
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

}
