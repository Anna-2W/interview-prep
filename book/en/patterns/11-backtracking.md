# Pattern 11. Backtracking

🇫🇷 [Version française](../../fr/patterns/11-backtracking.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Some problems ask for **every** solution: all combinations, all orders, all ways to fill a
grid, all valid strings...

Example: list every pair of numbers taken from `1..3`: `[1, 2]`, `[1, 3]`, `[2, 3]`.

For pairs, two nested loops are enough:

```java
for (int a = 1; a <= n; a++) {
    for (int b = a + 1; b <= n; b++) {
        result.add(List.of(a, b));
    }
}
```

But "every group of **k** numbers" would need **k nested loops**, and k is only known when the
program runs. You cannot write a variable number of loops. Generating everything (all `2ⁿ`
groups) and filtering at the end works, but wastes time on groups that were wrong from the
start.

## 2. The key idea

Build a solution **one choice at a time**, with **recursion** playing the role of the nested
loops. At each step:

1. **Choose**: add one element to the current solution.
2. **Explore**: call yourself to make the next choices.
3. **Undo**: remove that element, so you can try the next option.

All the possible choices form a **decision tree**. Backtracking walks this tree depth first.
Groups of 2 numbers out of `1..3`:

```
                       []
           /           |          \
        [1]           [2]          [3]
       /    \          |
   [1, 2]  [1, 3]    [2, 3]
    save    save      save
```

Each level of the tree is one choice. Each path from the top to a "save" is one solution.
Going back up a branch is the **undo** ("back-track").

## 3. Step by step on an example

All groups of **k = 2** numbers out of `1..3`. Depth = how many numbers are already chosen
(the level in the tree, and how many recursive calls are open).

| Step | Action | Depth | current | result |
|---|---|---|---|---|
| 1 | choose 1 | 0 | [1] | [] |
| 2 | choose 2 | 1 | [1, 2] | [] |
| 3 | size = k: **save** | 2 | [1, 2] | [[1, 2]] |
| 4 | undo 2 | 1 | [1] | [[1, 2]] |
| 5 | choose 3 | 1 | [1, 3] | [[1, 2]] |
| 6 | **save** | 2 | [1, 3] | [[1, 2], [1, 3]] |
| 7 | undo 3 | 1 | [1] | [[1, 2], [1, 3]] |
| 8 | undo 1 | 0 | [] | [[1, 2], [1, 3]] |
| 9 | choose 2 | 0 | [2] | [[1, 2], [1, 3]] |
| 10 | choose 3 | 1 | [2, 3] | [[1, 2], [1, 3]] |
| 11 | **save** | 2 | [2, 3] | [[1, 2], [1, 3], [2, 3]] |
| 12 | undo 3 | 1 | [2] | [[1, 2], [1, 3], [2, 3]] |
| 13 | undo 2 | 0 | [] | [[1, 2], [1, 3], [2, 3]] |
| 14 | choose 3 | 0 | [3] | [[1, 2], [1, 3], [2, 3]] |
| 15 | undo 3 | 0 | [] | [[1, 2], [1, 3], [2, 3]] |

Look at step 14: from `[3]` there is no bigger number left, so this branch can never reach
size 2. It was a **useless** branch: see pruning in section 6.

`current` is **one single list** that grows and shrinks. The call stack remembers where we are:
when a call ends, we come back to the loop of the level above, which tries its next number.

## 4. The template, line by line

```java
static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(n, k, 1, new ArrayList<>(), result);
    return result;
}

static void backtrack(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i <= n; i++) {
        current.add(i);
        backtrack(n, k, i + 1, current, result);
        current.removeLast();
    }
}
```

| Line | Why |
|---|---|
| `if (current.size() == k)` | **base case**: the solution is complete. Without it, the recursion never stops |
| `result.add(new ArrayList<>(current))` | save a **copy** (see below) |
| `return` | a complete solution has no more choices to make |
| `for (int i = start; ...)` | the choices at this level. Starting at `start` avoids taking `[2, 1]` after `[1, 2]` |
| `current.add(i)` | **choose** |
| `backtrack(..., i + 1, ...)` | **explore**: the next numbers must be bigger than `i` |
| `current.removeLast()` | **undo**: put `current` back as it was before the choice |

### Why `new ArrayList<>(current)` and not `current`?

There is only **one** `current` list during the whole search. If you save it directly, the
result holds three references to that same list, and at the end that list is empty:

| Code at the "save" line | Final result |
|---|---|
| `result.add(current)` | `[[], [], []]` |
| `result.add(new ArrayList<>(current))` | `[[1, 2], [1, 3], [2, 3]]` |

## 5. Why it is correct

**Invariant**: when `backtrack` starts, `current` holds exactly the choices made on the path
from the root of the tree to this node. When it returns, `current` is **exactly as it was**
when it started, because every `add` is followed by its `removeLast`.

So the loop of each level can safely try its next choice: the level below has cleaned up
after itself. Every path of the tree is visited once, so every solution is saved once.

## 6. Variants and pruning

The template stays the same. What changes is **when you save** and **which choices** the
loop offers:

| Problem | When to save | Choices at each level |
|---|---|---|
| Groups of exactly k (combinations) | when `size == k` | numbers after the last one taken (`start = i + 1`) |
| Every group of any size | at **every** node, not only the leaves | numbers after the last one taken |
| Every order (arrangements) | when every element is used | every element not used yet (track them with a `boolean[] used`) |
| A number can be taken again | when the target is reached | from the **same** index (`start = i`) |
| Input with duplicates | as usual | sort first, then skip `if (i > start && nums[i] == nums[i - 1]) continue;` |
| Puzzles (N queens, sudoku) | when the board is full | only the moves that keep the board valid |

**Pruning** means not entering a branch that cannot lead to a solution. Here: if there are
not enough numbers left to reach size k, stop the loop early.

```java
for (int i = start; i <= n - (k - current.size()) + 1; i++) {
```

| Search | Calls without pruning | Calls with pruning |
|---|---|---|
| k = 2 out of 3 | 7 | 6 |
| k = 8 out of 10 | 1 013 | 165 |
| k = 18 out of 20 | 1 048 555 | 1 330 |

The answer is the same, the tree is much smaller. Pruning is often what separates a timeout
from a pass.

### Cost

Cost = **number of leaves × work per leaf** (copying a solution costs its length).

| Search | Leaves | n = 10 | n = 20 |
|---|---|---|---|
| Groups of any size | 2ⁿ | 1 024 | about 1 million |
| Every order | n! | 3 628 800 | about 2.4 × 10¹⁸ (impossible) |
| Groups of exactly 3 | C(n, 3) | 120 | 1 140 |

That is why backtracking problems come with a **small n** (≤ 20 for 2ⁿ, ≤ 10 for n!).

## 7. A problem solved from start to finish

**Problem (letter case permutations)**: return every string you can make by changing each
letter of `s` to lower or upper case. Digits stay as they are.
`"a1b"` → `["a1b", "a1B", "A1b", "A1B"]`.

1. **One choice per position**: a letter has 2 options (lower, upper), a digit has 1.
2. **Complete when** every position is decided: save.
3. **Undo**: here we work on a `char[]` and **overwrite** the position with the other case;
   writing over it plays the role of the undo. `new String(chars)` is the copy.

Decision tree:

```
                    index 0: 'a'
              /                    \
           a1b                      A1b
            |  index 1: '1'          |
           a1b                      A1b
         /     \   index 2: 'b'   /     \
       a1b     a1B             A1b     A1B
      save    save            save    save
```

| Step | Action | Index (depth) | chars | result |
|---|---|---|---|---|
| 1 | lower a | 0 | a1b | [] |
| 2 | keep 1 | 1 | a1b | [] |
| 3 | lower b | 2 | a1b | [] |
| 4 | **save** | 3 | a1b | [a1b] |
| 5 | upper B | 2 | a1B | [a1b] |
| 6 | **save** | 3 | a1B | [a1b, a1B] |
| 7 | upper A | 0 | A1B | [a1b, a1B] |
| 8 | keep 1 | 1 | A1B | [a1b, a1B] |
| 9 | lower b | 2 | A1b | [a1b, a1B] |
| 10 | **save** | 3 | A1b | [a1b, a1B, A1b] |
| 11 | upper B | 2 | A1B | [a1b, a1B, A1b] |
| 12 | **save** | 3 | A1B | [a1b, a1B, A1b, A1B] |

```java
static List<String> letterCasePermutations(String s) {
    List<String> result = new ArrayList<>();
    build(s.toCharArray(), 0, result);
    return result;
}

static void build(char[] chars, int index, List<String> result) {
    if (index == chars.length) {
        result.add(new String(chars));
        return;
    }
    if (Character.isLetter(chars[index])) {
        chars[index] = Character.toLowerCase(chars[index]);
        build(chars, index + 1, result);
        chars[index] = Character.toUpperCase(chars[index]);
        build(chars, index + 1, result);
    } else {
        build(chars, index + 1, result);
    }
}
```

4. **Cost**: 2^L leaves (L = number of letters), each copied in O(n): O(2^L × n) time,
   O(n) extra space for the recursion.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| `result.add(current)` | every saved solution is empty at the end | `result.add(new ArrayList<>(current))` |
| Forget `current.removeLast()` | solutions get longer and longer, mixed up | every `add` needs its `removeLast` after the call |
| No base case, or a wrong one | `StackOverflowError` | write the "complete" test first |
| Loop starts at 0 instead of `start` | the same group in several orders: `[1, 2]` and `[2, 1]` | start at `start`, pass `i + 1` |
| Pass `start + 1` instead of `i + 1` | wrong or repeated groups | the next choice depends on `i`, the one just taken |
| Duplicates in the input, no skip | duplicate solutions | sort, then skip equal neighbors at the same level |
| Modify the input array without restoring it | later branches see a broken input | restore it, like `current` |
| No pruning | timeout on bigger inputs | stop a branch as soon as it cannot succeed |

## 9. How to recognize it

- "Return **all** / **every** / list all possible..." combinations, subsets, orders, strings,
  paths, boards.
- The input is **small** (n ≤ 20, often ≤ 10).
- Constraint puzzles: sudoku, N queens, word search in a grid.
- If the problem only asks **how many** ways or the **best** one, and the same sub-problems come
  back again and again, think dynamic programming (chapter 07) instead.

## 10. Practice

Exercises: [`patterns/backtracking/BacktrackingExercises.java`](../../../src/main/java/com/mastery/interview/patterns/backtracking/BacktrackingExercises.java)

```bash
mvn -Dtest='BacktrackingExercisesTest' test
```

Before coding each one, draw the top of the decision tree on paper and write: **what is one
choice at each level? When is a solution complete (save)? What do I undo? Where does the loop
start?**
