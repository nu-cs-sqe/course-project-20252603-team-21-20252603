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
}