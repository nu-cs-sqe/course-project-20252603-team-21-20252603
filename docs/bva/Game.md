# BVA Analysis for Game

---

## Method under test: `Game()`

| ID  | Method(s) under test | System under test | Expected output | Implemented? |
|-----|----------------------|------------------|-----------------|--------------|
| TC1 | `Game()`, `getBoard()` | New game constructed (before initialization) | `getBoard()` returns null or empty board | :x: |
| TC2 | `Game()`, `getCurrentTurn()` | New game constructed (before initialization) | `getCurrentTurn()` returns null or default | :x: |

---

## Method under test: `initializeGame()`

| ID  | Method(s) under test | System under test | Expected output | Implemented? |
|-----|----------------------|------------------|-----------------|--------------|
| TC3 | `initializeGame()`, `getBoard()` | Game is initialized | Board is non-null and size is 8 | :x: |
| TC4 | `initializeGame()`, `getCurrentTurn()` | Game is initialized | Current turn is WHITE | :x: |

### Collection-Based BVA (Board State)
(Based on BVA catalog: collection size & contents) :contentReference[oaicite:2]{index=2}

| ID  | Method(s) under test | System under test | Expected output | Implemented? |
|-----|----------------------|------------------|-----------------|--------------|
| TC5 | `initializeGame()`, `getBoard()` | Game initialized | Board contains >1 pieces | :x: |
| TC6 | `initializeGame()`, `getBoard()` | Game initialized | Board is NOT empty | :x: |

---

### Representative Piece Placement (Boundary of correctness)

| ID  | Method(s) under test | System under test | Expected output | Implemented? |
|-----|----------------------|------------------|-----------------|--------------|
| TC7 | `initializeGame()`, `getSquare(7,4)` | Game initialized | White king at (7,4) | :x: |
| TC8 | `initializeGame()`, `getSquare(0,4)` | Game initialized | Black king at (0,4) | :x: |
| TC9 | `initializeGame()`, `getSquare(6,0)` | Game initialized | White pawn at (6,0) | :x: |
| TC10 | `initializeGame()`, `getSquare(1,0)` | Game initialized | Black pawn at (1,0) | :x: |

---

## Method under test: `getBoard()`

| ID  | Method(s) under test | System under test | Expected output | Implemented? |
|-----|----------------------|------------------|-----------------|--------------|
| TC11 | `getBoard()` | Before initialization | Returns null or empty board | :x: |
| TC12 | `getBoard()` | After initialization | Returns fully initialized board | :x: |

---

## Method under test: `getCurrentTurn()`

| ID  | Method(s) under test | System under test | Expected output | Implemented? |
|-----|----------------------|------------------|-----------------|--------------|
| TC13 | `getCurrentTurn()` | Before initialization | Returns null or default | :x: |
| TC14 | `getCurrentTurn()` | After initialization | Returns WHITE | :x: |