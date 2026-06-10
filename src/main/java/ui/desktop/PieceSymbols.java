package ui.desktop;

import domain.Piece;
import domain.PieceColor;
import domain.PieceType;

import java.util.EnumMap;
import java.util.Map;

public final class PieceSymbols {

    private static final Map<PieceType, String> WHITE_SYMBOLS = createWhiteSymbols();

    private static final Map<PieceType, String> BLACK_SYMBOLS = createBlackSymbols();

    private PieceSymbols() {
    }

    public static String symbolFor(Piece piece) {
        if (piece == null) {
            return "";
        }

        if (piece.getColor() == PieceColor.WHITE) {
            return WHITE_SYMBOLS.get(piece.getType());
        }

        return BLACK_SYMBOLS.get(piece.getType());
    }

    private static Map<PieceType, String> createWhiteSymbols() {
        Map<PieceType, String> symbols = new EnumMap<>(PieceType.class);

        symbols.put(PieceType.KING, "♔");
        symbols.put(PieceType.QUEEN, "♕");
        symbols.put(PieceType.ROOK, "♖");
        symbols.put(PieceType.BISHOP, "♗");
        symbols.put(PieceType.KNIGHT, "♘");
        symbols.put(PieceType.PAWN, "♙");

        return symbols;
    }

    private static Map<PieceType, String> createBlackSymbols() {
        Map<PieceType, String> symbols = new EnumMap<>(PieceType.class);

        symbols.put(PieceType.KING, "♚");
        symbols.put(PieceType.QUEEN, "♛");
        symbols.put(PieceType.ROOK, "♜");
        symbols.put(PieceType.BISHOP, "♝");
        symbols.put(PieceType.KNIGHT, "♞");
        symbols.put(PieceType.PAWN, "♟");

        return symbols;
    }
}
