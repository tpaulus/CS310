package data_structures;

import java.util.Iterator;

/**
 * @author Tom Paulus
 *         Created on 4/24/17.
 */
public class BinarySearchTree<K, V> implements DictionaryADT {
    /**
     * Returns true if the dictionary has an object identified by
     * key in it, otherwise false.
     *
     * @param key
     */
    public boolean contains(Comparable key) {
        // TODO
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
    public boolean add(Comparable key, Object value) {
        // TODO
        return false;
    }

    /**
     * Deletes the key/value pair identified by the key parameter.
     * Returns true if the key/value pair was found and removed,
     * otherwise false.
     *
     * @param key
     */
    public boolean delete(Comparable key) {
        // TODO
        return false;
    }

    /**
     * Returns the value associated with the parameter key. Returns
     * null if the key is not found or the dictionary is empty.
     *
     * @param key
     */
    public Object getValue(Comparable key) {
        // TODO
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
    public Comparable getKey(Object value) {
        // TODO
        return null;
    }

    /**
     * Returns the number of key/value pairs currently stored
     * in the dictionary
     */
    public int size() {
        // TODO
        return 0;
    }

    /**
     * Returns true if the dictionary is at max capacity
     */
    public boolean isFull() {
        // TODO
        return false;
    }

    /**
     * Returns true if the dictionary is empty
     */
    public boolean isEmpty() {
        // TODO
        return false;
    }

    /**
     * Returns the Dictionary object to an empty state.
     */
    public void clear() {
        // TODO

    }

    /**
     * Returns an Iterator of the keys in the dictionary, in ascending
     * sorted order. The iterator must be fail-fast.
     */
    public Iterator keys() {
        // TODO
        return null;
    }

    /**
     * Returns an Iterator of the values in the dictionary. The
     * order of the values must match the order of the keys.
     * The iterator must be fail-fast.
     */
    public Iterator values() {
        // TODO
        return null;
    }
}
