# Pattern 4. Prefix sums

🇫🇷 [Version française](../../fr/patterns/04-prefix-sums.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Many problems ask for **the sum of a piece of the array**: "sum between index i and j",
"how many subarrays have sum k", "where is the balance point"...

The brute force adds the numbers again for every question:

```java
for (int[] q : queries) {
    long sum = 0;
    for (int i = q[0]; i <= q[1]; i++) {
        sum += nums[i];
    }
    answers.add(sum);
}
```

Each query walks up to n numbers: **O(n)** per query, **O(n × q)** for q queries.
With 100 000 numbers and 100 000 queries, that is 10 billion additions. Too slow.

Same problem for "subarrays with sum k": try every start and every end, O(n²).

## 2. The key idea

Add everything **once**, from the left, and keep every running total in an array `prefix`.
`prefix[i]` is the sum of the **first i numbers**.

```
 nums        [ 3 | 1 | 4 | 1 | 5 ]
 index         0   1   2   3   4

 prefix    [ 0 | 3 | 4 | 8 | 9 | 14 ]
 index       0   1   2   3   4   5

 sum(1..3) = prefix[4] - prefix[1] = 9 - 3 = 6      (1 + 4 + 1)
```

`prefix[4]` is `3 + 1 + 4 + 1`. `prefix[1]` is `3`. Subtracting removes everything **before**
index 1, and what is left is exactly `1 + 4 + 1`.

So any range sum is **one subtraction**: O(1), after O(n) to build the array.

## 3. Step by step on an example

Build `prefix` for `nums = [3, 1, 4, 1, 5]`, then answer three queries.

| Step | i | nums[i] | prefix[i + 1] = prefix[i] + nums[i] |
|---|---|---|---|
| start | | | prefix[0] = 0 |
| 1 | 0 | 3 | prefix[1] = 0 + 3 = 3 |
| 2 | 1 | 1 | prefix[2] = 3 + 1 = 4 |
| 3 | 2 | 4 | prefix[3] = 4 + 4 = 8 |
| 4 | 3 | 1 | prefix[4] = 8 + 1 = 9 |
| 5 | 4 | 5 | prefix[5] = 9 + 5 = 14 |

`prefix = [0, 3, 4, 8, 9, 14]`

| Query | Formula | Result | Check |
|---|---|---|---|
| sum(1..3) | prefix[4] - prefix[1] | 9 - 3 = **6** | 1 + 4 + 1 |
| sum(0..4) | prefix[5] - prefix[0] | 14 - 0 = **14** | the whole array |
| sum(2..2) | prefix[3] - prefix[2] | 8 - 4 = **4** | just nums[2] |

Each query costs one subtraction, whatever the size of the range.

## 4. The template, line by line

```java
long[] prefix = new long[nums.length + 1];
for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}

long rangeSum(long[] prefix, int from, int to) {
    return prefix[to + 1] - prefix[from];
}
```

| Line | Why |
|---|---|
| `new long[nums.length + 1]` | one more box than `nums`: `prefix[0] = 0` is the sum of "nothing" |
| `long` | sums of many `int` can overflow an `int` |
| `prefix[i + 1] = prefix[i] + nums[i]` | each total reuses the previous one: O(1) per box |
| `prefix[to + 1] - prefix[from]` | everything up to `to`, minus everything before `from` |

The extra `0` at the start is what makes `sum(0..j)` work without a special case:
`prefix[j + 1] - prefix[0]`.

## 5. Why it is correct

**Invariant**: after step `i`, `prefix[i + 1] = nums[0] + nums[1] + ... + nums[i]`.

Then for any `from <= to`:

```
 prefix[to + 1]  = nums[0] + ... + nums[from - 1] + nums[from] + ... + nums[to]
 prefix[from]    = nums[0] + ... + nums[from - 1]
 difference      =                                  nums[from] + ... + nums[to]
```

The common start cancels out. This works with **negative numbers** too: it is only addition
and subtraction.

## 6. Variants

### Prefix sums + hash map: "a subarray with sum k"

This is the most important variant. Read it slowly.

A subarray `i..j` has sum k exactly when `prefix[j + 1] - prefix[i] = k`, that is when
`prefix[i] = prefix[j + 1] - k`.

So while you walk the array with a running `sum`, the question at each step is:

> "Did an **earlier** running sum equal `sum - k`?"

That is a **hashing** question (pattern 1): keep the earlier sums in a `HashMap`.

| You want | The map stores | Start with |
|---|---|---|
| does a subarray with sum k exist? | the earlier sums (`HashSet`) | `{0}` |
| how many subarrays have sum k? | earlier sum → **how many times** it appeared | `{0=1}` |
| the longest subarray with sum k | earlier sum → **first index** where it appeared | `{0=-1}` |

The starting entry for sum `0` stands for "the empty prefix, before index 0". Without it,
subarrays that start at index 0 are never found.

Section 7 solves the "longest" version step by step.

### Other variants

| Variant | Idea | Example |
|---|---|---|
| Count of something | prefix of "1 if the element matches, else 0" | number of vowels between i and j |
| Prefix XOR | `px[i + 1] = px[i] ^ nums[i]`, then `xor(i..j) = px[j + 1] ^ px[i]` | XOR of a range |
| Products from the left and from the right | `left[i]` = product before i, `right[i]` = product after i | product of the array except itself |
| 2D prefix sums | `p[r + 1][c + 1] = grid[r][c] + p[r][c + 1] + p[r + 1][c] - p[r][c]` | sum of a rectangle in a grid in O(1) |
| Difference array (the reverse) | add `v` at `from`, subtract `v` at `to + 1`, then take the prefix sum | add a value to many ranges, then read the final array |

## 7. A problem solved from start to finish

**Problem (longest subarray with sum k)**: return the length of the longest contiguous piece
of `nums` whose sum is exactly `k`. Numbers can be negative.
`longestWithSum([1, -1, 5, -2, 3], 3)` → `4` (the piece `[1, -1, 5, -2]`).

1. **Brute force**: try every start and every end, O(n²).
2. **Why not a sliding window?** With negative numbers, growing the window can make the sum
   go **down**, so you never know when to shrink. Sliding window needs positive numbers.
3. **Which question repeats?** At index `j`, with `sum` = total of `nums[0..j]`: "where is
   the **first** index `i` where the running sum was `sum - k`?" Then `i + 1 .. j` has sum k,
   and its length is `j - i`.
4. **Hash structure**: running sum → first index where it appeared. Start with `{0=-1}`.
   Use `putIfAbsent` to keep the **first** index (the earliest start gives the longest piece).

| i | nums[i] | sum | need = sum - k | need in the map? | best | Map after the step |
|---|---|---|---|---|---|---|
| start | | 0 | | | 0 | {0=-1} |
| 0 | 1 | 1 | -2 | no | 0 | {0=-1, 1=0} |
| 1 | -1 | 0 | -3 | no | 0 | {0=-1, 1=0} (0 already there, keep -1) |
| 2 | 5 | 5 | 2 | no | 0 | {0=-1, 1=0, 5=2} |
| 3 | -2 | 3 | 0 | **yes, at -1**: length 3 - (-1) = 4 | **4** | {0=-1, 1=0, 3=3, 5=2} |
| 4 | 3 | 6 | 3 | yes, at 3: length 4 - 3 = 1 | 4 | {0=-1, 1=0, 3=3, 5=2, 6=4} |

Answer: **4**. The match at step 3 uses the starting entry `0=-1`: the piece starts at index 0.

```java
static int longestWithSum(int[] nums, int k) {
    Map<Integer, Integer> firstIndex = new HashMap<>();
    firstIndex.put(0, -1);
    int sum = 0;
    int best = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        Integer start = firstIndex.get(sum - k);
        if (start != null) {
            best = Math.max(best, i - start);
        }
        firstIndex.putIfAbsent(sum, i);
    }
    return best;
}
```

5. **Cost**: O(n) time, O(n) space for the map. You never build the `prefix` array: the
   running `sum` plus the map is enough.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| `prefix` of size n, no leading 0 | ranges that start at 0 need a special case, or are wrong | size `n + 1`, `prefix[0] = 0` |
| `prefix[to] - prefix[from]` | the last element is missing | `prefix[to + 1] - prefix[from]` |
| `int` prefix on big inputs | negative totals out of nowhere (overflow) | `long[] prefix` |
| Map without the starting `0` entry | subarrays that start at index 0 are never counted | `put(0, 1)` or `put(0, -1)` before the loop |
| Store the current sum **before** checking | with k = 0, an empty piece counts as an answer | check `sum - k` first, then store `sum` |
| `put` instead of `putIfAbsent` for "longest" | the index moves forward, pieces get shorter | keep the **first** index |
| Sliding window with negative numbers | wrong answers | prefix sums + map |

## 9. How to recognize it

- "Sum between i and j", "many queries on ranges".
- "Number of subarrays with sum k", "longest subarray with sum k", "subarray sum divisible
  by k".
- "Balance point", "left sum equals right sum".
- The array can contain **negative numbers**, so a sliding window does not work.
- Your brute force recomputes the same sums again and again.

## 10. Practice

Exercises: [`patterns/prefixsum/PrefixSumExercises.java`](../../../src/main/java/com/mastery/interview/patterns/prefixsum/PrefixSumExercises.java)

```bash
mvn -Dtest='PrefixSumExercisesTest' test
```

Before coding each one, write on paper: **what does `prefix[i]` (or the running sum)
contain? which subtraction gives my answer? if I use a map, what does it store and what is
its starting entry?**
