# Pattern 2. Deux pointeurs

🇬🇧 [English version](../../en/patterns/02-two-pointers.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Certains problèmes comparent ou déplacent des éléments **à deux endroits à la fois** dans
un tableau ou une chaîne : les deux bouts d'un palindrome, deux nombres d'un tableau trié, un
filtre « garder / jeter » fait sur place.

La force brute essaie toutes les paires avec deux boucles imbriquées, ou construit un
deuxième tableau :

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        check(nums[i], nums[j]);
    }
}
```

C'est **O(n²)** en temps, ou O(n) de mémoire en plus pour la copie. Les deux pointeurs le
font en **O(n)** en temps et **O(1)** en mémoire.

## 2. L'idée clé

Utiliser **deux indices** qui ne font qu'avancer (jamais reculer). Chaque étape en déplace au
moins un, donc après au plus n étapes ils ont couvert tout le tableau.

Il y a deux formes :

```
 aux deux bouts                        même sens (lecture / écriture)

  left ──▶              ◀── right       write ──▶
  [ 2 | 5 | 7 | 9 | 12 | 15 ]           [ 3 | 2 | 2 | 3 | 4 ]
                                          read ──▶ (toujours devant)
 ils se rejoignent au milieu            read regarde chaque élément,
                                        write marque où va le prochain gardé
```

| Forme | Quand l'utiliser | À chaque étape |
|---|---|---|
| Aux deux bouts | la réponse dépend des **deux extrémités** (tableau trié, palindrome, le plus grand est à un bout) | décider : avancer `left` vers la droite, ou `right` vers la gauche |
| Même sens | on **filtre ou compacte** un tableau sur place | `read` avance toujours ; `write` avance seulement quand on garde un élément |

## 3. Pas à pas sur deux exemples

### Aux deux bouts : les carrés d'un tableau trié

`[-4, -1, 0, 3, 10]` est trié. Renvoyer les carrés, triés : `[0, 1, 9, 16, 100]`.

Le plus grand carré est toujours à **l'un des deux bouts** (un grand négatif ou un grand
positif). Donc on remplit le résultat **par la fin** : comparer les deux bouts, prendre le
plus grand carré, avancer ce pointeur.

| pos | left (valeur) | right (valeur) | left² | right² | On prend | Résultat après | Pointeurs après |
|---|---|---|---|---|---|---|---|
| 4 | 0 (-4) | 4 (10) | 16 | 100 | right | [_, _, _, _, 100] | left 0, right 3 |
| 3 | 0 (-4) | 3 (3) | 16 | 9 | left | [_, _, _, 16, 100] | left 1, right 3 |
| 2 | 1 (-1) | 3 (3) | 1 | 9 | right | [_, _, 9, 16, 100] | left 1, right 2 |
| 1 | 1 (-1) | 2 (0) | 1 | 0 | left | [_, 1, 9, 16, 100] | left 2, right 2 |
| 0 | 2 (0) | 2 (0) | 0 | 0 | right | [0, 1, 9, 16, 100] | fini |

5 étapes pour 5 éléments : O(n), pas besoin de trier.

### Même sens : supprimer une valeur sur place

Supprimer tous les `3` de `[3, 2, 2, 3, 4]` sans nouveau tableau. Renvoyer combien il en reste.

`read` visite chaque élément. Quand l'élément est gardé, on le copie à `write` et on avance `write`.

| read | x | Gardé ? | Tableau après | write après |
|---|---|---|---|---|
| 0 | 3 | non | [3, 2, 2, 3, 4] | 0 |
| 1 | 2 | oui | [2, 2, 2, 3, 4] | 1 |
| 2 | 2 | oui | [2, 2, 2, 3, 4] | 2 |
| 3 | 3 | non | [2, 2, 2, 3, 4] | 2 |
| 4 | 4 | oui | [2, 2, 4, 3, 4] | 3 |

Réponse : **3**, et les 3 premières cases sont `[2, 2, 4]`. Ce qu'il y a après n'a pas d'importance.

## 4. Les modèles, ligne par ligne

### Aux deux bouts

```java
int left = 0;
int right = nums.length - 1;
while (left < right) {
    if (/* c'est le côté gauche qu'il faut traiter */) {
        left++;
    } else {
        right--;
    }
}
```

| Ligne | Pourquoi |
|---|---|
| `left = 0`, `right = n - 1` | partir des deux bouts |
| `while (left < right)` | s'arrêter quand ils se rejoignent ; avec `<=`, l'élément du milieu est aussi traité (utile pour remplir un résultat, comme les carrés) |
| le `if` | le cœur du pattern : une règle qui dit **quel côté on peut jeter** à coup sûr |
| `left++` / `right--` | au moins un pointeur bouge à chaque tour, donc la boucle s'arrête après au plus n tours |

### Même sens (lecture / écriture)

```java
int write = 0;
for (int read = 0; read < nums.length; read++) {
    if (/* garder nums[read] */) {
        nums[write] = nums[read];
        write++;
    }
}
return write;
```

| Ligne | Pourquoi |
|---|---|
| `int write = 0` | prochaine case où va un élément gardé |
| `for (int read ...)` | `read` regarde chaque élément une fois |
| `nums[write] = nums[read]` | copier l'élément gardé vers l'avant ; on a toujours `write <= read`, donc on n'écrase jamais un élément qu'il reste à lire |
| `return write` | nombre d'éléments gardés = longueur de la partie propre |

## 5. Pourquoi c'est correct

**Aux deux bouts (les carrés)** : à chaque étape, tout ce qui est hors de `[left, right]` est
déjà dans le résultat, et le plus grand carré restant est en `left` ou en `right` (le tableau
est trié, donc les valeurs les plus loin de 0 sont aux bouts). Prendre le plus grand est
toujours juste.

**La règle générale** : déplacer un pointeur ne doit **jamais sauter une réponse
possible**. Avant d'écrire le `if`, se demander : « pourquoi puis-je oublier cet élément sans
risque ? » Si on ne sait pas répondre, le pattern ne colle pas (ou il faut d'abord trier le
tableau).

**Même sens** : invariant : `nums[0 .. write-1]` contient exactement les éléments gardés
parmi `nums[0 .. read-1]`, dans leur ordre d'origine.

## 6. Variantes

| Variante | Idée | Exemple |
|---|---|---|
| Deux bouts sur un tableau trié | somme trop petite → `left++`, trop grande → `right--` | paire de somme donnée |
| Deux bouts sur une chaîne | comparer `s[left]` et `s[right]`, avancer les deux | palindrome |
| Deux bouts, garder le meilleur | avancer le pointeur qui limite la réponse | récipient qui contient le plus d'eau |
| Fixer un élément, deux pointeurs sur le reste | une boucle choisit le premier élément, deux pointeurs cherchent les deux autres | triplets de somme donnée |
| Même sens, lecture / écriture | filtrer, compacter, supprimer les doublons sur place | supprimer une valeur, déplacer les zéros |
| Deux tableaux, un pointeur chacun | avancer le pointeur du plus petit élément | fusionner deux tableaux triés, intersection |
| Lent et rapide | l'un avance de 2, l'autre de 1 | milieu d'une liste chaînée, cycle (pattern 5) |

## 7. Un problème résolu du début à la fin

**Problème (palindrome nettoyé)** : `s` est-il un palindrome si on **ignore tout ce qui
n'est pas une lettre ou un chiffre** et **qu'on ignore la casse** ?
`isCleanPalindrome("Race, car!")` → `true`, `isCleanPalindrome("hello")` → `false`.

1. **Force brute** : construire une copie nettoyée, puis la comparer à son inverse. O(n) en
   temps mais O(n) de mémoire en plus.
2. **Deux endroits à la fois ?** Oui : le premier caractère utile doit égaler le dernier
   caractère utile, et ainsi de suite. Deux bouts.
3. **Règle pour avancer** : si `s[left]` n'est pas une lettre ou un chiffre, on le saute
   (`left++`) ; pareil pour `right`. Sinon on compare (en minuscules) ; différent → `false` ;
   égal → on avance les deux.
4. **Trace** sur `"Race, car!"` (indices 0 à 9) :

| left | car. | right | car. | Action |
|---|---|---|---|---|
| 0 | R | 9 | ! | `!` n'est pas une lettre : sauter à droite |
| 0 | R | 8 | r | `r == r` en minuscules : avancer les deux |
| 1 | a | 7 | a | égaux : avancer les deux |
| 2 | c | 6 | c | égaux : avancer les deux |
| 3 | e | 5 | (espace) | sauter à droite |
| 3 | e | 4 | , | sauter à droite |
| 3 | e | 3 | e | `left == right` : stop → `true` |

```java
static boolean isCleanPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
        if (!Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        } else if (!Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        } else {
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
    }
    return true;
}
```

5. **Coût** : O(n) en temps, O(1) en espace.

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| Deux bouts sur un tableau **non trié** | réponses fausses, paires ratées | trier d'abord (O(n log n)), ou utiliser le hachage |
| `while (left <= right)` quand un élément ne doit pas faire la paire avec lui-même | le même élément sert deux fois | `left < right` |
| Une branche qui ne bouge aucun pointeur | boucle infinie | chaque branche doit bouger `left` ou `right` |
| Sauter des caractères sans vérifier les bornes | `StringIndexOutOfBoundsException` | garder `left < right` dans la condition de saut, ou sauter d'un pas par tour comme ci-dessus |
| Même sens : oublier que `write` est la réponse | renvoyer la longueur du tableau | renvoyer `write` |
| Trier alors que l'ordre doit être gardé | ordre de sortie faux | lecture / écriture garde l'ordre ; le tri non |

## 9. Comment le reconnaître

- Le tableau est **trié** (ou on peut le trier) et on cherche une paire.
- « Palindrome », « inverser », « comparer les deux bouts ».
- « **Sur place** », « O(1) en espace », « sans nouveau tableau ».
- « Supprimer / déplacer / garder certains éléments en gardant l'ordre ».
- Deux entrées triées à combiner.

## 10. S'entraîner

Exos : [`patterns/twopointers/TwoPointersExercises.java`](../../../src/main/java/com/mastery/interview/patterns/twopointers/TwoPointersExercises.java)

```bash
mvn -Dtest='TwoPointersExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **quelle forme (deux bouts ou lecture /
écriture) ? où part chaque pointeur ? quelle règle décide quel pointeur avance, et pourquoi
peut-on oublier sans risque l'élément sauté ?**
