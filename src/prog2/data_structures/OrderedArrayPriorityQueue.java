/*  Tom Paulus
    cssc0948
*/

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An Array Based Priority Queue.
 * In this implementation, the Array which stores the entries is sorted, and the element is stored
 * at the correct location at insertion.
 *
 * @author Tom Paulus
 *         Created on 2/13/17.
 */
public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    private E[] array;
    private int size;

    private long modificationCount;

    public OrderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public OrderedArrayPriorityQueue(int size) {
        //noinspection unchecked
        this.array = (E[]) new Comparable[size];
        this.size = 0;
        modificationCount = 0;
    }

    /**
     * Inserts a new object into the priority queue.  Returns true if
     * the insertion is successful.  If the PQ is full, the insertion
     * is aborted, and the method returns false.
     *
     * @param obj Element to insert
     */
    public boolean insert(E obj) {
        if (isFull())
            return false;

        int insertionPoint = 0;
        if (!isEmpty())
            insertionPoint = findInsertionPoint(obj, 0, size - 1);
        System.arraycopy(array, insertionPoint, array, insertionPoint + 1, size() - insertionPoint);
        array[insertionPoint] = obj;
        size++;
        modificationCount++;
        return true;
    }

    /**
     * Removes the object of highest priority that has been in the
     * PQ the longest, and returns it. Returns null if the PQ is empty.
     */
    public E remove() {
        if (isEmpty())
            return null;
        modificationCount++;
        return array[--size];
    }

    /**
     * Returns the object of highest priority that has been in the
     * PQ the longest, but does NOT remove it.
     * Returns null if the PQ is empty.
     */
    public E peek() {
        if (isEmpty())
            return null;
        return array[size - 1];
    }

    /**
     * Returns true if the priority queue contains the specified element
     * false otherwise.
     *
     * @param obj Element to look for
     */
    public boolean contains(E obj) {
        return find(obj, 0, size() - 1) != null;
    }

    /**
     * Returns the number of objects currently in the PQ.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the PQ to an empty state.
     */
    public void clear() {
        size = 0;
        modificationCount++;
    }

    /**
     * Returns true if the PQ is empty, otherwise false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if the PQ is full, otherwise false. List based
     * implementations should always return false.
     */
    public boolean isFull() {
        return size() == array.length;
    }

    /**
     * Returns an iterator of the objects in the PQ, in no particular
     * order.  The iterator is fail-fast, and will throw a ConcurrentModificationException
     * if the underlying array is modified after the iterator has been constructed.
     *
     * @throws ConcurrentModificationException Structure modified after iterator construction
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            long modCount = modificationCount;

            public boolean hasNext() {
                if (modCount != modificationCount) throw new ConcurrentModificationException(
                        "Structure Modified after Iterator Creation"
                );
                return this.index < size();
            }

            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return array[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private int findInsertionPoint(E obj, int low, int high) {
        if (high < low)
            return low;

        int mid = (low + high) >> 1;
        if (obj.compareTo(array[mid]) >= 0)
            return findInsertionPoint(obj, low, mid - 1); // Bin Search Left
        return findInsertionPoint(obj, mid + 1, high); // Bin Search Right
    }

    private E find(E obj, int low, int high) {
        if (high < low) {
            E value = array[low];
            if (obj.compareTo(value) != 0)
                return null;
            return value;
        }

        int mid = (low + high) >> 1;
        if (array[mid].compareTo(obj) < 0) // Bin Search Left
            return find(obj, low, mid - 1);
        return find(obj, mid + 1, high); // Bin Search Right
    }
}
