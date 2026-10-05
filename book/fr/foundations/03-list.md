# F03. List (liste)

🇬🇧 [English version](../../en/foundations/03-list.md)

## 1. C'est quoi

Une `List` est une collection **ordonnée** qui **peut grandir et rétrécir**. L'indice
commence à 0, les doublons sont autorisés.

`List` est une interface. Deux implémentations :

| Implémentation | À l'intérieur | Quand |
|---|---|---|
| `ArrayList` | Un tableau, remplacé par un plus grand quand il est plein | **Presque toujours** |
| `LinkedList` | Des nœuds reliés au précédent et au suivant | Rarement |

```java
List<String> names = new ArrayList<>();
```

Une liste ne contient que des objets : `List<Integer>`, jamais `List<int>`.

## 2. Créer une liste

| Code | Résultat | Modifiable ? |
|---|---|---|
| `new ArrayList<>()` | `[]` | oui |
| `new ArrayList<>(List.of("a", "b"))` | `[a, b]` | oui |
| `List.of("a", "b")` | `[a, b]` | **non**, tout plante |
| `List.copyOf(list)` | copie immuable | **non** |
| `Arrays.asList("a", "b")` | `[a, b]` | `set` oui, `add`/`remove` non |

## 3. Toutes les méthodes de `List`

Chaque exemple part de `list = new ArrayList<>(List.of("a", "b", "c"))`.

### Ajouter

| Méthode | Exemple | Résultat |
|---|---|---|
| `add(x)` | `list.add("d")` | `[a, b, c, d]`, renvoie `true` |
| `add(i, x)` | `list.add(1, "x")` | `[a, x, b, c]` |
| `addFirst(x)` | `list.addFirst("z")` | `[z, a, b, c]` |
| `addLast(x)` | `list.addLast("d")` | `[a, b, c, d]` |
| `addAll(c)` | `list.addAll(List.of("d", "e"))` | `[a, b, c, d, e]` |
| `addAll(i, c)` | `list.addAll(0, List.of("z"))` | `[z, a, b, c]` |

### Lire

| Méthode | Exemple | Résultat |
|---|---|---|
| `get(i)` | `list.get(0)` | `"a"` |
| `getFirst()` | `list.getFirst()` | `"a"` |
| `getLast()` | `list.getLast()` | `"c"` |
| `size()` | `list.size()` | `3` |
| `isEmpty()` | `list.isEmpty()` | `false` |

### Chercher

| Méthode | Exemple | Résultat |
|---|---|---|
| `contains(x)` | `list.contains("b")` | `true` |
| `containsAll(c)` | `list.containsAll(List.of("a", "c"))` | `true` |
| `indexOf(x)` | `list.indexOf("b")` | `1` |
| `indexOf(x)` absent | `list.indexOf("z")` | `-1` |
| `lastIndexOf(x)` | `List.of("a", "b", "a").lastIndexOf("a")` | `2` |

### Modifier

| Méthode | Exemple | Résultat |
|---|---|---|
| `set(i, x)` | `list.set(1, "x")` | `[a, x, c]`, renvoie `"b"` |
| `replaceAll(f)` | `list.replaceAll(String::toUpperCase)` | `[A, B, C]` |
| `sort(comparator)` | `list.sort(Comparator.reverseOrder())` | `[c, b, a]` |
| `sort(null)` | `list.sort(null)` | ordre naturel |

### Supprimer

| Méthode | Exemple | Résultat |
|---|---|---|
| `remove(int i)` | `list.remove(0)` | `[b, c]`, renvoie `"a"` |
| `remove(Object x)` | `list.remove("b")` | `[a, c]`, renvoie `true` |
| `removeFirst()` | `list.removeFirst()` | `[b, c]`, renvoie `"a"` |
| `removeLast()` | `list.removeLast()` | `[a, b]`, renvoie `"c"` |
| `removeIf(test)` | `list.removeIf(s -> s.equals("b"))` | `[a, c]` |
| `removeAll(c)` | `list.removeAll(List.of("a", "b"))` | `[c]` |
| `retainAll(c)` | `list.retainAll(List.of("a", "z"))` | `[a]` |
| `clear()` | `list.clear()` | `[]` |

### Vues et copies

| Méthode | Exemple | Résultat |
|---|---|---|
| `subList(a, b)` | `list.subList(0, 2)` | `[a, b]` (b exclu) |
| `reversed()` | `list.reversed()` | `[c, b, a]` |
| `toArray()` | `list.toArray()` | `Object[]` `[a, b, c]` |
| `toArray(array)` | `list.toArray(new String[0])` | `String[]` `[a, b, c]` |
| `toArray(generator)` | `list.toArray(String[]::new)` | `String[]` `[a, b, c]` |
| `List.of(...)` | `List.of(1, 2)` | `[1, 2]` immuable |
| `List.copyOf(c)` | `List.copyOf(list)` | `[a, b, c]` immuable |

### Parcourir

| Méthode | Exemple | Résultat |
|---|---|---|
| `forEach(f)` | `list.forEach(System.out::println)` | affiche `a`, `b`, `c` |
| `iterator()` | `list.iterator().next()` | `"a"` |
| `listIterator()` | `list.listIterator().next()` | `"a"` |
| `listIterator(i)` | `list.listIterator(3).previous()` | `"c"` |
| `stream()` | `list.stream().map(String::toUpperCase).toList()` | `[A, B, C]` |
| `parallelStream()` | `list.parallelStream().count()` | `3` |
| `spliterator()` | `list.spliterator().estimateSize()` | `3` (utilisé par les streams, rare) |

### Comparer

| Méthode | Exemple | Résultat |
|---|---|---|
| `equals(o)` | `list.equals(List.of("a", "b", "c"))` | `true` |
| `hashCode()` | `list.hashCode()` | `126145` |

### Seulement dans `ArrayList`

| Méthode | Exemple | Résultat |
|---|---|---|
| `ensureCapacity(n)` | `arrayList.ensureCapacity(1000)` | place pour 1000 sans agrandir |
| `trimToSize()` | `arrayList.trimToSize()` | tableau interne réduit à `size()` |
| `clone()` | `arrayList.clone()` | copie superficielle |

### `Iterator` et `ListIterator`

| Méthode | Ce qu'elle fait |
|---|---|
| `hasNext()` / `next()` | Y a-t-il un suivant / le récupérer |
| `remove()` | Supprime l'élément qu'on vient de lire (sans danger pendant une boucle) |
| `hasPrevious()` / `previous()` | `ListIterator` seulement : revenir en arrière |
| `nextIndex()` / `previousIndex()` | `ListIterator` seulement : position actuelle |
| `set(x)` / `add(x)` | `ListIterator` seulement : remplacer / insérer à la position actuelle |

### Outils utiles de `Collections`

| Méthode | Exemple | Résultat |
|---|---|---|
| `Collections.sort(list)` | avec `[c, a, b]` | `[a, b, c]` |
| `Collections.reverse(list)` | avec `[a, b, c]` | `[c, b, a]` |
| `Collections.max(list)` / `min` | avec `[3, 9, 2]` | `9` / `2` |
| `Collections.frequency(list, x)` | `frequency([a, b, a], "a")` | `2` |
| `Collections.swap(list, i, j)` | `swap([a, b, c], 0, 2)` | `[c, b, a]` |
| `Collections.nCopies(n, x)` | `nCopies(3, "x")` | `[x, x, x]` |
| `Collections.shuffle(list)` | avec `[a, b, c]` | ordre aléatoire |
| `Collections.unmodifiableList(list)` | | vue en lecture seule |
| `Collections.emptyList()` | | `[]` immuable |

## 4. Coût : `ArrayList` contre `LinkedList`

| Opération | `ArrayList` | `LinkedList` |
|---|---|---|
| `get(i)`, `set(i, x)` | **O(1)** | O(n) |
| `add(x)` à la fin | O(1) amorti | O(1) |
| `addFirst`, `removeFirst` | O(n) | **O(1)** |
| `add(i, x)`, `remove(i)` | O(n) | O(n) |
| `contains`, `indexOf` | O(n) | O(n) |

O(1) amorti : quand il est plein, le tableau interne est remplacé par un 1,5 fois plus
grand (copie en O(n)), mais c'est si rare que la moyenne reste O(1).

## 5. Parcourir une liste

```java
for (String name : names) {
    System.out.println(name);
}

for (int i = 0; i < names.size(); i++) {
    System.out.println(i + " " + names.get(i));
}

names.removeIf(name -> name.startsWith("A"));
```

## 6. En entretien

- Collecter un résultat de taille inconnue : « renvoyer tous les... ».
- `List<List<Integer>>` : tous les sous-ensembles, tous les chemins, les niveaux d'un arbre.
- Graphes : `List<List<Integer>> graph` (liste d'adjacence).
- « ArrayList ou LinkedList ? » : ArrayList, `get(i)` est en O(1) et la mémoire est contiguë.

## 7. Pièges

| Piège | Faux | Juste |
|---|---|---|
| Supprimer une valeur d'une `List<Integer>` | `list.remove(1)` supprime l'**indice** 1 | `list.remove(Integer.valueOf(1))` |
| Supprimer dans un for-each | `ConcurrentModificationException` | `removeIf` ou `iterator.remove()` |
| Modifier un `List.of` | `List.of(1, 2).add(3)` plante | `new ArrayList<>(List.of(1, 2))` |
| `add` sur `Arrays.asList` | plante | `new ArrayList<>(Arrays.asList(...))` |
| Comparer des `Integer` | `a == b` faux au-dessus de 127 | `a.equals(b)` |
| Boucle `get(i)` sur une `LinkedList` | O(n²) | for-each |
| Renvoyer une nouvelle liste | modifier l'entrée | copier d'abord |

## 8. Exercices

Fichier : [`ListExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ListExercises.java)

```bash
mvn -Dtest='ListExercisesTest$E01LastElement' test
mvn -Dtest=ListExercisesTest test
```

| # | Exercice | Exemple |
|---|---|---|
| 01 | Dernier élément | `lastElement([4, 8, 15])` → `15` |
| 02 | Somme | `sum([1, 2, 3])` → `6` |
| 03 | Pairs (nouvelle liste) | `evens([1, 2, 3, 4])` → `[2, 4]` |
| 04 | Ajouter au début | `addFirst(["b", "c"], "a")` → `["a", "b", "c"]` |
| 05 | Supprimer toutes les occurrences | `removeValue([1, 2, 1], 1)` → `[2]` |
| 06 | Compter les mots longs | `countLongWords(["hi", "hello", "hey"], 3)` → `1` |
| 07 | Inversée (nouvelle liste) | `reversed([1, 2, 3])` → `[3, 2, 1]` |
| 08 | Sans doublons | `withoutDuplicates(["a", "b", "a"])` → `["a", "b"]` |
| 09 | Fusionner | `merge([1, 2], [3])` → `[1, 2, 3]` |
| 10 | Copie triée | `sortedCopy(["c", "a", "b"])` → `["a", "b", "c"]` |

---

⬅️ [Sommaire](../../README.fr.md) · [README](../../../README.fr.md)
