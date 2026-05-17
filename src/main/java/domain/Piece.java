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
}
