package com.company;

import java.util.*;
import java.util.function.Consumer;

public class DynamicArray<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ARRAY = {};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private int capacity = 16;
    private int size = 0;
    private Object[] array;

    public DynamicArray() {
        array = new Object[capacity];
    }

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public boolean add(T o) {
        if (size == capacity) {
            capacity = capacity * 2;
            array = Arrays.copyOf(array, capacity);
        }
        array[size] = o;
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return remove(indexOf(o)) != null;
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, array, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        rangeCheckForAdd(index); //Почему нельзя сначала попробовать увеличить, а потом проверять уже возможность добавления

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(array, index, array, index + numNew, numMoved);

        System.arraycopy(a, 0, array, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            array[i] = null;

        size = 0;
    }

    @Override
    public T get(int index) {
        return elementData(index);
    }

    @Override
    public T set(int index, Object element) {
        array[index] = element;
        return elementData(index);
    }

    @Override
    public void add(int index, Object element) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(array, index, array, index + 1,
                size - index);
        array[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        //Не совсем понял каким образом в дефолтной реализации работает этот метод?
        T oldValue = elementData(index);
        final int newSize;
        if (index >= size) {
            return null;
        }
        if ((newSize = size - 1) > index)
            System.arraycopy(array, index + 1, array, index, newSize - index);
        return oldValue;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (array[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    T elementData(int index) {
        return (T) array[index];
    }

    private static int calculateCapacity(Object[] array, int minCapacity) {
        if (array == DEFAULT_CAPACITY_EMPTY_ARRAY) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(array, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - array.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        array = Arrays.copyOf(array, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private class MyIterator implements Iterator<T> {
        private int current;
        private int prev = -1;

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public T next() {
            int tempCurrent = current;
            if (tempCurrent >= size) {
                throw new NoSuchElementException();
            }
            current= tempCurrent +1;
            prev = tempCurrent;
            return elementData(prev);
        }

        @Override
        public void remove() {
            if (prev < 0) {
                throw new IndexOutOfBoundsException();
            }
            DynamicArray.this.remove(prev);
            current = prev;
            prev--; //Вот тут не уловил почему должно быть -1
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }
    }
}
