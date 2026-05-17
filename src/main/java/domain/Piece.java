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
        // pawn movement
        if (type == PieceType.PAWN && color == PieceColor.WHITE) {
            return endRow == startRow - 1 && endCol == startCol;
        }

        if (type == PieceType.PAWN && color == PieceColor.BLACK) {
            return endRow == startRow + 1 && endCol == startCol;
        }

        // rook movement
        if (type == PieceType.ROOK) {
            return startRow == endRow || startCol == endCol;
        }

        // bishop movement
        int rowDifference = Math.abs(endRow - startRow);
        int colDifference = Math.abs(endCol - startCol);

        if (type == PieceType.BISHOP) {
            return rowDifference == colDifference;
        }


        return false;
    }
}
