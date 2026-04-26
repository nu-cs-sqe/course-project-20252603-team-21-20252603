# BVA Analysis for Board

## Method under test: `Board()`

| ID | Method(s) under test | System under test | Expected output | Implemented?    |
|---|---|---|---|-----------------|
| TC1 | `Board()`, `getSize()` | New board is constructed | Board size is `8` | :white_check_mark: |
| TC2 | `Board()`, `getSquare(int row, int col)` | New board is constructed | All valid squares are empty | :white_check_mark:                |

## Method under test: `getSize()`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|--------------|
| TC3 | `getSize()` | New board is constructed | Returns `8` | :white_check_mark: Done in TC1 |

## Method under test: `getSquare(int row, int col)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC4 | `getSquare(int, int)` | Position `(0, 0)` | Does not throw; returns square contents | :x: |
| TC5 | `getSquare(int, int)` | Position `(7, 7)` | Does not throw; returns square contents | :x: |
| TC6 | `getSquare(int, int)` | Position `(-1, 0)` | Throws `IndexOutOfBoundsException` | :x: |
| TC7 | `getSquare(int, int)` | Position `(8, 0)` | Throws `IndexOutOfBoundsException` | :x: |
| TC8 | `getSquare(int, int)` | Position `(0, -1)` | Throws `IndexOutOfBoundsException` | :x: |
| TC9 | `getSquare(int, int)` | Position `(0, 8)` | Throws `IndexOutOfBoundsException` | :x: |