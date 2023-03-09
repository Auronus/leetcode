package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DynamicArray<T> implements List {
    //ArrayList
    private int size = 16;
    private int actualSize = 0;
    private Object[] array;

    public DynamicArray() {
        array = new Object[size];
    }

    public DynamicArray(int size) {
        this.size = size;
        array = new Object[size];
    }

    @Override
    public boolean add(Object o) {
        if (actualSize == size) {
            size = size * 2;
            array = Arrays.copyOf(array,size);
        }
        array[actualSize] = o;
        actualSize++;
        return true;
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize==0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        //Не совсем понял каким образом в дефолтной реализации работает этот метод?
        Object oldValue = array[index];
        final int newSize;
        if (index >= actualSize) {
            return null;
        }
        if ((newSize = actualSize - 1) > index)
            System.arraycopy(array, index + 1, array, index, newSize - index);
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
    public List subList(int fromIndex, int toIndex) {
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
}
