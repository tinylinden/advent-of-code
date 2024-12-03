# Advent of Code

![aoc.jpg](aoc.jpg)

[![stability: experimental](https://masterminds.github.io/stability/experimental.svg)](https://masterminds.github.io/stability/experimental.html)

### 2024

1. [Historian Hysteria](https://adventofcode.com/2024/day/1) [⭐⭐](./src/main/kotlin/eu/tinylinden/aoc/y2024/d01/Day01.kt)
2. [Red-Nosed Reports](https://adventofcode.com/2024/day/2) [⭐⭐](./src/main/kotlin/eu/tinylinden/aoc/y2024/d02/Day02.kt)
3. [Mull It Over](https://adventofcode.com/2024/day/3) [⭐⭐](./src/main/kotlin/eu/tinylinden/aoc/y2024/d03/Day03.kt)

### Notes for future me

In [Advent of Code / About](https://adventofcode.com/about) author(s) asks for
not to share puzzle texts or inputs anywhere. Wanting to comply with that request 
all puzzle inputs are taken from `src/test/resources/private` directory. 

Scaffolding for given day can be initialized with `make setup YYYY DD` (e.g, day 1, 2024 - `make setup 2024 01`).

Puzzle input for given day can be fetched with `make input YYYY DD` (e.g.,
day 1, 2024 - `make input 2024 01`). This will only work if following variables
are specified in `.env` file:

  - `TEMP` - temporary directory,
  - `SESSION` - session identifier (stored as cookie named `session` after
     [Advent of Code / Log In](https://adventofcode.com/auth/login)).
