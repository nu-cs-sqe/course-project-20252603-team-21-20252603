# Chess Game Rules

Chess is a two-player board game played on an 8x8 board. One player controls the white pieces and the other controls the black pieces. White always moves first, and players alternate turns.

## Objective

The goal of chess is to checkmate the opponent's king.

A king is in checkmate when:
- The king is under attack.
- The player has no legal move that removes the attack.
- The king cannot move, capture, or be protected from the threat.

## Board Setup

The board has 64 squares arranged in 8 ranks and 8 files.

Each player starts with:
- 1 king
- 1 queen
- 2 rooks
- 2 bishops
- 2 knights
- 8 pawns

Initial setup:
- White pieces are placed on ranks 1 and 2.
- Black pieces are placed on ranks 7 and 8.
- The queens begin on their matching colors: white queen on a light square, black queen on a dark square.
- The kings are placed next to the queens.

White back rank from left to right:
rook, knight, bishop, queen, king, bishop, knight, rook

Black back rank from left to right:
rook, knight, bishop, queen, king, bishop, knight, rook

## Turns

Players take turns moving one piece at a time.

A move is legal only if:
- The selected piece moves according to its movement rules.
- The destination square is either empty or occupied by an opponent's piece.
- The move does not leave the moving player's king in check.

A player may not skip their turn.

## Capturing

A piece captures an opponent's piece by moving to the square occupied by that piece.

Captured pieces are removed from the board.

A player may not capture their own pieces.

## Piece Movement

### King

The king moves one square in any direction:
- Horizontally
- Vertically
- Diagonally

The king may not move onto a square that is under attack by an opposing piece.

### Queen

The queen moves any number of squares in a straight line:
- Horizontally
- Vertically
- Diagonally

The queen may not jump over other pieces.

### Rook

The rook moves any number of squares horizontally or vertically.

The rook may not jump over other pieces.

### Bishop

The bishop moves any number of squares diagonally.

The bishop may not jump over other pieces.

### Knight

The knight moves in an L-shape:
- Two squares in one direction, then one square perpendicular.

The knight is the only piece that may jump over other pieces.

### Pawn

Pawns move forward one square.

On a pawn's first move, it may move forward two squares if both squares are empty.

Pawns capture one square diagonally forward.

Pawns may not move backward.

## Special Moves

### Castling

Castling is a special move involving the king and one rook.

A player may castle kingside or queenside if all of the following are true:
- The king has not moved.
- The rook being used has not moved.
- The squares between the king and rook are empty.
- The king is not currently in check.
- The king does not pass through a square under attack.
- The king does not end on a square under attack.

When castling:
- The king moves two squares toward the rook.
- The rook moves to the square immediately on the other side of the king.

### En Passant

En passant is a special pawn capture.

If a pawn moves forward two squares from its starting position and lands next to an opposing pawn, the opposing pawn may capture it as if it had moved only one square.

En passant must be performed immediately on the next move, or the right to do so is lost.

### Pawn Promotion

When a pawn reaches the opposite end of the board, it must be promoted.

A pawn may promote to:
- Queen
- Rook
- Bishop
- Knight

The promoted piece replaces the pawn on the promotion square.

## Check

A king is in check when it is under attack by an opposing piece.

A player whose king is in check must make a legal move that removes the check.

A player may get out of check by:
- Moving the king to a safe square.
- Capturing the attacking piece.
- Blocking the attack, if the attacking piece is a rook, bishop, or queen.

A player may not make a move that leaves their own king in check.

## Checkmate

Checkmate occurs when a player's king is in check and the player has no legal move to escape check.

When checkmate occurs, the player delivering checkmate wins the game.

## Stalemate

Stalemate occurs when:
- The current player is not in check.
- The current player has no legal moves.

A stalemate is a draw.

## Draws

A game may end in a draw by:
- Stalemate.
- Agreement between players.
- Threefold repetition, where the same position occurs three times.
- The fifty-move rule, where no pawn has moved and no capture has occurred in fifty moves by each player.
- Insufficient material, where neither player has enough pieces to force checkmate.

## Legal Move Requirements

A legal move must satisfy all of the following:
- The piece belongs to the player whose turn it is.
- The piece moves according to its rules.
- The path is clear, unless the piece is a knight.
- The destination square does not contain a friendly piece.
- The move does not leave the player's own king in check.
- Any special move follows its required conditions.

## Game End Conditions

The game ends when one of the following occurs:
- A player checkmates the opponent.
- The game reaches stalemate.
- A draw condition is met.
- A player resigns.