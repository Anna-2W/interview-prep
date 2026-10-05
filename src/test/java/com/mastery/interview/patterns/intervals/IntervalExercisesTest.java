package com.mastery.interview.patterns.intervals;

import static com.mastery.interview.patterns.intervals.IntervalExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class IntervalExercisesTest {

    @Nested
    class E01CanAttendAll {
        @Test
        void overlap() {
            assertThat(canAttendAll(new int[][] {{0, 30}, {5, 10}, {15, 20}})).isFalse();
        }

        @Test
        void noOverlapUnsorted() {
            assertThat(canAttendAll(new int[][] {{7, 10}, {2, 4}})).isTrue();
        }

        @Test
        void touchingIsFine() {
            assertThat(canAttendAll(new int[][] {{1, 5}, {5, 8}})).isTrue();
        }
    }

    @Nested
    class E02Merge {
        @Test
        void example() {
            assertThat(merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}}))
                    .isDeepEqualTo(new int[][] {{1, 6}, {8, 10}, {15, 18}});
        }

        @Test
        void touching() {
            assertThat(merge(new int[][] {{1, 4}, {4, 5}})).isDeepEqualTo(new int[][] {{1, 5}});
        }

        @Test
        void unsortedAndContained() {
            assertThat(merge(new int[][] {{2, 3}, {1, 10}})).isDeepEqualTo(new int[][] {{1, 10}});
        }
    }

    @Nested
    class E03Insert {
        @Test
        void mergesWithOne() {
            assertThat(insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}))
                    .isDeepEqualTo(new int[][] {{1, 5}, {6, 9}});
        }

        @Test
        void mergesWithSeveral() {
            assertThat(insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8}))
                    .isDeepEqualTo(new int[][] {{1, 2}, {3, 10}, {12, 16}});
        }

        @Test
        void intoEmpty() {
            assertThat(insert(new int[][] {}, new int[] {5, 7})).isDeepEqualTo(new int[][] {{5, 7}});
        }
    }

    @Nested
    class E04MinMeetingRooms {
        @Test
        void example() {
            assertThat(minMeetingRooms(new int[][] {{0, 30}, {5, 10}, {15, 20}})).isEqualTo(2);
        }

        @Test
        void touchingNeedsOneRoom() {
            assertThat(minMeetingRooms(new int[][] {{1, 5}, {5, 8}})).isEqualTo(1);
        }

        @Test
        void allOverlap() {
            assertThat(minMeetingRooms(new int[][] {{1, 10}, {2, 9}, {3, 8}})).isEqualTo(3);
        }
    }
}
