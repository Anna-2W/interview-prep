package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 2
// Empty list on removeFirst / removeLast: throw NoSuchElementException. Bad index: IndexOutOfBoundsException.
public class MyLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    // [b, c].addFirst("a") -> [a, b, c]
    public void addFirst(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b].addLast("c") -> [a, b, c]
    public void addLast(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b].removeFirst() -> returns "a", [b]
    public T removeFirst() {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b].removeLast() -> returns "b", [a]
    public T removeLast() {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b, c].get(2) -> "c"
    public T get(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean contains(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    // [a, b, c].reverse() -> [c, b, a]    in place, no new nodes
    public void reverse() {
        throw new UnsupportedOperationException("TODO");
    }
}
