# Pattern 3. Sliding window

🇫🇷 [Version française](../../fr/patterns/03-sliding-window.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Many problems ask about a **contiguous** piece of an array or a string (a subarray, a
substring): "the longest piece that...", "the best piece of size k", "how many pieces...".

The brute force tries every start and every end, and checks each piece:

```java
for (int start = 0; start < n; start++) {
    for (int end = start; end < n; end++) {
        check(start, end);
    }
}
```

There are about n² / 2 pieces, and checking one can cost O(n) more: **O(n²)** or even
**O(n³)**. With n = 100 000, it never finishes.

## 2. The key idea

Two neighbor pieces share almost everything. `[2, 7, 1, 8]` and `[7, 1, 8, 4]` differ by
**one element out, one element in**. So do not recompute from scratch: keep a **window**
`[left, right]` and update it in O(1) when it moves.

```
           left        right
            ▼           ▼
 [ 4 | 2 | 7 | 1 | 8 | 4 | 6 ]
            └─ window ──┘

 right moves: one element enters  (add it)
 left moves:  one element leaves  (remove it)
```

`right` and `left` only move forward. Each element enters once and leaves once: **O(n)** in
total, even with a `while` inside the `for`.

| Kind | Window size | When |
|---|---|---|
| Fixed | always `k` | "of size k", "every k consecutive" |
| Variable | grows and shrinks | "longest / shortest piece such that...", "at most k ..." |

## 3. Step by step on two examples

### Fixed window: most vowels in a piece of length 3

`s = "abciiidef"`, `k = 3`. Which piece of length 3 has the most vowels?

Keep `count` = number of vowels in the window. When `right` moves, the new letter enters; once
the window is longer than k, the letter at `right - k` leaves.

| right | Enters | Leaves | Window | count | best |
|---|---|---|---|---|---|
| 0 | a | | a | 1 | (not full yet) |
| 1 | b | | ab | 1 | (not full yet) |
| 2 | c | | abc | 1 | 1 |
| 3 | i | a | bci | 1 | 1 |
| 4 | i | b | cii | 2 | 2 |
| 5 | i | c | iii | 3 | 3 |
| 6 | d | i | iid | 2 | 3 |
| 7 | e | i | ide | 2 | 3 |
| 8 | f | i | def | 1 | 3 |

Answer: **3** (`"iii"`). Each step costs O(1): one letter in, one letter out.

### Variable window: longest piece with at most 2 different letters

`s = "eceba"`. Find the longest piece using at most 2 different letters.

Keep a map `counts` of the letters in the window. `right` adds a letter. **While** the window
has more than 2 different letters, `left` removes letters. Then the window is valid: compare
its length with `best`.

| right | Adds | left after shrinking | Window | counts | best |
|---|---|---|---|---|---|
| 0 | e | 0 | e | {e=1} | 1 |
| 1 | c | 0 | ec | {c=1, e=1} | 2 |
| 2 | e | 0 | ece | {c=1, e=2} | 3 |
| 3 | b | 2 (removed e, c) | eb | {b=1, e=1} | 3 |
| 4 | a | 3 (removed e) | ba | {a=1, b=1} | 3 |

Answer: **3** (`"ece"`). At step 3, adding `b` made 3 different letters: `left` moved until
`c` disappeared from the counts.

## 4. The templates, line by line

### Fixed window of size k

```java
int current = 0;
int best = 0;
for (int right = 0; right < n; right++) {
    current += value(right);
    if (right >= k) {
        current -= value(right - k);
    }
    if (right >= k - 1) {
        best = Math.max(best, current);
    }
}
```

| Line | Why |
|---|---|
| `current += value(right)` | the new element enters |
| `if (right >= k) current -= value(right - k)` | once the window would hold k + 1 elements, the oldest one leaves |
| `if (right >= k - 1)` | the window holds exactly k elements only from `right = k - 1`: do not record before |

### Variable window (longest valid piece)

```java
int left = 0;
int best = 0;
for (int right = 0; right < n; right++) {
    add(right);
    while (windowIsInvalid()) {
        remove(left);
        left++;
    }
    best = Math.max(best, right - left + 1);
}
```

| Line | Why |
|---|---|
| `for (int right ...)` | `right` grows the window by one at each turn |
| `add(right)` | update the window state (a sum, a map of counts, a number of zeros...) |
| `while (windowIsInvalid())` | a `while`, not an `if`: one new element can force several removals |
| `remove(left); left++` | shrink from the left until the window is valid again |
| `right - left + 1` | the length of the window `[left, right]`, both included |

## 5. Why it is correct

**Invariant** (variable window): after the `while`, `[left, right]` is the **longest valid
window that ends at `right`**.

Why can `left` never need to go back? Because of the **monotonic property**: if
`[left, right]` is invalid (3 different letters), every bigger window that contains it is
invalid too. So no valid window can start before `left` and end at `right` or later. Moving
`left` forward never loses an answer.

This is why the pattern needs that property. It works with "at most k different letters",
"sum of **positive** numbers", "number of zeros". It does **not** work with "sum = k" when
numbers can be negative (adding an element can make the sum go down): use prefix sums
(pattern 4) instead.

## 6. Variants

| Variant | How the loop changes | Example |
|---|---|---|
| Fixed size k | one in, one out, record when size is k | max vowels, max average of k elements |
| Longest valid | grow, `while` invalid shrink, then record | at most k different letters, longest piece without repeat |
| Shortest valid | grow, `while` **valid** record then shrink | shortest piece with sum ≥ target |
| Count the valid pieces | after shrinking, add `right - left + 1` (all valid pieces ending at `right`) | pieces with product < k |
| Exactly k | `atMost(k) - atMost(k - 1)` | pieces with exactly k different numbers |
| Window state = counts | a `HashMap` or `int[26]` of the letters in the window | anagram or permutation inside a text |

## 7. A problem solved from start to finish

**Problem (product less than k)**: count the contiguous pieces of `nums` (all numbers
positive) whose product is **strictly less than k**.
`countProductLessThan({10, 5, 2, 6}, 100)` → `8`.

1. **Brute force**: every start, every end, multiply. O(n²).
2. **Contiguous + a rule that breaks when the piece grows?** Yes: numbers are positive, so a
   bigger piece has a bigger (or equal) product. Variable window.
3. **Window state**: the product of the window. Add = multiply, remove = divide.
4. **Counting trick**: when the window `[left, right]` is valid, **every** piece that ends at
   `right` and starts at `left`, `left + 1`, ..., `right` is valid too. That is
   `right - left + 1` new pieces.

| right | x | left after shrinking | Window | Product | New pieces | Total |
|---|---|---|---|---|---|---|
| 0 | 10 | 0 | [10] | 10 | 1 | 1 |
| 1 | 5 | 0 | [10, 5] | 50 | 2 | 3 |
| 2 | 2 | 1 (100 ≥ 100: removed 10) | [5, 2] | 10 | 2 | 5 |
| 3 | 6 | 1 | [5, 2, 6] | 60 | 3 | 8 |

```java
static int countProductLessThan(int[] nums, int k) {
    if (k <= 1) {
        return 0;
    }
    long product = 1;
    int left = 0;
    int count = 0;
    for (int right = 0; right < nums.length; right++) {
        product *= nums[right];
        while (product >= k) {
            product /= nums[left];
            left++;
        }
        count += right - left + 1;
    }
    return count;
}
```

5. **Cost**: O(n) time, O(1) space. The `if (k <= 1)` guard matters: with k = 1 no product
   of positive integers is below 1, and the `while` would run past `right`.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| `if` instead of `while` to shrink | window stays invalid, answer too big | `while (windowIsInvalid())` |
| Record `best` before shrinking | counts an invalid window | shrink first, then record |
| Length `right - left` | off by one | `right - left + 1` |
| Fixed window recorded too early | `best` taken on a window smaller than k | record only when `right >= k - 1` |
| Forget to update the state when `left` moves | counts drift, wrong answer | every `left++` has its `remove(left)` |
| Keep a letter whose count fell to 0 in the map | `map.size()` stays too big | `if (count == 0) map.remove(key)` |
| Using it with negative numbers and "sum = k" | misses answers | prefix sums + hashing |

## 9. How to recognize it

- "**Contiguous** subarray", "substring", "consecutive elements".
- "Longest", "shortest", "maximum / minimum ... of a piece".
- "Of size k", "every k consecutive".
- "At most k ...", "no more than k ...".
- Your brute force has two loops `start` / `end` over the same array.

If the piece does **not** need to be contiguous (subsequence), it is not a sliding window:
think DP or backtracking.

## 10. Practice

Exercises: [`patterns/slidingwindow/SlidingWindowExercises.java`](../../../src/main/java/com/mastery/interview/patterns/slidingwindow/SlidingWindowExercises.java)

```bash
mvn -Dtest='SlidingWindowExercisesTest' test
```

Before coding each one, write on paper: **fixed or variable window? what state do I keep for
the window (sum, counts, number of zeros...)? when is the window invalid? what do I record,
and when?**
