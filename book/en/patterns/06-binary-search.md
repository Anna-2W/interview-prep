# Pattern 6. Binary search

🇫🇷 [Version française](../../fr/patterns/06-binary-search.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

"Find a value in a **sorted** array." The brute force checks every box:

```java
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == target) {
        return i;
    }
}
return -1;
```

That is **O(n)**: with a billion values, a billion checks. And it ignores the most useful
fact: the array is **sorted**.

The same waste appears in another form: "find the **smallest value** that works" (a speed,
a capacity, a size...) by trying 1, 2, 3, 4... until one works.

## 2. The key idea

Look at the **middle**. Because the array is sorted, one comparison tells you which **half**
cannot contain the answer: throw it away.

```
 search 23 in [2 5 8 12 16 23 38 56 72 91]

 [2  5  8  12  16  23  38  56  72  91]     middle 16 < 23: keep the right half
                  [23  38  56  72  91]     middle 56 > 23: keep the left half
                  [23  38]                 middle 23: found
```

Each step **halves** the search space: 1 000 000 → 500 000 → ... → 1 in about **20** steps.
A billion values need about **30**. That is **O(log n)**.

## 3. Step by step on an example

Search **23** in `[2, 5, 8, 12, 16, 23, 38, 56, 72, 91]` (indexes 0 to 9).
`mid = left + (right - left) / 2`.

| Step | left | right | mid | nums[mid] | Decision |
|---|---|---|---|---|---|
| 1 | 0 | 9 | 4 | 16 | 16 < 23: answer is on the right, `left = 5` |
| 2 | 5 | 9 | 7 | 56 | 56 > 23: answer is on the left, `right = 6` |
| 3 | 5 | 6 | 5 | 23 | **found at index 5** |

3 checks instead of 6.

Now search **20**, which is **absent**:

| Step | left | right | mid | nums[mid] | Decision |
|---|---|---|---|---|---|
| 1 | 0 | 9 | 4 | 16 | 16 < 20: `left = 5` |
| 2 | 5 | 9 | 7 | 56 | 56 > 20: `right = 6` |
| 3 | 5 | 6 | 5 | 23 | 23 > 20: `right = 4` |
| end | 5 | 4 | | | `left > right`: the range is empty, **return -1** |

Notice where `left` ends: **5**, exactly where 20 would have to be inserted to keep the array
sorted. Remember this, it is the idea behind "lower bound" (section 6).

## 4. The template, line by line

```java
int left = 0;
int right = nums.length - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
return -1;
```

| Line | Why |
|---|---|
| `right = nums.length - 1` | the range `[left, right]` includes **both** ends |
| `while (left <= right)` | a range with one element (`left == right`) still has to be checked |
| `left + (right - left) / 2` | same as `(left + right) / 2`, but `left + right` can overflow an `int` |
| `nums[mid] == target` | found |
| `left = mid + 1` | `mid` is too small and so is everything on its left: drop them **and** `mid` |
| `right = mid - 1` | `mid` is too big and so is everything on its right |
| `return -1` | the range became empty: the value is not there |

## 5. Why it is correct

**Invariant**: if `target` is in the array, it is inside `[left, right]`.

- At the start, `[0, n - 1]` is the whole array: true.
- When `nums[mid] < target`, everything at `mid` or before is `<= nums[mid] < target`
  (the array is sorted), so the target can only be after `mid`: `left = mid + 1` keeps the
  invariant true. Same reasoning on the other side.
- Each step removes at least `mid` itself, so the range always shrinks: the loop ends.
- If it ends with `left > right`, the range is empty, and by the invariant the target is not
  in the array.

**Cost**: O(log n) time, O(1) memory.

## 6. Variants

### Lower bound: the first position where `nums[i] >= target`

Very useful: "where to insert", "first occurrence", "how many values are smaller".
The template changes in **three** places:

```java
int lo = 0;
int hi = nums.length;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] < target) {
        lo = mid + 1;
    } else {
        hi = mid;
    }
}
return lo;
```

| Change | Why |
|---|---|
| `hi = nums.length` (not `- 1`) | the answer can be `n`: "after every element" |
| `while (lo < hi)` | stop when `lo == hi`: one candidate left, and it is the answer |
| `hi = mid` (not `mid - 1`) | `mid` is big enough, so it **can be** the answer: keep it |

**Invariant**: the answer is always inside `[lo, hi]`. When `lo == hi`, there is only one
possible place left.

Trace: first position of **3** in `[1, 3, 3, 3, 5, 8]`.

| Step | lo | hi | mid | nums[mid] | Decision |
|---|---|---|---|---|---|
| 1 | 0 | 6 | 3 | 3 | 3 >= 3, big enough: `hi = 3` |
| 2 | 0 | 3 | 1 | 3 | 3 >= 3, big enough: `hi = 1` |
| 3 | 0 | 1 | 0 | 1 | 1 < 3, too small: `lo = 1` |
| end | 1 | 1 | | | `lo == hi`: answer **1** |

The classic search could have stopped on any of the three 3s. Lower bound always finds the
**first** one.

**Upper bound** (first position where `nums[i] > target`): same code with `nums[mid] <= target`.
Then the number of times `target` appears is `upperBound - lowerBound`.

### Binary search on the answer

Sometimes there is no array to search: you search a **number** (a speed, a size, a
capacity...). It works when the question is **monotonic**:

```
 value       1    2    3    4    5    6    7
 works?      no   no   no   yes  yes  yes  yes
                            ▲
                            the smallest value that works
```

If a value works, every bigger value works too. So "works?" looks like
`no no no yes yes yes`: a sorted array of answers. Binary search the **first yes**, exactly
like lower bound:

```java
int lo = smallestPossible;
int hi = biggestPossible;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (works(mid)) {
        hi = mid;
    } else {
        lo = mid + 1;
    }
}
return lo;
```

The cost is O(log(range)) calls to `works`. Section 7 uses it.

### Which template?

| Question | Range | Loop | Moves | Returns |
|---|---|---|---|---|
| "is `target` there? where?" | `[0, n - 1]` | `left <= right` | `mid + 1` / `mid - 1` | `mid`, or `-1` |
| "first position with `nums[i] >= target`" | `[0, n]` | `lo < hi` | `mid + 1` / `hi = mid` | `lo` |
| "smallest value that works" | `[min, max]` of possible answers | `lo < hi` | `mid + 1` / `hi = mid` | `lo` |

Pick one per question and **always write it the same way**. Most binary search bugs come
from mixing pieces of two templates.

Why `lo < hi` with `hi = mid` never loops forever: `mid` rounds **down**, so `mid < hi`
always, and `hi = mid` makes the range smaller. But `lo = mid` (instead of `mid + 1`) can
loop forever when `hi = lo + 1`.

## 7. A problem solved from start to finish

**Problem (ship packages)**: packages with these weights must be shipped **in this order**.
Each day the ship is loaded with the next packages until adding one more would exceed its
capacity. Return the **smallest capacity** that ships everything within `days` days.
`shipWithinDays([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 5)` → `15`.

1. **Brute force**: try capacity 1, 2, 3... and simulate each one, O(sum × n).
2. **Is it monotonic?** If capacity `c` ships everything in time, a bigger ship does too.
   Yes: binary search on the answer.
3. **Bounds**: the ship must carry the heaviest package, so `lo = max weight = 10`. With
   `hi = sum of all weights = 55`, everything leaves on day 1.
4. **The check `works(c)`**: count the days needed with capacity `c` (one pass, O(n)), and
   compare with `days`.

| Step | lo | hi | mid | Days needed with mid | Decision |
|---|---|---|---|---|---|
| 1 | 10 | 55 | 32 | 2 | 2 <= 5, works: `hi = 32` |
| 2 | 10 | 32 | 21 | 3 | works: `hi = 21` |
| 3 | 10 | 21 | 15 | 5 | works: `hi = 15` |
| 4 | 10 | 15 | 12 | 6 | 6 > 5, too small: `lo = 13` |
| 5 | 13 | 15 | 14 | 6 | too small: `lo = 15` |
| end | 15 | 15 | | | answer **15** |

Check: with 15, the days are `[1..5] = 15`, `[6, 7] = 13`, `[8]`, `[9]`, `[10]`: 5 days.
With 14 it takes 6 days.

```java
static int daysNeeded(int[] weights, int capacity) {
    int days = 1;
    int load = 0;
    for (int w : weights) {
        if (load + w > capacity) {
            days++;
            load = 0;
        }
        load += w;
    }
    return days;
}

static int shipWithinDays(int[] weights, int days) {
    int lo = 0;
    int hi = 0;
    for (int w : weights) {
        lo = Math.max(lo, w);
        hi += w;
    }
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (daysNeeded(weights, mid) <= days) {
            hi = mid;
        } else {
            lo = mid + 1;
        }
    }
    return lo;
}
```

5. **Cost**: O(n × log(sum)): 5 checks of 10 packages here, instead of trying 6 capacities
   one by one from 10 to 15 (and far more on big inputs).

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| `(left + right) / 2` | negative `mid` on huge indexes or values (overflow) | `left + (right - left) / 2` |
| `lo = mid` with `while (lo < hi)` | infinite loop when `hi = lo + 1` | `lo = mid + 1` |
| `while (left < right)` with `right = mid - 1` | the last remaining element is never checked | `left <= right` for the classic search |
| `hi = n - 1` for a lower bound | wrong when the target is bigger than everything (answer is `n`) | `hi = n` |
| Array not sorted | random answers | sort first, or use another pattern |
| `works` is not monotonic | the binary search jumps over the answer | check: "if x works, does x + 1 work?" |
| Bad bounds for the answer (`lo = 0` for a speed) | division by zero, or the answer is outside `[lo, hi]` | `lo` = smallest **valid** value, `hi` = a value that surely works |
| `mid * mid` or a sum in `int` | overflow inside `works` | `long` |

## 9. How to recognize it

- The array is **sorted** (or sorted then rotated).
- "In O(log n)".
- "Find the **first** / **last** position where...".
- "The **minimum** speed / capacity / size such that..." or "the **maximum** value such
  that...": the answer is a number in a range, and "does x work?" is easy to check.
- The input is huge (10⁹), so even O(n) on the range of answers is too slow.

## 10. Practice

Exercises: [`patterns/binarysearch/BinarySearchExercises.java`](../../../src/main/java/com/mastery/interview/patterns/binarysearch/BinarySearchExercises.java)

```bash
mvn -Dtest='BinarySearchExercisesTest' test
```

Before coding each one, write on paper: **what is my search space (indexes or values)?
what are `lo` and `hi` at the start? which template (`<=` or `<`)? what question do I ask
about `mid`, and which half do I keep? can the range stop shrinking?** Then trace 3 steps
by hand with a table like the ones above.
