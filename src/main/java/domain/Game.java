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
        validateBounds(startRow, startCol);
        validateBounds(endRow, endCol);

        Piece piece = board.getSquare(startRow, startCol);
        Piece destinationPiece = board.getSquare(endRow, endCol);


        if (piece == null) {
            throw new IllegalArgumentException("Start square is empty.");
        }

        if (piece.getColor() != currentTurn) {
            throw new IllegalArgumentException("Cannot move opponent's piece.");
        }

        if (!piece.isValidMovePattern(startRow, startCol, endRow, endCol)) {
            throw new IllegalArgumentException("Invalid move pattern.");
        }

        if (destinationPiece != null && destinationPiece.getColor() == piece.getColor()) {
            throw new IllegalArgumentException("Cannot capture own piece.");
        }

        board.setSquare(endRow, endCol, piece);
        board.setSquare(startRow, startCol, null);

        currentTurn = PieceColor.BLACK;
    }

    private void validateBounds(int row, int col) {
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            throw new IndexOutOfBoundsException("Position is outside the board.");
        }
    }
}