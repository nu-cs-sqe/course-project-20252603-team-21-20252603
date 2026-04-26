package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}
