package integration.steps;

import domain.Board;
import domain.Game;
import domain.Piece;
import domain.PieceColor;
import domain.PieceType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class F1StepDefinitions {

    private Game game;

    @Given("no chess game has been started")
    public void noChessGameHasBeenStarted() {
        game = new Game();
    }

    @When("the player starts a new chess game")
    public void thePlayerStartsANewChessGame() {
        game.initializeGame();
    }

    @Then("the game board has {int} rows and {int} columns")
    public void theGameBoardHasRowsAndColumns(int expectedRows, int expectedColumns) {
        Board board = game.getBoard();

        assertEquals(expectedRows, board.getSize());
        assertEquals(expectedColumns, board.getSize());
    }

    @Then("{word} has the first turn")
    public void playerHasTheFirstTurn(String color) {
        assertEquals(PieceColor.valueOf(color), game.getCurrentTurn());
    }

    @Then("both kings are in their standard starting positions")
    public void bothKingsAreInTheirStandardStartingPositions() {
        assertPiece(7, 4, PieceType.KING, PieceColor.WHITE);
        assertPiece(0, 4, PieceType.KING, PieceColor.BLACK);
    }

    @Then("each player has {int} pawns in the standard starting position")
    public void eachPlayerHasPawnsInTheStandardStartingPosition(int expectedPawnCount) {
        assertEquals(expectedPawnCount, countPawns(PieceColor.WHITE));
        assertEquals(expectedPawnCount, countPawns(PieceColor.BLACK));
    }

    @Then("the middle rows are empty")
    public void theMiddleRowsAreEmpty() {
        Board board = game.getBoard();

        for (int row = 2; row <= 5; row++) {
            for (int col = 0; col < board.getSize(); col++) {
                assertTrue(board.isEmpty(row, col));
            }
        }
    }

    @Then("a {word} {word} is at row {int} column {int} in the initial position")
    public void aPieceIsAtPosition(String color, String type, int row, int col) {
        assertPiece(
                row,
                col,
                PieceType.valueOf(type),
                PieceColor.valueOf(color)
        );
    }

    private int countPawns(PieceColor color) {
        Board board = game.getBoard();
        int pawnCount = 0;

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getSquare(row, col);

                if (piece != null
                        && piece.getType() == PieceType.PAWN
                        && piece.getColor() == color) {
                    pawnCount++;
                }
            }
        }

        return pawnCount;
    }

    private void assertPiece(
            int row,
            int col,
            PieceType expectedType,
            PieceColor expectedColor
    ) {
        Piece piece = game.getBoard().getSquare(row, col);

        assertEquals(expectedType, piece.getType());
        assertEquals(expectedColor, piece.getColor());
    }
}
