# BVA Analysis for Game

## Method under test: `Game()`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `Game()`, `getBoard()` | New game constructed before `initializeGame()` is called | `getBoard()` returns `null` | :x: |
| TC2 | `Game()`, `getCurrentTurn()` | New game constructed before `initializeGame()` is called | `getCurrentTurn()` returns `null` | :x: |

---

## Method under test: `initializeGame()`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC3 | `initializeGame()`, `getBoard()` | Game is initialized | `getBoard()` returns a non-null `Board` | :x: |
| TC4 | `initializeGame()`, `getBoard().getSize()` | Game is initialized | Board size is `8` | :x: |
| TC5 | `initializeGame()`, `getCurrentTurn()` | Game is initialized | `getCurrentTurn()` returns `WHITE` | :x: |
| TC6 | `initializeGame()`, `getBoard()` | Game is initialized | Board is not empty | :x: |
| TC7 | `initializeGame()`, `getBoard().getSquare(7, 4)` | Game is initialized | White king is at `(7, 4)` | :x: |
| TC8 | `initializeGame()`, `getBoard().getSquare(0, 4)` | Game is initialized | Black king is at `(0, 4)` | :x: |
| TC9 | `initializeGame()`, `getBoard().getSquare(6, 0)` | Game is initialized | White pawn is at `(6, 0)` | :x: |
| TC10 | `initializeGame()`, `getBoard().getSquare(1, 0)` | Game is initialized | Black pawn is at `(1, 0)` | :x: |
| TC11 | `initializeGame()`, `getBoard()` | Game is initialized | Board contains 32 pieces | :x: |