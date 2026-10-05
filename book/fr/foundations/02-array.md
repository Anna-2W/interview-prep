# F02. Array (tableau)

🇬🇧 [English version](../../en/foundations/02-array.md)

## 1. C'est quoi

Un tableau est une rangée de cases de **taille fixe**, toutes du **même type**. L'indice commence à **0**.

```
 int[] nums = {4, 8, 15, 16};
 indice:  0  1   2   3          nums.length = 4
```

- La taille ne change jamais. Besoin de grandir ? Utiliser une `List` (F03).
- Lire ou écrire par indice : **O(1)**.

## 2. Créer un tableau

| Code | Résultat |
|---|---|
| `int[] a = {4, 8, 15};` | `[4, 8, 15]` |
| `new int[3]` | `[0, 0, 0]` |
| `new boolean[2]` | `[false, false]` |
| `new String[2]` | `[null, null]` |
| `new int[2][3]` | 2 lignes de `[0, 0, 0]` |
| `new int[] {1, 2}` | `[1, 2]` (pour le passer directement à une méthode) |

## 3. Ce qu'un tableau a tout seul

| Code | Exemple avec `a = {4, 8, 15}` | Résultat |
|---|---|---|
| `a[i]` | `a[1]` | `8` |
| `a[i] = x` | `a[1] = 99` | `a = [4, 99, 15]` |
| `a.length` | `a.length` | `3` (sans parenthèses) |
| `a.clone()` | `int[] b = a.clone()` | `b = [4, 8, 15]`, une copie séparée |

## 4. Toutes les méthodes de `java.util.Arrays`

### Afficher

| Méthode | Exemple | Résultat |
|---|---|---|
| `toString(a)` | `Arrays.toString(new int[] {3, 1, 2})` | `"[3, 1, 2]"` |
| `deepToString(a)` | `Arrays.deepToString(new int[][] {{1, 2}, {3}})` | `"[[1, 2], [3]]"` |

### Trier

| Méthode | Exemple | Résultat |
|---|---|---|
| `sort(a)` | `Arrays.sort(a)` avec `a = {3, 1, 2}` | `a = [1, 2, 3]` |
| `sort(a, from, to)` | `Arrays.sort(a, 0, 2)` avec `a = {3, 1, 2}` | `a = [1, 3, 2]` |
| `sort(a, comparator)` | `Arrays.sort(b, Comparator.reverseOrder())` avec `Integer[] b = {3, 1, 2}` | `b = [3, 2, 1]` |
| `parallelSort(a)` | `Arrays.parallelSort(a)` avec `a = {3, 1, 2}` | `a = [1, 2, 3]` (utilise plusieurs cœurs) |

### Chercher

| Méthode | Exemple | Résultat |
|---|---|---|
| `binarySearch(a, x)` | `Arrays.binarySearch(new int[] {1, 2, 3}, 2)` | `1` |
| `binarySearch(a, x)` absent | `Arrays.binarySearch(new int[] {1, 2, 3}, 5)` | `-4` (négatif = absent) |
| `binarySearch(a, from, to, x)` | `Arrays.binarySearch(new int[] {1, 2, 3}, 0, 2, 2)` | `1` |

Le tableau **doit être trié** avant `binarySearch`.

### Remplir et construire

| Méthode | Exemple | Résultat |
|---|---|---|
| `fill(a, x)` | `Arrays.fill(a, 7)` avec `a = new int[3]` | `a = [7, 7, 7]` |
| `fill(a, from, to, x)` | `Arrays.fill(a, 1, 3, 0)` avec `a = {3, 1, 2}` | `a = [3, 0, 0]` |
| `setAll(a, f)` | `Arrays.setAll(a, i -> i * i)` avec `a = new int[4]` | `a = [0, 1, 4, 9]` |
| `parallelSetAll(a, f)` | `Arrays.parallelSetAll(a, i -> i * i)` | `a = [0, 1, 4, 9]` |
| `parallelPrefix(a, f)` | `Arrays.parallelPrefix(a, Integer::sum)` avec `a = {1, 2, 3, 4}` | `a = [1, 3, 6, 10]` |

### Copier

| Méthode | Exemple | Résultat |
|---|---|---|
| `copyOf(a, n)` | `Arrays.copyOf(new int[] {1, 2}, 3)` | `[1, 2, 0]` |
| `copyOf(a, n)` plus court | `Arrays.copyOf(new int[] {1, 2, 3}, 2)` | `[1, 2]` |
| `copyOfRange(a, from, to)` | `Arrays.copyOfRange(new int[] {1, 2, 3, 4}, 1, 3)` | `[2, 3]` (to exclu) |

### Comparer

| Méthode | Exemple | Résultat |
|---|---|---|
| `equals(a, b)` | `Arrays.equals(new int[] {1, 2}, new int[] {1, 2})` | `true` |
| `deepEquals(a, b)` | `Arrays.deepEquals(new int[][] {{1}, {2}}, new int[][] {{1}, {2}})` | `true` |
| `compare(a, b)` | `Arrays.compare(new int[] {1, 2}, new int[] {1, 3})` | `-1` (a avant b) |
| `compareUnsigned(a, b)` | `Arrays.compareUnsigned(new int[] {-1}, new int[] {1})` | `1` |
| `mismatch(a, b)` | `Arrays.mismatch(new int[] {1, 2, 3}, new int[] {1, 5, 3})` | `1` (premier indice différent) |
| `mismatch(a, b)` identiques | `Arrays.mismatch(new int[] {1, 2}, new int[] {1, 2})` | `-1` |
| `hashCode(a)` | `Arrays.hashCode(new int[] {1, 2})` | `994` |
| `deepHashCode(a)` | `Arrays.deepHashCode(new int[][] {{1, 2}})` | `1025` |

### Convertir

| Méthode | Exemple | Résultat |
|---|---|---|
| `asList(...)` | `Arrays.asList("a", "b")` | `[a, b]` (`List` de taille fixe) |
| `stream(a)` | `Arrays.stream(new int[] {1, 2, 3}).sum()` | `6` |
| `stream(a)` | `Arrays.stream(new int[] {1, 2, 3}).max().getAsInt()` | `3` |
| `spliterator(a)` | `Arrays.spliterator(new int[] {1, 2, 3}).estimateSize()` | `3` (utilisé par les streams, rare) |

### Pas dans `Arrays` mais utile : `System.arraycopy`

| Méthode | Exemple | Résultat |
|---|---|---|
| `System.arraycopy(src, i, dst, j, n)` | `System.arraycopy(new int[] {1, 2}, 0, dst, 1, 2)` avec `dst = new int[3]` | `dst = [0, 1, 2]` |

## 5. Parcourir un tableau

```java
for (int i = 0; i < nums.length; i++) {
    nums[i] = nums[i] * 2;
}

for (int n : nums) {
    System.out.println(n);
}

for (int i = nums.length - 1; i >= 0; i--) {
    System.out.println(nums[i]);
}
```

## 6. En entretien

- La moitié des exos de code prennent un `int[]`.
- Compter les lettres : `int[] count = new int[26]`, puis `count[c - 'a']++`.
- Les techniques qui démarrent ici : deux pointeurs, fenêtre glissante, sommes préfixes,
  recherche dichotomique.
- Les grilles `int[][]` et `char[][]` : labyrinthes, îles.

## 7. Pièges

| Piège | Faux | Juste |
|---|---|---|
| Dernier indice | `i <= nums.length` | `i < nums.length` |
| Comparer le contenu | `a == b` ou `a.equals(b)` | `Arrays.equals(a, b)` |
| Afficher | `System.out.println(a)` donne `[I@1b6d3586` | `Arrays.toString(a)` |
| Copier | `int[] b = a;` (même tableau) | `int[] b = a.clone();` |
| Max avec des négatifs | `int max = 0;` | `int max = nums[0];` |
| Moyenne | `sum / n` donne un int | `(double) sum / n` |
| Tableau vide | `nums[0]` plante | tester `nums.length == 0` d'abord |
| Grosses sommes | un `int` peut déborder | `long` |
| for-each pour écrire | `for (int n : a) n = 0;` ne change rien | `for (int i...) a[i] = 0;` |

## 8. Exercices

Fichier : [`ArrayExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ArrayExercises.java).
Écrire les boucles soi-même : pas de `Arrays.sort`, pas de streams.

```bash
mvn -Dtest='ArrayExercisesTest$E01Sum' test
mvn -Dtest=ArrayExercisesTest test
```

| # | Exercice | Exemple |
|---|---|---|
| 01 | Somme | `sum({1, 2, 3})` → `6` |
| 02 | Maximum | `max({3, 9, 2})` → `9` |
| 03 | Contient | `contains({1, 2, 3}, 2)` → `true` |
| 04 | Indice de | `indexOf({5, 7, 5}, 5)` → `0` |
| 05 | Compter les pairs | `countEven({1, 2, 4})` → `2` |
| 06 | Doublé (nouveau tableau) | `doubled({1, 2})` → `{2, 4}` |
| 07 | Moyenne | `average({1, 2})` → `1.5` |
| 08 | Inversé (nouveau tableau) | `reversed({1, 2, 3})` → `{3, 2, 1}` |
| 09 | Est trié | `isSorted({1, 2, 2, 5})` → `true` |
| 10 | Concaténer | `concat({1, 2}, {3})` → `{1, 2, 3}` |

---

⬅️ [Sommaire](../../README.fr.md) · [README](../../../README.fr.md)
