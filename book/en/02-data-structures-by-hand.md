# 02. Data structures coded by hand

🇫🇷 [Version française](../fr/02-data-structures-by-hand.md)

In F01 to F06 you **used** `ArrayList`, `LinkedList`, `ArrayDeque`, `HashMap`. Here you
**build** them. Interviewers ask "how does it work inside?" and "code it": after this
chapter you can answer both.

| Structure | Java class it mimics | You will code |
|---|---|---|
| Dynamic array | `ArrayList` | `MyArrayList` |
| Singly linked list | `LinkedList` (which is doubly linked) | `MyLinkedList` |
| Stack on an array | `ArrayDeque` used as a stack | `MyStack` |
| Queue on a linked list | `ArrayDeque` used as a queue | `MyQueue` |
| Circular queue (ring buffer) | `ArrayDeque` inside | `MyCircularQueue` |
| Hash table with chaining | `HashMap` | `MyHashMap` |

---

## 1. Dynamic array (`ArrayList`)

An array has a fixed size. A dynamic array hides a real array and **replaces it with a
bigger one** when it is full.

```
 data     [ a | b | c | _ ]      capacity = 4, size = 3
 add(d)   [ a | b | c | d ]      size = 4
 add(e)   full: new array twice as big, copy, then add
          [ a | b | c | d | e | _ | _ | _ ]      capacity = 8, size = 5
```

| Operation | How | Cost |
|---|---|---|
| `get(i)`, `set(i, x)` | `data[i]` | O(1) |
| `add(x)` at the end | `data[size++] = x`, grow if full | O(1) amortized |
| `add(i, x)` | shift `i..size-1` one box to the right | O(n) |
| `remove(i)` | shift `i+1..size-1` one box to the left | O(n) |
| `contains(x)` | loop | O(n) |

**Why double the size?** If you grow by +1 each time, every add copies everything: O(n²) for
n adds. If you double, copies happen at sizes 1, 2, 4, 8... The total copy work is
1 + 2 + 4 + ... + n < 2n, so each add costs O(1) on average. Java's `ArrayList` grows by 1.5×,
same idea.

| Trap | Wrong | Right |
|---|---|---|
| Valid index | `i <= size` | `0 <= i < size` (and `<= size` only for `add(i, x)`) |
| Check against the capacity | `i < data.length` | `i < size` |
| Shift direction on insert | from left to right (overwrites) | from the end towards `i` |
| After remove | old last box still holds the object | `data[size] = null` (lets the GC free it) |
| Generic array | `new T[10]` does not compile | `(T[]) new Object[10]` |

---

## 2. Singly linked list

Each **node** holds a value and a reference to the **next** node. The list keeps `head`
(first node), often `tail` (last node) and `size`.

```
 head                         tail
  │                             │
  ▼                             ▼
 [ 3 | ●]──▶[ 7 | ●]──▶[ 1 | ●]──▶ null
```

```java
class Node<T> {
    T value;
    Node<T> next;
}
```

| Operation | How | Cost |
|---|---|---|
| `addFirst(x)` | new node, `node.next = head`, `head = node` | O(1) |
| `addLast(x)` with `tail` | `tail.next = node`, `tail = node` | O(1) |
| `removeFirst()` | `head = head.next` | O(1) |
| `removeLast()` | walk to the node **before** the tail | O(n) (singly) |
| `get(i)` | walk `i` steps from `head` | O(n) |
| `contains(x)` | walk the whole list | O(n) |

**Doubly linked list**: each node also has `prev`. Then `removeLast()` is O(1). Java's
`LinkedList` is doubly linked.

```
 null◀──[● | 3 | ●]◀──▶[● | 7 | ●]◀──▶[● | 1 | ●]──▶null
```

**Circular linked list**: the last node points back to the first one instead of `null`.
Used for round-robin scheduling.

### Rare variants (🟢, just know the name)

| Variant | Idea |
|---|---|
| Skip list | several levels of "express" links over a sorted list: search in O(log n). Used by Redis sorted sets |
| Unrolled linked list | each node holds a small array of values: fewer nodes, better cache use |
| Lock-free linked list | updated with atomic compare-and-set instead of locks, for concurrent code |

### Traps

| Trap | Wrong | Right |
|---|---|---|
| Empty list | `head.next` when `head == null` | check `head == null` first |
| Lose the list | move `head` to walk | walk with a separate `current` variable |
| One element | update `head` only | when the list becomes empty, `tail = null` too |
| Change order of links | `head = node; node.next = head;` (cycle) | `node.next = head; head = node;` |
| Forget `size` | count by walking each time | update `size` in every add / remove |

---

## 3. Stack on an array

A stack only touches **one end**: the top. An array with a `top` index does it perfectly.

```
 push(5), push(8), push(2)
 data [ 5 | 8 | 2 | _ ]    size = 3, top = data[size - 1] = 2
 pop() -> 2                size = 2
```

| Operation | How | Cost |
|---|---|---|
| `push(x)` | `data[size++] = x`, grow if full | O(1) amortized |
| `pop()` | `return data[--size]` | O(1) |
| `peek()` | `return data[size - 1]` | O(1) |

Uses: undo, matching brackets, evaluating expressions, iterative DFS, the **call stack** of
your program itself.

---

## 4. Queue on a linked list

A queue adds at the **back** and removes at the **front**. With a linked list keeping `head`
and `tail`, both are O(1).

```
 offer(5), offer(8), offer(2)
 head ─▶ [5] ─▶ [8] ─▶ [2] ◀─ tail
 poll() -> 5
 head ─▶ [8] ─▶ [2] ◀─ tail
```

| Operation | How | Cost |
|---|---|---|
| `offer(x)` | add after `tail` | O(1) |
| `poll()` | remove `head` | O(1) |
| `peek()` | `head.value` | O(1) |

Why not an `ArrayList` with `remove(0)`? Because it shifts everything: O(n) per poll.

---

## 5. Circular queue (ring buffer)

A fixed-size array where `head` and `tail` **wrap around** with `%`. No shifting, no
allocation: perfect for buffers (audio, network, logs).

```
 capacity 4
 offer 1, 2, 3       [ 1 | 2 | 3 | _ ]   head = 0, size = 3
 poll -> 1           [ _ | 2 | 3 | _ ]   head = 1, size = 2
 offer 4, 5          [ 5 | 2 | 3 | 4 ]   5 went to index (1 + 3) % 4 = 0
```

| Computation | Formula |
|---|---|
| index of the next free box | `(head + size) % capacity` |
| after a poll | `head = (head + 1) % capacity` |
| full | `size == capacity` |
| empty | `size == 0` |

Keeping a `size` counter is the simplest way to tell "full" from "empty" (with only `head`
and `tail`, both cases have `head == tail`).

---

## 6. Hash table (`HashMap`)

An array of **buckets**. A key goes to bucket `index = hash(key) % capacity`. Several keys
in the same bucket form a small linked list: this is **chaining**.

```
 capacity 4
 put("Ada", 36)   hash % 4 = 1
 put("Bob", 30)   hash % 4 = 3
 put("Eve", 25)   hash % 4 = 1   collision with Ada

 buckets
 [0] null
 [1] (Ada,36) ─▶ (Eve,25) ─▶ null
 [2] null
 [3] (Bob,30) ─▶ null
```

| Step | Code |
|---|---|
| index of the bucket | `Math.floorMod(key.hashCode(), buckets.length)` |
| `get(k)` | walk the bucket, return the value whose key `equals(k)` |
| `put(k, v)` | key found in the bucket: replace the value; otherwise add a node |
| `remove(k)` | unlink the node from the bucket |
| load factor | `size / capacity`; above **0.75**, double the capacity and **re-insert every entry** |

| Operation | Average | Worst (every key in one bucket) |
|---|---|---|
| `get`, `put`, `remove` | O(1) | O(n) |

Java's `HashMap` turns a bucket into a red-black tree above 8 entries, so its worst case is
O(log n).

**Why `floorMod` and not `%`?** `hashCode()` can be negative, and `-7 % 4` is `-3`: an
invalid index. `Math.floorMod(-7, 4)` is `1`.

### Other collision strategy: open addressing

No lists: if the bucket is taken, try the next one (`index + 1`, `+ 2`...). This is
**linear probing**. Variants seen in your topic list: **Robin Hood hashing** (steal the box
from a key that is closer to home), **cuckoo hashing** (two tables, a key kicks out the
other).

### Close cousins (🟠 / 🟢)

| Structure | Idea | Trade-off |
|---|---|---|
| Bloom filter | k hash functions set k bits in a bit array; "maybe present" or "surely absent" | tiny memory, false positives possible, no remove |
| Count-Min Sketch | same idea with counters: approximate frequencies | tiny memory, overestimates |

### Traps

| Trap | Wrong | Right |
|---|---|---|
| Negative hash | `key.hashCode() % n` | `Math.floorMod(key.hashCode(), n)` |
| Compare keys | `node.key == key` | `node.key.equals(key)` |
| `put` an existing key | add a second node | find it and replace the value |
| Resize | copy the bucket array as it is | re-insert every entry: the index depends on the capacity |
| Forget the size | resize never happens | `size++` only for a **new** key |

---

## 7. Linked list problems (very common in interviews)

| Problem | Idea | Cost |
|---|---|---|
| Length | walk and count | O(n) |
| Reverse | three pointers `prev`, `current`, `next` | O(n) time, O(1) space |
| Middle | **slow** moves 1, **fast** moves 2: when fast ends, slow is in the middle | O(n) |
| Cycle | slow and fast: if they ever meet, there is a cycle (Floyd) | O(n), O(1) space |
| Merge two sorted lists | dummy node, always take the smaller head | O(n + m) |
| Remove n-th from the end | fast goes n steps ahead, then both move together | O(n), one pass |
| Remove duplicates (sorted) | if `current.value == current.next.value`, skip `next` | O(n) |
| Palindrome | find the middle, reverse the second half, compare | O(n), O(1) space |

**The dummy node trick**: create `ListNode dummy = new ListNode(0)` before the head. You
never need a special case for "the head changes". Return `dummy.next`.

```java
ListNode prev = null;
ListNode current = head;
while (current != null) {
    ListNode next = current.next;
    current.next = prev;
    prev = current;
    current = next;
}
return prev;
```

This is the in-place reversal. Learn it by heart, it shows up everywhere.

---

## 8. Exercises

Package [`datastructures`](../../src/main/java/com/mastery/interview/datastructures/).
Fields and constructors are given: you write the methods.

| # | File | Methods to write | Run |
|---|---|---|---|
| 1 | `MyArrayList.java` | `add`, `add(i, x)`, `get`, `set`, `remove`, `size`, `isEmpty`, `contains`, `indexOf` | `mvn -Dtest=MyArrayListTest test` |
| 2 | `MyLinkedList.java` | `addFirst`, `addLast`, `removeFirst`, `removeLast`, `get`, `contains`, `size`, `isEmpty`, `reverse` | `mvn -Dtest=MyLinkedListTest test` |
| 3 | `MyStack.java` | `push`, `pop`, `peek`, `size`, `isEmpty` | `mvn -Dtest=MyStackTest test` |
| 4 | `MyQueue.java` | `offer`, `poll`, `peek`, `size`, `isEmpty` | `mvn -Dtest=MyQueueTest test` |
| 5 | `MyCircularQueue.java` | `offer`, `poll`, `peek`, `size`, `isEmpty`, `isFull` | `mvn -Dtest=MyCircularQueueTest test` |
| 6 | `MyHashMap.java` | `put`, `get`, `remove`, `containsKey`, `size`, `isEmpty` (+ resize) | `mvn -Dtest=MyHashMapTest test` |
| 7 | `LinkedListProblems.java` | 8 classic problems below | `mvn -Dtest=LinkedListProblemsTest test` |

Do them in this order: each one reuses ideas from the previous ones.

| # | Problem | Example |
|---|---|---|
| E01 | `length` | `1 → 2 → 3` gives `3` |
| E02 | `reverse` | `1 → 2 → 3` gives `3 → 2 → 1` |
| E03 | `middle` | `1 → 2 → 3 → 4 → 5` gives the node `3`; `1 → 2 → 3 → 4` gives `3` |
| E04 | `hasCycle` | `1 → 2 → 3 → back to 2` gives `true` |
| E05 | `mergeSorted` | `1 → 3` and `2 → 4` give `1 → 2 → 3 → 4` |
| E06 | `removeNthFromEnd` | `1 → 2 → 3 → 4`, n = 2 gives `1 → 2 → 4` |
| E07 | `removeDuplicates` | `1 → 1 → 2 → 3 → 3` gives `1 → 2 → 3` |
| E08 | `isPalindrome` | `1 → 2 → 2 → 1` gives `true` |

Some tests use a million operations with a 2-second limit: an O(n) `add` or `poll` where
O(1) is expected will fail.

---

⬅️ [Table of contents](../README.md) · [README](../../README.md)
