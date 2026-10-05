# 🎯 Interview Prep

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Tests-JUnit%205-25A162?logo=junit5&logoColor=white" alt="JUnit 5">
  <img src="https://img.shields.io/badge/Approach-TDD-1f6feb" alt="TDD">
  <img src="https://img.shields.io/badge/Languages-EN%20%7C%20FR-blueviolet" alt="EN | FR">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License: MIT">
</p>

🇫🇷 [Version française](README.fr.md)

**An all-in-one, bilingual (EN/FR) preparation kit for software engineering interviews,
with a Java focus.** Algorithms you code until the tests are green, a book that covers every
topic interviewers ask about, system design notes, memos to review the night before, and a
week-by-week roadmap.

Same spirit as [java-mastery](https://github.com/Anna-2W/java-mastery) and
[sql-mastery](https://github.com/Anna-2W/sql-mastery): you learn by making failing tests pass.

---

## 🧭 What is inside

| Part | What it is | Where |
|---|---|---|
| 🗺️ **Roadmap** | 16-week plan, what to study each week, how many problems | [ROADMAP.md](ROADMAP.md) |
| 📖 **The book** | Chapters on DS, algorithms, Java, system design, OS, networking, databases | [book/](book/README.md) |
| 💻 **Exercises** | Java problems with JUnit tests that start red | [src/main/java](src/main/java/com/mastery/interview) |
| 🧠 **Memos** | One-page cheat sheets for the last review before an interview | `memos/` (coming) |
| 🏗️ **System design** | Classic designs (URL shortener, chat, feed...) with a fixed template | `system-design/` (coming) |
| ✅ **Progress tracker** | Every topic as a checkbox, with a priority tag | [PROGRESS.md](PROGRESS.md) |

---

## 💻 How the exercises work

1. **Read** the problem in the Javadoc of the exercise file (EN and FR).
2. **Code** your solution in place of `throw new UnsupportedOperationException("TODO")`.
3. **Run the test**:
   ```bash
   mvn -Dtest=TwoSumTest test   # one exercise
   mvn test                     # everything
   ```
4. **Green?** ✅ Say the complexity out loud, as you would in the interview, then move on.

> **The rule:** a test is red until your code is correct AND fast enough. Some tests use
> large inputs on purpose, so a brute force solution times out.

Requirements: JDK 21+ and Maven.

---

## 🏷️ Priority tags

The topic list is huge. Not everything is worth the same in an interview:

- 🔴 **Must**: asked all the time, you must solve it without help.
- 🟠 **Should**: asked often at mid/senior level, know it well.
- 🟢 **Nice**: rare, read it once so the name does not surprise you.

Study the 🔴 first. A 🟢 topic never comes before an unfinished 🔴 one.

---

## 📅 Status

This repo is built step by step. See the [ROADMAP](ROADMAP.md) for the full plan and the
[book outline](book/README.md) for which chapters are written.

## License

[MIT](LICENSE)
