# BVA Analysis for Game Castling Legality

## Method under test: `movePiece(startRow, startCol, endRow, endCol)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling attempted while white king on e1 is currently in check; f1/g1 clear; white rook on h1 | Move rejected; king stays e1; rook stays h1; f1/g1 remain empty; turn remains WHITE | ✅ |
| TC2 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling attempted when f1, the square the king passes through, is attacked; e1/g1 not attacked | Move rejected; king stays e1; rook stays h1; f1/g1 remain empty; turn remains WHITE | ✅ |
| TC3 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling attempted when g1, the destination square, is attacked; e1/f1 not attacked | Move rejected; king stays e1; rook stays h1; f1/g1 remain empty; turn remains WHITE | ✅ |
| TC4 | `movePiece`, `getBoard`, `getCurrentTurn` | White queenside castling attempted while white king on e1 is currently in check; d1/c1/b1 clear; white rook on a1 | Move rejected; king stays e1; rook stays a1; d1/c1/b1 remain empty; turn remains WHITE | ✅ |
| TC5 | `movePiece`, `getBoard`, `getCurrentTurn` | White queenside castling attempted when d1, the square the king passes through, is attacked; e1/c1 not attacked | Move rejected; king stays e1; rook stays a1; d1/c1/b1 remain empty; turn remains WHITE | ✅ |
| TC6 | `movePiece`, `getBoard`, `getCurrentTurn` | White queenside castling attempted when c1, the destination square, is attacked; e1/d1 not attacked | Move rejected; king stays e1; rook stays a1; d1/c1/b1 remain empty; turn remains WHITE | ✅ |
| TC7 | `movePiece`, `getBoard`, `getCurrentTurn` | Black kingside castling attempted while black king on e8 is currently in check; f8/g8 clear; black rook on h8 | Move rejected; king stays e8; rook stays h8; f8/g8 remain empty; turn remains BLACK | ✅ |
| TC8 | `movePiece`, `getBoard`, `getCurrentTurn` | Black kingside castling attempted when f8, the square the king passes through, is attacked; e8/g8 not attacked | Move rejected; king stays e8; rook stays h8; f8/g8 remain empty; turn remains BLACK | ✅ |
| TC9 | `movePiece`, `getBoard`, `getCurrentTurn` | Black kingside castling attempted when g8, the destination square, is attacked; e8/f8 not attacked | Move rejected; king stays e8; rook stays h8; f8/g8 remain empty; turn remains BLACK | ✅ |
| TC10 | `movePiece`, `getBoard`, `getCurrentTurn` | Black queenside castling attempted while black king on e8 is currently in check; d8/c8/b8 clear; black rook on a8 | Move rejected; king stays e8; rook stays a8; d8/c8/b8 remain empty; turn remains BLACK | ✅ |
| TC11 | `movePiece`, `getBoard`, `getCurrentTurn` | Black queenside castling attempted when d8, the square the king passes through, is attacked; e8/c8 not attacked | Move rejected; king stays e8; rook stays a8; d8/c8/b8 remain empty; turn remains BLACK | ✅ |
| TC12 | `movePiece`, `getBoard`, `getCurrentTurn` | Black queenside castling attempted when c8, the destination square, is attacked; e8/d8 not attacked | Move rejected; king stays e8; rook stays a8; d8/c8/b8 remain empty; turn remains BLACK | ✅ |
| TC13 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling when f1 is attacked, but only by a piece blocked by another piece before reaching f1 | Castle succeeds; king moves to g1; rook moves to f1; turn switches to BLACK | ✅ |
| TC14 | `movePiece`, `getBoard`, `getCurrentTurn` | White queenside castling when b1 is attacked, but e1/d1/c1 are not attacked | Castle succeeds; king moves to c1; rook moves to d1; turn switches to BLACK | ✅ |
| TC15 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling after king previously moved away from e1 and returned to e1 | Move rejected; king stays e1; rook stays h1; board unchanged; turn remains WHITE | ✅ |
| TC16 | `movePiece`, `getBoard`, `getCurrentTurn` | White kingside castling after h1 rook previously moved away from h1 and returned to h1 | Move rejected; king stays e1; rook stays h1; board unchanged; turn remains WHITE | ✅ |
| TC17 | `movePiece`, `getBoard`, `getCurrentTurn` | Black queenside castling after black king previously moved away from e8 and returned to e8 | Move rejected; king stays e8; rook stays a8; board unchanged; turn remains BLACK | ✅ |
| TC18 | `movePiece`, `getBoard`, `getCurrentTurn` | Black queenside castling after a8 rook previously moved away from a8 and returned to a8 | Move rejected; king stays e8; rook stays a8; board unchanged; turn remains BLACK | ❌ |
