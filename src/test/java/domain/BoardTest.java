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
}
