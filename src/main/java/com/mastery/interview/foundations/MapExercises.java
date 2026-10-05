package com.mastery.interview.foundations;

import java.util.List;
import java.util.Map;

// F04 Map: book/en/foundations/04-map.md
public final class MapExercises {

    private MapExercises() {
    }

    // E01  Return the age of name, or -1 if name is not in the map.
    //      Renvoyer l'âge de name, ou -1 si name n'est pas dans la map.
    //      ageOf({Ada=36}, "Ada") -> 36    ageOf({Ada=36}, "Bob") -> -1
    public static int ageOf(Map<String, Integer> ages, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Add name with its age (change the map). If already there, replace the age.
    //      Ajouter name avec son âge (modifier la map). S'il existe déjà, remplacer l'âge.
    //      ages = {}; addPerson(ages, "Bob", 30); ages -> {Bob=30}
    public static void addPerson(Map<String, Integer> ages, String name, int age) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Add 1 to the age of name. If name is absent, change nothing.
    //      Ajouter 1 à l'âge de name. Si name est absent, ne rien changer.
    //      ages = {Ada=36}; birthday(ages, "Ada"); ages -> {Ada=37}    birthday(ages, "Bob") -> no change
    public static void birthday(Map<String, Integer> ages, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return the sum of all ages. Empty map gives 0.
    //      Renvoyer la somme de tous les âges. Une map vide donne 0.
    //      totalAge({Ada=36, Alan=41}) -> 77
    public static int totalAge(Map<String, Integer> ages) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Return how many times each character appears in s.
    //      Renvoyer combien de fois chaque caractère apparaît dans s.
    //      charCount("banana") -> {a=3, b=1, n=2}
    public static Map<Character, Integer> charCount(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Return how many times each word appears. Words are separated by one space.
    //      Renvoyer combien de fois chaque mot apparaît. Les mots sont séparés par un espace.
    //      wordCount("to be or not to be") -> {to=2, be=2, or=1, not=1}
    public static Map<String, Integer> wordCount(String sentence) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  Return the names of people strictly older than minAge, sorted alphabetically.
    //      Renvoyer les noms des personnes strictement plus âgées que minAge, triés par ordre alphabétique.
    //      olderThan({Ada=36, Grace=85, Alan=41}, 40) -> ["Alan", "Grace"]
    public static List<String> olderThan(Map<String, Integer> ages, int minAge) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  Return a new map where keys become values and values become keys.
    //      Renvoyer une nouvelle map où les clés deviennent les valeurs et inversement.
    //      invert({fr=France, it=Italy}) -> {France=fr, Italy=it}
    public static Map<String, String> invert(Map<String, String> map) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  Group the words by length. Inside a group, keep the input order.
    //      Regrouper les mots par longueur. Dans un groupe, garder l'ordre d'entrée.
    //      groupByLength(["hi", "hey", "yo"]) -> {2=[hi, yo], 3=[hey]}
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  Return the first character that appears only once in s, or '_' if none.
    //      Renvoyer le premier caractère qui n'apparaît qu'une fois dans s, ou '_' s'il n'y en a pas.
    //      firstUniqueChar("swiss") -> 'w'    firstUniqueChar("aabb") -> '_'
    public static char firstUniqueChar(String s) {
        throw new UnsupportedOperationException("TODO");
    }
}
