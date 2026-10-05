package com.mastery.interview.patterns.prefixsum;

// 03 Patterns, section 4 Prefix sums: book/en/03-patterns.md
public final class PrefixSumExercises {

    private PrefixSumExercises() {
    }

    // E01  Return a new array where box i holds the sum of nums[0] to nums[i].
    //      Renvoyer un nouveau tableau où la case i contient la somme de nums[0] à nums[i].
    //      runningSum({1, 2, 3, 4}) -> {1, 3, 6, 10}
    public static int[] runningSum(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Return the first index where the sum of the numbers on its left equals the sum on its right (the number itself is not counted), or -1.
    //      Renvoyer le premier indice où la somme des nombres à sa gauche égale celle à sa droite (le nombre lui-même ne compte pas), ou -1.
    //      pivotIndex({1, 7, 3, 6, 5, 6}) -> 3    (1+7+3 = 5+6 = 11)
    public static int pivotIndex(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Count the contiguous pieces of nums whose sum is exactly k. Numbers can be negative.
    //      Compter les morceaux contigus de nums dont la somme vaut exactement k. Les nombres peuvent être négatifs.
    //      countSubarraysWithSum({1, 1, 1}, 2) -> 2    ({1, -1, 0}, 0) -> 3
    public static int countSubarraysWithSum(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }
}
