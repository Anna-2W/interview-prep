package com.mastery.interview.datastructures;

import static com.mastery.interview.datastructures.LinkedListProblems.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinkedListProblemsTest {

    @Nested
    class E01Length {
        @Test
        void counts() {
            assertThat(length(ListNode.of(1, 2, 3))).isEqualTo(3);
        }

        @Test
        void emptyList() {
            assertThat(length(null)).isZero();
        }
    }

    @Nested
    class E02Reverse {
        @Test
        void reverses() {
            assertThat(ListNode.toList(reverse(ListNode.of(1, 2, 3)))).containsExactly(3, 2, 1);
        }

        @Test
        void singleAndEmpty() {
            assertThat(ListNode.toList(reverse(ListNode.of(7)))).containsExactly(7);
            assertThat(reverse(null)).isNull();
        }

        @Test
        void inPlace() {
            ListNode head = ListNode.of(1, 2);
            ListNode second = head.next;
            assertThat(reverse(head)).isSameAs(second);
        }
    }

    @Nested
    class E03Middle {
        @Test
        void oddLength() {
            assertThat(middle(ListNode.of(1, 2, 3, 4, 5)).val).isEqualTo(3);
        }

        @Test
        void evenLengthGivesSecondMiddle() {
            assertThat(middle(ListNode.of(1, 2, 3, 4)).val).isEqualTo(3);
        }

        @Test
        void singleNode() {
            assertThat(middle(ListNode.of(9)).val).isEqualTo(9);
        }
    }

    @Nested
    class E04HasCycle {
        @Test
        void withCycle() {
            ListNode head = ListNode.of(1, 2, 3);
            head.next.next.next = head.next;
            assertThat(hasCycle(head)).isTrue();
        }

        @Test
        void selfLoop() {
            ListNode head = ListNode.of(1);
            head.next = head;
            assertThat(hasCycle(head)).isTrue();
        }

        @Test
        void withoutCycle() {
            assertThat(hasCycle(ListNode.of(1, 2, 3))).isFalse();
            assertThat(hasCycle(null)).isFalse();
        }
    }

    @Nested
    class E05MergeSorted {
        @Test
        void interleaves() {
            assertThat(ListNode.toList(mergeSorted(ListNode.of(1, 3), ListNode.of(2, 4)))).containsExactly(1, 2, 3, 4);
        }

        @Test
        void differentLengthsAndDuplicates() {
            assertThat(ListNode.toList(mergeSorted(ListNode.of(1, 1, 5, 9), ListNode.of(1, 2))))
                    .containsExactly(1, 1, 1, 2, 5, 9);
        }

        @Test
        void oneEmpty() {
            assertThat(ListNode.toList(mergeSorted(null, ListNode.of(1)))).containsExactly(1);
            assertThat(mergeSorted(null, null)).isNull();
        }
    }

    @Nested
    class E06RemoveNthFromEnd {
        @Test
        void middleNode() {
            assertThat(ListNode.toList(removeNthFromEnd(ListNode.of(1, 2, 3, 4), 2))).containsExactly(1, 2, 4);
        }

        @Test
        void headNode() {
            assertThat(ListNode.toList(removeNthFromEnd(ListNode.of(1, 2, 3, 4), 4))).containsExactly(2, 3, 4);
        }

        @Test
        void lastNode() {
            assertThat(ListNode.toList(removeNthFromEnd(ListNode.of(1, 2), 1))).containsExactly(1);
        }

        @Test
        void onlyNode() {
            assertThat(removeNthFromEnd(ListNode.of(1), 1)).isNull();
        }
    }

    @Nested
    class E07RemoveDuplicates {
        @Test
        void removes() {
            assertThat(ListNode.toList(removeDuplicates(ListNode.of(1, 1, 2, 3, 3)))).containsExactly(1, 2, 3);
        }

        @Test
        void allTheSame() {
            assertThat(ListNode.toList(removeDuplicates(ListNode.of(4, 4, 4)))).containsExactly(4);
        }

        @Test
        void emptyList() {
            assertThat(removeDuplicates(null)).isNull();
        }
    }

    @Nested
    class E08IsPalindrome {
        @Test
        void evenPalindrome() {
            assertThat(isPalindrome(ListNode.of(1, 2, 2, 1))).isTrue();
        }

        @Test
        void oddPalindrome() {
            assertThat(isPalindrome(ListNode.of(1, 2, 3, 2, 1))).isTrue();
        }

        @Test
        void notPalindrome() {
            assertThat(isPalindrome(ListNode.of(1, 2))).isFalse();
        }

        @Test
        void emptyAndSingle() {
            assertThat(isPalindrome(null)).isTrue();
            assertThat(isPalindrome(ListNode.of(5))).isTrue();
        }
    }
}
