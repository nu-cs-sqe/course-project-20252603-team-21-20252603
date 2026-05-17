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
        squares[1][0] = new Piece(PieceType.PAWN, PieceColor.BLACK);
        squares[6][0] = new Piece(PieceType.PAWN, PieceColor.WHITE);
    }


}
