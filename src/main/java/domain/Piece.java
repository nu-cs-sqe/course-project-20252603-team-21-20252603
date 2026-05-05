package domain;

public class Piece {

    private PieceColor color;

    public Piece(PieceType type, PieceColor color) {
        this.color = color;
    }

    public PieceType getType() {
        return PieceType.KING;
    }

    public PieceColor getColor() {
        return color;
    }
}
