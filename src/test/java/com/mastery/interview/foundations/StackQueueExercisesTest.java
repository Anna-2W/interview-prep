package com.mastery.interview.foundations;

import static com.mastery.interview.foundations.StackQueueExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StackQueueExercisesTest {

    @Nested
    class E01ReverseWithStack {
        @Test
        void reverses() {
            assertThat(reverseWithStack("abc")).isEqualTo("cba");
        }

        @Test
        void emptyStaysEmpty() {
            assertThat(reverseWithStack("")).isEmpty();
        }
    }

    @Nested
    class E02IsBalanced {
        @Test
        void nested() {
            assertThat(isBalanced("(())")).isTrue();
        }

        @Test
        void sideBySide() {
            assertThat(isBalanced("()()")).isTrue();
        }

        @Test
        void missingClose() {
            assertThat(isBalanced("(()")).isFalse();
        }

        @Test
        void closeBeforeOpen() {
            assertThat(isBalanced(")(")).isFalse();
        }

        @Test
        void emptyIsBalanced() {
            assertThat(isBalanced("")).isTrue();
        }
    }

    @Nested
    class E03IsValid {
        @Test
        void mixedValid() {
            assertThat(isValid("([]{})")).isTrue();
        }

        @Test
        void wrongOrder() {
            assertThat(isValid("([)]")).isFalse();
        }

        @Test
        void unclosed() {
            assertThat(isValid("(")).isFalse();
        }

        @Test
        void onlyClosing() {
            assertThat(isValid("]")).isFalse();
        }
    }

    @Nested
    class E04RemoveAdjacentDuplicates {
        @Test
        void removesInChain() {
            assertThat(removeAdjacentDuplicates("abbaca")).isEqualTo("ca");
        }

        @Test
        void everythingRemoved() {
            assertThat(removeAdjacentDuplicates("abba")).isEmpty();
        }

        @Test
        void nothingToRemove() {
            assertThat(removeAdjacentDuplicates("abc")).isEqualTo("abc");
        }
    }

    @Nested
    class E05ApplyBackspaces {
        @Test
        void oneBackspace() {
            assertThat(applyBackspaces("ab#c")).isEqualTo("ac");
        }

        @Test
        void twoBackspaces() {
            assertThat(applyBackspaces("a##b")).isEqualTo("b");
        }

        @Test
        void backspaceOnEmptyText() {
            assertThat(applyBackspaces("#a")).isEqualTo("a");
        }
    }

    @Nested
    class E06EvalRpn {
        @Test
        void addThenMultiply() {
            assertThat(evalRpn(List.of("2", "1", "+", "3", "*"))).isEqualTo(9);
        }

        @Test
        void integerDivision() {
            assertThat(evalRpn(List.of("4", "13", "5", "/", "+"))).isEqualTo(6);
        }

        @Test
        void subtractionOrderMatters() {
            assertThat(evalRpn(List.of("5", "3", "-"))).isEqualTo(2);
        }

        @Test
        void singleNumber() {
            assertThat(evalRpn(List.of("-7"))).isEqualTo(-7);
        }
    }

    @Nested
    class E07Rotate {
        @Test
        void rotateByOne() {
            assertThat(rotate(List.of(1, 2, 3, 4), 1)).containsExactly(2, 3, 4, 1);
        }

        @Test
        void rotateByZero() {
            assertThat(rotate(List.of(1, 2, 3), 0)).containsExactly(1, 2, 3);
        }

        @Test
        void rotateMoreThanSize() {
            assertThat(rotate(List.of(1, 2, 3), 4)).containsExactly(2, 3, 1);
        }
    }

    @Nested
    class E08SmallestK {
        @Test
        void twoSmallestSorted() {
            assertThat(smallestK(List.of(5, 1, 4, 2), 2)).containsExactly(1, 2);
        }

        @Test
        void withDuplicates() {
            assertThat(smallestK(List.of(3, 1, 1, 2), 3)).containsExactly(1, 1, 2);
        }

        @Test
        void zero() {
            assertThat(smallestK(List.of(3, 1), 0)).isEmpty();
        }
    }

    @Nested
    class E09HeapSorted {
        @Test
        void sorts() {
            assertThat(heapSorted(List.of(3, 1, 2))).containsExactly(1, 2, 3);
        }

        @Test
        void keepsDuplicates() {
            assertThat(heapSorted(List.of(2, -1, 2, 0))).containsExactly(-1, 0, 2, 2);
        }

        @Test
        void emptyList() {
            assertThat(heapSorted(List.of())).isEmpty();
        }
    }

    @Nested
    class E10KthLargest {
        @Test
        void secondLargest() {
            assertThat(kthLargest(List.of(3, 2, 1, 5, 6, 4), 2)).isEqualTo(5);
        }

        @Test
        void largest() {
            assertThat(kthLargest(List.of(3, 2, 1, 5, 6, 4), 1)).isEqualTo(6);
        }

        @Test
        void withDuplicates() {
            assertThat(kthLargest(List.of(3, 2, 3, 1, 2, 4, 5, 5, 6), 4)).isEqualTo(4);
        }
    }
}
