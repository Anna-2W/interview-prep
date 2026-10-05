# 03. Patterns d'algorithmes

🇬🇧 [English version](../en/03-patterns.md)

La plupart des exos d'entretien sont un **pattern** connu déguisé. Le vrai savoir-faire,
c'est de lire l'énoncé et de reconnaître lequel s'applique. Chaque pattern ci-dessous a :
**comment le reconnaître**, **l'idée**, **un modèle de code Java**, **le coût**, et des
**exos** du plus facile au moyen.

## 0. Quel pattern ? (à lire en premier)

| Si l'énoncé dit... | Penser à | Section |
|---|---|---|
| « déjà vu ? », « compter », « paire qui fait X » (non trié) | Hachage | 1 |
| tableau **trié**, paire, « en place », palindrome | Deux pointeurs | 2 |
| « sous-tableau / sous-chaîne **contigu** », « le plus long », « de taille k » | Fenêtre glissante | 3 |
| « somme entre i et j », beaucoup de requêtes, « sous-tableau de somme k » | Sommes préfixes | 4 |
| liste chaînée, cycle, milieu | Pointeurs lent et rapide | 5 |
| tableau trié, « valeur minimale telle que... », réponse dans un intervalle | Recherche dichotomique | 6 |
| « prochain plus grand / plus petit », « combien de jours avant » | Pile monotone | 7 |
| intervalles, réunions, chevauchements | Intervalles | 8 |
| « les k plus grands / fréquents / proches », k listes triées, médiane | Tas | 9 |
| « sans espace en plus », XOR, puissances de 2 | Manipulation de bits | 10 |
| « toutes les combinaisons / sous-ensembles / permutations » | Backtracking | 11 |
| arbre, « niveau par niveau », grille, plus court chemin, dépendances | BFS, DFS, tri topologique, union-find | chapitres 04 et 05 |
| « nombre de façons », « min / max » avec des choix qui se répètent | Programmation dynamique, glouton | chapitre 07 |
| préfixes de mots, autocomplétion | Trie | chapitre 04 |

| Taille de l'entrée | Complexité attendue | Patterns qui collent |
|---|---|---|
| n ≤ 20 | O(2ⁿ) | backtracking |
| n ≤ 5 000 | O(n²) | deux boucles imbriquées, DP simple |
| n ≤ 10⁶ | O(n log n) ou O(n) | tri + deux pointeurs, hachage, fenêtre glissante, tas |
| énorme, ou « en O(log n) » | O(log n) | recherche dichotomique |

---

## Comment apprendre un pattern (à faire pour chacun)

Lire un modèle de code ne suffit pas : il faut **le voir tourner** et **le reconstruire seule**.

| Étape | Ce que tu fais | Temps |
|---|---|---|
| 1. Comprendre le problème | Lire les sections 1 et 2 de la leçon détaillée : pourquoi la force brute est lente, ce que l'astuce change | 10 min |
| 2. Dérouler à la main | Prendre une feuille, recopier l'exemple de la section 3 et remplir le tableau **toi-même**, ligne par ligne, avant de regarder la réponse | 15 min |
| 3. Comprendre chaque ligne | Lire le modèle ligne par ligne (section 4). Pour chaque ligne, se demander : « qu'est-ce qui casse si je l'enlève ? » | 10 min |
| 4. Réécrire de mémoire | Fermer la leçon, écrire le modèle de mémoire dans un fichier vide, comparer | 10 min |
| 5. Faire les exos | Dans l'ordre, à partir de E01. Avant de coder, écrire sur papier ce que tu stockes et ce que représente chaque pointeur | 1 à 2 h |
| 6. Expliquer à voix haute | Expliquer une solution comme en entretien : idée, complexité, pourquoi c'est correct | 5 min |
| 7. Refaire 2 jours après | Refaire l'exo le plus dur de zéro, sans regarder | 20 min |

**Bloquée plus de 25 minutes ?** Reviens à la trace (étape 2) sur une entrée plus petite. La
plupart des bugs apparaissent quand on suit les variables à la main.

---

## 1. Hachage

📖 **Leçon détaillée, pas à pas** : [patterns/01-hashing.md](patterns/01-hashing.md)

**Reconnaître** : « est-ce que je l'ai déjà vu ? », « compter », « regrouper », « paire »
dans une entrée **non triée**.

**Idée** : une `HashMap` ou un `HashSet` répond à « est-ce présent ? » en O(1). Ça supprime
la boucle de recherche intérieure : O(n²) devient O(n).

```java
Map<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int need = target - nums[i];
    if (seen.containsKey(need)) {
        return new int[] {seen.get(need), i};
    }
    seen.put(nums[i], i);
}
```

**Coût** : O(n) en temps, O(n) en espace.

| Classique | Clé à utiliser |
|---|---|
| Vérifier une anagramme | nombre de chaque lettre (`int[26]` ou une map) |
| Regrouper les anagrammes | les lettres du mot triées, comme clé |
| Plus longue suite consécutive | un `HashSet`, ne compter qu'à partir de `x` quand `x - 1` est absent |

**Exos** [`HashingExercises.java`](../../src/main/java/com/mastery/interview/patterns/hashing/HashingExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `isAnagram` | `isAnagram("listen", "silent")` → `true` |
| E02 | `groupAnagrams` | `["eat", "tea", "tan", "ate", "nat"]` → `[[eat, tea, ate], [tan, nat]]` |
| E03 | `longestConsecutive` | `[100, 4, 200, 1, 3, 2]` → `4` (1, 2, 3, 4) en O(n) |

---

## 2. Deux pointeurs

📖 **Leçon détaillée, pas à pas** : [patterns/02-two-pointers.md](patterns/02-two-pointers.md)

**Reconnaître** : tableau **trié**, trouver une paire, inverser ou comparer les deux bouts,
modifier un tableau **en place**.

**Idée** : deux indices avancent l'un vers l'autre (ou dans le même sens), pour que chaque
élément ne soit visité qu'une fois.

```java
int left = 0;
int right = nums.length - 1;
while (left < right) {
    int sum = nums[left] + nums[right];
    if (sum == target) {
        return new int[] {left, right};
    } else if (sum < target) {
        left++;
    } else {
        right--;
    }
}
```

Même sens (lecture / écriture) pour modifier un tableau en place :

```java
int write = 0;
for (int read = 0; read < nums.length; read++) {
    if (nums[read] != 0) {
        nums[write++] = nums[read];
    }
}
```

**Coût** : O(n) en temps, O(1) en espace (plus O(n log n) s'il faut trier d'abord).

**Exos** [`TwoPointersExercises.java`](../../src/main/java/com/mastery/interview/patterns/twopointers/TwoPointersExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `pairWithSum` (trié) | `[1, 2, 4, 7, 11]`, 9 → `[1, 3]` |
| E02 | `moveZeroes` (en place) | `[0, 1, 0, 3, 12]` → `[1, 3, 12, 0, 0]` |
| E03 | `removeDuplicates` (trié, en place) | `[1, 1, 2, 3, 3]` → renvoie `3`, le tableau commence par `[1, 2, 3]` |
| E04 | `maxArea` | `[1, 8, 6, 2, 5, 4, 8, 3, 7]` → `49` |
| E05 | `threeSum` | `[-1, 0, 1, 2, -1, -4]` → `[[-1, -1, 2], [-1, 0, 1]]` |

---

## 3. Fenêtre glissante

📖 **Leçon détaillée, pas à pas** : [patterns/03-sliding-window.md](patterns/03-sliding-window.md)

**Reconnaître** : « sous-tableau / sous-chaîne **contigu** », « le plus long / le plus
court », « de taille k », « au plus k... ».

**Idée** : une fenêtre `[left, right]` glisse sur le tableau. `right` agrandit la fenêtre ;
quand elle ne respecte plus la règle, `left` la rétrécit. Chaque indice entre et sort une
fois : O(n).

```java
int left = 0;
int best = 0;
Map<Character, Integer> count = new HashMap<>();
for (int right = 0; right < s.length(); right++) {
    count.merge(s.charAt(right), 1, Integer::sum);
    while (windowIsInvalid(count)) {
        count.merge(s.charAt(left), -1, Integer::sum);
        left++;
    }
    best = Math.max(best, right - left + 1);
}
```

| Sorte | Taille de la fenêtre | Exemple |
|---|---|---|
| Fixe | toujours `k` | somme max de k consécutifs (chapitre 01, E04) |
| Variable, la plus longue | agrandir, rétrécir quand invalide | plus longue sous-chaîne sans répétition |
| Variable, la plus courte | agrandir jusqu'à valide, puis rétrécir tant que ça reste valide | plus court sous-tableau de somme ≥ cible |

**Coût** : O(n) en temps, O(k) ou O(alphabet) en espace.

**Exos** [`SlidingWindowExercises.java`](../../src/main/java/com/mastery/interview/patterns/slidingwindow/SlidingWindowExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `longestUniqueSubstring` | `"abcabcbb"` → `3` (`"abc"`) |
| E02 | `minSubarrayLength` (nombres positifs) | cible 7, `[2, 3, 1, 2, 4, 3]` → `2` (`[4, 3]`) |
| E03 | `longestOnes` (retourner au plus k zéros) | `[1, 1, 0, 0, 1, 1, 1, 0, 1]`, k = 1 → `5` |
| E04 | `containsPermutation` | `"ab"` dans `"eidbaooo"` → `true` |

---

## 4. Sommes préfixes

📖 **Leçon détaillée, pas à pas** : [patterns/04-prefix-sums.md](patterns/04-prefix-sums.md)

**Reconnaître** : « somme entre i et j », beaucoup de requêtes sur des intervalles,
« nombre de sous-tableaux de somme k », « point d'équilibre ».

**Idée** : `prefix[i]` = somme des `i` premiers éléments. Alors
`somme(i..j) = prefix[j + 1] - prefix[i]` en O(1).

```java
long[] prefix = new long[nums.length + 1];
for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
```

**Avec une map** (compter les sous-tableaux de somme `k`, négatifs autorisés) : en avançant,
le nombre de préfixes précédents égaux à `courant - k` est le nombre de sous-tableaux qui
finissent ici.

```java
Map<Integer, Integer> seen = new HashMap<>();
seen.put(0, 1);
int current = 0;
int count = 0;
for (int x : nums) {
    current += x;
    count += seen.getOrDefault(current - k, 0);
    seen.merge(current, 1, Integer::sum);
}
```

**Coût** : O(n) pour construire, O(1) par requête.

**Exos** [`PrefixSumExercises.java`](../../src/main/java/com/mastery/interview/patterns/prefixsum/PrefixSumExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `runningSum` | `[1, 2, 3, 4]` → `[1, 3, 6, 10]` |
| E02 | `pivotIndex` | `[1, 7, 3, 6, 5, 6]` → `3` (gauche 11 = droite 11) |
| E03 | `countSubarraysWithSum` | `[1, 1, 1]`, k = 2 → `2` |

---

## 5. Pointeurs lent et rapide

📖 **Leçon détaillée, pas à pas** : [patterns/05-fast-slow-pointers.md](patterns/05-fast-slow-pointers.md)

**Reconnaître** : liste chaînée, « cycle », « milieu », « n-ième depuis la fin ».

**Idée** : `slow` avance d'un pas, `fast` de deux. Quand `fast` arrive au bout, `slow` est
au milieu. S'il y a un cycle, ils se rencontrent.

```java
ListNode slow = head;
ListNode fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;
```

**Coût** : O(n) en temps, O(1) en espace.

**Exos** : déjà faits au chapitre 02, [`LinkedListProblems`](../../src/main/java/com/mastery/interview/datastructures/LinkedListProblems.java) E03, E04, E06, E08.

---

## 6. Recherche dichotomique

📖 **Leçon détaillée, pas à pas** : [patterns/06-binary-search.md](patterns/06-binary-search.md)

**Reconnaître** : tableau trié, « en O(log n) », « la valeur minimale telle que... », « la
première position où... ».

**Idée** : regarder le milieu, jeter la moitié qui ne peut pas contenir la réponse.

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

**Dichotomie sur la réponse** : quand la réponse est un nombre dans un intervalle
`[lo, hi]` et que « `x` marche » veut dire « tout `x` plus grand marche aussi », on cherche
le plus petit `x` qui marche.

```java
int lo = 1;
int hi = max;
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

**Coût** : O(log n), ou O(n log(intervalle)) quand `works` coûte O(n).

| Piège | Faux | Juste |
|---|---|---|
| Dépassement | `(left + right) / 2` | `left + (right - left) / 2` |
| Boucle infinie | `lo = mid` avec `while (lo < hi)` | `lo = mid + 1` |
| Bornes | mélanger `<` et `<=` au hasard | choisir un modèle et s'y tenir |

**Exos** [`BinarySearchExercises.java`](../../src/main/java/com/mastery/interview/patterns/binarysearch/BinarySearchExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `search` | `[-1, 0, 3, 5, 9, 12]`, 9 → `4` |
| E02 | `searchInsert` | `[1, 3, 5, 6]`, 2 → `1` |
| E03 | `sqrt` (partie entière, sans `Math.sqrt`) | `8` → `2` |
| E04 | `searchRotated` | `[4, 5, 6, 7, 0, 1, 2]`, 0 → `4` |
| E05 | `minEatingSpeed` | tas `[3, 6, 7, 11]`, 8 heures → `4` |

---

## 7. Pile monotone

📖 **Leçon détaillée, pas à pas** : [patterns/07-monotonic-stack.md](patterns/07-monotonic-stack.md)

**Reconnaître** : « prochain élément plus grand », « prochain plus petit », « combien de
jours avant qu'il fasse plus chaud ».

**Idée** : garder une pile d'**indices** dont les valeurs sont décroissantes. Quand une
valeur plus grande arrive, c'est la réponse pour toutes les valeurs plus petites au sommet :
on les dépile.

```java
int[] answer = new int[nums.length];
Arrays.fill(answer, -1);
Deque<Integer> stack = new ArrayDeque<>();
for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
        answer[stack.pop()] = nums[i];
    }
    stack.push(i);
}
```

**Coût** : O(n) : chaque indice est empilé et dépilé une seule fois.

**Exos** [`MonotonicStackExercises.java`](../../src/main/java/com/mastery/interview/patterns/monotonicstack/MonotonicStackExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `nextGreater` | `[2, 1, 2, 4, 3]` → `[4, 2, 4, -1, -1]` |
| E02 | `dailyTemperatures` | `[73, 74, 75, 71, 69, 72, 76, 73]` → `[1, 1, 4, 2, 1, 1, 0, 0]` |

---

## 8. Intervalles

📖 **Leçon détaillée, pas à pas** : [patterns/08-intervals.md](patterns/08-intervals.md)

**Reconnaître** : des paires `[début, fin]`, réunions, réservations, « chevauchement »,
« fusionner ».

**Idée** : **trier par début**. Ensuite deux intervalles se chevauchent seulement si le
suivant commence avant la fin du courant.

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
List<int[]> merged = new ArrayList<>();
for (int[] current : intervals) {
    if (merged.isEmpty() || merged.getLast()[1] < current[0]) {
        merged.add(current);
    } else {
        merged.getLast()[1] = Math.max(merged.getLast()[1], current[1]);
    }
}
```

**Coût** : O(n log n) pour le tri.

**Exos** [`IntervalExercises.java`](../../src/main/java/com/mastery/interview/patterns/intervals/IntervalExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `canAttendAll` | `[[0, 30], [5, 10], [15, 20]]` → `false` |
| E02 | `merge` | `[[1, 3], [2, 6], [8, 10], [15, 18]]` → `[[1, 6], [8, 10], [15, 18]]` |
| E03 | `insert` (liste triée, sans chevauchement) | `[[1, 3], [6, 9]]` + `[2, 5]` → `[[1, 5], [6, 9]]` |
| E04 | `minMeetingRooms` | `[[0, 30], [5, 10], [15, 20]]` → `2` |

---

## 9. Tas (top K, fusion de K listes, deux tas)

📖 **Leçon détaillée, pas à pas** : [patterns/09-heap.md](patterns/09-heap.md)

**Reconnaître** : « les k plus grands / petits / fréquents / proches », « fusionner k
listes triées », « médiane d'un flux ».

| Sous-pattern | Idée | Coût |
|---|---|---|
| Top K plus grands | tas **min** de taille k : s'il dépasse k, `poll` le plus petit | O(n log k) |
| Fusion de K listes | le tas contient la tête actuelle de chaque liste ; on sort la plus petite, on pousse sa suivante | O(n log k) |
| Deux tas | tas max pour la moitié basse, tas min pour la moitié haute ; la médiane est au sommet | O(log n) par ajout |

```java
PriorityQueue<Integer> heap = new PriorityQueue<>();
for (int x : nums) {
    heap.offer(x);
    if (heap.size() > k) {
        heap.poll();
    }
}
```

**Exos** [`HeapExercises.java`](../../src/main/java/com/mastery/interview/patterns/heap/HeapExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `topKFrequent` | `[1, 1, 1, 2, 2, 3]`, k = 2 → `[1, 2]` |
| E02 | `kClosest` (de l'origine) | `[[1, 3], [-2, 2], [5, 8]]`, k = 1 → `[[-2, 2]]` |
| E03 | `mergeKSorted` | `[[1, 4, 5], [1, 3, 4], [2, 6]]` → `[1, 1, 2, 3, 4, 4, 5, 6]` |
| E04 | `runningMedians` | `[5, 15, 1, 3]` → `[5.0, 10.0, 5.0, 4.0]` |

---

## 10. Manipulation de bits

📖 **Leçon détaillée, pas à pas** : [patterns/10-bits.md](patterns/10-bits.md)

**Reconnaître** : « sans espace en plus », « apparaît une fois alors que les autres
apparaissent deux fois », puissances de 2, « compter les bits à 1 ».

| Astuce | Code | Exemple |
|---|---|---|
| `x ^ x = 0`, `x ^ 0 = x` | faire le XOR de tous les nombres | les paires s'annulent, l'unique reste |
| Bit le plus bas | `x & 1` | `5 & 1` → `1` (impair) |
| Enlever le bit à 1 le plus bas | `x & (x - 1)` | `12 & 11` → `8` |
| Puissance de 2 | `x > 0 && (x & (x - 1)) == 0` | `8` → `true` |
| Décalage | `x >> 1` (divise par 2), `x << 1` (multiplie par 2) | `5 >> 1` → `2` |
| Compter les bits à 1 | `Integer.bitCount(x)` | `bitCount(11)` → `3` |

**Exos** [`BitExercises.java`](../../src/main/java/com/mastery/interview/patterns/bits/BitExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `singleNumber` | `[4, 1, 2, 1, 2]` → `4` |
| E02 | `countOnes` (sans `Integer.bitCount`) | `11` (`1011`) → `3` |
| E03 | `isPowerOfTwo` | `16` → `true`, `6` → `false` |
| E04 | `missingNumber` (0..n, il en manque un) | `[3, 0, 1]` → `2` |

---

## 11. Backtracking

📖 **Leçon détaillée, pas à pas** : [patterns/11-backtracking.md](patterns/11-backtracking.md)

**Reconnaître** : « toutes les combinaisons », « tous les sous-ensembles », « toutes les
permutations », « toutes les solutions valides... ».

**Idée** : construire une solution pas à pas ; à chaque pas **choisir**, **explorer**
(appel récursif), puis **annuler** le choix.

```java
void backtrack(int start, List<Integer> current, List<List<Integer>> result, int[] nums) {
    result.add(new ArrayList<>(current));
    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);
        backtrack(i + 1, current, result, nums);
        current.removeLast();
    }
}
```

**Coût** : exponentiel : O(2ⁿ) sous-ensembles, O(n!) permutations. Ça passe parce que n est petit.

| Piège | Faux | Juste |
|---|---|---|
| Sauver une solution | `result.add(current)` (même liste, modifiée ensuite) | `result.add(new ArrayList<>(current))` |
| Oublier d'annuler | `current.add(x); backtrack(...);` | puis `current.removeLast();` |

**Exos** [`BacktrackingExercises.java`](../../src/main/java/com/mastery/interview/patterns/backtracking/BacktrackingExercises.java)

| # | Exercice | Exemple |
|---|---|---|
| E01 | `subsets` | `[1, 2]` → `[[], [1], [2], [1, 2]]` |
| E02 | `permutations` | `[1, 2, 3]` → 6 permutations |
| E03 | `combinationSum` (réutilisation permise) | `[2, 3, 6, 7]`, 7 → `[[2, 2, 3], [7]]` |
| E04 | `generateParentheses` | `3` → `["((()))", "(()())", "(())()", "()(())", "()()()"]` |

---

## 12. Lancer les tests

```bash
mvn -Dtest='HashingExercisesTest' test
mvn -Dtest='TwoPointersExercisesTest$E05ThreeSum' test
mvn -Dtest='com/mastery/interview/patterns/**/*Test' test
```

Quand un résultat est une liste de listes (groupes d'anagrammes, sous-ensembles,
permutations...), les tests ne tiennent pas compte de l'ordre.

---

⬅️ [Sommaire](../README.fr.md) · [README](../../README.fr.md)
