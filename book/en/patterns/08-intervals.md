# Pattern 8. Intervals

🇫🇷 [Version française](../../fr/patterns/08-intervals.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

The input is a list of intervals `[start, end]`: meetings, bookings, time slots, ranges of
numbers. The question is about how they **overlap**: merge them, count them, find free time,
find conflicts.

The brute force compares **every pair** of intervals:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (overlap(intervals[i], intervals[j])) {
            ...
        }
    }
}
```

That is **O(n²)** pairs. Worse, when you merge two intervals the result can overlap others, so
you may have to start the comparisons again.

## 2. The key idea

**Sort the intervals by start.** Then you only need to compare each interval with **the
current block** (the last merged one), never with the whole list.

```
 time   1   2   3   4   5   6   7   8
 [1,3]  |-------|
 [2,5]      |-----------|
 [6,7]                  |---|
 [7,8]                      |---|

 sorted by start, read from left to right:
 block [1,3] -> [2,5] starts before 3 ends: same block [1,5]
 block [1,5] -> [6,7] starts after 5: block [1,5] is finished, new block [6,7]
 block [6,7] -> [7,8] starts at 7, touches: same block [6,8]
```

**The overlap condition.** Two intervals `[a, b]` and `[c, d]` overlap when each one starts
before the other ends:

```
 a <= d  &&  c <= b       (touching counts as overlapping)
 a <  d  &&  c <  b       (touching does not count: a meeting ending at 10 and one starting at 10 are fine)
```

Once the list is sorted, `c >= a` is always true, so the test becomes just **`c <= b`**: "does
the next one start before the current block ends?"

**Why sorting makes the check local**: if the next interval starts **after** the current block
ends, every later interval starts even later (they are sorted), so none of them can touch the
current block. The block is finished, you never look back.

## 3. Step by step on an example

Total **length of time covered** by `[[1, 3], [2, 5], [7, 8], [6, 7]]` (a time covered twice
counts once). After sorting by start: `[[1, 3], [2, 5], [6, 7], [7, 8]]`.

| Step | Interval | Starts before the block ends? | Block after | Total after |
|---|---|---|---|---|
| start | [1, 3] | first one | [1, 3] | 0 |
| 1 | [2, 5] | 2 <= 3, yes | [1, 5] | 0 |
| 2 | [6, 7] | 6 <= 5, no: close [1, 5], add 4 | [6, 7] | 4 |
| 3 | [7, 8] | 7 <= 7, yes | [6, 8] | 4 |
| end | | close [6, 8], add 2 | | **6** |

Answer: **6** (from 1 to 5, then from 6 to 8).

## 4. The template, line by line

```java
static int totalCovered(int[][] intervals) {
    int[][] sorted = intervals.clone();
    Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
    int start = sorted[0][0];
    int end = sorted[0][1];
    int total = 0;
    for (int i = 1; i < sorted.length; i++) {
        if (sorted[i][0] <= end) {
            end = Math.max(end, sorted[i][1]);
        } else {
            total += end - start;
            start = sorted[i][0];
            end = sorted[i][1];
        }
    }
    total += end - start;
    return total;
}
```

| Line | Why |
|---|---|
| `intervals.clone()` | do not reorder the caller's array |
| `Arrays.sort(..., Integer.compare(a[0], b[0]))` | sort by start. `Integer.compare` never overflows, `a[0] - b[0]` can |
| `start`, `end` | the **current block**, the only thing you compare with |
| `sorted[i][0] <= end` | the overlap test after sorting (`<` if touching does not count) |
| `Math.max(end, sorted[i][1])` | the next interval can end **before** the block (it is inside it): never shrink the block |
| `else` branch | the block is finished: use it (here, add its length), then start a new block |
| `total += end - start` after the loop | the **last** block is never closed inside the loop |

**Cost**: O(n log n) for the sort, then O(n) for the pass.

## 5. Why it is correct

**Invariant**: after step `i`, `[start, end]` is the union of all the intervals read so far
that are connected to the last one, and every block closed before it is final.

The block is closed only when the next start is `> end`. Since the starts only grow, no later
interval can reach back into it. So no overlap is ever missed, and no time is counted twice.

## 6. The variants

| Problem | Sort by | What changes |
|---|---|---|
| Merge, total length, free time between blocks | start | what you do when a block closes (save it, add its length, record the gap) |
| Do any two overlap? (one person, all meetings) | start | return `false` at the first overlap with the previous one |
| Insert into a list that is already sorted | nothing | three parts: before, overlapping (merge), after |
| Keep the most non-overlapping intervals | **end** | greedy, chapter 07 |
| Intersection of two sorted lists | already sorted | two pointers, section 7 |
| How many at the same time (sweep line) | events | see below |

**Sweep line with events.** Cut each interval into two events: `+1` at its start, `-1` at its
end. Sort the events by time; when two events have the same time, put the `-1` first (a meeting
that ends at 4 frees its room for one that starts at 4). Walk the events and keep a running
count: the count is "how many intervals are open right now".

Example `[[1, 4], [2, 6], [5, 7]]`:

| Time | Event | Open now |
|---|---|---|
| 1 | +1 | 1 |
| 2 | +1 | 2 |
| 4 | -1 | 1 |
| 5 | +1 | 2 |
| 6 | -1 | 1 |
| 7 | -1 | 0 |

The highest count (2) is the most intervals open at the same time.

## 7. A problem solved from start to finish

**Problem (interval intersections)**: two lists of intervals, each one sorted and without
overlaps inside it. Return the intervals where **both** lists are covered.

`a = [[0, 2], [5, 10], [13, 23], [24, 25]]`, `b = [[1, 5], [8, 12], [15, 24], [25, 26]]`
→ `[[1, 2], [5, 5], [8, 10], [15, 23], [24, 24], [25, 25]]`.

1. **Brute force**: compare every interval of `a` with every interval of `b`. O(n × m).
2. **Use the order**: both lists are sorted, so walk them together with two pointers `i` and `j`,
   like merging two sorted lists.
3. **Intersection of two intervals**: `lo = max(starts)`, `hi = min(ends)`. If `lo <= hi`,
   `[lo, hi]` is common.
4. **Which pointer moves?** The interval that **ends first** cannot meet anything else in the
   other list: move its pointer.

| a[i] | b[j] | lo = max(starts) | hi = min(ends) | Added | Move |
|---|---|---|---|---|---|
| [0, 2] | [1, 5] | 1 | 2 | [1, 2] | i (2 < 5) |
| [5, 10] | [1, 5] | 5 | 5 | [5, 5] | j |
| [5, 10] | [8, 12] | 8 | 10 | [8, 10] | i (10 < 12) |
| [13, 23] | [8, 12] | 13 | 12 | nothing (13 > 12) | j |
| [13, 23] | [15, 24] | 15 | 23 | [15, 23] | i (23 < 24) |
| [24, 25] | [15, 24] | 24 | 24 | [24, 24] | j |
| [24, 25] | [25, 26] | 25 | 25 | [25, 25] | i (25 < 26) |

```java
static int[][] intersect(int[][] a, int[][] b) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < a.length && j < b.length) {
        int lo = Math.max(a[i][0], b[j][0]);
        int hi = Math.min(a[i][1], b[j][1]);
        if (lo <= hi) {
            result.add(new int[] {lo, hi});
        }
        if (a[i][1] < b[j][1]) {
            i++;
        } else {
            j++;
        }
    }
    return result.toArray(new int[0][]);
}
```

5. **Cost**: O(n + m) time, no sort needed. O(1) extra space besides the result.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| Forget to sort | blocks merged in the wrong order, overlaps missed | `Arrays.sort` by start first |
| Sort with `a[0] - b[0]` | wrong order with very big or negative values (overflow) | `Integer.compare(a[0], b[0])` |
| `end = sorted[i][1]` instead of `Math.max` | an interval inside the block shrinks it | `Math.max(end, sorted[i][1])` |
| Forget the last block | the last merged interval is missing | handle it after the loop |
| `<` versus `<=` | touching intervals merged (or not) by mistake | read the statement: does touching count? |
| Change the input rows | the caller's intervals are modified | copy the row (`new int[] {s, e}`) before changing it |
| Return `List<int[]>` when `int[][]` is expected | does not compile | `list.toArray(new int[0][])` |

## 9. How to recognize it

- The input is a list of pairs `[start, end]`: meetings, bookings, ranges, time slots.
- Words: "overlap", "merge", "conflict", "free time", "at the same time", "cover".
- If the intervals are not sorted, your first line is almost always a sort by start.
- "How many at the same time" or "how many rooms": sweep line, or a heap of end times (pattern 9).

## 10. Practice

Exercises: [`patterns/intervals/IntervalExercises.java`](../../../src/main/java/com/mastery/interview/patterns/intervals/IntervalExercises.java)

```bash
mvn -Dtest='IntervalExercisesTest' test
```

Before coding each one, write on paper: **is the input sorted? sort by start or by end? does
touching count as overlapping? what do I do when a block closes?** Then draw the intervals on a
time line, like in section 2.
