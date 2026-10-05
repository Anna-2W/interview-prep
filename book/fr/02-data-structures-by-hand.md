# 02. Structures de données codées à la main

🇬🇧 [English version](../en/02-data-structures-by-hand.md)

Dans F01 à F06, tu as **utilisé** `ArrayList`, `LinkedList`, `ArrayDeque`, `HashMap`. Ici,
tu les **construis**. En entretien on demande « comment ça marche à l'intérieur ? » et
« code-le » : après ce chapitre, tu sais répondre aux deux.

| Structure | Classe Java imitée | Tu codes |
|---|---|---|
| Tableau dynamique | `ArrayList` | `MyArrayList` |
| Liste simplement chaînée | `LinkedList` (qui est doublement chaînée) | `MyLinkedList` |
| Pile sur un tableau | `ArrayDeque` utilisée comme pile | `MyStack` |
| File sur une liste chaînée | `ArrayDeque` utilisée comme file | `MyQueue` |
| File circulaire (tampon circulaire) | `ArrayDeque` à l'intérieur | `MyCircularQueue` |
| Table de hachage par chaînage | `HashMap` | `MyHashMap` |

---

## 1. Tableau dynamique (`ArrayList`)

Un tableau a une taille fixe. Un tableau dynamique cache un vrai tableau et **le remplace
par un plus grand** quand il est plein.

```
 data     [ a | b | c | _ ]      capacité = 4, size = 3
 add(d)   [ a | b | c | d ]      size = 4
 add(e)   plein : nouveau tableau deux fois plus grand, copie, puis ajout
          [ a | b | c | d | e | _ | _ | _ ]      capacité = 8, size = 5
```

| Opération | Comment | Coût |
|---|---|---|
| `get(i)`, `set(i, x)` | `data[i]` | O(1) |
| `add(x)` à la fin | `data[size++] = x`, agrandir si plein | O(1) amorti |
| `add(i, x)` | décaler `i..size-1` d'une case vers la droite | O(n) |
| `remove(i)` | décaler `i+1..size-1` d'une case vers la gauche | O(n) |
| `contains(x)` | boucle | O(n) |

**Pourquoi doubler la taille ?** Si on agrandit de +1 à chaque fois, chaque ajout recopie
tout : O(n²) pour n ajouts. Si on double, les copies arrivent aux tailles 1, 2, 4, 8... Le
total copié vaut 1 + 2 + 4 + ... + n < 2n, donc chaque ajout coûte O(1) en moyenne.
L'`ArrayList` de Java agrandit de 1,5×, même idée.

| Piège | Faux | Juste |
|---|---|---|
| Indice valide | `i <= size` | `0 <= i < size` (et `<= size` seulement pour `add(i, x)`) |
| Comparer à la capacité | `i < data.length` | `i < size` |
| Sens du décalage à l'insertion | de gauche à droite (écrase) | de la fin vers `i` |
| Après un remove | l'ancienne dernière case garde l'objet | `data[size] = null` (laisse le GC le libérer) |
| Tableau générique | `new T[10]` ne compile pas | `(T[]) new Object[10]` |

---

## 2. Liste simplement chaînée

Chaque **nœud** contient une valeur et une référence vers le nœud **suivant**. La liste
garde `head` (premier nœud), souvent `tail` (dernier nœud) et `size`.

```
 head                         tail
  │                             │
  ▼                             ▼
 [ 3 | ●]──▶[ 7 | ●]──▶[ 1 | ●]──▶ null
```

```java
class Node<T> {
    T value;
    Node<T> next;
}
```

| Opération | Comment | Coût |
|---|---|---|
| `addFirst(x)` | nouveau nœud, `node.next = head`, `head = node` | O(1) |
| `addLast(x)` avec `tail` | `tail.next = node`, `tail = node` | O(1) |
| `removeFirst()` | `head = head.next` | O(1) |
| `removeLast()` | marcher jusqu'au nœud **avant** la queue | O(n) (simplement chaînée) |
| `get(i)` | avancer de `i` pas depuis `head` | O(n) |
| `contains(x)` | parcourir toute la liste | O(n) |

**Liste doublement chaînée** : chaque nœud a aussi `prev`. Alors `removeLast()` est en
O(1). La `LinkedList` de Java est doublement chaînée.

```
 null◀──[● | 3 | ●]◀──▶[● | 7 | ●]◀──▶[● | 1 | ●]──▶null
```

**Liste circulaire** : le dernier nœud pointe vers le premier au lieu de `null`. Sert pour
l'ordonnancement à tour de rôle (round-robin).

### Variantes rares (🟢, juste connaître le nom)

| Variante | Idée |
|---|---|
| Skip list | plusieurs niveaux de liens « express » au-dessus d'une liste triée : recherche en O(log n). Utilisée par les sorted sets de Redis |
| Unrolled linked list | chaque nœud contient un petit tableau de valeurs : moins de nœuds, meilleur usage du cache |
| Lock-free linked list | modifiée avec des compare-and-set atomiques au lieu de verrous, pour le code concurrent |

### Pièges

| Piège | Faux | Juste |
|---|---|---|
| Liste vide | `head.next` quand `head == null` | tester `head == null` d'abord |
| Perdre la liste | déplacer `head` pour parcourir | parcourir avec une variable `current` à part |
| Un seul élément | mettre à jour `head` seulement | quand la liste devient vide, `tail = null` aussi |
| Ordre des liens | `head = node; node.next = head;` (cycle) | `node.next = head; head = node;` |
| Oublier `size` | compter en parcourant à chaque fois | mettre à jour `size` à chaque ajout / suppression |

---

## 3. Pile sur un tableau

Une pile ne touche qu'**un seul bout** : le sommet. Un tableau avec un indice de sommet suffit.

```
 push(5), push(8), push(2)
 data [ 5 | 8 | 2 | _ ]    size = 3, sommet = data[size - 1] = 2
 pop() -> 2                size = 2
```

| Opération | Comment | Coût |
|---|---|---|
| `push(x)` | `data[size++] = x`, agrandir si plein | O(1) amorti |
| `pop()` | `return data[--size]` | O(1) |
| `peek()` | `return data[size - 1]` | O(1) |

Usages : annuler, parenthèses, évaluer des expressions, DFS itératif, et la **pile
d'appels** de ton propre programme.

---

## 4. File sur une liste chaînée

Une file ajoute à **l'arrière** et retire à **l'avant**. Avec une liste chaînée qui garde
`head` et `tail`, les deux sont en O(1).

```
 offer(5), offer(8), offer(2)
 head ─▶ [5] ─▶ [8] ─▶ [2] ◀─ tail
 poll() -> 5
 head ─▶ [8] ─▶ [2] ◀─ tail
```

| Opération | Comment | Coût |
|---|---|---|
| `offer(x)` | ajouter après `tail` | O(1) |
| `poll()` | retirer `head` | O(1) |
| `peek()` | `head.value` | O(1) |

Pourquoi pas une `ArrayList` avec `remove(0)` ? Parce qu'elle décale tout : O(n) par poll.

---

## 5. File circulaire (tampon circulaire)

Un tableau de taille fixe où `head` et `tail` **reviennent au début** grâce à `%`. Pas de
décalage, pas d'allocation : parfait pour les tampons (audio, réseau, logs).

```
 capacité 4
 offer 1, 2, 3       [ 1 | 2 | 3 | _ ]   head = 0, size = 3
 poll -> 1           [ _ | 2 | 3 | _ ]   head = 1, size = 2
 offer 4, 5          [ 5 | 2 | 3 | 4 ]   5 est allé à l'indice (1 + 3) % 4 = 0
```

| Calcul | Formule |
|---|---|
| indice de la prochaine case libre | `(head + size) % capacity` |
| après un poll | `head = (head + 1) % capacity` |
| plein | `size == capacity` |
| vide | `size == 0` |

Garder un compteur `size` est le moyen le plus simple de distinguer « plein » de « vide »
(avec seulement `head` et `tail`, les deux cas donnent `head == tail`).

---

## 6. Table de hachage (`HashMap`)

Un tableau de **buckets** (cases). Une clé va dans le bucket `indice = hash(clé) % capacité`.
Plusieurs clés dans le même bucket forment une petite liste chaînée : c'est le **chaînage**.

```
 capacité 4
 put("Ada", 36)   hash % 4 = 1
 put("Bob", 30)   hash % 4 = 3
 put("Eve", 25)   hash % 4 = 1   collision avec Ada

 buckets
 [0] null
 [1] (Ada,36) ─▶ (Eve,25) ─▶ null
 [2] null
 [3] (Bob,30) ─▶ null
```

| Étape | Code |
|---|---|
| indice du bucket | `Math.floorMod(key.hashCode(), buckets.length)` |
| `get(k)` | parcourir le bucket, renvoyer la valeur dont la clé `equals(k)` |
| `put(k, v)` | clé trouvée dans le bucket : remplacer la valeur ; sinon ajouter un nœud |
| `remove(k)` | détacher le nœud du bucket |
| load factor | `size / capacité` ; au-delà de **0,75**, doubler la capacité et **réinsérer chaque entrée** |

| Opération | En moyenne | Pire cas (toutes les clés dans un bucket) |
|---|---|---|
| `get`, `put`, `remove` | O(1) | O(n) |

La `HashMap` de Java transforme un bucket en arbre rouge-noir au-delà de 8 entrées, donc son
pire cas est O(log n).

**Pourquoi `floorMod` et pas `%` ?** `hashCode()` peut être négatif, et `-7 % 4` vaut `-3` :
un indice invalide. `Math.floorMod(-7, 4)` vaut `1`.

### Autre stratégie de collision : l'adressage ouvert

Pas de listes : si le bucket est pris, on essaie le suivant (`indice + 1`, `+ 2`...). C'est
le **sondage linéaire** (linear probing). Variantes de ta liste de sujets : **Robin Hood
hashing** (on prend la case d'une clé plus proche de sa place idéale), **cuckoo hashing**
(deux tables, une clé expulse l'autre).

### Cousins proches (🟠 / 🟢)

| Structure | Idée | Compromis |
|---|---|---|
| Filtre de Bloom | k fonctions de hachage mettent k bits à 1 ; réponse « peut-être présent » ou « sûrement absent » | très peu de mémoire, faux positifs possibles, pas de suppression |
| Count-Min Sketch | même idée avec des compteurs : fréquences approximatives | très peu de mémoire, surestime |

### Pièges

| Piège | Faux | Juste |
|---|---|---|
| Hash négatif | `key.hashCode() % n` | `Math.floorMod(key.hashCode(), n)` |
| Comparer les clés | `node.key == key` | `node.key.equals(key)` |
| `put` d'une clé existante | ajouter un deuxième nœud | la trouver et remplacer la valeur |
| Agrandir | recopier le tableau de buckets tel quel | réinsérer chaque entrée : l'indice dépend de la capacité |
| Oublier la taille | l'agrandissement n'arrive jamais | `size++` seulement pour une **nouvelle** clé |

---

## 7. Problèmes sur les listes chaînées (très fréquents en entretien)

| Problème | Idée | Coût |
|---|---|---|
| Longueur | parcourir et compter | O(n) |
| Inverser | trois pointeurs `prev`, `current`, `next` | O(n) en temps, O(1) en espace |
| Milieu | **lent** avance de 1, **rapide** de 2 : quand rapide arrive au bout, lent est au milieu | O(n) |
| Cycle | lent et rapide : s'ils se rencontrent, il y a un cycle (Floyd) | O(n), O(1) en espace |
| Fusionner deux listes triées | nœud factice, toujours prendre la plus petite tête | O(n + m) |
| Supprimer le n-ième depuis la fin | rapide prend n pas d'avance, puis les deux avancent ensemble | O(n), un seul passage |
| Supprimer les doublons (triée) | si `current.value == current.next.value`, sauter `next` | O(n) |
| Palindrome | trouver le milieu, inverser la deuxième moitié, comparer | O(n), O(1) en espace |

**L'astuce du nœud factice** : créer `ListNode dummy = new ListNode(0)` avant la tête. On
n'a plus de cas particulier « la tête change ». On renvoie `dummy.next`.

```java
ListNode prev = null;
ListNode current = head;
while (current != null) {
    ListNode next = current.next;
    current.next = prev;
    prev = current;
    current = next;
}
return prev;
```

C'est l'inversion en place. À connaître par cœur, elle revient partout.

---

## 8. Exercices

Package [`datastructures`](../../src/main/java/com/mastery/interview/datastructures/).
Les champs et les constructeurs sont donnés : tu écris les méthodes.

| # | Fichier | Méthodes à écrire | Lancer |
|---|---|---|---|
| 1 | `MyArrayList.java` | `add`, `add(i, x)`, `get`, `set`, `remove`, `size`, `isEmpty`, `contains`, `indexOf` | `mvn -Dtest=MyArrayListTest test` |
| 2 | `MyLinkedList.java` | `addFirst`, `addLast`, `removeFirst`, `removeLast`, `get`, `contains`, `size`, `isEmpty`, `reverse` | `mvn -Dtest=MyLinkedListTest test` |
| 3 | `MyStack.java` | `push`, `pop`, `peek`, `size`, `isEmpty` | `mvn -Dtest=MyStackTest test` |
| 4 | `MyQueue.java` | `offer`, `poll`, `peek`, `size`, `isEmpty` | `mvn -Dtest=MyQueueTest test` |
| 5 | `MyCircularQueue.java` | `offer`, `poll`, `peek`, `size`, `isEmpty`, `isFull` | `mvn -Dtest=MyCircularQueueTest test` |
| 6 | `MyHashMap.java` | `put`, `get`, `remove`, `containsKey`, `size`, `isEmpty` (+ agrandissement) | `mvn -Dtest=MyHashMapTest test` |
| 7 | `LinkedListProblems.java` | les 8 problèmes ci-dessous | `mvn -Dtest=LinkedListProblemsTest test` |

Les faire dans cet ordre : chacun réutilise les idées des précédents.

| # | Problème | Exemple |
|---|---|---|
| E01 | `length` | `1 → 2 → 3` donne `3` |
| E02 | `reverse` | `1 → 2 → 3` donne `3 → 2 → 1` |
| E03 | `middle` | `1 → 2 → 3 → 4 → 5` donne le nœud `3` ; `1 → 2 → 3 → 4` donne `3` |
| E04 | `hasCycle` | `1 → 2 → 3 → retour à 2` donne `true` |
| E05 | `mergeSorted` | `1 → 3` et `2 → 4` donnent `1 → 2 → 3 → 4` |
| E06 | `removeNthFromEnd` | `1 → 2 → 3 → 4`, n = 2 donne `1 → 2 → 4` |
| E07 | `removeDuplicates` | `1 → 1 → 2 → 3 → 3` donne `1 → 2 → 3` |
| E08 | `isPalindrome` | `1 → 2 → 2 → 1` donne `true` |

Certains tests font un million d'opérations avec une limite de 2 secondes : un `add` ou un
`poll` en O(n) là où on attend O(1) échouera.

---

⬅️ [Sommaire](../README.fr.md) · [README](../../README.fr.md)
