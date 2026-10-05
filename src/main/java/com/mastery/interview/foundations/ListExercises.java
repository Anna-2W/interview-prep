package com.mastery.interview.foundations;

import java.util.List;

/**
 * Chapter F03: List. Theory / théorie : book/en/foundations/03-list.md
 *
 * <p>Run / lancer : {@code mvn -Dtest=ListExercisesTest test}
 */
public final class ListExercises {

    private ListExercises() {
    }

    /**
     * E01. EN: Return the last element. The list is never empty.
     * FR : Renvoyer le dernier élément. La liste n'est jamais vide.
     *
     * <pre>lastElement([4, 8, 15]) -> 15</pre>
     */
    public static int lastElement(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E02. EN: Return the sum of all elements. Empty list gives 0.
     * FR : Renvoyer la somme de tous les éléments. Une liste vide donne 0.
     *
     * <pre>sum([1, 2, 3]) -> 6     sum([]) -> 0</pre>
     */
    public static int sum(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E03. EN: Return a new list with only the even numbers, in the same order.
     * FR : Renvoyer une nouvelle liste avec seulement les nombres pairs, dans le même ordre.
     *
     * <pre>evens([1, 2, 3, 4]) -> [2, 4]</pre>
     */
    public static List<Integer> evens(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E04. EN: Insert the value at the very beginning of the list (modify the list, return nothing).
     * FR : Insérer la valeur tout au début de la liste (modifier la liste, ne rien renvoyer).
     *
     * <pre>list = ["b", "c"]; addFirst(list, "a"); list -> ["a", "b", "c"]</pre>
     */
    public static void addFirst(List<String> list, String value) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E05. EN: Remove EVERY occurrence of the value from the list (modify the list).
     * Trap: list.remove(1) removes index 1, not the value 1.
     * FR : Supprimer TOUTES les occurrences de la valeur (modifier la liste).
     * Piège : list.remove(1) supprime l'indice 1, pas la valeur 1.
     *
     * <pre>list = [1, 2, 1]; removeValue(list, 1); list -> [2]</pre>
     */
    public static void removeValue(List<Integer> list, int value) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E06. EN: Count the words strictly longer than minLength.
     * FR : Compter les mots strictement plus longs que minLength.
     *
     * <pre>countLongWords(["hi", "hello", "hey"], 3) -> 1</pre>
     */
    public static int countLongWords(List<String> words, int minLength) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E07. EN: Return a NEW list in reverse order. The input may be immutable: do not change it.
     * FR : Renvoyer une NOUVELLE liste dans l'ordre inverse. L'entrée peut être immuable : ne pas la modifier.
     *
     * <pre>reversed([1, 2, 3]) -> [3, 2, 1]</pre>
     */
    public static List<Integer> reversed(List<Integer> list) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E08. EN: Return a new list without duplicates, keeping the first occurrence and the order.
     * FR : Renvoyer une nouvelle liste sans doublons, en gardant la première occurrence et l'ordre.
     *
     * <pre>withoutDuplicates(["a", "b", "a", "c", "b"]) -> ["a", "b", "c"]</pre>
     */
    public static List<String> withoutDuplicates(List<String> list) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E09. EN: Return a new list with all elements of a, then all elements of b.
     * FR : Renvoyer une nouvelle liste avec tous les éléments de a, puis ceux de b.
     *
     * <pre>merge([1, 2], [3]) -> [1, 2, 3]</pre>
     */
    public static List<Integer> merge(List<Integer> a, List<Integer> b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E10. EN: Return a sorted copy (alphabetical order). The input may be immutable: do not change it.
     * FR : Renvoyer une copie triée (ordre alphabétique). L'entrée peut être immuable : ne pas la modifier.
     *
     * <pre>sortedCopy(["c", "a", "b"]) -> ["a", "b", "c"]</pre>
     */
    public static List<String> sortedCopy(List<String> list) {
        throw new UnsupportedOperationException("TODO");
    }
}
