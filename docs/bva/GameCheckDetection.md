# BVA Analysis for Game Check Detection

## Method under test: `isKingInCheck(PieceColor color)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `isKingInCheck(WHITE)` | White king on e1; no black pieces attack the white king | Returns `false` | ✅ |
| TC2 | `isKingInCheck(WHITE)` | White king on e1; black rook on e8; e2/e3/e4/e5/e6/e7 clear | Returns `true` | ✅ |
| TC3 | `isKingInCheck(WHITE)` | White king on e1; black bishop on b4; diagonal path d2/c3 clear | Returns `true` | ✅ |
| TC4 | `isKingInCheck(WHITE)` | White king on e1; black queen on h4; diagonal path f2/g3 clear | Returns `true` | ✅ |
| TC5 | `isKingInCheck(WHITE)` | White king on e1; black queen on e8; e2/e3/e4/e5/e6/e7 clear | Returns `true` | ✅ |
| TC6 | `isKingInCheck(WHITE)` | White king on e1; black knight on f3 | Returns `true` | ✅ |
| TC7 | `isKingInCheck(WHITE)` | White king on e1; black knight on f2, which is not a valid knight attack square | Returns `false` | ❌ |
| TC8 | `isKingInCheck(WHITE)` | White king on e1; black pawn on d2 | Returns `true` | ❌ |
| TC9 | `isKingInCheck(WHITE)` | White king on e1; black pawn on e2 directly in front of king but not attacking diagonally | Returns `false` | ❌ |
| TC10 | `isKingInCheck(WHITE)` | White king on e1; black king on e2 adjacent to white king | Returns `true` | ❌ |
| TC11 | `isKingInCheck(WHITE)` | White king on e1; black king on e3, not adjacent to white king | Returns `false` | ❌ |
| TC12 | `isKingInCheck(WHITE)` | White king on e1; black rook on e8; white piece on e4 blocks the path | Returns `false` | ❌ |
| TC13 | `isKingInCheck(WHITE)` | White king on e1; white rook on e8 aligned with king | Returns `false` | ❌ |
| TC14 | `isKingInCheck(BLACK)` | Black king on e8; white rook on e1; e2/e3/e4/e5/e6/e7 clear | Returns `true` | ❌ |
| TC15 | `isKingInCheck(WHITE)` | No white king exists on the board | Throws `IllegalStateException` | ❌ |
