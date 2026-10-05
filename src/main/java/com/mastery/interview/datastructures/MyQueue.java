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

    // Add value at the back. O(1).
    // Ajouter value à l'arrière. O(1).
    // offer(5), offer(8) -> [5, 8]
    public void offer(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Remove the front value and return it, or null if empty. O(1).
    // Retirer la valeur de devant et la renvoyer, ou null si vide. O(1).
    // [5, 8].poll() -> 5, queue is [8]
    public T poll() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the front value without removing it, or null if empty.
    // Renvoyer la valeur de devant sans la retirer, ou null si vide.
    // [5, 8].peek() -> 5, nothing removed
    public T peek() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the number of values.
    // Renvoyer le nombre de valeurs.
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if the queue is empty.
    // Renvoyer true si la file est vide.
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }
}
