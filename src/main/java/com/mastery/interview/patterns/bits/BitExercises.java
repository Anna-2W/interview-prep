package com.mastery.interview.patterns.bits;

// 03 Patterns, section 10 Bit manipulation: book/en/03-patterns.md
public final class BitExercises {

    private BitExercises() {
    }

    // E01  Every number appears twice except one. Return that one, with O(1) extra space.
    //      Chaque nombre apparaît deux fois sauf un. Renvoyer celui-là, avec O(1) en espace.
    //      singleNumber({4, 1, 2, 1, 2}) -> 4
    public static int singleNumber(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  n >= 0. Return how many bits are 1 in n, without Integer.bitCount.
    //      n >= 0. Renvoyer combien de bits valent 1 dans n, sans Integer.bitCount.
    //      countOnes(11) -> 3    (11 = 1011)
    public static int countOnes(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return true if n is 1, 2, 4, 8, 16... Without a loop.
    //      Renvoyer true si n vaut 1, 2, 4, 8, 16... Sans boucle.
    //      isPowerOfTwo(16) -> true    (6) -> false    (0) -> false
    public static boolean isPowerOfTwo(int n) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  The array holds the numbers from 0 to n except one. Return the missing one.
    //      Le tableau contient les nombres de 0 à n sauf un. Renvoyer celui qui manque.
    //      missingNumber({3, 0, 1}) -> 2
    public static int missingNumber(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }
}
