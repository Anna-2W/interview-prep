package com.mastery.interview.foundations;

import static com.mastery.interview.foundations.SetExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SetExercisesTest {

    @Nested
    class E01HasDuplicate {
        @Test
        void withDuplicate() {
            assertThat(hasDuplicate(List.of(1, 2, 3, 1))).isTrue();
        }

        @Test
        void withoutDuplicate() {
            assertThat(hasDuplicate(List.of(1, 2, 3))).isFalse();
        }

        @Test
        void emptyList() {
            assertThat(hasDuplicate(List.of())).isFalse();
        }
    }

    @Nested
    class E02CountDistinct {
        @Test
        void countsDifferentValues() {
            assertThat(countDistinct(List.of(1, 2, 2, 3))).isEqualTo(3);
        }

        @Test
        void allTheSame() {
            assertThat(countDistinct(List.of(7, 7, 7))).isEqualTo(1);
        }

        @Test
        void emptyList() {
            assertThat(countDistinct(List.of())).isZero();
        }
    }

    @Nested
    class E03Common {
        @Test
        void intersection() {
            assertThat(common(Set.of(1, 2, 3), Set.of(2, 3, 4))).containsExactlyInAnyOrder(2, 3);
        }

        @Test
        void nothingInCommon() {
            assertThat(common(Set.of(1), Set.of(2))).isEmpty();
        }
    }

    @Nested
    class E04Union {
        @Test
        void allElementsOnce() {
            assertThat(union(Set.of(1, 2), Set.of(2, 3))).containsExactlyInAnyOrder(1, 2, 3);
        }

        @Test
        void withEmptySet() {
            assertThat(union(Set.of(1), Set.of())).containsExactly(1);
        }
    }

    @Nested
    class E05OnlyInFirst {
        @Test
        void difference() {
            assertThat(onlyInFirst(Set.of(1, 2, 3), Set.of(2))).containsExactlyInAnyOrder(1, 3);
        }

        @Test
        void everythingRemoved() {
            assertThat(onlyInFirst(Set.of(1, 2), Set.of(1, 2, 3))).isEmpty();
        }
    }

    @Nested
    class E06CountUniqueChars {
        @Test
        void countsDifferentLetters() {
            assertThat(countUniqueChars("hello")).isEqualTo(4);
        }

        @Test
        void emptyString() {
            assertThat(countUniqueChars("")).isZero();
        }

        @Test
        void caseMatters() {
            assertThat(countUniqueChars("aA")).isEqualTo(2);
        }
    }

    @Nested
    class E07FirstRepeated {
        @Test
        void findsFirstRepeated() {
            assertThat(firstRepeated("abcb")).isEqualTo('b');
        }

        @Test
        void firstToRepeatNotFirstLetter() {
            assertThat(firstRepeated("abba")).isEqualTo('b');
        }

        @Test
        void noneGivesUnderscore() {
            assertThat(firstRepeated("abc")).isEqualTo('_');
        }
    }

    @Nested
    class E08IsSubset {
        @Test
        void subset() {
            assertThat(isSubset(Set.of(1, 2), Set.of(1, 2, 3))).isTrue();
        }

        @Test
        void notSubset() {
            assertThat(isSubset(Set.of(1, 4), Set.of(1, 2, 3))).isFalse();
        }

        @Test
        void emptyIsAlwaysSubset() {
            assertThat(isSubset(Set.of(), Set.of(1))).isTrue();
        }
    }

    @Nested
    class E09SortedUnique {
        @Test
        void sortsAndRemovesDuplicates() {
            assertThat(sortedUnique(List.of(3, 1, 3, 2))).containsExactly(1, 2, 3);
        }

        @Test
        void negatives() {
            assertThat(sortedUnique(List.of(0, -5, 0, -5))).containsExactly(-5, 0);
        }
    }

    @Nested
    class E10IsPangram {
        @Test
        void pangramWithUpperCase() {
            assertThat(isPangram("The quick brown fox jumps over the lazy dog")).isTrue();
        }

        @Test
        void notPangram() {
            assertThat(isPangram("hello")).isFalse();
        }

        @Test
        void missingOneLetter() {
            assertThat(isPangram("the quick brown fox jumps over the lay dog")).isFalse();
        }
    }
}
