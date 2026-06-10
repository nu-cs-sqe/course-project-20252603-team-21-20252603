package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void BoardConstructor_SizeIsEight() {
        Board board = new Board();
        assertEquals(8, board.getSize());
    }

    @Test
    public void BoardConstructor_AllSquaresInitiallyEmpty() {
        Board board = new Board();

        assertNull(board.getSquare(0,0));
        assertNull(board.getSquare(7,7));
    }

    @Test
    public void GetSquare_WithRowZeroColumnZero_DoesNotThrow() {
        Board board = new Board();

        assertDoesNotThrow(() -> board.getSquare(0, 0));
    }

    @Test
    public void GetSquare_WithRowSevenColumnSeven_DoesNotThrow() {
        Board board = new Board();

        assertDoesNotThrow(() -> board.getSquare(7, 7));
    }

    @Test
    public void GetSquare_WithNegativeRow_ThrowsException() {
        Board board = new Board();

        assertThrows(IndexOutOfBoundsException.class, () -> board.getSquare(-1, 0));
    }

    @Test
    public void GetSquare_WithRowTooLarge_ThrowsException() {
        Board board = new Board();

        assertThrows(IndexOutOfBoundsException.class, () -> board.getSquare(8, 0));
    }

    @Test
    public void GetSquare_WithNegativeColumn_ThrowsException() {
        Board board = new Board();

        assertThrows(IndexOutOfBoundsException.class, () -> board.getSquare(0, -1));
    }

    @Test
    public void GetSquare_WithColumnTooLarge_ThrowsException() {
        Board board = new Board();

        assertThrows(IndexOutOfBoundsException.class, () -> board.getSquare(0, 8));
    }

    @Test
    public void IsEmpty_WithNewBoardSquare_ReturnsTrue() {
        Board board = new Board();

        assertTrue(board.isEmpty(0, 0));
    }

    @Test
    public void SetupInitialPosition_WithWhitePawnStartingSquare_PlacesWhitePawn() {
        Board board = new Board();

        board.setupInitialPosition();

        Piece piece = board.getSquare(6, 0);

        assertEquals(PieceType.PAWN, piece.getType());
        assertEquals(PieceColor.WHITE, piece.getColor());
    }

    @Test
    public void SetupInitialPosition_WithBlackPawnStartingSquare_PlacesBlackPawn() {
        Board board = new Board();

        board.setupInitialPosition();

        Piece piece = board.getSquare(1, 0);

        assertEquals(PieceType.PAWN, piece.getType());
        assertEquals(PieceColor.BLACK, piece.getColor());
    }

    @Test
    public void SetupInitialPosition_WithWhitePawnRow_PlacesAllWhitePawns() {
        Board board = new Board();

        board.setupInitialPosition();

        for (int col = 0; col < board.getSize(); col++) {
            Piece piece = board.getSquare(6, col);

            assertEquals(PieceType.PAWN, piece.getType());
            assertEquals(PieceColor.WHITE, piece.getColor());
        }
    }

    @Test
    public void SetupInitialPosition_WithBlackPawnRow_PlacesAllBlackPawns() {
        Board board = new Board();

        board.setupInitialPosition();

        for (int col = 0; col < board.getSize(); col++) {
            Piece piece = board.getSquare(1, col);

            assertEquals(PieceType.PAWN, piece.getType());
            assertEquals(PieceColor.BLACK, piece.getColor());
        }
    }


    @Test
    public void SetupInitialPosition_WithWhiteBackRow_PlacesWhiteBackRowPieces() {
        Board board = new Board();

        board.setupInitialPosition();

        assertPiece(board, 7, 0, PieceType.ROOK, PieceColor.WHITE);
        assertPiece(board, 7, 1, PieceType.KNIGHT, PieceColor.WHITE);
        assertPiece(board, 7, 2, PieceType.BISHOP, PieceColor.WHITE);
        assertPiece(board, 7, 3, PieceType.QUEEN, PieceColor.WHITE);
        assertPiece(board, 7, 4, PieceType.KING, PieceColor.WHITE);
        assertPiece(board, 7, 5, PieceType.BISHOP, PieceColor.WHITE);
        assertPiece(board, 7, 6, PieceType.KNIGHT, PieceColor.WHITE);
        assertPiece(board, 7, 7, PieceType.ROOK, PieceColor.WHITE);
    }

    @Test
    public void SetupInitialPosition_WithBlackBackRow_PlacesBlackBackRowPieces() {
        Board board = new Board();

        board.setupInitialPosition();

        assertPiece(board, 0, 0, PieceType.ROOK, PieceColor.BLACK);
        assertPiece(board, 0, 1, PieceType.KNIGHT, PieceColor.BLACK);
        assertPiece(board, 0, 2, PieceType.BISHOP, PieceColor.BLACK);
        assertPiece(board, 0, 3, PieceType.QUEEN, PieceColor.BLACK);
        assertPiece(board, 0, 4, PieceType.KING, PieceColor.BLACK);
        assertPiece(board, 0, 5, PieceType.BISHOP, PieceColor.BLACK);
        assertPiece(board, 0, 6, PieceType.KNIGHT, PieceColor.BLACK);
        assertPiece(board, 0, 7, PieceType.ROOK, PieceColor.BLACK);
    }


    @Test
    public void SetupInitialPosition_WithMiddleRows_KeepsSquaresEmpty() {
        Board board = new Board();

        board.setupInitialPosition();

        for (int row = 2; row <= 5; row++) {
            for (int col = 0; col < board.getSize(); col++) {
                assertTrue(board.isEmpty(row, col));
            }
        }
    }

    @Test
    public void SetupInitialPosition_WithAlreadySetupBoard_ResetsToStandardStartingPosition() {
        Board board = new Board();

        board.setupInitialPosition();
        board.setupInitialPosition();

        assertEquals(32, countPieces(board));
        assertPiece(board, 7, 4, PieceType.KING, PieceColor.WHITE);
        assertPiece(board, 0, 4, PieceType.KING, PieceColor.BLACK);
        assertTrue(board.isEmpty(3, 3));
    }



    private int countPieces(Board board) {
        int count = 0;

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (!board.isEmpty(row, col)) {
                    count++;
                }
            }
        }

        return count;
    }

    // Mutation Tests
    @Test
    public void SetupInitialPosition_ClearsExistingPieces() {
        Board board = new Board();

        board.setSquare(4, 4,
                new Piece(PieceType.QUEEN, PieceColor.WHITE));

        board.setupInitialPosition();

        assertNull(board.getSquare(4, 4));
    }

    @Test
    public void SetupInitialPosition_ClearsEntireBoardBeforeSetup() {
        Board board = new Board();

        board.setSquare(3, 3,
                new Piece(PieceType.BISHOP, PieceColor.BLACK));

        board.setupInitialPosition();

        assertNull(board.getSquare(3, 3));
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
