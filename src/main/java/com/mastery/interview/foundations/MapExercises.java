package com.mastery.interview.foundations;

import java.util.List;
import java.util.Map;

// F04 Map: book/en/foundations/04-map.md
public final class MapExercises {

    private MapExercises() {
    }

    // E01  ageOf({Ada=36}, "Ada") -> 36    ageOf({Ada=36}, "Bob") -> -1
    public static int ageOf(Map<String, Integer> ages, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  ages = {}; addPerson(ages, "Bob", 30); ages -> {Bob=30}
    public static void addPerson(Map<String, Integer> ages, String name, int age) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  ages = {Ada=36}; birthday(ages, "Ada"); ages -> {Ada=37}    birthday(ages, "Bob") -> no change
    public static void birthday(Map<String, Integer> ages, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  totalAge({Ada=36, Alan=41}) -> 77
    public static int totalAge(Map<String, Integer> ages) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  charCount("banana") -> {a=3, b=1, n=2}
    public static Map<Character, Integer> charCount(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  wordCount("to be or not to be") -> {to=2, be=2, or=1, not=1}
    public static Map<String, Integer> wordCount(String sentence) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  olderThan({Ada=36, Grace=85, Alan=41}, 40) -> ["Alan", "Grace"]
    public static List<String> olderThan(Map<String, Integer> ages, int minAge) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  invert({fr=France, it=Italy}) -> {France=fr, Italy=it}
    public static Map<String, String> invert(Map<String, String> map) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  groupByLength(["hi", "hey", "yo"]) -> {2=[hi, yo], 3=[hey]}
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  firstUniqueChar("swiss") -> 'w'    firstUniqueChar("aabb") -> '_'
    public static char firstUniqueChar(String s) {
        throw new UnsupportedOperationException("TODO");
    }
}
