# F2: Make a Legal Move

## User Story

As a chess player, I want to make a legal move so that the board updates and play passes to my opponent.

## Integrated Modules

- `Game` validates the move, updates game state, and changes the current turn.
- `Board` provides the starting position and stores the updated position.
- `Piece`, `PieceType`, and `PieceColor` provide the moving piece and its movement rules.

## Expected Behavior

- White can make representative legal opening pawn and knight moves.
- Black can make representative legal pawn and knight replies.
- The moved piece leaves its starting square and occupies its destination square.
- The moved piece keeps its original type and color.
- The current turn changes to the opposing player after each move.
