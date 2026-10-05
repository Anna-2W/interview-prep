package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 1
// Bad index: throw IndexOutOfBoundsException. Grow by doubling when full.
public class MyArrayList<T> {

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (T[]) new Object[2];
        size = 0;
    }

    // add("a") -> [a]
    public void add(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, c].add(1, "b") -> [a, b, c]    index can be size (add at the end)
    public void add(int index, T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b].get(1) -> "b"
    public T get(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b].set(0, "z") -> [z, b], returns "a"
    public T set(int index, T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b, c].remove(1) -> [a, c], returns "b"
    public T remove(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b].contains("b") -> true
    public boolean contains(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b, a].indexOf("a") -> 0    absent -> -1
    public int indexOf(T value) {
        throw new UnsupportedOperationException("TODO");
    }
}
