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
        clearBoard();
        setupBackRow(0, PieceColor.BLACK);
        setupPawnRow(1, PieceColor.BLACK);
        setupPawnRow(6, PieceColor.WHITE);
        setupBackRow(7, PieceColor.WHITE);
    }


    private void clearBoard() {
        for (int row = 0; row < getSize(); row++) {
            for (int col = 0; col < getSize(); col++) {
                squares[row][col] = null;
            }
        }
    }

    private void setupBackRow(int row, PieceColor color) {
        squares[row][0] = new Piece(PieceType.ROOK, color);
        squares[row][1] = new Piece(PieceType.KNIGHT, color);
        squares[row][2] = new Piece(PieceType.BISHOP, color);
        squares[row][3] = new Piece(PieceType.QUEEN, color);
        squares[row][4] = new Piece(PieceType.KING, color);
        squares[row][5] = new Piece(PieceType.BISHOP, color);
        squares[row][6] = new Piece(PieceType.KNIGHT, color);
        squares[row][7] = new Piece(PieceType.ROOK, color);
    }

    private void setupPawnRow(int row, PieceColor color) {
        for (int col = 0; col < getSize(); col++) {
            squares[row][col] = new Piece(PieceType.PAWN, color);
        }
    }

    public void setSquare(int row, int col, Piece piece) {
        squares[row][col] = piece;
    }


}
