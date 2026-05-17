package domain;

public class Piece {

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
        if (type == PieceType.PAWN && color == PieceColor.WHITE) {
            return endRow == startRow - 1 && endCol == startCol;
        }

        if (type == PieceType.PAWN && color == PieceColor.BLACK) {
            return endRow == startRow + 1 && endCol == startCol;
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
