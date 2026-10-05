package com.mastery.interview.patterns.binarysearch;

// 03 Patterns, section 6 Binary search: book/en/03-patterns.md
public final class BinarySearchExercises {

    private BinarySearchExercises() {
    }

    // E01  The array is sorted. Return the index of target, or -1. O(log n).
    //      Le tableau est trié. Renvoyer l'indice de target, ou -1. O(log n).
    //      search({-1, 0, 3, 5, 9, 12}, 9) -> 4
    public static int search(int[] sorted, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  The array is sorted. Return the index of target if present, otherwise the index where it should be inserted to keep the order.
    //      Le tableau est trié. Renvoyer l'indice de target s'il est présent, sinon l'indice où l'insérer pour garder l'ordre.
    //      searchInsert({1, 3, 5, 6}, 5) -> 2    (same, 2) -> 1    (same, 7) -> 4
    public static int searchInsert(int[] sorted, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return the square root of x rounded down, without Math.sqrt. Careful: mid * mid can overflow an int.
    //      Renvoyer la racine carrée de x arrondie vers le bas, sans Math.sqrt. Attention : mid * mid peut dépasser un int.
    //      sqrt(8) -> 2    sqrt(16) -> 4
    public static int sqrt(int x) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  A sorted array of distinct values was rotated (cut in two, halves swapped). Return the index of target, or -1. O(log n).
    //      Un tableau trié de valeurs distinctes a été tourné (coupé en deux, moitiés échangées). Renvoyer l'indice de target, ou -1. O(log n).
    //      searchRotated({4, 5, 6, 7, 0, 1, 2}, 0) -> 4
    public static int searchRotated(int[] nums, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Each hour you eat up to "speed" bananas from one pile. Return the smallest speed that finishes every pile within the hours.
    //      Chaque heure on mange jusqu'à « speed » bananes dans un seul tas. Renvoyer la plus petite vitesse qui finit tous les tas dans le temps donné.
    //      minEatingSpeed({3, 6, 7, 11}, 8) -> 4
    public static int minEatingSpeed(int[] piles, int hours) {
        throw new UnsupportedOperationException("TODO");
    }
}
