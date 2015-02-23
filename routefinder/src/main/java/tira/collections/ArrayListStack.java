package tira.collections;

import java.util.NoSuchElementException;

/**
 * An implementation of {@link Stack} based on {@link ArrayList}.
 */
public class ArrayListStack<E> implements Stack<E> {
    
    private final ArrayList<E> list;
    
    public ArrayListStack() {
        this(new ArrayList<E>());
    }
    
    public ArrayListStack(ArrayList<E> list) {
        this.list = list;
    }
    
    @Override
    public void push(E elem) {
        list.add(elem);
    }

    @Override
    public E pop() throws NoSuchElementException {
        return list.remove(list.tail());
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
