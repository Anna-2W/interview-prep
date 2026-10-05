# Pattern 3. Fenêtre glissante

🇬🇧 [English version](../../en/patterns/03-sliding-window.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Beaucoup de problèmes portent sur un morceau **contigu** d'un tableau ou d'une chaîne (un
sous-tableau, une sous-chaîne) : « le plus long morceau qui... », « le meilleur morceau de
taille k », « combien de morceaux... ».

La force brute essaie chaque début et chaque fin, et vérifie chaque morceau :

```java
for (int start = 0; start < n; start++) {
    for (int end = start; end < n; end++) {
        check(start, end);
    }
}
```

Il y a environ n² / 2 morceaux, et en vérifier un peut coûter O(n) de plus : **O(n²)** ou
même **O(n³)**. Avec n = 100 000, ça ne finit jamais.

## 2. L'idée clé

Deux morceaux voisins partagent presque tout. `[2, 7, 1, 8]` et `[7, 1, 8, 4]` diffèrent
d'**un élément qui sort et un élément qui entre**. Donc on ne recalcule pas tout : on garde
une **fenêtre** `[left, right]` et on la met à jour en O(1) quand elle bouge.

```
           left        right
            ▼           ▼
 [ 4 | 2 | 7 | 1 | 8 | 4 | 6 ]
            └─ fenêtre ─┘

 right avance : un élément entre  (on l'ajoute)
 left avance :  un élément sort   (on le retire)
```

`right` et `left` ne font qu'avancer. Chaque élément entre une fois et sort une fois :
**O(n)** au total, même avec un `while` dans le `for`.

| Sorte | Taille de la fenêtre | Quand |
|---|---|---|
| Fixe | toujours `k` | « de taille k », « tous les k consécutifs » |
| Variable | grandit et rétrécit | « le plus long / le plus court morceau tel que... », « au plus k ... » |

## 3. Pas à pas sur deux exemples

### Fenêtre fixe : le plus de voyelles dans un morceau de longueur 3

`s = "abciiidef"`, `k = 3`. Quel morceau de longueur 3 a le plus de voyelles ?

On garde `count` = nombre de voyelles dans la fenêtre. Quand `right` avance, la nouvelle
lettre entre ; dès que la fenêtre dépasse k, la lettre en `right - k` sort.

| right | Entre | Sort | Fenêtre | count | best |
|---|---|---|---|---|---|
| 0 | a | | a | 1 | (pas encore pleine) |
| 1 | b | | ab | 1 | (pas encore pleine) |
| 2 | c | | abc | 1 | 1 |
| 3 | i | a | bci | 1 | 1 |
| 4 | i | b | cii | 2 | 2 |
| 5 | i | c | iii | 3 | 3 |
| 6 | d | i | iid | 2 | 3 |
| 7 | e | i | ide | 2 | 3 |
| 8 | f | i | def | 1 | 3 |

Réponse : **3** (`"iii"`). Chaque étape coûte O(1) : une lettre entre, une lettre sort.

### Fenêtre variable : le plus long morceau avec au plus 2 lettres différentes

`s = "eceba"`. Trouver le plus long morceau qui utilise au plus 2 lettres différentes.

On garde une map `counts` des lettres de la fenêtre. `right` ajoute une lettre. **Tant
que** la fenêtre a plus de 2 lettres différentes, `left` retire des lettres. Ensuite la
fenêtre est valide : on compare sa longueur à `best`.

| right | Ajoute | left après rétrécissement | Fenêtre | counts | best |
|---|---|---|---|---|---|
| 0 | e | 0 | e | {e=1} | 1 |
| 1 | c | 0 | ec | {c=1, e=1} | 2 |
| 2 | e | 0 | ece | {c=1, e=2} | 3 |
| 3 | b | 2 (e, c retirés) | eb | {b=1, e=1} | 3 |
| 4 | a | 3 (e retiré) | ba | {a=1, b=1} | 3 |

Réponse : **3** (`"ece"`). À l'étape 3, ajouter `b` donnait 3 lettres différentes : `left`
a avancé jusqu'à ce que `c` disparaisse des comptes.

## 4. Les modèles, ligne par ligne

### Fenêtre fixe de taille k

```java
int current = 0;
int best = 0;
for (int right = 0; right < n; right++) {
    current += value(right);
    if (right >= k) {
        current -= value(right - k);
    }
    if (right >= k - 1) {
        best = Math.max(best, current);
    }
}
```

| Ligne | Pourquoi |
|---|---|
| `current += value(right)` | le nouvel élément entre |
| `if (right >= k) current -= value(right - k)` | dès que la fenêtre contiendrait k + 1 éléments, le plus ancien sort |
| `if (right >= k - 1)` | la fenêtre contient exactement k éléments seulement à partir de `right = k - 1` : ne rien enregistrer avant |

### Fenêtre variable (le plus long morceau valide)

```java
int left = 0;
int best = 0;
for (int right = 0; right < n; right++) {
    add(right);
    while (windowIsInvalid()) {
        remove(left);
        left++;
    }
    best = Math.max(best, right - left + 1);
}
```

| Ligne | Pourquoi |
|---|---|
| `for (int right ...)` | `right` agrandit la fenêtre d'un élément à chaque tour |
| `add(right)` | mettre à jour l'état de la fenêtre (une somme, une map de comptes, un nombre de zéros...) |
| `while (windowIsInvalid())` | un `while`, pas un `if` : un seul nouvel élément peut forcer plusieurs retraits |
| `remove(left); left++` | rétrécir par la gauche jusqu'à ce que la fenêtre redevienne valide |
| `right - left + 1` | la longueur de la fenêtre `[left, right]`, bornes comprises |

## 5. Pourquoi c'est correct

**Invariant** (fenêtre variable) : après le `while`, `[left, right]` est la **plus longue
fenêtre valide qui finit en `right`**.

Pourquoi `left` n'a-t-il jamais besoin de reculer ? À cause de la **propriété monotone** :
si `[left, right]` est invalide (3 lettres différentes), toute fenêtre plus grande qui la
contient est invalide aussi. Donc aucune fenêtre valide ne peut commencer avant `left` et
finir en `right` ou après. Avancer `left` ne fait jamais perdre de réponse.

C'est pour ça que le pattern a besoin de cette propriété. Il marche avec « au plus k lettres
différentes », « somme de nombres **positifs** », « nombre de zéros ». Il ne marche **pas**
avec « somme = k » quand les nombres peuvent être négatifs (ajouter un élément peut faire
baisser la somme) : utiliser alors les sommes préfixes (pattern 4).

## 6. Variantes

| Variante | Ce qui change dans la boucle | Exemple |
|---|---|---|
| Taille fixe k | un entre, un sort, enregistrer quand la taille vaut k | le plus de voyelles, meilleure moyenne de k éléments |
| Le plus long valide | agrandir, rétrécir `while` invalide, puis enregistrer | au plus k lettres différentes, plus long morceau sans répétition |
| Le plus court valide | agrandir, `while` **valide** enregistrer puis rétrécir | plus court morceau de somme ≥ cible |
| Compter les morceaux valides | après le rétrécissement, ajouter `right - left + 1` (tous les morceaux valides qui finissent en `right`) | morceaux de produit < k |
| Exactement k | `auPlus(k) - auPlus(k - 1)` | morceaux avec exactement k nombres différents |
| État = des comptes | une `HashMap` ou un `int[26]` des lettres de la fenêtre | anagramme ou permutation dans un texte |

## 7. Un problème résolu du début à la fin

**Problème (produit inférieur à k)** : compter les morceaux contigus de `nums` (tous les
nombres sont positifs) dont le produit est **strictement inférieur à k**.
`countProductLessThan({10, 5, 2, 6}, 100)` → `8`.

1. **Force brute** : chaque début, chaque fin, multiplier. O(n²).
2. **Contigu + une règle qui casse quand le morceau grandit ?** Oui : les nombres sont
   positifs, donc un morceau plus grand a un produit plus grand (ou égal). Fenêtre variable.
3. **État de la fenêtre** : le produit de la fenêtre. Ajouter = multiplier, retirer = diviser.
4. **Astuce de comptage** : quand la fenêtre `[left, right]` est valide, **tous** les
   morceaux qui finissent en `right` et commencent en `left`, `left + 1`, ..., `right` sont
   valides aussi. Ça fait `right - left + 1` nouveaux morceaux.

| right | x | left après rétrécissement | Fenêtre | Produit | Nouveaux morceaux | Total |
|---|---|---|---|---|---|---|
| 0 | 10 | 0 | [10] | 10 | 1 | 1 |
| 1 | 5 | 0 | [10, 5] | 50 | 2 | 3 |
| 2 | 2 | 1 (100 ≥ 100 : 10 retiré) | [5, 2] | 10 | 2 | 5 |
| 3 | 6 | 1 | [5, 2, 6] | 60 | 3 | 8 |

```java
static int countProductLessThan(int[] nums, int k) {
    if (k <= 1) {
        return 0;
    }
    long product = 1;
    int left = 0;
    int count = 0;
    for (int right = 0; right < nums.length; right++) {
        product *= nums[right];
        while (product >= k) {
            product /= nums[left];
            left++;
        }
        count += right - left + 1;
    }
    return count;
}
```

5. **Coût** : O(n) en temps, O(1) en espace. Le garde-fou `if (k <= 1)` compte : avec k = 1,
   aucun produit d'entiers positifs n'est sous 1, et le `while` dépasserait `right`.

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| `if` au lieu de `while` pour rétrécir | la fenêtre reste invalide, réponse trop grande | `while (windowIsInvalid())` |
| Enregistrer `best` avant de rétrécir | compte une fenêtre invalide | rétrécir d'abord, puis enregistrer |
| Longueur `right - left` | décalage de 1 | `right - left + 1` |
| Fenêtre fixe enregistrée trop tôt | `best` pris sur une fenêtre plus petite que k | enregistrer seulement quand `right >= k - 1` |
| Oublier de mettre à jour l'état quand `left` avance | les comptes dérivent, réponse fausse | chaque `left++` a son `remove(left)` |
| Garder dans la map une lettre dont le compte est tombé à 0 | `map.size()` reste trop grand | `if (count == 0) map.remove(key)` |
| L'utiliser avec des négatifs et « somme = k » | rate des réponses | sommes préfixes + hachage |

## 9. Comment le reconnaître

- « Sous-tableau **contigu** », « sous-chaîne », « éléments consécutifs ».
- « Le plus long », « le plus court », « maximum / minimum ... d'un morceau ».
- « De taille k », « tous les k consécutifs ».
- « Au plus k ... », « pas plus de k ... ».
- Ta force brute a deux boucles `start` / `end` sur le même tableau.

Si le morceau n'a **pas** besoin d'être contigu (sous-séquence), ce n'est pas une fenêtre
glissante : penser DP ou backtracking.

## 10. S'entraîner

Exos : [`patterns/slidingwindow/SlidingWindowExercises.java`](../../../src/main/java/com/mastery/interview/patterns/slidingwindow/SlidingWindowExercises.java)

```bash
mvn -Dtest='SlidingWindowExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **fenêtre fixe ou variable ? quel état je
garde pour la fenêtre (somme, comptes, nombre de zéros...) ? quand la fenêtre est-elle
invalide ? qu'est-ce que j'enregistre, et quand ?**
