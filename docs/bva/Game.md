# BVA Analysis for Game

## Method under test: `initializeGame()`

### Relevant BVA Categories
- Cases
- Collections
- Sizes of collections
- Contents of collections
- Overwriting previous contents

---

| ID | Method(s) under test | BVA category | System under test | Expected output | Implemented? |
|---|---|---|---|---|---|
| TC1 | `initializeGame()`, `getBoard()` | Collections | Game after initialization | Board is not null | :x: |
| TC2 | `initializeGame()`, `getBoard()`, `getSize()` | Sizes of collections | Game after initialization | Board size is `8` | :x: |
| TC3 | `initializeGame()`, `getCurrentTurn()` | Cases | Game after initialization | Current turn is `WHITE` | :x: |
| TC4 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | White king is at `(7, 4)` | :x: |
| TC5 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | Black king is at `(0, 4)` | :x: |
| TC6 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | White pawn is at `(6, 0)` | :x: |
| TC7 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | Black pawn is at `(1, 0)` | :x: |
| TC8 | `initializeGame()`, `getBoard()`, `isEmpty(int, int)` | Contents of collections | Game after initialization | Middle board squares are empty | :x: |
| TC9 | `initializeGame()` twice | Overwriting previous contents | Already initialized game | Board resets to standard starting state | :x: |
