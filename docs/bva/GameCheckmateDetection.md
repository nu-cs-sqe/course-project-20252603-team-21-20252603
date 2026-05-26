# BVA Analysis for Game Checkmate Detection

## Method under test: `isCheckmate(PieceColor color)`

BVA basis:
- Input includes the method parameter `color`, the current board state, legal move availability, and whether the king is currently in check.
- Output is boolean: `true` only when the given color's king is in check and that color has no legal move that removes the check.
- Uses each-choice strategy to cover representative check/checkmate boundaries without testing every possible chess position.

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `isCheckmate(WHITE)` | White king on e1 is not in check and white has legal moves available | Returns `false` | ✅ |
| TC2 | `isCheckmate(WHITE)` | White king on e1 is in check from black rook on e8, but white king can legally move to d1 | Returns `false` | ✅ |
| TC3 | `isCheckmate(WHITE)` | White king on e1 is in check from black rook on e8, but white bishop can move to e2 to block the check | Returns `false` | ✅ |
| TC4 | `isCheckmate(WHITE)` | White king on e1 is in check from black rook on e8, but white rook can capture the checking rook on e8 | Returns `false` | ✅ |
| TC5 | `isCheckmate(WHITE)` | White king on e1 is in check from black queen on e2; all adjacent king moves are attacked or occupied; queen is protected; no white piece can block or capture | Returns `true` | ✅ |
| TC6 | `isCheckmate(WHITE)` | White king on h1 is in check from black queen on g2; escape squares g1/h2 are attacked or occupied; queen is protected; no legal capture or block exists | Returns `true` | ✅ |
| TC7 | `isCheckmate(WHITE)` | White king on e1 is in check from black knight on f3, but white king can capture the knight safely | Returns `false` | ✅ |
| TC8 | `isCheckmate(WHITE)` | White king on e1 is in check from black knight on f3; knight is protected; all king escape squares are attacked; knight check cannot be blocked | Returns `true` | ✅ |
| TC9 | `isCheckmate(WHITE)` | White king on e1 is in double check from black rook on e8 and black bishop on b4; white king has one legal escape square | Returns `false` | ✅ |
| TC10 | `isCheckmate(WHITE)` | White king on e1 is in double check from black rook on e8 and black bishop on b4; all king escape squares are attacked; blocking or capturing only one attacker is insufficient | Returns `true` | ✅ |
| TC11 | `isCheckmate(BLACK)` | Black king on e8 is in check from white rook on e1, but black bishop can move to e7 to block the check | Returns `false` | ✅ |
| TC12 | `isCheckmate(BLACK)` | Black king on h8 is in check from white queen on g7; escape squares g8/h7 are attacked or occupied; queen is protected; no legal capture or block exists | Returns `true` | ✅ |
| TC13 | `isCheckmate(WHITE)` | No white king exists on the board | Throws `IllegalStateException` | :x: |
