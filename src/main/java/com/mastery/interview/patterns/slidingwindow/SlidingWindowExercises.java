package com.mastery.interview.patterns.slidingwindow;

// 03 Patterns, section 3 Sliding window: book/en/03-patterns.md
public final class SlidingWindowExercises {

    private SlidingWindowExercises() {
    }

    // E01  Return the length of the longest piece of s (contiguous) where no character repeats.
    //      Renvoyer la longueur du plus long morceau de s (contigu) où aucun caractère ne se répète.
    //      longestUniqueSubstring("abcabcbb") -> 3 ("abc")    ("pwwkew") -> 3 ("wke")    ("") -> 0
    public static int longestUniqueSubstring(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  All numbers are positive. Return the length of the shortest contiguous piece whose sum is >= target, or 0 if none.
    //      Tous les nombres sont positifs. Renvoyer la longueur du plus court morceau contigu dont la somme est >= target, ou 0 s'il n'y en a pas.
    //      minSubarrayLength(7, {2, 3, 1, 2, 4, 3}) -> 2    ({4, 3})
    public static int minSubarrayLength(int target, int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  The array has only 0 and 1. You may turn at most k zeros into ones. Return the longest run of ones you can get.
    //      Le tableau ne contient que des 0 et des 1. On peut changer au plus k zéros en uns. Renvoyer la plus longue suite de 1 qu'on peut obtenir.
    //      longestOnes({1, 1, 0, 0, 1, 1, 1, 0, 1}, 1) -> 5
    public static int longestOnes(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return true if text contains a contiguous piece made of exactly the letters of pattern, in any order.
    //      Renvoyer true si text contient un morceau contigu fait exactement des lettres de pattern, dans n'importe quel ordre.
    //      containsPermutation("ab", "eidbaooo") -> true ("ba")    ("ab", "eidboaoo") -> false
    public static boolean containsPermutation(String pattern, String text) {
        throw new UnsupportedOperationException("TODO");
    }
}
