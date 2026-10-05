package com.mastery.interview.patterns.hashing;

import java.util.List;

// 03 Patterns, section 1 Hashing: book/en/03-patterns.md
public final class HashingExercises {

    private HashingExercises() {
    }

    // E01  Return true if t has exactly the same letters as s, each one the same number of times.
    //      Renvoyer true si t a exactement les mêmes lettres que s, chacune le même nombre de fois.
    //      isAnagram("listen", "silent") -> true    isAnagram("rat", "car") -> false
    public static boolean isAnagram(String s, String t) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Put the words that are anagrams of each other in the same group. Order does not matter.
    //      Mettre dans le même groupe les mots qui sont des anagrammes entre eux. L'ordre ne compte pas.
    //      groupAnagrams(["eat", "tea", "tan", "ate", "nat"]) -> [[eat, tea, ate], [tan, nat]]
    public static List<List<String>> groupAnagrams(List<String> words) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return the length of the longest run of consecutive numbers (x, x+1, x+2...), in any position. Target O(n), no sort.
    //      Renvoyer la longueur de la plus longue suite de nombres consécutifs (x, x+1, x+2...), où qu'ils soient. Viser O(n), sans trier.
    //      longestConsecutive({100, 4, 200, 1, 3, 2}) -> 4    (1, 2, 3, 4)
    public static int longestConsecutive(int[] nums) {
        throw new UnsupportedOperationException("TODO");
    }
}
