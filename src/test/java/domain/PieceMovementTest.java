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



}