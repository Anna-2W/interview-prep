# Pattern 4. Sommes préfixes

🇬🇧 [English version](../../en/patterns/04-prefix-sums.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Beaucoup d'exos demandent **la somme d'un morceau du tableau** : « somme entre l'indice i et
j », « combien de sous-tableaux ont une somme k », « où est le point d'équilibre »...

La force brute refait l'addition à chaque question :

```java
for (int[] q : queries) {
    long sum = 0;
    for (int i = q[0]; i <= q[1]; i++) {
        sum += nums[i];
    }
    answers.add(sum);
}
```

Chaque requête parcourt jusqu'à n nombres : **O(n)** par requête, **O(n × q)** pour q
requêtes. Avec 100 000 nombres et 100 000 requêtes, ça fait 10 milliards d'additions. Trop lent.

Même problème pour « les sous-tableaux de somme k » : essayer chaque début et chaque fin, O(n²).

## 2. L'idée clé

Tout additionner **une seule fois**, depuis la gauche, et garder chaque total courant dans
un tableau `prefix`. `prefix[i]` est la somme des **i premiers nombres**.

```
 nums        [ 3 | 1 | 4 | 1 | 5 ]
 indice        0   1   2   3   4

 prefix    [ 0 | 3 | 4 | 8 | 9 | 14 ]
 indice      0   1   2   3   4   5

 somme(1..3) = prefix[4] - prefix[1] = 9 - 3 = 6      (1 + 4 + 1)
```

`prefix[4]` vaut `3 + 1 + 4 + 1`. `prefix[1]` vaut `3`. La soustraction enlève tout ce qui
est **avant** l'indice 1, et il reste exactement `1 + 4 + 1`.

Donc toute somme d'intervalle est **une seule soustraction** : O(1), après O(n) pour
construire le tableau.

## 3. Pas à pas sur un exemple

Construire `prefix` pour `nums = [3, 1, 4, 1, 5]`, puis répondre à trois requêtes.

| Étape | i | nums[i] | prefix[i + 1] = prefix[i] + nums[i] |
|---|---|---|---|
| départ | | | prefix[0] = 0 |
| 1 | 0 | 3 | prefix[1] = 0 + 3 = 3 |
| 2 | 1 | 1 | prefix[2] = 3 + 1 = 4 |
| 3 | 2 | 4 | prefix[3] = 4 + 4 = 8 |
| 4 | 3 | 1 | prefix[4] = 8 + 1 = 9 |
| 5 | 4 | 5 | prefix[5] = 9 + 5 = 14 |

`prefix = [0, 3, 4, 8, 9, 14]`

| Requête | Formule | Résultat | Vérification |
|---|---|---|---|
| somme(1..3) | prefix[4] - prefix[1] | 9 - 3 = **6** | 1 + 4 + 1 |
| somme(0..4) | prefix[5] - prefix[0] | 14 - 0 = **14** | tout le tableau |
| somme(2..2) | prefix[3] - prefix[2] | 8 - 4 = **4** | juste nums[2] |

Chaque requête coûte une soustraction, quelle que soit la taille de l'intervalle.

## 4. Le modèle, ligne par ligne

```java
long[] prefix = new long[nums.length + 1];
for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}

long rangeSum(long[] prefix, int from, int to) {
    return prefix[to + 1] - prefix[from];
}
```

| Ligne | Pourquoi |
|---|---|
| `new long[nums.length + 1]` | une case de plus que `nums` : `prefix[0] = 0` est la somme de « rien » |
| `long` | la somme de beaucoup d'`int` peut dépasser un `int` |
| `prefix[i + 1] = prefix[i] + nums[i]` | chaque total réutilise le précédent : O(1) par case |
| `prefix[to + 1] - prefix[from]` | tout jusqu'à `to`, moins tout ce qui est avant `from` |

Le `0` en plus au début permet à `somme(0..j)` de marcher sans cas particulier :
`prefix[j + 1] - prefix[0]`.

## 5. Pourquoi c'est correct

**Invariant** : après l'étape `i`, `prefix[i + 1] = nums[0] + nums[1] + ... + nums[i]`.

Alors pour tout `from <= to` :

```
 prefix[to + 1]  = nums[0] + ... + nums[from - 1] + nums[from] + ... + nums[to]
 prefix[from]    = nums[0] + ... + nums[from - 1]
 différence      =                                  nums[from] + ... + nums[to]
```

Le début commun s'annule. Ça marche aussi avec des **nombres négatifs** : ce ne sont que des
additions et des soustractions.

## 6. Variantes

### Sommes préfixes + table de hachage : « un sous-tableau de somme k »

C'est la variante la plus importante. À lire lentement.

Un sous-tableau `i..j` a une somme k exactement quand `prefix[j + 1] - prefix[i] = k`,
c'est-à-dire quand `prefix[i] = prefix[j + 1] - k`.

Donc en parcourant le tableau avec une somme courante `sum`, la question à chaque pas est :

> « Est-ce qu'une somme courante **précédente** valait `sum - k` ? »

C'est une question de **hachage** (pattern 1) : on garde les sommes précédentes dans une `HashMap`.

| Tu veux | La map contient | On commence avec |
|---|---|---|
| existe-t-il un sous-tableau de somme k ? | les sommes précédentes (`HashSet`) | `{0}` |
| combien de sous-tableaux ont une somme k ? | somme précédente → **combien de fois** elle est apparue | `{0=1}` |
| le plus long sous-tableau de somme k | somme précédente → **premier indice** où elle est apparue | `{0=-1}` |

L'entrée de départ pour la somme `0` représente « le préfixe vide, avant l'indice 0 ». Sans
elle, les sous-tableaux qui commencent à l'indice 0 ne sont jamais trouvés.

La section 7 résout la version « le plus long » pas à pas.

### Autres variantes

| Variante | Idée | Exemple |
|---|---|---|
| Compter quelque chose | préfixe de « 1 si l'élément correspond, sinon 0 » | nombre de voyelles entre i et j |
| XOR préfixe | `px[i + 1] = px[i] ^ nums[i]`, puis `xor(i..j) = px[j + 1] ^ px[i]` | XOR d'un intervalle |
| Produits depuis la gauche et depuis la droite | `left[i]` = produit avant i, `right[i]` = produit après i | produit du tableau sauf soi-même |
| Sommes préfixes 2D | `p[r + 1][c + 1] = grid[r][c] + p[r][c + 1] + p[r + 1][c] - p[r][c]` | somme d'un rectangle d'une grille en O(1) |
| Tableau de différences (l'inverse) | ajouter `v` en `from`, retirer `v` en `to + 1`, puis faire la somme préfixe | ajouter une valeur à beaucoup d'intervalles, puis lire le tableau final |

## 7. Un problème résolu du début à la fin

**Problème (plus long sous-tableau de somme k)** : renvoyer la longueur du plus long morceau
contigu de `nums` dont la somme vaut exactement `k`. Les nombres peuvent être négatifs.
`longestWithSum([1, -1, 5, -2, 3], 3)` → `4` (le morceau `[1, -1, 5, -2]`).

1. **Force brute** : essayer chaque début et chaque fin, O(n²).
2. **Pourquoi pas une fenêtre glissante ?** Avec des nombres négatifs, agrandir la fenêtre
   peut faire **baisser** la somme : on ne sait jamais quand rétrécir. La fenêtre glissante
   a besoin de nombres positifs.
3. **Quelle question se répète ?** À l'indice `j`, avec `sum` = total de `nums[0..j]` : « où
   est le **premier** indice `i` où la somme courante valait `sum - k` ? » Alors `i + 1 .. j`
   a une somme k, et sa longueur est `j - i`.
4. **Structure de hachage** : somme courante → premier indice où elle est apparue. On
   commence avec `{0=-1}`. On utilise `putIfAbsent` pour garder le **premier** indice (le
   début le plus tôt donne le morceau le plus long).

| i | nums[i] | sum | need = sum - k | need dans la map ? | best | Map après l'étape |
|---|---|---|---|---|---|---|
| départ | | 0 | | | 0 | {0=-1} |
| 0 | 1 | 1 | -2 | non | 0 | {0=-1, 1=0} |
| 1 | -1 | 0 | -3 | non | 0 | {0=-1, 1=0} (0 déjà là, on garde -1) |
| 2 | 5 | 5 | 2 | non | 0 | {0=-1, 1=0, 5=2} |
| 3 | -2 | 3 | 0 | **oui, en -1** : longueur 3 - (-1) = 4 | **4** | {0=-1, 1=0, 3=3, 5=2} |
| 4 | 3 | 6 | 3 | oui, en 3 : longueur 4 - 3 = 1 | 4 | {0=-1, 1=0, 3=3, 5=2, 6=4} |

Réponse : **4**. Le résultat de l'étape 3 utilise l'entrée de départ `0=-1` : le morceau
commence à l'indice 0.

```java
static int longestWithSum(int[] nums, int k) {
    Map<Integer, Integer> firstIndex = new HashMap<>();
    firstIndex.put(0, -1);
    int sum = 0;
    int best = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        Integer start = firstIndex.get(sum - k);
        if (start != null) {
            best = Math.max(best, i - start);
        }
        firstIndex.putIfAbsent(sum, i);
    }
    return best;
}
```

5. **Coût** : O(n) en temps, O(n) en espace pour la map. On ne construit jamais le tableau
   `prefix` : la somme courante plus la map suffisent.

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| `prefix` de taille n, sans 0 au début | les intervalles qui commencent à 0 demandent un cas particulier, ou sont faux | taille `n + 1`, `prefix[0] = 0` |
| `prefix[to] - prefix[from]` | le dernier élément manque | `prefix[to + 1] - prefix[from]` |
| `prefix` en `int` sur de grosses entrées | des totaux négatifs sortis de nulle part (dépassement) | `long[] prefix` |
| Map sans l'entrée de départ `0` | les sous-tableaux qui commencent à l'indice 0 ne sont jamais comptés | `put(0, 1)` ou `put(0, -1)` avant la boucle |
| Ranger la somme courante **avant** de vérifier | avec k = 0, un morceau vide compte comme réponse | vérifier `sum - k` d'abord, puis ranger `sum` |
| `put` au lieu de `putIfAbsent` pour « le plus long » | l'indice avance, les morceaux raccourcissent | garder le **premier** indice |
| Fenêtre glissante avec des négatifs | réponses fausses | sommes préfixes + map |

## 9. Comment le reconnaître

- « Somme entre i et j », « beaucoup de requêtes sur des intervalles ».
- « Nombre de sous-tableaux de somme k », « plus long sous-tableau de somme k », « somme
  divisible par k ».
- « Point d'équilibre », « somme à gauche égale somme à droite ».
- Le tableau peut contenir des **nombres négatifs**, donc une fenêtre glissante ne marche pas.
- Ta force brute recalcule les mêmes sommes encore et encore.

## 10. S'entraîner

Exos : [`patterns/prefixsum/PrefixSumExercises.java`](../../../src/main/java/com/mastery/interview/patterns/prefixsum/PrefixSumExercises.java)

```bash
mvn -Dtest='PrefixSumExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **que contient `prefix[i]` (ou la somme
courante) ? quelle soustraction donne ma réponse ? si j'utilise une map, que contient-elle
et quelle est son entrée de départ ?**
