package com.mastery.interview.foundations;

/**
 * Chapter F02: Array. Theory / théorie : book/en/foundations/02-array.md
 *
 * <p>Write the loops yourself: no streams, no {@code Arrays.sort}, the goal is to train the basics.
 * Écrire les boucles soi-même : pas de streams, pas de {@code Arrays.sort}, le but est d'entraîner les bases.
 *
 * <p>Run / lancer : {@code mvn -Dtest=ArrayExercisesTest test}
 */
public final class ArrayExercises {

    private ArrayExercises() {
    }

    /**
     * E01. EN: Return the sum of all numbers. Empty array gives 0.
     * FR : Renvoyer la somme de tous les nombres. Un tableau vide donne 0.
     *
     * <pre>sum({1, 2, 3}) -> 6     sum({}) -> 0</pre>
     */
    public static int sum(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E02. EN: Return the biggest number. The array is never empty. Numbers can be negative.
     * FR : Renvoyer le plus grand nombre. Le tableau n'est jamais vide. Les nombres peuvent être négatifs.
     *
     * <pre>max({3, 9, 2}) -> 9     max({-5, -2, -8}) -> -2</pre>
     */
    public static int max(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E03. EN: True if the target is in the array.
     * FR : Vrai si la cible est dans le tableau.
     *
     * <pre>contains({1, 2, 3}, 2) -> true     contains({1, 2, 3}, 7) -> false</pre>
     */
    public static boolean contains(int[] nums, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E04. EN: Return the index of the first occurrence of the target, or -1.
     * FR : Renvoyer l'indice de la première occurrence de la cible, ou -1.
     *
     * <pre>indexOf({5, 7, 5}, 5) -> 0     indexOf({5, 7, 5}, 7) -> 1     indexOf({5, 7}, 9) -> -1</pre>
     */
    public static int indexOf(int[] nums, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E05. EN: Count the even numbers. Careful: negative numbers can be even too.
     * FR : Compter les nombres pairs. Attention : les nombres négatifs peuvent aussi être pairs.
     *
     * <pre>countEven({1, 2, 4}) -> 2     countEven({-2, -3}) -> 1</pre>
     */
    public static int countEven(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E06. EN: Return a NEW array where each number is multiplied by 2. Do not change the input.
     * FR : Renvoyer un NOUVEAU tableau où chaque nombre est multiplié par 2. Ne pas modifier l'entrée.
     *
     * <pre>doubled({1, 2}) -> {2, 4}</pre>
     */
    public static int[] doubled(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E07. EN: Return the average as a double. The array is never empty.
     * FR : Renvoyer la moyenne en double. Le tableau n'est jamais vide.
     *
     * <pre>average({1, 2}) -> 1.5     average({4}) -> 4.0</pre>
     */
    public static double average(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E08. EN: Return a NEW array with the elements in reverse order. Do not change the input.
     * FR : Renvoyer un NOUVEAU tableau avec les éléments dans l'ordre inverse. Ne pas modifier l'entrée.
     *
     * <pre>reversed({1, 2, 3}) -> {3, 2, 1}     reversed({}) -> {}</pre>
     */
    public static int[] reversed(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E09. EN: True if each number is smaller than or equal to the next one.
     * FR : Vrai si chaque nombre est inférieur ou égal au suivant.
     *
     * <pre>isSorted({1, 2, 2, 5}) -> true     isSorted({1, 3, 2}) -> false     isSorted({}) -> true</pre>
     */
    public static boolean isSorted(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E10. EN: Return a new array with all elements of a, then all elements of b.
     * FR : Renvoyer un nouveau tableau avec tous les éléments de a, puis tous ceux de b.
     *
     * <pre>concat({1, 2}, {3}) -> {1, 2, 3}     concat({}, {4}) -> {4}</pre>
     */
    public static int[] concat(int[] a, int[] b) {
        throw new UnsupportedOperationException("TODO");
    }
}
