package tira.collections;

import java.util.Iterator;
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
    public void add(E elem) {
        ensureCapacity(1);
        array[++tail] = elem;
    }

    @Override
    public void clear() {
        array = new Object[array.length]; // discard old contents
        tail = -1;
    }
    
    @Override
    public void reverse() {
        if (isEmpty()) {
            return;
        }
        for (int i = 0; i <= (tail / 2); i++) {
            Object temp = array[i];
            array[i] = array[tail - i];
            array[tail - i] = temp;
        }
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

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) array[index];
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
    public boolean remove(Object elem) {
    	int index = indexOf(elem);
        if (index == -1) {
        	return false;
        } else {
        	remove(index);
        	return true;
        }
    }
    
    private int indexOf(Object elem) {
        return indexOf(elem, true);
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
    public int size() {
        return tail + 1;
    }
    
    protected int tail() {
        return tail;
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
            return (tail >= (cursor + 1));
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if ((cursor + 1) > tail) {
                throw new NoSuchElementException();
            }
            removedAtCursor = false;
            return (T) array[++cursor];
        }

        @Override
        public void remove() {
            if (cursor < 0 || removedAtCursor) {
                throw new IllegalStateException("Already removed at cursor position");
            }
            removedAtCursor = true;
            ArrayList.this.remove(cursor);
            cursor--; // otherwise we'd point at next() already
        }
    }

}
