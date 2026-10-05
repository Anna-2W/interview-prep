# F01. String

🇬🇧 [English version](../../en/foundations/01-string.md)

## 1. C'est quoi

Un `String` est une suite de caractères. Chaque caractère a un **indice** qui commence à **0**.

```
  h  e  l  l  o
  0  1  2  3  4      length() = 5, dernier indice = 4
```

Un `String` est **immuable** : aucune méthode ne le modifie, elles renvoient toutes une
nouvelle chaîne.

```java
String s = "hello";
s.toUpperCase();        // s vaut toujours "hello"
s = s.toUpperCase();    // s vaut maintenant "HELLO"
```

## 2. Créer un String

| Code | Résultat |
|---|---|
| `"hello"` | `"hello"` |
| `new String("hello")` | `"hello"` (objet en plus, à éviter) |
| `"Age: " + 30` | `"Age: 30"` |
| `String.valueOf(42)` | `"42"` |

## 3. Toutes les méthodes de `String`

### Longueur et accès

| Méthode | Exemple | Résultat |
|---|---|---|
| `length()` | `"hello".length()` | `5` |
| `isEmpty()` | `"".isEmpty()` | `true` |
| `isBlank()` | `"  ".isBlank()` | `true` |
| `charAt(i)` | `"hello".charAt(1)` | `'e'` |
| `chars()` | `"abc".chars().toArray()` | `[97, 98, 99]` |
| `codePointAt(i)` | `"hello".codePointAt(0)` | `104` |
| `codePointBefore(i)` | `"hello".codePointBefore(1)` | `104` |
| `codePointCount(a, b)` | `"hello".codePointCount(0, 5)` | `5` |
| `codePoints()` | `"ab".codePoints().toArray()` | `[97, 98]` |
| `offsetByCodePoints(i, n)` | `"hello".offsetByCodePoints(0, 2)` | `2` |

### Comparer

| Méthode | Exemple | Résultat |
|---|---|---|
| `equals(o)` | `"a".equals("a")` | `true` |
| `equalsIgnoreCase(o)` | `"Java".equalsIgnoreCase("JAVA")` | `true` |
| `contentEquals(cs)` | `"abc".contentEquals(new StringBuilder("abc"))` | `true` |
| `compareTo(o)` | `"apple".compareTo("banana")` | `-1` (négatif : avant) |
| `compareToIgnoreCase(o)` | `"a".compareToIgnoreCase("A")` | `0` |
| `startsWith(s)` | `"hello".startsWith("he")` | `true` |
| `startsWith(s, from)` | `"hello".startsWith("ll", 2)` | `true` |
| `endsWith(s)` | `"hello".endsWith("lo")` | `true` |
| `regionMatches(i, other, j, len)` | `"Hello World".regionMatches(6, "World", 0, 5)` | `true` |
| `regionMatches(true, i, other, j, len)` | `"Hello World".regionMatches(true, 6, "WORLD", 0, 5)` | `true` |
| `matches(regex)` | `"abc123".matches("[a-z]+\\d+")` | `true` |
| `hashCode()` | `"hi".hashCode()` | `3329` |

### Chercher

| Méthode | Exemple | Résultat |
|---|---|---|
| `contains(s)` | `"hello".contains("ell")` | `true` |
| `indexOf(c)` | `"banana".indexOf('a')` | `1` |
| `indexOf(s)` | `"banana".indexOf("an")` | `1` |
| `indexOf(c, from)` | `"banana".indexOf('a', 2)` | `3` |
| `indexOf(c, from, to)` | `"banana".indexOf('a', 2, 4)` | `3` |
| `lastIndexOf(c)` | `"banana".lastIndexOf('a')` | `5` |
| `lastIndexOf(c, from)` | `"banana".lastIndexOf('a', 4)` | `3` |
| `lastIndexOf(s)` | `"banana".lastIndexOf("an")` | `3` |

Pas trouvé : `indexOf` et `lastIndexOf` renvoient `-1`.

### Extraire

| Méthode | Exemple | Résultat |
|---|---|---|
| `substring(a)` | `"hello".substring(2)` | `"llo"` |
| `substring(a, b)` | `"hello".substring(1, 3)` | `"el"` (b exclu) |
| `subSequence(a, b)` | `"hello".subSequence(1, 3)` | `"el"` |

### Transformer

| Méthode | Exemple | Résultat |
|---|---|---|
| `toUpperCase()` | `"Hi".toUpperCase()` | `"HI"` |
| `toLowerCase()` | `"Hi".toLowerCase()` | `"hi"` |
| `trim()` | `" hi ".trim()` | `"hi"` |
| `strip()` | `" hi ".strip()` | `"hi"` |
| `stripLeading()` | `" hi ".stripLeading()` | `"hi "` |
| `stripTrailing()` | `" hi ".stripTrailing()` | `" hi"` |
| `replace(c1, c2)` | `"banana".replace('a', 'o')` | `"bonono"` |
| `replace(s1, s2)` | `"banana".replace("na", "NA")` | `"baNANA"` |
| `replaceAll(regex, s)` | `"a1b22".replaceAll("\\d+", "#")` | `"a#b#"` |
| `replaceFirst(regex, s)` | `"a1b22".replaceFirst("\\d+", "#")` | `"a#b22"` |
| `concat(s)` | `"hi".concat("!")` | `"hi!"` |
| `repeat(n)` | `"ab".repeat(3)` | `"ababab"` |
| `indent(n)` | `"hi".indent(2)` | `"  hi\n"` |
| `stripIndent()` | `"  a\n  b".stripIndent()` | `"a\nb"` |
| `translateEscapes()` | `"a\\tb".translateEscapes()` | `"a<tabulation>b"` |
| `transform(f)` | `"42".transform(Integer::parseInt)` | `42` |
| `formatted(args)` | `"%s is %d".formatted("Ada", 36)` | `"Ada is 36"` |
| `intern()` | `new String("hi").intern() == "hi"` | `true` |

### Découper et joindre

| Méthode | Exemple | Résultat |
|---|---|---|
| `split(regex)` | `"a,b,,c".split(",")` | `["a", "b", "", "c"]` |
| `split(regex, limit)` | `"a,b,c".split(",", 2)` | `["a", "b,c"]` |
| `splitWithDelimiters(regex, limit)` | `"a1b2".splitWithDelimiters("\\d", 0)` | `["a", "1", "b", "2"]` |
| `lines()` | `"a\nb".lines().toList()` | `["a", "b"]` |
| `String.join(sep, a, b...)` | `String.join("-", "a", "b")` | `"a-b"` |
| `String.join(sep, list)` | `String.join(", ", List.of("x", "y"))` | `"x, y"` |

### Convertir

| Méthode | Exemple | Résultat |
|---|---|---|
| `toCharArray()` | `"hi".toCharArray()` | `['h', 'i']` |
| `getChars(a, b, dst, i)` | `"hey".getChars(0, 2, dst, 1)` avec `dst = new char[3]` | `dst = ['\0', 'h', 'e']` |
| `getBytes()` | `"hi".getBytes()` | `[104, 105]` |
| `getBytes(charset)` | `"é".getBytes(StandardCharsets.UTF_8).length` | `2` |
| `toString()` | `"hi".toString()` | `"hi"` |
| `String.valueOf(x)` | `String.valueOf(42)`, `String.valueOf(true)` | `"42"`, `"true"` |
| `String.valueOf(chars)` | `String.valueOf(new char[] {'h', 'i'})` | `"hi"` |
| `String.valueOf(chars, from, n)` | `String.valueOf(new char[] {'h', 'i'}, 1, 1)` | `"i"` |
| `String.copyValueOf(chars)` | `String.copyValueOf(new char[] {'h', 'i'})` | `"hi"` |
| `String.format(fmt, args)` | `String.format("%.2f", 3.14159)` | `"3,14"` sur une machine en français |
| `describeConstable()` | `"Hi".describeConstable()` | `Optional[Hi]` |
| `resolveConstantDesc(lookup)` | utilisé en interne par la JVM | jamais dans du code d'appli |

### Pas dans `String` mais indispensable : `Integer.parseInt` et `Character`

| Méthode | Exemple | Résultat |
|---|---|---|
| `Integer.parseInt(s)` | `Integer.parseInt("42")` | `42` |
| `Character.isLetter(c)` | `Character.isLetter('a')` | `true` |
| `Character.isDigit(c)` | `Character.isDigit('7')` | `true` |
| `Character.isLetterOrDigit(c)` | `Character.isLetterOrDigit('_')` | `false` |
| `Character.isWhitespace(c)` | `Character.isWhitespace(' ')` | `true` |
| `Character.isUpperCase(c)` | `Character.isUpperCase('A')` | `true` |
| `Character.toUpperCase(c)` | `Character.toUpperCase('a')` | `'A'` |
| `Character.toLowerCase(c)` | `Character.toLowerCase('A')` | `'a'` |
| `Character.getNumericValue(c)` | `Character.getNumericValue('7')` | `7` |

## 4. Toutes les méthodes de `StringBuilder`

`StringBuilder` est une chaîne **modifiable**. On l'utilise pour construire une chaîne
dans une boucle : `s = s + x` dans une boucle coûte O(n²), `sb.append(x)` coûte O(n) au total.

Chaque exemple part de `sb = new StringBuilder("hello")`.

| Méthode | Exemple | Résultat |
|---|---|---|
| `append(x)` | `sb.append("!")` | `"hello!"` |
| `appendCodePoint(cp)` | `sb.appendCodePoint(33)` | `"hello!"` |
| `insert(i, x)` | `sb.insert(0, ">")` | `">hello"` |
| `delete(a, b)` | `sb.delete(1, 3)` | `"hlo"` |
| `deleteCharAt(i)` | `sb.deleteCharAt(0)` | `"ello"` |
| `replace(a, b, s)` | `sb.replace(0, 2, "J")` | `"Jllo"` |
| `reverse()` | `sb.reverse()` | `"olleh"` |
| `setCharAt(i, c)` | `sb.setCharAt(0, 'j')` | `"jello"` |
| `setLength(n)` | `sb.setLength(2)` | `"he"` |
| `repeat(s, n)` | `new StringBuilder().repeat("ab", 2)` | `"abab"` |
| `charAt(i)` | `sb.charAt(1)` | `'e'` |
| `length()` | `sb.length()` | `5` |
| `indexOf(s)` | `sb.indexOf("l")` | `2` |
| `lastIndexOf(s)` | `sb.lastIndexOf("l")` | `3` |
| `substring(a)` | `sb.substring(1)` | `"ello"` |
| `substring(a, b)` | `sb.substring(1, 3)` | `"el"` |
| `subSequence(a, b)` | `sb.subSequence(1, 3)` | `"el"` |
| `compareTo(other)` | `sb.compareTo(new StringBuilder("help"))` | `-4` |
| `chars()` | `sb.chars().count()` | `5` |
| `codePointAt(i)` | `sb.codePointAt(0)` | `104` |
| `getChars(a, b, dst, i)` | pareil que `String` | |
| `capacity()` | `sb.capacity()` | `21` |
| `ensureCapacity(n)` | `sb.ensureCapacity(100)` | la capacité passe à au moins 100 |
| `trimToSize()` | `sb.trimToSize()` | la capacité passe à 5 |
| `toString()` | `sb.toString()` | `"hello"` |

## 5. Parcourir une chaîne

```java
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
}

for (char c : s.toCharArray()) {
}
```

## 6. En entretien

- Compter les caractères : une `Map` (F04) ou un `int[26]` avec `count[c - 'a']++`.
- Construire la réponse : `StringBuilder`.
- Problèmes classiques : palindrome, anagramme, inverser les mots, premier caractère unique.

## 7. Pièges

| Piège | Faux | Juste |
|---|---|---|
| Comparer du texte | `a == b` | `a.equals(b)` |
| Dernier caractère | `s.charAt(s.length())` | `s.charAt(s.length() - 1)` |
| Garder le résultat | `s.trim();` | `s = s.trim();` |
| `null` possible | `s.equals("x")` | `"x".equals(s)` |
| Construire dans une boucle | `s = s + x` | `sb.append(x)` |
| Fin de `substring` | `"hello".substring(0, 2)` ne vaut pas `"hel"` | il vaut `"he"` |
| `char` est un nombre | `'a' + 1` donne `98` | `(char) ('a' + 1)` donne `'b'` |
| Chiffre vers int | `(int) '7'` donne `55` | `'7' - '0'` donne `7` |
| `split` et regex | `"a.b".split(".")` donne `[]` | `"a.b".split("\\.")` |
| `format` et langue | `String.format("%.2f", 3.14159)` donne `"3,14"` sur une machine en français | `String.format(Locale.US, "%.2f", 3.14159)` |

## 8. Exercices

Fichier : [`StringExercises.java`](../../../src/main/java/com/mastery/interview/foundations/StringExercises.java)

```bash
mvn -Dtest='StringExercisesTest$E01LastChar' test
mvn -Dtest=StringExercisesTest test
```

| # | Exercice | Exemple |
|---|---|---|
| 01 | Dernier caractère | `lastChar("hello")` → `'o'` |
| 02 | Majuscules + `!` | `shout("hello")` → `"HELLO!"` |
| 03 | Nom complet sans espaces en trop | `fullName("  ada ", "lovelace ")` → `"ada lovelace"` |
| 04 | Même texte, casse ignorée | `sameText("Java", "JAVA")` → `true` |
| 05 | Compter un caractère | `countChar("banana", 'a')` → `3` |
| 06 | Inverser | `reverse("abc")` → `"cba"` |
| 07 | Palindrome | `isPalindrome("kayak")` → `true` |
| 08 | Compter les voyelles | `countVowels("Interview")` → `4` |
| 09 | Initiales | `initials("Ada Lovelace")` → `"AL"` |
| 10 | Slug d'URL | `slug("  Hello World  ")` → `"hello-world"` |

---

⬅️ [Sommaire](../../README.fr.md) · [README](../../../README.fr.md)
