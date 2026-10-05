package com.mastery.interview.complexity;

import java.util.Arrays;
import java.util.List;

// 01 Complexity, part A: book/en/01-complexity.md
// Read each snippet, then return its time complexity: "O(1)", "O(log n)", "O(n)", "O(n log n)", "O(n^2)", "O(n*m)", "O(2^n)"
public final class ComplexityQuiz {

    private ComplexityQuiz() {
    }

    static int q01Code(int[] a) {
        int total = 0;
        for (int x : a) {
            total += x;
        }
        return total;
    }

    // Q01  Return the time complexity of q01Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q01Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q01() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q02Code(int[] a) {
        return a[0];
    }

    // Q02  Return the time complexity of q02Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q02Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q02() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q03Code(int[] a) {
        int pairs = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i] + a[j] == 0) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    // Q03  Return the time complexity of q03Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q03Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q03() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q04Code(int n) {
        int steps = 0;
        for (int i = 1; i < n; i = i * 2) {
            steps++;
        }
        return steps;
    }

    // Q04  Return the time complexity of q04Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q04Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q04() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q05Code(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int x : a) {
            min = Math.min(min, x);
        }
        int max = Integer.MIN_VALUE;
        for (int x : a) {
            max = Math.max(max, x);
        }
        return max - min;
    }

    // Q05  Return the time complexity of q05Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q05Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q05() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q06Code(int[] a, int[] b) {
        int same = 0;
        for (int x : b) {
            for (int y : a) {
                if (x == y) {
                    same++;
                }
            }
        }
        return same;
    }

    // Q06  Return the time complexity of q06Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q06Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q06() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q07Code(String s) {
        int found = 0;
        for (char c : s.toCharArray()) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                if (c == letter) {
                    found++;
                }
            }
        }
        return found;
    }

    // Q07  Return the time complexity of q07Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q07Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q07() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q08Code(int[] a) {
        Arrays.sort(a);
        int gaps = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1]) {
                gaps++;
            }
        }
        return gaps;
    }

    // Q08  Return the time complexity of q08Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q08Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q08() {
        throw new UnsupportedOperationException("TODO");
    }

    static int q09Code(List<Integer> list, int[] targets) {
        int found = 0;
        for (int t : targets) {
            if (list.contains(t)) {
                found++;
            }
        }
        return found;
    }

    // Q09  Return the time complexity of q09Code above. list and targets both have n elements.
    //      Renvoyer la complexité en temps de q09Code ci-dessus. list et targets ont tous les deux n éléments.
    public static String q09() {
        throw new UnsupportedOperationException("TODO");
    }

    static long q10Code(int n) {
        if (n < 2) {
            return n;
        }
        return q10Code(n - 1) + q10Code(n - 2);
    }

    // Q10  Return the time complexity of q10Code above, as a string like "O(n)".
    //      Renvoyer la complexité en temps de q10Code ci-dessus, sous forme de chaîne comme "O(n)".
    public static String q10() {
        throw new UnsupportedOperationException("TODO");
    }
}
