package com.mastery.interview.patterns.backtracking;

import java.util.List;

// 03 Patterns, section 11 Backtracking: book/en/03-patterns.md
public final class BacktrackingExercises {

    private BacktrackingExercises() {
    }

    // E01  Return every subset of nums (including the empty one). Order does not matter.
    //      Renvoyer tous les sous-ensembles de nums (y compris le vide). L'ordre ne compte pas.
    //      subsets({1, 2}) -> [[], [1], [2], [1, 2]]
    public static List<List<Integer>> subsets(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Values are distinct. Return every possible order of the values.
    //      Les valeurs sont distinctes. Renvoyer tous les ordres possibles des valeurs.
    //      permutations({1, 2, 3}) -> [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]
    public static List<List<Integer>> permutations(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return every combination of candidates whose sum is target. A number can be used several times. No duplicate combinations.
    //      Renvoyer toutes les combinaisons de candidates dont la somme vaut target. Un nombre peut servir plusieurs fois. Pas de combinaison en double.
    //      combinationSum({2, 3, 6, 7}, 7) -> [[2, 2, 3], [7]]
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return every well-formed string made of n "(" and n ")".
    //      Renvoyer toutes les chaînes bien formées faites de n "(" et n ")".
    //      generateParentheses(2) -> ["(())", "()()"]
    public static List<String> generateParentheses(int n) {
        throw new UnsupportedOperationException("TODO");
    }
}
