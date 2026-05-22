# BVA Analysis for Game Capturing

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE piece moves onto square occupied by WHITE piece: `(7,1)` to `(6,3)` | Move is rejected with exception, board unchanged, turn remains WHITE | :white_check_mark: |
| TC2 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE knight captures BLACK pawn with valid movement pattern after board is arranged for capture: WHITE knight is placed at `(4,4)`, BLACK pawn is placed at `(2,3)`, then `movePiece(4,4,2,3)` is called | BLACK pawn is removed, WHITE knight occupies `(2,3)`, start square `(4,4)` is empty, turn switches to BLACK | :white_check_mark: |
| TC3 | `movePiece`, `getBoard`, `getCurrentTurn` | BLACK knight captures WHITE pawn after turn switches to BLACK: BLACK knight is placed at `(5,3)`, WHITE pawn is placed at `(3,2)`, WHITE first moves `(6,7)` to `(5,7)`, then `movePiece(5,3,3,2)` is called | WHITE pawn is removed, BLACK knight occupies `(3,2)`, start square `(5,3)` is empty, turn switches to WHITE | :white_check_mark: |
| TC4 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE knight attempts to capture BLACK pawn with invalid movement pattern: WHITE knight is placed at `(4,4)`, BLACK pawn is placed at `(3,3)`, then `movePiece(4,4,3,3)` is called | Move is rejected with exception, both pieces remain, turn remains WHITE | :white_check_mark: |
| TC5 | `movePiece`, `getBoard`, `getCurrentTurn` | WHITE knight captures BLACK pawn at board edge: WHITE knight is placed at `(2,2)`, BLACK pawn is placed at `(0,1)`, then `movePiece(2,2,0,1)` is called | BLACK pawn is removed, WHITE knight occupies `(0,1)`, start square `(2,2)` is empty, turn switches to BLACK | :x: |
