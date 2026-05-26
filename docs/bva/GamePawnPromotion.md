# BVA Analysis for Game Pawn Promotion

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

BVA basis:
- Input includes move coordinates, pawn color, pawn location, destination rank, destination occupancy, and chosen promotion type.
- Output includes board state, promoted piece type/color, captured piece removal, turn switching, and exceptions for invalid promotion choices.
- Uses each-choice strategy to cover representative promotion boundaries.

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn moves from a7 to a8 with empty destination and promotes to queen | Move succeeds; a8 contains WHITE queen; a7 empty; turn switches to BLACK | ✅ |
| TC2 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn moves from h7 to h8 with empty destination and promotes to rook | Move succeeds; h8 contains WHITE rook; h7 empty; turn switches to BLACK | ✅ |
| TC3 | `movePiece`, `getBoard`, `getCurrentTurn` | Black pawn moves from a2 to a1 with empty destination and promotes to queen on BLACK turn | Move succeeds; a1 contains BLACK queen; a2 empty; turn switches to WHITE | ✅ |
| TC4 | `movePiece`, `getBoard`, `getCurrentTurn` | Black pawn moves from h2 to h1 with empty destination and promotes to bishop on BLACK turn | Move succeeds; h1 contains BLACK bishop; h2 empty; turn switches to WHITE | ✅ |
| TC5 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn moves diagonally from g7 to h8, captures black rook, and promotes to knight | Move succeeds; h8 contains WHITE knight; black rook removed; g7 empty; turn switches to BLACK | ✅ |
| TC6 | `movePiece`, `getBoard`, `getCurrentTurn` | Black pawn moves diagonally from b2 to a1, captures white rook, and promotes to rook on BLACK turn | Move succeeds; a1 contains BLACK rook; white rook removed; b2 empty; turn switches to WHITE | ✅ |
| TC7 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn moves from a6 to a7, not reaching final rank | Move succeeds; pawn remains WHITE pawn on a7; no promotion occurs; turn switches to BLACK | ✅ |
| TC8 | `movePiece`, `getBoard`, `getCurrentTurn` | Black pawn moves from a3 to a2 on BLACK turn, not reaching final rank | Move succeeds; pawn remains BLACK pawn on a2; no promotion occurs; turn switches to WHITE | ✅ |
| TC9 | `movePiece`, `getBoard`, `getCurrentTurn` | White non-pawn piece moves to final rank | Move succeeds normally; piece type does not change; turn switches to BLACK | ✅ |
| TC10 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn attempts to promote to KING | Move rejected; pawn remains in place; destination unchanged; turn remains WHITE | :x: |
| TC11 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn attempts to promote to PAWN | Move rejected; pawn remains in place; destination unchanged; turn remains WHITE | :x: |
| TC12 | `movePiece`, `getBoard`, `getCurrentTurn` | White pawn reaches final rank with no promotion type provided | Move succeeds; pawn promotes to default WHITE queen; turn switches to BLACK | :x: |
| TC13 | `movePiece`, `getBoard`, `getCurrentTurn`, `isKingInCheck` | White pawn promotion move would leave white king in check | Move rejected; pawn remains in place; destination unchanged; `isKingInCheck(WHITE)` remains false; turn remains WHITE | :x: |
