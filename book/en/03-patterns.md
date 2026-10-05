# 03. Coding patterns

🇫🇷 [Version française](../fr/03-patterns.md)

Most interview problems are a known **pattern** in disguise. The real skill is to read the
statement and recognize which one applies. Each pattern below has: **how to recognize it**,
**the idea**, **a Java template**, **the cost**, and **exercises** from easy to medium.

## 0. Which pattern? (read this first)

| If the problem says... | Think of | Section |
|---|---|---|
| "already seen?", "count", "pair that sums to X" (unsorted) | Hashing | 1 |
| **sorted** array, pair, "in place", palindrome | Two pointers | 2 |
| "**contiguous** subarray / substring", "longest", "of size k" | Sliding window | 3 |
| "sum between i and j", many range queries, "subarray sum = k" | Prefix sums | 4 |
| linked list, cycle, middle | Fast and slow pointers | 5 |
| sorted array, "minimum value such that...", answer in a range | Binary search | 6 |
| "next greater / smaller element", "how many days until" | Monotonic stack | 7 |
| intervals, meetings, overlaps | Intervals | 8 |
| "the k biggest / most frequent / closest", k sorted lists, median | Heap | 9 |
| "without extra space", XOR, powers of 2 | Bit manipulation | 10 |
| "all combinations / subsets / permutations" | Backtracking | 11 |
| tree, "level by level", grid, shortest path, dependencies | BFS, DFS, topological sort, union-find | chapters 04 and 05 |
| "number of ways", "min / max" with repeated choices | Dynamic programming, greedy | chapter 07 |
| prefixes of words, autocomplete | Trie | chapter 04 |

| Input size | Expected complexity | Patterns that fit |
|---|---|---|
| n ≤ 20 | O(2ⁿ) | backtracking |
| n ≤ 5 000 | O(n²) | two nested loops, simple DP |
| n ≤ 10⁶ | O(n log n) or O(n) | sort + two pointers, hashing, sliding window, heap |
| huge, or "in O(log n)" | O(log n) | binary search |

---

## 1. Hashing

**Recognize**: "have I already seen it?", "count", "group", "pair" in an **unsorted** input.

**Idea**: a `HashMap` or `HashSet` answers "is it there?" in O(1). It removes the inner
search loop: O(n²) becomes O(n).

```java
Map<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int need = target - nums[i];
    if (seen.containsKey(need)) {
        return new int[] {seen.get(need), i};
    }
    seen.put(nums[i], i);
}
```

**Cost**: O(n) time, O(n) space.

| Classic | Key to use |
|---|---|
| Anagram check | count of each letter (`int[26]` or a map) |
| Group anagrams | sorted letters of the word as the key |
| Longest consecutive sequence | a `HashSet`, start counting only from `x` when `x - 1` is absent |

**Exercises** [`HashingExercises.java`](../../src/main/java/com/mastery/interview/patterns/hashing/HashingExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `isAnagram` | `isAnagram("listen", "silent")` → `true` |
| E02 | `groupAnagrams` | `["eat", "tea", "tan", "ate", "nat"]` → `[[eat, tea, ate], [tan, nat]]` |
| E03 | `longestConsecutive` | `[100, 4, 200, 1, 3, 2]` → `4` (1, 2, 3, 4) in O(n) |

---

## 2. Two pointers

**Recognize**: **sorted** array, find a pair, reverse or compare both ends, change an array
**in place**.

**Idea**: two indexes move towards each other (or in the same direction) so that each element
is visited once.

```java
int left = 0;
int right = nums.length - 1;
while (left < right) {
    int sum = nums[left] + nums[right];
    if (sum == target) {
        return new int[] {left, right};
    } else if (sum < target) {
        left++;
    } else {
        right--;
    }
}
```

Same direction (read / write) to change an array in place:

```java
int write = 0;
for (int read = 0; read < nums.length; read++) {
    if (nums[read] != 0) {
        nums[write++] = nums[read];
    }
}
```

**Cost**: O(n) time, O(1) space (plus O(n log n) if you must sort first).

**Exercises** [`TwoPointersExercises.java`](../../src/main/java/com/mastery/interview/patterns/twopointers/TwoPointersExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `pairWithSum` (sorted) | `[1, 2, 4, 7, 11]`, 9 → `[1, 3]` |
| E02 | `moveZeroes` (in place) | `[0, 1, 0, 3, 12]` → `[1, 3, 12, 0, 0]` |
| E03 | `removeDuplicates` (sorted, in place) | `[1, 1, 2, 3, 3]` → returns `3`, array starts with `[1, 2, 3]` |
| E04 | `maxArea` | `[1, 8, 6, 2, 5, 4, 8, 3, 7]` → `49` |
| E05 | `threeSum` | `[-1, 0, 1, 2, -1, -4]` → `[[-1, -1, 2], [-1, 0, 1]]` |

---

## 3. Sliding window

**Recognize**: "**contiguous** subarray / substring", "longest / shortest", "of size k",
"at most k...".

**Idea**: a window `[left, right]` moves over the array. `right` grows the window; when the
window breaks a rule, `left` shrinks it. Each index enters and leaves once: O(n).

```java
int left = 0;
int best = 0;
Map<Character, Integer> count = new HashMap<>();
for (int right = 0; right < s.length(); right++) {
    count.merge(s.charAt(right), 1, Integer::sum);
    while (windowIsInvalid(count)) {
        count.merge(s.charAt(left), -1, Integer::sum);
        left++;
    }
    best = Math.max(best, right - left + 1);
}
```

| Kind | Window size | Example |
|---|---|---|
| Fixed | always `k` | max sum of k consecutive (chapter 01, E04) |
| Variable, longest | grow, shrink when invalid | longest substring without repeat |
| Variable, shortest | grow until valid, then shrink while still valid | shortest subarray with sum ≥ target |

**Cost**: O(n) time, O(k) or O(alphabet) space.

**Exercises** [`SlidingWindowExercises.java`](../../src/main/java/com/mastery/interview/patterns/slidingwindow/SlidingWindowExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `longestUniqueSubstring` | `"abcabcbb"` → `3` (`"abc"`) |
| E02 | `minSubarrayLength` (positive numbers) | target 7, `[2, 3, 1, 2, 4, 3]` → `2` (`[4, 3]`) |
| E03 | `longestOnes` (flip at most k zeros) | `[1, 1, 0, 0, 1, 1, 1, 0, 1]`, k = 1 → `5` |
| E04 | `containsPermutation` | `"ab"` in `"eidbaooo"` → `true` |

---

## 4. Prefix sums

**Recognize**: "sum between i and j", many range queries, "number of subarrays with sum k",
"balance point".

**Idea**: `prefix[i]` = sum of the first `i` elements. Then
`sum(i..j) = prefix[j + 1] - prefix[i]` in O(1).

```java
long[] prefix = new long[nums.length + 1];
for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
```

**With a map** (count subarrays with sum `k`, negatives allowed): while walking, the number
of earlier prefixes equal to `current - k` is the number of subarrays ending here.

```java
Map<Integer, Integer> seen = new HashMap<>();
seen.put(0, 1);
int current = 0;
int count = 0;
for (int x : nums) {
    current += x;
    count += seen.getOrDefault(current - k, 0);
    seen.merge(current, 1, Integer::sum);
}
```

**Cost**: O(n) to build, O(1) per query.

**Exercises** [`PrefixSumExercises.java`](../../src/main/java/com/mastery/interview/patterns/prefixsum/PrefixSumExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `runningSum` | `[1, 2, 3, 4]` → `[1, 3, 6, 10]` |
| E02 | `pivotIndex` | `[1, 7, 3, 6, 5, 6]` → `3` (left 11 = right 11) |
| E03 | `countSubarraysWithSum` | `[1, 1, 1]`, k = 2 → `2` |

---

## 5. Fast and slow pointers

**Recognize**: linked list, "cycle", "middle", "n-th from the end".

**Idea**: `slow` moves 1 step, `fast` moves 2. When `fast` reaches the end, `slow` is in the
middle. If there is a cycle, they meet.

```java
ListNode slow = head;
ListNode fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;
```

**Cost**: O(n) time, O(1) space.

**Exercises**: already done in chapter 02, [`LinkedListProblems`](../../src/main/java/com/mastery/interview/datastructures/LinkedListProblems.java) E03, E04, E06, E08.

---

## 6. Binary search

**Recognize**: sorted array, "in O(log n)", "the minimum value such that...", "the first
position where...".

**Idea**: look at the middle, throw away the half that cannot contain the answer.

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

**Binary search on the answer**: when the answer is a number in a range `[lo, hi]` and
"`x` works" means "every bigger `x` works too", search the smallest `x` that works.

```java
int lo = 1;
int hi = max;
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

**Cost**: O(log n), or O(n log(range)) when `works` costs O(n).

| Trap | Wrong | Right |
|---|---|---|
| Overflow | `(left + right) / 2` | `left + (right - left) / 2` |
| Infinite loop | `lo = mid` with `while (lo < hi)` | `lo = mid + 1` |
| Bounds | mixing `<` and `<=` at random | pick one template and stick to it |

**Exercises** [`BinarySearchExercises.java`](../../src/main/java/com/mastery/interview/patterns/binarysearch/BinarySearchExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `search` | `[-1, 0, 3, 5, 9, 12]`, 9 → `4` |
| E02 | `searchInsert` | `[1, 3, 5, 6]`, 2 → `1` |
| E03 | `sqrt` (floor, no `Math.sqrt`) | `8` → `2` |
| E04 | `searchRotated` | `[4, 5, 6, 7, 0, 1, 2]`, 0 → `4` |
| E05 | `minEatingSpeed` | piles `[3, 6, 7, 11]`, 8 hours → `4` |

---

## 7. Monotonic stack

**Recognize**: "next greater element", "next smaller", "how many days until warmer",
"span".

**Idea**: keep a stack of **indexes** whose values are in decreasing order. When a bigger
value arrives, it is the answer for every smaller value on top: pop them.

```java
int[] answer = new int[nums.length];
Arrays.fill(answer, -1);
Deque<Integer> stack = new ArrayDeque<>();
for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
        answer[stack.pop()] = nums[i];
    }
    stack.push(i);
}
```

**Cost**: O(n): each index is pushed and popped once.

**Exercises** [`MonotonicStackExercises.java`](../../src/main/java/com/mastery/interview/patterns/monotonicstack/MonotonicStackExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `nextGreater` | `[2, 1, 2, 4, 3]` → `[4, 2, 4, -1, -1]` |
| E02 | `dailyTemperatures` | `[73, 74, 75, 71, 69, 72, 76, 73]` → `[1, 1, 4, 2, 1, 1, 0, 0]` |

---

## 8. Intervals

**Recognize**: pairs `[start, end]`, meetings, bookings, "overlap", "merge".

**Idea**: **sort by start**. Then two intervals overlap only if the next one starts before
the current one ends.

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
List<int[]> merged = new ArrayList<>();
for (int[] current : intervals) {
    if (merged.isEmpty() || merged.getLast()[1] < current[0]) {
        merged.add(current);
    } else {
        merged.getLast()[1] = Math.max(merged.getLast()[1], current[1]);
    }
}
```

**Cost**: O(n log n) for the sort.

**Exercises** [`IntervalExercises.java`](../../src/main/java/com/mastery/interview/patterns/intervals/IntervalExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `canAttendAll` | `[[0, 30], [5, 10], [15, 20]]` → `false` |
| E02 | `merge` | `[[1, 3], [2, 6], [8, 10], [15, 18]]` → `[[1, 6], [8, 10], [15, 18]]` |
| E03 | `insert` (list sorted, no overlap) | `[[1, 3], [6, 9]]` + `[2, 5]` → `[[1, 5], [6, 9]]` |
| E04 | `minMeetingRooms` | `[[0, 30], [5, 10], [15, 20]]` → `2` |

---

## 9. Heap (top K, k-way merge, two heaps)

**Recognize**: "the k biggest / smallest / most frequent / closest", "merge k sorted lists",
"median of a stream".

| Sub-pattern | Idea | Cost |
|---|---|---|
| Top K biggest | **min**-heap of size k: if it grows above k, `poll` the smallest | O(n log k) |
| K-way merge | heap holds the current head of each list; poll the smallest, push its next | O(n log k) |
| Two heaps | max-heap for the lower half, min-heap for the upper half; median is on top | O(log n) per add |

```java
PriorityQueue<Integer> heap = new PriorityQueue<>();
for (int x : nums) {
    heap.offer(x);
    if (heap.size() > k) {
        heap.poll();
    }
}
```

**Exercises** [`HeapExercises.java`](../../src/main/java/com/mastery/interview/patterns/heap/HeapExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `topKFrequent` | `[1, 1, 1, 2, 2, 3]`, k = 2 → `[1, 2]` |
| E02 | `kClosest` (to the origin) | `[[1, 3], [-2, 2], [5, 8]]`, k = 1 → `[[-2, 2]]` |
| E03 | `mergeKSorted` | `[[1, 4, 5], [1, 3, 4], [2, 6]]` → `[1, 1, 2, 3, 4, 4, 5, 6]` |
| E04 | `runningMedians` | `[5, 15, 1, 3]` → `[5.0, 10.0, 5.0, 4.0]` |

---

## 10. Bit manipulation

**Recognize**: "without extra space", "appears once while others appear twice", powers of 2,
"count the 1 bits".

| Trick | Code | Example |
|---|---|---|
| `x ^ x = 0`, `x ^ 0 = x` | XOR every number | pairs cancel, the single one stays |
| Lowest bit set | `x & 1` | `5 & 1` → `1` (odd) |
| Remove the lowest 1 bit | `x & (x - 1)` | `12 & 11` → `8` |
| Power of 2 | `x > 0 && (x & (x - 1)) == 0` | `8` → `true` |
| Shift | `x >> 1` (divide by 2), `x << 1` (multiply by 2) | `5 >> 1` → `2` |
| Count the 1 bits | `Integer.bitCount(x)` | `bitCount(11)` → `3` |

**Exercises** [`BitExercises.java`](../../src/main/java/com/mastery/interview/patterns/bits/BitExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `singleNumber` | `[4, 1, 2, 1, 2]` → `4` |
| E02 | `countOnes` (without `Integer.bitCount`) | `11` (`1011`) → `3` |
| E03 | `isPowerOfTwo` | `16` → `true`, `6` → `false` |
| E04 | `missingNumber` (0..n, one missing) | `[3, 0, 1]` → `2` |

---

## 11. Backtracking

**Recognize**: "all combinations", "all subsets", "all permutations", "every valid...".

**Idea**: build a solution step by step; at each step **choose**, **explore** (recursive
call), then **undo** the choice.

```java
void backtrack(int start, List<Integer> current, List<List<Integer>> result, int[] nums) {
    result.add(new ArrayList<>(current));
    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);
        backtrack(i + 1, current, result, nums);
        current.removeLast();
    }
}
```

**Cost**: exponential: O(2ⁿ) subsets, O(n!) permutations. Fine because n is small.

| Trap | Wrong | Right |
|---|---|---|
| Save a solution | `result.add(current)` (same list, changed later) | `result.add(new ArrayList<>(current))` |
| Forget to undo | `current.add(x); backtrack(...);` | then `current.removeLast();` |

**Exercises** [`BacktrackingExercises.java`](../../src/main/java/com/mastery/interview/patterns/backtracking/BacktrackingExercises.java)

| # | Exercise | Example |
|---|---|---|
| E01 | `subsets` | `[1, 2]` → `[[], [1], [2], [1, 2]]` |
| E02 | `permutations` | `[1, 2, 3]` → 6 permutations |
| E03 | `combinationSum` (reuse allowed) | `[2, 3, 6, 7]`, 7 → `[[2, 2, 3], [7]]` |
| E04 | `generateParentheses` | `3` → `["((()))", "(()())", "(())()", "()(())", "()()()"]` |

---

## 12. How to run

```bash
mvn -Dtest='HashingExercisesTest' test
mvn -Dtest='TwoPointersExercisesTest$E05ThreeSum' test
mvn -Dtest='com/mastery/interview/patterns/**/*Test' test
```

When a result is a list of lists (anagram groups, subsets, permutations...), the tests do not
care about the order.

---

⬅️ [Table of contents](../README.md) · [README](../../README.md)
