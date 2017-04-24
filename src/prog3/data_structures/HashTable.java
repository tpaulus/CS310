package data_structures;

import java.util.Iterator;

/**
 * @author Tom Paulus
 *         Created on 4/24/17.
 */
public class HashTable<K extends Comparable<K>,V> implements DictionaryADT<K, V> {
    private static final int DEFAULT_SIZE = 1000;
    private UnorderedList<DictionaryNode<K, V>>[] list;

    private int modCounter = 0;
    private int size = 0;

    public HashTable(int size) {
        //noinspection unchecked
        list = new UnorderedList[size];
        for (int i = 0; i < list.length; i++)
            list[i] = new UnorderedList<>();
    }

    /**
     * Returns true if the dictionary has an object identified by
     * key in it, otherwise false.
     *
     * @param key
     */
    public boolean contains(K key) {
        return false;
    }

    /**
     * Adds the given key/value pair to the dictionary. Returns
     * false if the dictionary is full, or if the key is a duplicate.
     * Returns true if addition succeeded.
     *
     * @param key
     * @param value
     */
        return false;
    public boolean add(K key, V value) {
    }

    /**
     * Deletes the key/value pair identified by the key parameter.
     * Returns true if the key/value pair was found and removed,
     * otherwise false.
     *
     * @param key
     */
    public boolean delete(K key) {
        return false;
    }

    /**
     * Returns the value associated with the parameter key. Returns
     * null if the key is not found or the dictionary is empty.
     *
     * @param key
     */
    public V getValue(K key) {
        return null;
    }

    /**
     * Returns the key associated with the parameter value. Returns
     * null if the value is not found in the dictionary. If more
     * than one key exists that matches the given value, returns the
     * first one found.
     *
     * @param value
     */
    public K getKey(V value) {
        return null;
    }

    /**
     * Returns the number of key/value pairs currently stored
     * in the dictionary
     */
    public int size() {
        return 0;
    }

    /**
     * Returns true if the dictionary is at max capacity
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Returns true if the dictionary is empty
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the Dictionary object to an empty state.
     */
    public void clear() {

    }

    /**
     * Returns an Iterator of the keys in the dictionary, in ascending
     * sorted order. The iterator must be fail-fast.
     */
    public Iterator<K> keys() {
        return null;
    }

    /**
     * Returns an Iterator of the values in the dictionary. The
     * order of the values must match the order of the keys.
     * The iterator must be fail-fast.
     */
    public Iterator<V> values() {
        return null;
    }
}
