/*  Tom Paulus
    cssc0948
*/


package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO JavaDoc
 *
 * @author Tom Paulus
 *         Created on 3/11/17.
 */
public class UnorderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    private E[] array;
    private int size;

    private long modificationCount;

    public UnorderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public UnorderedArrayPriorityQueue(int size) {
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
     * @param obj
     */
    public boolean insert(E obj) {
        if (isFull())
            return false;

        array[size()] = obj;
        size++;
        modificationCount++;
        return true;
    }

    /**
     * Removes the object of highest priority that has been in the
     * PQ the longest, and returns it.  Returns null if the PQ is empty.
     */
    public E remove() {
        if (isEmpty())
            return null;

        E highest = array[0];
        int index = 0;

        for (int i = 1; i < size(); i++) {
            final E obj = array[i];
            if (highest.compareTo(obj) > 0) {
                index = i;
                highest = obj;
            }
        }

        System.arraycopy(array, index + 1, array, index, size() - 1 - index);
        size--;
        modificationCount++;
        return highest;
    }

    /**
     * Returns the object of highest priority that has been in the
     * PQ the longest, but does NOT remove it.
     * Returns null if the PQ is empty.
     */
    public E peek() {
        if (isEmpty())
            return null;

        E highest = array[0];

        for (int i = 1; i < size(); i++) {
            final E obj = array[i];
            if (highest.compareTo(obj) > 0) {
                highest = obj;
            }
        }

        return highest;
    }

    /**
     * Returns true if the priority queue contains the specified element
     * false otherwise.
     *
     * @param obj
     */
    public boolean contains(E obj) {
        for (int i = 0; i < size(); i++) {
            if (array[i].compareTo(obj) == 0)
                return true;
        }

        return false;
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
     * Returns true if the PQ is full, otherwise false.  List based
     * implementations should always return false.
     */
    public boolean isFull() {
        return size() == array.length;
    }

    /**
     * Returns an iterator of the objects in the PQ, in no particular
     * order.  The iterator must be fail-fast.
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
}
