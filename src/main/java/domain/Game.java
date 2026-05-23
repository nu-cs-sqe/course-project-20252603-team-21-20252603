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

        if (isCastlingMove(piece, startRow, startCol, endRow, endCol)) {
            castleKingsideWhite(piece);
            switchTurn();
            return;
        }

        if (!piece.isValidMovePattern(startRow, startCol, endRow, endCol)) {
            throw new IllegalArgumentException("Invalid move pattern.");
        }

        if (piece.getType() != PieceType.KNIGHT
                && isPathBlocked(startRow, startCol, endRow, endCol)) {
            throw new IllegalArgumentException("Path is blocked.");
        }

        if (destinationPiece != null && destinationPiece.getColor() == piece.getColor()) {
            throw new IllegalArgumentException("Cannot capture own piece.");
        }

        if (isPawnMovingStraight(piece, startCol, endCol) && destinationPiece != null) {
            throw new IllegalArgumentException("Pawn cannot move forward into occupied square.");
        }

        if (isPawnTwoSquareMove(piece, startRow, endRow, startCol, endCol)
                && ((piece.getColor() == PieceColor.WHITE && startRow != 6)
                || (piece.getColor() == PieceColor.BLACK && startRow != 1))) {
            throw new IllegalArgumentException("Pawn can only move two squares from starting row.");
        }

        if (isPawnMovingDiagonally(piece, startCol, endCol) && destinationPiece == null) {
            throw new IllegalArgumentException("Pawn cannot move diagonally without capturing.");
        }

        board.setSquare(endRow, endCol, piece);
        board.setSquare(startRow, startCol, null);

        if (currentTurn == PieceColor.WHITE) {
            currentTurn = PieceColor.BLACK;
        } else {
            currentTurn = PieceColor.WHITE;
        }
    }

    private void validateBounds(int row, int col) {
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            throw new IndexOutOfBoundsException("Position is outside the board.");
        }
    }

    private boolean isPathBlocked(int startRow, int startCol, int endRow, int endCol) {
        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);

        int currentRow = startRow + rowStep;
        int currentCol = startCol + colStep;

        while (currentRow != endRow || currentCol != endCol) {
            if (!board.isEmpty(currentRow, currentCol)) {
                return true;
            }

            currentRow += rowStep;
            currentCol += colStep;
        }

        return false;
    }

    private boolean isPawnMovingStraight(Piece piece, int startCol, int endCol) {
        return piece.getType() == PieceType.PAWN && startCol == endCol;
    }

    private boolean isPawnTwoSquareMove(Piece piece, int startRow, int endRow, int startCol, int endCol) {
        return piece.getType() == PieceType.PAWN
                && Math.abs(endRow - startRow) == 2
                && startCol == endCol;
    }

    private boolean isPawnMovingDiagonally(Piece piece, int startCol, int endCol) {
        return piece.getType() == PieceType.PAWN && Math.abs(endCol - startCol) == 1;
    }

    private boolean isCastlingMove(
            Piece piece,
            int startRow,
            int startCol,
            int endRow,
            int endCol
    ) {
        return piece.getType() == PieceType.KING
                && startRow == endRow
                && Math.abs(endCol - startCol) == 2;
    }

    private void castleKingsideWhite(Piece king) {
        Piece rook = board.getSquare(7, 7);

        if (rook == null
                || rook.getType() != PieceType.ROOK
                || rook.getColor() != PieceColor.WHITE
                || !board.isEmpty(7, 5)
                || !board.isEmpty(7, 6)) {
            throw new IllegalArgumentException("Invalid castling move.");
        }

        board.setSquare(7, 6, king);
        board.setSquare(7, 5, rook);
        board.setSquare(7, 4, null);
        board.setSquare(7, 7, null);
    }

    private void switchTurn() {
        if (currentTurn == PieceColor.WHITE) {
            currentTurn = PieceColor.BLACK;
        } else {
            currentTurn = PieceColor.WHITE;
        }
    }
}