# F02. Array

🇫🇷 [Version française](../../fr/foundations/02-array.md)

## 1. What it is

An array is a **fixed-size** row of boxes, all of the **same type**, side by side in memory.
Each box has an index starting at **0**.

```
 int[] nums = {4, 8, 15, 16};
 index:   0  1   2   3          nums.length = 4 (no parentheses: it is a field)
```

- **Size fixed at creation.** You cannot add a fifth box. Need to grow? Use a `List` (F03).
- **Reading or writing by index is O(1)**: the computer jumps straight to the box.
- An array is **mutable**: you can change the content of a box.

## 2. Create an array

```java
int[] a = {4, 8, 15, 16};            // with values
int[] b = new int[5];                // 5 boxes, all at 0
String[] c = new String[3];          // 3 boxes, all at null
boolean[] d = new boolean[2];        // all at false
int[][] grid = new int[3][4];        // 3 rows, 4 columns
```

Default values: `0` for numbers, `false` for `boolean`, `'\u0000'` for `char`, `null` for objects.

## 3. Basic operations

| Operation | Code | Time |
|---|---|---|
| Read a box | `a[2]` | O(1) |
| Write a box | `a[2] = 99;` | O(1) |
| Size | `a.length` | O(1) |
| Search a value (unsorted) | loop over every box | O(n) |
| Insert / delete in the middle | impossible in place, you shift or copy | O(n) |

## 4. The `Arrays` toolbox (`java.util.Arrays`)

| Method | What it does | Example | Time |
|---|---|---|---|
| `Arrays.toString(a)` | Readable text | `[4, 8, 15]` | O(n) |
| `Arrays.sort(a)` | Sort in place, ascending | `{3,1,2}` → `{1,2,3}` | O(n log n) |
| `Arrays.binarySearch(a, x)` | Find `x` in a **sorted** array | index, or negative if absent | O(log n) |
| `Arrays.fill(a, x)` | Put `x` in every box | `fill(a, -1)` | O(n) |
| `Arrays.copyOf(a, len)` | Copy, cut or padded with 0 | `copyOf({1,2}, 3)` → `{1,2,0}` | O(n) |
| `Arrays.copyOfRange(a, from, to)` | Copy a slice (**to excluded**) | `copyOfRange({1,2,3}, 0, 2)` → `{1,2}` | O(n) |
| `Arrays.equals(a, b)` | Same content | `equals({1,2}, {1,2})` → `true` | O(n) |
| `Arrays.asList(...)` | View as a fixed-size `List` | see F03 | O(1) |
| `Arrays.stream(a)` | Stream: `sum()`, `max()`... | `Arrays.stream(a).sum()` | O(n) |

## 5. Going through an array

```java
int[] nums = {4, 8, 15, 16};

for (int i = 0; i < nums.length; i++) {   // index needed (to write, or compare neighbors)
    nums[i] = nums[i] * 2;
}

for (int n : nums) {                       // read only
    System.out.println(n);
}

for (int i = nums.length - 1; i >= 0; i--) {   // backwards
    System.out.println(nums[i]);
}
```

The **for-each** gives you a copy of the value: `n = 0;` inside it does not change the array.

## 6. When you use it in interviews

- Half of all coding problems take an `int[]` as input.
- Counting: `int[] count = new int[26]` for letters, `count[c - 'a']++`.
- Classic techniques that start here: **two pointers** (one at each end), **sliding
  window**, **prefix sums**, **binary search** on a sorted array.
- Grids (`int[][]`, `char[][]`) for maze and island problems.

## 7. Traps

1. **`ArrayIndexOutOfBoundsException`.** Valid indexes go from `0` to `length - 1`.
   `i <= nums.length` in a loop is the classic bug: it must be `<`.
2. **`==` and `equals` compare references.** `a == b` and `a.equals(b)` are `false` for two
   arrays with the same content. Use `Arrays.equals(a, b)`.
3. **Printing.** `System.out.println(a)` prints something like `[I@1b6d3586`.
   Use `Arrays.toString(a)`.
4. **Copying is not `=`.** `int[] b = a;` does not copy: `b` and `a` are the same array.
   Changing `b[0]` changes `a[0]`. Use `a.clone()` or `Arrays.copyOf`.
5. **Changing the input.** If a method sorts or modifies the array it receives, the caller
   sees it. When asked to "return a new array", do not touch the input.
6. **Empty array.** `nums[0]` on `new int[0]` throws. Ask about it in an interview.
7. **Integer overflow.** Summing big `int`s can overflow silently. Use `long` if needed.

## 8. Exercises

Code in [`ArrayExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ArrayExercises.java).

```bash
mvn -Dtest='ArrayExercisesTest$E01Sum' test   # one exercise
mvn -Dtest=ArrayExercisesTest test            # the whole chapter
```

| # | Exercise | What it trains |
|---|---|---|
| 01 | `sum({1, 2, 3})` → `6` | Basic loop |
| 02 | `max({3, 9, 2})` → `9` | Start from `nums[0]`, not from 0 |
| 03 | `contains({1, 2, 3}, 2)` → `true` | Linear search, early return |
| 04 | `indexOf({5, 7, 5}, 5)` → `0` | Return the index, `-1` if absent |
| 05 | `countEven({1, 2, 4})` → `2` | `%` modulo |
| 06 | `doubled({1, 2})` → `{2, 4}` | New array, input unchanged |
| 07 | `average({1, 2})` → `1.5` | Integer vs double division |
| 08 | `reversed({1, 2, 3})` → `{3, 2, 1}` | Index from the end |
| 09 | `isSorted({1, 2, 2, 5})` → `true` | Compare neighbors `i` and `i + 1` |
| 10 | `concat({1, 2}, {3})` → `{1, 2, 3}` | Size the result, copy in two parts |
