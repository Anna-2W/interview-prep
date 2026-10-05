package com.mastery.interview.patterns.heap;

import static com.mastery.interview.patterns.heap.HeapExercises.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class HeapExercisesTest {

    @Nested
    class E01TopKFrequent {
        @Test
        void example() {
            assertThat(topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2)).containsExactly(1, 2);
        }

        @Test
        void mostFrequentFirst() {
            assertThat(topKFrequent(new int[] {4, 5, 5, 6, 6, 6}, 3)).containsExactly(6, 5, 4);
        }
    }

    @Nested
    class E02KClosest {
        @Test
        void example() {
            assertThat(kClosest(new int[][] {{1, 3}, {-2, 2}, {5, 8}}, 1)).isDeepEqualTo(new int[][] {{-2, 2}});
        }

        @Test
        void twoClosest() {
            int[][] result = kClosest(new int[][] {{3, 3}, {5, -1}, {-2, 4}}, 2);
            assertThat(result).hasDimensions(2, 2);
            assertThat(List.of(List.of(result[0][0], result[0][1]), List.of(result[1][0], result[1][1])))
                    .containsExactlyInAnyOrder(List.of(3, 3), List.of(-2, 4));
        }
    }

    @Nested
    class E03MergeKSorted {
        @Test
        void example() {
            assertThat(mergeKSorted(List.of(new int[] {1, 4, 5}, new int[] {1, 3, 4}, new int[] {2, 6})))
                    .containsExactly(1, 1, 2, 3, 4, 4, 5, 6);
        }

        @Test
        void withEmptyLists() {
            assertThat(mergeKSorted(List.of(new int[] {}, new int[] {2}, new int[] {}))).containsExactly(2);
        }
    }

    @Nested
    class E04RunningMedians {
        @Test
        void example() {
            assertThat(runningMedians(new int[] {5, 15, 1, 3})).containsExactly(new double[] {5.0, 10.0, 5.0, 4.0}, within(1e-9));
        }

        @Test
        void decreasing() {
            assertThat(runningMedians(new int[] {3, 2, 1})).containsExactly(new double[] {3.0, 2.5, 2.0}, within(1e-9));
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[200_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (i * 7919) % 200_000;
            }
            assertThat(runningMedians(nums)).hasSize(200_000);
        }
    }
}
