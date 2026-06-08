Feature: F2 Make a Legal Move
  As a chess player,
  I want to make a legal move
  so that the board updates and play passes to my opponent.

  Scenario Outline: White makes a legal opening move
    Given a new chess game has been started
    And a WHITE <piece> is at row <start_row> column <start_column>
    When the player moves the piece from row <start_row> column <start_column> to row <end_row> column <end_column>
    Then row <start_row> column <start_column> is empty
    And the moved piece is a WHITE <piece> at row <end_row> column <end_column>
    And play passes to BLACK

    Examples:
      | piece  | start_row | start_column | end_row | end_column |
      | PAWN   | 6         | 0            | 5       | 0          |
      | PAWN   | 6         | 4            | 4       | 4          |
      | PAWN   | 6         | 7            | 5       | 7          |
      | KNIGHT | 7         | 1            | 5       | 2          |
      | KNIGHT | 7         | 6            | 5       | 5          |

  Scenario Outline: Black makes a legal reply
    Given a new chess game has been started
    And WHITE has moved the pawn from row 6 column 4 to row 4 column 4
    And a BLACK <piece> is at row <start_row> column <start_column>
    When the player moves the piece from row <start_row> column <start_column> to row <end_row> column <end_column>
    Then row <start_row> column <start_column> is empty
    And the moved piece is a BLACK <piece> at row <end_row> column <end_column>
    And play passes to WHITE

    Examples:
      | piece  | start_row | start_column | end_row | end_column |
      | PAWN   | 1         | 0            | 2       | 0          |
      | PAWN   | 1         | 4            | 3       | 4          |
      | PAWN   | 1         | 7            | 2       | 7          |
      | KNIGHT | 0         | 1            | 2       | 2          |
      | KNIGHT | 0         | 6            | 2       | 5          |
