# F03. List (liste)

🇬🇧 [English version](../../en/foundations/03-list.md)

## 1. C'est quoi

Une `List` est une collection **ordonnée** qui **peut grandir et rétrécir**. Comme un
tableau, chaque élément a un indice qui commence à 0, et les doublons sont autorisés.
Contrairement à un tableau, on peut ajouter et supprimer des éléments.

`List` est une **interface** (un contrat). On choisit une implémentation :

| Implémentation | À l'intérieur | Quand l'utiliser |
|---|---|---|
| `ArrayList` | Un tableau remplacé par un plus grand quand il est plein | **Presque toujours.** Le choix par défaut |
| `LinkedList` | Des nœuds reliés au précédent et au suivant | Rarement. Pour les files, préférer `ArrayDeque` |

```java
List<String> names = new ArrayList<>();   // on déclare avec l'interface, on crée avec la classe
```

Une liste ne contient que des **objets** : `List<Integer>`, pas `List<int>`. Java convertit
`int` en `Integer` tout seul (autoboxing).

## 2. Créer une liste

```java
List<String> a = new ArrayList<>();                   // vide, modifiable
List<String> b = new ArrayList<>(List.of("x", "y"));  // copie avec des valeurs, modifiable
List<String> c = List.of("x", "y");                   // IMMUABLE : add/remove/set plantent
List<String> d = Arrays.asList("x", "y");             // taille fixe : set marche, add/remove plantent
```

## 3. Les méthodes à connaître

| Méthode | Ce qu'elle fait | `ArrayList` | `LinkedList` |
|---|---|---|---|
| `add(x)` | Ajoute à la fin | O(1) amorti | O(1) |
| `add(i, x)` | Insère à l'indice `i` (décale le reste) | O(n) | O(n) |
| `get(i)` | Élément à l'indice `i` | **O(1)** | O(n) |
| `set(i, x)` | Remplace l'élément en `i` | O(1) | O(n) |
| `remove(int i)` | Supprime à l'**indice** `i` | O(n) | O(n) |
| `remove(Object x)` | Supprime la première **valeur** `x` | O(n) | O(n) |
| `size()` | Nombre d'éléments | O(1) | O(1) |
| `isEmpty()` | Taille 0 | O(1) | O(1) |
| `contains(x)` | `x` est-il dans la liste | O(n) | O(n) |
| `indexOf(x)` | Premier indice de `x`, ou `-1` | O(n) | O(n) |
| `clear()` | Vide tout | O(n) | O(n) |
| `addAll(other)` | Ajoute tous les éléments d'une autre collection | O(m) | O(m) |
| `subList(a, b)` | Vue de `a` à `b` (**b exclu**) | O(1) | O(1) |

### Outils pratiques

| Code | Ce qu'il fait |
|---|---|
| `Collections.sort(list)` ou `list.sort(null)` | Tri croissant, sur place, O(n log n) |
| `list.sort(Comparator.reverseOrder())` | Tri décroissant |
| `Collections.reverse(list)` | Inverse sur place |
| `Collections.max(list)` / `min` | Plus grand / plus petit |
| `list.removeIf(x -> x < 0)` | Supprime tous les éléments qui vérifient une condition |
| `String.join(", ", list)` | Colle une `List<String>` en une seule chaîne |
| `new ArrayList<>(list)` | Copie |

**O(1) amorti** pour `ArrayList.add` : la plupart des ajouts sont immédiats. Quand le
tableau interne est plein, Java en crée un environ 1,5 fois plus grand et recopie tout
(O(n)). C'est assez rare pour que la moyenne reste O(1).

## 4. Parcourir une liste

```java
List<String> names = new ArrayList<>(List.of("Ada", "Alan", "Grace"));

for (String name : names) {                // lecture seule
    System.out.println(name);
}

for (int i = 0; i < names.size(); i++) {   // besoin de l'indice
    System.out.println(i + " " + names.get(i));
}

names.removeIf(name -> name.startsWith("A"));   // supprimer en filtrant : removeIf
```

## 5. À quoi ça sert en entretien

- Pour **collecter un résultat** dont on ne connaît pas la taille à l'avance : « renvoyer tous les... ».
- `List<List<Integer>>` pour des résultats comme « tous les sous-ensembles », « tous les
  chemins », « regrouper par niveau ».
- Les listes d'adjacence des graphes : `List<List<Integer>> graph`.
- Question « ArrayList ou LinkedList ? » : ArrayList, car `get(i)` est en O(1) et la
  mémoire est contiguë (bien pour le cache). LinkedList ne gagne que pour insérer à une
  position qu'on tient déjà avec un itérateur.

## 6. Pièges

1. **`remove(int)` contre `remove(Object)`.** Sur une `List<Integer>`, `list.remove(1)`
   supprime l'élément à l'**indice 1**, pas la valeur 1. Pour la valeur :
   `list.remove(Integer.valueOf(1))`.
2. **`ConcurrentModificationException`.** Supprimer dans une boucle for-each plante.
   Utiliser `removeIf`, ou un `Iterator` avec `it.remove()`.
3. **`List.of` est immuable.** `List.of(1, 2).add(3)` lève
   `UnsupportedOperationException`. L'emballer : `new ArrayList<>(List.of(1, 2))`.
4. **`Arrays.asList` a une taille fixe.** `add` et `remove` plantent, `set` marche et
   modifie aussi le tableau d'origine.
5. **`==` sur des `Integer`.** Deux objets `Integer` au-dessus de 127 ne sont pas `==`.
   Utiliser `equals`.
   ```java
   Integer a = 1000, b = 1000;
   a == b;        // false
   a.equals(b);   // true
   ```
6. **`get(i)` sur une `LinkedList` dans une boucle** coûte O(n) à chaque fois : la boucle
   devient O(n²).
7. **Modifier la liste d'entrée.** Si on demande de renvoyer une nouvelle liste, copier d'abord.

## 7. Exercices

On code dans [`ListExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ListExercises.java).

```bash
mvn -Dtest='ListExercisesTest$E01LastElement' test   # un exercice
mvn -Dtest=ListExercisesTest test                    # tout le chapitre
```

| # | Exercice | Ce que ça entraîne |
|---|---|---|
| 01 | `lastElement([4, 8, 15])` → `15` | `get`, `size() - 1` |
| 02 | `sum([1, 2, 3])` → `6` | for-each sur une liste |
| 03 | `evens([1, 2, 3, 4])` → `[2, 4]` | Construire une nouvelle liste avec `add` |
| 04 | `addFirst(["b", "c"], "a")` → `["a", "b", "c"]` | `add(indice, x)` |
| 05 | `removeValue([1, 2, 1], 1)` → `[2]` | `remove(Object)` contre `remove(int)`, `removeIf` |
| 06 | `countLongWords(["hi", "hello"], 3)` → `1` | Boucle avec une condition |
| 07 | `reversed([1, 2, 3])` → `[3, 2, 1]` | Copier, puis inverser |
| 08 | `withoutDuplicates(["a", "b", "a"])` → `["a", "b"]` | `contains`, garder l'ordre |
| 09 | `merge([1, 2], [3])` → `[1, 2, 3]` | `addAll` |
| 10 | `sortedCopy(["c", "a", "b"])` → `["a", "b", "c"]` | Trier une copie, entrée intacte |
