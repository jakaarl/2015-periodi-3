package tira.collections;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    
    private static final int DEFAULT_SIZE = 10;
    private static final float GROW_FACTOR = 1.5f;
    private static final int MINIMUM_GROWTH = 10;
    
    /** Internal array for storing list elements. */
    private Object[] array;
    /** Index of the last used array &quot;slot&quot;. */
    private int tail;

    public ArrayList() {
        this(DEFAULT_SIZE);
    }
    
    public ArrayList(int initialSize) {
        this(new Object[initialSize], -1);
    }
    
    public ArrayList(E[] initialArray) {
    	this(initialArray, initialArray.length - 1);
    }
    
    private ArrayList(Object[] array, int tail) {
    	this.array = array;
        this.tail = tail;
    }
    
    @Override
    public boolean add(E elem) {
        ensureCapacity(1);
        array[++tail] = elem;
        return true;
    }

    @Override
    public void add(int index, E elem) {
    	if (index < 0 || index > size()) { // allows adding to end of list
            throw new ArrayIndexOutOfBoundsException(index);
        }
    	if (index == size()) {
    		ensureCapacity(1); // make sure there's room after tail
    		tail = index; // update tail location
    	}
    	
        array[index] = elem;
    }

    @Override
    public boolean addAll(Collection<? extends E> elems) {
        // TODO addAll implementation
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elems) {
     // TODO addAll implementation
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        array = new Object[array.length]; // discard old contents
        tail = -1;
    }

    @Override
    public boolean contains(Object elem) {
        for (Object content : array) {
            if (elem.equals(content)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> elems) {
        for (Object elem : elems) {
            boolean contains = this.contains(elem);
            if (!contains) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) array[index];
    }

    @Override
    public int indexOf(Object elem) {
        return indexOf(elem, true);
    }

    @Override
    public boolean isEmpty() {
        return (tail == -1);
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl<E>();
    }

    @Override
    public int lastIndexOf(Object elem) {
        return indexOf(elem, false);
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object elem) {
    	int index = indexOf(elem);
        if (index == -1) {
        	return false;
        } else {
        	remove(index);
        	return true;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        validateIndex(index);
        E elem = (E) array[index];
        for (int i = (index + 1); i < array.length; i++) {
            array[i - 1] = array[i];
        }
        tail--;
        return elem;
    }

    @Override
    public boolean removeAll(Collection<?> elems) {
        boolean removed = false;
        for (int i = 0; i < array.length; i++) {
            Object elem = array[i];
            if (elems.contains(elem)) {
                this.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> elems) {
        // TODO Auto-generated method stub
        return false;
    }

    @SuppressWarnings("unchecked")
	@Override
    public E set(int index, E elem) {
    	validateIndex(index);
    	E oldElem = (E) array[index];
    	array[index] = elem;
        return oldElem;
    }

    @Override
    public int size() {
        return tail + 1;
    }

    @Override
    public List<E> subList(int from, int to) {
    	validateIndex(from);
    	validateIndex(to - 1);
    	int length = to - from;
    	Object[] newArray = new Object[length];
        copyArrayTo(length, newArray);
        return new ArrayList<E>(newArray, length - 1);
    }

    @Override
    public Object[] toArray() {
    	int length = size();
        Object[] copied = new Object[length];
        copyArrayTo(length, copied);
        return copied;
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T> T[] toArray(T[] array) {
    	T[] copied = (array.length >= this.array.length
    			? array
				: (T[]) Array.newInstance(array.getClass().getComponentType(), this.array.length));
    	copyArrayTo(copied.length, copied);
        return copied;
    }
    
    void ensureCapacity(int elemCount) {
        if ((tail + elemCount) >= array.length) {
            growCapacity(elemCount);
        }
    }
    
    void growCapacity(int growAtLeastBy) {
        int growBy = determineGrowBy(growAtLeastBy);
        Object[] newArray = new Object[array.length + growBy];
        copyArrayTo(newArray);
        array = newArray; // discard old array
    }
    
    int determineGrowBy(int growAtLeastBy) {
        int minGrowth = (growAtLeastBy > MINIMUM_GROWTH ? growAtLeastBy : MINIMUM_GROWTH);
        int growth = Math.round(array.length * GROW_FACTOR);
        return (growth < minGrowth ? minGrowth : growth);
    }
    
    void validateIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
    
    private void copyArrayTo(Object[] newArray) {
    	copyArrayTo(array.length, newArray);
    }
    
    private void copyArrayTo(int length, Object[] newArray) {
        /* Could do this...
        for (int i = 0; i < length; i++) {
            newArray[i] = array[i];
        }
        ... but this probably utilizes system calls for efficiency: */
        System.arraycopy(array, 0, newArray, 0, length);
    }
    
    private int indexOf(Object elem, boolean firstIndexOf) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(elem)) {
                index = i;
                if (firstIndexOf) {
                    break;                    
                }
            }
        }
        return index;
    }
    
    private class IteratorImpl<T> implements Iterator<T> {
        
        private int cursor = -1;
        private boolean removedAtCursor = false;

        @Override
        public boolean hasNext() {
            return (array.length > (cursor + 1));
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if ((cursor + 1) >= array.length) {
                throw new NoSuchElementException();
            }
            removedAtCursor = false;
            return (T) array[++cursor];
        }

        @Override
        public void remove() {
            if (cursor < 0 || removedAtCursor) {
                throw new IllegalStateException();
            }
            removedAtCursor = true;
            array[cursor] = null;
        }
    }

}
