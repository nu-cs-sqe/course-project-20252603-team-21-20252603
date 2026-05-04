# BVA Analysis for Piece

## Method under test: `Piece(PieceType type, PieceColor color)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `Piece(PieceType, PieceColor)`, `getType()` | Create piece with type `KING` and color `WHITE` | `getType()` returns `KING` | :x: |
| TC2 | `Piece(PieceType, PieceColor)`, `getColor()` | Create piece with type `KING` and color `WHITE` | `getColor()` returns `WHITE` | :x: |
| TC3 | `Piece(PieceType, PieceColor)` | Create piece with `null` type and color `WHITE` | Throws `IllegalArgumentException` | :x: |
| TC4 | `Piece(PieceType, PieceColor)` | Create piece with type `KING` and `null` color | Throws `IllegalArgumentException` | :x: |