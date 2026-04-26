package domain;

public class Board {

    private final Object[][] squares = new Object[8][8];

    public int getSize() {
        return 8;
    }

    public Object getSquare(int row, int col) {
        return squares[row][col];
    }
}
