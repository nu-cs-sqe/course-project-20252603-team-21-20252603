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
}
