/*  Tom Paulus
    cssc0948
*/

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * TODO JavaDoc
 *
 * @author Tom Paulus
 *         Created on 3/11/17.
 */
public class OrderedList<E extends Comparable<E>> implements Iterable<E> {
    private Node<E> head;
    private int size;

    private long modificationCount = 0;

    public OrderedList() {
        size = 0;
        head = null;
    }

    public void add(E obj) {
        Node<E> newNode = new Node<>(obj);
        Node<E> previous = null;
        Node<E> current = head;

        while (current != null && current.data.compareTo(obj) <= 0) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            // New node goes at the head
            newNode.next = head;
            head = newNode;
        } else {
            // Node goes in the middle or at the end of the LL
            previous.next = newNode;
            newNode.next = current;
        }

        size++;
        modificationCount++;
    }

    public E remove() {
        if (head == null) return null;

        E data = head.data;
        head = head.next;
        size--;
        modificationCount++;
        return data;
    }

    public E find(E obj) {
        Node<E> node = head;
        while (node.next != null && node.data.compareTo(obj) < 0)
            node = node.next;

        if (node.next == null && node.data.compareTo(obj) < 0)
            return null;
        return node.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        head = null;
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
