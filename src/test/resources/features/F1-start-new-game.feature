Feature: F1 Start a New Chess Game
  As a chess player,
  I want to start a new chess game
  so that I can begin playing from the standard initial position.

  Scenario: Start a game in the standard initial position
    Given no chess game has been started
    When the player starts a new chess game
    Then the game board has 8 rows and 8 columns
    And WHITE has the first turn
    And both kings are in their standard starting positions
    And each player has 8 pawns in the standard starting position
    And the middle rows are empty

  Scenario Outline: Standard pieces are placed across the board
    Given no chess game has been started
    When the player starts a new chess game
    Then a <color> <piece> is at row <row> column <column> in the initial position

    Examples:
      | color | piece | row | column |
      | BLACK | ROOK  | 0   | 0      |
      | BLACK | QUEEN | 0   | 3      |
      | BLACK | KING  | 0   | 4      |
      | BLACK | PAWN  | 1   | 7      |
      | WHITE | PAWN  | 6   | 0      |
      | WHITE | QUEEN | 7   | 3      |
      | WHITE | KING  | 7   | 4      |
      | WHITE | ROOK  | 7   | 7      |
