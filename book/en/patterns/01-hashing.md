# Pattern 1. Hashing

🇫🇷 [Version française](../../fr/patterns/01-hashing.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Many problems ask: "for this element, is there **another element** with some property?"
(the same value, the complement to a target, the same letters...).

The brute force answers it with a second loop:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[] {i, j};
        }
    }
}
```

For each element, the inner loop **searches** the whole array again: n × n = **O(n²)**.
With n = 100 000, that is 10 billion checks, about 10 seconds. Too slow.

## 2. The key idea

Searching an array costs O(n). Searching a `HashMap` or `HashSet` costs **O(1)**.

So instead of searching again and again, **remember what you have already seen** in a
hash structure, and ask it the question in O(1).

```
 brute force                         hashing
 for each x:                         for each x:
     search the whole array  O(n)        ask the map           O(1)
                                         then put x in the map O(1)
 total O(n²)                         total O(n)
```

You pay O(n) **memory** to save a factor n of **time**. This trade is almost always worth it
in an interview.

## 3. Step by step on an example

Find two numbers that sum to **10** in `[3, 8, 4, 6]`.

At each step: compute `need = 10 - x`, look for `need` in the map, then store `x → index`.

| Step | i | x | need = 10 - x | `need` in the map? | Map after the step |
|---|---|---|---|---|---|
| 1 | 0 | 3 | 7 | no | {3=0} |
| 2 | 1 | 8 | 2 | no | {3=0, 8=1} |
| 3 | 2 | 4 | 6 | no | {3=0, 8=1, 4=2} |
| 4 | 3 | 6 | 4 | **yes, at index 2** | stop |

Answer: indexes **[2, 3]** (4 + 6 = 10). Each element was looked at **once**.

Notice the order inside the loop: **look first, then store**. If you store first, `x` could
match itself (for target 12 and x = 6, you would wrongly answer [3, 3]).

## 4. The template, line by line

```java
Map<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int need = target - nums[i];
    if (seen.containsKey(need)) {
        return new int[] {seen.get(need), i};
    }
    seen.put(nums[i], i);
}
return new int[] {-1, -1};
```

| Line | Why |
|---|---|
| `Map<Integer, Integer> seen` | key = a value already seen, value = where it was (its index). If you only need "seen or not", use a `HashSet` |
| `int need = target - nums[i]` | the question to ask: "which value would complete me?" |
| `seen.containsKey(need)` | O(1) instead of a loop |
| `return ... seen.get(need), i` | the old index is smaller, so it comes first |
| `seen.put(nums[i], i)` | **after** the check, so an element never pairs with itself |

## 5. Why it is correct

**Invariant**: at the start of step `i`, the map contains exactly the elements at indexes
`0 .. i-1`.

So if a valid pair `(j, i)` exists with `j < i`, when the loop reaches `i`, the element `j`
is already in the map and is found. Every pair is checked once, from its second element.

## 6. The three ways to use a hash structure

| Need | Structure | Example |
|---|---|---|
| "seen or not?" | `HashSet<T>` | contains duplicate, cycle detection, visited cells |
| "where / what was it?" | `HashMap<K, V>` with an index or a value | Two Sum (value → index) |
| "how many times?" | `HashMap<K, Integer>` frequency map, or `int[26]` for letters | anagrams, most frequent, first unique |
| "which ones go together?" | `HashMap<K, List<T>>` with a **signature** as key | group anagrams (key = sorted letters), group by category |

**Choosing the key** is the real skill. Ask: "what do two elements that belong together have
in common?" Anagrams have the same sorted letters. Points on the same line have the same slope.
That common thing is the key.

## 7. A problem solved from start to finish

**Problem (ransom note)**: can you write `note` by cutting letters out of `magazine`? Each
letter of the magazine can be used once.
`canWrite("aab", "baa")` → `true`, `canWrite("aa", "ab")` → `false`.

1. **Brute force**: for each letter of the note, search the magazine and cross it out.
   O(n × m).
2. **Which question repeats?** "Is there still an unused letter `c` in the magazine?" That is
   a **count** per letter.
3. **Hash structure**: frequency of each magazine letter (`int[26]` since only `a` to `z`).
4. **Algorithm**: count the magazine, then for each note letter take one; if the count goes
   below 0, it is impossible.

| Step | Letter | Counts after (a, b) | OK? |
|---|---|---|---|
| count magazine `"baa"` | | a=2, b=1 | |
| note letter 1 | a | a=1, b=1 | yes |
| note letter 2 | a | a=0, b=1 | yes |
| note letter 3 | b | a=0, b=0 | yes → `true` |

```java
static boolean canWrite(String note, String magazine) {
    int[] count = new int[26];
    for (char c : magazine.toCharArray()) {
        count[c - 'a']++;
    }
    for (char c : note.toCharArray()) {
        count[c - 'a']--;
        if (count[c - 'a'] < 0) {
            return false;
        }
    }
    return true;
}
```

5. **Cost**: O(n + m) time, O(1) space (26 counters, whatever the input size).

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| Store before checking | an element pairs with itself | check, **then** `put` |
| `map.get(k)` on a missing key, unboxed to `int` | `NullPointerException` | `getOrDefault(k, 0)` or `containsKey` |
| `int[26]` with upper case or spaces | `ArrayIndexOutOfBoundsException` | lower-case first, or use a `HashMap<Character, Integer>` |
| Mutable object as a key (`List`, array) | the map cannot find it again | use a `String` or a record as the key |
| `int[]` as a key | two equal arrays are different keys | `Arrays.toString(arr)` or a `List<Integer>` |
| Claiming "always O(1)" | wrong in the worst case | "O(1) on average" |

## 9. How to recognize it

- "Is there a pair / duplicate / complement...?" in an **unsorted** input.
- "Count", "frequency", "most common", "first unique".
- "Group the ... that have the same ..."
- Your brute force has an **inner loop that searches** for something.

If the array is **sorted**, think two pointers first (O(1) memory).

## 10. Practice

Exercises: [`patterns/hashing/HashingExercises.java`](../../../src/main/java/com/mastery/interview/patterns/hashing/HashingExercises.java)

```bash
mvn -Dtest='HashingExercisesTest' test
```

Before coding each one, write on paper: **what do I store? what is the key? what question
do I ask the map at each step?**
