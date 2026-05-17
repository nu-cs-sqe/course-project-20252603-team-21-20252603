package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void InitializeGame_WithNewGame_CreatesBoard() {
        Game game = new Game();

        game.initializeGame();

        assertNotNull(game.getBoard());
    }
}
