package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    public void PieceConstructor_WithKingWhite_ReturnsType() {
        Piece piece = new Piece(PieceType.KING, PieceColor.WHITE);

        assertEquals(PieceType.KING, piece.getType());
    }

    @Test
    public void PieceConstructor_WithKingWhite_ReturnsColor() {
        Piece piece = new Piece(PieceType.KING, PieceColor.WHITE);

        assertEquals(PieceColor.WHITE, piece.getColor());
    }

    @Test
    public void PieceConstructor_WithNullType_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(null, PieceColor.WHITE);
        });
    }

    @Test
    public void PieceConstructor_WithNullColor_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Piece(PieceType.KING, null);
        });
    }
}
