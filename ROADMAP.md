# 🗺️ Roadmap: 16 weeks to a better offer

🇫🇷 [Version française](ROADMAP.fr.md)

**Target:** pass mid/senior Java backend interviews (coding, system design, Java deep dive,
behavioral) and negotiate a higher salary.

**Budget:** about 10 h per week. 1 h on weekdays, 2 to 3 h on the weekend.
If you have less time, stretch the plan; never skip the 🔴 topics.

---

## 🔁 The weekly routine

| When | What |
|---|---|
| Weekdays (1 h) | 1 chapter section of the book (20 min) + 1 or 2 exercises (40 min) |
| Saturday (2 h) | 3 exercises timed at 25 min each, as in a real interview |
| Sunday (1 h) | Redo the exercises you failed this week, from scratch, without looking |
| Every 2 weeks | 1 mock interview (a friend, a peer platform, or out loud alone with a timer) |

**The 25-minute rule:** stuck for 25 minutes? Read the hint, then the solution, then close it
and redo the problem 2 days later. A problem counts as "done" only when solved alone.

---

## Phase 0: Foundations and baseline (weeks 0 and 1)

- [ ] Set up the repo and run `mvn test`
- [ ] Part 0 foundations: [String](book/en/foundations/01-string.md), [Array](book/en/foundations/02-array.md), [List](book/en/foundations/03-list.md), [Map](book/en/foundations/04-map.md), all exercises green
- [ ] Solve the warm-up `TwoSum`
- [ ] Solve 3 easy problems timed, write down where you got stuck
- [ ] Write your 1-minute "tell me about yourself" pitch (book chapter 14)
- [ ] Note your current salary and the market range for your level (in `private/`, never committed)

## Phase 1: Foundations and patterns (weeks 1 to 4)

| Week | Book | Patterns to practice | Problems |
|---|---|---|---|
| 1 | Big O, arrays, strings, hashing | Hashing, two pointers | 10 |
| 2 | Stacks, queues, deques | Stack, monotonic stack, sliding window | 10 |
| 3 | Linked lists | Fast and slow pointers, in-place reversal | 10 |
| 4 | Binary search, sorting | Binary search on the answer, merge and quick sort from scratch | 10 |

**Exit check:** you solve any easy problem in under 15 min and explain its complexity.

## Phase 2: Core algorithms (weeks 5 to 8)

| Week | Book | Patterns to practice | Problems |
|---|---|---|---|
| 5 | Trees, BST, heaps | DFS, BFS by level, top K with a heap | 12 |
| 6 | Graphs, union-find, tries | BFS/DFS on grids, topological sort, Dijkstra | 12 |
| 7 | Backtracking, greedy | Subsets, permutations, intervals | 12 |
| 8 | Dynamic programming | 1D DP, 2D DP, knapsack, LCS, edit distance | 12 |

**Exit check:** you solve a medium problem in under 30 min, 2 times out of 3.

## Phase 3: Java deep dive (weeks 9 and 10)

- Week 9: core Java, `equals`/`hashCode`, collections internals (`HashMap`, `ArrayList`,
  `ConcurrentHashMap`), generics, streams, records, sealed types, exceptions
- Week 10: concurrency (threads, `ExecutorService`, `CompletableFuture`, virtual threads,
  locks, `volatile`, Java Memory Model), JVM memory, GC algorithms, Spring Boot essentials,
  testing

**Exit check:** you answer the 50 questions of the Java memo without notes.

## Phase 4: Design (weeks 11 to 13)

- Week 11: OOP, SOLID, design patterns (Gang of Four), low-level design (parking lot,
  elevator, LRU cache, rate limiter)
- Week 12: system design fundamentals (scalability, caching, load balancing, databases,
  replication, partitioning, consistent hashing, queues, CAP)
- Week 13: classic designs: URL shortener, rate limiter, chat, news feed, notification
  system, distributed key-value store

**Exit check:** you run a 45-minute design out loud using the template, without blanks.

## Phase 5: Breadth review (week 14)

- OS: processes, threads, synchronization, deadlocks, memory, scheduling
- Networking: TCP/IP, TCP vs UDP, HTTP, DNS, TLS, "what happens when you type a URL"
- Databases: SQL, joins, indexes, transactions, isolation levels, NoSQL
  (pairs with [sql-mastery](https://github.com/Anna-2W/sql-mastery))

## Phase 6: Get the offer (weeks 15 and 16)

- [ ] Write 8 STAR stories (conflict, failure, leadership, tight deadline, disagreement...)
- [ ] 4 full mock interviews (coding + design + behavioral)
- [ ] Update CV and LinkedIn, apply to a "practice" company first, then the target ones
- [ ] Prepare the negotiation: market data, your number, your walk-away number, never give
      the first figure if you can avoid it (book chapter 14)

---

## 📊 Totals

| | Count |
|---|---|
| Coding problems | ~110 (plus redos) |
| Book chapters | 15 |
| System designs | 10 |
| STAR stories | 8 |
| Mock interviews | 8 or more |

Track every topic in [PROGRESS.md](PROGRESS.md).
