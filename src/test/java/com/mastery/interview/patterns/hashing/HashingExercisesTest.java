package com.mastery.interview.patterns.hashing;

import static com.mastery.interview.patterns.hashing.HashingExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class HashingExercisesTest {

    static List<List<String>> sortedGroups(List<List<String>> groups) {
        List<List<String>> result = new ArrayList<>();
        for (List<String> g : groups) {
            List<String> copy = new ArrayList<>(g);
            copy.sort(null);
            result.add(copy);
        }
        return result;
    }

    @Nested
    class E01IsAnagram {
        @Test
        void anagram() {
            assertThat(isAnagram("listen", "silent")).isTrue();
        }

        @Test
        void notAnagram() {
            assertThat(isAnagram("rat", "car")).isFalse();
        }

        @Test
        void differentLengths() {
            assertThat(isAnagram("ab", "abb")).isFalse();
        }

        @Test
        void sameLettersDifferentCounts() {
            assertThat(isAnagram("aab", "abb")).isFalse();
        }
    }

    @Nested
    class E02GroupAnagrams {
        @Test
        void groups() {
            assertThat(sortedGroups(groupAnagrams(List.of("eat", "tea", "tan", "ate", "nat"))))
                    .containsExactlyInAnyOrder(List.of("ate", "eat", "tea"), List.of("nat", "tan"));
        }

        @Test
        void singleWordsStayAlone() {
            assertThat(sortedGroups(groupAnagrams(List.of("a", "b"))))
                    .containsExactlyInAnyOrder(List.of("a"), List.of("b"));
        }

        @Test
        void empty() {
            assertThat(groupAnagrams(List.of())).isEmpty();
        }
    }

    @Nested
    class E03LongestConsecutive {
        @Test
        void example() {
            assertThat(longestConsecutive(new int[] {100, 4, 200, 1, 3, 2})).isEqualTo(4);
        }

        @Test
        void withDuplicates() {
            assertThat(longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1})).isEqualTo(9);
        }

        @Test
        void empty() {
            assertThat(longestConsecutive(new int[] {})).isZero();
        }

        @Test
        @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
        void bigInputIsFast() {
            int[] nums = new int[200_000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums.length - i;
            }
            assertThat(longestConsecutive(nums)).isEqualTo(200_000);
        }
    }
}
