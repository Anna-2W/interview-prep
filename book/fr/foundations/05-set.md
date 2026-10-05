# F05. Set (ensemble)

🇬🇧 [English version](../../en/foundations/05-set.md)

## 1. C'est quoi

Un `Set` est une collection **sans doublons**. Ajouter un élément déjà présent ne fait rien.
Il n'y a pas d'indice.

```
 add("a"), add("b"), add("a")   ->   {a, b}
```

- `HashSet` : ajouter, supprimer, `contains` en **O(1)** en moyenne.
- Un `HashSet` est une `HashMap` dont on n'utilise que les clés.

| Implémentation | Ordre | Temps |
|---|---|---|
| `HashSet` | aucun ordre | O(1) |
| `LinkedHashSet` | ordre d'insertion | O(1) |
| `TreeSet` | trié | O(log n) |

## 2. Créer un set

| Code | Résultat | Modifiable ? |
|---|---|---|
| `new HashSet<>()` | `[]` | oui |
| `new HashSet<>(List.of(3, 1, 3, 2))` | `[1, 2, 3]` (doublons supprimés) | oui |
| `Set.of("a", "b")` | `[a, b]` | **non** |
| `Set.copyOf(list)` | copie immuable sans doublons | **non** |
| `new TreeSet<>(list)` | trié, sans doublons | oui |
| `HashSet.newHashSet(100)` | `[]`, place pour 100 éléments | oui |

## 3. Toutes les méthodes de `Set`

Chaque exemple part de `set = new HashSet<>(Set.of("a", "b", "c"))`.

| Méthode | Exemple | Résultat |
|---|---|---|
| `add(x)` nouveau | `set.add("d")` | `[a, b, c, d]`, renvoie `true` |
| `add(x)` déjà présent | `set.add("a")` | rien ne change, renvoie `false` |
| `remove(x)` | `set.remove("a")` | `[b, c]`, renvoie `true` |
| `contains(x)` | `set.contains("b")` | `true` |
| `size()` | `set.size()` | `3` |
| `isEmpty()` | `set.isEmpty()` | `false` |
| `clear()` | `set.clear()` | `[]` |
| `addAll(c)` | `set.addAll(Set.of("c", "d"))` | `[a, b, c, d]` |
| `removeAll(c)` | `set.removeAll(Set.of("a"))` | `[b, c]` |
| `retainAll(c)` | `set.retainAll(Set.of("b", "c", "z"))` | `[b, c]` |
| `containsAll(c)` | `set.containsAll(Set.of("a", "b"))` | `true` |
| `removeIf(test)` | `set.removeIf(s -> s.equals("b"))` | `[a, c]` |
| `iterator()` | `set.iterator().next()` | un élément, ordre non garanti |
| `forEach(f)` | `set.forEach(System.out::println)` | affiche chaque élément |
| `stream()` | `set.stream().map(String::toUpperCase).toList()` | `[A, B, C]` (ordre quelconque) |
| `toArray(generator)` | `set.toArray(String[]::new)` | `String[]` avec les 3 éléments |
| `equals(o)` | `Set.of(1, 2).equals(Set.of(2, 1))` | `true` |
| `hashCode()` | `Set.of("a").hashCode()` | `97` |
| `Set.of(...)` | `Set.of("a", "b")` | immuable, refuse les doublons |
| `Set.copyOf(c)` | `Set.copyOf(List.of(1, 1, 2))` | `[1, 2]` immuable |
| `spliterator()` | `set.spliterator().estimateSize()` | `3` (utilisé par les streams, rare) |
| `clone()` (`HashSet`) | `hashSet.clone()` | copie superficielle |

### Les 4 opérations d'ensemble

| Opération | Code | `a = {1, 2, 3}`, `b = {2, 3, 4}` |
|---|---|---|
| Union (a ou b) | `a.addAll(b)` | `{1, 2, 3, 4}` |
| Intersection (a et b) | `a.retainAll(b)` | `{2, 3}` |
| Différence (a sans b) | `a.removeAll(b)` | `{1}` |
| Inclusion (tout b dans a ?) | `a.containsAll(b)` | `false` |

Ces méthodes **modifient** `a`. Pour garder `a`, copier d'abord : `Set<Integer> r = new HashSet<>(a);`.

## 4. Seulement dans `TreeSet` (trié)

Chaque exemple part de `t = new TreeSet<>(Set.of(10, 20, 30))`.

| Méthode | Exemple | Résultat |
|---|---|---|
| `first()` / `last()` | `t.first()` | `10` / `30` |
| `getFirst()` / `getLast()` | `t.getFirst()` | `10` / `30` |
| `floor(x)` | `t.floor(25)` | `20` (plus grand `<= 25`) |
| `ceiling(x)` | `t.ceiling(25)` | `30` (plus petit `>= 25`) |
| `lower(x)` | `t.lower(20)` | `10` (plus grand `< 20`) |
| `higher(x)` | `t.higher(20)` | `30` (plus petit `> 20`) |
| `headSet(x)` | `t.headSet(20)` | `[10]` (x exclu) |
| `headSet(x, true)` | `t.headSet(20, true)` | `[10, 20]` |
| `tailSet(x)` | `t.tailSet(20)` | `[20, 30]` (x inclus) |
| `subSet(a, b)` | `t.subSet(10, 30)` | `[10, 20]` |
| `pollFirst()` / `pollLast()` | `t.pollFirst()` | renvoie `10` et le supprime |
| `removeFirst()` / `removeLast()` | `t.removeFirst()` | renvoie `10` et le supprime |
| `descendingSet()` | `t.descendingSet()` | `[30, 20, 10]` |
| `reversed()` | `t.reversed()` | `[30, 20, 10]` |
| `descendingIterator()` | `t.descendingIterator().next()` | `30` |
| `comparator()` | `t.comparator()` | `null` (ordre naturel) |
| `addFirst(x)` / `addLast(x)` | `t.addFirst(5)` | lève `UnsupportedOperationException` |

## 5. Seulement dans `LinkedHashSet` (ordre d'insertion)

Chaque exemple part de `l = new LinkedHashSet<>(List.of("b", "c"))`.

| Méthode | Exemple | Résultat |
|---|---|---|
| `addFirst(x)` | `l.addFirst("a")` | `[a, b, c]` |
| `addFirst(x)` déjà présent | `l.addFirst("c")` | `[c, b]` (déplacé au début) |
| `addLast(x)` | `l.addLast("z")` | `[b, c, z]` |
| `getFirst()` / `getLast()` | `l.getFirst()` | `"b"` / `"c"` |
| `removeFirst()` / `removeLast()` | `l.removeFirst()` | renvoie `"b"`, `[c]` |
| `reversed()` | `l.reversed()` | `[c, b]` |
| `LinkedHashSet.newLinkedHashSet(n)` | `LinkedHashSet.newLinkedHashSet(100)` | `[]`, place pour 100 |

## 6. Parcourir un set

```java
for (String s : set) {
    System.out.println(s);
}
```

Pas de `get(i)` : un set n'a pas d'indice.

## 7. En entretien

- « Y a-t-il un doublon ? » : tout ajouter et comparer les tailles, ou s'arrêter quand `add`
  renvoie `false`.
- « Est-ce que je l'ai déjà visité ? » : BFS et DFS gardent un `Set<Node> visited`.
- Supprimer les doublons d'une liste en gardant l'ordre : `new ArrayList<>(new LinkedHashSet<>(list))`.
- Éléments communs à deux listes : intersection.
- `contains` sur une `List` coûte O(n), sur un `HashSet` O(1) : transformer une liste en set
  fait souvent passer de O(n²) à O(n).

## 8. Pièges

| Piège | Faux | Juste |
|---|---|---|
| Ordre | compter sur l'ordre d'un `HashSet` | `LinkedHashSet` ou `TreeSet` |
| Accès par indice | `set.get(0)` n'existe pas | boucle, ou `TreeSet.first()` |
| Doublons dans `Set.of` | `Set.of("a", "a")` lève `IllegalArgumentException` | `new HashSet<>(List.of("a", "a"))` |
| Garder l'original | `a.retainAll(b)` modifie `a` | `r = new HashSet<>(a); r.retainAll(b);` |
| Ignorer le retour de `add` | tester `contains` puis `add` | `if (!set.add(x))` veut dire x était déjà là |
| Ses propres objets | sans `equals`/`hashCode`, les doublons restent | implémenter les deux, ou utiliser un record |
| `TreeSet` de ses propres objets | `ClassCastException` | implémenter `Comparable` ou donner un `Comparator` |

## 9. Exercices

Fichier : [`SetExercises.java`](../../../src/main/java/com/mastery/interview/foundations/SetExercises.java)

```bash
mvn -Dtest='SetExercisesTest$E01HasDuplicate' test
mvn -Dtest=SetExercisesTest test
```

| # | Exercice | Exemple |
|---|---|---|
| 01 | Contient un doublon | `hasDuplicate([1, 2, 3, 1])` → `true` |
| 02 | Compter les valeurs différentes | `countDistinct([1, 2, 2, 3])` → `3` |
| 03 | Éléments communs | `common({1, 2, 3}, {2, 3, 4})` → `{2, 3}` |
| 04 | Tous les éléments | `union({1, 2}, {2, 3})` → `{1, 2, 3}` |
| 05 | Seulement dans le premier | `onlyInFirst({1, 2, 3}, {2})` → `{1, 3}` |
| 06 | Compter les lettres différentes | `countUniqueChars("hello")` → `4` |
| 07 | Premier caractère répété | `firstRepeated("abcb")` → `'b'` |
| 08 | Est inclus | `isSubset({1, 2}, {1, 2, 3})` → `true` |
| 09 | Trié sans doublons | `sortedUnique([3, 1, 3, 2])` → `[1, 2, 3]` |
| 10 | Pangramme (les 26 lettres) | `isPangram("the quick brown fox jumps over the lazy dog")` → `true` |

---

⬅️ [Sommaire](../../README.fr.md) · [README](../../../README.fr.md)
