# Advent of Code

[![aoc.jpg](aoc.jpg)](https://unsplash.com/photos/ySNkCkdKyTY)

### 2024

1. [Historian Hysteria](https://adventofcode.com/2024/day/1)
   [⭐⭐](./src/main/kotlin/eu/tinylinden/aoc/y2024/d01/Day01.kt)
2. [Red-Nosed Reports](https://adventofcode.com/2024/day/2)
   [⭐⭐](./src/main/kotlin/eu/tinylinden/aoc/y2024/d02/Day02.kt)
3. [Mull It Over](https://adventofcode.com/2024/day/3)
   [⭐⭐](./src/main/kotlin/eu/tinylinden/aoc/y2024/d03/Day03.kt)
4. [Ceres Search](https://adventofcode.com/2024/day/4)
   [⭐⭐](./src/main/kotlin/eu/tinylinden/aoc/y2024/d04/Day04.kt)

### Notes for future me

[Advent of Code / About](https://adventofcode.com/about) asks not to share 
puzzle texts or inputs anywhere. Wanting to comply with that request 
all puzzle inputs are kept in `src/test/resources/private` directory. 

Scaffolding for given day can be initialized with `make scaffold y=YYYY d=DD`
(e.g., day 1, 2024 - `make scaffold y=2024 d=01`).

Puzzle input for given day can be fetched with `make fetch-input y=YYYY d=DD`
(e.g., day 1, 2024 - `make fetch-input y=2024 d=01`). This will only work if 
following variables are specified in `.env` file:

  - `TEMP` - temporary directory,
  - `SESSION` - session identifier (stored as cookie named `session` after
     [Advent of Code / Log In](https://adventofcode.com/auth/login)).
