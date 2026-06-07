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
| TC1 | `initializeGame()`, `getBoard()` | Collections | Game after initialization | Board is not null | :white_check_mark: |
| TC2 | `initializeGame()`, `getBoard()`, `getSize()` | Sizes of collections | Game after initialization | Board size is `8` | :white_check_mark: |
| TC3 | `initializeGame()`, `getCurrentTurn()` | Cases | Game after initialization | Current turn is `WHITE` | :white_check_mark: |
| TC4 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | White king is at `(7, 4)` | :white_check_mark: |
| TC5 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | Black king is at `(0, 4)` | :white_check_mark: |
| TC6 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | White pawn is at `(6, 0)` | :white_check_mark: |
| TC7 | `initializeGame()`, `getBoard()`, `getSquare(int, int)` | Contents of collections | Game after initialization | Black pawn is at `(1, 0)` | :white_check_mark: |
| TC8 | `initializeGame()`, `getBoard()`, `isEmpty(int, int)` | Contents of collections | Game after initialization | Middle board squares are empty | :white_check_mark: |
| TC9 | `initializeGame()` twice | Overwriting previous contents | Already initialized game | Board resets to standard starting state | :white_check_mark: |
