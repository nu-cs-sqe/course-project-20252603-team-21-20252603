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

    @Test
    public void InitializeGame_WithNewGame_KeepsMiddleSquaresEmpty() {
        Game game = new Game();

        game.initializeGame();

        for (int row = 2; row <= 5; row++) {
            for (int col = 0; col < game.getBoard().getSize(); col++) {
                assertTrue(game.getBoard().isEmpty(row, col));
            }
        }
    }

    @Test
    public void InitializeGame_WithAlreadyInitializedGame_ResetsToStandardStartingPosition() {
        Game game = new Game();

        game.initializeGame();
        game.initializeGame();

        assertPiece(game.getBoard(), 7, 4, PieceType.KING, PieceColor.WHITE);
        assertPiece(game.getBoard(), 0, 4, PieceType.KING, PieceColor.BLACK);
        assertPiece(game.getBoard(), 6, 0, PieceType.PAWN, PieceColor.WHITE);
        assertPiece(game.getBoard(), 1, 0, PieceType.PAWN, PieceColor.BLACK);
        assertTrue(game.getBoard().isEmpty(3, 3));
    }

    @Test
    public void MovePiece_ValidWhitePawnMove_UpdatesBoardAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        assertFalse(board.isEmpty(6, 0));
        assertTrue(board.isEmpty(5, 0));

        Piece pawn = board.getSquare(6, 0);

        game.movePiece(6, 0, 5, 0);

        assertTrue(board.isEmpty(6, 0));
        assertEquals(pawn, board.getSquare(5, 0));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WithEmptyStartSquare_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        assertTrue(board.isEmpty(4, 0));

        assertThrows(IllegalArgumentException.class, () -> game.movePiece(4, 0, 3, 0));

        assertTrue(board.isEmpty(4, 0));
        assertTrue(board.isEmpty(3, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WrongPlayerTurn_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();
        Piece blackPawn = board.getSquare(1, 0);

        assertThrows(IllegalArgumentException.class, () -> game.movePiece(1, 0, 2, 0));

        assertEquals(blackPawn, board.getSquare(1, 0));
        assertTrue(board.isEmpty(2, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_InvalidMovementPattern_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();
        Piece whitePawn = board.getSquare(6, 0);
        Piece otherWhitePawn = board.getSquare(6, 1);

        assertThrows(IllegalArgumentException.class, () -> game.movePiece(6, 0, 6, 1));

        assertEquals(whitePawn, board.getSquare(6, 0));
        assertEquals(otherWhitePawn, board.getSquare(6, 1));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_SameSquareMove_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();
        Piece pawn = board.getSquare(6, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(6, 0, 6, 0)
        );

        assertEquals(pawn, board.getSquare(6, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
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
