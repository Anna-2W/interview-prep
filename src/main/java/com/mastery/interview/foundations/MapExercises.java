package com.mastery.interview.foundations;

import java.util.List;
import java.util.Map;

/**
 * Chapter F04: Map. Theory / théorie : book/en/foundations/04-map.md
 *
 * <p>Run / lancer : {@code mvn -Dtest=MapExercisesTest test}
 */
public final class MapExercises {

    private MapExercises() {
    }

    /**
     * E01. EN: Return the age of the person, or -1 if the person is not in the map.
     * FR : Renvoyer l'âge de la personne, ou -1 si elle n'est pas dans la map.
     *
     * <pre>ageOf({Ada=36}, "Ada") -> 36     ageOf({Ada=36}, "Bob") -> -1</pre>
     */
    public static int ageOf(Map<String, Integer> ages, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E02. EN: Add the person with their age (modify the map). If already present, replace the age.
     * FR : Ajouter la personne avec son âge (modifier la map). Si elle existe déjà, remplacer l'âge.
     *
     * <pre>ages = {}; addPerson(ages, "Bob", 30); ages -> {Bob=30}</pre>
     */
    public static void addPerson(Map<String, Integer> ages, String name, int age) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E03. EN: Add 1 to the age of the person (modify the map). If the person is absent, change nothing.
     * FR : Ajouter 1 à l'âge de la personne (modifier la map). Si elle est absente, ne rien changer.
     *
     * <pre>ages = {Ada=36}; birthday(ages, "Ada"); ages -> {Ada=37}</pre>
     */
    public static void birthday(Map<String, Integer> ages, String name) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E04. EN: Return the sum of all ages. Empty map gives 0.
     * FR : Renvoyer la somme de tous les âges. Une map vide donne 0.
     *
     * <pre>totalAge({Ada=36, Alan=41}) -> 77</pre>
     */
    public static int totalAge(Map<String, Integer> ages) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E05. EN: Count how many times each character appears.
     * FR : Compter combien de fois chaque caractère apparaît.
     *
     * <pre>charCount("banana") -> {a=3, b=1, n=2}     charCount("") -> {}</pre>
     */
    public static Map<Character, Integer> charCount(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E06. EN: Count how many times each word appears. Words are separated by one space.
     * FR : Compter combien de fois chaque mot apparaît. Les mots sont séparés par un espace.
     *
     * <pre>wordCount("to be or not to be") -> {to=2, be=2, or=1, not=1}</pre>
     */
    public static Map<String, Integer> wordCount(String sentence) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E07. EN: Return the names of people strictly older than minAge, sorted alphabetically.
     * FR : Renvoyer les noms des personnes strictement plus âgées que minAge, triés par ordre alphabétique.
     *
     * <pre>olderThan({Ada=36, Grace=85, Alan=41}, 40) -> ["Alan", "Grace"]</pre>
     */
    public static List<String> olderThan(Map<String, Integer> ages, int minAge) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E08. EN: Return a new map where keys become values and values become keys. Values are all different.
     * FR : Renvoyer une nouvelle map où les clés deviennent les valeurs et inversement. Les valeurs sont toutes différentes.
     *
     * <pre>invert({fr=France, it=Italy}) -> {France=fr, Italy=it}</pre>
     */
    public static Map<String, String> invert(Map<String, String> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E09. EN: Group the words by their length. Inside a group, keep the input order.
     * FR : Regrouper les mots par longueur. Dans un groupe, garder l'ordre d'entrée.
     *
     * <pre>groupByLength(["hi", "hey", "yo"]) -> {2=[hi, yo], 3=[hey]}</pre>
     */
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E10. EN: Return the first character that appears only once, or '_' if there is none.
     * FR : Renvoyer le premier caractère qui n'apparaît qu'une fois, ou '_' s'il n'y en a pas.
     *
     * <pre>firstUniqueChar("swiss") -> 'w'     firstUniqueChar("aabb") -> '_'</pre>
     */
    public static char firstUniqueChar(String s) {
        throw new UnsupportedOperationException("TODO");
    }
}
