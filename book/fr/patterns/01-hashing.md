# Pattern 1. Hachage

🇬🇧 [English version](../../en/patterns/01-hashing.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Beaucoup de problèmes demandent : « pour cet élément, existe-t-il **un autre élément** avec
une certaine propriété ? » (la même valeur, le complément d'une cible, les mêmes lettres...).

La force brute répond avec une deuxième boucle :

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[] {i, j};
        }
    }
}
```

Pour chaque élément, la boucle intérieure **recherche** à nouveau dans tout le tableau :
n × n = **O(n²)**. Avec n = 100 000, ça fait 10 milliards de vérifications, environ
10 secondes. Trop lent.

## 2. L'idée clé

Chercher dans un tableau coûte O(n). Chercher dans une `HashMap` ou un `HashSet` coûte **O(1)**.

Donc au lieu de chercher encore et encore, on **retient ce qu'on a déjà vu** dans une
structure de hachage, et on lui pose la question en O(1).

```
 force brute                          hachage
 pour chaque x :                      pour chaque x :
     chercher dans tout le tableau O(n)   interroger la map       O(1)
                                          puis ranger x dedans    O(1)
 total O(n²)                          total O(n)
```

On paie O(n) en **mémoire** pour gagner un facteur n en **temps**. En entretien, cet
échange vaut presque toujours le coup.

## 3. Pas à pas sur un exemple

Trouver deux nombres dont la somme vaut **10** dans `[3, 8, 4, 6]`.

À chaque étape : calculer `need = 10 - x`, chercher `need` dans la map, puis ranger `x → indice`.

| Étape | i | x | need = 10 - x | `need` dans la map ? | Map après l'étape |
|---|---|---|---|---|---|
| 1 | 0 | 3 | 7 | non | {3=0} |
| 2 | 1 | 8 | 2 | non | {3=0, 8=1} |
| 3 | 2 | 4 | 6 | non | {3=0, 8=1, 4=2} |
| 4 | 3 | 6 | 4 | **oui, à l'indice 2** | stop |

Réponse : indices **[2, 3]** (4 + 6 = 10). Chaque élément a été regardé **une seule fois**.

Remarque l'ordre dans la boucle : **d'abord chercher, ensuite ranger**. Si on range
d'abord, `x` peut se trouver lui-même (pour une cible 12 et x = 6, on répondrait à tort [3, 3]).

## 4. Le modèle, ligne par ligne

```java
Map<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int need = target - nums[i];
    if (seen.containsKey(need)) {
        return new int[] {seen.get(need), i};
    }
    seen.put(nums[i], i);
}
return new int[] {-1, -1};
```

| Ligne | Pourquoi |
|---|---|
| `Map<Integer, Integer> seen` | clé = une valeur déjà vue, valeur = où elle était (son indice). Si on a seulement besoin de « vu ou pas », on prend un `HashSet` |
| `int need = target - nums[i]` | la question à poser : « quelle valeur me compléterait ? » |
| `seen.containsKey(need)` | O(1) au lieu d'une boucle |
| `return ... seen.get(need), i` | l'ancien indice est plus petit, donc il vient en premier |
| `seen.put(nums[i], i)` | **après** la vérification, pour qu'un élément ne fasse jamais la paire avec lui-même |

## 5. Pourquoi c'est correct

**Invariant** : au début de l'étape `i`, la map contient exactement les éléments des
indices `0 .. i-1`.

Donc si une paire valide `(j, i)` existe avec `j < i`, quand la boucle arrive à `i`,
l'élément `j` est déjà dans la map et on le trouve. Chaque paire est vérifiée une fois,
depuis son deuxième élément.

## 6. Les trois façons d'utiliser une structure de hachage

| Besoin | Structure | Exemple |
|---|---|---|
| « vu ou pas ? » | `HashSet<T>` | contient un doublon, détection de cycle, cases visitées |
| « où / qu'est-ce que c'était ? » | `HashMap<K, V>` avec un indice ou une valeur | Two Sum (valeur → indice) |
| « combien de fois ? » | `HashMap<K, Integer>` de fréquences, ou `int[26]` pour les lettres | anagrammes, le plus fréquent, premier unique |
| « lesquels vont ensemble ? » | `HashMap<K, List<T>>` avec une **signature** comme clé | regrouper les anagrammes (clé = lettres triées), regrouper par catégorie |

**Choisir la clé**, c'est le vrai savoir-faire. Se demander : « qu'ont en commun deux
éléments qui vont ensemble ? » Des anagrammes ont les mêmes lettres triées. Des points sur
la même droite ont la même pente. Ce point commun, c'est la clé.

## 7. Un problème résolu du début à la fin

**Problème (lettre anonyme)** : peut-on écrire `note` en découpant des lettres dans
`magazine` ? Chaque lettre du magazine ne sert qu'une fois.
`canWrite("aab", "baa")` → `true`, `canWrite("aa", "ab")` → `false`.

1. **Force brute** : pour chaque lettre de la note, chercher dans le magazine et la rayer.
   O(n × m).
2. **Quelle question se répète ?** « Reste-t-il une lettre `c` inutilisée dans le
   magazine ? » C'est un **compte** par lettre.
3. **Structure de hachage** : la fréquence de chaque lettre du magazine (`int[26]` puisqu'il
   n'y a que `a` à `z`).
4. **Algorithme** : compter le magazine, puis pour chaque lettre de la note en prendre une ;
   si le compte passe sous 0, c'est impossible.

| Étape | Lettre | Comptes après (a, b) | OK ? |
|---|---|---|---|
| compter le magazine `"baa"` | | a=2, b=1 | |
| lettre 1 de la note | a | a=1, b=1 | oui |
| lettre 2 de la note | a | a=0, b=1 | oui |
| lettre 3 de la note | b | a=0, b=0 | oui → `true` |

```java
static boolean canWrite(String note, String magazine) {
    int[] count = new int[26];
    for (char c : magazine.toCharArray()) {
        count[c - 'a']++;
    }
    for (char c : note.toCharArray()) {
        count[c - 'a']--;
        if (count[c - 'a'] < 0) {
            return false;
        }
    }
    return true;
}
```

5. **Coût** : O(n + m) en temps, O(1) en espace (26 compteurs, quelle que soit la taille de l'entrée).

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| Ranger avant de vérifier | un élément fait la paire avec lui-même | vérifier, **puis** `put` |
| `map.get(k)` sur une clé absente, déballé en `int` | `NullPointerException` | `getOrDefault(k, 0)` ou `containsKey` |
| `int[26]` avec des majuscules ou des espaces | `ArrayIndexOutOfBoundsException` | passer en minuscules d'abord, ou utiliser une `HashMap<Character, Integer>` |
| Objet modifiable comme clé (`List`, tableau) | la map ne le retrouve plus | utiliser un `String` ou un record comme clé |
| `int[]` comme clé | deux tableaux égaux sont deux clés différentes | `Arrays.toString(arr)` ou une `List<Integer>` |
| Dire « toujours O(1) » | faux dans le pire cas | « O(1) en moyenne » |

## 9. Comment le reconnaître

- « Y a-t-il une paire / un doublon / un complément... ? » dans une entrée **non triée**.
- « Compter », « fréquence », « le plus courant », « premier unique ».
- « Regrouper les ... qui ont le même ... »
- Ta force brute a une **boucle intérieure qui cherche** quelque chose.

Si le tableau est **trié**, penser d'abord aux deux pointeurs (O(1) en mémoire).

## 10. S'entraîner

Exos : [`patterns/hashing/HashingExercises.java`](../../../src/main/java/com/mastery/interview/patterns/hashing/HashingExercises.java)

```bash
mvn -Dtest='HashingExercisesTest' test
```

Avant de coder chaque exo, écrire sur papier : **qu'est-ce que je range ? quelle est la
clé ? quelle question je pose à la map à chaque étape ?**
