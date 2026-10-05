package com.mastery.interview.patterns.prefixsum;

import static com.mastery.interview.patterns.prefixsum.PrefixSumExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class PrefixSumExercisesTest {

    @Nested
    class E01RunningSum {
        @Test
        void example() {
            assertThat(runningSum(new int[] {1, 2, 3, 4})).containsExactly(1, 3, 6, 10);
        }

        @Test
        void empty() {
            assertThat(runningSum(new int[] {})).isEmpty();
        }
    }

    @Nested
    class E02PivotIndex {
        @Test
        void example() {
            assertThat(pivotIndex(new int[] {1, 7, 3, 6, 5, 6})).isEqualTo(3);
        }

        @Test
        void none() {
            assertThat(pivotIndex(new int[] {1, 2, 3})).isEqualTo(-1);
        }

        @Test
        void firstIndex() {
            assertThat(pivotIndex(new int[] {2, 1, -1})).isZero();
        }
    }

    @Nested
    class E03CountSubarraysWithSum {
        @Test
        void example() {
            assertThat(countSubarraysWithSum(new int[] {1, 1, 1}, 2)).isEqualTo(2);
        }

        @Test
        void withNegativesAndZero() {
            assertThat(countSubarraysWithSum(new int[] {1, -1, 0}, 0)).isEqualTo(3);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[300_000];
            assertThat(countSubarraysWithSum(nums, 1)).isZero();
        }
    }
}
