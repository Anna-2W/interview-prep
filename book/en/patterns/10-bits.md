# Pattern 10. Bit manipulation

🇫🇷 [Version française](../../fr/patterns/10-bits.md) · ⬅️ [All patterns](../03-patterns.md)

## 1. The problem it solves

Some problems are about the **bits** of a number: is bit 3 on? How many bits differ? Reverse
the bits. Store 20 yes/no flags in one `int`. Find a value "without extra space".

### First, binary in 2 minutes

A number is written in base 2: each position is a power of 2, read from the right.

```
  13 =  1    1    0    1
        8    4    2    1      ->  8 + 4 + 1 = 13
      bit3 bit2 bit1 bit0
```

Bit 0 is the rightmost one (the "lowest" bit). An `int` in Java has **32 bits**.

**Negative numbers** use **two's complement**: to get `-x`, flip every bit of `x`, then add 1.
The leftmost bit is the sign (1 = negative). On 8 bits:

| Value | 8 bits |
|---|---|
| 5 | `00000101` |
| -5 | `11111011` (flip `00000101` → `11111010`, + 1) |
| -1 | `11111111` (all ones) |
| 127 | `01111111` (biggest positive on 8 bits) |
| -128 | `10000000` (smallest negative on 8 bits) |

In Java, `~5 + 1` gives `-5`.

### The brute force

Turn the number into a `String` with `Integer.toBinaryString`, then work on characters, then
parse back. It allocates strings, is slow, and gets confusing with negatives
(`Integer.toBinaryString(-1)` is 32 ones).

## 2. The key idea

Java has operators that work on **all the bits at once**, in one processor instruction. With a
few of them combined, you can read, set, clear or flip any bit in **O(1)**, without any string
or array.

The operators, with `a = 12` (`1100`) and `b = 10` (`1010`):

| Operator | Name | Rule per bit | Example | Result |
|---|---|---|---|---|
| `a & b` | AND | 1 if **both** are 1 | `1100 & 1010` | `1000` (8) |
| `a \| b` | OR | 1 if **at least one** is 1 | `1100 \| 1010` | `1110` (14) |
| `a ^ b` | XOR | 1 if they are **different** | `1100 ^ 1010` | `0110` (6) |
| `~a` | NOT | flip every bit | `~1100` | `...11110011` (-13) |
| `a << 1` | left shift | move bits left, add 0 on the right | `1100 << 1` | `11000` (24), same as × 2 |
| `a >> 2` | right shift | move bits right, keep the sign | `1100 >> 2` | `0011` (3), same as ÷ 4 |
| `-16 >> 2` | right shift on a negative | fills with 1 on the left | | `-4` |
| `-16 >>> 28` | unsigned right shift | always fills with 0 on the left | | `15` |

```
   1100        1100        1100
 & 1010      | 1010      ^ 1010
 ------      ------      ------
   1000        1110        0110
```

## 3. Step by step on an example

**Reverse the lowest 8 bits** of `n = 13` (`00001101`). Expected: `10110000` (176).

At each step: take the lowest bit of `n` (`n & 1`), push it into `result` from the right
(`result << 1 | bit`), then drop it from `n` (`n >>> 1`).

| Step i | bit = n & 1 | result after (8 bits) | result | n after |
|---|---|---|---|---|
| 0 | 1 | `00000001` | 1 | `0110` (6) |
| 1 | 0 | `00000010` | 2 | `0011` (3) |
| 2 | 1 | `00000101` | 5 | `0001` (1) |
| 3 | 1 | `00001011` | 11 | `0000` (0) |
| 4 | 0 | `00010110` | 22 | `0000` (0) |
| 5 | 0 | `00101100` | 44 | `0000` (0) |
| 6 | 0 | `01011000` | 88 | `0000` (0) |
| 7 | 0 | `10110000` | **176** | `0000` (0) |

The bits leave `n` from the right and enter `result` from the right too, so their order is
reversed. 8 steps, whatever the value: O(1).

## 4. The template, line by line

The basic tool: read, test, set, clear or flip bit `k` with a **mask** `1 << k` (a number with
only bit `k` on).

```java
int bit = (x >> k) & 1;
boolean on = (x & (1 << k)) != 0;
x = x | (1 << k);
x = x & ~(1 << k);
x = x ^ (1 << k);
```

| Line | What it does | Example with `x = 10` (`1010`) |
|---|---|---|
| `(x >> k) & 1` | **read** bit k (0 or 1): bring it to position 0, keep only it | k = 1 → `1`, k = 2 → `0` |
| `(x & (1 << k)) != 0` | **test** bit k | k = 3 → `true` |
| `x \| (1 << k)` | **set** bit k to 1 | k = 0 → `1011` (11) |
| `x & ~(1 << k)` | **clear** bit k to 0 (`~mask` has every bit on except k) | k = 3 → `0010` (2) |
| `x ^ (1 << k)` | **flip** bit k | k = 2 → `1110` (14) |

To go through every bit of an `int`:

```java
for (int k = 0; k < 32; k++) {
    int bit = (n >>> k) & 1;
}
```

## 5. Why it is correct

Each operator works **position by position**, independently: bit k of `a & b` only depends on
bit k of `a` and bit k of `b`. So a mask with a single 1 at position k touches position k and
**leaves every other bit unchanged** (`x | 0 = x`, `x & 1 = x`, `x ^ 0 = x`).

For the reverse loop, the **invariant** is: after step `i`, `result` holds the lowest `i + 1`
bits of the original `n`, in reverse order, and `n` holds the bits not used yet.

## 6. The tricks to know

| Trick | Code | Example | Why it works |
|---|---|---|---|
| Is x odd? | `(x & 1) == 1` | `13 & 1` → `1`, `12 & 1` → `0` | bit 0 is the only odd power of 2 |
| Remove the lowest 1 bit | `x & (x - 1)` | `1100 & 1011` → `1000` | `x - 1` flips the lowest 1 and every 0 to its right |
| Keep only the lowest 1 bit | `x & -x` | `12 & -12` → `4` (`0100`) | two's complement of x has the same lowest 1 |
| XOR cancels pairs | `a ^ a = 0`, `a ^ 0 = a`, order does not matter | `5 ^ 3 ^ 5` → `3` | the two 5s cancel, see below |
| Multiply / divide by 2^k | `x << k`, `x >> k` | `12 << 1` → `24`, `12 >> 2` → `3` | each position is a power of 2 |
| All ones below bit k | `(1 << k) - 1` | k = 4 → `1111` (15) | `10000 - 1 = 01111` |

XOR in binary, step by step:

```
   101   (5)
 ^ 011   (3)
 -----
   110   (6)
 ^ 101   (5)
 -----
   011   (3)
```

**Bitmask as a set**: with n ≤ 20 items, an `int` from `0` to `(1 << n) - 1` can represent
"which items are chosen": bit k on = item k chosen.

## 7. A problem solved from start to finish

**Problem (alternating bits)**: `n > 0`. Return `true` if two neighbor bits are never equal.
`10` (`1010`) → `true`, `11` (`1011`) → `false`, `5` (`101`) → `true`, `7` (`111`) → `false`.

1. **Brute force**: loop over the bits and compare each one with the next. Fine, but there is a
   trick with no loop at all.
2. **Idea**: shift `n` by one (`n >> 1`) to put each bit under its neighbor. XOR them: where the
   neighbors differ we get 1. Alternating means **all ones**.
3. **All ones?** A number like `1111` plus 1 gives `10000`: they share no bit, so
   `x & (x + 1) == 0`.

| n | n in binary | n >> 1 | x = n ^ (n >> 1) | x + 1 | x & (x + 1) | Answer |
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

4. **Cost**: O(1) time, O(1) space.

## 8. Common bugs

| Bug | Symptom | Fix |
|---|---|---|
| `x & 1 == 0` | does not compile: `==` runs before `&` | always parenthesize: `(x & 1) == 0` |
| `while (n != 0) n >>= 1;` with a negative n | infinite loop: `>>` keeps adding 1s on the left | use `>>>` |
| `1 << 32` | gives `1`, not 2³² (the shift is taken modulo 32) | use `1L << k` with a `long` |
| `1 << 31` | negative (`-2147483648`): it is the sign bit | be careful when you use bit 31 |
| `x % 2 == 1` to test odd | `false` for `-3` (`-3 % 2` is `-1`) | `(x & 1) == 1` |
| Expecting `~x` to flip "only the useful bits" | `~5` is `-6`, every one of the 32 bits flipped | combine with a mask: `~x & ((1 << k) - 1)` |
| Printing to check | `Integer.toBinaryString` drops the leading zeros | pad it, or count bits from the right |

## 9. How to recognize it

- "Without extra space" or "in O(1) memory" with values that appear in pairs.
- "Power of 2", "odd / even", "count the 1 bits", "reverse the bits", "how many bits differ".
- Yes/no flags, permissions, a small set of items (n ≤ 20): a **bitmask**.
- Multiply or divide by 2 quickly.

## 10. Practice

Exercises: [`patterns/bits/BitExercises.java`](../../../src/main/java/com/mastery/interview/patterns/bits/BitExercises.java)

```bash
mvn -Dtest='BitExercisesTest' test
```

Before coding each one, write on paper: **the numbers of the example in binary, which bit
(or bits) I look at, and which operator keeps, flips or removes it.**
