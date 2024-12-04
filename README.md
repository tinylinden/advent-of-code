# Advent of Code

[![aoc.jpg](aoc.jpg)](https://unsplash.com/photos/ySNkCkdKyTY)  
<sub>Photo by [Rodion Kutsaiev](https://unsplash.com/@frostroomhead) on [Unsplash](https://unsplash.com/)</sub>

This repo is my personal attempt at solving [Advent of Code](https://adventofcode.com/)
puzzles with Kotlin programming language.

## Events

### 2024

Score: 8/50 ⭐

| Day | Puzzle                                                    |                          Solution                           |
|:---:|-----------------------------------------------------------|:-----------------------------------------------------------:|
|  1  | [Historian Hysteria](https://adventofcode.com/2024/day/1) | [✔](./src/main/kotlin/eu/tinylinden/aoc/y2024/d01/Day01.kt) |
|  2  | [Red-Nosed Reports](https://adventofcode.com/2024/day/2)  | [✔](./src/main/kotlin/eu/tinylinden/aoc/y2024/d02/Day02.kt) |
|  3  | [Mull It Over](https://adventofcode.com/2024/day/3)       | [✔](./src/main/kotlin/eu/tinylinden/aoc/y2024/d03/Day03.kt) |
|  4  | [Ceres Search](https://adventofcode.com/2024/day/4)       | [✔](./src/main/kotlin/eu/tinylinden/aoc/y2024/d04/Day04.kt) |

## Notes for future me

[Advent of Code / About](https://adventofcode.com/about) asks not to share
puzzle texts or inputs anywhere. Wanting to comply with that request
all puzzle inputs are kept in `src/test/resources/private` directory.

I don't intend to enter the race for a place in the top 100, but manually 
configuring resources for individual puzzles can be tedious. Scaffolding 
for given day can be initialized with `make scaffold y=YYYY d=DD`.
For example `make scaffold y=2024 d=01`, will create:

```
// (1) empty solution
src/main/**/y2024/d01/Day01.kt

// (2) disabled test
src/test/**/y2024/d01/Day01Test.kt

// (3) empty test cases
src/test/resources/private/y2024d01p1-example
src/test/resources/private/y2024d01p2-example
src/test/resources/private/y2024d01p1-puzzle
src/test/resources/private/y2024d01p2-puzzle
```

Test case file format supported by current implementation:

```
EXPECTED RESULT
PUZZLE
LINES
...
```

Example test cases should be populated by copying them manually from
AoC. Puzzle ones for given day can be fetched auto-magically with
`make fetch-puzzle y=YYYY d=DD`. This will only work if following 
variables are specified in `.env` file:

- `TEMP` - temporary directory,
- `SESSION` - session identifier (stored as cookie named `session` after
  [Advent of Code / Log In](https://adventofcode.com/auth/login)).

