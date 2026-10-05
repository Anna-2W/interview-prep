# 🎯 Prépa Entretiens

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Tests-JUnit%205-25A162?logo=junit5&logoColor=white" alt="JUnit 5">
  <img src="https://img.shields.io/badge/Approche-TDD-1f6feb" alt="TDD">
  <img src="https://img.shields.io/badge/Langues-EN%20%7C%20FR-blueviolet" alt="EN | FR">
  <img src="https://img.shields.io/badge/Licence-MIT-yellow" alt="Licence : MIT">
</p>

🇬🇧 [English version](README.md)

Se préparer aux entretiens de développeur Java, étape par étape : lire un chapitre, faire
ses exos jusqu'à ce que les tests passent au vert, passer au suivant.

---

## 🚀 Par où commencer

```bash
git clone https://github.com/Anna-2W/interview-prep.git
cd interview-prep
mvn -Dtest=StringExercisesTest test
```

Tous les tests sont rouges au départ. C'est normal : le but est de les faire passer au vert.

---

## 📚 Partie 0 : Les bases

Le tout début : les classes Java qu'on utilise dans chaque entretien, toutes leurs méthodes
avec un exemple, et 10 exos faciles par chapitre.

| # | Chapitre | Exos | Lancer les tests |
|---|---|---|---|
| F01 | String · [FR](book/fr/foundations/01-string.md) · [EN](book/en/foundations/01-string.md) | [StringExercises.java](src/main/java/com/mastery/interview/foundations/StringExercises.java) | `mvn -Dtest=StringExercisesTest test` |
| F02 | Array · [FR](book/fr/foundations/02-array.md) · [EN](book/en/foundations/02-array.md) | [ArrayExercises.java](src/main/java/com/mastery/interview/foundations/ArrayExercises.java) | `mvn -Dtest=ArrayExercisesTest test` |
| F03 | List · [FR](book/fr/foundations/03-list.md) · [EN](book/en/foundations/03-list.md) | [ListExercises.java](src/main/java/com/mastery/interview/foundations/ListExercises.java) | `mvn -Dtest=ListExercisesTest test` |
| F04 | Map · [FR](book/fr/foundations/04-map.md) · [EN](book/en/foundations/04-map.md) | [MapExercises.java](src/main/java/com/mastery/interview/foundations/MapExercises.java) | `mvn -Dtest=MapExercisesTest test` |
| F05 | Set · [FR](book/fr/foundations/05-set.md) · [EN](book/en/foundations/05-set.md) | [SetExercises.java](src/main/java/com/mastery/interview/foundations/SetExercises.java) | `mvn -Dtest=SetExercisesTest test` |
| F06 | Stack et Queue · [FR](book/fr/foundations/06-stack-queue.md) · [EN](book/en/foundations/06-stack-queue.md) | [StackQueueExercises.java](src/main/java/com/mastery/interview/foundations/StackQueueExercises.java) | `mvn -Dtest=StackQueueExercisesTest test` |

## 🧠 Partie 1 : Les sujets d'entretien

| # | Chapitre | Exos | Lancer les tests |
|---|---|---|---|
| 01 | Complexité (Big O) · [FR](book/fr/01-complexity.md) · [EN](book/en/01-complexity.md) | [ComplexityQuiz.java](src/main/java/com/mastery/interview/complexity/ComplexityQuiz.java) · [FasterExercises.java](src/main/java/com/mastery/interview/complexity/FasterExercises.java) | `mvn -Dtest=ComplexityQuizTest test` · `mvn -Dtest=FasterExercisesTest test` |
| 02 | Structures de données codées à la main | à venir | |

La liste complète des chapitres est dans le [sommaire du livre](book/README.fr.md).

---

Chaque chapitre de la partie 0 a les mêmes parties :

1. C'est quoi
2. Comment le créer
3. Toutes les méthodes, chacune avec un exemple et son résultat
4. Comment le parcourir
5. À quoi ça sert en entretien
6. Les pièges (faux / juste)
7. Les 10 exos

---

## 💻 Comment faire un exo

1. Ouvrir le fichier d'exos, par exemple `StringExercises.java`.
2. Chaque méthode a une ligne de commentaire avec des exemples :
   ```java
   // E01  lastChar("hello") -> 'o'
   public static char lastChar(String s) {
       throw new UnsupportedOperationException("TODO");
   }
   ```
3. Remplacer la ligne `throw` par son code.
4. Lancer seulement cet exo :
   ```bash
   mvn -Dtest='StringExercisesTest$E01LastChar' test
   ```
5. Vert ✅ : exo suivant. Rouge ❌ : lire le message d'erreur, il montre la valeur attendue.

Prérequis : JDK 21+ et Maven.

---

## 🗂️ Plan du repo

```
interview-prep/
├── README.fr.md               ← vous êtes ici
├── ROADMAP.fr.md              plan sur 16 semaines
├── PROGRESS.md                chaque sujet en case à cocher
├── book/
│   ├── README.fr.md           sommaire complet
│   ├── fr/                    chapitres de la partie 1, en français (01-complexity.md...)
│   ├── fr/foundations/        chapitres de la partie 0, en français
│   ├── en/                    chapitres de la partie 1, en anglais
│   └── en/foundations/        chapitres de la partie 0, en anglais
└── src/
    ├── main/java/.../         les exos, un package par chapitre (on écrit ici)
    └── test/java/.../         les tests (ne pas toucher)
```

| Document | C'est quoi |
|---|---|
| [Sommaire du livre](book/README.fr.md) | Tous les chapitres, écrits ou prévus |
| [Roadmap](ROADMAP.fr.md) | Quoi étudier chaque semaine |
| [Suivi](PROGRESS.md) | Cocher ce qu'on sait, avec les priorités 🔴 🟠 🟢 |

---

## Licence

[MIT](LICENSE)
