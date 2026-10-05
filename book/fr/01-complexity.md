# 01. Complexité (Big O)

🇬🇧 [English version](../en/01-complexity.md)

## 1. C'est quoi

La complexité répond à une seule question : **quand l'entrée grossit, à quel point mon code
devient-il plus lent (ou plus gourmand en mémoire) ?**

On ne compte pas des secondes : elles dépendent de la machine. On compte **comment le
nombre d'étapes grandit** avec `n`, la taille de l'entrée.

| Notation | Sens | En pratique |
|---|---|---|
| **O** (grand O) | borne haute : « jamais pire que » | **celle utilisée en entretien**, pour le pire cas |
| **Ω** (Omega) | borne basse : « jamais mieux que » | rare |
| **Θ** (Theta) | borne exacte : à la fois O et Ω | rare |

| Cas | Exemple : chercher `x` dans un tableau non trié |
|---|---|
| Meilleur cas | `x` est le premier élément : 1 étape |
| Pire cas | `x` est absent : `n` étapes → **O(n)** |
| Cas moyen | environ `n / 2` étapes, toujours O(n) |

Quand on te demande « quelle est la complexité ? », donne le **pire cas, en temps et en espace**.

## 2. Les complexités à connaître

De la plus rapide à la plus lente :

| Complexité | Nom | n = 1 000 | Code typique |
|---|---|---|---|
| O(1) | constante | 1 | `a[i]`, `map.get(k)` |
| O(log n) | logarithmique | ~10 | recherche dichotomique, boucle qui divise par 2 |
| O(n) | linéaire | 1 000 | une boucle sur l'entrée |
| O(n log n) | quasi linéaire | ~10 000 | un bon tri (`Arrays.sort`) |
| O(n²) | quadratique | 1 000 000 | deux boucles imbriquées |
| O(2ⁿ) | exponentielle | plus que d'atomes dans l'univers | tous les sous-ensembles, Fibonacci récursif naïf |
| O(n!) | factorielle | encore pire | toutes les permutations |

**Règle pratique** (environ 10⁸ étapes simples par seconde) :

| n jusqu'à | Complexité acceptable |
|---|---|
| 10 | O(n!) |
| 20 | O(2ⁿ) |
| 5 000 | O(n²) |
| 10⁶ | O(n log n) |
| 10⁸ | O(n) |
| plus | O(log n) ou O(1) |

Si l'énoncé dit `n <= 10^5`, O(n²) est trop lent : on attend O(n log n) ou O(n).

## 3. Un exemple par complexité

### O(1)

```java
int first(int[] a) {
    return a[0];
}
```

### O(log n)

```java
int halvings(int n) {
    int steps = 0;
    while (n > 1) {
        n = n / 2;
        steps++;
    }
    return steps;
}
```

`n` est divisé par 2 à chaque tour : 1 000 → 500 → 250 → ... → 1 en environ 10 tours.

### O(n)

```java
int sum(int[] a) {
    int total = 0;
    for (int x : a) {
        total += x;
    }
    return total;
}
```

### O(n log n)

```java
void sortThenPrint(int[] a) {
    Arrays.sort(a);
    for (int x : a) {
        System.out.println(x);
    }
}
```

O(n log n) + O(n) = O(n log n) : le plus gros terme l'emporte.

### O(n²)

```java
boolean hasDuplicate(int[] a) {
    for (int i = 0; i < a.length; i++) {
        for (int j = i + 1; j < a.length; j++) {
            if (a[i] == a[j]) {
                return true;
            }
        }
    }
    return false;
}
```

### O(2ⁿ)

```java
long fib(int n) {
    if (n < 2) {
        return n;
    }
    return fib(n - 1) + fib(n - 2);
}
```

Chaque appel fait 2 appels : l'arbre des appels double à chaque niveau.

## 4. Comment la calculer

| Règle | Exemple | Résultat |
|---|---|---|
| Étapes l'une après l'autre : on **additionne** | une boucle de n, puis une autre boucle de n | O(n + n) = O(n) |
| Étapes l'une dans l'autre : on **multiplie** | une boucle de n dans une boucle de n | O(n × n) = O(n²) |
| On enlève les constantes | 3n + 5 | O(n) |
| On garde seulement le plus gros terme | n² + n + 100 | O(n²) |
| Entrées différentes, lettres différentes | boucle sur `a` (n), dedans boucle sur `b` (m) | O(n × m), **pas** O(n²) |
| Boucle intérieure de taille fixe | `for i < n`, dedans `for j < 10` | O(10n) = O(n) |
| Boucle intérieure de `i` à `n` | n + (n-1) + ... + 1 = n(n+1)/2 | O(n²) |
| Boucle qui multiplie ou divise par 2 | `i = i * 2` | O(log n) |
| Récursion | nombre d'appels × travail par appel | `fib` : 2ⁿ appels × O(1) = O(2ⁿ) |

## 5. Complexité en espace

La mémoire **en plus** utilisée par le code, sans compter l'entrée.

| Code | Espace en plus |
|---|---|
| quelques variables (`int total`) | O(1) |
| un nouveau tableau ou une liste de taille n | O(n) |
| un `HashSet` rempli avec les n éléments | O(n) |
| une grille n × n | O(n²) |
| une récursion de profondeur n | O(n) : chaque appel attend sur la **pile d'appels** |
| une récursion de profondeur log n (dichotomie) | O(log n) |

Très souvent, on **échange de l'espace contre du temps** : un `HashSet` (O(n) en espace)
transforme une recherche en O(n²) en O(n).

## 6. Coût amorti

`ArrayList.add` est en O(1) **amorti** :
- en général il reste de la place : O(1) ;
- parfois le tableau interne est plein : Java recopie tout dans un tableau 1,5 fois plus grand, O(n) ;
- ces copies sont si rares que, réparties sur tous les ajouts, la moyenne reste O(1).

Même idée pour `HashMap.put` quand la map grandit.

## 7. Coûts cachés en Java

| Code qui a l'air en O(1) | Vrai coût | Dans une boucle de n |
|---|---|---|
| `list.contains(x)` | O(n) | O(n²) |
| `list.remove(0)` sur une `ArrayList` | O(n) | O(n²) |
| `list.add(0, x)` sur une `ArrayList` | O(n) | O(n²) |
| `s = s + x` | O(longueur de s) | O(n²) |
| `s.substring(a, b)` | O(b - a) | |
| `linkedList.get(i)` | O(n) | O(n²) |
| `Arrays.sort(a)` | O(n log n) | |
| `map.containsValue(v)` | O(n) | O(n²) |
| `String.equals` | O(longueur) | |

## 8. Récap : ce qu'on a vu de F01 à F06

| Opération | `ArrayList` | `HashMap` / `HashSet` | `TreeMap` / `TreeSet` | `ArrayDeque` | `PriorityQueue` |
|---|---|---|---|---|---|
| Accès par indice | O(1) | | | | |
| Chercher `contains` | O(n) | O(1) | O(log n) | O(n) | O(n) |
| Ajouter | O(1) amorti à la fin | O(1) | O(log n) | O(1) aux deux bouts | O(log n) |
| Supprimer | O(n) | O(1) | O(log n) | O(1) aux deux bouts | O(log n) la tête |
| Min / max | O(n) | O(n) | O(log n) | | O(1) avec peek |

| Autre | Coût |
|---|---|
| `String.charAt`, `length` | O(1) |
| `StringBuilder.append` | O(1) amorti |
| Lecture / écriture dans un tableau | O(1) |
| `Arrays.sort`, `Collections.sort` | O(n log n) |
| `Arrays.binarySearch` | O(log n) |

## 9. Comment le dire en entretien

> « C'est en O(n) en temps, parce que je parcours le tableau une fois et que chaque
> opération sur le `HashSet` est en O(1) en moyenne. C'est O(n) en espace pour le set. La
> force brute était en O(n²) en temps et O(1) en espace : j'ai échangé de la mémoire contre
> de la vitesse. »

Toujours donner : **le temps**, **l'espace**, et **pourquoi** en une phrase.

## 10. Pièges

| Piège | Faux | Juste |
|---|---|---|
| Deux tableaux de tailles différentes | O(n²) | O(n × m) |
| Deux boucles l'une après l'autre | O(n²) | O(n) |
| Boucle intérieure de taille fixe 26 | O(n²) | O(n) |
| Oublier le tri | « une seule boucle : O(n) » | tri + boucle = O(n log n) |
| `contains` sur une liste dans une boucle | O(n) | O(n²) |
| Récursion | « pas de boucle, donc O(1) » | compter les appels |
| Espace de la récursion | O(1) | O(profondeur) |
| Pire cas de `HashMap` | « toujours O(1) » | O(1) en moyenne, O(n) si toutes les clés entrent en collision |
| Ne donner que le temps | « O(n) » | « O(n) en temps, O(1) en espace » |

## 11. Exercices

Deux fichiers, deux sortes d'exos.

### Partie A : quelle est la complexité ? ([`ComplexityQuiz.java`](../../src/main/java/com/mastery/interview/complexity/ComplexityQuiz.java))

Chaque question montre une méthode. Renvoyer sa complexité en **temps** sous forme de chaîne :
`"O(1)"`, `"O(log n)"`, `"O(n)"`, `"O(n log n)"`, `"O(n^2)"`, `"O(n*m)"`, `"O(2^n)"`.
Les espaces et la casse ne comptent pas. Ne pas lire le test avant de répondre : les réponses sont dedans.

```bash
mvn -Dtest=ComplexityQuizTest test
```

| # | Code |
|---|---|
| Q01 | somme d'un tableau |
| Q02 | premier élément |
| Q03 | toutes les paires |
| Q04 | `i = i * 2` |
| Q05 | deux boucles l'une après l'autre |
| Q06 | boucle sur `a` dans une boucle sur `b` |
| Q07 | boucle intérieure de taille 26 |
| Q08 | tri, puis une boucle |
| Q09 | `list.contains` dans une boucle |
| Q10 | Fibonacci récursif naïf |

### Partie B : rends-le plus rapide ([`FasterExercises.java`](../../src/main/java/com/mastery/interview/complexity/FasterExercises.java))

Chaque exo donne une **version lente qui marche**. Écrire la version rapide à côté.
Les tests utilisent de grosses entrées avec une limite de 2 secondes : la version lente
échoue, une bonne version passe.

```bash
mvn -Dtest='FasterExercisesTest$E01HasDuplicate' test
mvn -Dtest=FasterExercisesTest test
```

| # | Exercice | Lent | Cible | Idée |
|---|---|---|---|---|
| E01 | `hasDuplicate(int[])` | O(n²) | O(n) | `HashSet` |
| E02 | `countCommon(int[] a, int[] b)` | O(n × m) | O(n + m) | `HashSet` |
| E03 | `joinWords(List<String>)` | O(n²) | O(n) | `StringBuilder` |
| E04 | `maxWindowSum(int[], k)` | O(n × k) | O(n) | fenêtre glissante |
| E05 | `rangeSums(int[], queries)` | O(n × q) | O(n + q) | sommes préfixes |
| E06 | `fib(n)` | O(2ⁿ) | O(n) | retenir les deux dernières valeurs |

---

⬅️ [Sommaire](../README.fr.md) · [README](../../README.fr.md)
