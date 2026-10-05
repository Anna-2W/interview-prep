package com.mastery.interview.patterns.twopointers;

import java.util.List;

// 03 Patterns, section 2 Two pointers: book/en/03-patterns.md
public final class TwoPointersExercises {

    private TwoPointersExercises() {
    }

    // E01  The array is sorted. Return the indexes {i, j} (i < j) of two numbers whose sum is target, or {-1, -1}. O(n), O(1) space.
    //      Le tableau est trié. Renvoyer les indices {i, j} (i < j) de deux nombres dont la somme vaut target, ou {-1, -1}. O(n), O(1) en espace.
    //      pairWithSum({1, 2, 4, 7, 11}, 9) -> {1, 3}
    public static int[] pairWithSum(int[] sorted, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Move every 0 to the end of the array, keeping the order of the other numbers. Change the array itself, no new array.
    //      Déplacer tous les 0 à la fin du tableau, en gardant l'ordre des autres nombres. Modifier le tableau lui-même, pas de nouveau tableau.
    //      {0, 1, 0, 3, 12} becomes {1, 3, 12, 0, 0}
    public static void moveZeroes(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  The array is sorted. Put each different value once at the start of the array and return how many there are. In place.
    //      Le tableau est trié. Mettre chaque valeur différente une seule fois au début du tableau et renvoyer combien il y en a. Sur place.
    //      {1, 1, 2, 3, 3} -> returns 3, the array starts with {1, 2, 3}
    public static int removeDuplicates(int[] sorted) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Each number is the height of a wall. Pick two walls: water fills up to the lower one, width = distance between them. Return the biggest amount of water.
    //      Chaque nombre est la hauteur d'un mur. Choisir deux murs : l'eau monte jusqu'au plus bas, largeur = distance entre eux. Renvoyer la plus grande quantité d'eau.
    //      maxArea({1, 8, 6, 2, 5, 4, 8, 3, 7}) -> 49    (walls 8 and 7, width 7)
    public static int maxArea(int[] heights) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Return every triplet of values that sums to 0, without the same triplet twice. Order does not matter.
    //      Renvoyer tous les triplets de valeurs dont la somme vaut 0, sans le même triplet deux fois. L'ordre ne compte pas.
    //      threeSum({-1, 0, 1, 2, -1, -4}) -> [[-1, -1, 2], [-1, 0, 1]]
    public static List<List<Integer>> threeSum(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }
}
