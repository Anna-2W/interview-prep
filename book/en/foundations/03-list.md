# F03. List

🇫🇷 [Version française](../../fr/foundations/03-list.md)

## 1. What it is

A `List` is an **ordered** collection that **can grow and shrink**. Index starts at 0,
duplicates are allowed.

`List` is an interface. Two implementations:

| Implementation | Inside | When |
|---|---|---|
| `ArrayList` | An array, replaced by a bigger one when full | **Almost always** |
| `LinkedList` | Nodes linked to the previous and the next | Rarely |

```java
List<String> names = new ArrayList<>();
```

A list holds objects only: `List<Integer>`, never `List<int>`.

## 2. Create a list

| Code | Result | Can be modified? |
|---|---|---|
| `new ArrayList<>()` | `[]` | yes |
| `new ArrayList<>(List.of("a", "b"))` | `[a, b]` | yes |
| `List.of("a", "b")` | `[a, b]` | **no**, everything throws |
| `List.copyOf(list)` | immutable copy | **no** |
| `Arrays.asList("a", "b")` | `[a, b]` | `set` yes, `add`/`remove` no |

## 3. All the methods of `List`

Every example starts from `list = new ArrayList<>(List.of("a", "b", "c"))`.

### Add

| Method | Example | Result |
|---|---|---|
| `add(x)` | `list.add("d")` | `[a, b, c, d]`, returns `true` |
| `add(i, x)` | `list.add(1, "x")` | `[a, x, b, c]` |
| `addFirst(x)` | `list.addFirst("z")` | `[z, a, b, c]` |
| `addLast(x)` | `list.addLast("d")` | `[a, b, c, d]` |
| `addAll(c)` | `list.addAll(List.of("d", "e"))` | `[a, b, c, d, e]` |
| `addAll(i, c)` | `list.addAll(0, List.of("z"))` | `[z, a, b, c]` |

### Read

| Method | Example | Result |
|---|---|---|
| `get(i)` | `list.get(0)` | `"a"` |
| `getFirst()` | `list.getFirst()` | `"a"` |
| `getLast()` | `list.getLast()` | `"c"` |
| `size()` | `list.size()` | `3` |
| `isEmpty()` | `list.isEmpty()` | `false` |

### Search

| Method | Example | Result |
|---|---|---|
| `contains(x)` | `list.contains("b")` | `true` |
| `containsAll(c)` | `list.containsAll(List.of("a", "c"))` | `true` |
| `indexOf(x)` | `list.indexOf("b")` | `1` |
| `indexOf(x)` absent | `list.indexOf("z")` | `-1` |
| `lastIndexOf(x)` | `List.of("a", "b", "a").lastIndexOf("a")` | `2` |

### Change

| Method | Example | Result |
|---|---|---|
| `set(i, x)` | `list.set(1, "x")` | `[a, x, c]`, returns `"b"` |
| `replaceAll(f)` | `list.replaceAll(String::toUpperCase)` | `[A, B, C]` |
| `sort(comparator)` | `list.sort(Comparator.reverseOrder())` | `[c, b, a]` |
| `sort(null)` | `list.sort(null)` | natural order |

### Remove

| Method | Example | Result |
|---|---|---|
| `remove(int i)` | `list.remove(0)` | `[b, c]`, returns `"a"` |
| `remove(Object x)` | `list.remove("b")` | `[a, c]`, returns `true` |
| `removeFirst()` | `list.removeFirst()` | `[b, c]`, returns `"a"` |
| `removeLast()` | `list.removeLast()` | `[a, b]`, returns `"c"` |
| `removeIf(test)` | `list.removeIf(s -> s.equals("b"))` | `[a, c]` |
| `removeAll(c)` | `list.removeAll(List.of("a", "b"))` | `[c]` |
| `retainAll(c)` | `list.retainAll(List.of("a", "z"))` | `[a]` |
| `clear()` | `list.clear()` | `[]` |

### Views and copies

| Method | Example | Result |
|---|---|---|
| `subList(a, b)` | `list.subList(0, 2)` | `[a, b]` (b excluded) |
| `reversed()` | `list.reversed()` | `[c, b, a]` |
| `toArray()` | `list.toArray()` | `Object[]` `[a, b, c]` |
| `toArray(array)` | `list.toArray(new String[0])` | `String[]` `[a, b, c]` |
| `toArray(generator)` | `list.toArray(String[]::new)` | `String[]` `[a, b, c]` |
| `List.of(...)` | `List.of(1, 2)` | `[1, 2]` immutable |
| `List.copyOf(c)` | `List.copyOf(list)` | `[a, b, c]` immutable |

### Go through

| Method | Example | Result |
|---|---|---|
| `forEach(f)` | `list.forEach(System.out::println)` | prints `a`, `b`, `c` |
| `iterator()` | `list.iterator().next()` | `"a"` |
| `listIterator()` | `list.listIterator().next()` | `"a"` |
| `listIterator(i)` | `list.listIterator(3).previous()` | `"c"` |
| `stream()` | `list.stream().map(String::toUpperCase).toList()` | `[A, B, C]` |
| `parallelStream()` | `list.parallelStream().count()` | `3` |
| `spliterator()` | `list.spliterator().estimateSize()` | `3` (used by streams, rare) |

### Compare

| Method | Example | Result |
|---|---|---|
| `equals(o)` | `list.equals(List.of("a", "b", "c"))` | `true` |
| `hashCode()` | `list.hashCode()` | `126145` |

### `ArrayList` only

| Method | Example | Result |
|---|---|---|
| `ensureCapacity(n)` | `arrayList.ensureCapacity(1000)` | room for 1000 without growing |
| `trimToSize()` | `arrayList.trimToSize()` | inner array cut to `size()` |
| `clone()` | `arrayList.clone()` | shallow copy |

### `Iterator` and `ListIterator`

| Method | What it does |
|---|---|
| `hasNext()` / `next()` | Is there a next element / get it |
| `remove()` | Remove the element just returned (safe during a loop) |
| `hasPrevious()` / `previous()` | `ListIterator` only: go backwards |
| `nextIndex()` / `previousIndex()` | `ListIterator` only: current position |
| `set(x)` / `add(x)` | `ListIterator` only: replace / insert at the current position |

### Useful helpers from `Collections`

| Method | Example | Result |
|---|---|---|
| `Collections.sort(list)` | with `[c, a, b]` | `[a, b, c]` |
| `Collections.reverse(list)` | with `[a, b, c]` | `[c, b, a]` |
| `Collections.max(list)` / `min` | with `[3, 9, 2]` | `9` / `2` |
| `Collections.frequency(list, x)` | `frequency([a, b, a], "a")` | `2` |
| `Collections.swap(list, i, j)` | `swap([a, b, c], 0, 2)` | `[c, b, a]` |
| `Collections.nCopies(n, x)` | `nCopies(3, "x")` | `[x, x, x]` |
| `Collections.shuffle(list)` | with `[a, b, c]` | random order |
| `Collections.unmodifiableList(list)` | | read-only view |
| `Collections.emptyList()` | | `[]` immutable |

## 4. Cost: `ArrayList` vs `LinkedList`

| Operation | `ArrayList` | `LinkedList` |
|---|---|---|
| `get(i)`, `set(i, x)` | **O(1)** | O(n) |
| `add(x)` at the end | O(1) amortized | O(1) |
| `addFirst`, `removeFirst` | O(n) | **O(1)** |
| `add(i, x)`, `remove(i)` | O(n) | O(n) |
| `contains`, `indexOf` | O(n) | O(n) |

Amortized O(1): when full, the inner array is replaced by one 1.5 times bigger (O(n) copy),
but it happens so rarely that the average stays O(1).

## 5. Going through a list

```java
for (String name : names) {
    System.out.println(name);
}

for (int i = 0; i < names.size(); i++) {
    System.out.println(i + " " + names.get(i));
}

names.removeIf(name -> name.startsWith("A"));
```

## 6. In interviews

- Collect a result of unknown size: "return all the...".
- `List<List<Integer>>`: all subsets, all paths, levels of a tree.
- Graphs: `List<List<Integer>> graph` (adjacency list).
- "ArrayList or LinkedList?": ArrayList, `get(i)` is O(1) and memory is contiguous.

## 7. Traps

| Trap | Wrong | Right |
|---|---|---|
| Remove a value from `List<Integer>` | `list.remove(1)` removes **index** 1 | `list.remove(Integer.valueOf(1))` |
| Remove inside a for-each | `ConcurrentModificationException` | `removeIf` or `iterator.remove()` |
| Modify `List.of` | `List.of(1, 2).add(3)` throws | `new ArrayList<>(List.of(1, 2))` |
| `add` on `Arrays.asList` | throws | `new ArrayList<>(Arrays.asList(...))` |
| Compare `Integer` | `a == b` false above 127 | `a.equals(b)` |
| `get(i)` loop on `LinkedList` | O(n²) | for-each |
| Return a new list | change the input | copy first |

## 8. Exercises

File: [`ListExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ListExercises.java)

```bash
mvn -Dtest='ListExercisesTest$E01LastElement' test
mvn -Dtest=ListExercisesTest test
```

| # | Exercise | Example |
|---|---|---|
| 01 | Last element | `lastElement([4, 8, 15])` → `15` |
| 02 | Sum | `sum([1, 2, 3])` → `6` |
| 03 | Evens (new list) | `evens([1, 2, 3, 4])` → `[2, 4]` |
| 04 | Add first | `addFirst(["b", "c"], "a")` → `["a", "b", "c"]` |
| 05 | Remove every occurrence | `removeValue([1, 2, 1], 1)` → `[2]` |
| 06 | Count long words | `countLongWords(["hi", "hello", "hey"], 3)` → `1` |
| 07 | Reversed (new list) | `reversed([1, 2, 3])` → `[3, 2, 1]` |
| 08 | Without duplicates | `withoutDuplicates(["a", "b", "a"])` → `["a", "b"]` |
| 09 | Merge | `merge([1, 2], [3])` → `[1, 2, 3]` |
| 10 | Sorted copy | `sortedCopy(["c", "a", "b"])` → `["a", "b", "c"]` |

---

⬅️ [Table of contents](../../README.md) · [README](../../../README.md)
