package com.mastery.interview.foundations;

import static com.mastery.interview.foundations.ListExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ListExercisesTest {

    @Nested
    class E01LastElement {
        @Test
        void returnsLast() {
            assertThat(lastElement(List.of(4, 8, 15))).isEqualTo(15);
        }

        @Test
        void singleElement() {
            assertThat(lastElement(List.of(7))).isEqualTo(7);
        }
    }

    @Nested
    class E02Sum {
        @Test
        void addsAll() {
            assertThat(sum(List.of(1, 2, 3))).isEqualTo(6);
        }

        @Test
        void emptyIsZero() {
            assertThat(sum(List.of())).isZero();
        }
    }

    @Nested
    class E03Evens {
        @Test
        void keepsEvensInOrder() {
            assertThat(evens(List.of(1, 2, 3, 4))).containsExactly(2, 4);
        }

        @Test
        void noEvensGivesEmptyList() {
            assertThat(evens(List.of(1, 3))).isEmpty();
        }

        @Test
        void negativeEvens() {
            assertThat(evens(List.of(-2, -1, 0))).containsExactly(-2, 0);
        }
    }

    @Nested
    class E04AddFirst {
        @Test
        void insertsAtTheBeginning() {
            List<String> list = new ArrayList<>(List.of("b", "c"));
            addFirst(list, "a");
            assertThat(list).containsExactly("a", "b", "c");
        }

        @Test
        void worksOnEmptyList() {
            List<String> list = new ArrayList<>();
            addFirst(list, "a");
            assertThat(list).containsExactly("a");
        }
    }

    @Nested
    class E05RemoveValue {
        @Test
        void removesEveryOccurrence() {
            List<Integer> list = new ArrayList<>(List.of(1, 2, 1));
            removeValue(list, 1);
            assertThat(list).containsExactly(2);
        }

        @Test
        void removesTheValueNotTheIndex() {
            // list.remove(0) would remove the 5 at index 0: wrong
            List<Integer> list = new ArrayList<>(List.of(5, 0, 7));
            removeValue(list, 0);
            assertThat(list).containsExactly(5, 7);
        }

        @Test
        void absentValueChangesNothing() {
            List<Integer> list = new ArrayList<>(List.of(1, 2));
            removeValue(list, 9);
            assertThat(list).containsExactly(1, 2);
        }
    }

    @Nested
    class E06CountLongWords {
        @Test
        void strictlyLonger() {
            // "hey" has length 3: not strictly longer than 3
            assertThat(countLongWords(List.of("hi", "hello", "hey"), 3)).isEqualTo(1);
        }

        @Test
        void emptyList() {
            assertThat(countLongWords(List.of(), 0)).isZero();
        }
    }

    @Nested
    class E07Reversed {
        @Test
        void reversesOrder() {
            assertThat(reversed(List.of(1, 2, 3))).containsExactly(3, 2, 1);
        }

        @Test
        void doesNotChangeInput() {
            List<Integer> input = new ArrayList<>(List.of(1, 2, 3));
            reversed(input);
            assertThat(input).containsExactly(1, 2, 3);
        }
    }

    @Nested
    class E08WithoutDuplicates {
        @Test
        void keepsFirstOccurrenceAndOrder() {
            assertThat(withoutDuplicates(List.of("a", "b", "a", "c", "b"))).containsExactly("a", "b", "c");
        }

        @Test
        void noDuplicatesStaysTheSame() {
            assertThat(withoutDuplicates(List.of("x", "y"))).containsExactly("x", "y");
        }
    }

    @Nested
    class E09Merge {
        @Test
        void aThenB() {
            assertThat(merge(List.of(1, 2), List.of(3))).containsExactly(1, 2, 3);
        }

        @Test
        void bothEmpty() {
            assertThat(merge(List.of(), List.of())).isEmpty();
        }
    }

    @Nested
    class E10SortedCopy {
        @Test
        void sortsAlphabetically() {
            assertThat(sortedCopy(List.of("c", "a", "b"))).containsExactly("a", "b", "c");
        }

        @Test
        void doesNotChangeInput() {
            List<String> input = new ArrayList<>(List.of("c", "a", "b"));
            sortedCopy(input);
            assertThat(input).containsExactly("c", "a", "b");
        }
    }
}
