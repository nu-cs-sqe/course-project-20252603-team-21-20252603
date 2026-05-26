package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class Game {

    private Board board;

    private PieceColor currentTurn;

    public void initializeGame() {
        board = new Board();
        board.setupInitialPosition();
        currentTurn = PieceColor.WHITE;
    }

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP",
            justification = "Game must expose the Board through getBoard() according to the design."
    )

    public Board getBoard() {
        return board;
    }

    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public boolean isKingInCheck(PieceColor color) {
        int[] kingPosition = findKingPosition(color);

        return isAttackedByRookOrQueen(color, kingPosition[0], kingPosition[1])
                || isAttackedByBishopOrQueen(color, kingPosition[0], kingPosition[1])
                || isAttackedByKnight(color, kingPosition[0], kingPosition[1]);
    }

    private int[] findKingPosition(PieceColor color) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (isKingOfColor(row, col, color)) {
                    return new int[] {row, col};
                }
            }
        }

        throw new IllegalStateException("King not found.");
    }

    private boolean isKingOfColor(int row, int col, PieceColor color) {
        Piece piece = board.getSquare(row, col);

        return piece != null
                && piece.getType() == PieceType.KING
                && piece.getColor() == color;
    }

    private boolean isAttackedByRookOrQueen(PieceColor kingColor, int kingRow, int kingCol) {
        return hasRookOrQueenAttackFromDirection(kingColor, kingRow, kingCol, -1, 0)
                || hasRookOrQueenAttackFromDirection(kingColor, kingRow, kingCol, 1, 0)
                || hasRookOrQueenAttackFromDirection(kingColor, kingRow, kingCol, 0, -1)
                || hasRookOrQueenAttackFromDirection(kingColor, kingRow, kingCol, 0, 1);
    }

    private boolean isAttackedByBishopOrQueen(PieceColor kingColor, int kingRow, int kingCol) {
        return hasBishopOrQueenAttackFromDirection(kingColor, kingRow, kingCol, -1, -1)
                || hasBishopOrQueenAttackFromDirection(kingColor, kingRow, kingCol, -1, 1)
                || hasBishopOrQueenAttackFromDirection(kingColor, kingRow, kingCol, 1, -1)
                || hasBishopOrQueenAttackFromDirection(kingColor, kingRow, kingCol, 1, 1);
    }

    private boolean isAttackedByKnight(PieceColor kingColor, int kingRow, int kingCol) {
        return isEnemyKnightAt(kingColor, kingRow - 2, kingCol - 1)
                || isEnemyKnightAt(kingColor, kingRow - 2, kingCol + 1)
                || isEnemyKnightAt(kingColor, kingRow - 1, kingCol - 2)
                || isEnemyKnightAt(kingColor, kingRow - 1, kingCol + 2)
                || isEnemyKnightAt(kingColor, kingRow + 1, kingCol - 2)
                || isEnemyKnightAt(kingColor, kingRow + 1, kingCol + 2)
                || isEnemyKnightAt(kingColor, kingRow + 2, kingCol - 1)
                || isEnemyKnightAt(kingColor, kingRow + 2, kingCol + 1);
    }

    private boolean hasRookOrQueenAttackFromDirection(
            PieceColor kingColor,
            int kingRow,
            int kingCol,
            int rowStep,
            int colStep
    ) {
        int row = kingRow + rowStep;
        int col = kingCol + colStep;

        while (row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize()) {
            Piece piece = board.getSquare(row, col);

            if (piece != null) {
                return piece.getColor() != kingColor
                        && (piece.getType() == PieceType.ROOK
                        || piece.getType() == PieceType.QUEEN);
            }

            row += rowStep;
            col += colStep;
        }

        return false;
    }

    private boolean hasBishopOrQueenAttackFromDirection(
            PieceColor kingColor,
            int kingRow,
            int kingCol,
            int rowStep,
            int colStep
    ) {
        int row = kingRow + rowStep;
        int col = kingCol + colStep;

        while (row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize()) {
            Piece piece = board.getSquare(row, col);

            if (piece != null) {
                return piece.getColor() != kingColor
                        && (piece.getType() == PieceType.BISHOP
                        || piece.getType() == PieceType.QUEEN);
            }

            row += rowStep;
            col += colStep;
        }

        return false;
    }

    private boolean isEnemyKnightAt(PieceColor kingColor, int row, int col) {
        if (isOutsideBoard(row, col)) {
            return false;
        }

        Piece piece = board.getSquare(row, col);

        return piece != null
                && piece.getColor() != kingColor
                && piece.getType() == PieceType.KNIGHT;
    }

    private boolean isOutsideBoard(int row, int col) {
        return row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize();
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
            castle(piece, endCol);
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

    private void castle(Piece king, int endCol) {
        if (king.getColor() == PieceColor.WHITE) {
            castleWhite(king, endCol);
            return;
        }

        if (king.getColor() == PieceColor.BLACK) {
            castleBlack(king, endCol);
            return;
        }

        throw new IllegalArgumentException("Invalid castling move.");
    }

    private void castleWhite(Piece king, int endCol) {
        if (endCol == 6) {
            castleWhiteKingside(king);
            return;
        }

        if (endCol == 2) {
            castleWhiteQueenside(king);
            return;
        }

        throw new IllegalArgumentException("Invalid castling move.");
    }

    private void castleBlack(Piece king, int endCol) {
        if (endCol == 6) {
            castleBlackKingside(king);
            return;
        }

        if (endCol == 2) {
            castleBlackQueenside(king);
            return;
        }

        throw new IllegalArgumentException("Invalid castling move.");
    }

    private void castleBlackQueenside(Piece king) {
        Piece rook = board.getSquare(0, 0);

        if (rook == null
                || rook.getType() != PieceType.ROOK
                || rook.getColor() != PieceColor.BLACK
                || !board.isEmpty(0, 1)
                || !board.isEmpty(0, 2)
                || !board.isEmpty(0, 3)) {
            throw new IllegalArgumentException("Invalid castling move.");
        }

        board.setSquare(0, 2, king);
        board.setSquare(0, 3, rook);
        board.setSquare(0, 4, null);
        board.setSquare(0, 0, null);
    }

    private void castleBlackKingside(Piece king) {
        Piece rook = board.getSquare(0, 7);

        if (rook == null
                || rook.getType() != PieceType.ROOK
                || rook.getColor() != PieceColor.BLACK
                || !board.isEmpty(0, 5)
                || !board.isEmpty(0, 6)) {
            throw new IllegalArgumentException("Invalid castling move.");
        }

        board.setSquare(0, 6, king);
        board.setSquare(0, 5, rook);
        board.setSquare(0, 4, null);
        board.setSquare(0, 7, null);
    }

    private void castleWhiteKingside(Piece king) {
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

    private void castleWhiteQueenside(Piece king) {
        Piece rook = board.getSquare(7, 0);

        if (rook == null
                || rook.getType() != PieceType.ROOK
                || rook.getColor() != PieceColor.WHITE
                || !board.isEmpty(7, 1)
                || !board.isEmpty(7, 2)
                || !board.isEmpty(7, 3)) {
            throw new IllegalArgumentException("Invalid castling move.");
        }

        board.setSquare(7, 2, king);
        board.setSquare(7, 3, rook);
        board.setSquare(7, 4, null);
        board.setSquare(7, 0, null);
    }

    private void switchTurn() {
        if (currentTurn == PieceColor.WHITE) {
            currentTurn = PieceColor.BLACK;
        } else {
            currentTurn = PieceColor.WHITE;
        }
    }
}