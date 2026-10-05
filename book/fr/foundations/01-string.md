# F01. String

🇬🇧 [English version](../../en/foundations/01-string.md)

## 1. C'est quoi

Un `String` est une suite de caractères : `"hello"`, `"Ada Lovelace"`, `""` (vide).
Chaque caractère a une position appelée **indice**, qui commence à **0**.

```
 "hello"
  h  e  l  l  o
  0  1  2  3  4      length() = 5, dernier indice = length() - 1 = 4
```

**La règle clé : un `String` est immuable.** Aucune méthode ne le modifie. Les méthodes
comme `toUpperCase()` renvoient une **nouvelle** chaîne.

```java
String s = "hello";
s.toUpperCase();          // crée "HELLO" et le jette
System.out.println(s);    // hello
s = s.toUpperCase();      // on garde le résultat
System.out.println(s);    // HELLO
```

## 2. Créer un String

```java
String a = "hello";                       // littéral (la façon normale)
String b = new String("hello");           // à éviter : crée un objet en plus pour rien
String c = String.valueOf(42);            // "42"
String d = "Age: " + 30;                  // "Age: 30"
String e = String.join("-", "a", "b");    // "a-b"
```

## 3. Les méthodes à connaître

| Méthode | Ce qu'elle fait | Exemple | Temps |
|---|---|---|---|
| `length()` | Nombre de caractères | `"hello".length()` → `5` | O(1) |
| `charAt(i)` | Caractère à l'indice `i` | `"hello".charAt(1)` → `'e'` | O(1) |
| `isEmpty()` | Longueur 0 | `"".isEmpty()` → `true` | O(1) |
| `isBlank()` | Que des espaces (ou vide) | `"  ".isBlank()` → `true` | O(n) |
| `equals(o)` | Mêmes caractères | `"a".equals("a")` → `true` | O(n) |
| `equalsIgnoreCase(o)` | Pareil, sans tenir compte de la casse | `"A".equalsIgnoreCase("a")` → `true` | O(n) |
| `compareTo(o)` | Ordre alphabétique (<0, 0, >0) | `"a".compareTo("b")` → `-1` | O(n) |
| `contains(s)` | Contient un morceau de texte | `"hello".contains("ell")` → `true` | O(n·m) |
| `indexOf(s)` | Première position, ou `-1` | `"hello".indexOf('l')` → `2` | O(n·m) |
| `lastIndexOf(s)` | Dernière position, ou `-1` | `"hello".lastIndexOf('l')` → `3` | O(n·m) |
| `startsWith(s)` / `endsWith(s)` | Commence / finit par | `"hello".startsWith("he")` → `true` | O(m) |
| `substring(a, b)` | De l'indice `a` à `b` (**b exclu**) | `"hello".substring(1, 3)` → `"el"` | O(n) |
| `toUpperCase()` / `toLowerCase()` | Change la casse | `"Hi".toUpperCase()` → `"HI"` | O(n) |
| `trim()` / `strip()` | Enlève les espaces aux deux bouts | `" hi ".strip()` → `"hi"` | O(n) |
| `replace(a, b)` | Remplace chaque `a` par `b` | `"a-b".replace("-", "+")` → `"a+b"` | O(n) |
| `split(regex)` | Découpe en tableau | `"a b".split(" ")` → `["a", "b"]` | O(n) |
| `toCharArray()` | Copie dans un `char[]` | `"hi".toCharArray()` → `['h', 'i']` | O(n) |
| `repeat(n)` | Répète n fois | `"ab".repeat(3)` → `"ababab"` | O(n·k) |
| `String.valueOf(x)` | N'importe quoi vers String | `String.valueOf(3.5)` → `"3.5"` | O(n) |
| `Integer.parseInt(s)` | String vers int | `Integer.parseInt("42")` → `42` | O(n) |

n = longueur de la chaîne, m = longueur du texte cherché.

### Méthodes utiles de `Character`

| Méthode | Exemple |
|---|---|
| `Character.isLetter(c)` | `'a'` → `true`, `'1'` → `false` |
| `Character.isDigit(c)` | `'7'` → `true` |
| `Character.isWhitespace(c)` | `' '` → `true` |
| `Character.isUpperCase(c)` | `'A'` → `true` |
| `Character.toLowerCase(c)` | `'A'` → `'a'` |

## 4. `StringBuilder` : quand on construit une chaîne morceau par morceau

Comme `String` est immuable, `s = s + x` dans une boucle recopie toute la chaîne à chaque
tour : O(n²) au total. `StringBuilder` est un tampon **modifiable** : ajouter coûte O(1) en
moyenne.

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 3; i++) {
    sb.append(i).append(',');
}
sb.reverse();                 // StringBuilder sait s'inverser
String result = sb.toString();
```

| Méthode | Ce qu'elle fait |
|---|---|
| `append(x)` | Ajoute à la fin |
| `insert(i, x)` | Insère à l'indice `i` |
| `reverse()` | Inverse sur place |
| `deleteCharAt(i)` | Supprime un caractère |
| `setCharAt(i, c)` | Remplace un caractère |
| `length()` | Longueur actuelle |
| `toString()` | Récupère le `String` final |

## 5. Parcourir une chaîne

```java
String s = "hello";

for (int i = 0; i < s.length(); i++) {   // quand on a besoin de l'indice
    char c = s.charAt(i);
}

for (char c : s.toCharArray()) {          // quand on a seulement besoin du caractère
    System.out.println(c);
}
```

## 6. À quoi ça sert en entretien

- Traitement de texte : compter, inverser, tester un palindrome, trouver des mots.
- Très souvent combiné avec une **Map** (compter chaque caractère) ou un **tableau de 26
  int** (un compteur par lettre, `c - 'a'`).
- Construire une réponse : toujours avec `StringBuilder`.

## 7. Pièges

1. **`==` contre `equals`.** `==` compare les références (le même objet), `equals`
   compare le texte. Toujours `equals` pour les chaînes.
   ```java
   String a = "hi";
   String b = new String("hi");
   a == b;        // false
   a.equals(b);   // true
   ```
2. **Indice hors limites.** `s.charAt(s.length())` lève une exception. Le dernier indice
   est `length() - 1`. Et `charAt(0)` sur `""` en lève une aussi.
3. **`substring(a, b)` exclut `b`.** `"hello".substring(0, 2)` vaut `"he"`.
4. **Oublier de garder le résultat.** `s.trim();` tout seul ne fait rien.
5. **`null`.** `s.equals("x")` plante si `s` est `null`. `"x".equals(s)` est sûr.
6. **`char` est un nombre.** `'a' + 1` vaut `98`, pas `"b"`. `(char) ('a' + 1)` vaut
   `'b'`. Et `'7' - '0'` vaut `7` : la façon classique de transformer un chiffre en int.

## 8. Exercices

On code dans [`StringExercises.java`](../../../src/main/java/com/mastery/interview/foundations/StringExercises.java).
Chaque exercice a ses propres tests dans `StringExercisesTest`.

```bash
mvn -Dtest='StringExercisesTest$E01LastChar' test   # un exercice
mvn -Dtest=StringExercisesTest test                 # tout le chapitre
```

| # | Exercice | Ce que ça entraîne |
|---|---|---|
| 01 | `lastChar("hello")` → `'o'` | `charAt`, `length() - 1` |
| 02 | `shout("hello")` → `"HELLO!"` | `toUpperCase`, concaténation |
| 03 | `fullName("  ada ", "lovelace ")` → `"ada lovelace"` | `strip` |
| 04 | `sameText("Java", "JAVA")` → `true` | `equalsIgnoreCase`, jamais `==` |
| 05 | `countChar("banana", 'a')` → `3` | Boucle avec `charAt` |
| 06 | `reverse("abc")` → `"cba"` | `StringBuilder` |
| 07 | `isPalindrome("kayak")` → `true` | Comparer les deux bouts |
| 08 | `countVowels("Interview")` → `4` | `Character.toLowerCase`, `indexOf` |
| 09 | `initials("Ada Lovelace")` → `"AL"` | `split`, `charAt(0)` |
| 10 | `slug("  Hello World  ")` → `"hello-world"` | Enchaîner `strip`, `toLowerCase`, `replace` |
