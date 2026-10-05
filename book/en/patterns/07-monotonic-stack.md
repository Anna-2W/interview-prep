# Pattern 7. Monotonic stack

🇫🇷 [Version française](../../fr/patterns/07-monotonic-stack.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Many problems ask, for **each** element: "what is the **next** (or previous) element that is
bigger (or smaller) than me?"

The brute force answers it with a second loop that walks to the right:

```java
for (int i = 0; i < n; i++) {
    answer[i] = -1;
    for (int j = i + 1; j < n; j++) {
        if (nums[j] < nums[i]) {
            answer[i] = nums[j];
            break;
        }
    }
}
```

On a bad input (for example an array sorted in increasing order, when you look for the next
smaller), the inner loop walks to the end every time: n × n = **O(n²)**.

## 2. The key idea

Read the array **once**, from left to right, and keep a **stack of the elements that are
still waiting for their answer**.

When a new element `x` arrives, it **is** the answer for every waiting element it beats.
Those elements are on top of the stack: pop them, give them their answer, then push `x`
(it now waits too).

Because we always pop the elements that `x` beats, the values left in the stack are always
**sorted** (monotonic). That is where the name comes from.

```
 next smaller, values waiting in the stack (bottom -> top) are increasing

 stack: [3, 4]      new x = 1
         1 < 4  -> 4 gets its answer (1), pop
         1 < 3  -> 3 gets its answer (1), pop
 stack: [1]         1 waits for its own answer
```

We store **indexes** in the stack, not values: with an index you can read the value
(`nums[i]`) **and** know where to write the answer (`answer[i]`) or compute a distance.

## 3. Step by step on an example

Next **smaller** element for `[5, 3, 4, 1, 2]` (-1 if there is none).
Rule: while the value on top is **bigger** than `x`, pop it and give it `x` as its answer.
The stack is written bottom → top, as `index(value)`.

| i | x | Popped (and their answer) | Stack after the step |
|---|---|---|---|
| 0 | 5 | none | [0(5)] |
| 1 | 3 | 0(5) gets 3 | [1(3)] |
| 2 | 4 | none (3 is not bigger than 4) | [1(3), 2(4)] |
| 3 | 1 | 2(4) gets 1, then 1(3) gets 1 | [3(1)] |
| 4 | 2 | none | [3(1), 4(2)] |

At the end, the indexes still in the stack (3 and 4) never found a smaller element: they
keep -1. Answer: **[3, 1, 1, -1, -1]**.

Look at the stack column: from bottom to top the values are always **increasing**.

## 4. The template, line by line

```java
int[] answer = new int[nums.length];
Arrays.fill(answer, -1);
Deque<Integer> stack = new ArrayDeque<>();
for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
        answer[stack.pop()] = nums[i];
    }
    stack.push(i);
}
return answer;
```

| Line | Why |
|---|---|
| `Arrays.fill(answer, -1)` | the default for elements that never get an answer |
| `Deque<Integer> stack` | holds **indexes** of the elements still waiting |
| `!stack.isEmpty() && ...` | check empty first, `peek()` on an empty deque returns `null` |
| `nums[stack.peek()] > nums[i]` | "does `x` beat the element on top?" This comparison decides the variant (see section 6) |
| `answer[stack.pop()] = nums[i]` | the element on top found its answer: write it and remove it |
| `while`, not `if` | `x` can be the answer for **several** waiting elements in a row |
| `stack.push(i)` | `x` now waits for its own answer |

**Why O(n) even with a loop inside a loop?** Each index is pushed **once** and popped **at
most once**. Over the whole run the `while` loop does at most n pops in total, not n per
element. Total: O(n) time, O(n) space for the stack.

## 5. Why it is correct

**Invariant**: at any moment, the stack contains exactly the indexes already read that have
**not found** their answer yet, and their values are sorted (increasing from bottom to top
for "next smaller").

- When `x` arrives, the waiting elements it beats are all on top (the stack is sorted), so
  popping from the top finds all of them, and only them.
- `x` is the **first** element to their right that beats them: if an earlier one had beaten
  them, they would already have been popped.

## 6. The variants

Only two things change: the **comparison** in the `while`, and **when** you read the answer.

| Problem | Stack (bottom → top) | Pop while | Answer is |
|---|---|---|---|
| next greater | decreasing | `nums[top] < x` | `x`, when you pop |
| next smaller | increasing | `nums[top] > x` | `x`, when you pop |
| previous greater | decreasing | `nums[top] <= x` | the top **after** popping, before pushing `x` |
| previous smaller | increasing | `nums[top] >= x` | the top **after** popping, before pushing `x` |
| distance instead of value | same | same | `i - index` instead of `nums[i]` |
| circular array | same | same | loop `i` from 0 to `2n - 1` and use `i % n` |

`<` versus `<=` matters when there are equal values: decide if "greater" means strictly
greater, and check it on an example with duplicates.

## 7. A problem solved from start to finish

**Problem (stock span)**: for each day, return the **span**: the number of consecutive days
up to today (today included) where the price was **less than or equal to** today's price.
`prices = [100, 80, 60, 70, 60, 75, 85]` → `[1, 1, 1, 2, 1, 4, 6]`.

1. **Brute force**: for each day, walk back while the price is ≤ today. O(n²) on an
   increasing price list.
2. **Rephrase**: the span stops at the **previous greater** price. So
   `span = i - (index of the previous greater)`, or `i + 1` if there is none.
3. **Pattern**: previous greater → decreasing stack, pop while `price[top] <= price[i]`, then
   the top is the previous greater.

| i | Price | Popped | Previous greater (top) | Span | Stack after |
|---|---|---|---|---|---|
| 0 | 100 | none | none | 0 + 1 = 1 | [0(100)] |
| 1 | 80 | none | 0(100) | 1 - 0 = 1 | [0(100), 1(80)] |
| 2 | 60 | none | 1(80) | 2 - 1 = 1 | [0(100), 1(80), 2(60)] |
| 3 | 70 | 2(60) | 1(80) | 3 - 1 = 2 | [0(100), 1(80), 3(70)] |
| 4 | 60 | none | 3(70) | 4 - 3 = 1 | [0(100), 1(80), 3(70), 4(60)] |
| 5 | 75 | 4(60), 3(70) | 1(80) | 5 - 1 = 4 | [0(100), 1(80), 5(75)] |
| 6 | 85 | 5(75), 1(80) | 0(100) | 6 - 0 = 6 | [0(100), 6(85)] |

```java
static int[] stockSpan(int[] prices) {
    int[] span = new int[prices.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < prices.length; i++) {
        while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
            stack.pop();
        }
        span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
        stack.push(i);
    }
    return span;
}
```

4. **Cost**: O(n) time (each day pushed and popped once), O(n) space.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| Store values instead of indexes | cannot write `answer[j]` or compute a distance | push `i`, read `nums[i]` |
| `if` instead of `while` | only one waiting element gets its answer | `while` |
| `peek()` before checking empty | `NullPointerException` (unboxing `null`) | `!stack.isEmpty() &&` first |
| Wrong comparison | you get "next smaller" instead of "next greater" | write the rule in words, then the sign |
| `<` versus `<=` | wrong answers with equal values | test an example with duplicates |
| Forget the default value | 0 instead of -1 for elements never popped | `Arrays.fill(answer, -1)` |
| Use `Stack` | works but slow and old | `Deque<Integer> stack = new ArrayDeque<>()` |

## 9. How to recognize it

- "Next greater / next smaller / previous greater / previous smaller element".
- "How many days until...", "how far back...", "span".
- For each element, you need the **nearest** element on one side that is bigger or smaller.
- Your brute force has an inner loop that **walks** left or right and stops at the first
  element that beats the current one.

## 10. Practice

Exercises: [`patterns/monotonicstack/MonotonicStackExercises.java`](../../../src/main/java/com/mastery/interview/patterns/monotonicstack/MonotonicStackExercises.java)

```bash
mvn -Dtest='MonotonicStackExercisesTest' test
```

Before coding each one, write on paper: **next or previous? greater or smaller? so which
comparison pops? do I write the value or the distance?** Then trace the stack on the example,
like the table in section 3.
