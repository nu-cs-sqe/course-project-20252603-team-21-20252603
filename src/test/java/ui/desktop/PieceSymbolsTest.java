package ui.desktop;

import domain.Piece;
import domain.PieceColor;
import domain.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceSymbolsTest {

    @Test
    public void SymbolFor_WithEmptySquare_ReturnsEmptyString() {
        assertEquals("", PieceSymbols.symbolFor(null));
    }

    @Test
    public void SymbolFor_WithWhiteKing_ReturnsWhiteKingSymbol() {
        Piece king = new Piece(PieceType.KING, PieceColor.WHITE);

        assertEquals("♔", PieceSymbols.symbolFor(king));
    }

    @Test
    public void SymbolFor_WithBlackPawn_ReturnsBlackPawnSymbol() {
        Piece pawn = new Piece(PieceType.PAWN, PieceColor.BLACK);

        assertEquals("♟", PieceSymbols.symbolFor(pawn));
    }
}
