# Desktop UI Design

## Scope

The desktop UI provides a playable two-player chess board using the completed backend.

## Structure

- `ui.Main`: application launcher
- `ui.desktop.ChessApplication`: starts Swing and asks the player to choose English or Spanish
- `ui.desktop.ChessWindow`: displays the board and sends player moves to `Game`
- `ui.desktop.PieceSymbols`: maps domain pieces to Unicode chess symbols
- `ui.desktop.BoardCoordinates`: maps board array positions to chess coordinates

## Player Flow

1. Choose English or Spanish.
2. Select a piece belonging to the current player.
3. Select the destination square.
4. The UI calls `Game.movePiece(...)`.
5. The board and turn status refresh after a legal move.
6. Illegal moves display a localized error.
7. Promotion, check, checkmate, and stalemate are displayed through the UI.

The UI does not duplicate chess rules. All move legality and game-state decisions remain in `Game`.

## Running the UI

Run the desktop application with:

```text
./gradlew run
```
