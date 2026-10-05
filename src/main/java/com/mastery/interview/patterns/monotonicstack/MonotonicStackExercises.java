package com.mastery.interview.patterns.monotonicstack;

// 03 Patterns, section 7 Monotonic stack: book/en/03-patterns.md
public final class MonotonicStackExercises {

    private MonotonicStackExercises() {
    }

    // E01  For each number, return the first bigger number on its right, or -1 if there is none.
    //      Pour chaque nombre, renvoyer le premier nombre plus grand à sa droite, ou -1 s'il n'y en a pas.
    //      nextGreater({2, 1, 2, 4, 3}) -> {4, 2, 4, -1, -1}
    public static int[] nextGreater(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  For each day, return how many days you must wait for a warmer temperature, or 0 if it never comes.
    //      Pour chaque jour, renvoyer combien de jours attendre une température plus chaude, ou 0 si ça n'arrive jamais.
    //      dailyTemperatures({73, 74, 75, 71, 69, 72, 76, 73}) -> {1, 1, 4, 2, 1, 1, 0, 0}
    public static int[] dailyTemperatures(int[] temperatures) {
        throw new UnsupportedOperationException("TODO");
    }
}
