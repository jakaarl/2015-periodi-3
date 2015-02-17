package tira.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    @Override
    public void add(E elem) {
        Node<E> node = new Node<>(elem, tail, null);
        if (head == null) {
            head = node;
        }
        if (tail == null) {
            tail = node;
        } else {
            tail.next = node;
        }
        size++;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + "/ size: " + size);
        }
        // TODO: separate method for traveling to node at index
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public boolean remove(E elem) {
        for (Iterator<E> iterator = iterator(); iterator.hasNext();) {
            E current = iterator.next();
            if (current.equals(elem)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        E element = null;
        // TODO travel to node at index, remove it from list and return item
        return element;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void reverse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>();
    }

    @Override
    public boolean contains(E elem) {
        for (Iterator<E> iterator = iterator(); iterator.hasNext();) {
            E current = iterator.next();
            if (current.equals(elem)) {
                return true;
            }
        }
        return false;
    }
    
    private static class Node<E> {
        private E item;
        private Node<E> previous;
        private Node<E> next;
        private Node(E item, Node<E> previous, Node<E> next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }
    
    private class LinkedListIterator<T> implements Iterator<E> {
        
        private Node<E> current = null;
        private boolean deletedAtCursor = false;

        @Override
        public boolean hasNext() {
            return (current == null || current != tail);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = head;
            } else {
                current = current.next;
            }
            deletedAtCursor = false;
            return current.item;
        }

        @Override
        public void remove() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            if (deletedAtCursor) {
                throw new IllegalStateException("Already deleted element at current position");
            }
            if (current.previous != null) {
                current.previous.next = current.next;
            }
            if (current.next != null) {
                current.next.previous = current.previous;
            }
            deletedAtCursor = true;
            size--;
        }
        
    }

}
