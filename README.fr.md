# 🎯 Prépa Entretiens

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Tests-JUnit%205-25A162?logo=junit5&logoColor=white" alt="JUnit 5">
  <img src="https://img.shields.io/badge/Approche-TDD-1f6feb" alt="TDD">
  <img src="https://img.shields.io/badge/Langues-EN%20%7C%20FR-blueviolet" alt="EN | FR">
  <img src="https://img.shields.io/badge/Licence-MIT-yellow" alt="Licence : MIT">
</p>

🇬🇧 [English version](README.md)

**Un kit tout-en-un, bilingue (EN/FR), pour préparer les entretiens de développeur, centré
sur Java.** Des algos à coder jusqu'à ce que les tests passent au vert, un livre qui couvre
tous les sujets demandés en entretien, des notes de system design, des mémos à relire la
veille, et une roadmap semaine par semaine.

Même esprit que [java-mastery](https://github.com/Anna-2W/java-mastery) et
[sql-mastery](https://github.com/Anna-2W/sql-mastery) : on apprend en faisant passer des
tests rouges au vert.

---

## 🧭 Contenu

| Partie | C'est quoi | Où |
|---|---|---|
| 🗺️ **Roadmap** | Plan sur 16 semaines, quoi étudier chaque semaine, combien d'exos | [ROADMAP.fr.md](ROADMAP.fr.md) |
| 📖 **Le livre** | Chapitres structures de données, algos, Java, system design, OS, réseau, BDD | [book/](book/README.fr.md) |
| 💻 **Exercices** | Problèmes Java avec des tests JUnit qui démarrent en rouge | [src/main/java](src/main/java/com/mastery/interview) |
| 🧠 **Mémos** | Fiches d'une page pour la dernière révision avant l'entretien | `memos/` (à venir) |
| 🏗️ **System design** | Designs classiques (raccourcisseur d'URL, chat, fil d'actu...) avec un plan fixe | `system-design/` (à venir) |
| ✅ **Suivi** | Chaque sujet en case à cocher, avec une priorité | [PROGRESS.md](PROGRESS.md) |

---

## 💻 Comment marchent les exercices

1. **Lire** l'énoncé dans la Javadoc du fichier d'exercice (EN et FR).
2. **Coder** sa solution à la place de `throw new UnsupportedOperationException("TODO")`.
3. **Lancer le test** :
   ```bash
   mvn -Dtest=TwoSumTest test   # un exercice
   mvn test                     # tout
   ```
4. **Vert ?** ✅ Dire la complexité à voix haute, comme en entretien, puis passer au suivant.

> **La règle :** un test reste rouge tant que le code n'est pas correct ET assez rapide.
> Certains tests utilisent exprès de grosses entrées, donc une force brute ne passe pas.

Prérequis : JDK 21+ et Maven.

---

## 🏷️ Priorités

La liste des sujets est énorme. Tout ne vaut pas pareil en entretien :

- 🔴 **Indispensable** : tombe tout le temps, à résoudre sans aide.
- 🟠 **Important** : fréquent en mid/senior, à bien connaître.
- 🟢 **Bonus** : rare, à lire une fois pour ne pas être surpris par le nom.

On commence par les 🔴. Un sujet 🟢 ne passe jamais avant un 🔴 pas fini.

---

## 📅 Avancement

Ce repo se construit étape par étape. Voir la [ROADMAP](ROADMAP.fr.md) pour le plan complet
et le [sommaire du livre](book/README.fr.md) pour les chapitres déjà écrits.

## Licence

[MIT](LICENSE)
