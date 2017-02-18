package data_structures;

import java.util.Iterator;

/**
 * TODO JavaDoc
 *
 * @author Tom Paulus
 *         Created on 2/18/17.
 */
public class LinearLinkedList<E extends Comparable<E>> implements LinearListADT<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public LinearLinkedList() {
        size = 0;
        head = tail = null;
    }

    /**
     * Adds the Object obj to the end of list.
     *
     * @param obj
     */
    @Override
    public void addLast(E obj) {
        Node<E> newNode = new Node<>(obj);
        if (isEmpty()) head = tail = newNode;
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds the Object obj to the beginning of list.
     *
     * @param obj
     */
    @Override
    public void addFirst(E obj) {
        Node<E> newNode = new Node<>(obj);
        newNode.next = head;
        head = newNode;
        if (isEmpty()) tail = newNode;
        size++;
    }

    /**
     * Inserts the Object obj at the position indicated.  If there is an element at
     * that location, all elements from that location to the end of the list are
     * shifted down to make room for the new insertion.  The location is one based.
     * If the location > size()+1 then a RuntimeException is thrown. List elements
     * must be contiguous.
     *
     * @param obj
     * @param location
     */
    @Override
    public void insert(E obj, int location) {
        if (location > size() + 1 || location < 1) throw new RuntimeException("Invalid Index");
        if (location == 1) addFirst(obj);
        else if (location == size() + 1) addLast(obj);
        else {
            Node<E> node = head;
            for (int i = 0; i < size(); i++) {
                if (i == location - 2) { // Find the node before the insertion point
                    Node<E> newNode = new Node<>(obj);
                    newNode.next = node.next;
                    node.next = newNode;
                    size++;
                    break;
                }

                node = node.next;
            }
        }
    }

    /**
     * Removes the object located at the parameter location (one based).
     * Throws a RuntimeException if the location does not map to a valid position within the list.
     *
     * @param location
     */
    @Override
    public E remove(int location) {
        if (size() == 0) throw new RuntimeException("Empty List");
        if (location > size() + 1 || location < 1) throw new RuntimeException("Invalid Index");
        E obj = null;

        if (location == 1) return removeFirst();
        else if (location == size()) return removeLast();
        else {
            Node<E> node = head;
            for (int i = 0; i < size(); i++) {
                if (i == location - 2) {
                    obj = node.next.data;
                    node.next = node.next.next;
                    size--;
                    break;
                }

                node = node.next;
            }
        }
        return obj;
    }

    /**
     * Removes and returns the parameter object obj from the list if the list contains it, null otherwise.
     * The ordering of the list is preserved.  The list may contain duplicate elements.  This method
     * removes and returns the first matching element found when traversing the list from first position.
     *
     * @param obj
     */
    @Override
    public E remove(E obj) {
        if (size() == 0) return null;

        Node<E> node = head;
        Node<E> previous = head;
        E foundObj = null;

        for (int i = 0; i < size(); i++) {
            if (node.data.compareTo(obj) == 0) {
                foundObj = node.data;
                previous.next = node.next; // Drop the current node
                size--;
                break;
            }

            previous = node;
            node = node.next;
        }

        return foundObj;
    }

    /**
     * Removes and returns the parameter object obj in first position in list if the list is not empty,
     * null if the list is empty. The ordering of the list is preserved.
     */
    @Override
    public E removeFirst() {
        if (size() == 0) return null;
        E obj = head.data;
        head = head.next;
        size--;
        return obj;
    }

    /**
     * Removes and returns the parameter object obj in last position in list if the list is not empty,
     * null if the list is empty. The ordering of the list is preserved.
     */
    @Override
    public E removeLast() {
        if (size() == 0) return null;

        Node<E> node = head;
        for (int i = 0; i < size() - 2; i++) {
            node = node.next;
        }
        E obj = node.next.data;
        node.next = null; // Drop the last element
        size--;
        return obj;
    }

    /**
     * Returns the parameter object located at the parameter location position (one based).
     * Throws a RuntimeException if the location does not map to a valid position within the list.
     *
     * @param location
     */
    @Override
    public E get(int location) {
        if (size() == 0) throw new RuntimeException("Empty List");
        if (location > size() || location < 1) throw new RuntimeException("Invalid Index");

        Node<E> node = head;
        for (int i = 0; i < size(); i++) {
            if (i == location - 1) {
                return node.data;
            }

            node = node.next;
        }

        return null;
    }

    /**
     * Returns true if the parameter object obj is in the list, false otherwise.
     *
     * @param obj
     */
    @Override
    public boolean contains(E obj) {
        return locate(obj) != -1;
    }

    /**
     * Returns the one based location of the parameter object obj if it is in the list, -1 otherwise.
     * In the case of duplicates, this method returns the element closest to position #1.
     *
     * @param obj
     */
    @Override
    public int locate(E obj) {
        Node<E> node = head;
        for (int i = 0; i < size(); i++) {
            if (node.data.compareTo(obj) == 0) {
                return i + 1; // Shift for 1-based answer;
            }
            node = node.next;
        }

        return -1;
    }

    /**
     * The list is returned to an empty state.
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Returns true if the list is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of Objects currently in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an Iterator of the values in the list, presented in
     * the same order as the underlying order of the list. (position #1 first)
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            Node<E> node = head;

            public boolean hasNext() {
                return node != null;
            }

            public E next() {
                E obj = node.data;
                node = node.next;
                return obj;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
