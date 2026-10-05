package com.mastery.interview.foundations;

// F02 Array: book/en/foundations/02-array.md
public final class ArrayExercises {

    private ArrayExercises() {
    }

    // E01  Return the sum of all numbers. Empty array gives 0.
    //      Renvoyer la somme de tous les nombres. Un tableau vide donne 0.
    //      sum({1, 2, 3}) -> 6    sum({}) -> 0
    public static int sum(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Return the biggest number. The array is never empty, numbers can be negative.
    //      Renvoyer le plus grand nombre. Le tableau n'est jamais vide, les nombres peuvent être négatifs.
    //      max({3, 9, 2}) -> 9    max({-5, -2, -8}) -> -2
    public static int max(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return true if target is in the array.
    //      Renvoyer true si target est dans le tableau.
    //      contains({1, 2, 3}, 2) -> true    contains({1, 2, 3}, 7) -> false
    public static boolean contains(int[] nums, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return the index of the first target, or -1 if absent.
    //      Renvoyer l'indice du premier target, ou -1 s'il est absent.
    //      indexOf({5, 7, 5}, 5) -> 0    indexOf({5, 7}, 9) -> -1
    public static int indexOf(int[] nums, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Count the even numbers (negative ones too).
    //      Compter les nombres pairs (négatifs compris).
    //      countEven({1, 2, 4}) -> 2    countEven({-2, -3}) -> 1
    public static int countEven(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Return a NEW array where each number is multiplied by 2. Do not change the input.
    //      Renvoyer un NOUVEAU tableau où chaque nombre est multiplié par 2. Ne pas modifier l'entrée.
    //      doubled({1, 2}) -> {2, 4}    (input unchanged / entrée intacte)
    public static int[] doubled(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  Return the average as a double. The array is never empty.
    //      Renvoyer la moyenne en double. Le tableau n'est jamais vide.
    //      average({1, 2}) -> 1.5
    public static double average(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  Return a NEW array with the elements in reverse order. Do not change the input.
    //      Renvoyer un NOUVEAU tableau avec les éléments dans l'ordre inverse. Ne pas modifier l'entrée.
    //      reversed({1, 2, 3}) -> {3, 2, 1}    (input unchanged / entrée intacte)
    public static int[] reversed(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  Return true if each number is <= the next one.
    //      Renvoyer true si chaque nombre est <= au suivant.
    //      isSorted({1, 2, 2, 5}) -> true    isSorted({1, 3, 2}) -> false
    public static boolean isSorted(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  Return a new array with all elements of a, then all elements of b.
    //      Renvoyer un nouveau tableau avec tous les éléments de a, puis tous ceux de b.
    //      concat({1, 2}, {3}) -> {1, 2, 3}
    public static int[] concat(int[] a, int[] b) {
        throw new UnsupportedOperationException("TODO");
    }
}
