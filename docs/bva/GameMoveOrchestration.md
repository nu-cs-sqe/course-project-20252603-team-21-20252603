# BVA Analysis for Game Move Orchestration

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | Valid WHITE pawn move from `(6,0)` to `(5,0)` | Pawn moves to end square, start square becomes empty, turn switches to BLACK | :white_check_mark: |
| TC2 | `movePiece` | Start square is empty, moving from `(4,0)` to `(3,0)` | Move is rejected with exception | :white_check_mark: |
| TC3 | `movePiece` | Wrong player attempts to move BLACK pawn first from `(1,0)` to `(2,0)` | Move is rejected with exception and turn remains WHITE | :x: |
| TC4 | `movePiece` | WHITE pawn attempts invalid pattern from `(6,0)` to `(6,1)` | Move is rejected with exception and board is unchanged | :x: |
| TC5 | `movePiece` | Same-square move from `(6,0)` to `(6,0)` | Move is rejected with exception and board is unchanged | :x: |
| TC6 | `movePiece` | Start row below bounds: `(-1,0)` to `(0,0)` | Move is rejected with IndexOutOfBoundsException | :x: |
| TC7 | `movePiece` | Start row above bounds: `(8,0)` to `(7,0)` | Move is rejected with IndexOutOfBoundsException | :x: |
| TC8 | `movePiece` | End column below bounds: `(6,0)` to `(5,-1)` | Move is rejected with IndexOutOfBoundsException | :x: |
| TC9 | `movePiece` | End column above bounds: `(6,0)` to `(5,8)` | Move is rejected with IndexOutOfBoundsException | :x: |
