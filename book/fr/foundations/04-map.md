# F04. Map (dictionnaire)

🇬🇧 [English version](../../en/foundations/04-map.md)

## 1. C'est quoi

Une `Map` stocke des paires **clé → valeur**, comme un dictionnaire : un mot (la clé) donne
sa définition (la valeur). On trouve une valeur **par sa clé**, pas par un indice.

```
 Map<String, Integer> ages
   "Ada"   -> 36
   "Alan"  -> 41
   "Grace" -> 85
```

- **Les clés sont uniques.** Mettre une clé qui existe déjà remplace l'ancienne valeur.
- **Les valeurs peuvent se répéter.** Deux personnes peuvent avoir le même âge.
- Avec une `HashMap`, chercher, ajouter ou supprimer par clé coûte **O(1) en moyenne**.
  C'est LA structure la plus utile en entretien de code.

`Map` est une interface. Trois implémentations à connaître :

| Implémentation | Ordre des clés | Temps | Quand l'utiliser |
|---|---|---|---|
| `HashMap` | Aucun ordre garanti | O(1) en moyenne | **Choix par défaut** |
| `LinkedHashMap` | Ordre d'insertion | O(1) en moyenne | Besoin de « le premier qui... » |
| `TreeMap` | Clés triées | O(log n) | Besoin des clés dans l'ordre, ou de la plus petite / plus grande |

## 2. Créer une map

```java
Map<String, Integer> a = new HashMap<>();                 // vide, modifiable
Map<String, Integer> b = Map.of("Ada", 36, "Alan", 41);   // IMMUABLE
Map<String, Integer> c = new HashMap<>(b);                // copie modifiable
```

## 3. Les méthodes à connaître

| Méthode | Ce qu'elle fait | Exemple | Temps (`HashMap`) |
|---|---|---|---|
| `put(k, v)` | Ajoute ou remplace | `put("Ada", 36)` | O(1) |
| `get(k)` | Valeur de `k`, ou **`null`** si absente | `get("Ada")` → `36` | O(1) |
| `getOrDefault(k, d)` | Valeur de `k`, ou `d` si absente | `getOrDefault("Bob", 0)` → `0` | O(1) |
| `containsKey(k)` | La clé est-elle présente | `containsKey("Ada")` → `true` | O(1) |
| `containsValue(v)` | La valeur est-elle présente | `containsValue(36)` → `true` | **O(n)** |
| `remove(k)` | Supprime la paire | `remove("Ada")` | O(1) |
| `size()` / `isEmpty()` | Nombre de paires | | O(1) |
| `putIfAbsent(k, v)` | Ajoute seulement si la clé est absente | | O(1) |
| `merge(k, v, f)` | Absente : met `v`. Présente : combine avec `f` | `merge(c, 1, Integer::sum)` | O(1) |
| `computeIfAbsent(k, f)` | Absente : crée la valeur avec `f`, puis la renvoie | voir plus bas | O(1) |
| `keySet()` | Toutes les clés (un `Set`) | | vue O(1) |
| `values()` | Toutes les valeurs (une `Collection`) | | vue O(1) |
| `entrySet()` | Toutes les paires (`Map.Entry`) | | vue O(1) |

### Les bonus de `TreeMap`

| Méthode | Ce qu'elle fait |
|---|---|
| `firstKey()` / `lastKey()` | Plus petite / plus grande clé |
| `floorKey(k)` | Plus grande clé `<= k` |
| `ceilingKey(k)` | Plus petite clé `>= k` |

## 4. Les deux patterns qu'on utilise tout le temps

**Compter** (map de fréquences) :

```java
Map<Character, Integer> count = new HashMap<>();
for (char c : "banana".toCharArray()) {
    count.merge(c, 1, Integer::sum);       // pareil que : count.put(c, count.getOrDefault(c, 0) + 1)
}
// {a=3, b=1, n=2}
```

**Regrouper** (map de listes) :

```java
Map<Integer, List<String>> byLength = new HashMap<>();
for (String word : List.of("hi", "yo", "hey")) {
    byLength.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
}
// {2=[hi, yo], 3=[hey]}
```

## 5. Parcourir une map

```java
Map<String, Integer> ages = Map.of("Ada", 36, "Alan", 41);

for (Map.Entry<String, Integer> e : ages.entrySet()) {   // clé ET valeur : le meilleur choix
    System.out.println(e.getKey() + " a " + e.getValue() + " ans");
}

for (String name : ages.keySet()) { }      // clés seulement
for (int age : ages.values()) { }          // valeurs seulement
```

## 6. À quoi ça sert en entretien

- **« Est-ce que je l'ai déjà vu ? »** en O(1) : Two Sum mémorise chaque nombre avec son indice.
- **Compter** : anagrammes, élément le plus fréquent, premier caractère unique.
- **Regrouper** : grouper les anagrammes, regrouper par catégorie.
- **Mettre en cache** les résultats d'une fonction (mémoïsation en DP).
- Dès que la force brute a une boucle imbriquée qui **cherche** quelque chose, se demander :
  une map peut-elle supprimer la boucle intérieure ? On passe de O(n²) à O(n).

## 7. Comment marche `HashMap` (question classique en entretien Java)

1. `key.hashCode()` donne un int.
2. Cet int choisit un **bucket** (une case) dans un tableau interne.
3. Plusieurs clés peuvent tomber dans le même bucket (une **collision**) : elles sont
   gardées dans une petite liste, transformée en arbre quand elle devient longue (8+ éléments).
4. `key.equals(...)` trouve la bonne clé dans le bucket.
5. Quand la map est pleine à 75 % (le **load factor**), le tableau double et toutes les clés
   sont redistribuées (rehash).

C'est pour ça qu'**une clé doit implémenter `equals` et `hashCode` de façon cohérente** :
deux objets égaux doivent avoir le même hash code. Les records et `String` le font déjà.

## 8. Pièges

1. **`get` renvoie `null` pour une clé absente.** `int age = map.get("Bob");` lève une
   `NullPointerException` (déballage de `null`). Utiliser `getOrDefault` ou tester `containsKey`.
2. **`HashMap` n'a pas d'ordre.** Ne jamais compter sur l'ordre d'affichage. Besoin d'un
   ordre ? `LinkedHashMap` (insertion) ou `TreeMap` (trié).
3. **`Map.of` est immuable** et refuse les clés en double et `null`.
4. **Modifier pendant un parcours** lève `ConcurrentModificationException`. Utiliser
   `map.entrySet().removeIf(...)`.
5. **Clés modifiables.** Si on modifie un objet après l'avoir utilisé comme clé, son hash
   change et la map ne le retrouve plus. Utiliser des clés immuables.
6. **`containsValue` est en O(n)**, pas O(1).

## 9. Exercices

On code dans [`MapExercises.java`](../../../src/main/java/com/mastery/interview/foundations/MapExercises.java).

```bash
mvn -Dtest='MapExercisesTest$E01AgeOf' test   # un exercice
mvn -Dtest=MapExercisesTest test              # tout le chapitre
```

| # | Exercice | Ce que ça entraîne |
|---|---|---|
| 01 | `ageOf({Ada=36}, "Ada")` → `36`, `"Bob"` → `-1` | `getOrDefault` |
| 02 | `addPerson(map, "Bob", 30)` | `put` |
| 03 | `birthday(map, "Ada")` → Ada a 37 ans | `get` puis `put`, clé absente |
| 04 | `totalAge({Ada=36, Alan=41})` → `77` | `values()` |
| 05 | `charCount("banana")` → `{a=3, b=1, n=2}` | `merge` (map de fréquences) |
| 06 | `wordCount("to be or not to be")` → `{to=2, be=2, ...}` | `split` + `merge` |
| 07 | `olderThan(map, 40)` → `["Alan", "Grace"]` | `entrySet()`, trier le résultat |
| 08 | `invert({fr=France})` → `{France=fr}` | Construire une nouvelle map |
| 09 | `groupByLength(["hi", "yo", "hey"])` → `{2=[hi, yo], 3=[hey]}` | `computeIfAbsent` |
| 10 | `firstUniqueChar("swiss")` → `'w'` | `LinkedHashMap` ou deux passages |
