# BVA Analysis for Game Check Detection

## Method under test: `isKingInCheck(PieceColor color)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `isKingInCheck(WHITE)` | White king is not attacked by any opposing piece | Returns `false` | ✅ |
| TC2 | `isKingInCheck(WHITE)` | White king attacked horizontally/vertically by black rook or queen | Returns `true` | :x: |
| TC3 | `isKingInCheck(WHITE)` | White king attacked diagonally by black bishop or queen | Returns `true` | :x: |
| TC4 | `isKingInCheck(WHITE)` | White king attacked by black knight in valid L-shape position | Returns `true` | :x: |
| TC5 | `isKingInCheck(WHITE)` | White king attacked by black pawn from valid attack square | Returns `true` | :x: |
| TC6 | `isKingInCheck(WHITE)` | White king adjacent to opposing king | Returns `true` | :x: |
| TC7 | `isKingInCheck(WHITE)` | Sliding attack path exists geometrically but is blocked by another piece | Returns `false` | :x: |
| TC8 | `isKingInCheck(WHITE)` | Friendly white piece aligned with king does not count as attack | Returns `false` | :x: |
| TC9 | `isKingInCheck(BLACK)` | Black king attacked by white piece | Returns `true` | :x: |
| TC10 | `isKingInCheck(PieceColor color)` | King of specified color does not exist on board | Throws exception or returns `false` based on design decision | :x: |
