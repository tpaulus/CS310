/*  Tom Paulus
    cssc0948
*/

package data_structures;

import java.util.Iterator;

/**
 * An Linked-List Based Priority Queue.
 * In this implementation, the Linked-List which stores the entries is unsorted, and the correct element
 * is found at retrieval time (remove/peek).
 *
 * @author Tom Paulus
 *         Created on 2/15/17.
 */
public class UnorderedListPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    private UnorderedList<E> pq = new UnorderedList<>();

    /**
     * Inserts a new object into the priority queue.  Returns true if
     * the insertion is successful.  If the PQ is full, the insertion
     * is aborted, and the method returns false.
     *
     * @param obj Element to Insert
     */
    public boolean insert(E obj) {
        if (isFull())
            return false;

        pq.addLast(obj);
        return true;
    }

    /**
     * Removes the object of highest priority that has been in the
     * PQ the longest, and returns it.  Returns null if the PQ is empty.
     */
    public E remove() {
        if (isEmpty())
            return null;
        return pq.remove();
    }

    /**
     * Returns the object of highest priority that has been in the
     * PQ the longest, but does NOT remove it.
     * Returns null if the PQ is empty.
     */
    public E peek() {
        E largest = null;

        for (E obj : pq) {
            if (largest == null || obj.compareTo(largest) < 0)
                largest = obj;
        }

        return largest;
    }

    /**
     * Returns true if the priority queue contains the specified element
     * false otherwise.
     *
     * @param obj
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
