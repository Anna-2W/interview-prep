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

    // Add value before the first node. O(1).
    // Ajouter value avant le premier nœud. O(1).
    // [b, c].addFirst("a") -> [a, b, c]
    public void addFirst(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Add value after the last node, using tail. O(1).
    // Ajouter value après le dernier nœud, grâce à tail. O(1).
    // [a, b].addLast("c") -> [a, b, c]
    public void addLast(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Remove the first node and return its value.
    // Supprimer le premier nœud et renvoyer sa valeur.
    // [a, b].removeFirst() -> returns "a", [b]
    public T removeFirst() {
        throw new UnsupportedOperationException("TODO");
    }

    // Remove the last node and return its value (walk to the node before tail).
    // Supprimer le dernier nœud et renvoyer sa valeur (marcher jusqu'au nœud avant tail).
    // [a, b].removeLast() -> returns "b", [a]
    public T removeLast() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the value at index, walking from head.
    // Renvoyer la valeur à index, en marchant depuis head.
    // [a, b, c].get(2) -> "c"
    public T get(int index) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if a node holds value (compare with equals).
    // Renvoyer true si un nœud contient value (comparer avec equals).
    public boolean contains(T value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the number of nodes.
    // Renvoyer le nombre de nœuds.
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if the list has no node.
    // Renvoyer true si la liste n'a aucun nœud.
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }

    // Reverse the order of the nodes in place (no new node). Update head and tail.
    // Inverser l'ordre des nœuds sur place (pas de nouveau nœud). Mettre à jour head et tail.
    // [a, b, c].reverse() -> [c, b, a]    in place, no new nodes
    public void reverse() {
        throw new UnsupportedOperationException("TODO");
    }
}
