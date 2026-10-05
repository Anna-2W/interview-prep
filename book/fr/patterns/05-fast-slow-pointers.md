# Pattern 5. Pointeurs lent et rapide

🇬🇧 [English version](../../en/patterns/05-fast-slow-pointers.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Sur une **liste chaînée**, il n'y a ni indice ni `size()` : on ne voit que le nœud courant et
son `next`. Deux questions classiques deviennent alors difficiles :

- « Où est le **milieu** ? »
- « La liste a-t-elle un **cycle** ? » (un `next` qui revient vers un nœud précédent, si bien
  que le parcours n'arrive jamais à `null`)

Les forces brutes marchent, mais coûtent un deuxième passage ou de la mémoire en plus :

| Question | Force brute | Coût |
|---|---|---|
| Milieu | parcourir une fois pour compter n, reparcourir n / 2 pas | 2 passages |
| Milieu | copier les nœuds dans une `ArrayList`, prendre `get(n / 2)` | O(n) en mémoire |
| Cycle | ranger chaque nœud visité dans un `HashSet` ; vu deux fois veut dire cycle | O(n) en mémoire |

En entretien, on demande ensuite : « en un seul passage, avec **O(1) en mémoire** ? »

## 2. L'idée clé

Poser **deux pointeurs** sur la liste et les faire avancer à des **vitesses différentes** :
`slow` avance de **1** nœud par pas, `fast` de **2**.

```
 pas 0    slow, fast
            ▼
           [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ null

 pas 1           slow    fast
                  ▼       ▼
           [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ null

 pas 2                  slow           fast
                         ▼              ▼
           [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ null
```

- **Milieu** : `fast` a toujours fait deux fois plus de chemin que `slow`. Quand `fast`
  arrive au bout, `slow` est à mi-chemin.
- **Cycle** : imagine deux coureurs sur une piste circulaire. Le plus rapide finit par
  **prendre un tour** au plus lent : ils se rejoignent. Sans cycle, `fast` arrive
  simplement à `null`.

## 3. Pas à pas sur un exemple

Une liste avec un cycle : `1 → 2 → 3 → 4 → 5 → 6`, et `6.next` revient sur `3`.
Le cycle est `3 → 4 → 5 → 6 → 3` (4 nœuds).

```
 [1] ─▶ [2] ─▶ [3] ─▶ [4] ─▶ [5] ─▶ [6]
                ▲                    │
                └────────────────────┘
```

L'**écart** est le nombre de pas que `fast` devrait faire pour rejoindre `slow` (il n'a de
sens qu'une fois les deux dans le cycle).

| Pas | slow | fast | écart (fast → slow) | Commentaire |
|---|---|---|---|---|
| 0 | 1 | 1 | | les deux partent de la tête |
| 1 | 2 | 3 | | `slow` n'est pas encore dans le cycle |
| 2 | 3 | 5 | 2 | les deux sont dans le cycle : depuis 5, deux pas (5 → 6 → 3) rejoignent `slow` |
| 3 | 4 | 3 | 1 | `fast` est passé par 5 → 6 → 3 |
| 4 | 5 | 5 | **0** | **ils se rejoignent : il y a un cycle** |

Regarde l'écart : **2, puis 1, puis 0**. Il baisse d'exactement un à chaque pas. La section 5
explique pourquoi.

## 4. Le modèle, ligne par ligne

```java
ListNode slow = head;
ListNode fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```

| Ligne | Pourquoi |
|---|---|
| `slow = head; fast = head;` | les deux partent du même endroit, donc « fast a fait deux fois plus de chemin » reste vrai |
| `fast != null` | arrête les listes de longueur **paire** (`fast` saute après le dernier nœud) |
| `fast.next != null` | arrête les listes de longueur **impaire** (`fast` est sur le dernier nœud), et protège `fast.next.next` d'une `NullPointerException` |
| `slow = slow.next` | un pas |
| `fast = fast.next.next` | deux pas |

C'est le squelette. Chaque problème y ajoute **une seule chose** : ce qu'on renvoie après la
boucle, ou un test dans la boucle (pour un cycle : comparer les deux pointeurs **après** les
avoir avancés).

## 5. Pourquoi c'est correct

**Milieu.** Invariant : après `s` pas, `slow` est à `s` nœuds de la tête et `fast` à `2s`
nœuds. La boucle s'arrête quand `fast` ne peut plus faire 2 pas, c'est-à-dire quand `2s`
atteint la fin de la liste. Alors `s` en est la moitié.

**Cycle.** Deux cas :

1. **Pas de cycle** : `fast` parcourt la liste deux fois plus vite, arrive à `null` et la
   boucle s'arrête. Il ne rejoint jamais `slow`, puisque `fast` est toujours devant.
2. **Un cycle** : `fast` entre le premier dans le cycle et tourne. Plus tard `slow` entre
   aussi. À partir de là, à chaque pas :
   - `fast` avance de 2 nœuds, `slow` de 1 nœud ;
   - donc `fast` se rapproche de `slow` d'**1 nœud** ;
   - l'écart fait `g, g - 1, g - 2, ... , 1, 0`.

   L'écart baisse d'**exactement** 1, donc `fast` ne peut pas sauter par-dessus `slow` : il
   tombe dessus quand l'écart vaut 0. L'écart est plus petit que la longueur du cycle, donc
   ils se rejoignent avant que `slow` ait fait un tour complet.

**Coût** : O(n) en temps, O(1) en mémoire (deux références, quelle que soit la taille de la liste).

## 6. Variantes

| Variante | Comment | Sert à |
|---|---|---|
| Milieu | le squelette, puis utiliser `slow` | couper une liste en deux, tri fusion sur une liste |
| Détection de cycle | après avoir avancé, `if (slow == fast)` il y a un cycle | listes cassées, boucles infinies |
| Début du cycle | après la rencontre, remettre un pointeur sur la tête, avancer les deux de **1** pas : ils se retrouvent sur le premier nœud du cycle (Floyd) | « où commence le cycle ? » |
| Écart fixe | `fast` prend d'abord **n** pas d'avance, puis les deux avancent de 1 | n-ième nœud depuis la fin, en un passage |
| Liste palindrome | trouver le milieu, inverser la deuxième moitié, comparer les deux moitiés | « la liste se lit-elle pareil dans les deux sens ? » |
| Suites de nombres | remplacer `node.next` par une fonction `next(x)` | nombre heureux (section 7) |
| Tableau vu comme une liste | valeur = indice du « nœud » suivant | trouver le doublon dans un tableau de valeurs 1..n |

## 7. Un problème résolu du début à la fin

**Problème (nombre heureux)** : partir de `n`, le remplacer par la **somme des carrés de ses
chiffres**, et recommencer. Si on arrive à `1`, `n` est heureux. Sinon les nombres tournent
en rond pour toujours.
`isHappy(19)` → `true`, `isHappy(2)` → `false`.

```
 19 → 1² + 9² = 82 → 8² + 2² = 68 → 100 → 1                  heureux
 2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 → 16 ...     tourne pour toujours
```

1. **Force brute** : garder chaque nombre vu dans un `HashSet` ; si un nombre revient, ça
   tourne en rond. Ça marche, mais ça prend de la mémoire.
2. **Voir la liste chaînée** : chaque nombre a exactement un nombre « suivant ». La suite
   est une liste chaînée construite au fur et à mesure, et « tourne pour toujours » veut dire
   « **a un cycle** ». Donc lent et rapide : `slow` applique la fonction une fois, `fast` deux fois.
3. **S'arrêter** quand `fast` arrive à `1` (heureux), ou quand `slow == fast` (ils se sont
   rejoints dans un cycle qui ne contient pas 1).

Trace pour `n = 2`. `fast` part avec un pas d'avance, pour que la condition de la boucle ne
soit pas vraie tout de suite.

| Pas | slow | fast |
|---|---|---|
| départ | 2 | 4 |
| 1 | 4 | 37 |
| 2 | 16 | 89 |
| 3 | 37 | 42 |
| 4 | 58 | 4 |
| 5 | 89 | 37 |
| 6 | 145 | 89 |
| 7 | **42** | **42** |

Ils se rejoignent sur 42, et `fast` ne vaut pas 1 : **pas heureux**.
Pour `n = 19` : départ `slow = 19, fast = 82`, puis `82 / 100`, puis `68 / 1` : `fast` est
arrivé à 1, **heureux**.

```java
static int sumOfSquares(int n) {
    int sum = 0;
    while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n /= 10;
    }
    return sum;
}

static boolean isHappy(int n) {
    int slow = n;
    int fast = sumOfSquares(n);
    while (fast != 1 && slow != fast) {
        slow = sumOfSquares(slow);
        fast = sumOfSquares(sumOfSquares(fast));
    }
    return fast == 1;
}
```

4. **Coût** : O(1) en mémoire. Les nombres descendent vite sous 243 (la plus grande somme de
   carrés pour un nombre à 3 chiffres est 9² × 3), donc le nombre de pas reste petit.

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| `fast.next.next` sans tester `fast.next` | `NullPointerException` sur les longueurs paires ou impaires | `while (fast != null && fast.next != null)` |
| Tester `slow == fast` **avant** d'avancer | vrai tout de suite : les deux partent de la tête | avancer d'abord, puis comparer |
| Comparer les valeurs : `slow.val == fast.val` | faux cycle trouvé dans une liste avec des doublons | comparer les nœuds : `slow == fast` |
| Démarrer `fast = head.next` sans réfléchir | milieu décalé d'un nœud sur les longueurs paires | décider quel milieu on veut, tester avec 4 nœuds |
| Déplacer `head` au lieu d'un pointeur | le début de la liste est perdu | toujours parcourir avec `slow` / `fast` |
| Oublier la liste vide | `NullPointerException` sur `head.next` | la condition de la boucle gère déjà `head == null` |

## 9. Comment le reconnaître

- **Liste chaînée** + « milieu », « cycle », « n-ième depuis la fin », « palindrome ».
- « En un seul passage », « avec O(1) en mémoire » sur une liste.
- Une suite où chaque valeur donne la **suivante**, et la question est « est-ce que ça tourne
  en rond ? ».
- Un tableau de valeurs `1..n` utilisées comme des « pointeurs » vers d'autres indices.

## 10. S'entraîner

Exos (chapitre 02) : [`datastructures/LinkedListProblems.java`](../../../src/main/java/com/mastery/interview/datastructures/LinkedListProblems.java),
**E03** `middle`, **E04** `hasCycle`, **E06** `removeNthFromEnd`, **E08** `isPalindrome`.

```bash
mvn -Dtest=LinkedListProblemsTest test
```

Avant de coder chaque exo, écrire sur papier : **de combien avance chaque pointeur à chaque
pas ? quand exactement la boucle s'arrête-t-elle, pour une longueur impaire et pour une
paire ? est-ce que je compare des nœuds ou des valeurs ? qu'est-ce que je renvoie après la
boucle ?** Puis dessiner une liste de 4 nœuds et une de 5, et avancer les pointeurs à la main.
