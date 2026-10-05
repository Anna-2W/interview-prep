package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 7
public final class LinkedListProblems {

    private LinkedListProblems() {
    }

    // E01  Return the number of nodes.
    //      Renvoyer le nombre de nœuds.
    //      1 -> 2 -> 3 gives 3    null gives 0
    public static int length(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Reverse the list in place and return the new head.
    //      Inverser la liste sur place et renvoyer la nouvelle tête.
    //      1 -> 2 -> 3 gives 3 -> 2 -> 1    (in place)
    public static ListNode reverse(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Return the middle node (for an even length, the second of the two middle nodes).
    //      Renvoyer le nœud du milieu (pour une longueur paire, le second des deux du milieu).
    //      1 -> 2 -> 3 -> 4 -> 5 gives node 3    1 -> 2 -> 3 -> 4 gives node 3
    public static ListNode middle(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return true if following next never reaches null. O(1) extra space.
    //      Renvoyer true si en suivant next on n'arrive jamais à null. O(1) en espace.
    //      1 -> 2 -> 3 -> back to 2 gives true    1 -> 2 -> 3 gives false    (O(1) space)
    public static boolean hasCycle(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Both lists are sorted. Return one sorted list made of all their nodes.
    //      Les deux listes sont triées. Renvoyer une seule liste triée faite de tous leurs nœuds.
    //      1 -> 3 and 2 -> 4 give 1 -> 2 -> 3 -> 4
    public static ListNode mergeSorted(ListNode a, ListNode b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Remove the n-th node counting from the end (n = 1 is the last) and return the head.
    //      Supprimer le n-ième nœud en comptant depuis la fin (n = 1 est le dernier) et renvoyer la tête.
    //      1 -> 2 -> 3 -> 4, n = 2 gives 1 -> 2 -> 4    n = 4 gives 2 -> 3 -> 4
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  The list is sorted. Keep each value once and return the head.
    //      La liste est triée. Garder chaque valeur une seule fois et renvoyer la tête.
    //      1 -> 1 -> 2 -> 3 -> 3 gives 1 -> 2 -> 3    (list is sorted)
    public static ListNode removeDuplicates(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  Return true if the values read the same from both ends.
    //      Renvoyer true si les valeurs se lisent pareil dans les deux sens.
    //      1 -> 2 -> 2 -> 1 gives true    1 -> 2 gives false
    public static boolean isPalindrome(ListNode head) {
        throw new UnsupportedOperationException("TODO");
    }
}
