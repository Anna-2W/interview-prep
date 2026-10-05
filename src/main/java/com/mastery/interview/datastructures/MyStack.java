package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 3
// Array-based. Empty stack on pop / peek: throw NoSuchElementException. Grow by doubling when full.
public class MyStack {

    private int[] data = new int[2];
    private int size;

    // Put value on top. Double the array when it is full.
    // Poser value au sommet. Doubler le tableau quand il est plein.
    // push(5), push(8) -> top is 8
    public void push(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Remove the top value and return it.
    // Retirer la valeur du sommet et la renvoyer.
    // [5, 8].pop() -> 8, stack is [5]
    public int pop() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the top value without removing it.
    // Renvoyer la valeur du sommet sans la retirer.
    // [5, 8].peek() -> 8, nothing removed
    public int peek() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the number of values.
    // Renvoyer le nombre de valeurs.
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if the stack is empty.
    // Renvoyer true si la pile est vide.
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }
}
