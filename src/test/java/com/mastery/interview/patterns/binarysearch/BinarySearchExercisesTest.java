package com.mastery.interview.patterns.binarysearch;

import static com.mastery.interview.patterns.binarysearch.BinarySearchExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class BinarySearchExercisesTest {

    @Nested
    class E01Search {
        @Test
        void found() {
            assertThat(search(new int[] {-1, 0, 3, 5, 9, 12}, 9)).isEqualTo(4);
        }

        @Test
        void absent() {
            assertThat(search(new int[] {-1, 0, 3, 5, 9, 12}, 2)).isEqualTo(-1);
        }

        @Test
        void bounds() {
            assertThat(search(new int[] {1, 2, 3}, 1)).isZero();
            assertThat(search(new int[] {1, 2, 3}, 3)).isEqualTo(2);
            assertThat(search(new int[] {}, 3)).isEqualTo(-1);
        }
    }

    @Nested
    class E02SearchInsert {
        @Test
        void examples() {
            int[] nums = {1, 3, 5, 6};
            assertThat(searchInsert(nums, 5)).isEqualTo(2);
            assertThat(searchInsert(nums, 2)).isEqualTo(1);
            assertThat(searchInsert(nums, 7)).isEqualTo(4);
            assertThat(searchInsert(nums, 0)).isZero();
        }
    }

    @Nested
    class E03Sqrt {
        @Test
        void examples() {
            assertThat(sqrt(0)).isZero();
            assertThat(sqrt(1)).isEqualTo(1);
            assertThat(sqrt(8)).isEqualTo(2);
            assertThat(sqrt(16)).isEqualTo(4);
        }

        @Test
        void noOverflow() {
            assertThat(sqrt(Integer.MAX_VALUE)).isEqualTo(46_340);
        }
    }

    @Nested
    class E04SearchRotated {
        @Test
        void foundInRightPart() {
            assertThat(searchRotated(new int[] {4, 5, 6, 7, 0, 1, 2}, 0)).isEqualTo(4);
        }

        @Test
        void foundInLeftPart() {
            assertThat(searchRotated(new int[] {4, 5, 6, 7, 0, 1, 2}, 5)).isEqualTo(1);
        }

        @Test
        void absent() {
            assertThat(searchRotated(new int[] {4, 5, 6, 7, 0, 1, 2}, 3)).isEqualTo(-1);
        }

        @Test
        void notRotated() {
            assertThat(searchRotated(new int[] {1, 3}, 3)).isEqualTo(1);
        }
    }

    @Nested
    class E05MinEatingSpeed {
        @Test
        void examples() {
            assertThat(minEatingSpeed(new int[] {3, 6, 7, 11}, 8)).isEqualTo(4);
            assertThat(minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 5)).isEqualTo(30);
            assertThat(minEatingSpeed(new int[] {30, 11, 23, 4, 20}, 6)).isEqualTo(23);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void hugePilesAreFast() {
            int[] piles = new int[10_000];
            java.util.Arrays.fill(piles, 1_000_000_000);
            assertThat(minEatingSpeed(piles, 10_000)).isEqualTo(1_000_000_000);
        }
    }
}
