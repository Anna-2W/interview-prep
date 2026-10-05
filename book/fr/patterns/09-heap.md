# Pattern 9. Tas (top K, fusion de K listes, deux tas)

🇬🇧 [English version](../../en/patterns/09-heap.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Beaucoup de problèmes ont besoin, encore et encore, **du plus petit (ou du plus grand)
élément** d'un groupe qui change sans arrêt : « les k plus grands », « les k plus proches »,
« toujours traiter la tâche la moins chère ensuite », « fusionner k listes triées », « la
médiane jusqu'ici ».

Les deux forces brutes possibles sont lentes :

| Force brute | Coût |
|---|---|
| Tout trier, prendre les k premiers | O(n log n), et il faut garder les n éléments |
| Garder une liste, la parcourir pour trouver le plus petit à chaque fois | O(n) par question, O(n²) pour n questions |

## 2. L'idée clé

Un **tas** (`PriorityQueue` en Java) est une structure qui connaît toujours son plus petit
élément :

| Opération | Coût |
|---|---|
| `peek()` : regarder le plus petit | O(1) |
| `offer(x)` : ajouter | O(log n) |
| `poll()` : retirer le plus petit | O(log n) |

**À quoi ça ressemble à l'intérieur.** Un tas est un **tableau** lu comme un arbre. La règle :
chaque parent est `<=` à ses enfants (tas min). Donc le plus petit est toujours à l'indice 0,
la racine.

```
 tableau [1, 3, 8, 5]          vue en arbre
 indice   0  1  2  3                 1          indice 0
                                   /   \
 parent de i  = (i - 1) / 2       3     8       indices 1, 2
 enfants de i = 2i + 1, 2i + 2   /
                                5               indice 3
```

Le tableau n'est **pas trié** : seule la règle « parent <= enfants » est garantie.

- `offer(x)` : mettre `x` à la fin du tableau, puis le **faire remonter** : tant que `x` est
  plus petit que son parent, on les échange. Au plus un échange par niveau : O(log n).
- `poll()` : prendre la racine, mettre le **dernier** élément à la racine, puis le **faire
  descendre** : tant qu'il est plus grand que son plus petit enfant, on les échange. O(log n).

Le vrai tableau interne d'une `PriorityQueue<Integer>`, pas à pas :

| Opération | Ce qui se passe | Tableau après |
|---|---|---|
| `offer(5)` | premier élément | [5] |
| `offer(3)` | 3 < parent 5 : échange | [3, 5] |
| `offer(8)` | 8 > parent 3 : reste | [3, 5, 8] |
| `offer(1)` | à l'indice 3, parent 5 : échange ; puis parent 3 : échange | [1, 3, 8, 5] |
| `poll()` → 1 | le dernier (5) va à la racine, le plus petit enfant est 3 : échange | [3, 5, 8] |

## 3. Pas à pas sur un exemple

Les **3 plus grandes** valeurs de `[4, 1, 7, 3, 8, 5]`.

Idée : garder un tas **min** de taille 3. Sa racine est la **plus faible** des 3 meilleures
valeurs jusqu'ici. Quand une 4e valeur arrive, on retire la racine : la plus faible s'en va.

| x | Action | Vrai tableau | Vue triée | Racine (la plus faible gardée) |
|---|---|---|---|---|
| 4 | offer | [4] | [4] | 4 |
| 1 | offer | [1, 4] | [1, 4] | 1 |
| 7 | offer | [1, 4, 7] | [1, 4, 7] | 1 |
| 3 | offer, taille 4 > 3, poll 1 | [3, 4, 7] | [3, 4, 7] | 3 |
| 8 | offer, poll 3 | [4, 8, 7] | [4, 7, 8] | 4 |
| 5 | offer, poll 4 | [5, 8, 7] | [5, 7, 8] | 5 |

Réponse : **5, 7, 8**. Remarque : le vrai tableau `[5, 8, 7]` n'est pas trié. Afficher une
`PriorityQueue` ne montre **pas** les éléments dans l'ordre.

## 4. Le modèle de code, ligne par ligne

```java
static List<Integer> kLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    for (int x : nums) {
        heap.offer(x);
        if (heap.size() > k) {
            heap.poll();
        }
    }
    return new ArrayList<>(heap);
}
```

| Ligne | Pourquoi |
|---|---|
| `new PriorityQueue<>()` | un tas **min** : la racine est la plus petite des valeurs gardées |
| `heap.offer(x)` | chaque valeur a sa chance, O(log k) |
| `if (heap.size() > k)` | le tas ne garde jamais plus de k valeurs : mémoire O(k) |
| `heap.poll()` | retirer la plus petite : elle ne peut plus être dans le top k |
| `new ArrayList<>(heap)` | les k plus grandes, **dans aucun ordre particulier**. Faire des `poll()` un par un pour les avoir triées |

**Pourquoi un tas min pour trouver les plus grands ?** La question à chaque étape est « la
nouvelle valeur doit-elle remplacer la **plus faible** de mon top k actuel ? » La plus faible
est la plus petite, donc elle doit être à la racine, là où on peut la tester et la retirer en
O(log k). Un tas max mettrait la plus grande à la racine, c'est-à-dire celle qu'on veut
**garder** : il faudrait stocker les n valeurs, O(n log n) en temps et O(n) en mémoire.

**Coût** : O(n log k) en temps, O(k) en espace. Quand k est petit, c'est presque O(n).

## 5. Pourquoi c'est correct

**Invariant** : après avoir lu les `i` premières valeurs, le tas contient les `min(i, k)` plus
grandes d'entre elles.

Quand `x` arrive, les k + 1 candidates sont l'ancien top k plus `x`. La plus petite de ces
k + 1 ne peut pas être dans le nouveau top k, et `poll()` retire exactement celle-là. Donc
l'invariant reste vrai.

## 6. Les variantes

| Sous-pattern | Tas | Idée | Coût |
|---|---|---|---|
| Top K plus grands | tas min de taille k | poll quand la taille dépasse k | O(n log k) |
| Top K plus petits | tas max de taille k | pareil, inversé | O(n log k) |
| Fusion de K listes | tas min des k « têtes » | sortir la plus petite tête, pousser l'élément suivant de sa liste | O(N log k) pour N éléments |
| Deux tas | tas max (moitié basse) + tas min (moitié haute) | les deux sommets sont autour du milieu | O(log n) par ajout |

**Fusion de K listes, déroulée.** Fusionner `[2, 9]`, `[1, 5]`, `[3]`. Le tas contient un
élément par liste (le prochain pas encore utilisé), avec le numéro de sa liste.

| On sort | De la liste | On pousse le suivant de cette liste | Tas après (vue triée) | Sortie |
|---|---|---|---|---|
| départ | | | 1 (liste 1), 2 (liste 0), 3 (liste 2) | |
| 1 | 1 | 5 | 2 (liste 0), 3 (liste 2), 5 (liste 1) | 1 |
| 2 | 0 | 9 | 3 (liste 2), 5 (liste 1), 9 (liste 0) | 1, 2 |
| 3 | 2 | plus rien | 5 (liste 1), 9 (liste 0) | 1, 2, 3 |
| 5 | 1 | plus rien | 9 (liste 0) | 1, 2, 3, 5 |
| 9 | 0 | plus rien | vide | 1, 2, 3, 5, 9 |

Le tas ne contient jamais plus de k éléments, un par liste.

**Deux tas, déroulé.** On garde la moitié basse dans un tas **max** `low` et la moitié haute
dans un tas **min** `high`, avec `low` de la même taille que `high` ou d'un de plus. Le milieu
est au sommet des tas. Nombres `2, 8, 4, 6` :

| x | low (vue triée) | high (vue triée) | Milieu |
|---|---|---|---|
| 2 | [2] | [] | 2 |
| 8 | [2] | [8] | (2 + 8) / 2 = 5.0 |
| 4 | [2, 4] | [8] | 4 (sommet de low) |
| 6 | [2, 4] | [6, 8] | (4 + 6) / 2 = 5.0 |

**Écrire le comparateur** :

| Ordre | Code |
|---|---|
| le plus petit d'abord (par défaut) | `new PriorityQueue<>()` |
| le plus grand d'abord | `new PriorityQueue<>(Comparator.reverseOrder())` |
| selon un nombre calculé | `new PriorityQueue<>(Comparator.comparingInt(p -> score(p)))` |
| `int[]` selon sa première valeur | `new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]))` |

## 7. Un problème résolu du début à la fin

**Problème (la dernière pierre)** : à chaque tour, on prend les **deux pierres les plus
lourdes** et on les écrase. Même poids : les deux disparaissent. Poids différents : la plus
légère disparaît et la plus lourde garde la différence. Renvoyer le poids de la dernière
pierre, ou 0 s'il n'en reste aucune.
`lastStone([2, 7, 4, 1, 8, 1])` → `1`.

1. **Force brute** : trier la liste à chaque tour pour trouver les deux plus lourdes. O(n² log n).
2. **Quelle question se répète ?** « Quelles sont les deux plus grandes en ce moment ? »,
   alors que de nouvelles pierres reviennent. C'est un tas **max**.
3. **Algorithme** : mettre toutes les pierres dans un tas max. Tant qu'il en reste au moins
   2 : en sortir deux, remettre la différence si elle n'est pas 0.

| Tour | On écrase | On remet | Tas après (vue triée, la plus grande d'abord) |
|---|---|---|---|
| départ | | | 8, 7, 4, 2, 1, 1 |
| 1 | 8 et 7 | 1 | 4, 2, 1, 1, 1 |
| 2 | 4 et 2 | 2 | 2, 1, 1, 1 |
| 3 | 2 et 1 | 1 | 1, 1, 1 |
| 4 | 1 et 1 | rien (0) | 1 |

Il reste une pierre : **1**.

```java
static int lastStone(int[] stones) {
    PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int s : stones) {
        heap.offer(s);
    }
    while (heap.size() > 1) {
        int first = heap.poll();
        int second = heap.poll();
        if (first != second) {
            heap.offer(first - second);
        }
    }
    return heap.isEmpty() ? 0 : heap.peek();
}
```

4. **Coût** : O(n log n) en temps (au plus n tours, chacun en O(log n)), O(n) en espace.

## 8. Les bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| Afficher le tas pour vérifier l'ordre | `[5, 8, 7]` : ça a l'air faux mais c'est normal | seuls `peek()` / `poll()` donnent l'ordre |
| Parcourir le tas en for-each en attendant des valeurs triées | les valeurs arrivent dans l'ordre du tableau | `poll()` jusqu'à ce qu'il soit vide |
| Tas max pour « les k plus grands » | on garde les n valeurs, O(n log n), O(n) en mémoire | tas min de taille k |
| Comparateur `(a, b) -> b - a` | mauvais ordre avec de grands nombres ou des négatifs (débordement) | `Comparator.reverseOrder()` ou `Integer.compare(b, a)` |
| `int x = heap.poll()` sur un tas vide | `NullPointerException` (déballage de `null`) | tester `isEmpty()` d'abord |
| Modifier un objet après l'avoir mis dans le tas | l'ordre du tas est cassé | le retirer, le modifier, le remettre |
| `heap.remove(x)` dans une boucle | O(n) à chaque fois, la boucle devient O(n²) | s'organiser pour ne faire que des `poll()` sur la racine |

## 9. Comment le reconnaître

- « Les k plus grands / plus petits / plus fréquents / plus proches ».
- « Toujours prendre le moins cher / le plus tôt / le plus grand ensuite », alors que de
  nouveaux éléments arrivent.
- « Fusionner k listes / tableaux / fichiers triés ».
- « Médiane d'un flux », « valeur du milieu jusqu'ici ».
- On a envie de **trier encore et encore** dans une boucle : un tas supprime presque toujours ça.

## 10. S'entraîner

Exos : [`patterns/heap/HeapExercises.java`](../../../src/main/java/com/mastery/interview/patterns/heap/HeapExercises.java)

```bash
mvn -Dtest='HeapExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **tas min ou tas max ? qu'est-ce qu'il y a dans
le tas (une valeur, un indice, une paire) ? quel est le comparateur ? quelle est la taille
maximale du tas ?** Puis dérouler le contenu du tas sur l'exemple, comme le tableau de la
section 3, avec la vue triée.
