# Pattern 6. Recherche dichotomique

🇬🇧 [English version](../../en/patterns/06-binary-search.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

« Trouver une valeur dans un tableau **trié**. » La force brute regarde chaque case :

```java
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == target) {
        return i;
    }
}
return -1;
```

C'est **O(n)** : avec un milliard de valeurs, un milliard de tests. Et ça ignore
l'information la plus utile : le tableau est **trié**.

Le même gâchis existe sous une autre forme : « trouver la **plus petite valeur** qui marche »
(une vitesse, une capacité, une taille...) en essayant 1, 2, 3, 4... jusqu'à ce qu'une marche.

## 2. L'idée clé

Regarder le **milieu**. Comme le tableau est trié, une seule comparaison dit quelle
**moitié** ne peut pas contenir la réponse : on la jette.

```
 chercher 23 dans [2 5 8 12 16 23 38 56 72 91]

 [2  5  8  12  16  23  38  56  72  91]     milieu 16 < 23 : on garde la moitié droite
                  [23  38  56  72  91]     milieu 56 > 23 : on garde la moitié gauche
                  [23  38]                 milieu 23 : trouvé
```

Chaque pas **coupe en deux** la zone de recherche : 1 000 000 → 500 000 → ... → 1 en environ
**20** pas. Un milliard de valeurs demande environ **30** pas. C'est **O(log n)**.

## 3. Pas à pas sur un exemple

Chercher **23** dans `[2, 5, 8, 12, 16, 23, 38, 56, 72, 91]` (indices 0 à 9).
`mid = left + (right - left) / 2`.

| Pas | left | right | mid | nums[mid] | Décision |
|---|---|---|---|---|---|
| 1 | 0 | 9 | 4 | 16 | 16 < 23 : la réponse est à droite, `left = 5` |
| 2 | 5 | 9 | 7 | 56 | 56 > 23 : la réponse est à gauche, `right = 6` |
| 3 | 5 | 6 | 5 | 23 | **trouvé à l'indice 5** |

3 tests au lieu de 6.

Maintenant chercher **20**, qui est **absent** :

| Pas | left | right | mid | nums[mid] | Décision |
|---|---|---|---|---|---|
| 1 | 0 | 9 | 4 | 16 | 16 < 20 : `left = 5` |
| 2 | 5 | 9 | 7 | 56 | 56 > 20 : `right = 6` |
| 3 | 5 | 6 | 5 | 23 | 23 > 20 : `right = 4` |
| fin | 5 | 4 | | | `left > right` : la zone est vide, **renvoyer -1** |

Regarde où finit `left` : **5**, exactement là où il faudrait insérer 20 pour garder le
tableau trié. Retiens-le, c'est l'idée derrière la « borne inférieure » (section 6).

## 4. Le modèle, ligne par ligne

```java
int left = 0;
int right = nums.length - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
return -1;
```

| Ligne | Pourquoi |
|---|---|
| `right = nums.length - 1` | la zone `[left, right]` inclut **les deux** bouts |
| `while (left <= right)` | une zone d'un seul élément (`left == right`) doit encore être testée |
| `left + (right - left) / 2` | pareil que `(left + right) / 2`, mais `left + right` peut dépasser un `int` |
| `nums[mid] == target` | trouvé |
| `left = mid + 1` | `mid` est trop petit, et tout ce qui est à sa gauche aussi : on les jette, **avec** `mid` |
| `right = mid - 1` | `mid` est trop grand, et tout ce qui est à sa droite aussi |
| `return -1` | la zone est devenue vide : la valeur n'est pas là |

## 5. Pourquoi c'est correct

**Invariant** : si `target` est dans le tableau, il est dans `[left, right]`.

- Au départ, `[0, n - 1]` est tout le tableau : vrai.
- Quand `nums[mid] < target`, tout ce qui est en `mid` ou avant est `<= nums[mid] < target`
  (le tableau est trié), donc la cible ne peut être qu'après `mid` : `left = mid + 1` garde
  l'invariant vrai. Même raisonnement de l'autre côté.
- Chaque pas retire au moins `mid` lui-même, donc la zone rétrécit toujours : la boucle se termine.
- Si elle se termine avec `left > right`, la zone est vide, et d'après l'invariant la cible
  n'est pas dans le tableau.

**Coût** : O(log n) en temps, O(1) en mémoire.

## 6. Variantes

### Borne inférieure : la première position où `nums[i] >= target`

Très utile : « où insérer », « première occurrence », « combien de valeurs sont plus petites ».
Le modèle change à **trois** endroits :

```java
int lo = 0;
int hi = nums.length;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] < target) {
        lo = mid + 1;
    } else {
        hi = mid;
    }
}
return lo;
```

| Changement | Pourquoi |
|---|---|
| `hi = nums.length` (pas `- 1`) | la réponse peut valoir `n` : « après tous les éléments » |
| `while (lo < hi)` | on s'arrête quand `lo == hi` : il reste un seul candidat, et c'est la réponse |
| `hi = mid` (pas `mid - 1`) | `mid` est assez grand, donc il **peut être** la réponse : on le garde |

**Invariant** : la réponse est toujours dans `[lo, hi]`. Quand `lo == hi`, il ne reste qu'une
seule place possible.

Trace : première position de **3** dans `[1, 3, 3, 3, 5, 8]`.

| Pas | lo | hi | mid | nums[mid] | Décision |
|---|---|---|---|---|---|
| 1 | 0 | 6 | 3 | 3 | 3 >= 3, assez grand : `hi = 3` |
| 2 | 0 | 3 | 1 | 3 | 3 >= 3, assez grand : `hi = 1` |
| 3 | 0 | 1 | 0 | 1 | 1 < 3, trop petit : `lo = 1` |
| fin | 1 | 1 | | | `lo == hi` : réponse **1** |

La recherche classique aurait pu s'arrêter sur n'importe lequel des trois 3. La borne
inférieure trouve toujours le **premier**.

**Borne supérieure** (première position où `nums[i] > target`) : même code avec
`nums[mid] <= target`. Alors le nombre de fois où `target` apparaît vaut
`borneSup - borneInf`.

### Dichotomie sur la réponse

Parfois il n'y a pas de tableau à fouiller : on cherche un **nombre** (une vitesse, une
taille, une capacité...). Ça marche quand la question est **monotone** :

```
 valeur      1    2    3    4    5    6    7
 marche ?    non  non  non  oui  oui  oui  oui
                            ▲
                            la plus petite valeur qui marche
```

Si une valeur marche, toutes les valeurs plus grandes marchent aussi. Donc « marche ? »
ressemble à `non non non oui oui oui` : un tableau de réponses trié. On cherche par
dichotomie le **premier oui**, exactement comme la borne inférieure :

```java
int lo = smallestPossible;
int hi = biggestPossible;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (works(mid)) {
        hi = mid;
    } else {
        lo = mid + 1;
    }
}
return lo;
```

Le coût est de O(log(intervalle)) appels à `works`. La section 7 l'utilise.

### Quel modèle ?

| Question | Zone | Boucle | Déplacements | Renvoie |
|---|---|---|---|---|
| « `target` est-il là ? où ? » | `[0, n - 1]` | `left <= right` | `mid + 1` / `mid - 1` | `mid`, ou `-1` |
| « première position avec `nums[i] >= target` » | `[0, n]` | `lo < hi` | `mid + 1` / `hi = mid` | `lo` |
| « plus petite valeur qui marche » | `[min, max]` des réponses possibles | `lo < hi` | `mid + 1` / `hi = mid` | `lo` |

Choisir un modèle par question et **toujours l'écrire de la même façon**. La plupart des bugs
de dichotomie viennent d'un mélange de morceaux de deux modèles.

Pourquoi `lo < hi` avec `hi = mid` ne boucle jamais à l'infini : `mid` est arrondi **vers le
bas**, donc `mid < hi` toujours, et `hi = mid` rétrécit la zone. Par contre `lo = mid` (au
lieu de `mid + 1`) peut boucler à l'infini quand `hi = lo + 1`.

## 7. Un problème résolu du début à la fin

**Problème (expédier des colis)** : des colis avec ces poids doivent partir **dans cet
ordre**. Chaque jour on charge le bateau avec les colis suivants, jusqu'à ce qu'un colis de
plus dépasse sa capacité. Renvoyer la **plus petite capacité** qui expédie tout en `days` jours.
`shipWithinDays([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 5)` → `15`.

1. **Force brute** : essayer la capacité 1, 2, 3... et simuler chacune, O(somme × n).
2. **Est-ce monotone ?** Si la capacité `c` expédie tout à temps, un bateau plus grand aussi.
   Oui : dichotomie sur la réponse.
3. **Bornes** : le bateau doit porter le colis le plus lourd, donc `lo = poids max = 10`. Avec
   `hi = somme de tous les poids = 55`, tout part le premier jour.
4. **Le test `works(c)`** : compter les jours nécessaires avec la capacité `c` (un passage,
   O(n)), et comparer avec `days`.

| Pas | lo | hi | mid | Jours nécessaires avec mid | Décision |
|---|---|---|---|---|---|
| 1 | 10 | 55 | 32 | 2 | 2 <= 5, ça marche : `hi = 32` |
| 2 | 10 | 32 | 21 | 3 | ça marche : `hi = 21` |
| 3 | 10 | 21 | 15 | 5 | ça marche : `hi = 15` |
| 4 | 10 | 15 | 12 | 6 | 6 > 5, trop petit : `lo = 13` |
| 5 | 13 | 15 | 14 | 6 | trop petit : `lo = 15` |
| fin | 15 | 15 | | | réponse **15** |

Vérification : avec 15, les jours sont `[1..5] = 15`, `[6, 7] = 13`, `[8]`, `[9]`, `[10]` :
5 jours. Avec 14, il faut 6 jours.

```java
static int daysNeeded(int[] weights, int capacity) {
    int days = 1;
    int load = 0;
    for (int w : weights) {
        if (load + w > capacity) {
            days++;
            load = 0;
        }
        load += w;
    }
    return days;
}

static int shipWithinDays(int[] weights, int days) {
    int lo = 0;
    int hi = 0;
    for (int w : weights) {
        lo = Math.max(lo, w);
        hi += w;
    }
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (daysNeeded(weights, mid) <= days) {
            hi = mid;
        } else {
            lo = mid + 1;
        }
    }
    return lo;
}
```

5. **Coût** : O(n × log(somme)) : ici 5 tests de 10 colis, au lieu d'essayer 6 capacités une
   par une de 10 à 15 (et beaucoup plus sur de grosses entrées).

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| `(left + right) / 2` | `mid` négatif sur d'énormes indices ou valeurs (dépassement) | `left + (right - left) / 2` |
| `lo = mid` avec `while (lo < hi)` | boucle infinie quand `hi = lo + 1` | `lo = mid + 1` |
| `while (left < right)` avec `right = mid - 1` | le dernier élément restant n'est jamais testé | `left <= right` pour la recherche classique |
| `hi = n - 1` pour une borne inférieure | faux quand la cible est plus grande que tout (la réponse est `n`) | `hi = n` |
| Tableau non trié | réponses au hasard | trier d'abord, ou utiliser un autre pattern |
| `works` n'est pas monotone | la dichotomie saute par-dessus la réponse | vérifier : « si x marche, est-ce que x + 1 marche ? » |
| Mauvaises bornes pour la réponse (`lo = 0` pour une vitesse) | division par zéro, ou la réponse hors de `[lo, hi]` | `lo` = plus petite valeur **valide**, `hi` = une valeur qui marche à coup sûr |
| `mid * mid` ou une somme en `int` | dépassement dans `works` | `long` |

## 9. Comment la reconnaître

- Le tableau est **trié** (ou trié puis tourné).
- « En O(log n) ».
- « Trouver la **première** / **dernière** position où... ».
- « La vitesse / capacité / taille **minimale** telle que... » ou « la valeur **maximale**
  telle que... » : la réponse est un nombre dans un intervalle, et « est-ce que x marche ? »
  est facile à vérifier.
- L'entrée est énorme (10⁹), donc même O(n) sur l'intervalle des réponses est trop lent.

## 10. S'entraîner

Exos : [`patterns/binarysearch/BinarySearchExercises.java`](../../../src/main/java/com/mastery/interview/patterns/binarysearch/BinarySearchExercises.java)

```bash
mvn -Dtest='BinarySearchExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **quelle est ma zone de recherche (des
indices ou des valeurs) ? que valent `lo` et `hi` au départ ? quel modèle (`<=` ou `<`) ?
quelle question je pose sur `mid`, et quelle moitié je garde ? la zone peut-elle arrêter de
rétrécir ?** Puis dérouler 3 pas à la main avec un tableau comme ceux du dessus.
