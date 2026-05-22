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

        if (piece == null) {
            throw new IllegalArgumentException("Start square is empty.");
        }

        if (piece.getColor() != currentTurn) {
            throw new IllegalArgumentException("Cannot move opponent's piece.");
        }

        if (!piece.isValidMovePattern(startRow, startCol, endRow, endCol)) {
            throw new IllegalArgumentException("Invalid move pattern.");
        }

        board.setSquare(endRow, endCol, piece);
        board.setSquare(startRow, startCol, null);

        currentTurn = PieceColor.BLACK;
    }
}