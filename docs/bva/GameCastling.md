# BVA Analysis for Game Castling

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | White king castles kingside from e1 to g1; path f1/g1 clear; white rook on h1 | King moves to g1, rook moves to f1, e1/h1 empty, turn switches to BLACK | ✅: |
| TC2 | `movePiece`, `getBoard`, `getCurrentTurn` | White king castles queenside from e1 to c1; path d1/c1/b1 clear; white rook on a1 | King moves to c1, rook moves to d1, e1/a1 empty, turn switches to BLACK | :x: |
| TC3 | `movePiece`, `getBoard`, `getCurrentTurn` | Black king castles kingside from e8 to g8 on BLACK turn; path f8/g8 clear; black rook on h8 | King moves to g8, rook moves to f8, e8/h8 empty, turn switches to WHITE | :x: |
| TC4 | `movePiece`, `getBoard`, `getCurrentTurn` | Black king castles queenside from e8 to c8 on BLACK turn; path d8/c8/b8 clear; black rook on a8 | King moves to c8, rook moves to d8, e8/a8 empty, turn switches to WHITE | :x: |
| TC5 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling attempted while f1 is occupied | Move rejected; king/rook/blocking piece stay in place; turn remains WHITE | :x: |
| TC6 | `movePiece`, `getBoard`, `getCurrentTurn` | White queenside castling attempted while d1/c1/b1 path is blocked | Move rejected; king/rook/blocking piece stay in place; turn remains WHITE | :x: |
| TC7 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling attempted with no rook on h1 | Move rejected; king stays e1; destination stays empty; turn remains WHITE | :x: |
| TC8 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling attempted with opponent rook on h1 | Move rejected; king and opponent rook stay in place; turn remains WHITE | :x: |
| TC9 | `movePiece`, `getBoard`, `getCurrentTurn` | White king attempts castling-like two-square move from non-starting square | Move rejected; board unchanged; turn remains WHITE | :x: |
| TC10 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling attempted when destination g1 is occupied | Move rejected; board unchanged; turn remains WHITE | :x: |
| TC11 | `movePiece`, `getBoard`, `getCurrentTurn` | White attempts castling-like move to invalid king destination, e1 to b1 | Move rejected; board unchanged; turn remains WHITE | :x: |
| TC12 | `movePiece`, `getBoard`, `getCurrentTurn` | Non-king piece attempts two-square horizontal move that resembles castling | Move rejected or handled by normal piece rules only; no rook movement occurs; turn preserved if invalid | :x: |
