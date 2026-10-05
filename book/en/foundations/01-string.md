# F01. String

🇫🇷 [Version française](../../fr/foundations/01-string.md)

## 1. What it is

A `String` is a sequence of characters. Each character has an **index** starting at **0**.

```
  h  e  l  l  o
  0  1  2  3  4      length() = 5, last index = 4
```

A `String` is **immutable**: no method changes it, they all return a new string.

```java
String s = "hello";
s.toUpperCase();        // s is still "hello"
s = s.toUpperCase();    // s is now "HELLO"
```

## 2. Create a String

| Code | Result |
|---|---|
| `"hello"` | `"hello"` |
| `new String("hello")` | `"hello"` (extra object, avoid) |
| `"Age: " + 30` | `"Age: 30"` |
| `String.valueOf(42)` | `"42"` |

## 3. All the methods of `String`

### Length and access

| Method | Example | Result |
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

### Compare

| Method | Example | Result |
|---|---|---|
| `equals(o)` | `"a".equals("a")` | `true` |
| `equalsIgnoreCase(o)` | `"Java".equalsIgnoreCase("JAVA")` | `true` |
| `contentEquals(cs)` | `"abc".contentEquals(new StringBuilder("abc"))` | `true` |
| `compareTo(o)` | `"apple".compareTo("banana")` | `-1` (negative: before) |
| `compareToIgnoreCase(o)` | `"a".compareToIgnoreCase("A")` | `0` |
| `startsWith(s)` | `"hello".startsWith("he")` | `true` |
| `startsWith(s, from)` | `"hello".startsWith("ll", 2)` | `true` |
| `endsWith(s)` | `"hello".endsWith("lo")` | `true` |
| `regionMatches(i, other, j, len)` | `"Hello World".regionMatches(6, "World", 0, 5)` | `true` |
| `regionMatches(true, i, other, j, len)` | `"Hello World".regionMatches(true, 6, "WORLD", 0, 5)` | `true` |
| `matches(regex)` | `"abc123".matches("[a-z]+\\d+")` | `true` |
| `hashCode()` | `"hi".hashCode()` | `3329` |

### Search

| Method | Example | Result |
|---|---|---|
| `contains(s)` | `"hello".contains("ell")` | `true` |
| `indexOf(c)` | `"banana".indexOf('a')` | `1` |
| `indexOf(s)` | `"banana".indexOf("an")` | `1` |
| `indexOf(c, from)` | `"banana".indexOf('a', 2)` | `3` |
| `indexOf(c, from, to)` | `"banana".indexOf('a', 2, 4)` | `3` |
| `lastIndexOf(c)` | `"banana".lastIndexOf('a')` | `5` |
| `lastIndexOf(c, from)` | `"banana".lastIndexOf('a', 4)` | `3` |
| `lastIndexOf(s)` | `"banana".lastIndexOf("an")` | `3` |

Not found: `indexOf` and `lastIndexOf` return `-1`.

### Extract

| Method | Example | Result |
|---|---|---|
| `substring(a)` | `"hello".substring(2)` | `"llo"` |
| `substring(a, b)` | `"hello".substring(1, 3)` | `"el"` (b excluded) |
| `subSequence(a, b)` | `"hello".subSequence(1, 3)` | `"el"` |

### Transform

| Method | Example | Result |
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
| `translateEscapes()` | `"a\\tb".translateEscapes()` | `"a<tab>b"` |
| `transform(f)` | `"42".transform(Integer::parseInt)` | `42` |
| `formatted(args)` | `"%s is %d".formatted("Ada", 36)` | `"Ada is 36"` |
| `intern()` | `new String("hi").intern() == "hi"` | `true` |

### Split and join

| Method | Example | Result |
|---|---|---|
| `split(regex)` | `"a,b,,c".split(",")` | `["a", "b", "", "c"]` |
| `split(regex, limit)` | `"a,b,c".split(",", 2)` | `["a", "b,c"]` |
| `splitWithDelimiters(regex, limit)` | `"a1b2".splitWithDelimiters("\\d", 0)` | `["a", "1", "b", "2"]` |
| `lines()` | `"a\nb".lines().toList()` | `["a", "b"]` |
| `String.join(sep, a, b...)` | `String.join("-", "a", "b")` | `"a-b"` |
| `String.join(sep, list)` | `String.join(", ", List.of("x", "y"))` | `"x, y"` |

### Convert

| Method | Example | Result |
|---|---|---|
| `toCharArray()` | `"hi".toCharArray()` | `['h', 'i']` |
| `getChars(a, b, dst, i)` | `"hey".getChars(0, 2, dst, 1)` with `dst = new char[3]` | `dst = ['\0', 'h', 'e']` |
| `getBytes()` | `"hi".getBytes()` | `[104, 105]` |
| `getBytes(charset)` | `"é".getBytes(StandardCharsets.UTF_8).length` | `2` |
| `toString()` | `"hi".toString()` | `"hi"` |
| `String.valueOf(x)` | `String.valueOf(42)`, `String.valueOf(true)` | `"42"`, `"true"` |
| `String.valueOf(chars)` | `String.valueOf(new char[] {'h', 'i'})` | `"hi"` |
| `String.valueOf(chars, from, n)` | `String.valueOf(new char[] {'h', 'i'}, 1, 1)` | `"i"` |
| `String.copyValueOf(chars)` | `String.copyValueOf(new char[] {'h', 'i'})` | `"hi"` |
| `String.format(fmt, args)` | `String.format("%.2f", 3.14159)` | `"3.14"` |
| `describeConstable()` | `"Hi".describeConstable()` | `Optional[Hi]` |
| `resolveConstantDesc(lookup)` | used by the JVM internals | never in app code |

### Not on `String` but needed: `Integer.parseInt` and `Character`

| Method | Example | Result |
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

## 4. All the methods of `StringBuilder`

`StringBuilder` is a **mutable** string. Use it to build a string in a loop:
`s = s + x` in a loop costs O(n²), `sb.append(x)` costs O(n) in total.

Every example starts from `sb = new StringBuilder("hello")`.

| Method | Example | Result |
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
| `getChars(a, b, dst, i)` | same as `String` | |
| `capacity()` | `sb.capacity()` | `21` |
| `ensureCapacity(n)` | `sb.ensureCapacity(100)` | capacity becomes at least 100 |
| `trimToSize()` | `sb.trimToSize()` | capacity becomes 5 |
| `toString()` | `sb.toString()` | `"hello"` |

## 5. Going through a string

```java
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
}

for (char c : s.toCharArray()) {
}
```

## 6. In interviews

- Count characters: a `Map` (F04) or `int[26]` with `count[c - 'a']++`.
- Build the answer: `StringBuilder`.
- Classic problems: palindrome, anagram, reverse words, first unique character.

## 7. Traps

| Trap | Wrong | Right |
|---|---|---|
| Compare text | `a == b` | `a.equals(b)` |
| Last character | `s.charAt(s.length())` | `s.charAt(s.length() - 1)` |
| Keep the result | `s.trim();` | `s = s.trim();` |
| Possible `null` | `s.equals("x")` | `"x".equals(s)` |
| Build in a loop | `s = s + x` | `sb.append(x)` |
| `substring` end | `"hello".substring(0, 2)` is not `"hel"` | it is `"he"` |
| `char` is a number | `'a' + 1` gives `98` | `(char) ('a' + 1)` gives `'b'` |
| Digit to int | `(int) '7'` gives `55` | `'7' - '0'` gives `7` |
| `split` and regex | `"a.b".split(".")` gives `[]` | `"a.b".split("\\.")` |
| `format` and locale | `String.format("%.2f", 3.14159)` gives `"3,14"` on a French machine | `String.format(Locale.US, "%.2f", 3.14159)` |

## 8. Exercises

File: [`StringExercises.java`](../../../src/main/java/com/mastery/interview/foundations/StringExercises.java)

```bash
mvn -Dtest='StringExercisesTest$E01LastChar' test
mvn -Dtest=StringExercisesTest test
```

| # | Exercise | Example |
|---|---|---|
| 01 | Last character | `lastChar("hello")` → `'o'` |
| 02 | Upper case + `!` | `shout("hello")` → `"HELLO!"` |
| 03 | Full name without extra spaces | `fullName("  ada ", "lovelace ")` → `"ada lovelace"` |
| 04 | Same text, any case | `sameText("Java", "JAVA")` → `true` |
| 05 | Count a character | `countChar("banana", 'a')` → `3` |
| 06 | Reverse | `reverse("abc")` → `"cba"` |
| 07 | Palindrome | `isPalindrome("kayak")` → `true` |
| 08 | Count vowels | `countVowels("Interview")` → `4` |
| 09 | Initials | `initials("Ada Lovelace")` → `"AL"` |
| 10 | URL slug | `slug("  Hello World  ")` → `"hello-world"` |
