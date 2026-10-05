# Pattern 8. Intervalles

🇬🇧 [English version](../../en/patterns/08-intervals.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

L'entrée est une liste d'intervalles `[début, fin]` : réunions, réservations, créneaux,
plages de nombres. La question porte sur la façon dont ils se **chevauchent** : les fusionner,
les compter, trouver du temps libre, trouver des conflits.

La force brute compare **chaque paire** d'intervalles :

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (overlap(intervals[i], intervals[j])) {
            ...
        }
    }
}
```

Ça fait **O(n²)** paires. Pire : quand on fusionne deux intervalles, le résultat peut en
chevaucher d'autres, et il faut parfois recommencer les comparaisons.

## 2. L'idée clé

**Trier les intervalles par début.** Ensuite, il suffit de comparer chaque intervalle avec
**le bloc courant** (le dernier fusionné), jamais avec toute la liste.

```
 temps  1   2   3   4   5   6   7   8
 [1,3]  |-------|
 [2,5]      |-----------|
 [6,7]                  |---|
 [7,8]                      |---|

 triés par début, lus de gauche à droite :
 bloc [1,3] -> [2,5] commence avant la fin de 3 : même bloc [1,5]
 bloc [1,5] -> [6,7] commence après 5 : le bloc [1,5] est fini, nouveau bloc [6,7]
 bloc [6,7] -> [7,8] commence à 7, il touche : même bloc [6,8]
```

**La condition de chevauchement.** Deux intervalles `[a, b]` et `[c, d]` se chevauchent quand
chacun commence avant la fin de l'autre :

```
 a <= d  &&  c <= b       (se toucher compte comme chevaucher)
 a <  d  &&  c <  b       (se toucher ne compte pas : une réunion qui finit à 10 et une qui commence à 10, c'est bon)
```

Une fois la liste triée, `c >= a` est toujours vrai, donc le test devient seulement
**`c <= b`** : « est-ce que le suivant commence avant la fin du bloc courant ? »

**Pourquoi le tri rend la vérification locale** : si l'intervalle suivant commence **après**
la fin du bloc courant, tous les intervalles d'après commencent encore plus tard (ils sont
triés), donc aucun ne peut toucher le bloc courant. Le bloc est fini, on ne revient jamais en
arrière.

## 3. Pas à pas sur un exemple

**Durée totale couverte** par `[[1, 3], [2, 5], [7, 8], [6, 7]]` (un moment couvert deux fois
ne compte qu'une fois). Après le tri par début : `[[1, 3], [2, 5], [6, 7], [7, 8]]`.

| Étape | Intervalle | Commence avant la fin du bloc ? | Bloc après | Total après |
|---|---|---|---|---|
| départ | [1, 3] | le premier | [1, 3] | 0 |
| 1 | [2, 5] | 2 <= 3, oui | [1, 5] | 0 |
| 2 | [6, 7] | 6 <= 5, non : on ferme [1, 5], on ajoute 4 | [6, 7] | 4 |
| 3 | [7, 8] | 7 <= 7, oui | [6, 8] | 4 |
| fin | | on ferme [6, 8], on ajoute 2 | | **6** |

Réponse : **6** (de 1 à 5, puis de 6 à 8).

## 4. Le modèle de code, ligne par ligne

```java
static int totalCovered(int[][] intervals) {
    int[][] sorted = intervals.clone();
    Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
    int start = sorted[0][0];
    int end = sorted[0][1];
    int total = 0;
    for (int i = 1; i < sorted.length; i++) {
        if (sorted[i][0] <= end) {
            end = Math.max(end, sorted[i][1]);
        } else {
            total += end - start;
            start = sorted[i][0];
            end = sorted[i][1];
        }
    }
    total += end - start;
    return total;
}
```

| Ligne | Pourquoi |
|---|---|
| `intervals.clone()` | ne pas réordonner le tableau de l'appelant |
| `Arrays.sort(..., Integer.compare(a[0], b[0]))` | trier par début. `Integer.compare` ne déborde jamais, `a[0] - b[0]` peut déborder |
| `start`, `end` | le **bloc courant**, la seule chose avec laquelle on compare |
| `sorted[i][0] <= end` | le test de chevauchement après le tri (`<` si se toucher ne compte pas) |
| `Math.max(end, sorted[i][1])` | le suivant peut finir **avant** le bloc (il est dedans) : ne jamais rétrécir le bloc |
| branche `else` | le bloc est fini : on l'utilise (ici, on ajoute sa longueur), puis on démarre un nouveau bloc |
| `total += end - start` après la boucle | le **dernier** bloc n'est jamais fermé dans la boucle |

**Coût** : O(n log n) pour le tri, puis O(n) pour le passage.

## 5. Pourquoi c'est correct

**Invariant** : après l'étape `i`, `[start, end]` est l'union de tous les intervalles déjà
lus qui sont reliés au dernier, et chaque bloc fermé avant lui est définitif.

Un bloc n'est fermé que quand le début suivant est `> end`. Comme les débuts ne font que
grandir, aucun intervalle plus loin ne peut revenir dedans. Donc aucun chevauchement n'est
raté, et aucun moment n'est compté deux fois.

## 6. Les variantes

| Problème | Trier par | Ce qui change |
|---|---|---|
| Fusionner, durée totale, temps libre entre les blocs | début | ce qu'on fait quand un bloc se ferme (le garder, ajouter sa longueur, noter le trou) |
| Est-ce que deux se chevauchent ? (une personne, toutes les réunions) | début | renvoyer `false` au premier chevauchement avec le précédent |
| Insérer dans une liste déjà triée | rien | trois parties : avant, chevauchants (fusion), après |
| Garder le plus d'intervalles sans chevauchement | **fin** | glouton, chapitre 07 |
| Intersection de deux listes triées | déjà triées | deux pointeurs, section 7 |
| Combien en même temps (balayage) | événements | voir ci-dessous |

**Balayage avec des événements (sweep line).** On coupe chaque intervalle en deux
événements : `+1` à son début, `-1` à sa fin. On trie les événements par temps ; quand deux
événements ont le même temps, on met le `-1` d'abord (une réunion qui finit à 4 libère sa
salle pour une qui commence à 4). On parcourt les événements en tenant un compteur : c'est
« combien d'intervalles sont ouverts en ce moment ».

Exemple `[[1, 4], [2, 6], [5, 7]]` :

| Temps | Événement | Ouverts maintenant |
|---|---|---|
| 1 | +1 | 1 |
| 2 | +1 | 2 |
| 4 | -1 | 1 |
| 5 | +1 | 2 |
| 6 | -1 | 1 |
| 7 | -1 | 0 |

Le plus grand compteur (2) est le nombre maximum d'intervalles ouverts en même temps.

## 7. Un problème résolu du début à la fin

**Problème (intersections d'intervalles)** : deux listes d'intervalles, chacune triée et sans
chevauchement à l'intérieur. Renvoyer les intervalles où les **deux** listes sont couvertes.

`a = [[0, 2], [5, 10], [13, 23], [24, 25]]`, `b = [[1, 5], [8, 12], [15, 24], [25, 26]]`
→ `[[1, 2], [5, 5], [8, 10], [15, 23], [24, 24], [25, 25]]`.

1. **Force brute** : comparer chaque intervalle de `a` avec chaque intervalle de `b`. O(n × m).
2. **Profiter de l'ordre** : les deux listes sont triées, on les parcourt ensemble avec deux
   pointeurs `i` et `j`, comme pour fusionner deux listes triées.
3. **Intersection de deux intervalles** : `lo = max(débuts)`, `hi = min(fins)`. Si `lo <= hi`,
   `[lo, hi]` est commun.
4. **Quel pointeur avance ?** L'intervalle qui **finit le premier** ne peut plus rien
   rencontrer dans l'autre liste : on avance son pointeur.

| a[i] | b[j] | lo = max(débuts) | hi = min(fins) | Ajouté | On avance |
|---|---|---|---|---|---|
| [0, 2] | [1, 5] | 1 | 2 | [1, 2] | i (2 < 5) |
| [5, 10] | [1, 5] | 5 | 5 | [5, 5] | j |
| [5, 10] | [8, 12] | 8 | 10 | [8, 10] | i (10 < 12) |
| [13, 23] | [8, 12] | 13 | 12 | rien (13 > 12) | j |
| [13, 23] | [15, 24] | 15 | 23 | [15, 23] | i (23 < 24) |
| [24, 25] | [15, 24] | 24 | 24 | [24, 24] | j |
| [24, 25] | [25, 26] | 25 | 25 | [25, 25] | i (25 < 26) |

```java
static int[][] intersect(int[][] a, int[][] b) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < a.length && j < b.length) {
        int lo = Math.max(a[i][0], b[j][0]);
        int hi = Math.min(a[i][1], b[j][1]);
        if (lo <= hi) {
            result.add(new int[] {lo, hi});
        }
        if (a[i][1] < b[j][1]) {
            i++;
        } else {
            j++;
        }
    }
    return result.toArray(new int[0][]);
}
```

5. **Coût** : O(n + m) en temps, pas besoin de trier. O(1) en espace en plus du résultat.

## 8. Les bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| Oublier de trier | blocs fusionnés dans le mauvais ordre, chevauchements ratés | `Arrays.sort` par début d'abord |
| Trier avec `a[0] - b[0]` | mauvais ordre avec de très grandes valeurs ou des négatifs (débordement) | `Integer.compare(a[0], b[0])` |
| `end = sorted[i][1]` au lieu de `Math.max` | un intervalle à l'intérieur du bloc le rétrécit | `Math.max(end, sorted[i][1])` |
| Oublier le dernier bloc | le dernier intervalle fusionné manque | le traiter après la boucle |
| `<` contre `<=` | intervalles qui se touchent fusionnés (ou pas) par erreur | relire l'énoncé : se toucher compte-t-il ? |
| Modifier les lignes de l'entrée | les intervalles de l'appelant changent | copier la ligne (`new int[] {s, e}`) avant de la modifier |
| Renvoyer une `List<int[]>` alors qu'on attend `int[][]` | ne compile pas | `list.toArray(new int[0][])` |

## 9. Comment le reconnaître

- L'entrée est une liste de paires `[début, fin]` : réunions, réservations, plages, créneaux.
- Les mots : « chevauchement », « fusionner », « conflit », « temps libre », « en même temps », « couvrir ».
- Si les intervalles ne sont pas triés, la première ligne est presque toujours un tri par début.
- « Combien en même temps » ou « combien de salles » : balayage, ou un tas des heures de fin (pattern 9).

## 10. S'entraîner

Exos : [`patterns/intervals/IntervalExercises.java`](../../../src/main/java/com/mastery/interview/patterns/intervals/IntervalExercises.java)

```bash
mvn -Dtest='IntervalExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **l'entrée est-elle triée ? trier par début ou
par fin ? se toucher compte-t-il comme chevaucher ? que fais-je quand un bloc se ferme ?** Puis
dessiner les intervalles sur une ligne du temps, comme dans la section 2.
