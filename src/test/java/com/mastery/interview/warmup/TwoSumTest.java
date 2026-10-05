package com.mastery.interview.warmup;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class TwoSumTest {

    @Test
    void findsPairAtTheStart() {
        assertThat(TwoSum.twoSum(new int[] {2, 7, 11, 15}, 9)).containsExactly(0, 1);
    }

    @Test
    void findsPairNotAtTheStart() {
        assertThat(TwoSum.twoSum(new int[] {3, 2, 4}, 6)).containsExactly(1, 2);
    }

    @Test
    void doesNotReuseTheSameElement() {
        assertThat(TwoSum.twoSum(new int[] {3, 3}, 6)).containsExactly(0, 1);
    }

    @Test
    void handlesNegativeNumbers() {
        assertThat(TwoSum.twoSum(new int[] {-4, 1, 9, -1}, -5)).containsExactly(0, 3);
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void handlesLargeInputInLinearTime() {
        int n = 1_000_000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        assertThat(TwoSum.twoSum(nums, 2 * n - 3)).containsExactly(n - 2, n - 1);
    }
}
