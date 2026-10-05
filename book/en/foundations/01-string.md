# F01. String

🇫🇷 [Version française](../../fr/foundations/01-string.md)

## 1. What it is

A `String` is a sequence of characters: `"hello"`, `"Ada Lovelace"`, `""` (empty).
Each character has a position called an **index**, starting at **0**.

```
 "hello"
  h  e  l  l  o
  0  1  2  3  4      length() = 5, last index = length() - 1 = 4
```

**The key rule: a `String` is immutable.** No method changes it. Methods like
`toUpperCase()` return a **new** string.

```java
String s = "hello";
s.toUpperCase();          // creates "HELLO" and throws it away
System.out.println(s);    // hello
s = s.toUpperCase();      // keep the result
System.out.println(s);    // HELLO
```

## 2. Create a String

```java
String a = "hello";                       // literal (the usual way)
String b = new String("hello");           // avoid: creates a useless extra object
String c = String.valueOf(42);            // "42"
String d = "Age: " + 30;                  // "Age: 30"
String e = String.join("-", "a", "b");    // "a-b"
```

## 3. The methods you must know

| Method | What it does | Example | Time |
|---|---|---|---|
| `length()` | Number of characters | `"hello".length()` → `5` | O(1) |
| `charAt(i)` | Character at index `i` | `"hello".charAt(1)` → `'e'` | O(1) |
| `isEmpty()` | Length is 0 | `"".isEmpty()` → `true` | O(1) |
| `isBlank()` | Only spaces (or empty) | `"  ".isBlank()` → `true` | O(n) |
| `equals(o)` | Same characters | `"a".equals("a")` → `true` | O(n) |
| `equalsIgnoreCase(o)` | Same, ignoring case | `"A".equalsIgnoreCase("a")` → `true` | O(n) |
| `compareTo(o)` | Alphabetical order (<0, 0, >0) | `"a".compareTo("b")` → `-1` | O(n) |
| `contains(s)` | Contains a piece of text | `"hello".contains("ell")` → `true` | O(n·m) |
| `indexOf(s)` | First position, or `-1` | `"hello".indexOf('l')` → `2` | O(n·m) |
| `lastIndexOf(s)` | Last position, or `-1` | `"hello".lastIndexOf('l')` → `3` | O(n·m) |
| `startsWith(s)` / `endsWith(s)` | Begins / ends with | `"hello".startsWith("he")` → `true` | O(m) |
| `substring(a, b)` | From index `a` to `b` (**b excluded**) | `"hello".substring(1, 3)` → `"el"` | O(n) |
| `toUpperCase()` / `toLowerCase()` | Change case | `"Hi".toUpperCase()` → `"HI"` | O(n) |
| `trim()` / `strip()` | Remove spaces at both ends | `" hi ".strip()` → `"hi"` | O(n) |
| `replace(a, b)` | Replace every `a` with `b` | `"a-b".replace("-", "+")` → `"a+b"` | O(n) |
| `split(regex)` | Cut into an array | `"a b".split(" ")` → `["a", "b"]` | O(n) |
| `toCharArray()` | Copy into a `char[]` | `"hi".toCharArray()` → `['h', 'i']` | O(n) |
| `repeat(n)` | Repeat n times | `"ab".repeat(3)` → `"ababab"` | O(n·k) |
| `String.valueOf(x)` | Anything to String | `String.valueOf(3.5)` → `"3.5"` | O(n) |
| `Integer.parseInt(s)` | String to int | `Integer.parseInt("42")` → `42` | O(n) |

n = length of the string, m = length of the searched text.

### Useful `Character` methods

| Method | Example |
|---|---|
| `Character.isLetter(c)` | `'a'` → `true`, `'1'` → `false` |
| `Character.isDigit(c)` | `'7'` → `true` |
| `Character.isWhitespace(c)` | `' '` → `true` |
| `Character.isUpperCase(c)` | `'A'` → `true` |
| `Character.toLowerCase(c)` | `'A'` → `'a'` |

## 4. `StringBuilder`: when you build a string piece by piece

Because `String` is immutable, `s = s + x` in a loop copies the whole string every time:
O(n²) in total. `StringBuilder` is a **mutable** buffer: appending is O(1) on average.

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 3; i++) {
    sb.append(i).append(',');
}
sb.reverse();                 // StringBuilder can reverse itself
String result = sb.toString();
```

| Method | What it does |
|---|---|
| `append(x)` | Add at the end |
| `insert(i, x)` | Insert at index `i` |
| `reverse()` | Reverse in place |
| `deleteCharAt(i)` | Remove one character |
| `setCharAt(i, c)` | Replace one character |
| `length()` | Current length |
| `toString()` | Get the final `String` |

## 5. Going through a string

```java
String s = "hello";

for (int i = 0; i < s.length(); i++) {   // when you need the index
    char c = s.charAt(i);
}

for (char c : s.toCharArray()) {          // when you only need the character
    System.out.println(c);
}
```

## 6. When you use it in interviews

- Text processing: count, reverse, check palindromes, find words.
- Very often combined with a **Map** (count each character) or an **array of 26 ints**
  (one counter per letter `c - 'a'`).
- Building an answer: always with `StringBuilder`.

## 7. Traps

1. **`==` vs `equals`.** `==` compares references (same object), `equals` compares the
   text. Always use `equals` for strings.
   ```java
   String a = "hi";
   String b = new String("hi");
   a == b;        // false
   a.equals(b);   // true
   ```
2. **Index out of bounds.** `s.charAt(s.length())` throws an exception. The last index is
   `length() - 1`. And `charAt(0)` on `""` throws too.
3. **`substring(a, b)` excludes `b`.** `"hello".substring(0, 2)` is `"he"`.
4. **Forgetting to keep the result.** `s.trim();` alone does nothing.
5. **`null`.** `s.equals("x")` throws if `s` is `null`. `"x".equals(s)` is safe.
6. **`char` is a number.** `'a' + 1` is `98`, not `"b"`. `(char) ('a' + 1)` is `'b'`.
   And `'7' - '0'` is `7`: the classic way to turn a digit into an int.

## 8. Exercises

Code in [`StringExercises.java`](../../../src/main/java/com/mastery/interview/foundations/StringExercises.java).
Each exercise has its own tests in `StringExercisesTest`.

```bash
mvn -Dtest='StringExercisesTest$E01LastChar' test   # one exercise
mvn -Dtest=StringExercisesTest test                 # the whole chapter
```

| # | Exercise | What it trains |
|---|---|---|
| 01 | `lastChar("hello")` → `'o'` | `charAt`, `length() - 1` |
| 02 | `shout("hello")` → `"HELLO!"` | `toUpperCase`, concatenation |
| 03 | `fullName("  ada ", "lovelace ")` → `"ada lovelace"` | `strip` |
| 04 | `sameText("Java", "JAVA")` → `true` | `equalsIgnoreCase`, never `==` |
| 05 | `countChar("banana", 'a')` → `3` | Loop with `charAt` |
| 06 | `reverse("abc")` → `"cba"` | `StringBuilder` |
| 07 | `isPalindrome("kayak")` → `true` | Compare both ends |
| 08 | `countVowels("Interview")` → `4` | `Character.toLowerCase`, `indexOf` |
| 09 | `initials("Ada Lovelace")` → `"AL"` | `split`, `charAt(0)` |
| 10 | `slug("  Hello World  ")` → `"hello-world"` | Chain `strip`, `toLowerCase`, `replace` |
