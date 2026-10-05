package com.mastery.interview.foundations;

import static com.mastery.interview.foundations.StringExercises.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StringExercisesTest {

    @Nested
    class E01LastChar {
        @Test
        void returnsLastCharacter() {
            assertThat(lastChar("hello")).isEqualTo('o');
        }

        @Test
        void worksWithOneCharacter() {
            assertThat(lastChar("a")).isEqualTo('a');
        }
    }

    @Nested
    class E02Shout {
        @Test
        void upperCasesAndAddsExclamationMark() {
            assertThat(shout("hello")).isEqualTo("HELLO!");
        }

        @Test
        void keepsDigitsAndSpaces() {
            assertThat(shout("Java 21")).isEqualTo("JAVA 21!");
        }
    }

    @Nested
    class E03FullName {
        @Test
        void removesExtraSpaces() {
            assertThat(fullName("  ada ", "lovelace ")).isEqualTo("ada lovelace");
        }

        @Test
        void worksWithCleanInput() {
            assertThat(fullName("Alan", "Turing")).isEqualTo("Alan Turing");
        }
    }

    @Nested
    class E04SameText {
        @Test
        void ignoresCase() {
            assertThat(sameText("Java", "JAVA")).isTrue();
        }

        @Test
        void differentTextIsFalse() {
            assertThat(sameText("Java", "Jav")).isFalse();
        }

        @Test
        void comparesContentNotReference() {
            assertThat(sameText(new String("hi"), new String("hi"))).isTrue();
        }
    }

    @Nested
    class E05CountChar {
        @Test
        void countsOccurrences() {
            assertThat(countChar("banana", 'a')).isEqualTo(3);
        }

        @Test
        void zeroWhenAbsent() {
            assertThat(countChar("banana", 'z')).isZero();
        }

        @Test
        void zeroOnEmptyString() {
            assertThat(countChar("", 'a')).isZero();
        }
    }

    @Nested
    class E06Reverse {
        @Test
        void reversesCharacters() {
            assertThat(reverse("abc")).isEqualTo("cba");
        }

        @Test
        void emptyStaysEmpty() {
            assertThat(reverse("")).isEmpty();
        }

        @Test
        void handlesLongInputFast() {
            String big = "ab".repeat(100_000);
            assertThat(reverse(big)).isEqualTo("ba".repeat(100_000));
        }
    }

    @Nested
    class E07IsPalindrome {
        @Test
        void oddLengthPalindrome() {
            assertThat(isPalindrome("kayak")).isTrue();
        }

        @Test
        void evenLengthPalindrome() {
            assertThat(isPalindrome("abba")).isTrue();
        }

        @Test
        void notAPalindrome() {
            assertThat(isPalindrome("java")).isFalse();
        }

        @Test
        void caseMatters() {
            assertThat(isPalindrome("Kayak")).isFalse();
        }

        @Test
        void emptyAndSingleArePalindromes() {
            assertThat(isPalindrome("")).isTrue();
            assertThat(isPalindrome("x")).isTrue();
        }
    }

    @Nested
    class E08CountVowels {
        @Test
        void countsUpperAndLowerCase() {
            assertThat(countVowels("Interview")).isEqualTo(4);
        }

        @Test
        void zeroWithoutVowels() {
            assertThat(countVowels("xyz")).isZero();
        }

        @Test
        void allVowels() {
            assertThat(countVowels("AEIOUaeiou")).isEqualTo(10);
        }
    }

    @Nested
    class E09Initials {
        @Test
        void twoWords() {
            assertThat(initials("Ada Lovelace")).isEqualTo("AL");
        }

        @Test
        void lowerCaseWordsGiveUpperCaseInitials() {
            assertThat(initials("grace brewster hopper")).isEqualTo("GBH");
        }

        @Test
        void oneWord() {
            assertThat(initials("linus")).isEqualTo("L");
        }
    }

    @Nested
    class E10Slug {
        @Test
        void stripsLowersAndReplacesSpaces() {
            assertThat(slug("  Hello World  ")).isEqualTo("hello-world");
        }

        @Test
        void severalWords() {
            assertThat(slug("Java Interview Prep")).isEqualTo("java-interview-prep");
        }

        @Test
        void oneWord() {
            assertThat(slug("Java")).isEqualTo("java");
        }
    }
}
