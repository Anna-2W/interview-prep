package com.mastery.interview.foundations;

/**
 * Chapter F01: String. Theory / théorie : book/en/foundations/01-string.md
 *
 * <p>Replace each {@code throw new UnsupportedOperationException("TODO")} with your code.
 * Remplacer chaque {@code throw new UnsupportedOperationException("TODO")} par son code.
 *
 * <p>Run / lancer : {@code mvn -Dtest=StringExercisesTest test}
 */
public final class StringExercises {

    private StringExercises() {
    }

    /**
     * E01. EN: Return the last character. The string is never empty.
     * FR : Renvoyer le dernier caractère. La chaîne n'est jamais vide.
     *
     * <pre>lastChar("hello") -> 'o'     lastChar("a") -> 'a'</pre>
     */
    public static char lastChar(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E02. EN: Return the text in upper case followed by "!".
     * FR : Renvoyer le texte en majuscules suivi de "!".
     *
     * <pre>shout("hello") -> "HELLO!"     shout("Java 21") -> "JAVA 21!"</pre>
     */
    public static String shout(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E03. EN: Join first and last name with one space, without the extra spaces around them.
     * FR : Joindre prénom et nom avec un espace, sans les espaces en trop autour.
     *
     * <pre>fullName("  ada ", "lovelace ") -> "ada lovelace"</pre>
     */
    public static String fullName(String first, String last) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E04. EN: True if both texts are the same, ignoring upper/lower case.
     * FR : Vrai si les deux textes sont identiques, sans tenir compte de la casse.
     *
     * <pre>sameText("Java", "JAVA") -> true     sameText("Java", "Jav") -> false</pre>
     */
    public static boolean sameText(String a, String b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E05. EN: Count how many times the character appears. Use a loop.
     * FR : Compter combien de fois le caractère apparaît. Utiliser une boucle.
     *
     * <pre>countChar("banana", 'a') -> 3     countChar("banana", 'z') -> 0</pre>
     */
    public static int countChar(String s, char c) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E06. EN: Return the string reversed.
     * FR : Renvoyer la chaîne à l'envers.
     *
     * <pre>reverse("abc") -> "cba"     reverse("") -> ""</pre>
     */
    public static String reverse(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E07. EN: True if the string reads the same both ways. Case matters.
     * Try without reverse(): compare the first and last characters, then move inwards.
     * FR : Vrai si la chaîne se lit pareil dans les deux sens. La casse compte.
     * Essayer sans reverse() : comparer le premier et le dernier caractère, puis avancer vers le milieu.
     *
     * <pre>isPalindrome("kayak") -> true     isPalindrome("java") -> false     isPalindrome("") -> true</pre>
     */
    public static boolean isPalindrome(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E08. EN: Count the vowels (a, e, i, o, u), upper or lower case.
     * FR : Compter les voyelles (a, e, i, o, u), majuscules ou minuscules.
     *
     * <pre>countVowels("Interview") -> 4     countVowels("xyz") -> 0</pre>
     */
    public static int countVowels(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E09. EN: Return the first letter of each word, in upper case. Words are separated by one space.
     * FR : Renvoyer la première lettre de chaque mot, en majuscule. Les mots sont séparés par un espace.
     *
     * <pre>initials("Ada Lovelace") -> "AL"     initials("grace brewster hopper") -> "GBH"</pre>
     */
    public static String initials(String fullName) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * E10. EN: Turn a title into a URL slug: no spaces at the ends, lower case, spaces become "-".
     * FR : Transformer un titre en slug d'URL : pas d'espaces aux bouts, minuscules, les espaces deviennent "-".
     *
     * <pre>slug("  Hello World  ") -> "hello-world"     slug("Java Interview Prep") -> "java-interview-prep"</pre>
     */
    public static String slug(String title) {
        throw new UnsupportedOperationException("TODO");
    }
}
