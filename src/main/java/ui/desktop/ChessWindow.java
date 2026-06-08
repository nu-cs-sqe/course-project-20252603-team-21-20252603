package ui.desktop;

import domain.Board;
import domain.Game;
import domain.Piece;
import domain.PieceColor;
import domain.PieceType;
import ui.Messages;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;

public final class ChessWindow {

    private static final Color LIGHT_SQUARE = new Color(240, 217, 181);

    private static final Color DARK_SQUARE = new Color(181, 136, 99);

    private static final Color SELECTED_SQUARE = new Color(246, 246, 105);

    private static final int SQUARE_SIZE = 72;

    private static final int NO_SELECTION = -1;

    private final Messages messages;

    private final Locale locale;

    private final JFrame frame;

    private final JButton[][] squares = new JButton[8][8];

    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);

    private Game game;

    private int selectedRow = NO_SELECTION;

    private int selectedCol = NO_SELECTION;

    public ChessWindow(Messages messages, Locale locale) {
        this.messages = messages;
        this.locale = locale;
        frame = new JFrame(messages.getString("windowTitle"));
        initializeGame();
        buildWindow();
        refreshBoard();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void initializeGame() {
        game = new Game();
        game.initializeGame();
        clearSelection();
    }

    private void buildWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(12, 12));
        frame.add(createHeader(), BorderLayout.NORTH);
        frame.add(createBoardPanel(), BorderLayout.CENTER);
        frame.add(createControls(), BorderLayout.SOUTH);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel(messages.getString("windowTitle"), SwingConstants.CENTER);

        title.setFont(title.getFont().deriveFont(Font.BOLD, 24.0F));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        header.add(title, BorderLayout.NORTH);
        header.add(statusLabel, BorderLayout.SOUTH);

        return header;
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                JButton square = createSquare(row, col);
                squares[row][col] = square;
                boardPanel.add(square);
            }
        }

        boardPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        return boardPanel;
    }

    private JButton createSquare(int row, int col) {
        JButton square = new JButton();

        square.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
        square.setFont(new Font(Font.SERIF, Font.PLAIN, 44));
        square.setFocusPainted(false);
        square.setOpaque(true);
        square.setBorderPainted(false);
        square.addActionListener(event -> handleSquareClick(row, col));

        return square;
    }

    private JPanel createControls() {
        JPanel controls = new JPanel();
        JButton newGameButton = new JButton(messages.getString("newGame"));
        JButton instructionsButton = new JButton(messages.getString("instructions"));

        newGameButton.addActionListener(event -> {
            initializeGame();
            refreshBoard();
        });
        instructionsButton.addActionListener(event -> JOptionPane.showMessageDialog(
                frame,
                messages.getString("instructionsText"),
                messages.getString("instructions"),
                JOptionPane.INFORMATION_MESSAGE
        ));
        controls.add(newGameButton);
        controls.add(instructionsButton);

        return controls;
    }

    private void handleSquareClick(int row, int col) {
        if (selectedRow == NO_SELECTION) {
            selectPiece(row, col);
            return;
        }

        if (selectedRow == row && selectedCol == col) {
            clearSelection();
            refreshBoard();
            return;
        }

        moveSelectedPiece(row, col);
    }

    private void selectPiece(int row, int col) {
        Piece piece = game.getBoard().getSquare(row, col);

        if (piece == null) {
            showError("selectPieceError");
            return;
        }

        if (piece.getColor() != game.getCurrentTurn()) {
            showError("wrongTurnError");
            return;
        }

        selectedRow = row;
        selectedCol = col;
        refreshBoard();
    }

    private void moveSelectedPiece(int endRow, int endCol) {
        try {
            Piece movingPiece = game.getBoard().getSquare(selectedRow, selectedCol);
            PieceType promotion = choosePromotionIfNeeded(movingPiece, endRow);

            game.movePiece(selectedRow, selectedCol, endRow, endCol, promotion);
            clearSelection();
            refreshBoard();
            showEndgameMessageIfNeeded();
        } catch (IllegalArgumentException | IndexOutOfBoundsException exception) {
            showError("invalidMoveError");
            clearSelection();
            refreshBoard();
        }
    }

    private PieceType choosePromotionIfNeeded(Piece piece, int endRow) {
        if (piece == null || piece.getType() != PieceType.PAWN) {
            return PieceType.QUEEN;
        }

        boolean whitePromotes = piece.getColor() == PieceColor.WHITE && endRow == 0;
        boolean blackPromotes = piece.getColor() == PieceColor.BLACK && endRow == 7;

        if (!whitePromotes && !blackPromotes) {
            return PieceType.QUEEN;
        }

        PieceType[] choices = {
            PieceType.QUEEN,
            PieceType.ROOK,
            PieceType.BISHOP,
            PieceType.KNIGHT
        };
        String[] labels = new String[choices.length];

        for (int index = 0; index < choices.length; index++) {
            labels[index] = messages.getString("piece." + choices[index]);
        }

        Object selection = JOptionPane.showInputDialog(
                frame,
                messages.getString("promotionPrompt"),
                messages.getString("promotionTitle"),
                JOptionPane.QUESTION_MESSAGE,
                null,
                labels,
                labels[0]
        );

        for (int index = 0; index < labels.length; index++) {
            if (labels[index].equals(selection)) {
                return choices[index];
            }
        }

        return PieceType.QUEEN;
    }

    private void refreshBoard() {
        Board board = game.getBoard();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Piece piece = board.getSquare(row, col);
                JButton square = squares[row][col];

                square.setText(PieceSymbols.symbolFor(piece));
                square.setToolTipText(createSquareDescription(row, col, piece));
                square.setBackground(squareColor(row, col));
            }
        }

        updateStatus();
    }

    private String createSquareDescription(int row, int col, Piece piece) {
        String coordinate = BoardCoordinates.toAlgebraic(row, col);

        if (piece == null) {
            return coordinate;
        }

        String color = messages.getString("color." + piece.getColor());
        String type = messages.getString("piece." + piece.getType());
        return messages.format("squareDescription", coordinate, color, type);
    }

    private Color squareColor(int row, int col) {
        if (row == selectedRow && col == selectedCol) {
            return SELECTED_SQUARE;
        }

        if ((row + col) % 2 == 0) {
            return LIGHT_SQUARE;
        }

        return DARK_SQUARE;
    }

    private void updateStatus() {
        PieceColor turn = game.getCurrentTurn();
        String color = messages.getString("color." + turn);
        String status = messages.format("currentTurn", color);

        if (game.isKingInCheck(turn)) {
            status = messages.format("checkStatus", color);
        }

        statusLabel.setText(status);
    }

    private void showEndgameMessageIfNeeded() {
        PieceColor turn = game.getCurrentTurn();
        String color = messages.getString("color." + turn);

        if (game.isCheckmate(turn)) {
            showInformation(messages.format("checkmateMessage", color));
        } else if (game.isStalemate(turn)) {
            showInformation(messages.getString("stalemateMessage"));
        }
    }

    private void showError(String key) {
        JOptionPane.showMessageDialog(
                frame,
                messages.getString(key),
                messages.getString("errorTitle"),
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void showInformation(String message) {
        JOptionPane.showMessageDialog(
                frame,
                message,
                messages.getString("windowTitle"),
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void clearSelection() {
        selectedRow = NO_SELECTION;
        selectedCol = NO_SELECTION;
    }
}
