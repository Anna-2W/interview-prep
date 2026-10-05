package com.mastery.interview.foundations;

// F01 String: book/en/foundations/01-string.md
public final class StringExercises {

    private StringExercises() {
    }

    // E01  Return the last character of s. s is never empty.
    //      Renvoyer le dernier caractère de s. s n'est jamais vide.
    //      lastChar("hello") -> 'o'
    public static char lastChar(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Return s in upper case followed by "!".
    //      Renvoyer s en majuscules suivi de "!".
    //      shout("hello") -> "HELLO!"
    public static String shout(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  Join first and last with one space, after removing the spaces around each of them.
    //      Joindre first et last avec un espace, après avoir enlevé les espaces autour de chacun.
    //      fullName("  ada ", "lovelace ") -> "ada lovelace"
    public static String fullName(String first, String last) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return true if a and b are the same text, ignoring upper / lower case.
    //      Renvoyer true si a et b sont le même texte, sans tenir compte des majuscules / minuscules.
    //      sameText("Java", "JAVA") -> true    sameText("Java", "Jav") -> false
    public static boolean sameText(String a, String b) {
        throw new UnsupportedOperationException("TODO");
    }

    // E05  Count how many times c appears in s. Use a loop.
    //      Compter combien de fois c apparaît dans s. Utiliser une boucle.
    //      countChar("banana", 'a') -> 3
    public static int countChar(String s, char c) {
        throw new UnsupportedOperationException("TODO");
    }

    // E06  Return s written backwards.
    //      Renvoyer s écrit à l'envers.
    //      reverse("abc") -> "cba"
    public static String reverse(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E07  Return true if s reads the same from both ends. Case matters. Try without reverse().
    //      Renvoyer true si s se lit pareil dans les deux sens. La casse compte. Essayer sans reverse().
    //      isPalindrome("kayak") -> true    isPalindrome("Kayak") -> false    isPalindrome("") -> true
    public static boolean isPalindrome(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E08  Count the vowels a, e, i, o, u in s, upper or lower case.
    //      Compter les voyelles a, e, i, o, u dans s, majuscules ou minuscules.
    //      countVowels("Interview") -> 4
    public static int countVowels(String s) {
        throw new UnsupportedOperationException("TODO");
    }

    // E09  Return the first letter of each word, in upper case. Words are separated by one space.
    //      Renvoyer la première lettre de chaque mot, en majuscule. Les mots sont séparés par un espace.
    //      initials("Ada Lovelace") -> "AL"    initials("grace brewster hopper") -> "GBH"
    public static String initials(String fullName) {
        throw new UnsupportedOperationException("TODO");
    }

    // E10  Turn the title into a URL slug: no spaces at the ends, lower case, spaces become "-".
    //      Transformer le titre en slug d'URL : pas d'espaces aux bouts, minuscules, les espaces deviennent "-".
    //      slug("  Hello World  ") -> "hello-world"
    public static String slug(String title) {
        throw new UnsupportedOperationException("TODO");
    }
}
