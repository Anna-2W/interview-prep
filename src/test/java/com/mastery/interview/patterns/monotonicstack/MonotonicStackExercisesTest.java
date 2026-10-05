package com.mastery.interview.patterns.monotonicstack;

import static com.mastery.interview.patterns.monotonicstack.MonotonicStackExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class MonotonicStackExercisesTest {

    @Nested
    class E01NextGreater {
        @Test
        void example() {
            assertThat(nextGreater(new int[] {2, 1, 2, 4, 3})).containsExactly(4, 2, 4, -1, -1);
        }

        @Test
        void decreasing() {
            assertThat(nextGreater(new int[] {3, 2, 1})).containsExactly(-1, -1, -1);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[300_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums.length - i;
            }
            assertThat(nextGreater(nums)[0]).isEqualTo(-1);
        }
    }

    @Nested
    class E02DailyTemperatures {
        @Test
        void example() {
            assertThat(dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73}))
                    .containsExactly(1, 1, 4, 2, 1, 1, 0, 0);
        }

        @Test
        void increasing() {
            assertThat(dailyTemperatures(new int[] {30, 40, 50})).containsExactly(1, 1, 0);
        }
    }
}
