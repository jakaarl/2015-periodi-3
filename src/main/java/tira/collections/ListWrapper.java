package tira.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class ListWrapper<E> implements List<E> {
	
	private final java.util.List<E> wrapped = new java.util.ArrayList<>();

	@Override
	public boolean add(E e) {
		return wrapped.add(e);
	}

	@Override
	public void add(int index, E element) {
		wrapped.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return wrapped.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return wrapped.addAll(index, c);
	}

	@Override
	public void clear() {
		wrapped.clear();
	}

	@Override
	public boolean contains(Object o) {
		return wrapped.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return wrapped.containsAll(c);
	}

	@Override
	public E get(int index) {
		return wrapped.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return wrapped.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return wrapped.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return wrapped.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return wrapped.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return wrapped.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return wrapped.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return wrapped.remove(o);
	}

	@Override
	public E remove(int index) {
		return wrapped.remove(index);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return wrapped.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return wrapped.retainAll(c);
	}

	@Override
	public E set(int index, E element) {
		return wrapped.set(index, element);
	}

	@Override
	public int size() {
		return wrapped.size();
	}

	@Override
	public java.util.List<E> subList(int fromIndex, int toIndex) {
		return wrapped.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return wrapped.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return wrapped.toArray(a);
	}

}
