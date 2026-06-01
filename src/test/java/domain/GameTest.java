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

        board.setSquare(5, 1, blackKnight);
        board.setSquare(0, 1, null);
        board.setSquare(3, 2, whitePawn);
        board.setSquare(6, 0, null);
        board.setSquare(7, 0, null);

        game.movePiece(7, 6, 5, 7);

        game.movePiece(5, 1, 3, 2);

        assertTrue(board.isEmpty(5, 1));
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

    @Test
    public void MovePiece_BlackPawnCapturesDiagonally_RemovesCapturedPieceAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackPawn = board.getSquare(1, 3);
        Piece whitePawn = board.getSquare(6, 4);

        board.setSquare(3, 3, blackPawn);
        board.setSquare(1, 3, null);

        board.setSquare(4, 4, whitePawn);
        board.setSquare(6, 4, null);

        game.movePiece(6, 7, 5, 7);

        game.movePiece(3, 3, 4, 4);

        assertTrue(board.isEmpty(3, 3));
        assertEquals(blackPawn, board.getSquare(4, 4));
        assertNotEquals(whitePawn, board.getSquare(4, 4));
        assertEquals(PieceType.PAWN, board.getSquare(4, 4).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(4, 4).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    // Castling
    @Test
    public void MovePiece_WhiteKingsideCastle_UpdatesKingAndRookPositionsAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);

        game.movePiece(7, 4, 7, 6);

        assertTrue(board.isEmpty(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 5));
        assertEquals(whiteKing, board.getSquare(7, 6));
        assertTrue(board.isEmpty(7, 7));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteQueensideCastle_UpdatesKingAndRookPositionsAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);
        board.setSquare(7, 3, null);

        game.movePiece(7, 4, 7, 2);

        assertTrue(board.isEmpty(7, 4));
        assertTrue(board.isEmpty(7, 0));
        assertEquals(whiteKing, board.getSquare(7, 2));
        assertEquals(whiteRook, board.getSquare(7, 3));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKingsideCastle_UpdatesKingAndRookPositionsAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        game.movePiece(6, 0, 5, 0); // white move first

        game.movePiece(0, 4, 0, 6);

        assertTrue(board.isEmpty(0, 4));
        assertEquals(blackRook, board.getSquare(0, 5));
        assertEquals(blackKing, board.getSquare(0, 6));
        assertTrue(board.isEmpty(0, 7));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackQueensideCastle_UpdatesKingAndRookPositionsAndSwitchesTurn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 0);

        board.setSquare(0, 1, null);
        board.setSquare(0, 2, null);
        board.setSquare(0, 3, null);

        game.movePiece(6, 0, 5, 0); // white move first

        game.movePiece(0, 4, 0, 2);

        assertTrue(board.isEmpty(0, 4));
        assertTrue(board.isEmpty(0, 0));
        assertEquals(blackKing, board.getSquare(0, 2));
        assertEquals(blackRook, board.getSquare(0, 3));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleWithOccupiedF1_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);
        Piece blocker = board.getSquare(7, 5);

        board.setSquare(7, 6, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(blocker, board.getSquare(7, 5));
        assertTrue(board.isEmpty(7, 6));
        assertEquals(whiteRook, board.getSquare(7, 7));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteQueensideCastleWithBlockedPath_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);
        Piece blocker = board.getSquare(7, 3);

        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 2)
        );

        assertEquals(whiteRook, board.getSquare(7, 0));
        assertTrue(board.isEmpty(7, 1));
        assertTrue(board.isEmpty(7, 2));
        assertEquals(blocker, board.getSquare(7, 3));
        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleWithoutRook_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);
        board.setSquare(7, 7, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(7, 5));
        assertTrue(board.isEmpty(7, 6));
        assertTrue(board.isEmpty(7, 7));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleWithOpponentRook_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);
        board.setSquare(7, 7, blackRook);
        board.setSquare(0, 7, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(7, 5));
        assertTrue(board.isEmpty(7, 6));
        assertEquals(blackRook, board.getSquare(7, 7));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingCastlingLikeMoveFromNonStartingSquare_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);

        board.setSquare(5, 4, whiteKing);
        board.setSquare(7, 4, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(5, 4, 5, 6)
        );

        assertEquals(whiteKing, board.getSquare(5, 4));
        assertTrue(board.isEmpty(5, 5));
        assertTrue(board.isEmpty(5, 6));
        assertTrue(board.isEmpty(7, 4));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleWithOccupiedDestination_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);
        Piece blocker = board.getSquare(7, 6);

        board.setSquare(7, 5, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(7, 5));
        assertEquals(blocker, board.getSquare(7, 6));
        assertEquals(whiteRook, board.getSquare(7, 7));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingCastlingLikeMoveToInvalidDestination_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);
        board.setSquare(7, 3, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 1)
        );

        assertEquals(whiteRook, board.getSquare(7, 0));
        assertTrue(board.isEmpty(7, 1));
        assertTrue(board.isEmpty(7, 2));
        assertTrue(board.isEmpty(7, 3));
        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_NonKingTwoSquareHorizontalMove_DoesNotCastleRook() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteQueen = board.getSquare(7, 3);
        Piece whiteRook = board.getSquare(7, 7);
        Piece whiteBishop = board.getSquare(7, 5);

        board.setSquare(4, 4, whiteQueen);
        board.setSquare(7, 3, null);
        board.setSquare(4, 5, null);
        board.setSquare(4, 6, null);

        game.movePiece(4, 4, 4, 6);

        assertTrue(board.isEmpty(4, 4));
        assertEquals(whiteQueen, board.getSquare(4, 6));

        assertEquals(whiteRook, board.getSquare(7, 7));
        assertEquals(whiteBishop, board.getSquare(7, 5));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    // Check Detection
    @Test
    public void IsKingInCheck_WhiteKingNotAttacked_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();
        board.setSquare(0, 0, null);
        board.setSquare(0, 1, null);
        board.setSquare(0, 2, null);
        board.setSquare(0, 3, null);
        board.setSquare(0, 4, null);
        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);
        board.setSquare(0, 7, null);
        board.setSquare(1, 0, null);
        board.setSquare(1, 1, null);
        board.setSquare(1, 2, null);
        board.setSquare(1, 3, null);
        board.setSquare(1, 4, null);
        board.setSquare(1, 5, null);
        board.setSquare(1, 6, null);
        board.setSquare(1, 7, null);

        assertFalse(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackRook_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.BLACK)
        );

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackBishop_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 3, null);
        board.setSquare(5, 2, null);

        board.setSquare(
                4,
                1,
                new Piece(PieceType.BISHOP, PieceColor.BLACK)
        );

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackQueenDiagonally_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 5, null);
        board.setSquare(5, 6, null);

        board.setSquare(
                4,
                7,
                new Piece(PieceType.QUEEN, PieceColor.BLACK)
        );

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackQueenVertically_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                0,
                4,
                new Piece(PieceType.QUEEN, PieceColor.BLACK)
        );

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackKnight_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(
                5,
                5,
                new Piece(PieceType.KNIGHT, PieceColor.BLACK)
        );

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingNotAttackedByBlackKnight_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(
                6,
                5,
                new Piece(PieceType.KNIGHT, PieceColor.BLACK)
        );

        assertFalse(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackPawn_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(
                6,
                3,
                new Piece(PieceType.PAWN, PieceColor.BLACK)
        );

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingNotAttackedByBlackPawnDirectlyInFront_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(
                6,
                4,
                new Piece(PieceType.PAWN, PieceColor.BLACK)
        );

        assertFalse(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingAttackedByAdjacentBlackKing_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(0, 4, null);

        board.setSquare(
                6,
                4,
                new Piece(PieceType.KING, PieceColor.BLACK)
        );

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingNotAttackedByNonAdjacentBlackKing_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(0, 4, null);

        board.setSquare(
                5,
                4,
                new Piece(PieceType.KING, PieceColor.BLACK)
        );

        assertFalse(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingNotAttackedWhenRookPathBlocked_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                4,
                4,
                new Piece(PieceType.BISHOP, PieceColor.WHITE)
        );

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.BLACK)
        );

        assertFalse(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_WhiteKingNotAttackedByFriendlyRook_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.WHITE)
        );

        assertFalse(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_BlackKingAttackedByWhiteRook_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(7, 4, null);

        board.setSquare(1, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(6, 4, null);

        board.setSquare(
                7,
                4,
                new Piece(PieceType.ROOK, PieceColor.WHITE)
        );

        assertTrue(game.isKingInCheck(PieceColor.BLACK));
    }

    @Test
    public void IsKingInCheck_WhiteKingMissing_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(7, 4, null);

        assertThrows(
                IllegalStateException.class,
                () -> game.isKingInCheck(PieceColor.WHITE)
        );
    }

    // Self Check Detection

    @Test
    public void MovePiece_RookMovesWithoutExposingWhiteKingToCheck_MoveSucceeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(6, 0, null);

        game.movePiece(7, 0, 6, 0);

        assertTrue(board.isEmpty(7, 0));
        assertEquals(whiteRook, board.getSquare(6, 0));
        assertEquals(PieceType.ROOK, board.getSquare(6, 0).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(6, 0).getColor());
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BishopMoveExposesWhiteKingToRookCheck_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteBishop = board.getSquare(7, 2);
        Piece blackRook = board.getSquare(0, 0);

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(6, 4, whiteBishop);
        board.setSquare(7, 2, null);

        board.setSquare(0, 4, blackRook);
        board.setSquare(0, 0, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(6, 4, 5, 3)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteBishop, board.getSquare(6, 4));
        assertTrue(board.isEmpty(5, 3));
        assertEquals(blackRook, board.getSquare(0, 4));
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingMovesIntoRookCheck_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.BLACK)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 6, 4)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(6, 4));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingCapturesRookAndEscapesCheck_MoveSucceeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);

        board.setSquare(6, 4,
                new Piece(PieceType.ROOK, PieceColor.BLACK));

        board.setSquare(0, 4, null);

        board.setSquare(6, 3, null);
        board.setSquare(6, 5, null);

        game.movePiece(7, 4, 6, 4);

        assertTrue(board.isEmpty(7, 4));
        assertEquals(whiteKing, board.getSquare(6, 4));
        assertEquals(PieceType.KING, board.getSquare(6, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(6, 4).getColor());
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingCapturesDefendedRook_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece blackRook = new Piece(PieceType.ROOK, PieceColor.BLACK);
        Piece blackBishop = new Piece(PieceType.BISHOP, PieceColor.BLACK);

        board.setSquare(6, 4, blackRook);
        board.setSquare(3, 7, blackBishop);

        board.setSquare(0, 4, null);
        board.setSquare(5, 5, null);
        board.setSquare(4, 6, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 6, 4)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(blackRook, board.getSquare(6, 4));
        assertEquals(blackBishop, board.getSquare(3, 7));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BishopBlocksRookCheck_MoveSucceeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteBishop = board.getSquare(7, 2);

        board.setSquare(6, 3, null);
        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.BLACK)
        );

        game.movePiece(7, 2, 5, 4);

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(7, 2));
        assertEquals(whiteBishop, board.getSquare(5, 4));
        assertEquals(PieceType.BISHOP, board.getSquare(5, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(5, 4).getColor());
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_KnightMoveDoesNotResolveRookCheck_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteKnight = board.getSquare(7, 6);

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.BLACK)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 6, 5, 7)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteKnight, board.getSquare(7, 6));
        assertTrue(board.isEmpty(5, 7));
        assertTrue(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_RookCapturesCheckingRook_MoveSucceeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);
        Piece blackRook = new Piece(PieceType.ROOK, PieceColor.BLACK);

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(0, 4, blackRook);

        board.setSquare(0, 7, whiteRook);
        board.setSquare(7, 7, null);
        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        game.movePiece(0, 7, 0, 4);

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(0, 7));
        assertEquals(whiteRook, board.getSquare(0, 4));
        assertNotEquals(blackRook, board.getSquare(0, 4));
        assertEquals(PieceType.ROOK, board.getSquare(0, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 4).getColor());
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BishopMoveDoesNotResolveKnightCheck_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteBishop = board.getSquare(7, 2);

        board.setSquare(
                5,
                5,
                new Piece(PieceType.KNIGHT, PieceColor.BLACK)
        );

        board.setSquare(6, 3, null);
        board.setSquare(6, 4, null);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 2, 6, 3)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteBishop, board.getSquare(7, 2));
        assertTrue(board.isEmpty(6, 3));
        assertTrue(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BishopMoveExposesBlackKingToRookCheck_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackBishop = new Piece(PieceType.BISHOP, PieceColor.BLACK);
        Piece whiteRook = new Piece(PieceType.ROOK, PieceColor.WHITE);

        board.setSquare(1, 4, blackBishop);

        board.setSquare(2, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(6, 4, null);

        board.setSquare(7, 4, whiteRook);
        board.setSquare(7, 0, new Piece(PieceType.KING, PieceColor.WHITE));

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(1, 4, 2, 3)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackBishop, board.getSquare(1, 4));
        assertTrue(board.isEmpty(2, 3));
        assertFalse(game.isKingInCheck(PieceColor.BLACK));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BishopBlocksBlackKingRookCheck_MoveSucceeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackBishop = new Piece(PieceType.BISHOP, PieceColor.BLACK);
        board.setSquare(0, 3, blackBishop);

        board.setSquare(1, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(6, 4, null);

        board.setSquare(
                7,
                4,
                new Piece(PieceType.ROOK, PieceColor.WHITE)
        );

        board.setSquare(
                7,
                0,
                new Piece(PieceType.KING, PieceColor.WHITE)
        );

        game.movePiece(6, 0, 5, 0);

        game.movePiece(0, 3, 1, 4);

        assertEquals(blackKing, board.getSquare(0, 4));
        assertTrue(board.isEmpty(0, 3));
        assertEquals(blackBishop, board.getSquare(1, 4));
        assertEquals(PieceType.BISHOP, board.getSquare(1, 4).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(1, 4).getColor());
        assertFalse(game.isKingInCheck(PieceColor.BLACK));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_PinnedRookCapturesCheckingRook_MoveSucceeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);
        Piece blackRook = new Piece(PieceType.ROOK, PieceColor.BLACK);

        board.setSquare(6, 4, whiteRook);
        board.setSquare(7, 0, null);

        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(0, 4, blackRook);

        game.movePiece(6, 4, 0, 4);

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(6, 4));
        assertEquals(whiteRook, board.getSquare(0, 4));
        assertNotEquals(blackRook, board.getSquare(0, 4));
        assertEquals(PieceType.ROOK, board.getSquare(0, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 4).getColor());
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    // Checkmate Detection
    @Test
    public void IsCheckmate_WhiteKingNotInCheck_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        assertFalse(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteKingCanMoveOutOfRookCheck_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(7, 3, null);

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.BLACK)
        );

        assertFalse(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteBishopCanBlockRookCheck_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(
                7,
                3,
                new Piece(PieceType.BISHOP, PieceColor.WHITE)
        );

        board.setSquare(
                0,
                4,
                new Piece(PieceType.ROOK, PieceColor.BLACK)
        );

        assertFalse(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteRookCanCaptureCheckingRook_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(0, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));

        board.setSquare(0, 7, whiteRook);
        board.setSquare(7, 7, null);
        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        assertFalse(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteKingTrappedByProtectedQueen_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(6, 4, new Piece(PieceType.QUEEN, PieceColor.BLACK));
        board.setSquare(3, 7, new Piece(PieceType.BISHOP, PieceColor.BLACK));

        assertTrue(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteKingCorneredByProtectedQueen_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(6, 6, new Piece(PieceType.QUEEN, PieceColor.BLACK));
        board.setSquare(4, 4, new Piece(PieceType.BISHOP, PieceColor.BLACK));

        assertTrue(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteKingCanCaptureCheckingKnight_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(5, 5, new Piece(PieceType.KNIGHT, PieceColor.BLACK));

        assertFalse(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteKingTrappedByProtectedKnight_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 5, new Piece(PieceType.KNIGHT, PieceColor.BLACK));
        board.setSquare(3, 7, new Piece(PieceType.BISHOP, PieceColor.BLACK));
        board.setSquare(0, 3, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(0, 5, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(4, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(4, 7, new Piece(PieceType.BISHOP, PieceColor.BLACK));
        board.setSquare(5, 7, new Piece(PieceType.BISHOP, PieceColor.BLACK));

        assertTrue(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteKingInDoubleCheckWithEscapeSquare_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(0, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(4, 1, new Piece(PieceType.BISHOP, PieceColor.BLACK));

        board.setSquare(5, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(5, 5, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertFalse(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_WhiteKingInDoubleCheckWithoutEscape_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(0, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(4, 1, new Piece(PieceType.BISHOP, PieceColor.BLACK));

        board.setSquare(5, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(5, 5, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(7, 3, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(0, 3, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertTrue(game.isCheckmate(PieceColor.WHITE));
    }

    @Test
    public void IsCheckmate_BlackBishopCanBlockRookCheck_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(7, 0, new Piece(PieceType.KING, PieceColor.WHITE));

        board.setSquare(7, 4, new Piece(PieceType.ROOK, PieceColor.WHITE));
        board.setSquare(0, 3, new Piece(PieceType.BISHOP, PieceColor.BLACK));

        assertFalse(game.isCheckmate(PieceColor.BLACK));
    }

    @Test
    public void IsCheckmate_BlackKingCorneredByProtectedQueen_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(0, 7, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(7, 0, new Piece(PieceType.KING, PieceColor.WHITE));

        board.setSquare(1, 6, new Piece(PieceType.QUEEN, PieceColor.WHITE));
        board.setSquare(3, 4, new Piece(PieceType.BISHOP, PieceColor.WHITE));

        assertTrue(game.isCheckmate(PieceColor.BLACK));
    }

    @Test
    public void IsCheckmate_WhiteKingMissing_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(7, 4, null);

        assertThrows(
                IllegalStateException.class,
                () -> game.isCheckmate(PieceColor.WHITE)
        );
    }

    // Stalemate Detection
    @Test
    public void IsStalemate_WhiteKingNotInCheckAndHasLegalMove_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        assertFalse(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhiteKingHasNoLegalMoves_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 6, new Piece(PieceType.QUEEN, PieceColor.BLACK));

        assertTrue(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhiteKingHasOneLegalEscapeSquare_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 6, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertFalse(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhiteKingHasNoLegalMovesButRookCanMove_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(7, 0, new Piece(PieceType.ROOK, PieceColor.WHITE));

        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 6, new Piece(PieceType.QUEEN, PieceColor.BLACK));

        assertFalse(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhiteKingHasNoLegalMovesButBishopCanMove_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(7, 0, new Piece(PieceType.BISHOP, PieceColor.WHITE));

        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 6, new Piece(PieceType.QUEEN, PieceColor.BLACK));

        assertFalse(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhitePinnedBishopHasNoLegalMove_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(6, 4, new Piece(PieceType.BISHOP, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(0, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));

        board.setSquare(5, 3, new Piece(PieceType.QUEEN, PieceColor.BLACK));
        board.setSquare(5, 5, new Piece(PieceType.QUEEN, PieceColor.BLACK));

        assertTrue(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhitePawnHasLegalForwardMove_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(6, 0, new Piece(PieceType.PAWN, PieceColor.WHITE));

        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 6, new Piece(PieceType.QUEEN, PieceColor.BLACK));

        assertFalse(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhitePawnBlockedAndNoLegalMoves_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(6, 0, new Piece(PieceType.PAWN, PieceColor.WHITE));

        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 6, new Piece(PieceType.QUEEN, PieceColor.BLACK));
        board.setSquare(5, 0, new Piece(PieceType.PAWN, PieceColor.BLACK));

        assertTrue(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_WhiteKingHasNoLegalMovesButIsInCheck_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 7, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(5, 6, new Piece(PieceType.QUEEN, PieceColor.BLACK));
        board.setSquare(7, 0, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertFalse(game.isStalemate(PieceColor.WHITE));
    }

    @Test
    public void IsStalemate_BlackKingHasNoLegalMoves_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(0, 7, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(7, 0, new Piece(PieceType.KING, PieceColor.WHITE));

        board.setSquare(2, 6, new Piece(PieceType.QUEEN, PieceColor.WHITE));

        assertTrue(game.isStalemate(PieceColor.BLACK));
    }

    @Test
    public void IsStalemate_BlackKingNotInCheckAndHasLegalMove_ReturnsFalse() {
        Game game = new Game();
        game.initializeGame();

        assertFalse(game.isStalemate(PieceColor.BLACK));
    }

    @Test
    public void IsStalemate_WhiteKingMissing_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(7, 4, null);

        assertThrows(
                IllegalStateException.class,
                () -> game.isStalemate(PieceColor.WHITE)
        );
    }

    // Pawn Promotion
    @Test
    public void MovePiece_WhitePawnPromotesToQueen_ReturnsQueenOnFinalRank() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 0, new Piece(PieceType.PAWN, PieceColor.WHITE));

        game.movePiece(1, 0, 0, 0);

        assertTrue(board.isEmpty(1, 0));
        assertEquals(PieceType.QUEEN, board.getSquare(0, 0).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 0).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnPromotesToRook_ReturnsRookOnFinalRank() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 7, new Piece(PieceType.PAWN, PieceColor.WHITE));

        game.movePiece(1, 7, 0, 7, PieceType.ROOK);

        assertTrue(board.isEmpty(1, 7));
        assertEquals(PieceType.ROOK, board.getSquare(0, 7).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 7).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackPawnPromotesToQueen_ReturnsQueenOnFinalRank() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(6, 0, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(6, 0, 7, 0);

        assertTrue(board.isEmpty(6, 0));
        assertEquals(PieceType.QUEEN, board.getSquare(7, 0).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(7, 0).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackPawnPromotesToBishop_ReturnsBishopOnFinalRank() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(6, 7, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(6, 7, 7, 7, PieceType.BISHOP);

        assertTrue(board.isEmpty(6, 7));
        assertEquals(PieceType.BISHOP, board.getSquare(7, 7).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(7, 7).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCapturesAndPromotesToKnight_ReturnsKnightOnFinalRank() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 6, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(0, 7, new Piece(PieceType.ROOK, PieceColor.BLACK));

        game.movePiece(1, 6, 0, 7, PieceType.KNIGHT);

        assertTrue(board.isEmpty(1, 6));
        assertEquals(PieceType.KNIGHT, board.getSquare(0, 7).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 7).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackPawnCapturesAndPromotesToRook_ReturnsRookOnFinalRank() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(6, 1, new Piece(PieceType.PAWN, PieceColor.BLACK));
        board.setSquare(7, 0, new Piece(PieceType.ROOK, PieceColor.WHITE));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(6, 1, 7, 0, PieceType.ROOK);

        assertTrue(board.isEmpty(6, 1));
        assertEquals(PieceType.ROOK, board.getSquare(7, 0).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(7, 0).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnMovesWithoutPromotion_RemainsPawn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(2, 0, new Piece(PieceType.PAWN, PieceColor.WHITE));

        game.movePiece(2, 0, 1, 0);

        assertTrue(board.isEmpty(2, 0));
        assertEquals(PieceType.PAWN, board.getSquare(1, 0).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(1, 0).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackPawnMovesWithoutPromotion_RemainsPawn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(5, 0, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(5, 0, 6, 0);

        assertTrue(board.isEmpty(5, 0));
        assertEquals(PieceType.PAWN, board.getSquare(6, 0).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(6, 0).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteRookMovesToFinalRank_RemainsRook() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 0, new Piece(PieceType.ROOK, PieceColor.WHITE));

        game.movePiece(1, 0, 0, 0);

        assertTrue(board.isEmpty(1, 0));
        assertEquals(PieceType.ROOK, board.getSquare(0, 0).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 0).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnPromotesToKing_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 0, new Piece(PieceType.PAWN, PieceColor.WHITE));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(1, 0, 0, 0, PieceType.KING)
        );

        assertEquals(PieceType.PAWN, board.getSquare(1, 0).getType());
        assertTrue(board.isEmpty(0, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnPromotesToPawn_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 0, new Piece(PieceType.PAWN, PieceColor.WHITE));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(1, 0, 0, 0, PieceType.PAWN)
        );

        assertEquals(PieceType.PAWN, board.getSquare(1, 0).getType());
        assertTrue(board.isEmpty(0, 0));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnPromotesWithoutPromotionType_DefaultsToQueen() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 0, new Piece(PieceType.PAWN, PieceColor.WHITE));

        game.movePiece(1, 0, 0, 0);

        assertTrue(board.isEmpty(1, 0));
        assertEquals(PieceType.QUEEN, board.getSquare(0, 0).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(0, 0).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnPromotionExposesKingToCheck_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        Piece whiteKing = new Piece(PieceType.KING, PieceColor.WHITE);
        Piece whitePawn = new Piece(PieceType.PAWN, PieceColor.WHITE);

        board.setSquare(2, 7, whiteKing);
        board.setSquare(1, 6, whitePawn);
        board.setSquare(0, 5, new Piece(PieceType.BISHOP, PieceColor.BLACK));
        board.setSquare(0, 0, new Piece(PieceType.KING, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(1, 6, 0, 6)
        );

        assertEquals(whiteKing, board.getSquare(2, 7));
        assertEquals(whitePawn, board.getSquare(1, 6));
        assertTrue(board.isEmpty(0, 6));
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    // En Passant
    @Test
    public void MovePiece_WhitePawnCapturesEnPassant_RemovesBlackPawn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(1, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(1, 3, 3, 3);

        game.movePiece(3, 4, 2, 3);

        assertTrue(board.isEmpty(3, 4));
        assertTrue(board.isEmpty(3, 3));
        assertEquals(PieceType.PAWN, board.getSquare(2, 3).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(2, 3).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackPawnCapturesEnPassant_RemovesWhitePawn() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(4, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));
        board.setSquare(6, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));

        game.movePiece(6, 4, 4, 4);

        game.movePiece(4, 3, 5, 4);

        assertTrue(board.isEmpty(4, 3));
        assertTrue(board.isEmpty(4, 4));
        assertEquals(PieceType.PAWN, board.getSquare(5, 4).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(5, 4).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCannotEnPassantAfterOneSquarePawnMove_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(2, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(2, 3, 3, 3);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(PieceType.PAWN, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertEquals(PieceType.PAWN, board.getSquare(3, 3).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 3).getColor());
        assertTrue(board.isEmpty(2, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCannotEnPassantAfterKnightMove_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(3, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));
        board.setSquare(2, 1, new Piece(PieceType.KNIGHT, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(2, 1, 4, 2);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(PieceType.PAWN, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertEquals(PieceType.PAWN, board.getSquare(3, 3).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 3).getColor());
        assertTrue(board.isEmpty(2, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCannotEnPassantAfterWindowExpires_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(1, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(1, 3, 3, 3);

        game.movePiece(7, 3, 7, 4);

        game.movePiece(0, 4, 0, 3);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(PieceType.PAWN, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertEquals(PieceType.PAWN, board.getSquare(3, 3).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 3).getColor());
        assertTrue(board.isEmpty(2, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCannotEnPassantNonPawn_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(3, 3, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(PieceType.PAWN, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertEquals(PieceType.ROOK, board.getSquare(3, 3).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 3).getColor());
        assertTrue(board.isEmpty(2, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCannotEnPassantWhenAdjacentSquareEmpty_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(PieceType.PAWN, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertTrue(board.isEmpty(3, 3));
        assertTrue(board.isEmpty(2, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCannotEnPassantWhenDestinationOccupied_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(3, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));
        board.setSquare(2, 3, new Piece(PieceType.KNIGHT, PieceColor.WHITE));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(PieceType.PAWN, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertEquals(PieceType.PAWN, board.getSquare(3, 3).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 3).getColor());
        assertEquals(PieceType.KNIGHT, board.getSquare(2, 3).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(2, 3).getColor());
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnCannotEnPassantFromWrongRank_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(4, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(1, 1, new Piece(PieceType.PAWN, PieceColor.BLACK));
        board.setSquare(4, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(1, 1, 3, 1);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(4, 4, 3, 3)
        );

        assertEquals(PieceType.PAWN, board.getSquare(4, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(4, 4).getColor());
        assertEquals(PieceType.PAWN, board.getSquare(4, 3).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(4, 3).getColor());
        assertTrue(board.isEmpty(3, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackPawnCannotEnPassantFromWrongRank_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 3, new Piece(PieceType.PAWN, PieceColor.BLACK));
        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(6, 6, new Piece(PieceType.PAWN, PieceColor.WHITE));

        game.movePiece(6, 6, 4, 6);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 3, 4, 4)
        );

        assertEquals(PieceType.PAWN, board.getSquare(3, 3).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 3).getColor());
        assertEquals(PieceType.PAWN, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertTrue(board.isEmpty(4, 4));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteNonPawnCannotMoveEnPassantShape_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.ROOK, PieceColor.WHITE));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(PieceType.ROOK, board.getSquare(3, 4).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(3, 4).getColor());
        assertTrue(board.isEmpty(2, 3));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteEnPassantExposesKingToCheck_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        Piece whiteKing = new Piece(PieceType.KING, PieceColor.WHITE);
        Piece whitePawn = new Piece(PieceType.PAWN, PieceColor.WHITE);
        Piece blackPawn = new Piece(PieceType.PAWN, PieceColor.BLACK);

        board.setSquare(3, 7, whiteKing);
        board.setSquare(3, 4, whitePawn);
        board.setSquare(7, 7, new Piece(PieceType.ROOK, PieceColor.WHITE));

        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));
        board.setSquare(1, 3, blackPawn);
        board.setSquare(3, 0, new Piece(PieceType.ROOK, PieceColor.BLACK));

        game.movePiece(7, 7, 7, 6);

        game.movePiece(1, 3, 3, 3);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(3, 4, 2, 3)
        );

        assertEquals(whiteKing, board.getSquare(3, 7));
        assertEquals(whitePawn, board.getSquare(3, 4));
        assertEquals(blackPawn, board.getSquare(3, 3));
        assertTrue(board.isEmpty(2, 3));
        assertFalse(game.isKingInCheck(PieceColor.WHITE));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhitePawnNormalDiagonalCaptureAfterTwoSquareMoveElsewhere_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(7, 4, new Piece(PieceType.KING, PieceColor.WHITE));
        board.setSquare(0, 4, new Piece(PieceType.KING, PieceColor.BLACK));

        board.setSquare(3, 4, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(2, 5, new Piece(PieceType.ROOK, PieceColor.BLACK));
        board.setSquare(1, 0, new Piece(PieceType.PAWN, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 3);

        game.movePiece(1, 0, 3, 0);

        game.movePiece(3, 4, 2, 5);

        assertTrue(board.isEmpty(3, 4));
        assertEquals(PieceType.PAWN, board.getSquare(2, 5).getType());
        assertEquals(PieceColor.WHITE, board.getSquare(2, 5).getColor());
        assertEquals(PieceType.PAWN, board.getSquare(3, 0).getType());
        assertEquals(PieceColor.BLACK, board.getSquare(3, 0).getColor());
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    // Castling Legality
    @Test
    public void MovePiece_WhiteKingsideCastleWhileInCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);
        board.setSquare(0, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 7));
        assertTrue(board.isEmpty(7, 5));
        assertTrue(board.isEmpty(7, 6));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleThroughCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);

        board.setSquare(6, 5, null);
        board.setSquare(5, 5, null);
        board.setSquare(4, 5, null);
        board.setSquare(3, 5, null);
        board.setSquare(2, 5, null);
        board.setSquare(1, 5, null);

        board.setSquare(0, 5, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 7));
        assertTrue(board.isEmpty(7, 5));
        assertTrue(board.isEmpty(7, 6));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleIntoCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);

        board.setSquare(6, 6, null);
        board.setSquare(5, 6, null);
        board.setSquare(4, 6, null);
        board.setSquare(3, 6, null);
        board.setSquare(2, 6, null);
        board.setSquare(1, 6, null);

        board.setSquare(0, 6, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 7));
        assertTrue(board.isEmpty(7, 5));
        assertTrue(board.isEmpty(7, 6));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteQueensideCastleWhileInCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);
        board.setSquare(7, 3, null);

        board.setSquare(6, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(1, 4, null);

        board.setSquare(0, 4, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 2)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 0));

        assertTrue(board.isEmpty(7, 1));
        assertTrue(board.isEmpty(7, 2));
        assertTrue(board.isEmpty(7, 3));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteQueensideCastleThroughCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);
        board.setSquare(7, 3, null);

        board.setSquare(6, 3, null);
        board.setSquare(5, 3, null);
        board.setSquare(4, 3, null);
        board.setSquare(3, 3, null);
        board.setSquare(2, 3, null);
        board.setSquare(1, 3, null);

        board.setSquare(0, 3, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 2)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 0));

        assertTrue(board.isEmpty(7, 1));
        assertTrue(board.isEmpty(7, 2));
        assertTrue(board.isEmpty(7, 3));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteQueensideCastleIntoCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);
        board.setSquare(7, 3, null);

        board.setSquare(6, 2, null);
        board.setSquare(5, 2, null);
        board.setSquare(4, 2, null);
        board.setSquare(3, 2, null);
        board.setSquare(2, 2, null);
        board.setSquare(1, 2, null);

        board.setSquare(0, 2, new Piece(PieceType.ROOK, PieceColor.BLACK));

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 2)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 0));

        assertTrue(board.isEmpty(7, 1));
        assertTrue(board.isEmpty(7, 2));
        assertTrue(board.isEmpty(7, 3));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKingsideCastleWhileInCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        board.setSquare(1, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(6, 4, null);

        Piece whiteKing = board.getSquare(7, 4);
        board.setSquare(6, 7, whiteKing);
        board.setSquare(7, 4, board.getSquare(7, 0));
        board.setSquare(7, 0, null);

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 6)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 7));

        assertTrue(board.isEmpty(0, 5));
        assertTrue(board.isEmpty(0, 6));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKingsideCastleThroughCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        board.setSquare(1, 5, null);
        board.setSquare(2, 5, null);
        board.setSquare(3, 5, null);
        board.setSquare(4, 5, null);
        board.setSquare(5, 5, null);
        board.setSquare(6, 5, null);

        Piece whiteKing = board.getSquare(7, 4);
        board.setSquare(6, 7, whiteKing);
        board.setSquare(7, 4, board.getSquare(7, 0));
        board.setSquare(7, 0, null);

        board.setSquare(7, 5, new Piece(PieceType.ROOK, PieceColor.WHITE));

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 6)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 7));

        assertTrue(board.isEmpty(0, 5));
        assertTrue(board.isEmpty(0, 6));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKingsideCastleIntoCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        board.setSquare(1, 6, null);
        board.setSquare(2, 6, null);
        board.setSquare(3, 6, null);
        board.setSquare(4, 6, null);
        board.setSquare(5, 6, null);
        board.setSquare(6, 6, null);

        Piece whiteKing = board.getSquare(7, 4);
        board.setSquare(6, 7, whiteKing);
        board.setSquare(7, 4, board.getSquare(7, 0));
        board.setSquare(7, 0, null);

        board.setSquare(7, 6, new Piece(PieceType.ROOK, PieceColor.WHITE));

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 6)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 7));

        assertTrue(board.isEmpty(0, 5));
        assertTrue(board.isEmpty(0, 6));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackQueensideCastleWhileInCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 0);

        board.setSquare(0, 1, null);
        board.setSquare(0, 2, null);
        board.setSquare(0, 3, null);

        board.setSquare(1, 4, null);
        board.setSquare(2, 4, null);
        board.setSquare(3, 4, null);
        board.setSquare(4, 4, null);
        board.setSquare(5, 4, null);
        board.setSquare(6, 4, null);

        Piece whiteKing = board.getSquare(7, 4);
        board.setSquare(6, 7, whiteKing);
        board.setSquare(7, 4, board.getSquare(7, 0));
        board.setSquare(7, 0, null);

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 2)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 0));

        assertTrue(board.isEmpty(0, 1));
        assertTrue(board.isEmpty(0, 2));
        assertTrue(board.isEmpty(0, 3));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackQueensideCastleThroughCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 0);

        board.setSquare(0, 1, null);
        board.setSquare(0, 2, null);
        board.setSquare(0, 3, null);

        board.setSquare(1, 3, null);
        board.setSquare(2, 3, null);
        board.setSquare(3, 3, null);
        board.setSquare(4, 3, null);
        board.setSquare(5, 3, null);
        board.setSquare(6, 3, null);

        Piece whiteKing = board.getSquare(7, 4);
        board.setSquare(6, 7, whiteKing);
        board.setSquare(7, 4, board.getSquare(7, 0));
        board.setSquare(7, 0, null);

        board.setSquare(7, 3, new Piece(PieceType.ROOK, PieceColor.WHITE));

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 2)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 0));

        assertTrue(board.isEmpty(0, 1));
        assertTrue(board.isEmpty(0, 2));
        assertTrue(board.isEmpty(0, 3));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackQueensideCastleIntoCheck_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 0);

        board.setSquare(0, 1, null);
        board.setSquare(0, 2, null);
        board.setSquare(0, 3, null);

        board.setSquare(1, 2, null);
        board.setSquare(2, 2, null);
        board.setSquare(3, 2, null);
        board.setSquare(4, 2, null);
        board.setSquare(5, 2, null);
        board.setSquare(6, 2, null);

        Piece whiteKing = board.getSquare(7, 4);
        board.setSquare(6, 7, whiteKing);
        board.setSquare(7, 4, board.getSquare(7, 0));
        board.setSquare(7, 0, null);

        board.setSquare(7, 2, new Piece(PieceType.ROOK, PieceColor.WHITE));

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 2)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 0));

        assertTrue(board.isEmpty(0, 1));
        assertTrue(board.isEmpty(0, 2));
        assertTrue(board.isEmpty(0, 3));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleWithBlockedAttackOnThroughSquare_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);

        board.setSquare(6, 5, null);
        board.setSquare(5, 5, null);
        board.setSquare(4, 5, new Piece(PieceType.PAWN, PieceColor.WHITE));
        board.setSquare(3, 5, null);
        board.setSquare(2, 5, null);
        board.setSquare(1, 5, null);
        board.setSquare(0, 5, new Piece(PieceType.ROOK, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 6);

        assertTrue(board.isEmpty(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 5));
        assertEquals(whiteKing, board.getSquare(7, 6));
        assertTrue(board.isEmpty(7, 7));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteQueensideCastleWithAttackOnB1_Succeeds() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);
        board.setSquare(7, 3, null);

        board.setSquare(6, 1, null);
        board.setSquare(5, 1, null);
        board.setSquare(4, 1, null);
        board.setSquare(3, 1, null);
        board.setSquare(2, 1, null);
        board.setSquare(1, 1, null);

        board.setSquare(0, 1, new Piece(PieceType.ROOK, PieceColor.BLACK));

        game.movePiece(7, 4, 7, 2);

        assertTrue(board.isEmpty(7, 4));
        assertTrue(board.isEmpty(7, 0));

        assertEquals(whiteKing, board.getSquare(7, 2));
        assertEquals(whiteRook, board.getSquare(7, 3));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleAfterKingMoved_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(6, 4, null);
        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);

        game.movePiece(7, 4, 6, 4);
        game.movePiece(1, 0, 2, 0);
        game.movePiece(6, 4, 7, 4);
        game.movePiece(1, 1, 2, 1);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 7));

        assertTrue(board.isEmpty(7, 5));
        assertTrue(board.isEmpty(7, 6));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_WhiteKingsideCastleAfterKingsideRookMoved_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 7);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);

        game.movePiece(7, 7, 7, 6);
        game.movePiece(1, 0, 2, 0);
        game.movePiece(7, 6, 7, 7);
        game.movePiece(1, 1, 2, 1);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertTrue(board.isEmpty(7, 5));
        assertTrue(board.isEmpty(7, 6));
        assertEquals(whiteRook, board.getSquare(7, 7));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackQueensideCastleAfterKingMoved_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 0);

        board.setSquare(1, 4, null);
        board.setSquare(0, 1, null);
        board.setSquare(0, 2, null);
        board.setSquare(0, 3, null);

        game.movePiece(6, 0, 5, 0);
        game.movePiece(0, 4, 1, 4);
        game.movePiece(6, 1, 5, 1);
        game.movePiece(1, 4, 0, 4);
        game.movePiece(6, 2, 5, 2);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 2)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 0));

        assertTrue(board.isEmpty(0, 1));
        assertTrue(board.isEmpty(0, 2));
        assertTrue(board.isEmpty(0, 3));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackQueensideCastleAfterQueensideRookMoved_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 0);

        board.setSquare(1, 0, null);
        board.setSquare(0, 1, null);
        board.setSquare(0, 2, null);
        board.setSquare(0, 3, null);

        game.movePiece(6, 0, 5, 0);
        game.movePiece(0, 0, 1, 0);
        game.movePiece(6, 1, 5, 1);
        game.movePiece(1, 0, 0, 0);
        game.movePiece(6, 2, 5, 2);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 2)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 0));

        assertTrue(board.isEmpty(0, 1));
        assertTrue(board.isEmpty(0, 2));
        assertTrue(board.isEmpty(0, 3));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    // Code Coverage
    // validateCastlingRights
    @Test
    public void MovePiece_WhiteQueensideCastleAfterQueensideRookMoved_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteRook = board.getSquare(7, 0);

        board.setSquare(6, 0, null);
        board.setSquare(7, 1, null);
        board.setSquare(7, 2, null);
        board.setSquare(7, 3, null);

        game.movePiece(7, 0, 6, 0);
        game.movePiece(1, 0, 2, 0);
        game.movePiece(6, 0, 7, 0);
        game.movePiece(1, 1, 2, 1);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 2)
        );

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteRook, board.getSquare(7, 0));

        assertTrue(board.isEmpty(7, 1));
        assertTrue(board.isEmpty(7, 2));
        assertTrue(board.isEmpty(7, 3));

        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKingsideCastleAfterKingsideRookMoved_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        board.setSquare(1, 7, null);

        game.movePiece(6, 0, 5, 0);
        game.movePiece(0, 7, 1, 7);

        game.movePiece(6, 1, 5, 1);
        game.movePiece(1, 7, 0, 7);

        game.movePiece(6, 2, 5, 2);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 6)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 7));

        assertTrue(board.isEmpty(0, 5));
        assertTrue(board.isEmpty(0, 6));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKingsideCastleAfterKingMoved_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(0, 5, null);
        board.setSquare(0, 6, null);

        board.setSquare(1, 4, null);

        game.movePiece(6, 0, 5, 0);
        game.movePiece(0, 4, 1, 4);

        game.movePiece(6, 1, 5, 1);
        game.movePiece(1, 4, 0, 4);

        game.movePiece(6, 2, 5, 2);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(0, 4, 0, 6)
        );

        assertEquals(blackKing, board.getSquare(0, 4));
        assertEquals(blackRook, board.getSquare(0, 7));

        assertTrue(board.isEmpty(0, 5));
        assertTrue(board.isEmpty(0, 6));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    @Test
    public void MovePiece_BlackKingCastlingLikeMoveFromNonStartingSquare_ThrowsExceptionAndDoesNotChangeState() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackKing = board.getSquare(0, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(2, 4, blackKing);
        board.setSquare(0, 4, null);
        board.setSquare(2, 5, null);
        board.setSquare(2, 6, null);

        game.movePiece(6, 0, 5, 0);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.movePiece(2, 4, 2, 6)
        );

        assertEquals(blackKing, board.getSquare(2, 4));
        assertTrue(board.isEmpty(2, 5));
        assertTrue(board.isEmpty(2, 6));

        assertTrue(board.isEmpty(0, 4));
        assertEquals(blackRook, board.getSquare(0, 7));

        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    // updateCastlingRights
    @Test
    public void MovePiece_BlackRookFromNonStartingColumn_DoesNotUpdateCastlingRights() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackRook = board.getSquare(0, 7);
        board.setSquare(0, 7, null);
        board.setSquare(0, 6, null);
        board.setSquare(0, 5, blackRook);

        game.movePiece(6, 0, 5, 0);
        game.movePiece(0, 5, 0, 6);

        assertTrue(board.isEmpty(0, 5));
        assertEquals(blackRook, board.getSquare(0, 6));
        assertEquals(PieceColor.WHITE, game.getCurrentTurn());
    }

    // movePiece
    @Test
    public void MovePiece_BlackPawnMovesTwoSquaresFromNonStartingRow_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece blackPawn = board.getSquare(1, 0);
        board.setSquare(1, 0, null);
        board.setSquare(2, 0, blackPawn);

        game.movePiece(6, 1, 5, 1);

        assertThrows(IllegalArgumentException.class, () -> game.movePiece(2, 0, 4, 0));

        assertEquals(blackPawn, board.getSquare(2, 0));
        assertTrue(board.isEmpty(4, 0));
        assertEquals(PieceColor.BLACK, game.getCurrentTurn());
    }

    // isAttackedByKnight
    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackKnightFromAllRemainingOffsets_ReturnsTrue() {
        int[][] knightPositions = {
                {2, 3},
                {3, 2},
                {5, 2},
                {5, 6},
                {6, 3},
                {6, 5}
        };

        for (int[] position : knightPositions) {
            Game game = new Game();
            game.initializeGame();

            Board board = game.getBoard();
            Piece whiteKing = board.getSquare(7, 4);
            Piece blackKnight = board.getSquare(0, 1);

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    board.setSquare(row, col, null);
                }
            }

            board.setSquare(4, 4, whiteKing);
            board.setSquare(position[0], position[1], blackKnight);

            assertTrue(game.isKingInCheck(PieceColor.WHITE));
        }
    }

    // isAttackedByPawn
    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackPawnFromRightDiagonal_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece blackPawn = board.getSquare(1, 0);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(4, 4, whiteKing);
        board.setSquare(3, 5, blackPawn);

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    @Test
    public void IsKingInCheck_BlackKingAttackedByWhitePawns_ReturnsTrue() {
        int[][] pawnPositions = {
                {5, 3},
                {5, 5}
        };

        for (int[] position : pawnPositions) {
            Game game = new Game();
            game.initializeGame();

            Board board = game.getBoard();

            Piece blackKing = board.getSquare(0, 4);
            Piece whitePawn = board.getSquare(6, 0);

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    board.setSquare(row, col, null);
                }
            }

            board.setSquare(4, 4, blackKing);
            board.setSquare(position[0], position[1], whitePawn);

            assertTrue(game.isKingInCheck(PieceColor.BLACK));
        }
    }

    // isAttackedByBishopOrQueen
    @Test
    public void IsKingInCheck_WhiteKingAttackedByBlackBishopFromDownRightDiagonal_ReturnsTrue() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece blackBishop = board.getSquare(0, 2);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.setSquare(row, col, null);
            }
        }

        board.setSquare(4, 4, whiteKing);
        board.setSquare(6, 6, blackBishop);

        assertTrue(game.isKingInCheck(PieceColor.WHITE));
    }

    // castleWhiteKingside
    @Test
    public void MovePiece_WhiteKingsideCastleWithNonRookPiece_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece whiteKnight = board.getSquare(7, 6);

        board.setSquare(7, 5, null);
        board.setSquare(7, 6, null);
        board.setSquare(7, 7, whiteKnight);

        assertThrows(IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6));

        assertEquals(whiteKing, board.getSquare(7, 4));
        assertEquals(whiteKnight, board.getSquare(7, 7));
    }

    @Test
    public void MovePiece_WhiteKingsideCastleBlockedAtFinalSquare_ThrowsException() {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        board.setSquare(7, 5, null);

        assertThrows(IllegalArgumentException.class,
                () -> game.movePiece(7, 4, 7, 6));
    }

    @Test
    public void CastleWhiteKingsideWithBlackRook_ThrowsException() throws Exception {
        Game game = new Game();
        game.initializeGame();

        Board board = game.getBoard();

        Piece whiteKing = board.getSquare(7, 4);
        Piece blackRook = board.getSquare(0, 7);

        board.setSquare(0, 7, null);
        board.setSquare(7, 7, blackRook);

        java.lang.reflect.Method method = Game.class.getDeclaredMethod(
                "castleWhiteKingside", Piece.class);
        method.setAccessible(true);

        java.lang.reflect.InvocationTargetException exception =
                assertThrows(java.lang.reflect.InvocationTargetException.class,
                        () -> method.invoke(game, whiteKing));

        assertTrue(exception.getCause() instanceof IllegalArgumentException);
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
