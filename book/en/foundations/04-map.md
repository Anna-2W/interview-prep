# F04. Map

🇫🇷 [Version française](../../fr/foundations/04-map.md)

## 1. What it is

A `Map` stores **key → value** pairs, like a dictionary: a word (key) gives its definition
(value). You find a value **by its key**, not by an index.

```
 Map<String, Integer> ages
   "Ada"   -> 36
   "Alan"  -> 41
   "Grace" -> 85
```

- **Keys are unique.** Putting a key that exists replaces its old value.
- **Values can repeat.** Two people can have the same age.
- With a `HashMap`, finding, adding or removing by key is **O(1) on average**. This is the
  single most useful structure in coding interviews.

`Map` is an interface. Three implementations to know:

| Implementation | Order of the keys | Time | Use it when |
|---|---|---|---|
| `HashMap` | No order guaranteed | O(1) average | **Default choice** |
| `LinkedHashMap` | Insertion order | O(1) average | You need "the first one that..." |
| `TreeMap` | Sorted keys | O(log n) | You need keys in order, or the smallest/biggest key |

## 2. Create a map

```java
Map<String, Integer> a = new HashMap<>();          // empty, can be modified
Map<String, Integer> b = Map.of("Ada", 36, "Alan", 41);   // IMMUTABLE
Map<String, Integer> c = new HashMap<>(b);         // modifiable copy
```

## 3. The methods you must know

| Method | What it does | Example | Time (`HashMap`) |
|---|---|---|---|
| `put(k, v)` | Add or replace | `put("Ada", 36)` | O(1) |
| `get(k)` | Value of `k`, or **`null`** if absent | `get("Ada")` → `36` | O(1) |
| `getOrDefault(k, d)` | Value of `k`, or `d` if absent | `getOrDefault("Bob", 0)` → `0` | O(1) |
| `containsKey(k)` | Is the key present | `containsKey("Ada")` → `true` | O(1) |
| `containsValue(v)` | Is the value present | `containsValue(36)` → `true` | **O(n)** |
| `remove(k)` | Remove the pair | `remove("Ada")` | O(1) |
| `size()` / `isEmpty()` | Number of pairs | | O(1) |
| `putIfAbsent(k, v)` | Put only if the key is absent | | O(1) |
| `merge(k, v, f)` | Absent: put `v`. Present: combine with `f` | `merge(c, 1, Integer::sum)` | O(1) |
| `computeIfAbsent(k, f)` | Absent: create the value with `f`, then return it | see below | O(1) |
| `keySet()` | All the keys (a `Set`) | | O(1) view |
| `values()` | All the values (a `Collection`) | | O(1) view |
| `entrySet()` | All the pairs (`Map.Entry`) | | O(1) view |

### `TreeMap` extras

| Method | What it does |
|---|---|
| `firstKey()` / `lastKey()` | Smallest / biggest key |
| `floorKey(k)` | Biggest key `<= k` |
| `ceilingKey(k)` | Smallest key `>= k` |

## 4. The two patterns you will use all the time

**Count things** (frequency map):

```java
Map<Character, Integer> count = new HashMap<>();
for (char c : "banana".toCharArray()) {
    count.merge(c, 1, Integer::sum);       // same as: count.put(c, count.getOrDefault(c, 0) + 1)
}
// {a=3, b=1, n=2}
```

**Group things** (map of lists):

```java
Map<Integer, List<String>> byLength = new HashMap<>();
for (String word : List.of("hi", "yo", "hey")) {
    byLength.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
}
// {2=[hi, yo], 3=[hey]}
```

## 5. Going through a map

```java
Map<String, Integer> ages = Map.of("Ada", 36, "Alan", 41);

for (Map.Entry<String, Integer> e : ages.entrySet()) {   // key AND value: best choice
    System.out.println(e.getKey() + " is " + e.getValue());
}

for (String name : ages.keySet()) { }      // keys only
for (int age : ages.values()) { }          // values only
```

## 6. When you use it in interviews

- **"Have I seen this before?"** in O(1): Two Sum stores each number with its index.
- **Counting**: anagrams, most frequent element, first unique character.
- **Grouping**: group anagrams, group by category.
- **Caching** results of a function (memoization in DP).
- Whenever your brute force has a nested loop that **searches** for something, ask: can a
  map remove the inner loop? That turns O(n²) into O(n).

## 7. How `HashMap` works (asked in Java interviews)

1. `key.hashCode()` gives an int.
2. That int picks a **bucket** in an inner array.
3. Several keys can land in the same bucket (a **collision**): they are kept in a small list,
   turned into a tree when it gets long (8+ entries).
4. `key.equals(...)` finds the right key inside the bucket.
5. When the map is 75% full (the **load factor**), the array doubles and all keys are spread
   again (rehash).

That is why **a key must implement `equals` and `hashCode` consistently**: two equal
objects must have the same hash code. Records and `String` already do it.

## 8. Traps

1. **`get` returns `null` for a missing key.** `int age = map.get("Bob");` throws a
   `NullPointerException` (unboxing `null`). Use `getOrDefault` or check `containsKey`.
2. **`HashMap` has no order.** Never rely on the printing order. Need order? `LinkedHashMap`
   (insertion) or `TreeMap` (sorted).
3. **`Map.of` is immutable** and refuses duplicate keys and `null`.
4. **Modifying while iterating** throws `ConcurrentModificationException`. Use
   `map.entrySet().removeIf(...)`.
5. **Mutable keys.** If you change an object after using it as a key, its hash changes and
   the map cannot find it anymore. Use immutable keys.
6. **`containsValue` is O(n)**, not O(1).

## 9. Exercises

Code in [`MapExercises.java`](../../../src/main/java/com/mastery/interview/foundations/MapExercises.java).

```bash
mvn -Dtest='MapExercisesTest$E01AgeOf' test   # one exercise
mvn -Dtest=MapExercisesTest test              # the whole chapter
```

| # | Exercise | What it trains |
|---|---|---|
| 01 | `ageOf({Ada=36}, "Ada")` → `36`, `"Bob"` → `-1` | `getOrDefault` |
| 02 | `addPerson(map, "Bob", 30)` | `put` |
| 03 | `birthday(map, "Ada")` → Ada is 37 | `get` then `put`, missing key |
| 04 | `totalAge({Ada=36, Alan=41})` → `77` | `values()` |
| 05 | `charCount("banana")` → `{a=3, b=1, n=2}` | `merge` (frequency map) |
| 06 | `wordCount("to be or not to be")` → `{to=2, be=2, ...}` | `split` + `merge` |
| 07 | `olderThan(map, 40)` → `["Alan", "Grace"]` | `entrySet()`, sort the result |
| 08 | `invert({fr=France})` → `{France=fr}` | Build a new map |
| 09 | `groupByLength(["hi", "yo", "hey"])` → `{2=[hi, yo], 3=[hey]}` | `computeIfAbsent` |
| 10 | `firstUniqueChar("swiss")` → `'w'` | `LinkedHashMap` or two passes |
