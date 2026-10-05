package com.mastery.interview.patterns.bits;

import static com.mastery.interview.patterns.bits.BitExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class BitExercisesTest {

    @Nested
    class E01SingleNumber {
        @Test
        void examples() {
            assertThat(singleNumber(new int[] {4, 1, 2, 1, 2})).isEqualTo(4);
            assertThat(singleNumber(new int[] {7})).isEqualTo(7);
            assertThat(singleNumber(new int[] {-3, 5, 5})).isEqualTo(-3);
        }
    }

    @Nested
    class E02CountOnes {
        @Test
        void examples() {
            assertThat(countOnes(11)).isEqualTo(3);
            assertThat(countOnes(0)).isZero();
            assertThat(countOnes(128)).isEqualTo(1);
            assertThat(countOnes(Integer.MAX_VALUE)).isEqualTo(31);
        }
    }

    @Nested
    class E03IsPowerOfTwo {
        @Test
        void examples() {
            assertThat(isPowerOfTwo(1)).isTrue();
            assertThat(isPowerOfTwo(16)).isTrue();
            assertThat(isPowerOfTwo(6)).isFalse();
            assertThat(isPowerOfTwo(0)).isFalse();
            assertThat(isPowerOfTwo(-8)).isFalse();
        }
    }

    @Nested
    class E04MissingNumber {
        @Test
        void examples() {
            assertThat(missingNumber(new int[] {3, 0, 1})).isEqualTo(2);
            assertThat(missingNumber(new int[] {0, 1})).isEqualTo(2);
            assertThat(missingNumber(new int[] {1})).isZero();
        }
    }
}
