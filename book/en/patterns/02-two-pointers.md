# Pattern 2. Two pointers

🇫🇷 [Version française](../../fr/patterns/02-two-pointers.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Some problems compare or move elements **at two places at once** in an array or a string:
the two ends of a palindrome, two numbers of a sorted array, a "keep / drop" filter done in
place.

The brute force tries every pair with two nested loops, or builds a second array:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        check(nums[i], nums[j]);
    }
}
```

That is **O(n²)** time, or O(n) extra memory for the copy. Two pointers do it in **O(n)**
time and **O(1)** extra memory.

## 2. The key idea

Use **two indexes** that only move forward (never back). Each step moves at least one of
them, so after at most n steps they have covered the array.

There are two shapes:

```
 opposite ends                         same direction (read / write)

  left ──▶              ◀── right       write ──▶
  [ 2 | 5 | 7 | 9 | 12 | 15 ]           [ 3 | 2 | 2 | 3 | 4 ]
                                          read ──▶ (always ahead)
 they meet in the middle                read looks at every element,
                                        write marks where the next kept one goes
```

| Shape | Use it when | Each step |
|---|---|---|
| Opposite ends | the answer depends on **both ends** (sorted array, palindrome, biggest at an end) | decide: move `left` right, or `right` left |
| Same direction | you **filter or compact** an array in place | `read` always moves; `write` moves only when you keep an element |

## 3. Step by step on two examples

### Opposite ends: squares of a sorted array

`[-4, -1, 0, 3, 10]` is sorted. Return the squares, sorted: `[0, 1, 9, 16, 100]`.

The biggest square is always at **one of the two ends** (a big negative or a big positive).
So fill the result **from the end**: compare the two ends, take the bigger square, move that
pointer.

| pos | left (value) | right (value) | left² | right² | Take | Result after | Pointers after |
|---|---|---|---|---|---|---|---|
| 4 | 0 (-4) | 4 (10) | 16 | 100 | right | [_, _, _, _, 100] | left 0, right 3 |
| 3 | 0 (-4) | 3 (3) | 16 | 9 | left | [_, _, _, 16, 100] | left 1, right 3 |
| 2 | 1 (-1) | 3 (3) | 1 | 9 | right | [_, _, 9, 16, 100] | left 1, right 2 |
| 1 | 1 (-1) | 2 (0) | 1 | 0 | left | [_, 1, 9, 16, 100] | left 2, right 2 |
| 0 | 2 (0) | 2 (0) | 0 | 0 | right | [0, 1, 9, 16, 100] | done |

5 steps for 5 elements: O(n), no sort needed.

### Same direction: remove a value in place

Remove every `3` from `[3, 2, 2, 3, 4]` without a new array. Return how many remain.

`read` visits every element. When the element is kept, copy it to `write` and move `write`.

| read | x | Keep? | Array after | write after |
|---|---|---|---|---|
| 0 | 3 | no | [3, 2, 2, 3, 4] | 0 |
| 1 | 2 | yes | [2, 2, 2, 3, 4] | 1 |
| 2 | 2 | yes | [2, 2, 2, 3, 4] | 2 |
| 3 | 3 | no | [2, 2, 2, 3, 4] | 2 |
| 4 | 4 | yes | [2, 2, 4, 3, 4] | 3 |

Answer: **3**, and the first 3 boxes are `[2, 2, 4]`. What is after them does not matter.

## 4. The templates, line by line

### Opposite ends

```java
int left = 0;
int right = nums.length - 1;
while (left < right) {
    if (/* the left side is the one to handle */) {
        left++;
    } else {
        right--;
    }
}
```

| Line | Why |
|---|---|
| `left = 0`, `right = n - 1` | start at both ends |
| `while (left < right)` | stop when they meet; with `<=`, the middle element is handled too (useful when filling a result, like the squares) |
| the `if` | the heart of the pattern: a rule that tells **which side can be discarded** for sure |
| `left++` / `right--` | at least one pointer moves each turn, so the loop ends after at most n turns |

### Same direction (read / write)

```java
int write = 0;
for (int read = 0; read < nums.length; read++) {
    if (/* keep nums[read] */) {
        nums[write] = nums[read];
        write++;
    }
}
return write;
```

| Line | Why |
|---|---|
| `int write = 0` | next box where a kept element goes |
| `for (int read ...)` | `read` looks at every element once |
| `nums[write] = nums[read]` | copy the kept element to the front; `write <= read` always, so you never overwrite an element you still have to read |
| `return write` | number of kept elements = length of the clean part |

## 5. Why it is correct

**Opposite ends (squares)**: at every step, everything outside `[left, right]` is already in
the result, and the biggest remaining square is at `left` or `right` (the array is sorted, so
the values furthest from 0 are at the ends). Taking the bigger one is always right.

**The general rule**: moving a pointer must **never skip a possible answer**. Before writing
the `if`, ask: "why can I safely forget this element?" If you cannot answer, the pattern does
not fit (or the array must be sorted first).

**Same direction**: invariant: `nums[0 .. write-1]` holds exactly the kept elements among
`nums[0 .. read-1]`, in their original order.

## 6. Variants

| Variant | Idea | Example |
|---|---|---|
| Opposite ends on a sorted array | sum too small → `left++`, too big → `right--` | pair with a given sum |
| Opposite ends on a string | compare `s[left]` and `s[right]`, move both | palindrome |
| Opposite ends, keep the best | move the pointer that limits the answer | container with most water |
| Fix one, two pointers on the rest | a loop picks the first element, two pointers search the other two | triplets with a given sum |
| Same direction, read / write | filter, compact, remove duplicates in place | remove a value, move zeros |
| Two arrays, one pointer each | advance the pointer of the smaller element | merge two sorted arrays, intersection |
| Fast and slow | one moves 2 steps, the other 1 | linked list middle, cycle (pattern 5) |

## 7. A problem solved from start to finish

**Problem (clean palindrome)**: is `s` a palindrome if we **ignore everything that is not a
letter or a digit** and **ignore case**?
`isCleanPalindrome("Race, car!")` → `true`, `isCleanPalindrome("hello")` → `false`.

1. **Brute force**: build a cleaned copy, then compare it with its reverse. O(n) time but
   O(n) extra memory.
2. **Two places at once?** Yes: the first useful character must equal the last useful one,
   and so on. Opposite ends.
3. **Rule to move**: if `s[left]` is not a letter or digit, skip it (`left++`); same for
   `right`. Otherwise compare (lower case); different → `false`; equal → move both.
4. **Trace** on `"Race, car!"` (indexes 0 to 9):

| left | char | right | char | Action |
|---|---|---|---|---|
| 0 | R | 9 | ! | `!` is not a letter: skip right |
| 0 | R | 8 | r | `r == r` in lower case: move both |
| 1 | a | 7 | a | equal: move both |
| 2 | c | 6 | c | equal: move both |
| 3 | e | 5 | (space) | skip right |
| 3 | e | 4 | , | skip right |
| 3 | e | 3 | e | `left == right`: stop → `true` |

```java
static boolean isCleanPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
        if (!Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        } else if (!Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        } else {
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
    }
    return true;
}
```

5. **Cost**: O(n) time, O(1) space.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| Opposite ends on an **unsorted** array | wrong answers, pairs missed | sort first (O(n log n)), or use hashing |
| `while (left <= right)` when an element must not pair with itself | uses the same element twice | `left < right` |
| A branch that moves no pointer | infinite loop | every branch must move `left` or `right` |
| Skipping characters without checking bounds | `StringIndexOutOfBoundsException` | keep `left < right` in the skip condition, or skip one step per turn as above |
| Same direction: forget that `write` is the answer | return the array length | return `write` |
| Sorting when the order must be kept | wrong output order | read / write keeps the order; sorting does not |

## 9. How to recognize it

- The array is **sorted** (or you may sort it) and you look for a pair.
- "Palindrome", "reverse", "compare both ends".
- "**In place**", "O(1) extra space", "without a new array".
- "Remove / move / keep some elements and keep the order".
- Two sorted inputs to combine.

## 10. Practice

Exercises: [`patterns/twopointers/TwoPointersExercises.java`](../../../src/main/java/com/mastery/interview/patterns/twopointers/TwoPointersExercises.java)

```bash
mvn -Dtest='TwoPointersExercisesTest' test
```

Before coding each one, write on paper: **which shape (opposite ends or read / write)? where
does each pointer start? what rule decides which pointer moves, and why is it safe to forget
the element I skip?**
