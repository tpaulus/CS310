/*  Tom Paulus
    cssc0948
*/


package data_structures;

import java.util.Iterator;

/**
 * An Linked-List Based Priority Queue.
 * In this implementation, the Linked-List which stores the entries is sorted, and the element is
 * stored in the correct order, in relation to its peers, at insertion.
 *
 * @author Tom Paulus
 *         Created on 3/11/17.
 */
public class OrderedListPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    private OrderedList<E> pq = new OrderedList<>();

    /**
     * Inserts a new object into the priority queue.  Returns true if
     * the insertion is successful.  If the PQ is full, the insertion
     * is aborted, and the method returns false.
     *
     * @param obj Element to insert
     */
    public boolean insert(E obj) {
        if (isFull()) return false;
        pq.add(obj);
        return true;
    }

    /**
     * Removes the object of highest priority that has been in the
     * PQ the longest, and returns it.  Returns null if the PQ is empty.
     */
    public E remove() {
        return pq.remove();
    }

    /**
     * Returns the object of highest priority that has been in the
     * PQ the longest, but does NOT remove it.
     * Returns null if the PQ is empty.
     */
    public E peek() {
        for (E obj : pq) {
            return obj;
        }
        return null;
    }

    /**
     * Returns true if the priority queue contains the specified element
     * false otherwise.
     *
     * @param obj Element to look for
     */
    public boolean contains(E obj) {
        return pq.find(obj) != null;
    }

    /**
     * Returns the number of objects currently in the PQ.
     */
    public int size() {
        return pq.size();
    }

    /**
     * Returns the PQ to an empty state.
     */
    public void clear() {
        pq.clear();
    }

    /**
     * Returns true if the PQ is empty, otherwise false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if the PQ is full, otherwise false.  List based
     * implementations should always return false.
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Returns an iterator of the objects in the PQ, in no particular
     * order. The iterator is fail-fast, and will throw a ConcurrentModificationException
     * if the underlying data is modified after the iterator has been constructed.
     */
    public Iterator<E> iterator() {
        return pq.iterator();
    }
}
