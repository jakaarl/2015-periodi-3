package tira.collections;

import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {
    
    private LinkedList<E> list = new LinkedList<>();

    @Override
    public void enqueue(E elem) {
        list.add(elem);
    }

    @Override
    public E dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
