# BVA Analysis for Game Capturing

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE piece moves onto square occupied by WHITE piece: `(7,1)` to `(6,3)` | Move is rejected with exception, board unchanged, turn remains WHITE | :x: |
| TC2 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE piece captures BLACK piece with valid movement pattern: white knight `(7,1)` to `(5,2)`, then black pawn `(1,0)` to `(2,0)`, then white knight `(5,2)` to `(3,1)`, then black pawn `(2,0)` to `(3,0)`, then white knight `(3,1)` to `(1,0)` | BLACK pawn is removed, WHITE knight occupies destination, start square is empty, turn switches to BLACK | :x: |
| TC3 | `movePiece`, `getBoard`, `getCurrentTurn` | BLACK piece captures WHITE piece after turn switches to BLACK | WHITE piece is removed, BLACK piece occupies destination, start square is empty, turn switches to WHITE | :x: |
| TC4 | `movePiece`, `getBoard`, `getCurrentTurn` | Capture attempt uses invalid movement pattern: WHITE pawn `(6,0)` to `(5,1)` where destination is empty | Move is rejected with exception, board unchanged, turn remains WHITE | :x: |
| TC5 | `movePiece`, `getBoard`, `getCurrentTurn` | Failed own-piece capture attempt should not remove either piece: WHITE knight `(7,1)` to `(6,3)` | Both WHITE pieces remain in original squares, turn remains WHITE | :x: |