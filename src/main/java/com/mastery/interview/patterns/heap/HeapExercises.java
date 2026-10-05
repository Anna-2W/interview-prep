package com.mastery.interview.patterns.heap;

import java.util.List;

// 03 Patterns, section 9 Heap: book/en/03-patterns.md
public final class HeapExercises {

    private HeapExercises() {
    }

    // E01  Return the k values that appear the most, the most frequent first.
    //      Renvoyer les k valeurs qui apparaissent le plus, la plus fréquente d'abord.
    //      topKFrequent({1, 1, 1, 2, 2, 3}, 2) -> [1, 2]
    public static List<Integer> topKFrequent(int[] nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Each point is {x, y}. Return the k points closest to (0, 0), in any order.
    //      Chaque point est {x, y}. Renvoyer les k points les plus proches de (0, 0), dans n'importe quel ordre.
    //      kClosest({{1, 3}, {-2, 2}, {5, 8}}, 1) -> {{-2, 2}}
    public static int[][] kClosest(int[][] points, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Each array is sorted. Return one sorted array with all their values.
    //      Chaque tableau est trié. Renvoyer un seul tableau trié avec toutes leurs valeurs.
    //      mergeKSorted([{1, 4, 5}, {1, 3, 4}, {2, 6}]) -> {1, 1, 2, 3, 4, 4, 5, 6}
    public static int[] mergeKSorted(List<int[]> lists) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Read the numbers one by one. After each one, write the median of the numbers read so far (even count: average of the two middle ones).
    //      Lire les nombres un par un. Après chacun, écrire la médiane des nombres déjà lus (nombre pair : moyenne des deux du milieu).
    //      runningMedians({5, 15, 1, 3}) -> {5.0, 10.0, 5.0, 4.0}
    public static double[] runningMedians(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }
}
