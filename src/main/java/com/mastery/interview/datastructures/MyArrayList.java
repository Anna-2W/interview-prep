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

    // Add value at the end. Double the inner array when it is full.
    // Ajouter value à la fin. Doubler le tableau interne quand il est plein.
    // add("a") -> [a]
    public void add(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Insert value at index, shifting the next ones to the right. index can be size.
    // Insérer value à index en décalant les suivants vers la droite. index peut valoir size.
    // [a, c].add(1, "b") -> [a, b, c]    index can be size (add at the end)
    public void add(int index, T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the element at index.
    // Renvoyer l'élément à index.
    // [a, b].get(1) -> "b"
    public T get(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    // Replace the element at index and return the old one.
    // Remplacer l'élément à index et renvoyer l'ancien.
    // [a, b].set(0, "z") -> [z, b], returns "a"
    public T set(int index, T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Remove the element at index, shift the next ones to the left, and return it.
    // Supprimer l'élément à index, décaler les suivants vers la gauche, et le renvoyer.
    // [a, b, c].remove(1) -> [a, c], returns "b"
    public T remove(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the number of elements.
    // Renvoyer le nombre d'éléments.
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if there is no element.
    // Renvoyer true s'il n'y a aucun élément.
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if value is in the list (compare with equals).
    // Renvoyer true si value est dans la liste (comparer avec equals).
    // [a, b].contains("b") -> true
    public boolean contains(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the index of the first value equal to value, or -1.
    // Renvoyer l'indice de la première valeur égale à value, ou -1.
    // [a, b, a].indexOf("a") -> 0    absent -> -1
    public int indexOf(T value) {
        throw new UnsupportedOperationException("TODO");
    }
}
