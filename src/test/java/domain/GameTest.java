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

    // Capturing Piece
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

    @Test
    public void MovePiece_RookPathBlockedByOwnPieceInFirstIntermediateSquare_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteRook = board.getSquare(7, 0);
        Piece whitePawn = board.getSquare(6, 0);

        board.setSquare(4, 0, whiteRook);
        board.setSquare(7, 0, null);
        board.setSquare(4, 1, whitePawn);
        board.setSquare(6, 0, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(4, 0, 4, 3)
        );

        assertEquals(whiteRook, board.getSquare(4, 0));
        assertEquals(whitePawn, board.getSquare(4, 1));
        assertTrue(board.isEmpty(4, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BishopPathBlockedByOwnPieceInLastIntermediateSquare_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteBishop = board.getSquare(7, 2);
        Piece whitePawn = board.getSquare(6, 0);

        board.setSquare(4, 4, whiteBishop);
        board.setSquare(7, 2, null);
        board.setSquare(2, 2, whitePawn);
        board.setSquare(6, 0, null);

        board.setSquare(1, 1, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(4, 4, 1, 1)
        );

        assertEquals(whiteBishop, board.getSquare(4, 4));
        assertEquals(whitePawn, board.getSquare(2, 2));
        assertTrue(board.isEmpty(1, 1));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_QueenPathBlockedByOpponentPieceInMiddleIntermediateSquare_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteQueen = board.getSquare(7, 3);
        Piece blackPawn = board.getSquare(1, 0);

        board.setSquare(5, 5, whiteQueen);
        board.setSquare(7, 3, null);
        board.setSquare(3, 3, blackPawn);
        board.setSquare(1, 0, null);
        board.setSquare(1, 1, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(5, 5, 1, 1)
        );

        assertEquals(whiteQueen, board.getSquare(5, 5));
        assertEquals(blackPawn, board.getSquare(3, 3));
        assertTrue(board.isEmpty(1, 1));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_RookMovesVerticallyThroughMultipleEmptySquares_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(6, 0, null);
        board.setSquare(5, 0, null);
        board.setSquare(4, 0, null);

        game.movePiece(7, 0, 3, 0);

        assertTrue(board.isEmpty(7, 0));
        assertEquals(whiteRook, board.getSquare(3, 0));
        assertEquals(PieceType.ROOK, board.getSquare(3, 0).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 0).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BishopCapturesOpponentWithClearDiagonalPath_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteBishop = board.getSquare(7, 2);
        Piece blackPawn = board.getSquare(1, 0);

        board.setSquare(4, 4, whiteBishop);
        board.setSquare(7, 2, null);

        board.setSquare(1, 1, blackPawn);
        board.setSquare(1, 0, null);

        board.setSquare(3, 3, null);
        board.setSquare(2, 2, null);

        game.movePiece(4, 4, 1, 1);

        assertTrue(board.isEmpty(4, 4));
        assertEquals(whiteBishop, board.getSquare(1, 1));
        assertEquals(PieceType.BISHOP, board.getSquare(1, 1).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(1, 1).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_KnightMovesInValidLShapeDespiteNearbyPieces_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKnight = board.getSquare(7, 1);

        game.movePiece(7, 1, 5, 2);

        assertTrue(board.isEmpty(7, 1));
        assertEquals(whiteKnight, board.getSquare(5, 2));
        assertEquals(PieceType.KNIGHT, board.getSquare(5, 2).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(5, 2).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_QueenCaptureAttemptBlockedBeforeDestination_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteQueen = board.getSquare(7, 3);
        Piece blackPawnBlocker = board.getSquare(1, 0);
        Piece blackPawnDestination = board.getSquare(1, 1);

        board.setSquare(4, 0, whiteQueen);
        board.setSquare(7, 3, null);

        board.setSquare(4, 2, blackPawnBlocker);
        board.setSquare(1, 0, null);

        board.setSquare(4, 4, blackPawnDestination);
        board.setSquare(1, 1, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(4, 0, 4, 4)
        );

        assertEquals(whiteQueen, board.getSquare(4, 0));
        assertEquals(blackPawnBlocker, board.getSquare(4, 2));
        assertEquals(blackPawnDestination, board.getSquare(4, 4));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    // Pawn Special Movement
    @Test
    public void MovePiece_WhitePawnMovesTwoSquaresFromStartingRow_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 0);

        assertTrue(board.isEmpty(5, 0));
        assertTrue(board.isEmpty(4, 0));

        game.movePiece(6, 0, 4, 0);

        assertTrue(board.isEmpty(6, 0));
        assertEquals(whitePawn, board.getSquare(4, 0));
        assertEquals(PieceType.PAWN, board.getSquare(4, 0).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(4, 0).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackPawnMovesTwoSquaresFromStartingRow_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 7);
        Piece blackPawn = board.getSquare(1, 0);

        game.movePiece(6, 7, 5, 7);

        assertEquals(whitePawn, board.getSquare(5, 7));
        assertTrue(board.isEmpty(3, 0));
        assertTrue(board.isEmpty(2, 0));

        game.movePiece(1, 0, 3, 0);

        assertTrue(board.isEmpty(1, 0));
        assertEquals(blackPawn, board.getSquare(3, 0));
        assertEquals(PieceType.PAWN, board.getSquare(3, 0).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 0).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnTwoSquareMoveWithIntermediateBlocker_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 0);
        Piece blocker = board.getSquare(6, 1);

        board.setSquare(5, 0, blocker);
        board.setSquare(6, 1, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(6, 0, 4, 0)
        );

        assertEquals(whitePawn, board.getSquare(6, 0));
        assertEquals(blocker, board.getSquare(5, 0));
        assertTrue(board.isEmpty(4, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnTwoSquareMoveWithDestinationOccupied_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 0);
        Piece blackPawn = board.getSquare(1, 0);

        board.setSquare(4, 0, blackPawn);
        board.setSquare(1, 0, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(6, 0, 4, 0)
        );

        assertEquals(whitePawn, board.getSquare(6, 0));
        assertEquals(blackPawn, board.getSquare(4, 0));
        assertTrue(board.isEmpty(5, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnTwoSquareMoveAfterLeavingStartingRow_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 0);

        board.setSquare(5, 0, whitePawn);
        board.setSquare(6, 0, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(5, 0, 3, 0)
        );

        assertEquals(whitePawn, board.getSquare(5, 0));
        assertTrue(board.isEmpty(4, 0));
        assertTrue(board.isEmpty(3, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCapturesDiagonally_RemovesCapturedPieceAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 4);
        Piece blackPawn = board.getSquare(1, 5);

        board.setSquare(4, 4, whitePawn);
        board.setSquare(6, 4, null);

        board.setSquare(3, 5, blackPawn);
        board.setSquare(1, 5, null);

        game.movePiece(4, 4, 3, 5);

        assertTrue(board.isEmpty(4, 4));
        assertEquals(whitePawn, board.getSquare(3, 5));
        assertNotEquals(blackPawn, board.getSquare(3, 5));
        assertEquals(PieceType.PAWN, board.getSquare(3, 5).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 5).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnDiagonalMoveIntoEmptySquare_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 4);

        board.setSquare(4, 4, whitePawn);
        board.setSquare(6, 4, null);

        assertTrue(board.isEmpty(3, 5));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(4, 4, 3, 5)
        );

        assertEquals(whitePawn, board.getSquare(4, 4));
        assertTrue(board.isEmpty(3, 5));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnForwardMoveIntoOccupiedSquare_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whitePawn = board.getSquare(6, 4);
        Piece blackPawn = board.getSquare(1, 4);

        board.setSquare(4, 4, whitePawn);
        board.setSquare(6, 4, null);

        board.setSquare(3, 4, blackPawn);
        board.setSquare(1, 4, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(4, 4, 3, 4)
        );

        assertEquals(whitePawn, board.getSquare(4, 4));
        assertEquals(blackPawn, board.getSquare(3, 4));
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
