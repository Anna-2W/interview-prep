# Pattern 9. Heap (top K, k-way merge, two heaps)

🇫🇷 [Version française](../../fr/patterns/09-heap.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Many problems need, again and again, **the smallest (or biggest) element** of a group that
keeps changing: "the k largest", "the k closest", "always process the cheapest task next",
"merge k sorted lists", "the median so far".

The brute force options are both slow:

| Brute force | Cost |
|---|---|
| Sort everything, take the first k | O(n log n), and you must keep all n elements |
| Keep a list, scan it to find the smallest each time | O(n) per query, O(n²) for n queries |

## 2. The key idea

A **heap** (`PriorityQueue` in Java) is a structure that always knows its smallest element:

| Operation | Cost |
|---|---|
| `peek()`: look at the smallest | O(1) |
| `offer(x)`: add | O(log n) |
| `poll()`: remove the smallest | O(log n) |

**What it looks like inside.** A heap is an **array** read as a tree. The rule: every parent is
`<=` its children (min-heap). So the smallest is always at index 0, the root.

```
 array  [1, 3, 8, 5]           tree view
 index   0  1  2  3                  1          index 0
                                   /   \
 parent of i   = (i - 1) / 2      3     8       index 1, 2
 children of i = 2i + 1, 2i + 2  /
                                5               index 3
```

The array is **not sorted**: only "parent <= children" is guaranteed.

- `offer(x)`: put `x` at the end of the array, then **sift up**: while `x` is smaller than its
  parent, swap them. At most one swap per level: O(log n).
- `poll()`: take the root, move the **last** element to the root, then **sift down**: while it
  is bigger than its smallest child, swap them. O(log n).

The real inner array of a `PriorityQueue<Integer>`, step by step:

| Operation | What happens | Array after |
|---|---|---|
| `offer(5)` | first element | [5] |
| `offer(3)` | 3 < parent 5: swap | [3, 5] |
| `offer(8)` | 8 > parent 3: stays | [3, 5, 8] |
| `offer(1)` | at index 3, parent 5: swap; then parent 3: swap | [1, 3, 8, 5] |
| `poll()` → 1 | last (5) goes to the root, smallest child is 3: swap | [3, 5, 8] |

## 3. Step by step on an example

The **3 largest** values of `[4, 1, 7, 3, 8, 5]`.

Idea: keep a **min**-heap of size 3. Its root is the **weakest** of the 3 best values so far.
When a 4th value comes in, remove the root: the weakest one leaves.

| x | Action | Real array | Sorted view | Root (weakest of the kept) |
|---|---|---|---|---|
| 4 | offer | [4] | [4] | 4 |
| 1 | offer | [1, 4] | [1, 4] | 1 |
| 7 | offer | [1, 4, 7] | [1, 4, 7] | 1 |
| 3 | offer, size 4 > 3, poll 1 | [3, 4, 7] | [3, 4, 7] | 3 |
| 8 | offer, poll 3 | [4, 8, 7] | [4, 7, 8] | 4 |
| 5 | offer, poll 4 | [5, 8, 7] | [5, 7, 8] | 5 |

Answer: **5, 7, 8**. Notice the real array `[5, 8, 7]` is not sorted: printing a
`PriorityQueue` does **not** show the elements in order.

## 4. The template, line by line

```java
static List<Integer> kLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    for (int x : nums) {
        heap.offer(x);
        if (heap.size() > k) {
            heap.poll();
        }
    }
    return new ArrayList<>(heap);
}
```

| Line | Why |
|---|---|
| `new PriorityQueue<>()` | a **min**-heap: the root is the smallest of the kept values |
| `heap.offer(x)` | every value gets a chance, O(log k) |
| `if (heap.size() > k)` | the heap never holds more than k values: memory O(k) |
| `heap.poll()` | remove the smallest: it cannot be in the top k any more |
| `new ArrayList<>(heap)` | the k largest, in **no particular order**. Poll them one by one if you need them sorted |

**Why a min-heap to find the largest?** The question at each step is "should the new value
replace the **weakest** of my current top k?" The weakest is the smallest, so it must be at the
root, where it can be checked and removed in O(log k). A max-heap would keep the biggest at the
root, which is the one you want to **keep**: you would have to store all n values, O(n log n)
time and O(n) memory.

**Cost**: O(n log k) time, O(k) space. When k is small, that is almost O(n).

## 5. Why it is correct

**Invariant**: after reading the first `i` values, the heap holds the `min(i, k)` largest of
them.

When `x` arrives, the k + 1 candidates are the old top k plus `x`. The smallest of these k + 1
cannot be in the new top k, and `poll()` removes exactly that one. So the invariant still holds.

## 6. The variants

| Sub-pattern | Heap | Idea | Cost |
|---|---|---|---|
| Top K largest | min-heap of size k | poll when size > k | O(n log k) |
| Top K smallest | max-heap of size k | same, reversed | O(n log k) |
| K-way merge | min-heap of k "heads" | poll the smallest head, push the next element of its list | O(N log k) for N elements |
| Two heaps | max-heap (low half) + min-heap (high half) | both tops are around the middle | O(log n) per add |

**K-way merge, traced.** Merge `[2, 9]`, `[1, 5]`, `[3]`. The heap holds one element per list
(the next one not used yet), with the number of its list.

| Take | From list | Push next of that list | Heap after (sorted view) | Output |
|---|---|---|---|---|
| start | | | 1 (list 1), 2 (list 0), 3 (list 2) | |
| 1 | 1 | 5 | 2 (list 0), 3 (list 2), 5 (list 1) | 1 |
| 2 | 0 | 9 | 3 (list 2), 5 (list 1), 9 (list 0) | 1, 2 |
| 3 | 2 | nothing left | 5 (list 1), 9 (list 0) | 1, 2, 3 |
| 5 | 1 | nothing left | 9 (list 0) | 1, 2, 3, 5 |
| 9 | 0 | nothing left | empty | 1, 2, 3, 5, 9 |

The heap never holds more than k elements, one per list.

**Two heaps, traced.** Keep the smaller half in a **max**-heap `low` and the bigger half in a
**min**-heap `high`, with `low` the same size as `high` or one bigger. The middle is at the top
of the heaps. Numbers `2, 8, 4, 6`:

| x | low (sorted view) | high (sorted view) | Middle |
|---|---|---|---|
| 2 | [2] | [] | 2 |
| 8 | [2] | [8] | (2 + 8) / 2 = 5.0 |
| 4 | [2, 4] | [8] | 4 (top of low) |
| 6 | [2, 4] | [6, 8] | (4 + 6) / 2 = 5.0 |

**Writing the comparator**:

| Order | Code |
|---|---|
| smallest first (default) | `new PriorityQueue<>()` |
| biggest first | `new PriorityQueue<>(Comparator.reverseOrder())` |
| by a computed number | `new PriorityQueue<>(Comparator.comparingInt(p -> score(p)))` |
| `int[]` by its first value | `new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]))` |

## 7. A problem solved from start to finish

**Problem (last stone)**: each turn, take the **two heaviest** stones and smash them. Same
weight: both disappear. Different: the lighter one disappears and the heavier one keeps the
difference. Return the weight of the last stone, or 0 if none is left.
`lastStone([2, 7, 4, 1, 8, 1])` → `1`.

1. **Brute force**: sort the list at every turn to find the two heaviest. O(n² log n).
2. **Which question repeats?** "What are the two biggest right now?", while new stones come
   back in. That is a **max**-heap.
3. **Algorithm**: put every stone in a max-heap. While there are at least 2: poll two, push back
   the difference if it is not 0.

| Turn | Smash | Push back | Heap after (sorted view, biggest first) |
|---|---|---|---|
| start | | | 8, 7, 4, 2, 1, 1 |
| 1 | 8 and 7 | 1 | 4, 2, 1, 1, 1 |
| 2 | 4 and 2 | 2 | 2, 1, 1, 1 |
| 3 | 2 and 1 | 1 | 1, 1, 1 |
| 4 | 1 and 1 | nothing (0) | 1 |

One stone left: **1**.

```java
static int lastStone(int[] stones) {
    PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int s : stones) {
        heap.offer(s);
    }
    while (heap.size() > 1) {
        int first = heap.poll();
        int second = heap.poll();
        if (first != second) {
            heap.offer(first - second);
        }
    }
    return heap.isEmpty() ? 0 : heap.peek();
}
```

4. **Cost**: O(n log n) time (at most n turns, each O(log n)), O(n) space.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| Print the heap to check the order | `[5, 8, 7]`: looks wrong but is fine | only `peek()` / `poll()` give the order |
| Loop over the heap with for-each expecting sorted values | values come in array order | `poll()` until empty |
| Max-heap for "k largest" | you keep all n values, O(n log n), O(n) memory | min-heap of size k |
| Comparator `(a, b) -> b - a` | wrong order with big or negative numbers (overflow) | `Comparator.reverseOrder()` or `Integer.compare(b, a)` |
| `int x = heap.poll()` on an empty heap | `NullPointerException` (unboxing `null`) | check `isEmpty()` first |
| Change an object after putting it in the heap | the heap order is broken | remove it, change it, offer it again |
| `heap.remove(x)` in a loop | O(n) each, the loop becomes O(n²) | design so you only ever `poll()` the root |

## 9. How to recognize it

- "The k largest / smallest / most frequent / closest".
- "Always take the cheapest / earliest / biggest next", while new elements keep arriving.
- "Merge k sorted lists / arrays / files".
- "Median of a stream", "middle value so far".
- You are tempted to **sort again and again** inside a loop: a heap usually removes that.

## 10. Practice

Exercises: [`patterns/heap/HeapExercises.java`](../../../src/main/java/com/mastery/interview/patterns/heap/HeapExercises.java)

```bash
mvn -Dtest='HeapExercisesTest' test
```

Before coding each one, write on paper: **min-heap or max-heap? what is in the heap (a value,
an index, a pair)? what is the comparator? what is the maximum size of the heap?** Then trace
the heap content on the example, like the table in section 3, using the sorted view.
