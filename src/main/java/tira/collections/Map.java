package tira.collections;

/**
 * A simplified map interface. A map, also known as <i>associative array</i>,
 * maps keys to values. Duplicate keys are not permitted; putting a new key-value
 * pair with an existing key replaces the previous value.
 * 
 * @see http://en.wikipedia.org/wiki/Associative_array
 */
public interface Map<K,V> {
    
    /**
     * Adds a new key-value pair to the map.
     *  
     * @param key   key.
     * @param value value to be associated with the key.
     */
    void put(K key, V value);
    
    /**
     * Gets the value associated with the given key.
     * @param key
     * @return
     */
    V get(K key);
    
    /**
     * Removes a key-value association from this map.
     * 
     * @param key   key to remove.
     * 
     * @return  the associated value removed.
     */
    V remove(K key);
    
    /**
     * Checks if this map contains the given key.
     * 
     * @param key      key to check.
     *  
     * @return  <code>true</code> if a value has been associated with the key.
     */
    boolean contains(K key);
}
