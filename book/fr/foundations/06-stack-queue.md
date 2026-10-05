# F06. Stack et Queue (pile et file)

🇬🇧 [English version](../../en/foundations/06-stack-queue.md)

## 1. C'est quoi

| | Règle | Image | Dans la vraie vie |
|---|---|---|---|
| **Pile** (stack) | **LIFO** : dernier entré, premier sorti | `push 1, 2, 3` → `pop` donne `3` | une pile d'assiettes, le bouton « annuler » |
| **File** (queue) | **FIFO** : premier entré, premier sorti | `offer 1, 2, 3` → `poll` donne `1` | la queue à la boulangerie |
| **File de priorité** | le **plus petit** sort en premier | `offer 5, 1, 4` → `poll` donne `1` | les urgences à l'hôpital |

En Java, on utilise :

| Besoin | Classe | Déclaration |
|---|---|---|
| Pile | `ArrayDeque` | `Deque<Integer> stack = new ArrayDeque<>();` |
| File | `ArrayDeque` | `Queue<Integer> queue = new ArrayDeque<>();` |
| Les deux bouts | `ArrayDeque` | `Deque<Integer> deque = new ArrayDeque<>();` |
| File de priorité | `PriorityQueue` | `PriorityQueue<Integer> pq = new PriorityQueue<>();` |

**Deque** = « double-ended queue », file à deux bouts : on ajoute et on retire aux deux
extrémités, donc elle fait les deux métiers. L'ancienne classe `Stack` existe encore mais
elle est lente (synchronisée) : ne pas l'utiliser dans du nouveau code.

## 2. Créer

| Code | Résultat |
|---|---|
| `new ArrayDeque<>()` | vide |
| `new ArrayDeque<>(List.of(1, 2, 3))` | premier = `1`, dernier = `3` |
| `new PriorityQueue<>()` | vide, le plus petit d'abord |
| `new PriorityQueue<>(Comparator.reverseOrder())` | vide, le **plus grand** d'abord |
| `new PriorityQueue<>(List.of(5, 1, 4))` | le plus petit d'abord, `peek()` = `1` |
| `new PriorityQueue<>(Comparator.comparing(String::length))` | la chaîne la plus courte d'abord |

## 3. `ArrayDeque` comme pile

Chaque exemple part de `stack = new ArrayDeque<>(List.of(1, 2, 3))`. Le **sommet** est le premier élément.

| Méthode | Exemple | Résultat |
|---|---|---|
| `push(x)` | `stack.push(0)` | `[0, 1, 2, 3]`, sommet = `0` |
| `pop()` | `stack.pop()` | renvoie `1`, `[2, 3]` |
| `peek()` | `stack.peek()` | `1`, rien n'est retiré |
| `isEmpty()` | `stack.isEmpty()` | `false` |
| `size()` | `stack.size()` | `3` |

## 4. `ArrayDeque` comme file

Chaque exemple part de `queue = new ArrayDeque<>(List.of(1, 2, 3))`. On entre par la fin, on sort par le début.

| Méthode | Exemple | Résultat |
|---|---|---|
| `offer(x)` | `queue.offer(4)` | `[1, 2, 3, 4]` |
| `add(x)` | `queue.add(4)` | `[1, 2, 3, 4]` |
| `poll()` | `queue.poll()` | renvoie `1`, `[2, 3]` |
| `remove()` | `queue.remove()` | renvoie `1`, `[2, 3]` |
| `peek()` | `queue.peek()` | `1`, rien n'est retiré |
| `element()` | `queue.element()` | `1`, rien n'est retiré |

### Deux versions de chaque : exception ou valeur spéciale

| Action | Lève une exception si impossible | Renvoie `null` (ou `false`) |
|---|---|---|
| Ajouter à la fin | `add(x)` | `offer(x)` |
| Retirer le premier | `remove()` | `poll()` |
| Regarder le premier | `element()` | `peek()` |

Sur une file vide : `poll()` → `null`, `remove()` → `NoSuchElementException`.

## 5. Toutes les autres méthodes de `Deque` / `ArrayDeque`

Chaque exemple part de `d = new ArrayDeque<>(List.of(1, 2, 3))`.

| Méthode | Exemple | Résultat |
|---|---|---|
| `addFirst(x)` | `d.addFirst(0)` | `[0, 1, 2, 3]` |
| `addLast(x)` | `d.addLast(4)` | `[1, 2, 3, 4]` |
| `offerFirst(x)` | `d.offerFirst(0)` | `[0, 1, 2, 3]`, renvoie `true` |
| `offerLast(x)` | `d.offerLast(4)` | `[1, 2, 3, 4]`, renvoie `true` |
| `removeFirst()` | `d.removeFirst()` | renvoie `1` (plante si vide) |
| `removeLast()` | `d.removeLast()` | renvoie `3` (plante si vide) |
| `pollFirst()` | `d.pollFirst()` | renvoie `1` (`null` si vide) |
| `pollLast()` | `d.pollLast()` | renvoie `3` (`null` si vide) |
| `getFirst()` | `d.getFirst()` | `1` (plante si vide) |
| `getLast()` | `d.getLast()` | `3` (plante si vide) |
| `peekFirst()` | `d.peekFirst()` | `1` (`null` si vide) |
| `peekLast()` | `d.peekLast()` | `3` (`null` si vide) |
| `removeFirstOccurrence(x)` | avec `[1, 2, 1, 3, 1]` : `removeFirstOccurrence(1)` | `[2, 1, 3, 1]` |
| `removeLastOccurrence(x)` | avec `[2, 1, 3, 1]` : `removeLastOccurrence(1)` | `[2, 1, 3]` |
| `remove(x)` | `d.remove(2)` | `[1, 3]`, renvoie `true` |
| `contains(x)` | `d.contains(2)` | `true` |
| `addAll(c)` | `d.addAll(List.of(4, 5))` | `[1, 2, 3, 4, 5]` |
| `removeIf(test)` | `d.removeIf(x -> x % 2 == 1)` | `[2]` |
| `removeAll(c)` / `retainAll(c)` | `d.retainAll(List.of(1, 2))` | `[1, 2]` |
| `clear()` | `d.clear()` | `[]` |
| `iterator()` | `d.iterator().next()` | `1` (du début à la fin) |
| `descendingIterator()` | `d.descendingIterator().next()` | `3` (de la fin au début) |
| `reversed()` | `d.reversed()` | `[3, 2, 1]` |
| `forEach(f)` | `d.forEach(System.out::println)` | affiche `1`, `2`, `3` |
| `stream()` | `d.stream().mapToInt(x -> x).sum()` | `6` |
| `toArray()` | `d.toArray()` | `[1, 2, 3]` |
| `clone()` | `d.clone()` | copie |
| `spliterator()` | `d.spliterator().estimateSize()` | `3` (utilisé par les streams, rare) |

## 6. Toutes les méthodes de `PriorityQueue`

Chaque exemple part de `pq = new PriorityQueue<>(List.of(5, 1, 4, 2))`.

| Méthode | Exemple | Résultat | Temps |
|---|---|---|---|
| `offer(x)` / `add(x)` | `pq.offer(0)` | `0` devient la tête | O(log n) |
| `peek()` | `pq.peek()` | `1` | O(1) |
| `poll()` | `pq.poll()` | renvoie `1` et le retire | O(log n) |
| `element()` / `remove()` | `pq.remove()` | comme `peek` / `poll`, mais plantent si vide | |
| `remove(x)` | `pq.remove(4)` | retire un `4`, renvoie `true` | O(n) |
| `contains(x)` | `pq.contains(5)` | `true` | O(n) |
| `size()` / `isEmpty()` | `pq.size()` | `4` | O(1) |
| `clear()` | `pq.clear()` | vide | |
| `comparator()` | `pq.comparator()` | `null` (ordre naturel) | |
| `addAll(c)` | `pq.addAll(List.of(3, 0))` | ajoute les deux | |
| `removeIf`, `removeAll`, `retainAll` | `pq.removeIf(x -> x > 3)` | retire `5` et `4` | |
| `iterator()`, `forEach`, `toArray`, `stream` | `pq.toArray()` | les éléments, **pas triés** | |
| `spliterator()` | | rare | |

**Tas max** (le plus grand d'abord) : `new PriorityQueue<>(Comparator.reverseOrder())`.

## 7. L'ancienne classe `Stack` (à connaître, pas à utiliser)

| Méthode | Exemple avec `push(1)`, `push(2)`, `push(3)` | Résultat |
|---|---|---|
| `push(x)` | | `[1, 2, 3]`, sommet = `3` |
| `pop()` | `st.pop()` | `3` |
| `peek()` | `st.peek()` | `3` |
| `empty()` | `st.empty()` | `false` |
| `search(x)` | `st.search(1)` | `3` (distance depuis le sommet, en partant de 1) |

`pop()` sur une `Stack` vide lève `EmptyStackException`.

## 8. Coût

| Opération | `ArrayDeque` | `PriorityQueue` |
|---|---|---|
| Ajouter | O(1) aux deux bouts | O(log n) |
| Retirer la tête | O(1) aux deux bouts | O(log n) |
| Regarder la tête | O(1) | O(1) |
| `contains`, `remove(x)` | O(n) | O(n) |

## 9. En entretien

**Pile** : tout ce qui doit être apparié ou défait dans l'ordre inverse.
- Parenthèses valides `([]{})`.
- Évaluer une expression (notation polonaise inverse).
- Annuler, bouton « retour » du navigateur, retour arrière dans un texte.
- DFS itératif.

**File** : traiter les choses dans leur ordre d'arrivée.
- BFS (plus court chemin dans une grille, parcours d'un arbre niveau par niveau).
- Ordonnancement de tâches, tampons.

**File de priorité** : obtenir vite le plus petit ou le plus grand.
- Top K / K-ième plus grand : un tas min de taille K.
- Fusionner K listes triées, Dijkstra.

## 10. Pièges

| Piège | Faux | Juste |
|---|---|---|
| Choisir la classe | `new Stack<>()` | `new ArrayDeque<>()` |
| Pile vide | `stack.pop()` lève `NoSuchElementException` | tester `isEmpty()` d'abord, ou `poll()` |
| Mélanger pile et file | `push` puis `poll` sur la même deque | pile : `push`/`pop`/`peek` ; file : `offer`/`poll`/`peek` |
| `null` | `deque.add(null)` lève `NullPointerException` | `ArrayDeque` refuse `null` |
| Afficher une `PriorityQueue` | `[2, 5, 4]` n'est pas trié | `poll()` un par un pour les avoir dans l'ordre |
| Tas max | `new PriorityQueue<>()` donne le plus petit | `new PriorityQueue<>(Comparator.reverseOrder())` |
| Comparer des `Integer` venant de `peek()` | `a.peek() == b.peek()` | `a.peek().equals(b.peek())` |

## 11. Exercices

Fichier : [`StackQueueExercises.java`](../../../src/main/java/com/mastery/interview/foundations/StackQueueExercises.java)

```bash
mvn -Dtest='StackQueueExercisesTest$E01ReverseWithStack' test
mvn -Dtest=StackQueueExercisesTest test
```

| # | Exercice | Exemple | Outil |
|---|---|---|---|
| 01 | Inverser avec une pile | `reverseWithStack("abc")` → `"cba"` | pile |
| 02 | Parenthèses équilibrées | `isBalanced("(())")` → `true` | pile |
| 03 | Crochets valides | `isValid("([]{})")` → `true`, `isValid("([)]")` → `false` | pile |
| 04 | Supprimer les doublons voisins | `removeAdjacentDuplicates("abbaca")` → `"ca"` | pile |
| 05 | Retour arrière | `applyBackspaces("ab#c")` → `"ac"` | pile |
| 06 | Notation polonaise inverse | `evalRpn(["2", "1", "+", "3", "*"])` → `9` | pile |
| 07 | Rotation avec une file | `rotate([1, 2, 3, 4], 1)` → `[2, 3, 4, 1]` | file |
| 08 | Les K plus petits | `smallestK([5, 1, 4, 2], 2)` → `[1, 2]` | file de priorité |
| 09 | Trier avec un tas | `heapSorted([3, 1, 2])` → `[1, 2, 3]` | file de priorité |
| 10 | K-ième plus grand | `kthLargest([3, 2, 1, 5, 6, 4], 2)` → `5` | file de priorité |

---

⬅️ [Sommaire](../../README.fr.md) · [README](../../../README.fr.md)
