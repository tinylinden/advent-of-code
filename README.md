# Advent of Code

[![aoc.jpg](banner.jpg)](https://unsplash.com/photos/ySNkCkdKyTY)  
<sub>Photo by [Rodion Kutsaiev](https://unsplash.com/@frostroomhead) on [Unsplash](https://unsplash.com/)</sub>

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![stability: experimental](https://masterminds.github.io/stability/experimental.svg)](https://masterminds.github.io/stability/experimental.html)
[![build](https://github.com/tinylinden/advent-of-code/actions/workflows/gradle.yml/badge.svg)](https://github.com/tinylinden/advent-of-code/actions/workflows/gradle.yml)

This repo is my personal attempt at solving [Advent of Code](https://adventofcode.com/)
puzzles with Kotlin programming language. Let's see what comes out of this.

## Events

### 2025

| Day | Puzzle                                                     |                           :star:                            |
|:---:|------------------------------------------------------------|:-----------------------------------------------------------:|
|  1  | [Secret Entrance](https://adventofcode.com/2025/day/1)     | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d01/Day01.kt) | 
|  2  | [Gift Shop](https://adventofcode.com/2025/day/2)           | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d02/Day02.kt) | 
|  3  | [Lobby](https://adventofcode.com/2025/day/3)               | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d03/Day03.kt) | 
|  4  | [Printing Department](https://adventofcode.com/2025/day/4) | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d04/Day04.kt) | 
|  5  | [Cafeteria](https://adventofcode.com/2025/day/5)           | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d05/Day05.kt) | 
|  6  | [Trash Compactor](https://adventofcode.com/2025/day/6)     | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d06/Day06.kt) | 
|  7  | [Laboratories](https://adventofcode.com/2025/day/7)        | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d07/Day07.kt) | 
|  8  | [Playground](https://adventofcode.com/2025/day/8)          | [2/2](src/main/kotlin/eu/tinylinden/aoc/y2025/d08/Day08.kt) | 
|     |                                                            |                            16/24                            | 

### 2024

| Day | Puzzle                                                       | :star: |
|:---:|--------------------------------------------------------------|:------:|
|  1  | [Historian Hysteria](https://adventofcode.com/2024/day/1)    |  2/2   |
|  2  | [Red-Nosed Reports](https://adventofcode.com/2024/day/2)     |  2/2   |
|  3  | [Mull It Over](https://adventofcode.com/2024/day/3)          |  2/2   |
|  4  | [Ceres Search](https://adventofcode.com/2024/day/4)          |  2/2   |
|  5  | [Print Queue](https://adventofcode.com/2024/day/5)           |  2/2   |
|  6  | [Guard Gallivant](https://adventofcode.com/2024/day/6)       |  2/2   |
|  7  | [Bridge Repair](https://adventofcode.com/2024/day/7)         |  2/2   |
|  8  | [Resonant Collinearity](https://adventofcode.com/2024/day/8) |  2/2   |
|  9  | [Disk Fragmenter](https://adventofcode.com/2024/day/9)       |  2/2   |
| 10  | [Hoof It](https://adventofcode.com/2024/day/10)              |  2/2   |
| 11  | [Plutonian Pebbles](https://adventofcode.com/2024/day/11)    |  2/2   |
| 12  | [Garden Groups](https://adventofcode.com/2024/day/12)        |  0/2   |
| 13  | [Claw Contraption](https://adventofcode.com/2024/day/13)     |  2/2   |
| 14  | [Restroom Redoubt](https://adventofcode.com/2024/day/14)     |  2/2   |
|     |                                                              | 26/50  |

## Notes for future me

[Advent of Code / About](https://adventofcode.com/about) asks not to share
puzzle texts or inputs anywhere. Wanting to comply with that request
all puzzle inputs are kept in `src/test/resources/private` directory.

I don't intend to enter the race for a place in the top 100, but manually
configuring resources for individual puzzles can be tedious. Scaffolding
for given day can be initialized with `make scaffold y=YYYY d=DD`.
For example `make scaffold y=2024 d=01` will create:

1. Empty solution file
   ```
   src/main/**/y2024/d01/Day01.kt
   ```
2. Disabled test file
   ```
   src/test/**/y2024/d01/Day01Test.kt
   ```
3. Empty test case files for examples
   ```
   src/test/resources/private/y2024d01p1-example
   src/test/resources/private/y2024d01p2-example
   ```

Test case file format supported by current implementation:

```
EXPECTED RESULT
ALL...
...PUZZLE...
...LINES
```

Example test cases should be populated by copying them manually from
AoC. Puzzle ones for given day can be fetched auto-magically with
`make fetch-puzzle y=YYYY d=DD`. This will only work if following
environment variables are specified (e.g, in `.env` file):

- `TEMP` - temporary directory,
- `SESSION` - session identifier (stored as cookie named `session` after
  [Advent of Code / Log In](https://adventofcode.com/auth/login)).
