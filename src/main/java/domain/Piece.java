package domain;

public final class Piece {

    private PieceColor color;
    private PieceType type;

    public Piece(PieceType type, PieceColor color) {
        if (type == null || color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean isValidMovePattern(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return false;
        }

        if (type == PieceType.PAWN && color == PieceColor.WHITE) {
            boolean forwardMove = (endRow == startRow - 1 || endRow == startRow - 2)
                    && endCol == startCol;
            boolean diagonalMove = endRow == startRow - 1
                    && Math.abs(endCol - startCol) == 1;

            return forwardMove || diagonalMove;
        }

        if (type == PieceType.PAWN && color == PieceColor.BLACK) {
            boolean forwardMove = (endRow == startRow + 1 || endRow == startRow + 2)
                    && endCol == startCol;
            boolean diagonalMove = endRow == startRow + 1
                    && Math.abs(endCol - startCol) == 1;

            return forwardMove || diagonalMove;
        }

        if (type == PieceType.ROOK) {
            return startRow == endRow || startCol == endCol;
        }

        // bishop movement
        int rowDifference = Math.abs(endRow - startRow);
        int colDifference = Math.abs(endCol - startCol);

        if (type == PieceType.BISHOP) {
            return rowDifference == colDifference;
        }

        if (type == PieceType.KNIGHT) {
            return (rowDifference == 2 && colDifference == 1)
                    || (rowDifference == 1 && colDifference == 2);
        }


        if (type == PieceType.QUEEN) {
            return startRow == endRow
                    || startCol == endCol
                    || rowDifference == colDifference;
        }

        if (type == PieceType.KING) {
            return rowDifference <= 1 && colDifference <= 1;
        }
        return false;
    }
}
