package com.mastery.interview.foundations;

import java.util.List;

// F06 Stack and Queue: book/en/foundations/06-stack-queue.md
public final class StackQueueExercises {

    private StackQueueExercises() {
    }

    // E01  reverseWithStack("abc") -> "cba"    (use a Deque as a stack / utiliser une Deque comme pile)
    public static String reverseWithStack(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  isBalanced("(())") -> true    isBalanced("(()") -> false    isBalanced(")(") -> false
    public static boolean isBalanced(String parens) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  isValid("([]{})") -> true    isValid("([)]") -> false    isValid("(") -> false
    public static boolean isValid(String brackets) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  removeAdjacentDuplicates("abbaca") -> "ca"
    public static String removeAdjacentDuplicates(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  applyBackspaces("ab#c") -> "ac"    applyBackspaces("a##b") -> "b"
    public static String applyBackspaces(String typed) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  evalRpn(["2", "1", "+", "3", "*"]) -> 9    evalRpn(["4", "13", "5", "/", "+"]) -> 6
    public static int evalRpn(List<String> tokens) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  rotate([1, 2, 3, 4], 1) -> [2, 3, 4, 1]    (use a Queue / utiliser une Queue)
    public static List<Integer> rotate(List<Integer> nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  smallestK([5, 1, 4, 2], 2) -> [1, 2]
    public static List<Integer> smallestK(List<Integer> nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  heapSorted([3, 1, 2]) -> [1, 2, 3]    (use a PriorityQueue / utiliser une PriorityQueue)
    public static List<Integer> heapSorted(List<Integer> nums) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  kthLargest([3, 2, 1, 5, 6, 4], 2) -> 5
    public static int kthLargest(List<Integer> nums, int k) {
        throw new UnsupportedOperationException("TODO");
    }
}
