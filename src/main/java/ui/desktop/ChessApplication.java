package ui.desktop;

import ui.Messages;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.util.Locale;

public final class ChessApplication {

    private ChessApplication() {
    }

    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("OptionPane.okButtonText", "OK");
            Locale locale = chooseLocale();
            ChessWindow window = new ChessWindow(Messages.forLocale(locale), locale);
            window.show();
        });
    }

    private static Locale chooseLocale() {
        String[] languages = {"English", "Español"};
        Object selection = JOptionPane.showInputDialog(
                null,
                "Choose a language / Elija un idioma",
                "Chess / Ajedrez",
                JOptionPane.QUESTION_MESSAGE,
                null,
                languages,
                languages[0]
        );

        if (languages[1].equals(selection)) {
            return new Locale("es");
        }

        return Locale.ENGLISH;
    }
}
