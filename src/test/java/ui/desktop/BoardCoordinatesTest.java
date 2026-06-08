package ui.desktop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardCoordinatesTest {

    @Test
    public void ToAlgebraic_WithTopLeftSquare_ReturnsA8() {
        assertEquals("a8", BoardCoordinates.toAlgebraic(0, 0));
    }

    @Test
    public void ToAlgebraic_WithBottomRightSquare_ReturnsH1() {
        assertEquals("h1", BoardCoordinates.toAlgebraic(7, 7));
    }

    @Test
    public void ToAlgebraic_WithCenterSquare_ReturnsE4() {
        assertEquals("e4", BoardCoordinates.toAlgebraic(4, 4));
    }

    @Test
    public void ToAlgebraic_WithPositionOutsideBoard_ThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> BoardCoordinates.toAlgebraic(-1, 0));
    }
}
