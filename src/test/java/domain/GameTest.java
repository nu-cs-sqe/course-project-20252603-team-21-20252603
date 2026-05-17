package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void InitializeGame_WithNewGame_CreatesBoard() {
        Game game = new Game();

        game.initializeGame();

        assertNotNull(game.getBoard());
    }

    @Test
    public void InitializeGame_WithNewGame_CreatesBoardWithSizeEight() {
        Game game = new Game();

        game.initializeGame();

        assertEquals(8, game.getBoard().getSize());
    }

    @Test
    public void InitializeGame_WithNewGame_SetsCurrentTurnToWhite() {
        Game game = new Game();

        game.initializeGame();

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void InitializeGame_WithNewGame_PlacesWhiteKing() {
        Game game = new Game();

        game.initializeGame();

        assertPiece(game.getBoard(), 7, 4, PieceType.KING, PieceColor.WHITE);
    }

    @Test
    public void InitializeGame_WithNewGame_PlacesBlackKing() {
        Game game = new Game();

        game.initializeGame();

        assertPiece(game.getBoard(), 0, 4, PieceType.KING, PieceColor.BLACK);
    }

    @Test
    public void InitializeGame_WithNewGame_PlacesWhitePawn() {
        Game game = new Game();

        game.initializeGame();

        assertPiece(game.getBoard(), 6, 0, PieceType.PAWN, PieceColor.WHITE);
    }

    @Test
    public void InitializeGame_WithNewGame_PlacesBlackPawn() {
        Game game = new Game();

        game.initializeGame();

        assertPiece(game.getBoard(), 1, 0, PieceType.PAWN, PieceColor.BLACK);
    }

    private void assertPiece(
            Board board,
            int row,
            int col,
            PieceType expectedType,
            PieceColor expectedColor
    ) {
        Piece piece = board.getSquare(row, col);

        assertEquals(expectedType, piece.getType());
        assertEquals(expectedColor, piece.getColor());
    }
}
