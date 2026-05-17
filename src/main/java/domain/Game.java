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
}