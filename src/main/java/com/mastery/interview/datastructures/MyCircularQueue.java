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

    // Add value at the back and return true, or return false if the queue is full.
    // Ajouter value à l'arrière et renvoyer true, ou renvoyer false si la file est pleine.
    // capacity 2: offer(1) -> true, offer(2) -> true, offer(3) -> false (full)
    public boolean offer(int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Remove the front value and return it. head moves forward with %.
    // Retirer la valeur de devant et la renvoyer. head avance avec %.
    // [1, 2].poll() -> 1
    public int poll() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the front value without removing it.
    // Renvoyer la valeur de devant sans la retirer.
    // [1, 2].peek() -> 1, nothing removed
    public int peek() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the number of values.
    // Renvoyer le nombre de valeurs.
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if there is no value.
    // Renvoyer true s'il n'y a aucune valeur.
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if size equals the capacity.
    // Renvoyer true si size vaut la capacité.
    public boolean isFull() {
        throw new UnsupportedOperationException("TODO");
    }
}
