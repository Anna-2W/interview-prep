# F04. Map (dictionnaire)

🇬🇧 [English version](../../en/foundations/04-map.md)

## 1. C'est quoi

Une `Map` stocke des paires **clé → valeur**. On trouve une valeur **par sa clé**.

```
 "Ada"   -> 36
 "Alan"  -> 41
 "Grace" -> 85
```

- Les clés sont **uniques** : remettre une clé existante remplace sa valeur.
- Les valeurs peuvent se répéter.
- `HashMap` : chercher, ajouter, supprimer par clé en **O(1)** en moyenne.

| Implémentation | Ordre des clés | Temps |
|---|---|---|
| `HashMap` | aucun ordre | O(1) |
| `LinkedHashMap` | ordre d'insertion | O(1) |
| `TreeMap` | trié | O(log n) |

## 2. Créer une map

| Code | Résultat | Modifiable ? |
|---|---|---|
| `new HashMap<>()` | `{}` | oui |
| `new HashMap<>(Map.of("Ada", 36))` | `{Ada=36}` | oui |
| `Map.of("Ada", 36, "Alan", 41)` | `{Ada=36, Alan=41}` | **non** |
| `Map.ofEntries(Map.entry("Ada", 36))` | `{Ada=36}` | **non** |
| `Map.copyOf(map)` | copie immuable | **non** |
| `new TreeMap<>(map)` | mêmes paires, clés triées | oui |

## 3. Toutes les méthodes de `Map`

Chaque exemple part de `map = new HashMap<>(Map.of("Ada", 36, "Alan", 41))`.
Une `HashMap` peut afficher ses clés dans n'importe quel ordre.

### Lire

| Méthode | Exemple | Résultat |
|---|---|---|
| `get(k)` | `map.get("Ada")` | `36` |
| `get(k)` absente | `map.get("Bob")` | `null` |
| `getOrDefault(k, d)` | `map.getOrDefault("Bob", 0)` | `0` |
| `containsKey(k)` | `map.containsKey("Ada")` | `true` |
| `containsValue(v)` | `map.containsValue(41)` | `true` (O(n)) |
| `size()` | `map.size()` | `2` |
| `isEmpty()` | `map.isEmpty()` | `false` |

### Ajouter et modifier

| Méthode | Exemple | Résultat |
|---|---|---|
| `put(k, v)` nouvelle clé | `map.put("Bob", 30)` | ajoute `Bob=30`, renvoie `null` |
| `put(k, v)` clé existante | `map.put("Ada", 40)` | `Ada=40`, renvoie `36` (ancienne valeur) |
| `putAll(m)` | `map.putAll(Map.of("Bob", 30))` | ajoute `Bob=30` |
| `putIfAbsent(k, v)` | `map.putIfAbsent("Ada", 99)` | rien ne change, renvoie `36` |
| `replace(k, v)` | `map.replace("Ada", 40)` | `Ada=40`, renvoie `36` |
| `replace(k, v)` absente | `map.replace("Bob", 1)` | rien ne change, renvoie `null` |
| `replace(k, old, new)` | `map.replace("Ada", 36, 40)` | `Ada=40`, renvoie `true` |
| `replaceAll(f)` | `map.replaceAll((k, v) -> v + 1)` | `{Ada=37, Alan=42}` |

### Calculer (les plus utiles)

| Méthode | Exemple | Résultat |
|---|---|---|
| `merge(k, v, f)` présente | `map.merge("Ada", 1, Integer::sum)` | `Ada=37` |
| `merge(k, v, f)` absente | `map.merge("Bob", 1, Integer::sum)` | ajoute `Bob=1` |
| `compute(k, f)` | `map.compute("Ada", (k, v) -> v + 1)` | `Ada=37` |
| `compute(k, f)` absente | `map.compute("Bob", (k, v) -> v == null ? 1 : v + 1)` | ajoute `Bob=1` |
| `computeIfAbsent(k, f)` absente | `map.computeIfAbsent("Bob", k -> 0)` | ajoute `Bob=0`, renvoie `0` |
| `computeIfAbsent(k, f)` présente | `map.computeIfAbsent("Ada", k -> 0)` | rien ne change, renvoie `36` |
| `computeIfPresent(k, f)` | `map.computeIfPresent("Ada", (k, v) -> v + 1)` | `Ada=37` |
| `computeIfPresent(k, f)` absente | `map.computeIfPresent("Bob", (k, v) -> v + 1)` | rien ne change, renvoie `null` |

Si la fonction renvoie `null`, `compute`, `computeIfPresent` et `merge` **suppriment** la clé.

### Supprimer

| Méthode | Exemple | Résultat |
|---|---|---|
| `remove(k)` | `map.remove("Ada")` | `{Alan=41}`, renvoie `36` |
| `remove(k, v)` | `map.remove("Ada", 99)` | rien ne change, renvoie `false` |
| `clear()` | `map.clear()` | `{}` |

### Vues

| Méthode | Exemple | Résultat |
|---|---|---|
| `keySet()` | `map.keySet()` | `[Ada, Alan]` |
| `values()` | `map.values()` | `[36, 41]` |
| `entrySet()` | `map.entrySet()` | `[Ada=36, Alan=41]` |
| `forEach(f)` | `map.forEach((k, v) -> System.out.println(k + " " + v))` | affiche `Ada 36`, `Alan 41` |

### Comparer et construire

| Méthode | Exemple | Résultat |
|---|---|---|
| `equals(o)` | `map.equals(Map.of("Alan", 41, "Ada", 36))` | `true` (l'ordre ne compte pas) |
| `hashCode()` | `map.hashCode()` | un nombre, deux maps égales donnent le même |
| `Map.entry(k, v)` | `Map.entry("Ada", 36)` | `Ada=36` |
| `Map.of(...)` | `Map.of("a", 1)` | `{a=1}` immuable, jusqu'à 10 paires |
| `Map.ofEntries(...)` | `Map.ofEntries(Map.entry("a", 1), Map.entry("b", 2))` | `{a=1, b=2}` immuable |
| `Map.copyOf(m)` | `Map.copyOf(map)` | copie immuable |

### `Map.Entry` (une paire)

| Méthode | Exemple avec `e = Map.entry("Ada", 36)` | Résultat |
|---|---|---|
| `getKey()` | `e.getKey()` | `"Ada"` |
| `getValue()` | `e.getValue()` | `36` |
| `setValue(v)` | dans `for (var e : map.entrySet()) e.setValue(0);` | modifie la map |
| `Map.Entry.comparingByKey()` | `entries.sort(Map.Entry.comparingByKey())` | trie les paires par clé |
| `Map.Entry.comparingByValue()` | `entries.sort(Map.Entry.comparingByValue())` | trie les paires par valeur |

## 4. Seulement dans `TreeMap` (clés triées)

Chaque exemple part de `t = new TreeMap<>(Map.of(10, "a", 20, "b", 30, "c"))`.

| Méthode | Exemple | Résultat |
|---|---|---|
| `firstKey()` / `lastKey()` | `t.firstKey()` | `10` / `30` |
| `firstEntry()` / `lastEntry()` | `t.firstEntry()` | `10=a` / `30=c` |
| `floorKey(k)` | `t.floorKey(25)` | `20` (plus grande `<= 25`) |
| `ceilingKey(k)` | `t.ceilingKey(25)` | `30` (plus petite `>= 25`) |
| `lowerKey(k)` | `t.lowerKey(20)` | `10` (plus grande `< 20`) |
| `higherKey(k)` | `t.higherKey(20)` | `30` (plus petite `> 20`) |
| `floorEntry`, `ceilingEntry`, `lowerEntry`, `higherEntry` | `t.floorEntry(25)` | `20=b` |
| `headMap(k)` | `t.headMap(20)` | `{10=a}` (k exclue) |
| `headMap(k, true)` | `t.headMap(20, true)` | `{10=a, 20=b}` |
| `tailMap(k)` | `t.tailMap(20)` | `{20=b, 30=c}` (k incluse) |
| `subMap(a, b)` | `t.subMap(10, 30)` | `{10=a, 20=b}` |
| `pollFirstEntry()` | `t.pollFirstEntry()` | renvoie `10=a` et le supprime |
| `pollLastEntry()` | `t.pollLastEntry()` | renvoie `30=c` et le supprime |
| `descendingMap()` | `t.descendingMap()` | `{30=c, 20=b, 10=a}` |
| `descendingKeySet()` | `t.descendingKeySet()` | `[30, 20, 10]` |
| `navigableKeySet()` | `t.navigableKeySet()` | `[10, 20, 30]` |
| `comparator()` | `t.comparator()` | `null` (ordre naturel) |

## 5. Seulement dans `LinkedHashMap` (ordre d'insertion)

Chaque exemple part de `lh = new LinkedHashMap<>()` puis `lh.put("b", 2)`.

| Méthode | Exemple | Résultat |
|---|---|---|
| `putFirst(k, v)` | `lh.putFirst("a", 1)` | `{a=1, b=2}` |
| `putLast(k, v)` | `lh.putLast("c", 3)` | `{b=2, c=3}` |
| `firstEntry()` / `lastEntry()` | `lh.firstEntry()` | `b=2` |
| `pollFirstEntry()` / `pollLastEntry()` | `lh.pollFirstEntry()` | renvoie `b=2` et le supprime |
| `reversed()` | avec `{a=1, b=2, c=3}` | `{c=3, b=2, a=1}` |

## 6. Les deux patterns à connaître par cœur

```java
Map<Character, Integer> count = new HashMap<>();
for (char c : "banana".toCharArray()) {
    count.merge(c, 1, Integer::sum);
}
```
Résultat : `{a=3, b=1, n=2}`

```java
Map<Integer, List<String>> byLength = new HashMap<>();
for (String word : List.of("hi", "yo", "hey")) {
    byLength.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
}
```
Résultat : `{2=[hi, yo], 3=[hey]}`

## 7. Parcourir une map

```java
for (Map.Entry<String, Integer> e : ages.entrySet()) {
    System.out.println(e.getKey() + " " + e.getValue());
}

for (String name : ages.keySet()) {
}

for (int age : ages.values()) {
}
```

## 8. En entretien

- « Est-ce que je l'ai déjà vu ? » en O(1) : Two Sum.
- Compter : anagrammes, élément le plus fréquent, premier caractère unique.
- Regrouper : grouper les anagrammes, regrouper par catégorie.
- Mettre en cache des résultats (mémoïsation).
- Une boucle imbriquée qui **cherche** ? Une map la supprime souvent : O(n²) devient O(n).

## 9. Comment marche `HashMap`

1. `key.hashCode()` donne un nombre.
2. Ce nombre choisit un **bucket** (une case) dans un tableau interne.
3. Plusieurs clés dans le même bucket (une **collision**) sont gardées dans une petite
   liste, transformée en arbre au-delà de 8 éléments.
4. `key.equals(...)` trouve la bonne clé dans le bucket.
5. À 75 % de remplissage (le **load factor**), le tableau double et chaque clé est replacée.

Donc une clé **doit** avoir `equals` et `hashCode` cohérents. `String`, `Integer` et les
records le font déjà.

## 10. Pièges

| Piège | Faux | Juste |
|---|---|---|
| Clé absente | `int age = map.get("Bob");` lève `NullPointerException` | `map.getOrDefault("Bob", 0)` |
| Ordre | compter sur l'ordre d'une `HashMap` | `LinkedHashMap` ou `TreeMap` |
| Modifier un `Map.of` | `Map.of("a", 1).put("b", 2)` plante | `new HashMap<>(Map.of(...))` |
| Supprimer pendant un parcours | `ConcurrentModificationException` | `map.entrySet().removeIf(...)` |
| Clé modifiable | modifier l'objet clé après le `put` | des clés immuables |
| `containsValue` | croire que c'est O(1) | c'est O(n) |

## 11. Exercices

Fichier : [`MapExercises.java`](../../../src/main/java/com/mastery/interview/foundations/MapExercises.java)

```bash
mvn -Dtest='MapExercisesTest$E01AgeOf' test
mvn -Dtest=MapExercisesTest test
```

| # | Exercice | Exemple |
|---|---|---|
| 01 | Âge ou -1 | `ageOf({Ada=36}, "Bob")` → `-1` |
| 02 | Ajouter une personne | `addPerson({}, "Bob", 30)` → `{Bob=30}` |
| 03 | Anniversaire | `birthday({Ada=36}, "Ada")` → `{Ada=37}` |
| 04 | Somme des âges | `totalAge({Ada=36, Alan=41})` → `77` |
| 05 | Compter les caractères | `charCount("banana")` → `{a=3, b=1, n=2}` |
| 06 | Compter les mots | `wordCount("to be or not to be")` → `{to=2, be=2, or=1, not=1}` |
| 07 | Plus âgés que, triés | `olderThan({Ada=36, Grace=85, Alan=41}, 40)` → `["Alan", "Grace"]` |
| 08 | Inverser | `invert({fr=France})` → `{France=fr}` |
| 09 | Regrouper par longueur | `groupByLength(["hi", "hey", "yo"])` → `{2=[hi, yo], 3=[hey]}` |
| 10 | Premier caractère unique | `firstUniqueChar("swiss")` → `'w'` |
