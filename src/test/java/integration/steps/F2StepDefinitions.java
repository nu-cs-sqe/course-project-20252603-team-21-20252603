package integration.steps;

import domain.Game;
import domain.Piece;
import domain.PieceColor;
import domain.PieceType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class F2StepDefinitions {

    private Game game;

    @Given("a new chess game has been started")
    public void aNewChessGameHasBeenStarted() {
        game = new Game();
        game.initializeGame();
    }

    @Given("{word} has moved the pawn from row {int} column {int} to row {int} column {int}")
    public void playerHasMovedThePawn(
            String color,
            int startRow,
            int startCol,
            int endRow,
            int endCol
    ) {
        assertEquals(PieceColor.valueOf(color), game.getCurrentTurn());
        game.movePiece(startRow, startCol, endRow, endCol);
    }

    @Given("a {word} {word} is at row {int} column {int}")
    public void aPieceIsAtPosition(String color, String type, int row, int col) {
        assertPiece(color, type, row, col);
    }

    @Then("the moved piece is a {word} {word} at row {int} column {int}")
    public void theMovedPieceIsAtPosition(String color, String type, int row, int col) {
        assertPiece(color, type, row, col);
    }

    private void assertPiece(String color, String type, int row, int col) {
        Piece piece = game.getBoard().getSquare(row, col);

        assertEquals(PieceColor.valueOf(color), piece.getColor());
        assertEquals(PieceType.valueOf(type), piece.getType());
    }

    @When("the player moves the piece from row {int} column {int} to row {int} column {int}")
    public void thePlayerMovesThePiece(int startRow, int startCol, int endRow, int endCol) {
        game.movePiece(startRow, startCol, endRow, endCol);
    }

    @Then("row {int} column {int} is empty")
    public void positionIsEmpty(int row, int col) {
        assertTrue(game.getBoard().isEmpty(row, col));
    }

    @Then("play passes to {word}")
    public void playPassesToPlayer(String color) {
        assertEquals(PieceColor.valueOf(color), game.getCurrentTurn());
    }
}
