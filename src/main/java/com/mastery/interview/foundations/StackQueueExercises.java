package com.mastery.interview.foundations;

import java.util.List;

// F06 Stack and Queue: book/en/foundations/06-stack-queue.md
public final class StackQueueExercises {

    private StackQueueExercises() {
    }

    // E01  Return s backwards, using a Deque as a stack (push every char, then pop).
    //      Renvoyer s à l'envers, en utilisant une Deque comme pile (push chaque caractère, puis pop).
    //      reverseWithStack("abc") -> "cba"    (use a Deque as a stack / utiliser une Deque comme pile)
    public static String reverseWithStack(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  s has only "(" and ")". Return true if every "(" is closed in the right order.
    //      s ne contient que "(" et ")". Renvoyer true si chaque "(" est fermée dans le bon ordre.
    //      isBalanced("(())") -> true    isBalanced("(()") -> false    isBalanced(")(") -> false
    public static boolean isBalanced(String parens) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  s has ( ) [ ] { }. Return true if every bracket is closed by the same kind, in the right order.
    //      s contient ( ) [ ] { }. Renvoyer true si chaque crochet est fermé par le même type, dans le bon ordre.
    //      isValid("([]{})") -> true    isValid("([)]") -> false    isValid("(") -> false
    public static boolean isValid(String brackets) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Remove two equal letters side by side, again and again, until none are left. Return what remains.
    //      Supprimer deux lettres égales côte à côte, encore et encore, jusqu'à ce qu'il n'y en ait plus. Renvoyer ce qui reste.
    //      removeAdjacentDuplicates("abbaca") -> "ca"
    public static String removeAdjacentDuplicates(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  "#" deletes the character typed just before it (if any). Return the final text.
    //      "#" efface le caractère tapé juste avant (s'il y en a un). Renvoyer le texte final.
    //      applyBackspaces("ab#c") -> "ac"    applyBackspaces("a##b") -> "b"
    public static String applyBackspaces(String typed) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Compute an expression in Reverse Polish Notation (+ - * /, integer division).
    //      Calculer une expression en notation polonaise inverse (+ - * /, division entière).
    //      evalRpn(["2", "1", "+", "3", "*"]) -> 9    evalRpn(["4", "13", "5", "/", "+"]) -> 6
    public static int evalRpn(List<String> tokens) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  Move the first element to the end, k times, using a Queue. Return the result.
    //      Déplacer le premier élément à la fin, k fois, avec une Queue. Renvoyer le résultat.
    //      rotate([1, 2, 3, 4], 1) -> [2, 3, 4, 1]    (use a Queue / utiliser une Queue)
    public static List<Integer> rotate(List<Integer> nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  Return the k smallest values in increasing order, using a PriorityQueue.
    //      Renvoyer les k plus petites valeurs dans l'ordre croissant, avec une PriorityQueue.
    //      smallestK([5, 1, 4, 2], 2) -> [1, 2]
    public static List<Integer> smallestK(List<Integer> nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  Return all values sorted, using a PriorityQueue (no Collections.sort).
    //      Renvoyer toutes les valeurs triées, avec une PriorityQueue (sans Collections.sort).
    //      heapSorted([3, 1, 2]) -> [1, 2, 3]    (use a PriorityQueue / utiliser une PriorityQueue)
    public static List<Integer> heapSorted(List<Integer> nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  Return the k-th biggest value (k = 1 is the max), using a PriorityQueue of size k.
    //      Renvoyer la k-ième plus grande valeur (k = 1 est le max), avec une PriorityQueue de taille k.
    //      kthLargest([3, 2, 1, 5, 6, 4], 2) -> 5
    public static int kthLargest(List<Integer> nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }
}
