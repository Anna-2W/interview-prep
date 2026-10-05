# 00. Comment se passe un entretien tech

🇬🇧 [English version](../en/00-how-interviews-work.md)

## 1. Le parcours classique

La plupart des boîtes qui recrutent un développeur backend Java suivent une variante de :

| Étape | Durée | Ce qui est évalué |
|---|---|---|
| Appel recruteur | 20 à 30 min | Motivation, prétentions salariales, préavis, adéquation générale |
| Premier test technique | 45 à 60 min | Un ou deux exos de code, ou un quiz Java, parfois un test à la maison |
| Entretiens de code | 1 ou 2 × 45 à 60 min | Structures de données et algos, code propre, communication |
| System design | 45 à 60 min | Savoir concevoir un système qui tient la charge et expliquer les compromis |
| Approfondissement Java / tech | 45 à 60 min | Collections, concurrence, JVM, Spring, bases de données, projets passés |
| Comportemental | 30 à 45 min | Comment on travaille avec les autres : conflit, échec, prise d'initiative |
| Offre | | C'est là que se fait la négociation |

Les petites boîtes fusionnent souvent des étapes. Les grosses boîtes tech ajoutent des tours
de code et de design. Plus le niveau visé est haut, plus le design et le comportemental pèsent.

## 2. Ce que note la personne en face

Elle ne vérifie pas seulement que le code tourne. Une grille d'évaluation type a quatre lignes :

1. **Résolution du problème** : a-t-on compris le problème, trouvé une approche, puis l'a-t-on améliorée ?
2. **Code** : le code est-il correct, lisible, avec de bons noms et rien d'inutile ?
3. **Communication** : a-t-on réfléchi à voix haute, accepté les indices, expliqué les compromis ?
4. **Vérification** : a-t-on testé son propre code avant de dire « fini » ?

Une solution juste livrée en silence est souvent moins bien notée qu'une solution un peu
plus lente mais bien expliquée.

## 3. La méthode en 6 étapes (à appliquer sur chaque exo du repo)

1. **Comprendre.** Reformuler le problème avec ses mots. Demander la taille de l'entrée,
   le cas vide, les doublons, les négatifs, si c'est trié ou non. Écrire 2 exemples.
2. **Reconnaître.** À quel pattern ça ressemble ? Hachage, deux pointeurs, fenêtre
   glissante, BFS, DP... Les patterns sont au chapitre 03.
3. **Force brute d'abord.** La dire à voix haute avec sa complexité, même si elle est
   mauvaise. Ça prouve qu'on sait résoudre le problème et ça donne une base.
4. **Optimiser.** Où est le travail répété ? Une table de hachage, un tri ou un tas
   peut-il l'éliminer ? Valider l'approche avec la personne avant de coder.
5. **Coder.** Des noms clairs, de petites fonctions, pas d'astuce qu'on ne sait pas expliquer.
6. **Tester.** Dérouler ses exemples à la main, puis les cas limites. Annoncer la
   complexité finale en temps et en espace.

Exemple sur l'échauffement `TwoSum` :

- Force brute : tester toutes les paires, O(n²) en temps, O(1) en espace.
- Travail répété : pour chaque nombre, on recherche `cible - x` dans tout le tableau.
- Optimisation : mémoriser les nombres déjà vus dans une `HashMap<valeur, indice>`. Un seul
  passage, O(n) en temps, O(n) en espace.

## 4. Niveaux et salaire : pourquoi cette prépa rapporte

Les boîtes payent selon le **niveau**, pas selon les années d'expérience. C'est l'entretien
qui fixe le niveau.

| Niveau | Ce qui est attendu en entretien |
|---|---|
| Junior | Résout les exos faciles, connaît les bases du langage |
| Mid | Résout seul les exos moyens, connaît bien Java, bases du design |
| Senior | Exos moyens et quelques difficiles, mène un system design, explique les compromis, vraies histoires de responsabilité |
| Staff et au-dessus | Le design et le comportemental dominent : périmètre, influence, choix techniques à long terme |

Monter d'un niveau, c'est en général le plus gros saut de salaire possible. La même
personne peut recevoir une offre mid ou senior selon la façon dont se passent le design et
le comportemental. C'est pour ça que ce repo leur donne autant de place qu'aux algos.

Avant de négocier, toujours connaître :

- la fourchette du marché pour le niveau et la ville (ou le télétravail),
- son chiffre cible et son seuil de refus,
- le package complet : fixe, variable, actions, politique de télétravail, budget formation.

Le chapitre 15 couvre la négociation elle-même.

## 5. Questions posées sur ce chapitre

- « Comment abordez-vous un problème que vous n'avez jamais vu ? »
- « Votre solution est en O(n²). Peut-on faire mieux ? »
- « Comment testeriez-vous cette fonction ? »
- « Qu'est-ce qui change si les données ne tiennent pas en mémoire ? »
