package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceMovementTest {

    @Test
    public void IsValidMovePattern_WithWhitePawnMovingForwardOneSquare_ReturnsTrue() {
        Piece pawn = new Piece(PieceType.PAWN, PieceColor.WHITE);

        assertTrue(pawn.isValidMovePattern(6, 0, 5, 0));
    }

    @Test
    public void IsValidMovePattern_WithBlackPawnMovingForwardOneSquare_ReturnsTrue() {
        Piece pawn = new Piece(PieceType.PAWN, PieceColor.BLACK);

        assertTrue(pawn.isValidMovePattern(1, 0, 2, 0));
    }

    @Test
    public void IsValidMovePattern_WithPawnMovingBackward_ReturnsFalse() {
        Piece pawn = new Piece(PieceType.PAWN, PieceColor.WHITE);

        assertFalse(pawn.isValidMovePattern(6, 0, 7, 0));
    }

    @Test
    public void IsValidMovePattern_WithRookMovingVertically_ReturnsTrue() {
        Piece rook = new Piece(PieceType.ROOK, PieceColor.WHITE);

        assertTrue(rook.isValidMovePattern(7, 0, 4, 0));
    }

    @Test
    public void IsValidMovePattern_WithRookMovingDiagonally_ReturnsFalse() {
        Piece rook = new Piece(PieceType.ROOK, PieceColor.WHITE);

        assertFalse(rook.isValidMovePattern(7, 0, 5, 2));
    }

    @Test
    public void IsValidMovePattern_WithBishopMovingDiagonally_ReturnsTrue() {
        Piece bishop = new Piece(PieceType.BISHOP, PieceColor.WHITE);

        assertTrue(bishop.isValidMovePattern(7, 2, 5, 4));
    }

    @Test
    public void IsValidMovePattern_WithBishopMovingVertically_ReturnsFalse() {
        Piece bishop = new Piece(PieceType.BISHOP, PieceColor.WHITE);

        assertFalse(bishop.isValidMovePattern(7, 2, 5, 2));
    }

    @Test
    public void IsValidMovePattern_WithKnightMovingInLShape_ReturnsTrue() {
        Piece knight = new Piece(PieceType.KNIGHT, PieceColor.WHITE);

        assertTrue(knight.isValidMovePattern(7, 1, 5, 2));
    }

    @Test
    public void IsValidMovePattern_WithKnightMovingStraight_ReturnsFalse() {
        Piece knight = new Piece(PieceType.KNIGHT, PieceColor.WHITE);

        assertFalse(knight.isValidMovePattern(7, 1, 5, 1));
    }

    @Test
    public void IsValidMovePattern_WithQueenMovingVertically_ReturnsTrue() {
        Piece queen = new Piece(PieceType.QUEEN, PieceColor.WHITE);

        assertTrue(queen.isValidMovePattern(7, 3, 4, 3));
    }

    @Test
    public void IsValidMovePattern_WithQueenMovingDiagonally_ReturnsTrue() {
        Piece queen = new Piece(PieceType.QUEEN, PieceColor.WHITE);

        assertTrue(queen.isValidMovePattern(7, 3, 4, 6));
    }

    @Test
    public void IsValidMovePattern_WithQueenMovingLikeKnight_ReturnsFalse() {
        Piece queen = new Piece(PieceType.QUEEN, PieceColor.WHITE);

        assertFalse(queen.isValidMovePattern(7, 3, 5, 4));
    }

    @Test
    public void IsValidMovePattern_WithKingMovingOneSquare_ReturnsTrue() {
        Piece king = new Piece(PieceType.KING, PieceColor.WHITE);

        assertTrue(king.isValidMovePattern(7, 4, 6, 4));
    }

    @Test
    public void IsValidMovePattern_WithKingMovingMoreThanOneSquare_ReturnsFalse() {
        Piece king = new Piece(PieceType.KING, PieceColor.WHITE);

        assertFalse(king.isValidMovePattern(7, 4, 5, 4));
    }

    @Test
    public void IsValidMovePattern_WithSameStartAndEndSquare_ReturnsFalse() {
        Piece rook = new Piece(PieceType.ROOK, PieceColor.WHITE);

        assertFalse(rook.isValidMovePattern(7, 0, 7, 0));
    }

}