/*  Tom Paulus
    cssc0285
*/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO JavaDoc
 * TODO Add Messages to Runtime Exceptions
 *
 * @author Tom Paulus
 *         Created on 1/19/17.
 */
public class ArrayLinearList<E> implements LinearListADT<E> {
    private E[] array;
    private int size;

    public ArrayLinearList() {
        //noinspection unchecked
        array = (E[]) new Object[DEFAULT_MAX_CAPACITY];
        size = 0;
    }

    @Override
    public void addLast(E obj) {
        insert(obj, size());
    }

    @Override
    public void addFirst(E obj) {
        insert(obj, 1);
    }

    @Override
    public void insert(E obj, int location) throws RuntimeException {
        if (location > size() + 1) throw new RuntimeException();
        if (location == size()) array = growArray(array); // Double the array size as the current has been filled
        // Move the elements down as necessary if the insert is not at the end.
        if (!(location == size)) {
            // The element is being inserted in between elements, we need to move things around
            System.arraycopy(array, location, array, location + 1, size() - location);
        }
        array[location] = obj;
        size++;
    }

    @Override
    public E remove(int location) {
        // Decrement Location
        if (0 > location || location >= size()) throw new RuntimeException();
        E element = array[location - 1];
        // TODO Move elements to close the gap
        if (((float) size()) / array.length < .25) array = shrinkArray(array); // Shrink the array since is is 25% full.
        size--;
        return element;
    }

    @Override
    public E remove(E obj) {
        int location = locate(obj);
        if (location > 0) {
            remove(location - 1);
            //noinspection unchecked
            return array[location];
        }
        return null;
    }

    @Override
    public E removeFirst() {
        return remove(1);
    }

    @Override
    public E removeLast() {
        return remove(size());
    }

    @Override
    public E get(int location) throws RuntimeException {
        if (location > size()) throw new RuntimeException();
        //noinspection unchecked
        return array[location - 1];
    }

    @Override
    public boolean contains(E obj) {
        return locate(obj) > -1;
    }

    @Override
    public int locate(E obj) {
        for (int i = 0; i < size(); i++) {
            //noinspection unchecked
            if (((Comparable<E>) obj).compareTo(array[i]) == 0) return i + 1;
        }
        return -1;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ALLIterator<>(array);
    }

    private E[] growArray(E[] org) {
        //noinspection unchecked
        E[] newArr = (E[]) new Object[org.length * 2];
        System.arraycopy(org, 0, newArr, 0, org.length);
        return newArr;
    }

    private E[] shrinkArray(E[] org) {
        //noinspection unchecked
        E[] newArr = (E[]) new Object[(int) Math.ceil(org.length / 2)];
        System.arraycopy(org, 0, newArr, 0, size());
        return newArr;
    }

    private class ALLIterator implements Iterator<E> {
        int index;

        ALLIterator() {
            this.index = 1;
        }

        @Override
        public boolean hasNext() {
            return this.index < size();
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
