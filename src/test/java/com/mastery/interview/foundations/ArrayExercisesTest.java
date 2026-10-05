package com.mastery.interview.foundations;

import static com.mastery.interview.foundations.ArrayExercises.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArrayExercisesTest {

    @Nested
    class E01Sum {
        @Test
        void addsAllNumbers() {
            assertThat(sum(new int[] {1, 2, 3})).isEqualTo(6);
        }

        @Test
        void emptyArrayIsZero() {
            assertThat(sum(new int[] {})).isZero();
        }

        @Test
        void handlesNegatives() {
            assertThat(sum(new int[] {-4, 4, -1})).isEqualTo(-1);
        }
    }

    @Nested
    class E02Max {
        @Test
        void findsBiggest() {
            assertThat(max(new int[] {3, 9, 2})).isEqualTo(9);
        }

        @Test
        void allNegatives() {
            // starting from 0 instead of nums[0] gives 0 here: wrong
            assertThat(max(new int[] {-5, -2, -8})).isEqualTo(-2);
        }

        @Test
        void singleElement() {
            assertThat(max(new int[] {7})).isEqualTo(7);
        }
    }

    @Nested
    class E03Contains {
        @Test
        void present() {
            assertThat(contains(new int[] {1, 2, 3}, 2)).isTrue();
        }

        @Test
        void absent() {
            assertThat(contains(new int[] {1, 2, 3}, 7)).isFalse();
        }

        @Test
        void emptyArray() {
            assertThat(contains(new int[] {}, 1)).isFalse();
        }
    }

    @Nested
    class E04IndexOf {
        @Test
        void firstOccurrence() {
            assertThat(indexOf(new int[] {5, 7, 5}, 5)).isZero();
        }

        @Test
        void middle() {
            assertThat(indexOf(new int[] {5, 7, 5}, 7)).isEqualTo(1);
        }

        @Test
        void absentIsMinusOne() {
            assertThat(indexOf(new int[] {5, 7}, 9)).isEqualTo(-1);
        }
    }

    @Nested
    class E05CountEven {
        @Test
        void countsEvens() {
            assertThat(countEven(new int[] {1, 2, 4})).isEqualTo(2);
        }

        @Test
        void negativeNumbers() {
            assertThat(countEven(new int[] {-2, -3})).isEqualTo(1);
        }

        @Test
        void zeroIsEven() {
            assertThat(countEven(new int[] {0})).isEqualTo(1);
        }
    }

    @Nested
    class E06Doubled {
        @Test
        void doublesEachNumber() {
            assertThat(doubled(new int[] {1, 2, -3})).containsExactly(2, 4, -6);
        }

        @Test
        void doesNotChangeInput() {
            int[] input = {1, 2};
            doubled(input);
            assertThat(input).containsExactly(1, 2);
        }
    }

    @Nested
    class E07Average {
        @Test
        void notAnIntegerDivision() {
            assertThat(average(new int[] {1, 2})).isEqualTo(1.5, within(1e-9));
        }

        @Test
        void singleElement() {
            assertThat(average(new int[] {4})).isEqualTo(4.0, within(1e-9));
        }
    }

    @Nested
    class E08Reversed {
        @Test
        void reversesOrder() {
            assertThat(reversed(new int[] {1, 2, 3})).containsExactly(3, 2, 1);
        }

        @Test
        void emptyStaysEmpty() {
            assertThat(reversed(new int[] {})).isEmpty();
        }

        @Test
        void doesNotChangeInput() {
            int[] input = {1, 2, 3};
            reversed(input);
            assertThat(input).containsExactly(1, 2, 3);
        }
    }

    @Nested
    class E09IsSorted {
        @Test
        void sortedWithDuplicates() {
            assertThat(isSorted(new int[] {1, 2, 2, 5})).isTrue();
        }

        @Test
        void notSorted() {
            assertThat(isSorted(new int[] {1, 3, 2})).isFalse();
        }

        @Test
        void emptyAndSingleAreSorted() {
            assertThat(isSorted(new int[] {})).isTrue();
            assertThat(isSorted(new int[] {9})).isTrue();
        }
    }

    @Nested
    class E10Concat {
        @Test
        void joinsBothArrays() {
            assertThat(concat(new int[] {1, 2}, new int[] {3})).containsExactly(1, 2, 3);
        }

        @Test
        void emptyFirst() {
            assertThat(concat(new int[] {}, new int[] {4})).containsExactly(4);
        }

        @Test
        void bothEmpty() {
            assertThat(concat(new int[] {}, new int[] {})).isEmpty();
        }
    }
}
