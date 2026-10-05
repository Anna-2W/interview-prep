# F02. Array (tableau)

🇬🇧 [English version](../../en/foundations/02-array.md)

## 1. C'est quoi

Un tableau est une rangée de cases de **taille fixe**, toutes du **même type**, côte à côte
en mémoire. Chaque case a un indice qui commence à **0**.

```
 int[] nums = {4, 8, 15, 16};
 indice:  0  1   2   3          nums.length = 4 (sans parenthèses : c'est un champ)
```

- **Taille fixée à la création.** Impossible d'ajouter une cinquième case. Besoin de
  grandir ? Utiliser une `List` (F03).
- **Lire ou écrire par indice coûte O(1)** : l'ordinateur saute directement à la case.
- Un tableau est **modifiable** : on peut changer le contenu d'une case.

## 2. Créer un tableau

```java
int[] a = {4, 8, 15, 16};            // avec des valeurs
int[] b = new int[5];                // 5 cases, toutes à 0
String[] c = new String[3];          // 3 cases, toutes à null
boolean[] d = new boolean[2];        // toutes à false
int[][] grid = new int[3][4];        // 3 lignes, 4 colonnes
```

Valeurs par défaut : `0` pour les nombres, `false` pour `boolean`, `'\u0000'` pour `char`,
`null` pour les objets.

## 3. Opérations de base

| Opération | Code | Temps |
|---|---|---|
| Lire une case | `a[2]` | O(1) |
| Écrire une case | `a[2] = 99;` | O(1) |
| Taille | `a.length` | O(1) |
| Chercher une valeur (non trié) | boucle sur toutes les cases | O(n) |
| Insérer / supprimer au milieu | impossible sur place, on décale ou on copie | O(n) |

## 4. La boîte à outils `Arrays` (`java.util.Arrays`)

| Méthode | Ce qu'elle fait | Exemple | Temps |
|---|---|---|---|
| `Arrays.toString(a)` | Texte lisible | `[4, 8, 15]` | O(n) |
| `Arrays.sort(a)` | Trie sur place, croissant | `{3,1,2}` → `{1,2,3}` | O(n log n) |
| `Arrays.binarySearch(a, x)` | Cherche `x` dans un tableau **trié** | indice, ou négatif si absent | O(log n) |
| `Arrays.fill(a, x)` | Met `x` dans toutes les cases | `fill(a, -1)` | O(n) |
| `Arrays.copyOf(a, len)` | Copie, coupée ou complétée par des 0 | `copyOf({1,2}, 3)` → `{1,2,0}` | O(n) |
| `Arrays.copyOfRange(a, from, to)` | Copie une tranche (**to exclu**) | `copyOfRange({1,2,3}, 0, 2)` → `{1,2}` | O(n) |
| `Arrays.equals(a, b)` | Même contenu | `equals({1,2}, {1,2})` → `true` | O(n) |
| `Arrays.asList(...)` | Vue en `List` de taille fixe | voir F03 | O(1) |
| `Arrays.stream(a)` | Stream : `sum()`, `max()`... | `Arrays.stream(a).sum()` | O(n) |

## 5. Parcourir un tableau

```java
int[] nums = {4, 8, 15, 16};

for (int i = 0; i < nums.length; i++) {   // besoin de l'indice (pour écrire, ou comparer les voisins)
    nums[i] = nums[i] * 2;
}

for (int n : nums) {                       // lecture seule
    System.out.println(n);
}

for (int i = nums.length - 1; i >= 0; i--) {   // à l'envers
    System.out.println(nums[i]);
}
```

Le **for-each** donne une copie de la valeur : `n = 0;` dedans ne change pas le tableau.

## 6. À quoi ça sert en entretien

- La moitié des exos de code prennent un `int[]` en entrée.
- Compter : `int[] count = new int[26]` pour les lettres, `count[c - 'a']++`.
- Les techniques classiques qui démarrent ici : **deux pointeurs** (un à chaque bout),
  **fenêtre glissante**, **sommes préfixes**, **recherche dichotomique** sur un tableau trié.
- Les grilles (`int[][]`, `char[][]`) pour les problèmes de labyrinthe et d'îles.

## 7. Pièges

1. **`ArrayIndexOutOfBoundsException`.** Les indices valides vont de `0` à `length - 1`.
   `i <= nums.length` dans une boucle est le bug classique : il faut `<`.
2. **`==` et `equals` comparent les références.** `a == b` et `a.equals(b)` valent `false`
   pour deux tableaux au même contenu. Utiliser `Arrays.equals(a, b)`.
3. **Affichage.** `System.out.println(a)` affiche un truc du genre `[I@1b6d3586`.
   Utiliser `Arrays.toString(a)`.
4. **Copier, ce n'est pas `=`.** `int[] b = a;` ne copie rien : `b` et `a` sont le même
   tableau. Modifier `b[0]` modifie `a[0]`. Utiliser `a.clone()` ou `Arrays.copyOf`.
5. **Modifier l'entrée.** Si une méthode trie ou modifie le tableau reçu, l'appelant le
   voit. Quand on demande de « renvoyer un nouveau tableau », on ne touche pas l'entrée.
6. **Tableau vide.** `nums[0]` sur `new int[0]` plante. À demander en entretien.
7. **Dépassement d'entier.** Additionner de gros `int` peut déborder sans prévenir.
   Utiliser `long` si besoin.

## 8. Exercices

On code dans [`ArrayExercises.java`](../../../src/main/java/com/mastery/interview/foundations/ArrayExercises.java).

```bash
mvn -Dtest='ArrayExercisesTest$E01Sum' test   # un exercice
mvn -Dtest=ArrayExercisesTest test            # tout le chapitre
```

| # | Exercice | Ce que ça entraîne |
|---|---|---|
| 01 | `sum({1, 2, 3})` → `6` | Boucle de base |
| 02 | `max({3, 9, 2})` → `9` | Partir de `nums[0]`, pas de 0 |
| 03 | `contains({1, 2, 3}, 2)` → `true` | Recherche linéaire, sortie anticipée |
| 04 | `indexOf({5, 7, 5}, 5)` → `0` | Renvoyer l'indice, `-1` si absent |
| 05 | `countEven({1, 2, 4})` → `2` | Modulo `%` |
| 06 | `doubled({1, 2})` → `{2, 4}` | Nouveau tableau, entrée intacte |
| 07 | `average({1, 2})` → `1.5` | Division entière contre division décimale |
| 08 | `reversed({1, 2, 3})` → `{3, 2, 1}` | Indice depuis la fin |
| 09 | `isSorted({1, 2, 2, 5})` → `true` | Comparer les voisins `i` et `i + 1` |
| 10 | `concat({1, 2}, {3})` → `{1, 2, 3}` | Dimensionner le résultat, copier en deux fois |
