package domain;

public class Board {

    private static final int TOTAL_NUM_RANKS = 8;

    private static final int TOTAL_NUM_FILES = 8;

    private final Piece[][] squares = new Piece[TOTAL_NUM_RANKS][TOTAL_NUM_FILES];

    public int getSize() {
        return TOTAL_NUM_RANKS;
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
        for (int row = 0; row < TOTAL_NUM_RANKS; row++) {
            for (int col = 0; col < TOTAL_NUM_FILES; col++) {
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


}
