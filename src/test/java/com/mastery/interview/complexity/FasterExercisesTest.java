package com.mastery.interview.complexity;

import static com.mastery.interview.complexity.FasterExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class FasterExercisesTest {

    @Nested
    class E01HasDuplicate {
        @Test
        void withDuplicate() {
            assertThat(hasDuplicate(new int[] {1, 2, 3, 1})).isTrue();
        }

        @Test
        void withoutDuplicate() {
            assertThat(hasDuplicate(new int[] {1, 2, 3})).isFalse();
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[300_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i;
            }
            assertThat(hasDuplicate(nums)).isFalse();
        }
    }

    @Nested
    class E02CountCommon {
        @Test
        void countsCommonValues() {
            assertThat(countCommon(new int[] {1, 2, 3, 4}, new int[] {2, 4, 6})).isEqualTo(2);
        }

        @Test
        void nothingInCommon() {
            assertThat(countCommon(new int[] {1}, new int[] {2})).isZero();
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] a = new int[200_000];
            int[] b = new int[200_000];
            for (int i = 0; i < a.length; i++) {
                a[i] = i;
                b[i] = i + 100_000;
            }
            assertThat(countCommon(a, b)).isEqualTo(100_000);
        }
    }

    @Nested
    class E03JoinWords {
        @Test
        void joinsWithCommas() {
            assertThat(joinWords(List.of("a", "b", "c"))).isEqualTo("a,b,c");
        }

        @Test
        void oneWord() {
            assertThat(joinWords(List.of("solo"))).isEqualTo("solo");
        }

        @Test
        void noWords() {
            assertThat(joinWords(List.of())).isEmpty();
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            List<String> words = new ArrayList<>();
            for (int i = 0; i < 200_000; i++) {
                words.add("word");
            }
            assertThat(joinWords(words)).hasSize(200_000 * 5 - 1);
        }
    }

    @Nested
    class E04MaxWindowSum {
        @Test
        void findsBestWindow() {
            assertThat(maxWindowSum(new int[] {1, 4, 2, 10, 2, 3}, 3)).isEqualTo(16);
        }

        @Test
        void windowIsWholeArray() {
            assertThat(maxWindowSum(new int[] {1, 2, 3}, 3)).isEqualTo(6);
        }

        @Test
        void negatives() {
            assertThat(maxWindowSum(new int[] {-5, -1, -3}, 1)).isEqualTo(-1);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[1_000_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = i % 100;
            }
            assertThat(maxWindowSum(nums, 500_000)).isEqualTo(24_750_000L);
        }
    }

    @Nested
    class E05RangeSums {
        @Test
        void answersEachQuery() {
            assertThat(rangeSums(new int[] {1, 2, 3, 4}, new int[][] {{0, 1}, {1, 3}})).containsExactly(3, 9);
        }

        @Test
        void singleElementRange() {
            assertThat(rangeSums(new int[] {5, 6}, new int[][] {{1, 1}})).containsExactly(6);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int n = 200_000;
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = 1;
            }
            int[][] queries = new int[n][];
            for (int q = 0; q < n; q++) {
                queries[q] = new int[] {0, n - 1};
            }
            long[] answers = rangeSums(nums, queries);
            assertThat(answers).hasSize(n);
            assertThat(answers[n - 1]).isEqualTo(n);
        }
    }

    @Nested
    class E06Fib {
        @Test
        void smallValues() {
            assertThat(fib(0)).isZero();
            assertThat(fib(1)).isEqualTo(1);
            assertThat(fib(10)).isEqualTo(55);
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigValueIsFast() {
            assertThat(fib(90)).isEqualTo(2_880_067_194_370_816_120L);
        }
    }
}
