# Pattern 11. Backtracking (retour sur trace)

🇬🇧 [English version](../../en/patterns/11-backtracking.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Certains problèmes demandent **toutes** les solutions : toutes les combinaisons, tous les
ordres, toutes les façons de remplir une grille, toutes les chaînes valides...

Exemple : lister toutes les paires de nombres prises dans `1..3` : `[1, 2]`, `[1, 3]`, `[2, 3]`.

Pour des paires, deux boucles imbriquées suffisent :

```java
for (int a = 1; a <= n; a++) {
    for (int b = a + 1; b <= n; b++) {
        result.add(List.of(a, b));
    }
}
```

Mais « tous les groupes de **k** nombres » demanderait **k boucles imbriquées**, et k n'est
connu qu'au moment où le programme tourne. On ne peut pas écrire un nombre variable de
boucles. Tout générer (les `2ⁿ` groupes) puis filtrer à la fin marche, mais perd du temps sur
des groupes qui étaient faux dès le début.

## 2. L'idée clé

Construire une solution **un choix à la fois**, avec la **récursion** qui joue le rôle des
boucles imbriquées. À chaque étape :

1. **Choisir** : ajouter un élément à la solution en cours.
2. **Explorer** : s'appeler soi-même pour faire les choix suivants.
3. **Annuler** : retirer cet élément, pour pouvoir essayer l'option suivante.

Tous les choix possibles forment un **arbre de décision**. Le backtracking parcourt cet arbre
en profondeur. Groupes de 2 nombres parmi `1..3` :

```
                       []
           /           |          \
        [1]           [2]          [3]
       /    \          |
   [1, 2]  [1, 3]    [2, 3]
   sauver  sauver    sauver
```

Chaque niveau de l'arbre est un choix. Chaque chemin du haut jusqu'à un « sauver » est une
solution. Remonter une branche, c'est l'**annulation** (le « retour sur trace »).

## 3. Pas à pas sur un exemple

Tous les groupes de **k = 2** nombres parmi `1..3`. Profondeur = combien de nombres sont déjà
choisis (le niveau dans l'arbre, et le nombre d'appels récursifs ouverts).

| Étape | Action | Profondeur | current | result |
|---|---|---|---|---|
| 1 | choisir 1 | 0 | [1] | [] |
| 2 | choisir 2 | 1 | [1, 2] | [] |
| 3 | taille = k : **sauver** | 2 | [1, 2] | [[1, 2]] |
| 4 | annuler 2 | 1 | [1] | [[1, 2]] |
| 5 | choisir 3 | 1 | [1, 3] | [[1, 2]] |
| 6 | **sauver** | 2 | [1, 3] | [[1, 2], [1, 3]] |
| 7 | annuler 3 | 1 | [1] | [[1, 2], [1, 3]] |
| 8 | annuler 1 | 0 | [] | [[1, 2], [1, 3]] |
| 9 | choisir 2 | 0 | [2] | [[1, 2], [1, 3]] |
| 10 | choisir 3 | 1 | [2, 3] | [[1, 2], [1, 3]] |
| 11 | **sauver** | 2 | [2, 3] | [[1, 2], [1, 3], [2, 3]] |
| 12 | annuler 3 | 1 | [2] | [[1, 2], [1, 3], [2, 3]] |
| 13 | annuler 2 | 0 | [] | [[1, 2], [1, 3], [2, 3]] |
| 14 | choisir 3 | 0 | [3] | [[1, 2], [1, 3], [2, 3]] |
| 15 | annuler 3 | 0 | [] | [[1, 2], [1, 3], [2, 3]] |

Regarde l'étape 14 : depuis `[3]`, il ne reste aucun nombre plus grand, donc cette branche ne
peut jamais atteindre la taille 2. C'était une branche **inutile** : voir l'élagage en
section 6.

`current` est **une seule liste** qui grandit et rétrécit. La pile d'appels se souvient d'où
on en est : quand un appel se termine, on revient dans la boucle du niveau du dessus, qui
essaie son nombre suivant.

## 4. Le modèle, ligne par ligne

```java
static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(n, k, 1, new ArrayList<>(), result);
    return result;
}

static void backtrack(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i <= n; i++) {
        current.add(i);
        backtrack(n, k, i + 1, current, result);
        current.removeLast();
    }
}
```

| Ligne | Pourquoi |
|---|---|
| `if (current.size() == k)` | **cas de base** : la solution est complète. Sans lui, la récursion ne s'arrête jamais |
| `result.add(new ArrayList<>(current))` | sauver une **copie** (voir plus bas) |
| `return` | une solution complète n'a plus de choix à faire |
| `for (int i = start; ...)` | les choix de ce niveau. Partir de `start` évite de prendre `[2, 1]` après `[1, 2]` |
| `current.add(i)` | **choisir** |
| `backtrack(..., i + 1, ...)` | **explorer** : les nombres suivants doivent être plus grands que `i` |
| `current.removeLast()` | **annuler** : remettre `current` comme avant le choix |

### Pourquoi `new ArrayList<>(current)` et pas `current` ?

Il n'existe qu'**une seule** liste `current` pendant toute la recherche. Si on la sauve
directement, le résultat contient trois références vers cette même liste, et à la fin cette
liste est vide :

| Code à la ligne « sauver » | Résultat final |
|---|---|
| `result.add(current)` | `[[], [], []]` |
| `result.add(new ArrayList<>(current))` | `[[1, 2], [1, 3], [2, 3]]` |

## 5. Pourquoi c'est correct

**Invariant** : quand `backtrack` démarre, `current` contient exactement les choix faits sur
le chemin entre la racine de l'arbre et ce nœud. Quand il se termine, `current` est
**exactement comme au départ**, parce que chaque `add` est suivi de son `removeLast`.

Donc la boucle de chaque niveau peut essayer son choix suivant sans risque : le niveau du
dessous a tout remis en ordre derrière lui. Chaque chemin de l'arbre est visité une fois, donc
chaque solution est sauvée une fois.

## 6. Variantes et élagage

Le modèle reste le même. Ce qui change, c'est **quand on sauve** et **quels choix** propose
la boucle :

| Problème | Quand sauver | Choix à chaque niveau |
|---|---|---|
| Groupes d'exactement k (combinaisons) | quand `size == k` | les nombres après le dernier pris (`start = i + 1`) |
| Tous les groupes, de toute taille | à **chaque** nœud, pas seulement aux feuilles | les nombres après le dernier pris |
| Tous les ordres (arrangements) | quand tous les éléments sont utilisés | tous les éléments pas encore utilisés (les suivre avec un `boolean[] used`) |
| Un nombre peut être repris | quand la cible est atteinte | depuis le **même** indice (`start = i`) |
| Entrée avec des doublons | comme d'habitude | trier d'abord, puis sauter `if (i > start && nums[i] == nums[i - 1]) continue;` |
| Casse-têtes (N reines, sudoku) | quand la grille est pleine | seulement les coups qui gardent la grille valide |

**Élaguer**, c'est ne pas entrer dans une branche qui ne peut pas mener à une solution. Ici :
s'il ne reste pas assez de nombres pour atteindre la taille k, on arrête la boucle plus tôt.

```java
for (int i = start; i <= n - (k - current.size()) + 1; i++) {
```

| Recherche | Appels sans élagage | Appels avec élagage |
|---|---|---|
| k = 2 parmi 3 | 7 | 6 |
| k = 8 parmi 10 | 1 013 | 165 |
| k = 18 parmi 20 | 1 048 555 | 1 330 |

La réponse est la même, l'arbre est beaucoup plus petit. L'élagage fait souvent la différence
entre un dépassement de temps et un test qui passe.

### Coût

Coût = **nombre de feuilles × travail par feuille** (copier une solution coûte sa longueur).

| Recherche | Feuilles | n = 10 | n = 20 |
|---|---|---|---|
| Groupes de toute taille | 2ⁿ | 1 024 | environ 1 million |
| Tous les ordres | n! | 3 628 800 | environ 2,4 × 10¹⁸ (impossible) |
| Groupes d'exactement 3 | C(n, 3) | 120 | 1 140 |

C'est pour ça que les problèmes de backtracking viennent avec un **petit n** (≤ 20 pour 2ⁿ,
≤ 10 pour n!).

## 7. Un problème résolu du début à la fin

**Problème (permutations de casse)** : renvoyer toutes les chaînes qu'on peut obtenir en
mettant chaque lettre de `s` en minuscule ou en majuscule. Les chiffres restent tels quels.
`"a1b"` → `["a1b", "a1B", "A1b", "A1B"]`.

1. **Un choix par position** : une lettre a 2 options (minuscule, majuscule), un chiffre en
   a 1.
2. **Complet quand** chaque position est décidée : on sauve.
3. **Annuler** : ici on travaille sur un `char[]` et on **écrase** la position avec l'autre
   casse ; écrire par-dessus joue le rôle de l'annulation. `new String(chars)` est la copie.

Arbre de décision :

```
                    indice 0 : 'a'
              /                    \
           a1b                      A1b
            |  indice 1 : '1'        |
           a1b                      A1b
         /     \   indice 2 : 'b' /     \
       a1b     a1B             A1b     A1B
     sauver   sauver          sauver  sauver
```

| Étape | Action | Indice (profondeur) | chars | result |
|---|---|---|---|---|
| 1 | minuscule a | 0 | a1b | [] |
| 2 | garder 1 | 1 | a1b | [] |
| 3 | minuscule b | 2 | a1b | [] |
| 4 | **sauver** | 3 | a1b | [a1b] |
| 5 | majuscule B | 2 | a1B | [a1b] |
| 6 | **sauver** | 3 | a1B | [a1b, a1B] |
| 7 | majuscule A | 0 | A1B | [a1b, a1B] |
| 8 | garder 1 | 1 | A1B | [a1b, a1B] |
| 9 | minuscule b | 2 | A1b | [a1b, a1B] |
| 10 | **sauver** | 3 | A1b | [a1b, a1B, A1b] |
| 11 | majuscule B | 2 | A1B | [a1b, a1B, A1b] |
| 12 | **sauver** | 3 | A1B | [a1b, a1B, A1b, A1B] |

```java
static List<String> letterCasePermutations(String s) {
    List<String> result = new ArrayList<>();
    build(s.toCharArray(), 0, result);
    return result;
}

static void build(char[] chars, int index, List<String> result) {
    if (index == chars.length) {
        result.add(new String(chars));
        return;
    }
    if (Character.isLetter(chars[index])) {
        chars[index] = Character.toLowerCase(chars[index]);
        build(chars, index + 1, result);
        chars[index] = Character.toUpperCase(chars[index]);
        build(chars, index + 1, result);
    } else {
        build(chars, index + 1, result);
    }
}
```

4. **Coût** : 2^L feuilles (L = nombre de lettres), chacune copiée en O(n) : O(2^L × n) en
   temps, O(n) en espace pour la récursion.

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| `result.add(current)` | toutes les solutions sauvées sont vides à la fin | `result.add(new ArrayList<>(current))` |
| Oublier `current.removeLast()` | des solutions de plus en plus longues, mélangées | chaque `add` a son `removeLast` après l'appel |
| Pas de cas de base, ou un faux | `StackOverflowError` | écrire d'abord le test « complet » |
| Boucle qui part de 0 au lieu de `start` | le même groupe dans plusieurs ordres : `[1, 2]` et `[2, 1]` | partir de `start`, passer `i + 1` |
| Passer `start + 1` au lieu de `i + 1` | groupes faux ou répétés | le choix suivant dépend de `i`, celui qu'on vient de prendre |
| Doublons dans l'entrée, sans les sauter | solutions en double | trier, puis sauter les voisins égaux au même niveau |
| Modifier le tableau d'entrée sans le remettre | les branches suivantes voient une entrée cassée | le remettre, comme `current` |
| Pas d'élagage | dépassement de temps sur les plus grosses entrées | arrêter une branche dès qu'elle ne peut plus réussir |

## 9. Comment le reconnaître

- « Renvoyer **toutes** les... / lister toutes les possibilités » : combinaisons,
  sous-ensembles, ordres, chaînes, chemins, grilles.
- L'entrée est **petite** (n ≤ 20, souvent ≤ 10).
- Casse-têtes avec contraintes : sudoku, N reines, chercher un mot dans une grille.
- Si on demande seulement **combien** de façons ou **la meilleure**, et que les mêmes
  sous-problèmes reviennent sans cesse, penser plutôt programmation dynamique (chapitre 07).

## 10. S'entraîner

Exos : [`patterns/backtracking/BacktrackingExercises.java`](../../../src/main/java/com/mastery/interview/patterns/backtracking/BacktrackingExercises.java)

```bash
mvn -Dtest='BacktrackingExercisesTest' test
```

Avant de coder chacun, dessiner le haut de l'arbre de décision sur papier et écrire : **quel
est un choix à chaque niveau ? Quand une solution est-elle complète (sauver) ? Qu'est-ce que
j'annule ? Où commence la boucle ?**
