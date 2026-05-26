# BVA Analysis for Game En Passant

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

BVA basis:
- Input includes move coordinates, moving pawn color, adjacent pawn state, previous move history, destination square, current turn, and whether the move leaves the king in check.
- Output includes board state, captured pawn removal, moving pawn destination, turn switching, and exceptions for invalid en passant attempts.
- Uses each-choice strategy because en passant depends on several interacting state/history boundaries.

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e5 captures en passant on d6 immediately after black pawn moves from d7 to d5 | Move succeeds; white pawn moves to d6; black pawn removed from d5; e5 empty; turn switches to BLACK | ✅ |
| TC2 | `movePiece`, `getBoard`, `getCurrentTurn` | Black pawn on d4 captures en passant on e3 immediately after white pawn moves from e2 to e4 | Move succeeds; black pawn moves to e3; white pawn removed from e4; d4 empty; turn switches to WHITE | ✅ |
| TC3 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e5 attempts en passant on d6, but black pawn moved only one square from d6 to d5 | Move rejected; white pawn remains e5; black pawn remains d5; d6 empty; turn remains WHITE | ✅ |
| TC4 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e5 attempts en passant on d6, but the previous move was by a black knight instead of the adjacent black pawn | Move rejected; white pawn remains e5; black pawn remains d5; d6 empty; turn remains WHITE | ✅ |
| TC5 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e5 attempts en passant on d6 one full turn after black pawn moved from d7 to d5 | Move rejected; en passant window expired; white pawn remains e5; black pawn remains d5; d6 empty; turn remains WHITE | ✅ |
| TC6 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e5 attempts en passant on d6 when d5 contains a black rook, not a pawn | Move rejected; white pawn remains e5; black rook remains d5; d6 empty; turn remains WHITE | ✅ |
| TC7 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e5 attempts en passant on d6 when no piece exists on d5 | Move rejected; white pawn remains e5; d6 empty; turn remains WHITE | ✅ |
| TC8 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e5 attempts en passant on d6 when d6 is occupied | Move rejected; white pawn remains e5; d6 remains occupied; black pawn remains d5; turn remains WHITE | ✅ |
| TC9 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn on e4 attempts en passant on d5 from the wrong rank | Move rejected; white pawn remains e4; black pawn remains d4 or d5 according to setup; turn remains WHITE | ✅ |
| TC10 | `movePiece`, `getBoard`, `getCurrentTurn` | Black pawn on d5 attempts en passant on e4 from the wrong rank | Move rejected; black pawn remains d5; white pawn remains e5 or e4 according to setup; turn remains BLACK | ✅ |
| TC11 | `movePiece`, `getBoard`, `getCurrentTurn` | White non-pawn piece attempts an en passant-shaped diagonal move to an empty square | Move rejected; piece remains in place; destination empty; turn remains WHITE | ✅ |
| TC12 | `movePiece`, `getBoard`, `getCurrentTurn`, `isKingInCheck` | White en passant capture would expose white king to rook check along the rank/file | Move rejected; both pawns remain in place; `isKingInCheck(WHITE)` remains false; turn remains WHITE | :x: |
| TC13 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn makes a normal diagonal capture into an occupied square after previous move was a two-square pawn move elsewhere | Normal capture succeeds; en passant state does not incorrectly affect regular capture | :x: |
