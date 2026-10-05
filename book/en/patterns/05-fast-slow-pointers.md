# Pattern 5. Fast and slow pointers

🇫🇷 [Version française](../../fr/patterns/05-fast-slow-pointers.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

On a **linked list** you have no index and no `size()`: you only see the current node and
its `next`. Two classic questions are then hard:

- "Where is the **middle**?"
- "Does the list have a **cycle**?" (a `next` that points back to an earlier node, so the
  walk never reaches `null`)

The brute forces work, but cost a second pass or extra memory:

| Question | Brute force | Cost |
|---|---|---|
| Middle | walk once to count n, walk again n / 2 steps | 2 passes |
| Middle | copy the nodes into an `ArrayList`, take `get(n / 2)` | O(n) memory |
| Cycle | put every visited node in a `HashSet`; seen twice means a cycle | O(n) memory |

Interviewers then ask: "one pass, **O(1) memory**?"

## 2. The key idea

Put **two pointers** on the list and move them at **different speeds**:
`slow` moves **1** node per step, `fast` moves **2**.

```
 step 0   slow, fast
            ▼
           [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ null

 step 1          slow    fast
                  ▼       ▼
           [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ null

 step 2                 slow           fast
                         ▼              ▼
           [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ null
```

- **Middle**: `fast` always went twice as far as `slow`. When `fast` reaches the end,
  `slow` is halfway.
- **Cycle**: think of two runners on a circular track. The faster one ends up **lapping**
  the slower one: they meet. Without a cycle, `fast` simply reaches `null`.

## 3. Step by step on an example

A list with a cycle: `1 → 2 → 3 → 4 → 5 → 6`, and `6.next` points back to `3`.
The cycle is `3 → 4 → 5 → 6 → 3` (4 nodes).

```
 [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ [6]
                ▲                    │
                └────────────────────┘
```

The **gap** is the number of steps `fast` would need to walk to reach `slow` (it only makes
sense once both are in the cycle).

| Step | slow | fast | gap (fast → slow) | Comment |
|---|---|---|---|---|
| 0 | 1 | 1 | | both start at the head |
| 1 | 2 | 3 | | `slow` is not in the cycle yet |
| 2 | 3 | 5 | 2 | both in the cycle: from 5, two steps (5 → 6 → 3) reach `slow` |
| 3 | 4 | 3 | 1 | `fast` went 5 → 6 → 3 |
| 4 | 5 | 5 | **0** | **they meet: there is a cycle** |

Look at the gap: **2, then 1, then 0**. It goes down by exactly one each step. Section 5
explains why.

## 4. The template, line by line

```java
ListNode slow = head;
ListNode fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

| Line | Why |
|---|---|
| `slow = head; fast = head;` | both start at the same place, so "fast went twice as far" stays true |
| `fast != null` | stops on lists of **even** length (`fast` jumps past the last node) |
| `fast.next != null` | stops on lists of **odd** length (`fast` is on the last node), and protects `fast.next.next` from a `NullPointerException` |
| `slow = slow.next` | one step |
| `fast = fast.next.next` | two steps |

This is the skeleton. Each problem adds **one thing**: what to return after the loop, or a
check inside the loop (for a cycle: compare the two pointers **after** moving them).

## 5. Why it is correct

**Middle.** Invariant: after `s` steps, `slow` is `s` nodes from the head and `fast` is `2s`
nodes from the head. The loop stops when `fast` cannot make 2 more steps, that is when `2s`
reaches the end of the list. Then `s` is half of it.

**Cycle.** Two cases:

1. **No cycle**: `fast` walks the list twice as fast, reaches `null` and the loop stops. It
   never meets `slow`, because `fast` is always ahead.
2. **A cycle**: `fast` enters the cycle first and goes round. Later `slow` enters too. From
   that moment, at each step:
   - `fast` moves 2 nodes forward, `slow` moves 1 node forward;
   - so `fast` gets **1 node closer** to `slow`;
   - the gap goes `g, g - 1, g - 2, ... , 1, 0`.

   The gap goes down by **exactly** 1, so `fast` cannot jump over `slow`: it lands on it
   when the gap is 0. The gap is smaller than the cycle length, so they meet before `slow`
   finishes one full lap.

**Cost**: O(n) time, O(1) memory (two references, whatever the size of the list).

## 6. Variants

| Variant | How | Used for |
|---|---|---|
| Middle | the skeleton, then use `slow` | split a list in two, merge sort on a list |
| Cycle detection | after moving, `if (slow == fast)` there is a cycle | broken lists, infinite loops |
| Start of the cycle | after they meet, put one pointer back on the head, move both **1** step at a time: they meet at the first node of the cycle (Floyd) | "where does the cycle begin?" |
| Fixed gap | `fast` first goes **n** steps ahead, then both move 1 step at a time | n-th node from the end, in one pass |
| Palindrome list | find the middle, reverse the second half, compare the two halves | "does the list read the same both ways?" |
| Number sequences | replace `node.next` by a function `next(x)` | happy number (section 7) |
| Array as a list | value = index of the next "node" | find the duplicate in an array of values 1..n |

## 7. A problem solved from start to finish

**Problem (happy number)**: start from `n`, replace it by the **sum of the squares of its
digits**, and repeat. If you reach `1`, `n` is happy. Otherwise the numbers loop forever.
`isHappy(19)` → `true`, `isHappy(2)` → `false`.

```
 19 → 1² + 9² = 82 → 8² + 2² = 68 → 100 → 1                  happy
 2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 → 16 ...     loops forever
```

1. **Brute force**: keep every number seen in a `HashSet`; if one comes back, it loops.
   Works, but uses memory.
2. **See the linked list**: each number has exactly one "next" number. The sequence is a
   linked list built on the fly, and "loops forever" means "**has a cycle**". So use slow
   and fast: `slow` applies the function once, `fast` twice.
3. **Stop** when `fast` reaches `1` (happy), or when `slow == fast` (they met in a cycle
   that does not contain 1).

Trace for `n = 2`. `fast` starts one step ahead, so the loop condition is not true at once.

| Step | slow | fast |
|---|---|---|
| start | 2 | 4 |
| 1 | 4 | 37 |
| 2 | 16 | 89 |
| 3 | 37 | 42 |
| 4 | 58 | 4 |
| 5 | 89 | 37 |
| 6 | 145 | 89 |
| 7 | **42** | **42** |

They meet on 42, and `fast` is not 1: **not happy**.
For `n = 19`: start `slow = 19, fast = 82`, then `82 / 100`, then `68 / 1`: `fast` reached 1,
**happy**.

```java
static int sumOfSquares(int n) {
    int sum = 0;
    while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n /= 10;
    }
    return sum;
}

static boolean isHappy(int n) {
    int slow = n;
    int fast = sumOfSquares(n);
    while (fast != 1 && slow != fast) {
        slow = sumOfSquares(slow);
        fast = sumOfSquares(sumOfSquares(fast));
    }
    return fast == 1;
}
```

4. **Cost**: O(1) memory. The numbers quickly drop below 243 (the biggest sum of squares
   for a 3-digit number is 9² × 3), so the number of steps stays small.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| `fast.next.next` without checking `fast.next` | `NullPointerException` on odd or even lengths | `while (fast != null && fast.next != null)` |
| Check `slow == fast` **before** moving | true at once: both start on the head | move first, then compare |
| Compare values: `slow.val == fast.val` | false cycle found in a list with duplicates | compare the nodes: `slow == fast` |
| Start `fast = head.next` without thinking | middle shifted by one on even lengths | decide which middle you want, test with 4 nodes |
| Move `head` instead of a pointer | the start of the list is lost | always walk with `slow` / `fast` |
| Forget the empty list | `NullPointerException` on `head.next` | the loop condition already handles `head == null` |

## 9. How to recognize it

- **Linked list** + "middle", "cycle", "n-th from the end", "palindrome".
- "In one pass", "with O(1) extra memory" on a list.
- A sequence where each value gives the **next** one, and the question is "does it loop?".
- An array of values `1..n` used as "pointers" to other indexes.

## 10. Practice

Exercises (chapter 02): [`datastructures/LinkedListProblems.java`](../../../src/main/java/com/mastery/interview/datastructures/LinkedListProblems.java),
**E03** `middle`, **E04** `hasCycle`, **E06** `removeNthFromEnd`, **E08** `isPalindrome`.

```bash
mvn -Dtest=LinkedListProblemsTest test
```

Before coding each one, write on paper: **how far does each pointer move per step? when
exactly does the loop stop, for an odd and for an even length? do I compare nodes or
values? what do I return after the loop?** Then draw a 4-node and a 5-node list and move
the pointers by hand.
