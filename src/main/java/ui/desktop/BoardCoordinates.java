package ui.desktop;

public final class BoardCoordinates {

    private static final int BOARD_SIZE = 8;

    private BoardCoordinates() {
    }

    public static String toAlgebraic(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            throw new IndexOutOfBoundsException("Position is outside the board.");
        }

        char file = (char) ('a' + col);
        int rank = BOARD_SIZE - row;

        return String.valueOf(file) + rank;
    }
}
