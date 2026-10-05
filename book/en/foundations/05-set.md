# F05. Set

🇫🇷 [Version française](../../fr/foundations/05-set.md)

## 1. What it is

A `Set` is a collection **without duplicates**. Adding an element that is already there does
nothing. There is no index.

```
 add("a"), add("b"), add("a")   ->   {a, b}
```

- `HashSet`: add, remove, `contains` in **O(1)** on average.
- A `HashSet` is a `HashMap` where only the keys are used.

| Implementation | Order | Time |
|---|---|---|
| `HashSet` | no order | O(1) |
| `LinkedHashSet` | insertion order | O(1) |
| `TreeSet` | sorted | O(log n) |

## 2. Create a set

| Code | Result | Can be modified? |
|---|---|---|
| `new HashSet<>()` | `[]` | yes |
| `new HashSet<>(List.of(3, 1, 3, 2))` | `[1, 2, 3]` (duplicates removed) | yes |
| `Set.of("a", "b")` | `[a, b]` | **no** |
| `Set.copyOf(list)` | immutable copy without duplicates | **no** |
| `new TreeSet<>(list)` | sorted, no duplicates | yes |
| `HashSet.newHashSet(100)` | `[]`, room for 100 elements | yes |

## 3. All the methods of `Set`

Every example starts from `set = new HashSet<>(Set.of("a", "b", "c"))`.

| Method | Example | Result |
|---|---|---|
| `add(x)` new | `set.add("d")` | `[a, b, c, d]`, returns `true` |
| `add(x)` already there | `set.add("a")` | nothing changes, returns `false` |
| `remove(x)` | `set.remove("a")` | `[b, c]`, returns `true` |
| `contains(x)` | `set.contains("b")` | `true` |
| `size()` | `set.size()` | `3` |
| `isEmpty()` | `set.isEmpty()` | `false` |
| `clear()` | `set.clear()` | `[]` |
| `addAll(c)` | `set.addAll(Set.of("c", "d"))` | `[a, b, c, d]` |
| `removeAll(c)` | `set.removeAll(Set.of("a"))` | `[b, c]` |
| `retainAll(c)` | `set.retainAll(Set.of("b", "c", "z"))` | `[b, c]` |
| `containsAll(c)` | `set.containsAll(Set.of("a", "b"))` | `true` |
| `removeIf(test)` | `set.removeIf(s -> s.equals("b"))` | `[a, c]` |
| `iterator()` | `set.iterator().next()` | one element, order not guaranteed |
| `forEach(f)` | `set.forEach(System.out::println)` | prints each element |
| `stream()` | `set.stream().map(String::toUpperCase).toList()` | `[A, B, C]` (any order) |
| `toArray(generator)` | `set.toArray(String[]::new)` | `String[]` with the 3 elements |
| `equals(o)` | `Set.of(1, 2).equals(Set.of(2, 1))` | `true` |
| `hashCode()` | `Set.of("a").hashCode()` | `97` |
| `Set.of(...)` | `Set.of("a", "b")` | immutable, refuses duplicates |
| `Set.copyOf(c)` | `Set.copyOf(List.of(1, 1, 2))` | `[1, 2]` immutable |
| `spliterator()` | `set.spliterator().estimateSize()` | `3` (used by streams, rare) |
| `clone()` (`HashSet`) | `hashSet.clone()` | shallow copy |

### The 4 set operations

| Operation | Code | `a = {1, 2, 3}`, `b = {2, 3, 4}` |
|---|---|---|
| Union (a or b) | `a.addAll(b)` | `{1, 2, 3, 4}` |
| Intersection (a and b) | `a.retainAll(b)` | `{2, 3}` |
| Difference (a not b) | `a.removeAll(b)` | `{1}` |
| Subset (all of b in a?) | `a.containsAll(b)` | `false` |

These methods **change** `a`. To keep `a`, copy first: `Set<Integer> r = new HashSet<>(a);`.

## 4. `TreeSet` only (sorted)

Every example starts from `t = new TreeSet<>(Set.of(10, 20, 30))`.

| Method | Example | Result |
|---|---|---|
| `first()` / `last()` | `t.first()` | `10` / `30` |
| `getFirst()` / `getLast()` | `t.getFirst()` | `10` / `30` |
| `floor(x)` | `t.floor(25)` | `20` (biggest `<= 25`) |
| `ceiling(x)` | `t.ceiling(25)` | `30` (smallest `>= 25`) |
| `lower(x)` | `t.lower(20)` | `10` (biggest `< 20`) |
| `higher(x)` | `t.higher(20)` | `30` (smallest `> 20`) |
| `headSet(x)` | `t.headSet(20)` | `[10]` (x excluded) |
| `headSet(x, true)` | `t.headSet(20, true)` | `[10, 20]` |
| `tailSet(x)` | `t.tailSet(20)` | `[20, 30]` (x included) |
| `subSet(a, b)` | `t.subSet(10, 30)` | `[10, 20]` |
| `pollFirst()` / `pollLast()` | `t.pollFirst()` | returns `10` and removes it |
| `removeFirst()` / `removeLast()` | `t.removeFirst()` | returns `10` and removes it |
| `descendingSet()` | `t.descendingSet()` | `[30, 20, 10]` |
| `reversed()` | `t.reversed()` | `[30, 20, 10]` |
| `descendingIterator()` | `t.descendingIterator().next()` | `30` |
| `comparator()` | `t.comparator()` | `null` (natural order) |
| `addFirst(x)` / `addLast(x)` | `t.addFirst(5)` | throws `UnsupportedOperationException` |

## 5. `LinkedHashSet` only (insertion order)

Every example starts from `l = new LinkedHashSet<>(List.of("b", "c"))`.

| Method | Example | Result |
|---|---|---|
| `addFirst(x)` | `l.addFirst("a")` | `[a, b, c]` |
| `addFirst(x)` already there | `l.addFirst("c")` | `[c, b]` (moved to the front) |
| `addLast(x)` | `l.addLast("z")` | `[b, c, z]` |
| `getFirst()` / `getLast()` | `l.getFirst()` | `"b"` / `"c"` |
| `removeFirst()` / `removeLast()` | `l.removeFirst()` | returns `"b"`, `[c]` |
| `reversed()` | `l.reversed()` | `[c, b]` |
| `LinkedHashSet.newLinkedHashSet(n)` | `LinkedHashSet.newLinkedHashSet(100)` | `[]`, room for 100 |

## 6. Going through a set

```java
for (String s : set) {
    System.out.println(s);
}
```

No `get(i)`: a set has no index.

## 7. In interviews

- "Contains duplicate?": add everything, compare sizes, or stop when `add` returns `false`.
- "Have I already visited this?": BFS and DFS keep a `Set<Node> visited`.
- Remove duplicates from a list: `new ArrayList<>(new LinkedHashSet<>(list))` keeps the order.
- Common elements of two lists: intersection.
- `contains` on a `List` is O(n), on a `HashSet` O(1): converting a list to a set often turns
  O(n²) into O(n).

## 8. Traps

| Trap | Wrong | Right |
|---|---|---|
| Order | rely on `HashSet` order | `LinkedHashSet` or `TreeSet` |
| Get by index | `set.get(0)` does not exist | loop, or `TreeSet.first()` |
| Duplicates in `Set.of` | `Set.of("a", "a")` throws `IllegalArgumentException` | `new HashSet<>(List.of("a", "a"))` |
| Keep the original | `a.retainAll(b)` changes `a` | `r = new HashSet<>(a); r.retainAll(b);` |
| `add` result ignored | test `contains` then `add` | `if (!set.add(x))` means x was already there |
| Own objects | no `equals`/`hashCode`: duplicates stay | implement both, or use a record |
| `TreeSet` of own objects | `ClassCastException` | implement `Comparable` or pass a `Comparator` |

## 9. Exercises

File: [`SetExercises.java`](../../../src/main/java/com/mastery/interview/foundations/SetExercises.java)

```bash
mvn -Dtest='SetExercisesTest$E01HasDuplicate' test
mvn -Dtest=SetExercisesTest test
```

| # | Exercise | Example |
|---|---|---|
| 01 | Has a duplicate | `hasDuplicate([1, 2, 3, 1])` → `true` |
| 02 | Count distinct | `countDistinct([1, 2, 2, 3])` → `3` |
| 03 | Common elements | `common({1, 2, 3}, {2, 3, 4})` → `{2, 3}` |
| 04 | All elements | `union({1, 2}, {2, 3})` → `{1, 2, 3}` |
| 05 | Only in the first | `onlyInFirst({1, 2, 3}, {2})` → `{1, 3}` |
| 06 | Count different letters | `countUniqueChars("hello")` → `4` |
| 07 | First repeated character | `firstRepeated("abcb")` → `'b'` |
| 08 | Is subset | `isSubset({1, 2}, {1, 2, 3})` → `true` |
| 09 | Sorted without duplicates | `sortedUnique([3, 1, 3, 2])` → `[1, 2, 3]` |
| 10 | Pangram (all 26 letters) | `isPangram("the quick brown fox jumps over the lazy dog")` → `true` |

---

⬅️ [Table of contents](../../README.md) · [README](../../../README.md)
