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

        assertThrows(IllegalArgumentException.class, () -> game.movePiece(6, 0, 6, 0));

        assertEquals(pawn, board.getSquare(6, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_StartRowBelowBounds_ThrowsIndexOutOfBoundsException() {
        Game game = new Game();
        game.initializeGame();

        assertThrows(IndexOutOfBoundsException.class, () -> game.movePiece(-1, 0, 0, 0));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_StartRowAboveBounds_ThrowsIndexOutOfBoundsException() {
        Game game = new Game();
        game.initializeGame();

        assertThrows(IndexOutOfBoundsException.class, () -> game.movePiece(8, 0, 7, 0));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_EndColumnBelowBounds_ThrowsIndexOutOfBoundsException() {
        Game game = new Game();
        game.initializeGame();

        assertThrows(IndexOutOfBoundsException.class, () -> game.movePiece(6, 0, 5, -1));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_EndColumnAboveBounds_ThrowsIndexOutOfBoundsException() {
        Game game = new Game();
        game.initializeGame();

        assertThrows(IndexOutOfBoundsException.class, () -> game.movePiece(6, 0, 5, 8));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_MoveOntoOwnPiece_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKnight = board.getSquare(7, 1);
        Piece whitePawn = board.getSquare(6, 3);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 1, 6, 3)
        );

        assertEquals(whiteKnight, board.getSquare(7, 1));
        assertEquals(whitePawn, board.getSquare(6, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKnightCapturesBlackPawn_RemovesCapturedPieceAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKnight = board.getSquare(7, 1);
        Piece blackPawn = board.getSquare(1, 0);

        board.setSquare(4, 4, whiteKnight);
        board.setSquare(7, 1, null);
        board.setSquare(2, 3, blackPawn);
        board.setSquare(1, 0, null);

        game.movePiece(4, 4, 2, 3);

        assertTrue(board.isEmpty(4, 4));
        assertEquals(whiteKnight, board.getSquare(2, 3));
        assertNotEquals(blackPawn, board.getSquare(2, 3));
        assertEquals(PieceType.KNIGHT, board.getSquare(2, 3).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(2, 3).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKnightCapturesWhitePawn_RemovesCapturedPieceAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKnight = board.getSquare(0, 1);
        Piece whitePawn = board.getSquare(6, 0);

        board.setSquare(5, 3, blackKnight);
        board.setSquare(0, 1, null);
        board.setSquare(3, 2, whitePawn);
        board.setSquare(6, 0, null);

        game.movePiece(6, 7, 5, 7);

        game.movePiece(5, 3, 3, 2);

        assertTrue(board.isEmpty(5, 3));
        assertEquals(blackKnight, board.getSquare(3, 2));
        assertNotEquals(whitePawn, board.getSquare(3, 2));
        assertEquals(PieceType.KNIGHT, board.getSquare(3, 2).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 2).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_InvalidCapturePattern_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKnight = board.getSquare(7, 1);
        Piece blackPawn = board.getSquare(1, 0);

        board.setSquare(4, 4, whiteKnight);
        board.setSquare(7, 1, null);
        board.setSquare(3, 3, blackPawn);
        board.setSquare(1, 0, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(4, 4, 3, 3)
        );

        assertEquals(whiteKnight, board.getSquare(4, 4));
        assertEquals(blackPawn, board.getSquare(3, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKnightCapturesBlackPawnAtBoardEdge_RemovesCapturedPieceAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKnight = board.getSquare(7, 1);
        Piece blackPawn = board.getSquare(1, 0);

        board.setSquare(2, 2, whiteKnight);
        board.setSquare(7, 1, null);
        board.setSquare(0, 1, blackPawn);
        board.setSquare(1, 0, null);

        game.movePiece(2, 2, 0, 1);

        assertTrue(board.isEmpty(2, 2));
        assertEquals(whiteKnight, board.getSquare(0, 1));
        assertNotEquals(blackPawn, board.getSquare(0, 1));
        assertEquals(PieceType.KNIGHT, board.getSquare(0, 1).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 1).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    // Path Blocking
    @Test
    public void MovePiece_QueenMovesOneSquareWithNoIntermediateSquares_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteQueen = board.getSquare(7, 3);

        board.setSquare(4, 4, whiteQueen);
        board.setSquare(7, 3, null);

        game.movePiece(4, 4, 4, 5);

        assertTrue(board.isEmpty(4, 4));
        assertEquals(whiteQueen, board.getSquare(4, 5));
        assertEquals(PieceType.QUEEN, board.getSquare(4, 5).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(4, 5).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
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
