package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Tom Paulus
 *         Created on 4/24/17.
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements DictionaryADT<K, V> {
    private Node<K, V> root;
    private int size;
    private int modCount;

    public BinarySearchTree() {
        root = null;
        size = 0;
        modCount = 0;
    }

    /**
     * Returns true if the dictionary has an object identified by
     * key in it, otherwise false.
     *
     * @param key {@link K} Key to search for
     */
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    /**
     * Adds the given key/value pair to the dictionary. Returns
     * false if the dictionary is full, or if the key is a duplicate.
     * Returns true if addition succeeded.
     *
     * @param key   {@link K} Key to Add
     * @param value {@link V} Corresponding Value
     */
    public boolean add(K key, V value) {
        final Node<K, V> insertionNode = new Node<>(key, value);

        if (root == null)
            root = insertionNode;
        else if (!put(root, insertionNode))
            return false;
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
    @SuppressWarnings("unchecked")
    public boolean delete(K key) {
        if (deleteNode(root, key) != null) {
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
     * @param key {@link K} Key to find value for
     */
    public V getValue(K key) {
        return getValue(root, key);
    }

    /**
     * Returns the key associated with the parameter value. Returns
     * null if the value is not found in the dictionary. If more
     * than one key exists that matches the given value, returns the
     * first one found.
     *
     * @param value {@link V} Value to find corresponding key for
     */
    @SuppressWarnings("unchecked")
    public K getKey(V value) {
        TraverseStructure traverseStructure = new TraverseStructure(size());
        traverseStructure.traverse(root);
        for (Node<K, V> node : traverseStructure.nodes) {
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
        return false;
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
        modCount = 0;
        root = null;
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

    private boolean put(Node<K, V> root, Node<K, V> insertionNode) {
        int direction = insertionNode.key.compareTo(root.key);
        if (direction < 0) {
            // Left
            if (root.leftChild == null) {
                root.leftChild = insertionNode;
                return true;
            }

            return put(root.leftChild, insertionNode);
        } else if (direction > 0) {
            // Right
            if (root.rightChild == null) {
                root.rightChild = insertionNode;
                return true;
            }

            return put(root.rightChild, insertionNode);
        } else
            // Duplicate Node
            return false;
    }

    private V getValue(Node<K, V> root, K key) {
        int direction = key.compareTo(root.key);
        if (direction < 0) {
            // Left
            if (root.leftChild == null) {
                return null;
            }

            return getValue(root.leftChild, key);
        } else if (direction > 0) {
            // Right
            if (root.rightChild == null) {
                return null;
            }

            return getValue(root.rightChild, key);
        } else
            // You found me!
            return root.value;
    }

    /**
     * Get minimum element in binary search tree
     */
    private Node<K, V> minimumElement(Node<K, V> root) {
        if (root.leftChild == null)
            return root;
        else {
            return minimumElement(root.leftChild);
        }
    }

    private Node<K, V> deleteNode(Node<K, V> root, K key) {
        if (root == null)
            return null;
        if (root.key.compareTo(key) > 0) {
            root.leftChild = deleteNode(root.leftChild, key);
        } else if (root.key.compareTo(key) < 0) {
            root.rightChild = deleteNode(root.rightChild, key);

        } else {
            // if nodeToBeDeleted have both children
            if (root.leftChild != null && root.rightChild != null) {
                Node<K, V> temp = root;
                // Finding minimum element from right
                Node<K, V> minNodeForRight = minimumElement(temp.rightChild);
                // Replacing current node with minimum node from right subtree
                root = minNodeForRight;
                // Deleting minimum node from right now
                deleteNode(root.rightChild, minNodeForRight.key);

            }
            // if nodeToBeDeleted has only left child
            else if (root.leftChild != null) {
                root = root.leftChild;
            }
            // if nodeToBeDeleted has only right child
            else if (root.rightChild != null) {
                root = root.rightChild;
            }
            // if nodeToBeDeleted do not have child (Leaf node)
            else
                root = null;
        }
        return root;
    }

    private class TraverseStructure {
        Node[] nodes;
        int index;

        public TraverseStructure(int size) {
            nodes = new Node[size];
            index = 0;
        }

        void traverse(Node root) {
            if (root == null) return;
            traverse(root.leftChild);
            nodes[index++] = root;
            traverse(root.rightChild);
        }
    }

    private abstract class IteratorHelper<E> implements Iterator<E> {
        final Node<K, V>[] nodes;
        int index;
        int modStamp;

        public IteratorHelper() {
            TraverseStructure traverseStructure = new TraverseStructure(size());
            traverseStructure.traverse(root);
            //noinspection unchecked
            nodes = traverseStructure.nodes;
            index = 0;
            modStamp = modCount;
        }

        public boolean hasNext() {
            if (modCount != modStamp) throw new ConcurrentModificationException(
                    "Structure modified after iterator was created");
            return index < nodes.length;
        }

        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class KeyIteratorHelper extends IteratorHelper<K> {
        public K next() {
            if (!hasNext()) throw new NoSuchElementException();
            return nodes[index++].key;
        }
    }

    private class ValueIteratorHelper extends IteratorHelper<V> {
        public V next() {
            if (!hasNext()) throw new NoSuchElementException();
            return nodes[index++].value;
        }
    }

    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;

        Node<K, V> leftChild;
        Node<K, V> rightChild;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
}
