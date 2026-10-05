package com.mastery.interview.datastructures;

import java.util.ArrayList;
import java.util.List;

// Given helper for LinkedListProblems: do not change.
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    // ListNode.of(1, 2, 3) -> 1 -> 2 -> 3
    public static ListNode of(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : values) {
            current.next = new ListNode(v);
            current = current.next;
        }
        return dummy.next;
    }

    // 1 -> 2 -> 3 gives [1, 2, 3]
    public static List<Integer> toList(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode n = head; n != null; n = n.next) {
            values.add(n.val);
        }
        return values;
    }
}
