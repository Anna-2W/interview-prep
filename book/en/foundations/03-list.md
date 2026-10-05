# F03. List

🇫🇷 [Version française](../../fr/foundations/03-list.md)

## 1. What it is

A `List` is an **ordered** collection that **can grow and shrink**. Like an array, each
element has an index starting at 0, and duplicates are allowed. Unlike an array, you can add
and remove elements.

`List` is an **interface** (a contract). You choose an implementation:

| Implementation | Inside | Use it when |
|---|---|---|
| `ArrayList` | An array that is replaced by a bigger one when full | **Almost always.** The default choice |
| `LinkedList` | Nodes linked to the previous and next node | Rarely. Use `ArrayDeque` for queues instead |

```java
List<String> names = new ArrayList<>();   // declare with the interface, create with the class
```

A list only holds **objects**: `List<Integer>`, not `List<int>`. Java converts `int` to
`Integer` for you (autoboxing).

## 2. Create a list

```java
List<String> a = new ArrayList<>();                   // empty, can be modified
List<String> b = new ArrayList<>(List.of("x", "y"));  // copy with values, can be modified
List<String> c = List.of("x", "y");                   // IMMUTABLE: add/remove/set throw
List<String> d = Arrays.asList("x", "y");             // fixed size: set works, add/remove throw
```

## 3. The methods you must know

| Method | What it does | `ArrayList` | `LinkedList` |
|---|---|---|---|
| `add(x)` | Add at the end | O(1) amortized | O(1) |
| `add(i, x)` | Insert at index `i` (shifts the rest) | O(n) | O(n) |
| `get(i)` | Element at index `i` | **O(1)** | O(n) |
| `set(i, x)` | Replace element at `i` | O(1) | O(n) |
| `remove(int i)` | Remove at **index** `i` | O(n) | O(n) |
| `remove(Object x)` | Remove the first **value** `x` | O(n) | O(n) |
| `size()` | Number of elements | O(1) | O(1) |
| `isEmpty()` | Size is 0 | O(1) | O(1) |
| `contains(x)` | Is `x` in the list | O(n) | O(n) |
| `indexOf(x)` | First index of `x`, or `-1` | O(n) | O(n) |
| `clear()` | Remove everything | O(n) | O(n) |
| `addAll(other)` | Add all elements of another collection | O(m) | O(m) |
| `subList(a, b)` | View from `a` to `b` (**b excluded**) | O(1) | O(1) |

### Useful helpers

| Code | What it does |
|---|---|
| `Collections.sort(list)` or `list.sort(null)` | Sort ascending, in place, O(n log n) |
| `list.sort(Comparator.reverseOrder())` | Sort descending |
| `Collections.reverse(list)` | Reverse in place |
| `Collections.max(list)` / `min` | Biggest / smallest |
| `list.removeIf(x -> x < 0)` | Remove every element matching a condition |
| `String.join(", ", list)` | Join a `List<String>` into one string |
| `new ArrayList<>(list)` | Copy |

**Amortized O(1)** for `ArrayList.add`: most adds are instant. When the inner array is full,
Java creates one about 1.5 times bigger and copies everything (O(n)). This is rare enough that
the average stays O(1).

## 4. Going through a list

```java
List<String> names = new ArrayList<>(List.of("Ada", "Alan", "Grace"));

for (String name : names) {                // read only
    System.out.println(name);
}

for (int i = 0; i < names.size(); i++) {   // index needed
    System.out.println(i + " " + names.get(i));
}

names.removeIf(name -> name.startsWith("A"));   // remove while filtering: use removeIf
```

## 5. When you use it in interviews

- To **collect a result** whose size you do not know in advance: "return all the...".
- `List<List<Integer>>` for results like "all subsets", "all paths", "group by level".
- Adjacency lists for graphs: `List<List<Integer>> graph`.
- When asked "ArrayList or LinkedList?": ArrayList, because `get(i)` is O(1) and memory is
  contiguous (cache-friendly). LinkedList only wins when inserting at a position you already
  hold with an iterator.

## 6. Traps

1. **`remove(int)` vs `remove(Object)`.** On a `List<Integer>`, `list.remove(1)` removes the
   element at **index 1**, not the value 1. For the value: `list.remove(Integer.valueOf(1))`.
2. **`ConcurrentModificationException`.** Removing inside a for-each loop throws. Use
   `removeIf`, or an `Iterator` with `it.remove()`.
3. **`List.of` is immutable.** `List.of(1, 2).add(3)` throws
   `UnsupportedOperationException`. Wrap it: `new ArrayList<>(List.of(1, 2))`.
4. **`Arrays.asList` is fixed size.** `add` and `remove` throw, `set` works and also changes
   the original array.
5. **`==` on `Integer`.** Two `Integer` objects above 127 are not `==`. Use `equals`.
   ```java
   Integer a = 1000, b = 1000;
   a == b;        // false
   a.equals(b);   // true
   ```
6. **`get(i)` on a `LinkedList` in a loop** is O(n) each time: the loop becomes O(n²).
7. **Changing the input list.** If asked to return a new list, copy first.

## 7. Exercises

Code in [`ListExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ListExercises.java).

```bash
mvn -Dtest='ListExercisesTest$E01LastElement' test   # one exercise
mvn -Dtest=ListExercisesTest test                    # the whole chapter
```

| # | Exercise | What it trains |
|---|---|---|
| 01 | `lastElement([4, 8, 15])` → `15` | `get`, `size() - 1` |
| 02 | `sum([1, 2, 3])` → `6` | for-each on a list |
| 03 | `evens([1, 2, 3, 4])` → `[2, 4]` | Build a new list with `add` |
| 04 | `addFirst(["b", "c"], "a")` → `["a", "b", "c"]` | `add(index, x)` |
| 05 | `removeValue([1, 2, 1], 1)` → `[2]` | `remove(Object)` vs `remove(int)`, `removeIf` |
| 06 | `countLongWords(["hi", "hello"], 3)` → `1` | Loop with a condition |
| 07 | `reversed([1, 2, 3])` → `[3, 2, 1]` | Copy, then reverse |
| 08 | `withoutDuplicates(["a", "b", "a"])` → `["a", "b"]` | `contains`, keep the order |
| 09 | `merge([1, 2], [3])` → `[1, 2, 3]` | `addAll` |
| 10 | `sortedCopy(["c", "a", "b"])` → `["a", "b", "c"]` | Sort a copy, input unchanged |
