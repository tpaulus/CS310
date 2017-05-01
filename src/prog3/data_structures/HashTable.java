package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * An Chained Hash Map implementation of a Dictionary
 *
 * @author Tom Paulus
 *         Created on 4/24/17.
 */
public class HashTable<K extends Comparable<K>, V> implements DictionaryADT<K, V> {
    private UnorderedList<DictionaryNode<K, V>>[] list;

    private long modCounter = 0;
    private int size = 0;

    public HashTable(int size) {
        //noinspection unchecked
        list = new UnorderedList[((int) (size * 1.3))];
        for (int i = 0; i < list.length; i++)
            list[i] = new UnorderedList<>();
    }

    /**
     * Returns true if the dictionary has an object identified by
     * key in it, otherwise false.
     *
     * @param key {@link K} Key to search for
     */
    public boolean contains(K key) {
        return !isEmpty() && list[getHash(key)].contains(new DictionaryNode<K, V>(key, null));
    }

    /**
     * Adds the given key/value pair to the dictionary. Returns
     * false if the dictionary is full, or if the key is a duplicate.
     * Returns true if addition succeeded.
     *
     * @param key   {@link K} Key to Insert at
     * @param value {@link V} Value to Insert
     */
    public boolean add(K key, V value) {
        if (isFull() || contains(key)) return false;
        list[getHash(key)].addLast(new DictionaryNode<>(key, value));
        modCounter++;
        size++;
        return true;
    }

    /**
     * Deletes the key/value pair identified by the key parameter.
     * Returns true if the key/value pair was found and removed,
     * otherwise false.
     *
     * @param key {@link K} Key of Pair to Remove
     */
    public boolean delete(K key) {
        if (!isEmpty() && list[getHash(key)].remove(new DictionaryNode<K, V>(key, null)) != null) {
            size--;
            modCounter--;
            return true;
        }
        return false;
    }

    /**
     * Returns the value associated with the parameter key. Returns
     * null if the key is not found or the dictionary is empty.
     *
     * @param key {@link K} Key to retrieve from Dictionary
     */
    public V getValue(K key) {
        if (isEmpty() || key == null) return null;
        DictionaryNode<K, V> node = list[getHash(key)].find(new DictionaryNode<K, V>(key, null));
        return node != null ? node.value : null;
    }

    /**
     * Returns the key associated with the parameter value. Returns
     * null if the value is not found in the dictionary. If more
     * than one key exists that matches the given value, returns the
     * first one found.
     *
     * @param value {@link V} Value of Key/Value Pair to retrieve
     */
    public K getKey(V value) {
        for (UnorderedList<DictionaryNode<K, V>> nodeChain : list) {
            for (DictionaryNode<K, V> node : nodeChain) {
                //noinspection unchecked
                if (((Comparable<V>) node.value).compareTo(value) == 0)
                    return node.key;
            }
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
        return size == list.length;
    }

    /**
     * Returns true if the dictionary is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the Dictionary object to an empty state.
     */
    public void clear() {
        size = 0;
        modCounter++;
        for (UnorderedList<DictionaryNode<K, V>> nodeChain : list) {
            nodeChain.clear();
        }
    }

    /**
     * Returns an Iterator of the keys in the dictionary, in ascending
     * sorted order. The iterator must be fail-fast.
     */
    public Iterator<K> keys() {
        return new KeyIteratorHelper();
    }

    /**
     * Returns an Iterator of the values in the dictionary. The
     * order of the values must match the order of the keys.
     * The iterator must be fail-fast.
     */
    public Iterator<V> values() {
        return new ValueIteratorHelper();
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode() % this.list.length);
    }

    private static class DictionaryNode<K, V> implements Comparable<DictionaryNode<K, V>> {
        K key;
        V value;

        DictionaryNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(DictionaryNode<K, V> node) {
            //noinspection unchecked
            return ((Comparable<K>) key).compareTo(node.key);
        }
    }

    abstract class IteratorHelper<E> implements Iterator<E> {
        DictionaryNode<K, V>[] nodes;
        int index;
        long modificationStamp;

        IteratorHelper() {
            //noinspection unchecked
            nodes = new DictionaryNode[size];
            index = 0;
            int j = 0;
            modificationStamp = modCounter;

            for (UnorderedList<DictionaryNode<K, V>> chain : list)
                for (DictionaryNode node : chain)
                    //noinspection unchecked
                    nodes[j++] = node;


            nodes = shellSort(nodes);
        }

        private DictionaryNode<K, V>[] shellSort(DictionaryNode<K,V>[] n) {

            if (n.length < 2)
                return n;
            int in, out, h = 1;
            DictionaryNode<K, V> temp;
            int size = n.length;

            while (h <= size / 3)
                h = h * 3 + 1;
            while (h > 0) {
                for (out = h; out < size; out++) {
                    temp = n[out];
                    in = out;
                    while (in > h - 1 &&
                            ((Comparable) n[in - h]).compareTo(temp) >= 0) {
                        n[in] = n[in - h];
                        in -= h;
                    }
                    n[in] = temp;

                } // end for
                h = (h - 1) / 3;
            } // end while

            return n;
        }

        public boolean hasNext() {
            if (modCounter != modificationStamp) throw new ConcurrentModificationException(
                    "Structure Modified after Iterator Creation"
            );
            return index < size;
        }

        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class KeyIteratorHelper extends IteratorHelper<K> {
        KeyIteratorHelper() {
            super();
        }

        public K next() {
            return nodes[index++].key;
        }
    }

    class ValueIteratorHelper extends IteratorHelper<V> {
        ValueIteratorHelper() {
            super();
        }

        public V next() {
            return nodes[index++].value;
        }
    }
}
