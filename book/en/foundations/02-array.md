# F02. Array

🇫🇷 [Version française](../../fr/foundations/02-array.md)

## 1. What it is

An array is a row of boxes of **fixed size**, all of the **same type**. Index starts at **0**.

```
 int[] nums = {4, 8, 15, 16};
 index:   0  1   2   3          nums.length = 4
```

- The size never changes. Need to grow? Use a `List` (F03).
- Read or write by index: **O(1)**.

## 2. Create an array

| Code | Result |
|---|---|
| `int[] a = {4, 8, 15};` | `[4, 8, 15]` |
| `new int[3]` | `[0, 0, 0]` |
| `new boolean[2]` | `[false, false]` |
| `new String[2]` | `[null, null]` |
| `new int[2][3]` | 2 rows of `[0, 0, 0]` |
| `new int[] {1, 2}` | `[1, 2]` (to pass directly to a method) |

## 3. What an array has by itself

| Code | Example with `a = {4, 8, 15}` | Result |
|---|---|---|
| `a[i]` | `a[1]` | `8` |
| `a[i] = x` | `a[1] = 99` | `a = [4, 99, 15]` |
| `a.length` | `a.length` | `3` (no parentheses) |
| `a.clone()` | `int[] b = a.clone()` | `b = [4, 8, 15]`, a separate copy |

## 4. All the methods of `java.util.Arrays`

### Display

| Method | Example | Result |
|---|---|---|
| `toString(a)` | `Arrays.toString(new int[] {3, 1, 2})` | `"[3, 1, 2]"` |
| `deepToString(a)` | `Arrays.deepToString(new int[][] {{1, 2}, {3}})` | `"[[1, 2], [3]]"` |

### Sort

| Method | Example | Result |
|---|---|---|
| `sort(a)` | `Arrays.sort(a)` with `a = {3, 1, 2}` | `a = [1, 2, 3]` |
| `sort(a, from, to)` | `Arrays.sort(a, 0, 2)` with `a = {3, 1, 2}` | `a = [1, 3, 2]` |
| `sort(a, comparator)` | `Arrays.sort(b, Comparator.reverseOrder())` with `Integer[] b = {3, 1, 2}` | `b = [3, 2, 1]` |
| `parallelSort(a)` | `Arrays.parallelSort(a)` with `a = {3, 1, 2}` | `a = [1, 2, 3]` (uses several cores) |

### Search

| Method | Example | Result |
|---|---|---|
| `binarySearch(a, x)` | `Arrays.binarySearch(new int[] {1, 2, 3}, 2)` | `1` |
| `binarySearch(a, x)` absent | `Arrays.binarySearch(new int[] {1, 2, 3}, 5)` | `-4` (negative = absent) |
| `binarySearch(a, from, to, x)` | `Arrays.binarySearch(new int[] {1, 2, 3}, 0, 2, 2)` | `1` |

The array **must be sorted** before `binarySearch`.

### Fill and build

| Method | Example | Result |
|---|---|---|
| `fill(a, x)` | `Arrays.fill(a, 7)` with `a = new int[3]` | `a = [7, 7, 7]` |
| `fill(a, from, to, x)` | `Arrays.fill(a, 1, 3, 0)` with `a = {3, 1, 2}` | `a = [3, 0, 0]` |
| `setAll(a, f)` | `Arrays.setAll(a, i -> i * i)` with `a = new int[4]` | `a = [0, 1, 4, 9]` |
| `parallelSetAll(a, f)` | `Arrays.parallelSetAll(a, i -> i * i)` | `a = [0, 1, 4, 9]` |
| `parallelPrefix(a, f)` | `Arrays.parallelPrefix(a, Integer::sum)` with `a = {1, 2, 3, 4}` | `a = [1, 3, 6, 10]` |

### Copy

| Method | Example | Result |
|---|---|---|
| `copyOf(a, n)` | `Arrays.copyOf(new int[] {1, 2}, 3)` | `[1, 2, 0]` |
| `copyOf(a, n)` shorter | `Arrays.copyOf(new int[] {1, 2, 3}, 2)` | `[1, 2]` |
| `copyOfRange(a, from, to)` | `Arrays.copyOfRange(new int[] {1, 2, 3, 4}, 1, 3)` | `[2, 3]` (to excluded) |

### Compare

| Method | Example | Result |
|---|---|---|
| `equals(a, b)` | `Arrays.equals(new int[] {1, 2}, new int[] {1, 2})` | `true` |
| `deepEquals(a, b)` | `Arrays.deepEquals(new int[][] {{1}, {2}}, new int[][] {{1}, {2}})` | `true` |
| `compare(a, b)` | `Arrays.compare(new int[] {1, 2}, new int[] {1, 3})` | `-1` (a before b) |
| `compareUnsigned(a, b)` | `Arrays.compareUnsigned(new int[] {-1}, new int[] {1})` | `1` |
| `mismatch(a, b)` | `Arrays.mismatch(new int[] {1, 2, 3}, new int[] {1, 5, 3})` | `1` (first different index) |
| `mismatch(a, b)` identical | `Arrays.mismatch(new int[] {1, 2}, new int[] {1, 2})` | `-1` |
| `hashCode(a)` | `Arrays.hashCode(new int[] {1, 2})` | `994` |
| `deepHashCode(a)` | `Arrays.deepHashCode(new int[][] {{1, 2}})` | `1025` |

### Convert

| Method | Example | Result |
|---|---|---|
| `asList(...)` | `Arrays.asList("a", "b")` | `[a, b]` (fixed-size `List`) |
| `stream(a)` | `Arrays.stream(new int[] {1, 2, 3}).sum()` | `6` |
| `stream(a)` | `Arrays.stream(new int[] {1, 2, 3}).max().getAsInt()` | `3` |
| `spliterator(a)` | `Arrays.spliterator(new int[] {1, 2, 3}).estimateSize()` | `3` (used by streams, rare) |

### Not in `Arrays` but useful: `System.arraycopy`

| Method | Example | Result |
|---|---|---|
| `System.arraycopy(src, i, dst, j, n)` | `System.arraycopy(new int[] {1, 2}, 0, dst, 1, 2)` with `dst = new int[3]` | `dst = [0, 1, 2]` |

## 5. Going through an array

```java
for (int i = 0; i < nums.length; i++) {
    nums[i] = nums[i] * 2;
}

for (int n : nums) {
    System.out.println(n);
}

for (int i = nums.length - 1; i >= 0; i--) {
    System.out.println(nums[i]);
}
```

## 6. In interviews

- Half of coding problems take an `int[]`.
- Count letters: `int[] count = new int[26]`, then `count[c - 'a']++`.
- Techniques that start here: two pointers, sliding window, prefix sums, binary search.
- Grids `int[][]` and `char[][]`: mazes, islands.

## 7. Traps

| Trap | Wrong | Right |
|---|---|---|
| Last index | `i <= nums.length` | `i < nums.length` |
| Compare content | `a == b` or `a.equals(b)` | `Arrays.equals(a, b)` |
| Print | `System.out.println(a)` gives `[I@1b6d3586` | `Arrays.toString(a)` |
| Copy | `int[] b = a;` (same array) | `int[] b = a.clone();` |
| Max with negatives | `int max = 0;` | `int max = nums[0];` |
| Average | `sum / n` gives an int | `(double) sum / n` |
| Empty array | `nums[0]` throws | check `nums.length == 0` first |
| Big sums | `int` can overflow | `long` |
| for-each to write | `for (int n : a) n = 0;` changes nothing | `for (int i...) a[i] = 0;` |

## 8. Exercises

File: [`ArrayExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ArrayExercises.java).
Write the loops yourself: no `Arrays.sort`, no streams.

```bash
mvn -Dtest='ArrayExercisesTest$E01Sum' test
mvn -Dtest=ArrayExercisesTest test
```

| # | Exercise | Example |
|---|---|---|
| 01 | Sum | `sum({1, 2, 3})` → `6` |
| 02 | Max | `max({3, 9, 2})` → `9` |
| 03 | Contains | `contains({1, 2, 3}, 2)` → `true` |
| 04 | Index of | `indexOf({5, 7, 5}, 5)` → `0` |
| 05 | Count even | `countEven({1, 2, 4})` → `2` |
| 06 | Doubled (new array) | `doubled({1, 2})` → `{2, 4}` |
| 07 | Average | `average({1, 2})` → `1.5` |
| 08 | Reversed (new array) | `reversed({1, 2, 3})` → `{3, 2, 1}` |
| 09 | Is sorted | `isSorted({1, 2, 2, 5})` → `true` |
| 10 | Concat | `concat({1, 2}, {3})` → `{1, 2, 3}` |
