/*  Tom Paulus
    cssc0948
*/

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Unordered Linked List
 *
 * @author Tom Paulus
 *         Created on 2/15/17.
 */
public class UnorderedList<E extends Comparable<E>> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private long modificationCount = 0;

    public UnorderedList() {
        size = 0;
        head = tail = null;
    }

    /**
     * Add an element to the Head of the List
     *
     * @param obj {@link E} object to add
     */
    public void addFirst(E obj) {
        Node<E> newNode = new Node<>(obj);
        newNode.next = head;
        head = newNode;
        if (isEmpty()) tail = newNode;
        size++;
        modificationCount++;
    }

    /**
     * Add an element to the Tail of the List
     *
     * @param obj {@link E} object to add
     */
    public void addLast(E obj) {
        Node<E> newNode = new Node<>(obj);
        if (isEmpty()) head = tail = newNode;
        else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
        modificationCount++;
    }

    /**
     * Remove the first element in the list
     *
     * @return {@link E} First object in the list
     */
    public E removeFirst() {
        E value = head.data;
        head = head.next;
        size--;
        modificationCount++;
        return value;
    }

    /**
     * Remove the last element in the list
     *
     * @return {@link E} The Last object in the list
     */
    public E removeLast() {
        Node<E> secondLast = head;
        while (secondLast.next.next != null)
            secondLast = secondLast.next;
        E value = secondLast.next.data;
        secondLast.next = null;
        size--;
        modificationCount++;
        return value;
    }

    /**
     * Removes the specified element from the list
     *
     * @param obj {@link E} Element to Remove
     * @return {@link E} Removed Item
     */
    public E remove(E obj) {
        if (isEmpty()) return null;

        if (head.data.compareTo(obj) == 0) {
            E value = head.data;
            head = head.next;
            size--;
            modificationCount++;
            return value;
        }

        Node<E> previous = head;
        while (previous.next != null && previous.next.data.compareTo(obj) != 0)
            previous = previous.next;

        if (previous.next != null && previous.next.data.compareTo(obj) == 0) {
            // This would be false if our step loop ended because it was at the end of the list
            E value = previous.next.data;
            previous.next = previous.next.next;
            size--;
            modificationCount++;
            return value;
        }

        return null;
    }

    /**
     * Find a specified element in the list.
     * Returns Null if the element is not in the list
     *
     * @param obj {@link E} Element to Find
     * @return {@link E} Element from List
     */
    public E find(E obj) {
        Node<E> node = head;
        while (node.next != null && node.data.compareTo(obj) != 0)
            node = node.next;
        return obj.compareTo(node.data) == 0 ? node.data : null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        size = 0;
        head = tail = null;
        modificationCount++;
    }


    /**
     * Returns an Iterator of the values in the list, presented in
     * the same order as the underlying order of the list. (position #1 first)
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;
            long modCount = modificationCount;

            public boolean hasNext() {
                if (modCount != modificationCount) throw new ConcurrentModificationException(
                        "Structure Modified after Iterator Creation"
                );
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
