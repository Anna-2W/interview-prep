package com.mastery.interview.patterns.slidingwindow;

import static com.mastery.interview.patterns.slidingwindow.SlidingWindowExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class SlidingWindowExercisesTest {

    @Nested
    class E01LongestUniqueSubstring {
        @Test
        void examples() {
            assertThat(longestUniqueSubstring("abcabcbb")).isEqualTo(3);
            assertThat(longestUniqueSubstring("bbbbb")).isEqualTo(1);
            assertThat(longestUniqueSubstring("pwwkew")).isEqualTo(3);
        }

        @Test
        void empty() {
            assertThat(longestUniqueSubstring("")).isZero();
        }

        @Test
        void repeatOutsideTheWindow() {
            assertThat(longestUniqueSubstring("abba")).isEqualTo(2);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 300_000; i++) {
                sb.append((char) ('a' + i % 26));
            }
            assertThat(longestUniqueSubstring(sb.toString())).isEqualTo(26);
        }
    }

    @Nested
    class E02MinSubarrayLength {
        @Test
        void example() {
            assertThat(minSubarrayLength(7, new int[] {2, 3, 1, 2, 4, 3})).isEqualTo(2);
        }

        @Test
        void oneElementIsEnough() {
            assertThat(minSubarrayLength(4, new int[] {1, 4, 4})).isEqualTo(1);
        }

        @Test
        void impossible() {
            assertThat(minSubarrayLength(100, new int[] {1, 2, 3})).isZero();
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[300_000];
            java.util.Arrays.fill(nums, 1);
            assertThat(minSubarrayLength(150_000, nums)).isEqualTo(150_000);
        }
    }

    @Nested
    class E03LongestOnes {
        @Test
        void example() {
            assertThat(longestOnes(new int[] {1, 1, 0, 0, 1, 1, 1, 0, 1}, 1)).isEqualTo(5);
        }

        @Test
        void kZero() {
            assertThat(longestOnes(new int[] {1, 0, 1, 1}, 0)).isEqualTo(2);
        }

        @Test
        void flipEverything() {
            assertThat(longestOnes(new int[] {0, 0, 0}, 3)).isEqualTo(3);
        }
    }

    @Nested
    class E04ContainsPermutation {
        @Test
        void found() {
            assertThat(containsPermutation("ab", "eidbaooo")).isTrue();
        }

        @Test
        void notFound() {
            assertThat(containsPermutation("ab", "eidboaoo")).isFalse();
        }

        @Test
        void patternLongerThanText() {
            assertThat(containsPermutation("abc", "ab")).isFalse();
        }

        @Test
        void repeatedLetters() {
            assertThat(containsPermutation("aab", "xbaay")).isTrue();
            assertThat(containsPermutation("aab", "xbbay")).isFalse();
        }
    }
}
