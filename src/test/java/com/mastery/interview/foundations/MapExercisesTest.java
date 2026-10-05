package com.mastery.interview.foundations;

import static com.mastery.interview.foundations.MapExercises.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MapExercisesTest {

    @Nested
    class E01AgeOf {
        @Test
        void presentPerson() {
            assertThat(ageOf(Map.of("Ada", 36), "Ada")).isEqualTo(36);
        }

        @Test
        void absentPersonIsMinusOne() {
            assertThat(ageOf(Map.of("Ada", 36), "Bob")).isEqualTo(-1);
        }
    }

    @Nested
    class E02AddPerson {
        @Test
        void addsNewPerson() {
            Map<String, Integer> ages = new HashMap<>();
            addPerson(ages, "Bob", 30);
            assertThat(ages).containsExactly(entry("Bob", 30));
        }

        @Test
        void replacesExistingAge() {
            Map<String, Integer> ages = new HashMap<>(Map.of("Bob", 30));
            addPerson(ages, "Bob", 31);
            assertThat(ages).containsExactly(entry("Bob", 31));
        }
    }

    @Nested
    class E03Birthday {
        @Test
        void addsOneYear() {
            Map<String, Integer> ages = new HashMap<>(Map.of("Ada", 36, "Alan", 41));
            birthday(ages, "Ada");
            assertThat(ages).containsOnly(entry("Ada", 37), entry("Alan", 41));
        }

        @Test
        void absentPersonChangesNothing() {
            Map<String, Integer> ages = new HashMap<>(Map.of("Ada", 36));
            birthday(ages, "Bob");
            assertThat(ages).containsOnly(entry("Ada", 36));
        }
    }

    @Nested
    class E04TotalAge {
        @Test
        void sumsValues() {
            assertThat(totalAge(Map.of("Ada", 36, "Alan", 41))).isEqualTo(77);
        }

        @Test
        void emptyIsZero() {
            assertThat(totalAge(Map.of())).isZero();
        }
    }

    @Nested
    class E05CharCount {
        @Test
        void countsEachCharacter() {
            assertThat(charCount("banana")).containsOnly(entry('a', 3), entry('b', 1), entry('n', 2));
        }

        @Test
        void emptyString() {
            assertThat(charCount("")).isEmpty();
        }
    }

    @Nested
    class E06WordCount {
        @Test
        void countsEachWord() {
            assertThat(wordCount("to be or not to be"))
                    .containsOnly(entry("to", 2), entry("be", 2), entry("or", 1), entry("not", 1));
        }

        @Test
        void singleWord() {
            assertThat(wordCount("java")).containsOnly(entry("java", 1));
        }
    }

    @Nested
    class E07OlderThan {
        @Test
        void strictlyOlderAndSorted() {
            Map<String, Integer> ages = Map.of("Ada", 36, "Grace", 85, "Alan", 41, "Linus", 40);
            assertThat(olderThan(ages, 40)).containsExactly("Alan", "Grace");
        }

        @Test
        void nobodyOlder() {
            assertThat(olderThan(Map.of("Ada", 36), 99)).isEmpty();
        }
    }

    @Nested
    class E08Invert {
        @Test
        void swapsKeysAndValues() {
            assertThat(invert(Map.of("fr", "France", "it", "Italy")))
                    .containsOnly(entry("France", "fr"), entry("Italy", "it"));
        }

        @Test
        void emptyMap() {
            assertThat(invert(Map.of())).isEmpty();
        }
    }

    @Nested
    class E09GroupByLength {
        @Test
        void groupsAndKeepsOrder() {
            assertThat(groupByLength(List.of("hi", "hey", "yo")))
                    .containsOnly(entry(2, List.of("hi", "yo")), entry(3, List.of("hey")));
        }

        @Test
        void emptyList() {
            assertThat(groupByLength(List.of())).isEmpty();
        }
    }

    @Nested
    class E10FirstUniqueChar {
        @Test
        void findsFirstUnique() {
            assertThat(firstUniqueChar("swiss")).isEqualTo('w');
        }

        @Test
        void uniqueAtTheEnd() {
            assertThat(firstUniqueChar("aabbc")).isEqualTo('c');
        }

        @Test
        void noneGivesUnderscore() {
            assertThat(firstUniqueChar("aabb")).isEqualTo('_');
        }
    }
}
