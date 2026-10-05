package com.mastery.interview.patterns.backtracking;

import static com.mastery.interview.patterns.backtracking.BacktrackingExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class BacktrackingExercisesTest {

    static List<List<Integer>> sortedInside(List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> l : lists) {
            List<Integer> copy = new ArrayList<>(l);
            copy.sort(null);
            result.add(copy);
        }
        return result;
    }

    @Nested
    class E01Subsets {
        @Test
        void twoElements() {
            assertThat(sortedInside(subsets(new int[] {1, 2})))
                    .containsExactlyInAnyOrder(List.of(), List.of(1), List.of(2), List.of(1, 2));
        }

        @Test
        void countIsTwoPowerN() {
            assertThat(subsets(new int[] {1, 2, 3, 4})).hasSize(16);
        }
    }

    @Nested
    class E02Permutations {
        @Test
        void threeElements() {
            assertThat(permutations(new int[] {1, 2, 3})).containsExactlyInAnyOrder(
                    List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3),
                    List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1));
        }

        @Test
        void single() {
            assertThat(permutations(new int[] {9})).containsExactly(List.of(9));
        }
    }

    @Nested
    class E03CombinationSum {
        @Test
        void example() {
            assertThat(sortedInside(combinationSum(new int[] {2, 3, 6, 7}, 7)))
                    .containsExactlyInAnyOrder(List.of(2, 2, 3), List.of(7));
        }

        @Test
        void severalWays() {
            assertThat(sortedInside(combinationSum(new int[] {2, 3, 5}, 8)))
                    .containsExactlyInAnyOrder(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5));
        }

        @Test
        void impossible() {
            assertThat(combinationSum(new int[] {2}, 1)).isEmpty();
        }
    }

    @Nested
    class E04GenerateParentheses {
        @Test
        void three() {
            assertThat(generateParentheses(3))
                    .containsExactlyInAnyOrder("((()))", "(()())", "(())()", "()(())", "()()()");
        }

        @Test
        void one() {
            assertThat(generateParentheses(1)).containsExactly("()");
        }
    }
}
