# Pattern 10. Manipulation de bits

🇬🇧 [English version](../../en/patterns/10-bits.md) · ⬅️ [Tous les patterns](../03-patterns.md)

## 1. Le problème qu'il résout

Certains problèmes portent sur les **bits** d'un nombre : le bit 3 est-il à 1 ? Combien de
bits diffèrent ? Inverser les bits. Ranger 20 drapeaux oui/non dans un seul `int`. Trouver une
valeur « sans espace en plus ».

### D'abord, le binaire en 2 minutes

Un nombre s'écrit en base 2 : chaque position est une puissance de 2, lue depuis la droite.

```
  13 =  1    1    0    1
        8    4    2    1      ->  8 + 4 + 1 = 13
      bit3 bit2 bit1 bit0
```

Le bit 0 est celui tout à droite (le bit « le plus bas »). Un `int` en Java a **32 bits**.

Les **nombres négatifs** utilisent le **complément à deux** : pour obtenir `-x`, on inverse
tous les bits de `x`, puis on ajoute 1. Le bit tout à gauche est le signe (1 = négatif). Sur
8 bits :

| Valeur | 8 bits |
|---|---|
| 5 | `00000101` |
| -5 | `11111011` (inverser `00000101` → `11111010`, + 1) |
| -1 | `11111111` (que des 1) |
| 127 | `01111111` (plus grand positif sur 8 bits) |
| -128 | `10000000` (plus petit négatif sur 8 bits) |

En Java, `~5 + 1` donne `-5`.

### La force brute

Transformer le nombre en `String` avec `Integer.toBinaryString`, travailler sur les
caractères, puis reconvertir. Ça crée des chaînes, c'est lent, et ça devient confus avec les
négatifs (`Integer.toBinaryString(-1)` donne 32 fois le chiffre 1).

## 2. L'idée clé

Java a des opérateurs qui agissent sur **tous les bits à la fois**, en une seule instruction
du processeur. En en combinant quelques-uns, on peut lire, mettre à 1, mettre à 0 ou inverser
n'importe quel bit en **O(1)**, sans chaîne ni tableau.

Les opérateurs, avec `a = 12` (`1100`) et `b = 10` (`1010`) :

| Opérateur | Nom | Règle pour chaque bit | Exemple | Résultat |
|---|---|---|---|---|
| `a & b` | ET | 1 si **les deux** valent 1 | `1100 & 1010` | `1000` (8) |
| `a \| b` | OU | 1 si **au moins un** vaut 1 | `1100 \| 1010` | `1110` (14) |
| `a ^ b` | OU exclusif (XOR) | 1 s'ils sont **différents** | `1100 ^ 1010` | `0110` (6) |
| `~a` | NON | inverse chaque bit | `~1100` | `...11110011` (-13) |
| `a << 1` | décalage à gauche | décale vers la gauche, ajoute 0 à droite | `1100 << 1` | `11000` (24), comme × 2 |
| `a >> 2` | décalage à droite | décale vers la droite, garde le signe | `1100 >> 2` | `0011` (3), comme ÷ 4 |
| `-16 >> 2` | décalage à droite d'un négatif | remplit avec des 1 à gauche | | `-4` |
| `-16 >>> 28` | décalage à droite non signé | remplit toujours avec des 0 à gauche | | `15` |

```
   1100        1100        1100
 & 1010      | 1010      ^ 1010
 ------      ------      ------
   1000        1110        0110
```

## 3. Pas à pas sur un exemple

**Inverser les 8 bits les plus bas** de `n = 13` (`00001101`). Attendu : `10110000` (176).

À chaque étape : prendre le bit le plus bas de `n` (`n & 1`), le pousser dans `result` par la
droite (`result << 1 | bit`), puis l'enlever de `n` (`n >>> 1`).

| Étape i | bit = n & 1 | result après (8 bits) | result | n après |
|---|---|---|---|---|
| 0 | 1 | `00000001` | 1 | `0110` (6) |
| 1 | 0 | `00000010` | 2 | `0011` (3) |
| 2 | 1 | `00000101` | 5 | `0001` (1) |
| 3 | 1 | `00001011` | 11 | `0000` (0) |
| 4 | 0 | `00010110` | 22 | `0000` (0) |
| 5 | 0 | `00101100` | 44 | `0000` (0) |
| 6 | 0 | `01011000` | 88 | `0000` (0) |
| 7 | 0 | `10110000` | **176** | `0000` (0) |

Les bits sortent de `n` par la droite et entrent dans `result` par la droite aussi, donc leur
ordre est inversé. 8 étapes, quelle que soit la valeur : O(1).

## 4. Le modèle, ligne par ligne

L'outil de base : lire, tester, mettre à 1, mettre à 0 ou inverser le bit `k` avec un
**masque** `1 << k` (un nombre dont seul le bit `k` vaut 1).

```java
int bit = (x >> k) & 1;
boolean on = (x & (1 << k)) != 0;
x = x | (1 << k);
x = x & ~(1 << k);
x = x ^ (1 << k);
```

| Ligne | Ce qu'elle fait | Exemple avec `x = 10` (`1010`) |
|---|---|---|
| `(x >> k) & 1` | **lire** le bit k (0 ou 1) : l'amener en position 0, ne garder que lui | k = 1 → `1`, k = 2 → `0` |
| `(x & (1 << k)) != 0` | **tester** le bit k | k = 3 → `true` |
| `x \| (1 << k)` | **mettre à 1** le bit k | k = 0 → `1011` (11) |
| `x & ~(1 << k)` | **mettre à 0** le bit k (`~masque` a tous ses bits à 1 sauf k) | k = 3 → `0010` (2) |
| `x ^ (1 << k)` | **inverser** le bit k | k = 2 → `1110` (14) |

Pour parcourir tous les bits d'un `int` :

```java
for (int k = 0; k < 32; k++) {
    int bit = (n >>> k) & 1;
}
```

## 5. Pourquoi c'est correct

Chaque opérateur travaille **position par position**, indépendamment : le bit k de `a & b`
ne dépend que du bit k de `a` et du bit k de `b`. Donc un masque avec un seul 1 en position k
touche la position k et **laisse tous les autres bits intacts** (`x | 0 = x`, `x & 1 = x`,
`x ^ 0 = x`).

Pour la boucle d'inversion, l'**invariant** est : après l'étape `i`, `result` contient les
`i + 1` bits les plus bas du `n` de départ, dans l'ordre inverse, et `n` contient les bits pas
encore utilisés.

## 6. Les astuces à connaître

| Astuce | Code | Exemple | Pourquoi ça marche |
|---|---|---|---|
| x est-il impair ? | `(x & 1) == 1` | `13 & 1` → `1`, `12 & 1` → `0` | le bit 0 est la seule puissance de 2 impaire |
| Enlever le bit à 1 le plus bas | `x & (x - 1)` | `1100 & 1011` → `1000` | `x - 1` inverse le 1 le plus bas et tous les 0 à sa droite |
| Garder seulement le bit à 1 le plus bas | `x & -x` | `12 & -12` → `4` (`0100`) | le complément à deux de x a le même 1 le plus bas |
| Le XOR annule les paires | `a ^ a = 0`, `a ^ 0 = a`, l'ordre ne compte pas | `5 ^ 3 ^ 5` → `3` | les deux 5 s'annulent, voir plus bas |
| Multiplier / diviser par 2^k | `x << k`, `x >> k` | `12 << 1` → `24`, `12 >> 2` → `3` | chaque position est une puissance de 2 |
| Que des 1 sous le bit k | `(1 << k) - 1` | k = 4 → `1111` (15) | `10000 - 1 = 01111` |

Le XOR en binaire, pas à pas :

```
   101   (5)
 ^ 011   (3)
 -----
   110   (6)
 ^ 101   (5)
 -----
   011   (3)
```

**Un masque comme ensemble** : avec n ≤ 20 éléments, un `int` de `0` à `(1 << n) - 1` peut
représenter « quels éléments sont choisis » : bit k à 1 = élément k choisi.

## 7. Un problème résolu du début à la fin

**Problème (bits alternés)** : `n > 0`. Renvoyer `true` si deux bits voisins ne sont jamais
égaux. `10` (`1010`) → `true`, `11` (`1011`) → `false`, `5` (`101`) → `true`, `7` (`111`) →
`false`.

1. **Force brute** : parcourir les bits et comparer chacun avec le suivant. Ça marche, mais
   il existe une astuce sans aucune boucle.
2. **Idée** : décaler `n` d'un cran (`n >> 1`) pour mettre chaque bit sous son voisin. Faire
   le XOR : là où les voisins diffèrent, on obtient 1. Alterné veut dire **que des 1**.
3. **Que des 1 ?** Un nombre comme `1111` plus 1 donne `10000` : ils n'ont aucun bit en
   commun, donc `x & (x + 1) == 0`.

| n | n en binaire | n >> 1 | x = n ^ (n >> 1) | x + 1 | x & (x + 1) | Réponse |
|---|---|---|---|---|---|---|
| 10 | `1010` | `0101` | `1111` | `10000` | 0 | `true` |
| 11 | `1011` | `0101` | `1110` | `01111` | 14 | `false` |
| 5 | `0101` | `0010` | `0111` | `01000` | 0 | `true` |
| 7 | `0111` | `0011` | `0100` | `00101` | 4 | `false` |

```java
static boolean hasAlternatingBits(int n) {
    int x = n ^ (n >> 1);
    return (x & (x + 1)) == 0;
}
```

4. **Coût** : O(1) en temps, O(1) en espace.

## 8. Bugs classiques

| Bug | Symptôme | Correction |
|---|---|---|
| `x & 1 == 0` | ne compile pas : `==` passe avant `&` | toujours mettre des parenthèses : `(x & 1) == 0` |
| `while (n != 0) n >>= 1;` avec un n négatif | boucle infinie : `>>` rajoute des 1 à gauche | utiliser `>>>` |
| `1 << 32` | donne `1`, pas 2³² (le décalage est pris modulo 32) | utiliser `1L << k` avec un `long` |
| `1 << 31` | négatif (`-2147483648`) : c'est le bit de signe | attention dès qu'on utilise le bit 31 |
| `x % 2 == 1` pour tester impair | `false` pour `-3` (`-3 % 2` vaut `-1`) | `(x & 1) == 1` |
| Croire que `~x` inverse « seulement les bits utiles » | `~5` vaut `-6`, les 32 bits sont inversés | combiner avec un masque : `~x & ((1 << k) - 1)` |
| Afficher pour vérifier | `Integer.toBinaryString` enlève les zéros à gauche | compléter avec des 0, ou compter les bits depuis la droite |

## 9. Comment le reconnaître

- « Sans espace en plus » ou « en O(1) en mémoire » avec des valeurs qui vont par paires.
- « Puissance de 2 », « pair / impair », « compter les bits à 1 », « inverser les bits »,
  « combien de bits diffèrent ».
- Des drapeaux oui/non, des permissions, un petit ensemble d'éléments (n ≤ 20) : un **masque**.
- Multiplier ou diviser vite par 2.

## 10. S'entraîner

Exos : [`patterns/bits/BitExercises.java`](../../../src/main/java/com/mastery/interview/patterns/bits/BitExercises.java)

```bash
mvn -Dtest='BitExercisesTest' test
```

Avant de coder chacun, écrire sur papier : **les nombres de l'exemple en binaire, quel bit
(ou quels bits) je regarde, et quel opérateur le garde, l'inverse ou l'enlève.**
