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



}