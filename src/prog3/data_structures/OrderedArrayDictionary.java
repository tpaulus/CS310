package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Tom Paulus
 *         Created on 4/24/17.
 */
public class OrderedArrayDictionary<K extends Comparable<K>, V> implements DictionaryADT<K, V> {
    private Node<K, V>[] array;
    private int size;
    private int modCount;

    public OrderedArrayDictionary(int size) {
        //noinspection unchecked
        this.array = (Node<K, V>[]) new Node[size];
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * Returns true if the dictionary has an object identified by
     * key in it, otherwise false.
     *
     * @param key {@link K} Key to search for containment
     */
    public boolean contains(K key) {
        return find(key, 0, size() - 1) != null;
    }

    /**
     * Adds the given key/value pair to the dictionary. Returns
     * false if the dictionary is full, or if the key is a duplicate.
     * Returns true if addition succeeded.
     *
     * @param key   {@link K} Key to Insert
     * @param value {@link V} Corresponding Value
     */
    public boolean add(K key, V value) {
        if (isFull() || contains(key))
            return false;

        Node<K, V> insertionNode = new Node<>(key, value);

        int insertionPoint = 0;
        if (!isEmpty())
            insertionPoint = findInsertionPoint(insertionNode, 0, size - 1);
        System.arraycopy(array, insertionPoint, array, insertionPoint + 1, size() - insertionPoint);
        array[insertionPoint] = insertionNode;
        size++;
        modCount++;
        return true;
    }

    /**
     * Deletes the key/value pair identified by the key parameter.
     * Returns true if the key/value pair was found and removed,
     * otherwise false.
     *
     * @param key {@link K} Key to delete
     */
    public boolean delete(K key) {
        final int index = findIndex(key, 0, size() - 1);
        if (index >= 0 && array[index] != null && array[index].key.compareTo(key) == 0) {
            array[index] = null;
            System.arraycopy(array, index + 1, array, index, size() - 1 - index);

            size--;
            modCount++;
            return true;
        }

        return false;
    }

    /**
     * Returns the value associated with the parameter key. Returns
     * null if the key is not found or the dictionary is empty.
     *
     * @param key {@link K} Key to search for
     */
    public V getValue(K key) {
        Node<K, V> node = find(key, 0, size() - 1);
        return node != null ? node.value : null;
    }

    /**
     * Returns the key associated with the parameter value. Returns
     * null if the value is not found in the dictionary. If more
     * than one key exists that matches the given value, returns the
     * first one found.
     *
     * @param value {@link V} Value to find corresponding key for
     */
    public K getKey(V value) {
        for (int i = 0; i < size(); i++) {
            Node<K, V> node = array[i];
            //noinspection unchecked
            if (((Comparable<V>) node.value).compareTo(value) == 0)
                return node.key;
        }

        return null;
    }

    /**
     * Returns the number of key/value pairs currently stored
     * in the dictionary
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the dictionary is at max capacity
     */
    public boolean isFull() {
        return size() == array.length;
    }

    /**
     * Returns true if the dictionary is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the Dictionary object to an empty state.
     */
    public void clear() {
        //noinspection unchecked
        array = (Node<K, V>[]) new Comparable[array.length];
        size = 0;
        modCount = 0;
    }

    /**
     * Returns an Iterator of the keys in the dictionary, in ascending
     * sorted order. The iterator must be fail-fast.
     */
    public Iterator<K> keys() {
        return new Iterator<K>() {
            int index = 0;
            long modStamp = modCount;

            public boolean hasNext() {
                if (modCount != modStamp) throw new ConcurrentModificationException(
                        "Structure Modified after Iterator Creation"
                );
                return this.index < size();
            }

            public K next() {
                if (!hasNext()) throw new NoSuchElementException();
                return array[index++].key;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Returns an Iterator of the values in the dictionary. The
     * order of the values must match the order of the keys.
     * The iterator must be fail-fast.
     */
    public Iterator<V> values() {
        return new Iterator<V>() {
            int index = 0;
            long modStamp = modCount;

            public boolean hasNext() {
                if (modCount != modStamp) throw new ConcurrentModificationException(
                        "Structure Modified after Iterator Creation"
                );
                return this.index < size();
            }

            public V next() {
                if (!hasNext()) throw new NoSuchElementException();
                return array[index++].value;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static class Node<K, V> implements Comparable<Node<K, V>> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(Node<K, V> node) {
            //noinspection unchecked
            return ((Comparable<K>) key).compareTo(node.key);
        }
    }

    private int findInsertionPoint(Node<K, V> insertionNode, int low, int high) {
        if (high < low)
            return low;

        int mid = (low + high) >> 1;
        if (insertionNode.compareTo(array[mid]) <= 0)
            return findInsertionPoint(insertionNode, low, mid - 1); // Bin Search Left
        return findInsertionPoint(insertionNode, mid + 1, high); // Bin Search Right
    }

    private int findIndex(K key, int low, int high) {
        final Node<K, V> keyNode = new Node<>(key, null);

        if (high < low) {
            if (high < 0) return -1;

            Node<K, V> node = array[high];
            if (node.compareTo(keyNode) != 0)
                return -1;
            return high;
        }

        int mid = (low + high) >> 1;
        if (array[mid].compareTo(keyNode) > 0) // Bin Search Left
            return findIndex(key, low, mid - 1);
        return findIndex(key, mid + 1, high); // Bin Search Right
    }

    private Node<K, V> find(K key, int low, int high) {
        int index = findIndex(key, low, high);
        if (index >= 0)
            return array[index];
        return null;
    }
}
