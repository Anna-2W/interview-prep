# 🎯 Interview Prep

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Tests-JUnit%205-25A162?logo=junit5&logoColor=white" alt="JUnit 5">
  <img src="https://img.shields.io/badge/Approach-TDD-1f6feb" alt="TDD">
  <img src="https://img.shields.io/badge/Languages-EN%20%7C%20FR-blueviolet" alt="EN | FR">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License: MIT">
</p>

🇫🇷 [Version française](README.fr.md)

Prepare for Java developer interviews, step by step: read a chapter, solve its exercises
until the tests are green, move to the next one.

---

## 🚀 Start here

```bash
git clone https://github.com/Anna-2W/interview-prep.git
cd interview-prep
mvn -Dtest=StringExercisesTest test
```

Every test is red at the start. That is normal: your job is to make them green.

---

## 📚 Part 0: Foundations

The very beginning: the Java classes you use in every interview, all their methods with an
example, and 10 easy exercises per chapter.

| # | Chapter | Exercises | Run the tests |
|---|---|---|---|
| F01 | String · [EN](book/en/foundations/01-string.md) · [FR](book/fr/foundations/01-string.md) | [StringExercises.java](src/main/java/com/mastery/interview/foundations/StringExercises.java) | `mvn -Dtest=StringExercisesTest test` |
| F02 | Array · [EN](book/en/foundations/02-array.md) · [FR](book/fr/foundations/02-array.md) | [ArrayExercises.java](src/main/java/com/mastery/interview/foundations/ArrayExercises.java) | `mvn -Dtest=ArrayExercisesTest test` |
| F03 | List · [EN](book/en/foundations/03-list.md) · [FR](book/fr/foundations/03-list.md) | [ListExercises.java](src/main/java/com/mastery/interview/foundations/ListExercises.java) | `mvn -Dtest=ListExercisesTest test` |
| F04 | Map · [EN](book/en/foundations/04-map.md) · [FR](book/fr/foundations/04-map.md) | [MapExercises.java](src/main/java/com/mastery/interview/foundations/MapExercises.java) | `mvn -Dtest=MapExercisesTest test` |
| F05 | Set | coming | |
| F06 | Stack and Queue | coming | |

Every chapter has the same sections:

1. What it is
2. How to create it
3. All the methods, each with an example and its result
4. How to go through it
5. What it is used for in interviews
6. The traps (wrong / right)
7. The 10 exercises

---

## 💻 How to do an exercise

1. Open the exercise file, for example `StringExercises.java`.
2. Each method has one comment line with examples:
   ```java
   // E01  lastChar("hello") -> 'o'
   public static char lastChar(String s) {
       throw new UnsupportedOperationException("TODO");
   }
   ```
3. Replace the `throw` line with your code.
4. Run only this exercise:
   ```bash
   mvn -Dtest='StringExercisesTest$E01LastChar' test
   ```
5. Green ✅: go to the next one. Red ❌: read the error message, it shows the expected value.

Requirements: JDK 21+ and Maven.

---

## 🗂️ Repository map

```
interview-prep/
├── README.md                  ← you are here
├── ROADMAP.md                 16-week plan
├── PROGRESS.md                every topic as a checkbox
├── book/
│   ├── README.md              full table of contents
│   ├── en/foundations/        Part 0 chapters, English
│   └── fr/foundations/        Part 0 chapters, French
└── src/
    ├── main/java/.../foundations/   exercises (you write here)
    └── test/java/.../foundations/   tests (do not touch)
```

| Document | What it is |
|---|---|
| [Book table of contents](book/README.md) | Every chapter, written or planned |
| [Roadmap](ROADMAP.md) | What to study each week |
| [Progress](PROGRESS.md) | Tick what you know, with priorities 🔴 🟠 🟢 |

---

## License

[MIT](LICENSE)
