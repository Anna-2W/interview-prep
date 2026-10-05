# 00. How tech interviews work

🇫🇷 [Version française](../fr/00-how-interviews-work.md)

## 1. The usual loop

Most companies hiring a Java backend developer run some version of this:

| Step | Length | What they check |
|---|---|---|
| Recruiter call | 20 to 30 min | Motivation, salary expectations, notice period, basic fit |
| Technical screen | 45 to 60 min | One or two coding problems, or a Java quiz, sometimes a take-home |
| Coding rounds | 1 or 2 × 45 to 60 min | Data structures and algorithms, clean code, communication |
| System design | 45 to 60 min | Can you design something that scales, and explain trade-offs |
| Java / tech deep dive | 45 to 60 min | Collections, concurrency, JVM, Spring, databases, your past projects |
| Behavioral | 30 to 45 min | How you work with people: conflict, failure, ownership |
| Offer | | Negotiation happens here |

Smaller companies often merge steps. Big tech companies add more coding and design rounds.
The higher the level, the more weight on design and behavioral.

## 2. What the interviewer writes down

They are not only checking if the code runs. A typical scorecard has four lines:

1. **Problem solving**: did you understand the problem, find an approach, improve it?
2. **Coding**: is the code correct, readable, with good names and no dead weight?
3. **Communication**: did you think out loud, take hints, explain trade-offs?
4. **Verification**: did you test your own code before saying "done"?

A correct solution delivered in silence often scores lower than a slightly slower one that
was explained well.

## 3. The 6-step method (use it on every exercise in this repo)

1. **Understand.** Repeat the problem in your own words. Ask about input size, empty
   input, duplicates, negative numbers, sorted or not. Write 2 examples.
2. **Match.** Which pattern does it look like? Hashing, two pointers, sliding window,
   BFS, DP... The patterns are in chapter 06.
3. **Brute force first.** Say it out loud with its complexity, even if it is bad. It
   proves you can solve the problem and gives a baseline.
4. **Optimize.** Where is the repeated work? Can a hash map, a sort, or a heap remove it?
   Agree on the approach with the interviewer before coding.
5. **Code.** Clean names, small helpers, no clever tricks you cannot explain.
6. **Test.** Walk through your examples by hand, then the edge cases. State the final
   time and space complexity.

Example on the warm-up `TwoSum`:

- Brute force: try every pair, O(n²) time, O(1) space.
- Repeated work: for each number we search for `target - x` in the whole array again.
- Optimization: remember the numbers already seen in a `HashMap<value, index>`. One pass,
  O(n) time, O(n) space.

## 4. Levels and salary: why this prep pays

Companies pay by **level**, not by years of experience. The interview decides the level.

| Level | What they expect in the interview |
|---|---|
| Junior | Solves easy problems, knows the language basics |
| Mid | Solves medium problems alone, knows Java well, basic design |
| Senior | Medium and some hard problems, leads a system design, explains trade-offs, real stories of ownership |
| Staff and above | Design and behavioral dominate: scope, influence, long-term technical choices |

Moving up one level is usually the biggest salary jump you can get. The same person can be
offered mid or senior depending on how the design and behavioral rounds go. That is why this
repo gives those parts as much room as algorithms.

Before negotiating, always know:

- the market range for the level and the city (or remote),
- your target number and your walk-away number,
- the full package: base, bonus, equity, remote policy, training budget.

Chapter 14 covers the negotiation itself.

## 5. Questions interviewers ask about this chapter

- "Walk me through how you would approach a problem you have never seen."
- "Your solution is O(n²). Can we do better?"
- "How would you test this function?"
- "What would change if the input did not fit in memory?"
