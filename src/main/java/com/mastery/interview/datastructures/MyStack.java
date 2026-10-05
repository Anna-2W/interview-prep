package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 3
// Array-based. Empty stack on pop / peek: throw NoSuchElementException. Grow by doubling when full.
public class MyStack {

    private int[] data = new int[2];
    private int size;

    // push(5), push(8) -> top is 8
    public void push(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // [5, 8].pop() -> 8, stack is [5]
    public int pop() {
        throw new UnsupportedOperationException("TODO");
    }

    // [5, 8].peek() -> 8, nothing removed
    public int peek() {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }
}
