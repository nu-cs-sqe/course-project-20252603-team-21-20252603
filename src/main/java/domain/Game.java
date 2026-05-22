package domain;

public class Game {

    private Board board;
    private PieceColor currentTurn;

    public void initializeGame() {
        board = new Board();
        board.setupInitialPosition();
        currentTurn = PieceColor.WHITE;
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = board.getSquare(startRow, startCol);

        board.setSquare(endRow, endCol, piece);
        board.setSquare(startRow, startCol, null);

        currentTurn = PieceColor.BLACK;
    }
}