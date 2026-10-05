package com.mastery.interview.foundations;

import java.util.List;

// F03 List: book/en/foundations/03-list.md
public final class ListExercises {

    private ListExercises() {
    }

    // E01  Return the last element. The list is never empty.
    //      Renvoyer le dernier élément. La liste n'est jamais vide.
    //      lastElement([4, 8, 15]) -> 15
    public static int lastElement(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Return the sum of all elements. Empty list gives 0.
    //      Renvoyer la somme de tous les éléments. Une liste vide donne 0.
    //      sum([1, 2, 3]) -> 6    sum([]) -> 0
    public static int sum(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return a new list with only the even numbers, same order.
    //      Renvoyer une nouvelle liste avec seulement les nombres pairs, même ordre.
    //      evens([1, 2, 3, 4]) -> [2, 4]
    public static List<Integer> evens(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Insert value at the very beginning of the list (change the list, return nothing).
    //      Insérer value tout au début de la liste (modifier la liste, ne rien renvoyer).
    //      list = ["b", "c"]; addFirst(list, "a"); list -> ["a", "b", "c"]
    public static void addFirst(List<String> list, String value) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Remove EVERY occurrence of value from the list. Trap: list.remove(1) removes index 1.
    //      Supprimer TOUTES les occurrences de value. Piège : list.remove(1) supprime l'indice 1.
    //      list = [1, 2, 1]; removeValue(list, 1); list -> [2]
    public static void removeValue(List<Integer> list, int value) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Count the words strictly longer than minLength.
    //      Compter les mots strictement plus longs que minLength.
    //      countLongWords(["hi", "hello", "hey"], 3) -> 1
    public static int countLongWords(List<String> words, int minLength) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  Return a NEW list in reverse order. The input may be immutable: do not change it.
    //      Renvoyer une NOUVELLE liste dans l'ordre inverse. L'entrée peut être immuable : ne pas la modifier.
    //      reversed([1, 2, 3]) -> [3, 2, 1]    (input unchanged / entrée intacte)
    public static List<Integer> reversed(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  Return a new list without duplicates, keeping the first occurrence and the order.
    //      Renvoyer une nouvelle liste sans doublons, en gardant la première occurrence et l'ordre.
    //      withoutDuplicates(["a", "b", "a", "c", "b"]) -> ["a", "b", "c"]
    public static List<String> withoutDuplicates(List<String> list) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  Return a new list with all elements of a, then all elements of b.
    //      Renvoyer une nouvelle liste avec tous les éléments de a, puis ceux de b.
    //      merge([1, 2], [3]) -> [1, 2, 3]
    public static List<Integer> merge(List<Integer> a, List<Integer> b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  Return a sorted copy (alphabetical). The input may be immutable: do not change it.
    //      Renvoyer une copie triée (alphabétique). L'entrée peut être immuable : ne pas la modifier.
    //      sortedCopy(["c", "a", "b"]) -> ["a", "b", "c"]    (input unchanged / entrée intacte)
    public static List<String> sortedCopy(List<String> list) {
        throw new UnsupportedOperationException("TODO");
    }
}
