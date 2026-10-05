package com.mastery.interview.foundations;

import java.util.List;
import java.util.Set;

// F05 Set: book/en/foundations/05-set.md
public final class SetExercises {

    private SetExercises() {
    }

    // E01  Return true if a value appears more than once.
    //      Renvoyer true si une valeur apparaît plus d'une fois.
    //      hasDuplicate([1, 2, 3, 1]) -> true    hasDuplicate([1, 2, 3]) -> false
    public static boolean hasDuplicate(List<Integer> nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Return how many different values there are.
    //      Renvoyer combien il y a de valeurs différentes.
    //      countDistinct([1, 2, 2, 3]) -> 3
    public static int countDistinct(List<Integer> nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return a new set with the values that are in both a and b. Do not change a or b.
    //      Renvoyer un nouveau set avec les valeurs présentes dans a et dans b. Ne pas modifier a ni b.
    //      common({1, 2, 3}, {2, 3, 4}) -> {2, 3}    (inputs unchanged / entrées intactes)
    public static Set<Integer> common(Set<Integer> a, Set<Integer> b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return a new set with every value of a and of b. Do not change a or b.
    //      Renvoyer un nouveau set avec toutes les valeurs de a et de b. Ne pas modifier a ni b.
    //      union({1, 2}, {2, 3}) -> {1, 2, 3}    (inputs unchanged / entrées intactes)
    public static Set<Integer> union(Set<Integer> a, Set<Integer> b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Return a new set with the values of a that are not in b. Do not change a or b.
    //      Renvoyer un nouveau set avec les valeurs de a qui ne sont pas dans b. Ne pas modifier a ni b.
    //      onlyInFirst({1, 2, 3}, {2}) -> {1, 3}    (inputs unchanged / entrées intactes)
    public static Set<Integer> onlyInFirst(Set<Integer> a, Set<Integer> b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Return how many different characters s contains. Case matters.
    //      Renvoyer combien de caractères différents contient s. La casse compte.
    //      countUniqueChars("hello") -> 4
    public static int countUniqueChars(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  Return the first character that is seen a second time while reading s, or '_' if none.
    //      Renvoyer le premier caractère vu une deuxième fois en lisant s, ou '_' s'il n'y en a pas.
    //      firstRepeated("abcb") -> 'b'    firstRepeated("abc") -> '_'
    public static char firstRepeated(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  Return true if every value of small is also in big.
    //      Renvoyer true si chaque valeur de small est aussi dans big.
    //      isSubset({1, 2}, {1, 2, 3}) -> true    isSubset({1, 4}, {1, 2, 3}) -> false
    public static boolean isSubset(Set<Integer> small, Set<Integer> big) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  Return the values sorted, each one only once.
    //      Renvoyer les valeurs triées, chacune une seule fois.
    //      sortedUnique([3, 1, 3, 2]) -> [1, 2, 3]
    public static List<Integer> sortedUnique(List<Integer> nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  Return true if the sentence contains all 26 letters, ignoring case.
    //      Renvoyer true si la phrase contient les 26 lettres, sans tenir compte de la casse.
    //      isPangram("The quick brown fox jumps over the lazy dog") -> true    isPangram("hello") -> false
    public static boolean isPangram(String sentence) {
        throw new UnsupportedOperationException("TODO");
    }
}
