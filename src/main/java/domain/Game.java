package domain;

public class Game {

    private Board board;

    public void initializeGame() {
        board = new Board();
    }

    public Board getBoard() {
        return board;
    }
}