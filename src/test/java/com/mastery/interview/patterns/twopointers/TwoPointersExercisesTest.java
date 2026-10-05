package com.mastery.interview.patterns.twopointers;

import static com.mastery.interview.patterns.twopointers.TwoPointersExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class TwoPointersExercisesTest {

    @Nested
    class E01PairWithSum {
        @Test
        void found() {
            assertThat(pairWithSum(new int[] {1, 2, 4, 7, 11}, 9)).containsExactly(1, 3);
        }

        @Test
        void notFound() {
            assertThat(pairWithSum(new int[] {1, 2, 3}, 100)).containsExactly(-1, -1);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[300_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = 2 * i;
            }
            assertThat(pairWithSum(nums, 1)).containsExactly(-1, -1);
        }
    }

    @Nested
    class E02MoveZeroes {
        @Test
        void movesZeroesToTheEnd() {
            int[] nums = {0, 1, 0, 3, 12};
            moveZeroes(nums);
            assertThat(nums).containsExactly(1, 3, 12, 0, 0);
        }

        @Test
        void noZero() {
            int[] nums = {1, 2};
            moveZeroes(nums);
            assertThat(nums).containsExactly(1, 2);
        }
    }

    @Nested
    class E03RemoveDuplicates {
        @Test
        void keepsUniqueAtTheStart() {
            int[] nums = {1, 1, 2, 3, 3};
            int k = removeDuplicates(nums);
            assertThat(k).isEqualTo(3);
            assertThat(Arrays.copyOf(nums, k)).containsExactly(1, 2, 3);
        }

        @Test
        void allTheSame() {
            int[] nums = {7, 7, 7};
            assertThat(removeDuplicates(nums)).isEqualTo(1);
            assertThat(nums[0]).isEqualTo(7);
        }

        @Test
        void empty() {
            assertThat(removeDuplicates(new int[] {})).isZero();
        }
    }

    @Nested
    class E04MaxArea {
        @Test
        void example() {
            assertThat(maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7})).isEqualTo(49);
        }

        @Test
        void twoBars() {
            assertThat(maxArea(new int[] {1, 1})).isEqualTo(1);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] h = new int[300_000];
            Arrays.fill(h, 1);
            assertThat(maxArea(h)).isEqualTo(299_999);
        }
    }

    @Nested
    class E05ThreeSum {
        private List<List<Integer>> sorted(List<List<Integer>> triplets) {
            List<List<Integer>> result = new ArrayList<>();
            for (List<Integer> t : triplets) {
                List<Integer> copy = new ArrayList<>(t);
                copy.sort(null);
                result.add(copy);
            }
            return result;
        }

        @Test
        void example() {
            assertThat(sorted(threeSum(new int[] {-1, 0, 1, 2, -1, -4})))
                    .containsExactlyInAnyOrder(List.of(-1, -1, 2), List.of(-1, 0, 1));
        }

        @Test
        void noDuplicateTriplets() {
            assertThat(sorted(threeSum(new int[] {0, 0, 0, 0}))).containsExactly(List.of(0, 0, 0));
        }

        @Test
        void none() {
            assertThat(threeSum(new int[] {1, 2, 3})).isEmpty();
        }
    }
}
