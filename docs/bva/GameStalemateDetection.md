# BVA Analysis for Game Stalemate Detection

## Method under test: `isStalemate(PieceColor color)`

BVA basis:
- Input includes the method parameter `color`, the current board state, whether the king is currently in check, and whether the player has any legal moves.
- Output is boolean: `true` only when the given color's king is **not** in check and that color has **no legal move**.
- Uses each-choice strategy to cover representative stalemate boundaries without testing every possible chess position.

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `isStalemate(WHITE)` | White king on e1 is not in check and white has at least one legal move | Returns `false` | ✅ |
| TC2 | `isStalemate(WHITE)` | White king on h1 is not in check, but all legal king moves are attacked and white has no other pieces | Returns `true` | ✅ |
| TC3 | `isStalemate(WHITE)` | White king on h1 is not in check and has one legal escape square | Returns `false` | ✅ |
| TC4 | `isStalemate(WHITE)` | White king has no legal moves, but white rook has a legal move | Returns `false` | ✅ |
| TC5 | `isStalemate(WHITE)` | White king has no legal moves, but white bishop can legally move without exposing the king to check | Returns `false` | ✅ |
| TC6 | `isStalemate(WHITE)` | White king has no legal moves and a white pinned bishop has apparent movement, but every bishop move would expose the king to check | Returns `true` | ✅ |
| TC7 | `isStalemate(WHITE)` | White king has no legal moves, but white pawn has a legal forward move | Returns `false` | ✅ |
| TC8 | `isStalemate(WHITE)` | White king has no legal moves and white pawn is blocked, leaving no legal moves | Returns `true` | ✅ |
| TC9 | `isStalemate(WHITE)` | White king has no legal moves but is currently in check | Returns `false` | :x: |
| TC10 | `isStalemate(BLACK)` | Black king on h8 is not in check, but all legal king moves are attacked and black has no other pieces | Returns `true` | :x: |
| TC11 | `isStalemate(BLACK)` | Black king is not in check and black has at least one legal move | Returns `false` | :x: |
| TC12 | `isStalemate(WHITE)` | No white king exists on the board | Throws `IllegalStateException` | :x: |
