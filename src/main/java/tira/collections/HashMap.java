package tira.collections;

import java.util.Iterator;

public class HashMap<K, V> implements Map<K, V> {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 30;
	private LinkedList<Object>[] buckets;
	private int size;
	//TODO: keep track "load factor", resize/rehash when needed!
	
	public HashMap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	
	public HashMap(int initialCapacity) {
		this(new LinkedList[initialCapacity]);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap(LinkedList[] buckets) {
		this.buckets = (LinkedList<Object>[]) buckets;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(K key, V value) {
		int index = getIndex(key);
		LinkedList<Object> entries = buckets[index];
		if (entries == null) {
			entries = new LinkedList<Object>();
			buckets[index] = entries;
		}
		for (Object entryObj : entries) {
			Entry<K, V> entry = (Entry<K, V>) entryObj;
			if (entry.key.equals(key)) {
				entry.value = value;
				return;
			}
		}
		entries.add(new Entry<K, V>(key, value));
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		V value = null;
		int index = getIndex(key);
		LinkedList<Object> entries = buckets[index];
		if (entries != null) {
			for (Object entryObj : entries) {
				Entry<K, V> entry = (Entry<K, V>) entryObj;
				if (entry.key.equals(key)) {
					value = entry.value;
					break;
				}
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(K key) {
		V value = null;
		int index = getIndex(key);
		LinkedList<Object> entries = buckets[index];
		if (entries != null) {
			for (Iterator<Object> iterator = entries.iterator(); iterator.hasNext();) {
				Entry<K, V> entry = (Entry<K, V>) iterator.next();
				if (entry.key.equals(key)) {
					value = entry.value;
					iterator.remove();
					size--;
					break;
				}
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(K key) {
		int index = getIndex(key);
		LinkedList<Object> entries = buckets[index];
		if (entries != null) {
			for (Object entryObj : entries) {
				Entry<K, V> entry = (Entry<K, V>) entryObj;
				if (entry.key.equals(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	private int getIndex(K key) {
		return (key.hashCode() % buckets.length);
	}
	
	private static class Entry<A, B> {
		private final A key;
		private B value;
		private Entry(A key, B value) {
			this.key = key;
			this.value = value;
		}
	}

}
