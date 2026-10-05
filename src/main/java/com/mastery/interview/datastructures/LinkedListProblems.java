package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 7
public final class LinkedListProblems {

    private LinkedListProblems() {
    }

    // E01  1 -> 2 -> 3 gives 3    null gives 0
    public static int length(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  1 -> 2 -> 3 gives 3 -> 2 -> 1    (in place)
    public static ListNode reverse(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  1 -> 2 -> 3 -> 4 -> 5 gives node 3    1 -> 2 -> 3 -> 4 gives node 3
    public static ListNode middle(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  1 -> 2 -> 3 -> back to 2 gives true    1 -> 2 -> 3 gives false    (O(1) space)
    public static boolean hasCycle(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  1 -> 3 and 2 -> 4 give 1 -> 2 -> 3 -> 4
    public static ListNode mergeSorted(ListNode a, ListNode b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  1 -> 2 -> 3 -> 4, n = 2 gives 1 -> 2 -> 4    n = 4 gives 2 -> 3 -> 4
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  1 -> 1 -> 2 -> 3 -> 3 gives 1 -> 2 -> 3    (list is sorted)
    public static ListNode removeDuplicates(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  1 -> 2 -> 2 -> 1 gives true    1 -> 2 gives false
    public static boolean isPalindrome(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }
}
