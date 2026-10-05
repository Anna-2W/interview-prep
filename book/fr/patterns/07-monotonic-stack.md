# Pattern 7. Pile monotone

🇬🇧 [English version](../../en/patterns/07-monotonic-stack.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Beaucoup de problèmes demandent, pour **chaque** élément : « quel est le **prochain** (ou le
précédent) élément plus grand (ou plus petit) que moi ? »

La force brute répond avec une deuxième boucle qui avance vers la droite :

```java
for (int i = 0; i < n; i++) {
    answer[i] = -1;
    for (int j = i + 1; j < n; j++) {
        if (nums[j] < nums[i]) {
            answer[i] = nums[j];
            break;
        }
    }
}
```

Sur une mauvaise entrée (par exemple un tableau trié dans l'ordre croissant, quand on cherche
le prochain plus petit), la boucle intérieure va jusqu'au bout à chaque fois : n × n =
**O(n²)**.

## 2. L'idée clé

Lire le tableau **une seule fois**, de gauche à droite, et garder une **pile des éléments qui
attendent encore leur réponse**.

Quand un nouvel élément `x` arrive, il **est** la réponse de tous les éléments en attente
qu'il bat. Ces éléments sont au sommet de la pile : on les dépile, on leur donne leur
réponse, puis on empile `x` (il attend à son tour).

Comme on dépile toujours les éléments que `x` bat, les valeurs qui restent dans la pile sont
toujours **triées** (monotones). C'est de là que vient le nom.

```
 prochain plus petit, valeurs en attente dans la pile (bas -> sommet) croissantes

 pile : [3, 4]      nouveau x = 1
         1 < 4  -> 4 reçoit sa réponse (1), on dépile
         1 < 3  -> 3 reçoit sa réponse (1), on dépile
 pile : [1]         1 attend sa propre réponse
```

On met des **indices** dans la pile, pas des valeurs : avec un indice, on peut lire la valeur
(`nums[i]`) **et** savoir où écrire la réponse (`answer[i]`) ou calculer une distance.

## 3. Pas à pas sur un exemple

Prochain élément **plus petit** pour `[5, 3, 4, 1, 2]` (-1 s'il n'y en a pas).
Règle : tant que la valeur au sommet est **plus grande** que `x`, on la dépile et on lui
donne `x` comme réponse. La pile est écrite du bas vers le sommet, sous la forme `indice(valeur)`.

| i | x | Dépilés (et leur réponse) | Pile après l'étape |
|---|---|---|---|
| 0 | 5 | aucun | [0(5)] |
| 1 | 3 | 0(5) reçoit 3 | [1(3)] |
| 2 | 4 | aucun (3 n'est pas plus grand que 4) | [1(3), 2(4)] |
| 3 | 1 | 2(4) reçoit 1, puis 1(3) reçoit 1 | [3(1)] |
| 4 | 2 | aucun | [3(1), 4(2)] |

À la fin, les indices encore dans la pile (3 et 4) n'ont jamais trouvé plus petit : ils
gardent -1. Réponse : **[3, 1, 1, -1, -1]**.

Regarde la colonne de la pile : du bas vers le sommet, les valeurs sont toujours **croissantes**.

## 4. Le modèle de code, ligne par ligne

```java
int[] answer = new int[nums.length];
Arrays.fill(answer, -1);
Deque<Integer> stack = new ArrayDeque<>();
for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
        answer[stack.pop()] = nums[i];
    }
    stack.push(i);
}
return answer;
```

| Ligne | Pourquoi |
|---|---|
| `Arrays.fill(answer, -1)` | la valeur par défaut pour les éléments qui ne trouvent jamais de réponse |
| `Deque<Integer> stack` | contient les **indices** des éléments qui attendent encore |
| `!stack.isEmpty() && ...` | tester le vide d'abord, `peek()` sur une deque vide renvoie `null` |
| `nums[stack.peek()] > nums[i]` | « est-ce que `x` bat l'élément du sommet ? » Cette comparaison choisit la variante (voir section 6) |
| `answer[stack.pop()] = nums[i]` | l'élément du sommet a trouvé sa réponse : on l'écrit et on le retire |
| `while`, pas `if` | `x` peut être la réponse de **plusieurs** éléments en attente à la suite |
| `stack.push(i)` | `x` attend maintenant sa propre réponse |

**Pourquoi O(n) alors qu'il y a une boucle dans une boucle ?** Chaque indice est empilé
**une fois** et dépilé **au plus une fois**. Sur toute l'exécution, la boucle `while` fait au
plus n dépilements au total, pas n par élément. Total : O(n) en temps, O(n) en espace pour la pile.

## 5. Pourquoi c'est correct

**Invariant** : à tout moment, la pile contient exactement les indices déjà lus qui
**n'ont pas encore** trouvé leur réponse, et leurs valeurs sont triées (croissantes du bas
vers le sommet pour « prochain plus petit »).

- Quand `x` arrive, les éléments en attente qu'il bat sont tous au sommet (la pile est
  triée), donc dépiler par le sommet les trouve tous, et seulement eux.
- `x` est le **premier** élément à leur droite qui les bat : si un élément plus tôt les avait
  battus, ils auraient déjà été dépilés.

## 6. Les variantes

Seules deux choses changent : la **comparaison** dans le `while`, et **quand** on lit la réponse.

| Problème | Pile (bas → sommet) | On dépile tant que | La réponse est |
|---|---|---|---|
| prochain plus grand | décroissante | `nums[sommet] < x` | `x`, au moment où on dépile |
| prochain plus petit | croissante | `nums[sommet] > x` | `x`, au moment où on dépile |
| précédent plus grand | décroissante | `nums[sommet] <= x` | le sommet **après** les dépilements, avant d'empiler `x` |
| précédent plus petit | croissante | `nums[sommet] >= x` | le sommet **après** les dépilements, avant d'empiler `x` |
| distance au lieu de la valeur | pareil | pareil | `i - indice` au lieu de `nums[i]` |
| tableau circulaire | pareil | pareil | faire aller `i` de 0 à `2n - 1` et utiliser `i % n` |

`<` contre `<=` compte quand il y a des valeurs égales : décider si « plus grand » veut dire
strictement plus grand, et le vérifier sur un exemple avec des doublons.

## 7. Un problème résolu du début à la fin

**Problème (stock span)** : pour chaque jour, renvoyer le **span** : le nombre de jours
consécutifs jusqu'à aujourd'hui (aujourd'hui compris) où le prix était **inférieur ou égal**
au prix du jour.
`prices = [100, 80, 60, 70, 60, 75, 85]` → `[1, 1, 1, 2, 1, 4, 6]`.

1. **Force brute** : pour chaque jour, reculer tant que le prix est ≤ celui du jour. O(n²)
   sur des prix croissants.
2. **Reformuler** : le span s'arrête au **précédent prix plus grand**. Donc
   `span = i - (indice du précédent plus grand)`, ou `i + 1` s'il n'y en a pas.
3. **Pattern** : précédent plus grand → pile décroissante, on dépile tant que
   `price[sommet] <= price[i]`, et le sommet restant est le précédent plus grand.

| i | Prix | Dépilés | Précédent plus grand (sommet) | Span | Pile après |
|---|---|---|---|---|---|
| 0 | 100 | aucun | aucun | 0 + 1 = 1 | [0(100)] |
| 1 | 80 | aucun | 0(100) | 1 - 0 = 1 | [0(100), 1(80)] |
| 2 | 60 | aucun | 1(80) | 2 - 1 = 1 | [0(100), 1(80), 2(60)] |
| 3 | 70 | 2(60) | 1(80) | 3 - 1 = 2 | [0(100), 1(80), 3(70)] |
| 4 | 60 | aucun | 3(70) | 4 - 3 = 1 | [0(100), 1(80), 3(70), 4(60)] |
| 5 | 75 | 4(60), 3(70) | 1(80) | 5 - 1 = 4 | [0(100), 1(80), 5(75)] |
| 6 | 85 | 5(75), 1(80) | 0(100) | 6 - 0 = 6 | [0(100), 6(85)] |

```java
static int[] stockSpan(int[] prices) {
    int[] span = new int[prices.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < prices.length; i++) {
        while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
            stack.pop();
        }
        span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
        stack.push(i);
    }
    return span;
}
```

4. **Coût** : O(n) en temps (chaque jour empilé et dépilé une fois), O(n) en espace.

## 8. Les bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| Empiler les valeurs au lieu des indices | impossible d'écrire `answer[j]` ou de calculer une distance | empiler `i`, lire `nums[i]` |
| `if` au lieu de `while` | un seul élément en attente reçoit sa réponse | `while` |
| `peek()` avant de tester le vide | `NullPointerException` (déballage de `null`) | `!stack.isEmpty() &&` d'abord |
| Mauvaise comparaison | on obtient « prochain plus petit » au lieu de « prochain plus grand » | écrire la règle en mots, puis le signe |
| `<` contre `<=` | réponses fausses avec des valeurs égales | tester un exemple avec des doublons |
| Oublier la valeur par défaut | 0 au lieu de -1 pour les éléments jamais dépilés | `Arrays.fill(answer, -1)` |
| Utiliser `Stack` | marche mais lent et vieux | `Deque<Integer> stack = new ArrayDeque<>()` |

## 9. Comment le reconnaître

- « Prochain plus grand / prochain plus petit / précédent plus grand / précédent plus petit ».
- « Combien de jours avant... », « jusqu'où en arrière... », « span ».
- Pour chaque élément, il faut l'élément **le plus proche** d'un côté qui est plus grand ou plus petit.
- La force brute a une boucle intérieure qui **avance** à gauche ou à droite et s'arrête au
  premier élément qui bat l'élément courant.

## 10. S'entraîner

Exos : [`patterns/monotonicstack/MonotonicStackExercises.java`](../../../src/main/java/com/mastery/interview/patterns/monotonicstack/MonotonicStackExercises.java)

```bash
mvn -Dtest='MonotonicStackExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **prochain ou précédent ? plus grand ou plus
petit ? donc quelle comparaison fait dépiler ? j'écris la valeur ou la distance ?** Puis
dérouler la pile sur l'exemple, comme le tableau de la section 3.
