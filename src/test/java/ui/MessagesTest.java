package ui;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessagesTest {

    @Test
    public void GetString_WithEnglishLocale_ReturnsEnglishTranslation() {
        Messages messages = Messages.forLocale(Locale.ENGLISH);

        assertEquals("Welcome to Chess!", messages.getString("welcome"));
    }

    @Test
    public void GetString_WithSpanishLocale_ReturnsSpanishTranslation() {
        Messages messages = Messages.forLocale(new Locale("es"));

        assertEquals("¡Bienvenido al ajedrez!", messages.getString("welcome"));
    }

    @Test
    public void GetString_WithUnsupportedLocale_FallsBackToDefaultBundle() {
        Messages messages = Messages.forLocale(Locale.FRENCH);

        assertEquals("Welcome to Chess!", messages.getString("welcome"));
    }

    @Test
    public void Format_WithEnglishLocale_FormatsEnglishMessage() {
        Messages messages = Messages.forLocale(Locale.ENGLISH);

        String white = messages.getString("color.WHITE");

        assertEquals("Current turn: White", messages.format("currentTurn", white));
    }

    @Test
    public void Format_WithSpanishLocale_FormatsSpanishMessage() {
        Messages messages = Messages.forLocale(new Locale("es"));

        String white = messages.getString("color.WHITE");

        assertEquals("Turno actual: Blanco", messages.format("currentTurn", white));
    }

    @Test
    public void ForLocale_WithNullLocale_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Messages.forLocale(null));
    }
}
