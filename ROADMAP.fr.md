# 🗺️ Roadmap : 16 semaines pour une meilleure offre

🇬🇧 [English version](ROADMAP.md)

**Objectif :** réussir les entretiens backend Java mid/senior (code, system design,
questions Java pointues, comportemental) et négocier un meilleur salaire.

**Budget :** environ 10 h par semaine. 1 h en semaine, 2 à 3 h le week-end.
Moins de temps ? On étire le plan, mais on ne saute jamais les sujets 🔴.

---

## 🔁 La routine de la semaine

| Quand | Quoi |
|---|---|
| En semaine (1 h) | 1 section d'un chapitre du livre (20 min) + 1 ou 2 exos (40 min) |
| Samedi (2 h) | 3 exos chronométrés à 25 min chacun, comme en vrai entretien |
| Dimanche (1 h) | Refaire de zéro, sans regarder, les exos ratés dans la semaine |
| Toutes les 2 semaines | 1 entretien blanc (un ami, une plateforme entre pairs, ou seule à voix haute avec un chrono) |

**La règle des 25 minutes :** bloquée depuis 25 minutes ? Lire l'indice, puis la solution,
fermer, et refaire le problème 2 jours plus tard. Un exo n'est « fait » que résolu seule.

---

## Phase 0 : Les bases et le point de départ (semaines 0 et 1)

- [ ] Installer le repo et lancer `mvn test`
- [ ] Partie 0, les bases : [String](book/fr/foundations/01-string.md), [Array](book/fr/foundations/02-array.md), [List](book/fr/foundations/03-list.md), [Map](book/fr/foundations/04-map.md), [Set](book/fr/foundations/05-set.md), [Stack et Queue](book/fr/foundations/06-stack-queue.md), tous les exos au vert
- [ ] Résoudre l'échauffement `TwoSum`
- [ ] Résoudre 3 exos faciles chronométrés, noter où ça a bloqué
- [ ] Écrire son pitch d'une minute « parlez-moi de vous » (chapitre 15 du livre)
- [ ] Noter son salaire actuel et la fourchette du marché pour son niveau (dans `private/`, jamais commité)

## Phase 1 : Bases et patterns (semaines 1 à 4)

| Semaine | Livre | Patterns à pratiquer | Exos |
|---|---|---|---|
| 1 | Big O, tableaux, chaînes, hachage | Hachage, deux pointeurs | 10 |
| 2 | Piles, files, deques | Pile, pile monotone, fenêtre glissante | 10 |
| 3 | Listes chaînées | Pointeurs lent et rapide, inversion en place | 10 |
| 4 | Recherche dichotomique, tris | Dichotomie sur la réponse, tri fusion et tri rapide de zéro | 10 |

**Validation :** n'importe quel exo facile en moins de 15 min, complexité expliquée.

## Phase 2 : Algos essentiels (semaines 5 à 8)

| Semaine | Livre | Patterns à pratiquer | Exos |
|---|---|---|---|
| 5 | Arbres, BST, tas | DFS, BFS par niveau, top K avec un tas | 12 |
| 6 | Graphes, union-find, tries | BFS/DFS sur grille, tri topologique, Dijkstra | 12 |
| 7 | Backtracking, glouton | Sous-ensembles, permutations, intervalles | 12 |
| 8 | Programmation dynamique | DP 1D, DP 2D, sac à dos, LCS, distance d'édition | 12 |

**Validation :** un exo moyen en moins de 30 min, 2 fois sur 3.

## Phase 3 : Java en profondeur (semaines 9 et 10)

- Semaine 9 : Java de base, `equals`/`hashCode`, fonctionnement interne des collections
  (`HashMap`, `ArrayList`, `ConcurrentHashMap`), génériques, streams, records, types
  scellés, exceptions
- Semaine 10 : concurrence (threads, `ExecutorService`, `CompletableFuture`, threads
  virtuels, verrous, `volatile`, Java Memory Model), mémoire de la JVM, algos de GC,
  l'essentiel de Spring Boot, tests

**Validation :** répondre aux 50 questions du mémo Java sans notes.

## Phase 4 : Conception (semaines 11 à 13)

- Semaine 11 : POO, SOLID, design patterns (Gang of Four), conception bas niveau (parking,
  ascenseur, cache LRU, rate limiter)
- Semaine 12 : bases du system design (scalabilité, cache, répartition de charge, bases de
  données, réplication, partitionnement, hachage cohérent, files de messages, CAP)
- Semaine 13 : designs classiques : raccourcisseur d'URL, rate limiter, chat, fil
  d'actualité, système de notifications, base clé-valeur distribuée

**Validation :** dérouler un design de 45 minutes à voix haute avec le plan, sans trou.

## Phase 5 : Révision large (semaine 14)

- OS : processus, threads, synchronisation, interblocages, mémoire, ordonnancement
- Réseau : TCP/IP, TCP vs UDP, HTTP, DNS, TLS, « que se passe-t-il quand on tape une URL »
- Bases de données : SQL, jointures, index, transactions, niveaux d'isolation, NoSQL
  (en lien avec [sql-mastery](https://github.com/Anna-2W/sql-mastery))

## Phase 6 : Décrocher l'offre (semaines 15 et 16)

- [ ] Écrire 8 histoires STAR (conflit, échec, leadership, délai serré, désaccord...)
- [ ] 4 entretiens blancs complets (code + design + comportemental)
- [ ] Mettre à jour CV et LinkedIn, postuler d'abord dans une boîte « d'entraînement », puis
      dans les boîtes visées
- [ ] Préparer la négociation : données du marché, son chiffre, son seuil de refus, ne pas
      donner le premier chiffre si on peut l'éviter (chapitre 15 du livre)

---

## 📊 Totaux

| | Nombre |
|---|---|
| Exos de code | ~110 (plus les reprises) |
| Chapitres du livre | 16 |
| System designs | 10 |
| Histoires STAR | 8 |
| Entretiens blancs | 8 ou plus |

Suivi de chaque sujet dans [PROGRESS.md](PROGRESS.md).
