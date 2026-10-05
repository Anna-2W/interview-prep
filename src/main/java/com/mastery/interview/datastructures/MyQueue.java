package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 4
// Linked-list based, every method O(1). Empty queue: poll and peek return null.
public class MyQueue<T> {

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

    // offer(5), offer(8) -> [5, 8]
    public void offer(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [5, 8].poll() -> 5, queue is [8]
    public T poll() {
        throw new UnsupportedOperationException("TODO");
    }

    // [5, 8].peek() -> 5, nothing removed
    public T peek() {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }
}
