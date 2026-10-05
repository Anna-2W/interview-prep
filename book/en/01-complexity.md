# 01. Complexity (Big O)

🇫🇷 [Version française](../fr/01-complexity.md)

## 1. What it is

Complexity answers one question: **when the input gets bigger, how much slower (or how much
more memory) does my code get?**

We do not count seconds: they depend on the machine. We count **how the number of steps
grows** with `n`, the size of the input.

| Notation | Meaning | In practice |
|---|---|---|
| **O** (Big O) | upper bound: "never worse than" | **the one used in interviews**, for the worst case |
| **Ω** (Omega) | lower bound: "never better than" | rare |
| **Θ** (Theta) | exact bound: both O and Ω | rare, said "tight bound" |

| Case | Example: search `x` in an unsorted array |
|---|---|
| Best case | `x` is the first element: 1 step |
| Worst case | `x` is absent: `n` steps → **O(n)** |
| Average case | about `n / 2` steps, still O(n) |

When an interviewer asks "what is the complexity?", give the **worst case, time and space**.

## 2. The complexities to know

From fastest to slowest:

| Complexity | Name | n = 1 000 | Typical code |
|---|---|---|---|
| O(1) | constant | 1 | `a[i]`, `map.get(k)` |
| O(log n) | logarithmic | ~10 | binary search, loop that halves |
| O(n) | linear | 1 000 | one loop over the input |
| O(n log n) | linearithmic | ~10 000 | good sorting (`Arrays.sort`) |
| O(n²) | quadratic | 1 000 000 | two nested loops |
| O(2ⁿ) | exponential | more than atoms in the universe | all subsets, naive recursive Fibonacci |
| O(n!) | factorial | even worse | all permutations |

**Rule of thumb** (about 10⁸ simple steps per second):

| n up to | Acceptable complexity |
|---|---|
| 10 | O(n!) |
| 20 | O(2ⁿ) |
| 5 000 | O(n²) |
| 10⁶ | O(n log n) |
| 10⁸ | O(n) |
| more | O(log n) or O(1) |

If the problem says `n <= 10^5`, O(n²) is too slow: the interviewer expects O(n log n) or O(n).

## 3. One example per complexity

### O(1)

```java
int first(int[] a) {
    return a[0];
}
```

### O(log n)

```java
int halvings(int n) {
    int steps = 0;
    while (n > 1) {
        n = n / 2;
        steps++;
    }
    return steps;
}
```

`n` is divided by 2 each turn: 1 000 → 500 → 250 → ... → 1 in about 10 turns.

### O(n)

```java
int sum(int[] a) {
    int total = 0;
    for (int x : a) {
        total += x;
    }
    return total;
}
```

### O(n log n)

```java
void sortThenPrint(int[] a) {
    Arrays.sort(a);
    for (int x : a) {
        System.out.println(x);
    }
}
```

O(n log n) + O(n) = O(n log n): the biggest term wins.

### O(n²)

```java
boolean hasDuplicate(int[] a) {
    for (int i = 0; i < a.length; i++) {
        for (int j = i + 1; j < a.length; j++) {
            if (a[i] == a[j]) {
                return true;
            }
        }
    }
    return false;
}
```

### O(2ⁿ)

```java
long fib(int n) {
    if (n < 2) {
        return n;
    }
    return fib(n - 1) + fib(n - 2);
}
```

Each call makes 2 calls: the tree of calls doubles at each level.

## 4. How to compute it

| Rule | Example | Result |
|---|---|---|
| Steps one after the other: **add** | a loop of n, then another loop of n | O(n + n) = O(n) |
| Steps inside each other: **multiply** | a loop of n inside a loop of n | O(n × n) = O(n²) |
| Drop the constants | 3n + 5 | O(n) |
| Keep only the biggest term | n² + n + 100 | O(n²) |
| Different inputs, different letters | loop over `a` (n), inside loop over `b` (m) | O(n × m), **not** O(n²) |
| Inner loop with a fixed size | `for i < n`, inside `for j < 10` | O(10n) = O(n) |
| Inner loop from `i` to `n` | n + (n-1) + ... + 1 = n(n+1)/2 | O(n²) |
| Loop that multiplies or divides by 2 | `i = i * 2` | O(log n) |
| Recursion | number of calls × work per call | `fib`: 2ⁿ calls × O(1) = O(2ⁿ) |

## 5. Space complexity

The **extra** memory the code uses, not counting the input.

| Code | Extra space |
|---|---|
| a few variables (`int total`) | O(1) |
| a new array or list of size n | O(n) |
| a `HashSet` filled with the n elements | O(n) |
| an n × n grid | O(n²) |
| recursion of depth n | O(n): each call waits on the **call stack** |
| recursion of depth log n (binary search) | O(log n) |

Very often you **trade space for time**: a `HashSet` (O(n) space) turns an O(n²) search into O(n).

## 6. Amortized cost

`ArrayList.add` is O(1) **amortized**:
- usually, there is room: O(1);
- sometimes the inner array is full: Java copies everything into one 1.5 times bigger, O(n);
- the copies are so rare that, spread over all the adds, the average stays O(1).

Same idea for `HashMap.put` when the map grows.

## 7. Hidden costs in Java

| Code that looks O(1) | Real cost | Inside a loop of n |
|---|---|---|
| `list.contains(x)` | O(n) | O(n²) |
| `list.remove(0)` on `ArrayList` | O(n) | O(n²) |
| `list.add(0, x)` on `ArrayList` | O(n) | O(n²) |
| `s = s + x` | O(length of s) | O(n²) |
| `s.substring(a, b)` | O(b - a) | |
| `linkedList.get(i)` | O(n) | O(n²) |
| `Arrays.sort(a)` | O(n log n) | |
| `map.containsValue(v)` | O(n) | O(n²) |
| `String.equals` | O(length) | |

## 8. Recap: what you saw in F01 to F06

| Operation | `ArrayList` | `HashMap` / `HashSet` | `TreeMap` / `TreeSet` | `ArrayDeque` | `PriorityQueue` |
|---|---|---|---|---|---|
| Access by index | O(1) | | | | |
| Search `contains` | O(n) | O(1) | O(log n) | O(n) | O(n) |
| Add | O(1) amortized at the end | O(1) | O(log n) | O(1) at both ends | O(log n) |
| Remove | O(n) | O(1) | O(log n) | O(1) at both ends | O(log n) head |
| Min / max | O(n) | O(n) | O(log n) | | O(1) peek |

| Other | Cost |
|---|---|
| `String.charAt`, `length` | O(1) |
| `StringBuilder.append` | O(1) amortized |
| Array read / write | O(1) |
| `Arrays.sort`, `Collections.sort` | O(n log n) |
| `Arrays.binarySearch` | O(log n) |

## 9. How to say it in an interview

> "This is O(n) time, because I go through the array once, and each `HashSet` operation is
> O(1) on average. It is O(n) extra space for the set. The brute force was O(n²) time and
> O(1) space: I traded memory for speed."

Always give: **time**, **space**, and **why** in one sentence.

## 10. Traps

| Trap | Wrong | Right |
|---|---|---|
| Two arrays of different sizes | O(n²) | O(n × m) |
| Two loops one after the other | O(n²) | O(n) |
| Inner loop of fixed size 26 | O(n²) | O(n) |
| Forgetting the sort | "it is one loop: O(n)" | sort + loop = O(n log n) |
| `contains` on a list in a loop | O(n) | O(n²) |
| Recursion | "no loop, so O(1)" | count the calls |
| Space of recursion | O(1) | O(depth) |
| `HashMap` worst case | "always O(1)" | O(1) on average, O(n) if every key collides |
| Saying only time | "O(n)" | "O(n) time, O(1) space" |

## 11. Exercises

Two files, two kinds of exercise.

### Part A: what is the complexity? ([`ComplexityQuiz.java`](../../src/main/java/com/mastery/interview/complexity/ComplexityQuiz.java))

Each question shows a method. Return its **time** complexity as a string:
`"O(1)"`, `"O(log n)"`, `"O(n)"`, `"O(n log n)"`, `"O(n^2)"`, `"O(n*m)"`, `"O(2^n)"`.
Spaces and case do not matter. Do not read the test before answering: the answers are in it.

```bash
mvn -Dtest=ComplexityQuizTest test
```

| # | Snippet |
|---|---|
| Q01 | sum of an array |
| Q02 | first element |
| Q03 | every pair |
| Q04 | `i = i * 2` |
| Q05 | two loops one after the other |
| Q06 | loop over `a` inside a loop over `b` |
| Q07 | inner loop of size 26 |
| Q08 | sort, then one loop |
| Q09 | `list.contains` inside a loop |
| Q10 | naive recursive Fibonacci |

### Part B: make it faster ([`FasterExercises.java`](../../src/main/java/com/mastery/interview/complexity/FasterExercises.java))

Each exercise gives a **slow version that works**. Write the fast version next to it.
The tests use big inputs with a 2-second limit: the slow version fails, a good one passes.

```bash
mvn -Dtest='FasterExercisesTest$E01HasDuplicate' test
mvn -Dtest=FasterExercisesTest test
```

| # | Exercise | Slow | Target | Idea |
|---|---|---|---|---|
| E01 | `hasDuplicate(int[])` | O(n²) | O(n) | `HashSet` |
| E02 | `countCommon(int[] a, int[] b)` | O(n × m) | O(n + m) | `HashSet` |
| E03 | `joinWords(List<String>)` | O(n²) | O(n) | `StringBuilder` |
| E04 | `maxWindowSum(int[], k)` | O(n × k) | O(n) | sliding window |
| E05 | `rangeSums(int[], queries)` | O(n × q) | O(n + q) | prefix sums |
| E06 | `fib(n)` | O(2ⁿ) | O(n) | remember the last two values |

---

⬅️ [Table of contents](../README.md) · [README](../../README.md)
