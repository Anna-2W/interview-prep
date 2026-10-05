# F06. Stack and Queue

🇫🇷 [Version française](../../fr/foundations/06-stack-queue.md)

## 1. What it is

| | Rule | Picture | Real life |
|---|---|---|---|
| **Stack** (pile) | **LIFO**: last in, first out | `push 1, 2, 3` → `pop` gives `3` | a pile of plates, the "undo" button |
| **Queue** (file) | **FIFO**: first in, first out | `offer 1, 2, 3` → `poll` gives `1` | a line at the bakery |
| **Priority queue** | the **smallest** comes out first | `offer 5, 1, 4` → `poll` gives `1` | the emergency room |

In Java, use:

| Need | Class | Declared as |
|---|---|---|
| Stack | `ArrayDeque` | `Deque<Integer> stack = new ArrayDeque<>();` |
| Queue | `ArrayDeque` | `Queue<Integer> queue = new ArrayDeque<>();` |
| Both ends | `ArrayDeque` | `Deque<Integer> deque = new ArrayDeque<>();` |
| Priority queue | `PriorityQueue` | `PriorityQueue<Integer> pq = new PriorityQueue<>();` |

**Deque** = "double-ended queue": you can add and remove at both ends, so it does both jobs.
The old `Stack` class still exists but is slow (synchronized): do not use it in new code.

## 2. Create

| Code | Result |
|---|---|
| `new ArrayDeque<>()` | empty |
| `new ArrayDeque<>(List.of(1, 2, 3))` | first = `1`, last = `3` |
| `new PriorityQueue<>()` | empty, smallest first |
| `new PriorityQueue<>(Comparator.reverseOrder())` | empty, **biggest** first |
| `new PriorityQueue<>(List.of(5, 1, 4))` | smallest first, `peek()` = `1` |
| `new PriorityQueue<>(Comparator.comparing(String::length))` | shortest string first |

## 3. `ArrayDeque` as a stack

Every example starts from `stack = new ArrayDeque<>(List.of(1, 2, 3))`. The **top** is the first element.

| Method | Example | Result |
|---|---|---|
| `push(x)` | `stack.push(0)` | `[0, 1, 2, 3]`, top = `0` |
| `pop()` | `stack.pop()` | returns `1`, `[2, 3]` |
| `peek()` | `stack.peek()` | `1`, nothing removed |
| `isEmpty()` | `stack.isEmpty()` | `false` |
| `size()` | `stack.size()` | `3` |

## 4. `ArrayDeque` as a queue

Every example starts from `queue = new ArrayDeque<>(List.of(1, 2, 3))`. Elements enter at the end and leave at the front.

| Method | Example | Result |
|---|---|---|
| `offer(x)` | `queue.offer(4)` | `[1, 2, 3, 4]` |
| `add(x)` | `queue.add(4)` | `[1, 2, 3, 4]` |
| `poll()` | `queue.poll()` | returns `1`, `[2, 3]` |
| `remove()` | `queue.remove()` | returns `1`, `[2, 3]` |
| `peek()` | `queue.peek()` | `1`, nothing removed |
| `element()` | `queue.element()` | `1`, nothing removed |

### Two versions of each: exception or special value

| Action | Throws if impossible | Returns `null` (or `false`) |
|---|---|---|
| Add at the end | `add(x)` | `offer(x)` |
| Remove the front | `remove()` | `poll()` |
| Look at the front | `element()` | `peek()` |

On an empty queue: `poll()` → `null`, `remove()` → `NoSuchElementException`.

## 5. All the other methods of `Deque` / `ArrayDeque`

Every example starts from `d = new ArrayDeque<>(List.of(1, 2, 3))`.

| Method | Example | Result |
|---|---|---|
| `addFirst(x)` | `d.addFirst(0)` | `[0, 1, 2, 3]` |
| `addLast(x)` | `d.addLast(4)` | `[1, 2, 3, 4]` |
| `offerFirst(x)` | `d.offerFirst(0)` | `[0, 1, 2, 3]`, returns `true` |
| `offerLast(x)` | `d.offerLast(4)` | `[1, 2, 3, 4]`, returns `true` |
| `removeFirst()` | `d.removeFirst()` | returns `1` (throws if empty) |
| `removeLast()` | `d.removeLast()` | returns `3` (throws if empty) |
| `pollFirst()` | `d.pollFirst()` | returns `1` (`null` if empty) |
| `pollLast()` | `d.pollLast()` | returns `3` (`null` if empty) |
| `getFirst()` | `d.getFirst()` | `1` (throws if empty) |
| `getLast()` | `d.getLast()` | `3` (throws if empty) |
| `peekFirst()` | `d.peekFirst()` | `1` (`null` if empty) |
| `peekLast()` | `d.peekLast()` | `3` (`null` if empty) |
| `removeFirstOccurrence(x)` | with `[1, 2, 1, 3, 1]`: `removeFirstOccurrence(1)` | `[2, 1, 3, 1]` |
| `removeLastOccurrence(x)` | with `[2, 1, 3, 1]`: `removeLastOccurrence(1)` | `[2, 1, 3]` |
| `remove(x)` | `d.remove(2)` | `[1, 3]`, returns `true` |
| `contains(x)` | `d.contains(2)` | `true` |
| `addAll(c)` | `d.addAll(List.of(4, 5))` | `[1, 2, 3, 4, 5]` |
| `removeIf(test)` | `d.removeIf(x -> x % 2 == 1)` | `[2]` |
| `removeAll(c)` / `retainAll(c)` | `d.retainAll(List.of(1, 2))` | `[1, 2]` |
| `clear()` | `d.clear()` | `[]` |
| `iterator()` | `d.iterator().next()` | `1` (front to back) |
| `descendingIterator()` | `d.descendingIterator().next()` | `3` (back to front) |
| `reversed()` | `d.reversed()` | `[3, 2, 1]` |
| `forEach(f)` | `d.forEach(System.out::println)` | prints `1`, `2`, `3` |
| `stream()` | `d.stream().mapToInt(x -> x).sum()` | `6` |
| `toArray()` | `d.toArray()` | `[1, 2, 3]` |
| `clone()` | `d.clone()` | copy |
| `spliterator()` | `d.spliterator().estimateSize()` | `3` (used by streams, rare) |

## 6. All the methods of `PriorityQueue`

Every example starts from `pq = new PriorityQueue<>(List.of(5, 1, 4, 2))`.

| Method | Example | Result | Time |
|---|---|---|---|
| `offer(x)` / `add(x)` | `pq.offer(0)` | `0` becomes the head | O(log n) |
| `peek()` | `pq.peek()` | `1` | O(1) |
| `poll()` | `pq.poll()` | returns `1` and removes it | O(log n) |
| `element()` / `remove()` | `pq.remove()` | like `peek` / `poll`, but throw if empty | |
| `remove(x)` | `pq.remove(4)` | removes one `4`, returns `true` | O(n) |
| `contains(x)` | `pq.contains(5)` | `true` | O(n) |
| `size()` / `isEmpty()` | `pq.size()` | `4` | O(1) |
| `clear()` | `pq.clear()` | empty | |
| `comparator()` | `pq.comparator()` | `null` (natural order) | |
| `addAll(c)` | `pq.addAll(List.of(3, 0))` | adds both | |
| `removeIf`, `removeAll`, `retainAll` | `pq.removeIf(x -> x > 3)` | removes `5` and `4` | |
| `iterator()`, `forEach`, `toArray`, `stream` | `pq.toArray()` | the elements, **not sorted** | |
| `spliterator()` | | rare | |

**Max-heap** (biggest first): `new PriorityQueue<>(Comparator.reverseOrder())`.

## 7. The old `Stack` class (know it, do not use it)

| Method | Example with `push(1)`, `push(2)`, `push(3)` | Result |
|---|---|---|
| `push(x)` | | `[1, 2, 3]`, top = `3` |
| `pop()` | `st.pop()` | `3` |
| `peek()` | `st.peek()` | `3` |
| `empty()` | `st.empty()` | `false` |
| `search(x)` | `st.search(1)` | `3` (distance from the top, starting at 1) |

`pop()` on an empty `Stack` throws `EmptyStackException`.

## 8. Cost

| Operation | `ArrayDeque` | `PriorityQueue` |
|---|---|---|
| Add | O(1) at both ends | O(log n) |
| Remove the head | O(1) at both ends | O(log n) |
| Look at the head | O(1) | O(1) |
| `contains`, `remove(x)` | O(n) | O(n) |

## 9. In interviews

**Stack**: anything that must be matched or undone in reverse order.
- Valid parentheses `([]{})`.
- Evaluate an expression (Reverse Polish Notation).
- Undo, browser back button, backspace in a text.
- Iterative DFS.

**Queue**: process things in the order they arrive.
- BFS (shortest path in a grid, level-order traversal of a tree).
- Task scheduling, buffers.

**Priority queue**: always get the smallest or the biggest quickly.
- Top K / K-th largest: a min-heap of size K.
- Merge K sorted lists, Dijkstra.

## 10. Traps

| Trap | Wrong | Right |
|---|---|---|
| Choose the class | `new Stack<>()` | `new ArrayDeque<>()` |
| Empty stack | `stack.pop()` throws `NoSuchElementException` | check `isEmpty()` first, or use `poll()` |
| Mix stack and queue methods | `push` then `poll` on the same deque | stack: `push`/`pop`/`peek`; queue: `offer`/`poll`/`peek` |
| `null` | `deque.add(null)` throws `NullPointerException` | `ArrayDeque` refuses `null` |
| Print a `PriorityQueue` | `[2, 5, 4]` is not sorted | `poll()` one by one to get them in order |
| Max-heap | `new PriorityQueue<>()` gives the smallest | `new PriorityQueue<>(Comparator.reverseOrder())` |
| Compare `Integer` from `peek()` | `a.peek() == b.peek()` | `a.peek().equals(b.peek())` |

## 11. Exercises

File: [`StackQueueExercises.java`](../../../src/main/java/com/mastery/interview/foundations/StackQueueExercises.java)

```bash
mvn -Dtest='StackQueueExercisesTest$E01ReverseWithStack' test
mvn -Dtest=StackQueueExercisesTest test
```

| # | Exercise | Example | Tool |
|---|---|---|---|
| 01 | Reverse with a stack | `reverseWithStack("abc")` → `"cba"` | stack |
| 02 | Balanced parentheses | `isBalanced("(())")` → `true` | stack |
| 03 | Valid brackets | `isValid("([]{})")` → `true`, `isValid("([)]")` → `false` | stack |
| 04 | Remove adjacent duplicates | `removeAdjacentDuplicates("abbaca")` → `"ca"` | stack |
| 05 | Backspace | `applyBackspaces("ab#c")` → `"ac"` | stack |
| 06 | Reverse Polish Notation | `evalRpn(["2", "1", "+", "3", "*"])` → `9` | stack |
| 07 | Rotate with a queue | `rotate([1, 2, 3, 4], 1)` → `[2, 3, 4, 1]` | queue |
| 08 | K smallest | `smallestK([5, 1, 4, 2], 2)` → `[1, 2]` | priority queue |
| 09 | Sort with a heap | `heapSorted([3, 1, 2])` → `[1, 2, 3]` | priority queue |
| 10 | K-th largest | `kthLargest([3, 2, 1, 5, 6, 4], 2)` → `5` | priority queue |

---

⬅️ [Table of contents](../../README.md) · [README](../../../README.md)
