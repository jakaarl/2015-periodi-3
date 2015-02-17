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
            tail = node;
        }
        size++;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Node<E> node = getNode(index);
        return node.item;
    }
    
    private Node<E> getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + "/ size: " + size);
        }
        Node<E> node = head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }
        return node;
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
        Node<E> node = getNode(index);
        E element = removeNode(node);
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
        Node<E> current = tail;
        tail = head;
        head = current;
        while (current != null) {
            Node<E> next = current.previous;
            current.previous = current.next;
            current.next = next;
            current = next;
        }
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
    
    private E removeNode(Node<E> node) {
        if (head == node) {
            head = node.next;
        }
        if (tail == node) {
            tail = node.previous;
        }
        if (node.previous != null) {
            node.previous.next = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        }
        size--;
        return node.item;
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
        
        private Node<E> current;
        private boolean deletedAtCursor = false;

        @Override
        public boolean hasNext() {
            return (current == null && current != tail);
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
            Node<E> previous = current.previous;
            removeNode(current);
            current = previous;
            deletedAtCursor = true;
        }
        
    }

}
