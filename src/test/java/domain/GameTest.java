package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void InitializeGame_WithNewGame_CreatesBoardWithSizeEight() {
        Game game = new Game();

        game.initializeGame();

        assertEquals(8, game.getBoard().getSize());
    }
}