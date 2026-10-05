package com.mastery.interview.complexity;

import java.util.List;

// 01 Complexity, part B: book/en/01-complexity.md
// Each xxxSlow method works but is too slow. Write the fast version below it.
public final class FasterExercises {

    private FasterExercises() {
    }

    // E01  Return true if a value appears twice. Write hasDuplicate below, faster than hasDuplicateSlow.
    //      Renvoyer true si une valeur apparaît deux fois. Écrire hasDuplicate en dessous, plus rapide que hasDuplicateSlow.
    //      hasDuplicate({1, 2, 3, 1}) -> true    O(n^2) -> O(n)
    public static boolean hasDuplicateSlow(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasDuplicate(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Count the values of a that are also in b. Write countCommon below, faster.
    //      Compter les valeurs de a qui sont aussi dans b. Écrire countCommon en dessous, plus rapide.
    //      countCommon({1, 2, 3, 4}, {2, 4, 6}) -> 2    O(n*m) -> O(n + m)
    public static int countCommonSlow(int[] a, int[] b) {
        int count = 0;
        for (int x : a) {
            for (int y : b) {
                if (x == y) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static int countCommon(int[] a, int[] b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Join the words with ",". Write joinWords below, faster.
    //      Joindre les mots avec ",". Écrire joinWords en dessous, plus rapide.
    //      joinWords(["a", "b", "c"]) -> "a,b,c"    O(n^2) -> O(n)
    public static String joinWordsSlow(List<String> words) {
        String result = "";
        for (int i = 0; i < words.size(); i++) {
            if (i > 0) {
                result = result + ",";
            }
            result = result + words.get(i);
        }
        return result;
    }

    public static String joinWords(List<String> words) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return the biggest sum of k numbers side by side. Write maxWindowSum below, faster.
    //      Renvoyer la plus grande somme de k nombres côte à côte. Écrire maxWindowSum en dessous, plus rapide.
    //      maxWindowSum({1, 4, 2, 10, 2, 3}, 3) -> 16    O(n*k) -> O(n)
    public static long maxWindowSumSlow(int[] nums, int k) {
        long best = Long.MIN_VALUE;
        for (int start = 0; start + k <= nums.length; start++) {
            long sum = 0;
            for (int i = start; i < start + k; i++) {
                sum += nums[i];
            }
            best = Math.max(best, sum);
        }
        return best;
    }

    public static long maxWindowSum(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  For each query {from, to}, return the sum of nums[from..to] (to included). Write rangeSums below, faster.
    //      Pour chaque requête {from, to}, renvoyer la somme de nums[from..to] (to inclus). Écrire rangeSums en dessous, plus rapide.
    //      rangeSums({1, 2, 3, 4}, {{0, 1}, {1, 3}}) -> {3, 9}    each query {from, to} is inclusive    O(n*q) -> O(n + q)
    public static long[] rangeSumsSlow(int[] nums, int[][] queries) {
        long[] answers = new long[queries.length];
        for (int q = 0; q < queries.length; q++) {
            for (int i = queries[q][0]; i <= queries[q][1]; i++) {
                answers[q] += nums[i];
            }
        }
        return answers;
    }

    public static long[] rangeSums(int[] nums, int[][] queries) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Return the n-th Fibonacci number (0, 1, 1, 2, 3, 5...). Write fib below, faster.
    //      Renvoyer le n-ième nombre de Fibonacci (0, 1, 1, 2, 3, 5...). Écrire fib en dessous, plus rapide.
    //      fib(10) -> 55    fib(0) -> 0    fib(1) -> 1    O(2^n) -> O(n)
    public static long fibSlow(int n) {
        if (n < 2) {
            return n;
        }
        return fibSlow(n - 1) + fibSlow(n - 2);
    }

    public static long fib(int n) {
        throw new UnsupportedOperationException("TODO");
    }
}
