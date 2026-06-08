package ui;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public final class Messages {

    private static final String LABELS_BUNDLE_NAME = "labels";

    private final Locale locale;

    private final ResourceBundle labels;

    private Messages(Locale locale) {
        this.locale = locale;
        labels = ResourceBundle.getBundle(LABELS_BUNDLE_NAME, locale);
    }

    public static Messages forLocale(Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Locale cannot be null.");
        }

        return new Messages(locale);
    }

    public String getString(String key) {
        return labels.getString(key);
    }

    public String format(String key, Object... arguments) {
        MessageFormat formatter = new MessageFormat(getString(key), locale);

        return formatter.format(arguments);
    }
}
