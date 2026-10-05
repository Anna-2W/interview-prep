package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 5
// Fixed capacity, indexes wrap with %. Empty queue on poll / peek: throw NoSuchElementException.
public class MyCircularQueue {

    private final int[] data;
    private int head;
    private int size;

    public MyCircularQueue(int capacity) {
        data = new int[capacity];
    }

    // capacity 2: offer(1) -> true, offer(2) -> true, offer(3) -> false (full)
    public boolean offer(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [1, 2].poll() -> 1
    public int poll() {
        throw new UnsupportedOperationException("TODO");
    }

    // [1, 2].peek() -> 1, nothing removed
    public int peek() {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isFull() {
        throw new UnsupportedOperationException("TODO");
    }
}
