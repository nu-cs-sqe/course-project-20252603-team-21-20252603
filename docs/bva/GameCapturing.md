# BVA Analysis for Game Capturing

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE piece moves onto square occupied by WHITE piece: `(7,1)` to `(6,3)` | Move is rejected with exception, board unchanged, turn remains WHITE | :white_check_mark: |
| TC2 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE knight captures BLACK pawn with valid movement pattern after board is arranged for capture: WHITE knight is placed at `(4,4)`, BLACK pawn is placed at `(2,3)`, then `movePiece(4,4,2,3)` is called | BLACK pawn is removed, WHITE knight occupies `(2,3)`, start square `(4,4)` is empty, turn switches to BLACK | :white_check_mark: |
| TC3 | `movePiece`, `getBoard`, `getCurrentTurn` | BLACK piece captures WHITE piece after turn switches to BLACK | WHITE piece is removed, BLACK piece occupies destination, start square is empty, turn switches to WHITE | :x: |
| TC4 | `movePiece`, `getBoard`, `getCurrentTurn` | Capture attempt uses invalid movement pattern: WHITE pawn `(6,0)` to `(5,1)` where destination is empty | Move is rejected with exception, board unchanged, turn remains WHITE | :x: |
| TC5 | `movePiece`, `getBoard`, `getCurrentTurn` | Failed own-piece capture attempt should not remove either piece: WHITE knight `(7,1)` to `(6,3)` | Both WHITE pieces remain in original squares, turn remains WHITE | :x: |
