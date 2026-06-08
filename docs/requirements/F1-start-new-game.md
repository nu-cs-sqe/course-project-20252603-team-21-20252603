# F1: Start a New Chess Game

## User Story

As a chess player, I want to start a new chess game so that I can begin playing from the standard initial position.

## Integrated Modules

- `Game` creates and owns the game state.
- `Board` stores the initialized squares.
- `Piece`, `PieceType`, and `PieceColor` represent the pieces placed on the board.

## Expected Behavior

- A new game starts with an 8 by 8 board.
- White has the first turn.
- The white and black kings are in their standard starting positions.
- Each side has eight pawns in its standard pawn row.
- Representative pieces of both colors occupy their expected rows, columns, and board edges.
- The middle board rows are empty.
