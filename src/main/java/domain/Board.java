package domain;

public class Board {

    private final Piece[][] squares = new Piece[8][8];

    public int getSize() {
        return 8;
    }

    public Piece getSquare(int row, int col) {
        return squares[row][col];
    }

    public boolean isEmpty(int row, int col) {
        return squares[row][col] == null;
    }

    public void setupInitialPosition() {
        setupPawnRow(1, PieceColor.BLACK);
        setupPawnRow(6, PieceColor.WHITE);
    }

    private void setupPawnRow(int row, PieceColor color) {
        for (int col = 0; col < getSize(); col++) {
            squares[row][col] = new Piece(PieceType.PAWN, color);
        }
    }


}
