# F04. Map

🇫🇷 [Version française](../../fr/foundations/04-map.md)

## 1. What it is

A `Map` stores **key → value** pairs. You find a value **by its key**.

```
 "Ada"   -> 36
 "Alan"  -> 41
 "Grace" -> 85
```

- Keys are **unique**: putting an existing key replaces its value.
- Values can repeat.
- `HashMap`: find, add, remove by key in **O(1)** on average.

| Implementation | Order of the keys | Time |
|---|---|---|
| `HashMap` | no order | O(1) |
| `LinkedHashMap` | insertion order | O(1) |
| `TreeMap` | sorted | O(log n) |

## 2. Create a map

| Code | Result | Can be modified? |
|---|---|---|
| `new HashMap<>()` | `{}` | yes |
| `new HashMap<>(Map.of("Ada", 36))` | `{Ada=36}` | yes |
| `Map.of("Ada", 36, "Alan", 41)` | `{Ada=36, Alan=41}` | **no** |
| `Map.ofEntries(Map.entry("Ada", 36))` | `{Ada=36}` | **no** |
| `Map.copyOf(map)` | immutable copy | **no** |
| `new TreeMap<>(map)` | same pairs, sorted keys | yes |

## 3. All the methods of `Map`

Every example starts from `map = new HashMap<>(Map.of("Ada", 36, "Alan", 41))`.
A `HashMap` may print its keys in any order.

### Read

| Method | Example | Result |
|---|---|---|
| `get(k)` | `map.get("Ada")` | `36` |
| `get(k)` absent | `map.get("Bob")` | `null` |
| `getOrDefault(k, d)` | `map.getOrDefault("Bob", 0)` | `0` |
| `containsKey(k)` | `map.containsKey("Ada")` | `true` |
| `containsValue(v)` | `map.containsValue(41)` | `true` (O(n)) |
| `size()` | `map.size()` | `2` |
| `isEmpty()` | `map.isEmpty()` | `false` |

### Add and change

| Method | Example | Result |
|---|---|---|
| `put(k, v)` new key | `map.put("Bob", 30)` | adds `Bob=30`, returns `null` |
| `put(k, v)` existing key | `map.put("Ada", 40)` | `Ada=40`, returns `36` (old value) |
| `putAll(m)` | `map.putAll(Map.of("Bob", 30))` | adds `Bob=30` |
| `putIfAbsent(k, v)` | `map.putIfAbsent("Ada", 99)` | nothing changes, returns `36` |
| `replace(k, v)` | `map.replace("Ada", 40)` | `Ada=40`, returns `36` |
| `replace(k, v)` absent | `map.replace("Bob", 1)` | nothing changes, returns `null` |
| `replace(k, old, new)` | `map.replace("Ada", 36, 40)` | `Ada=40`, returns `true` |
| `replaceAll(f)` | `map.replaceAll((k, v) -> v + 1)` | `{Ada=37, Alan=42}` |

### Compute (the most useful ones)

| Method | Example | Result |
|---|---|---|
| `merge(k, v, f)` present | `map.merge("Ada", 1, Integer::sum)` | `Ada=37` |
| `merge(k, v, f)` absent | `map.merge("Bob", 1, Integer::sum)` | adds `Bob=1` |
| `compute(k, f)` | `map.compute("Ada", (k, v) -> v + 1)` | `Ada=37` |
| `compute(k, f)` absent | `map.compute("Bob", (k, v) -> v == null ? 1 : v + 1)` | adds `Bob=1` |
| `computeIfAbsent(k, f)` absent | `map.computeIfAbsent("Bob", k -> 0)` | adds `Bob=0`, returns `0` |
| `computeIfAbsent(k, f)` present | `map.computeIfAbsent("Ada", k -> 0)` | nothing changes, returns `36` |
| `computeIfPresent(k, f)` | `map.computeIfPresent("Ada", (k, v) -> v + 1)` | `Ada=37` |
| `computeIfPresent(k, f)` absent | `map.computeIfPresent("Bob", (k, v) -> v + 1)` | nothing changes, returns `null` |

If the function returns `null`, `compute`, `computeIfPresent` and `merge` **remove** the key.

### Remove

| Method | Example | Result |
|---|---|---|
| `remove(k)` | `map.remove("Ada")` | `{Alan=41}`, returns `36` |
| `remove(k, v)` | `map.remove("Ada", 99)` | nothing changes, returns `false` |
| `clear()` | `map.clear()` | `{}` |

### Views

| Method | Example | Result |
|---|---|---|
| `keySet()` | `map.keySet()` | `[Ada, Alan]` |
| `values()` | `map.values()` | `[36, 41]` |
| `entrySet()` | `map.entrySet()` | `[Ada=36, Alan=41]` |
| `forEach(f)` | `map.forEach((k, v) -> System.out.println(k + " " + v))` | prints `Ada 36`, `Alan 41` |

### Compare and build

| Method | Example | Result |
|---|---|---|
| `equals(o)` | `map.equals(Map.of("Alan", 41, "Ada", 36))` | `true` (order does not matter) |
| `hashCode()` | `map.hashCode()` | a number, equal maps give the same |
| `Map.entry(k, v)` | `Map.entry("Ada", 36)` | `Ada=36` |
| `Map.of(...)` | `Map.of("a", 1)` | `{a=1}` immutable, up to 10 pairs |
| `Map.ofEntries(...)` | `Map.ofEntries(Map.entry("a", 1), Map.entry("b", 2))` | `{a=1, b=2}` immutable |
| `Map.copyOf(m)` | `Map.copyOf(map)` | immutable copy |

### `Map.Entry` (one pair)

| Method | Example with `e = Map.entry("Ada", 36)` | Result |
|---|---|---|
| `getKey()` | `e.getKey()` | `"Ada"` |
| `getValue()` | `e.getValue()` | `36` |
| `setValue(v)` | inside `for (var e : map.entrySet()) e.setValue(0);` | changes the map |
| `Map.Entry.comparingByKey()` | `entries.sort(Map.Entry.comparingByKey())` | sort pairs by key |
| `Map.Entry.comparingByValue()` | `entries.sort(Map.Entry.comparingByValue())` | sort pairs by value |

## 4. `TreeMap` only (sorted keys)

Every example starts from `t = new TreeMap<>(Map.of(10, "a", 20, "b", 30, "c"))`.

| Method | Example | Result |
|---|---|---|
| `firstKey()` / `lastKey()` | `t.firstKey()` | `10` / `30` |
| `firstEntry()` / `lastEntry()` | `t.firstEntry()` | `10=a` / `30=c` |
| `floorKey(k)` | `t.floorKey(25)` | `20` (biggest `<= 25`) |
| `ceilingKey(k)` | `t.ceilingKey(25)` | `30` (smallest `>= 25`) |
| `lowerKey(k)` | `t.lowerKey(20)` | `10` (biggest `< 20`) |
| `higherKey(k)` | `t.higherKey(20)` | `30` (smallest `> 20`) |
| `floorEntry`, `ceilingEntry`, `lowerEntry`, `higherEntry` | `t.floorEntry(25)` | `20=b` |
| `headMap(k)` | `t.headMap(20)` | `{10=a}` (k excluded) |
| `headMap(k, true)` | `t.headMap(20, true)` | `{10=a, 20=b}` |
| `tailMap(k)` | `t.tailMap(20)` | `{20=b, 30=c}` (k included) |
| `subMap(a, b)` | `t.subMap(10, 30)` | `{10=a, 20=b}` |
| `pollFirstEntry()` | `t.pollFirstEntry()` | returns `10=a` and removes it |
| `pollLastEntry()` | `t.pollLastEntry()` | returns `30=c` and removes it |
| `descendingMap()` | `t.descendingMap()` | `{30=c, 20=b, 10=a}` |
| `descendingKeySet()` | `t.descendingKeySet()` | `[30, 20, 10]` |
| `navigableKeySet()` | `t.navigableKeySet()` | `[10, 20, 30]` |
| `comparator()` | `t.comparator()` | `null` (natural order) |

## 5. `LinkedHashMap` only (insertion order)

Every example starts from `lh = new LinkedHashMap<>()` then `lh.put("b", 2)`.

| Method | Example | Result |
|---|---|---|
| `putFirst(k, v)` | `lh.putFirst("a", 1)` | `{a=1, b=2}` |
| `putLast(k, v)` | `lh.putLast("c", 3)` | `{b=2, c=3}` |
| `firstEntry()` / `lastEntry()` | `lh.firstEntry()` | `b=2` |
| `pollFirstEntry()` / `pollLastEntry()` | `lh.pollFirstEntry()` | returns `b=2` and removes it |
| `reversed()` | with `{a=1, b=2, c=3}` | `{c=3, b=2, a=1}` |

## 6. The two patterns to know by heart

```java
Map<Character, Integer> count = new HashMap<>();
for (char c : "banana".toCharArray()) {
    count.merge(c, 1, Integer::sum);
}
```
Result: `{a=3, b=1, n=2}`

```java
Map<Integer, List<String>> byLength = new HashMap<>();
for (String word : List.of("hi", "yo", "hey")) {
    byLength.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
}
```
Result: `{2=[hi, yo], 3=[hey]}`

## 7. Going through a map

```java
for (Map.Entry<String, Integer> e : ages.entrySet()) {
    System.out.println(e.getKey() + " " + e.getValue());
}

for (String name : ages.keySet()) {
}

for (int age : ages.values()) {
}
```

## 8. In interviews

- "Have I already seen it?" in O(1): Two Sum.
- Count: anagrams, most frequent, first unique character.
- Group: group anagrams, group by category.
- Cache results (memoization).
- A nested loop that **searches**? A map often removes it: O(n²) becomes O(n).

## 9. How `HashMap` works

1. `key.hashCode()` gives a number.
2. That number picks a **bucket** in an inner array.
3. Several keys in the same bucket (a **collision**) are kept in a small list, turned into a
   tree above 8 entries.
4. `key.equals(...)` finds the right key in the bucket.
5. At 75% full (the **load factor**), the array doubles and every key is placed again.

So a key **must** have consistent `equals` and `hashCode`. `String`, `Integer` and records
already do.

## 10. Traps

| Trap | Wrong | Right |
|---|---|---|
| Absent key | `int age = map.get("Bob");` throws `NullPointerException` | `map.getOrDefault("Bob", 0)` |
| Order | rely on `HashMap` order | `LinkedHashMap` or `TreeMap` |
| Modify `Map.of` | `Map.of("a", 1).put("b", 2)` throws | `new HashMap<>(Map.of(...))` |
| Remove while looping | `ConcurrentModificationException` | `map.entrySet().removeIf(...)` |
| Mutable key | change a key object after `put` | immutable keys |
| `containsValue` | thinking it is O(1) | it is O(n) |

## 11. Exercises

File: [`MapExercises.java`](../../../src/main/java/com/mastery/interview/foundations/MapExercises.java)

```bash
mvn -Dtest='MapExercisesTest$E01AgeOf' test
mvn -Dtest=MapExercisesTest test
```

| # | Exercise | Example |
|---|---|---|
| 01 | Age or -1 | `ageOf({Ada=36}, "Bob")` → `-1` |
| 02 | Add a person | `addPerson({}, "Bob", 30)` → `{Bob=30}` |
| 03 | Birthday | `birthday({Ada=36}, "Ada")` → `{Ada=37}` |
| 04 | Total age | `totalAge({Ada=36, Alan=41})` → `77` |
| 05 | Count characters | `charCount("banana")` → `{a=3, b=1, n=2}` |
| 06 | Count words | `wordCount("to be or not to be")` → `{to=2, be=2, or=1, not=1}` |
| 07 | Older than, sorted | `olderThan({Ada=36, Grace=85, Alan=41}, 40)` → `["Alan", "Grace"]` |
| 08 | Invert | `invert({fr=France})` → `{France=fr}` |
| 09 | Group by length | `groupByLength(["hi", "hey", "yo"])` → `{2=[hi, yo], 3=[hey]}` |
| 10 | First unique character | `firstUniqueChar("swiss")` → `'w'` |

---

⬅️ [Table of contents](../../README.md) · [README](../../../README.md)
