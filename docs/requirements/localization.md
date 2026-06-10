# English and Spanish Localization

## User Story

As a chess player, I want to choose my language when the game starts so that user-facing game text is displayed in a language I understand.

## Acceptance Criteria

- The application asks the player for a language code at startup.
- English (`en`) and Spanish (`es`) are supported.
- User-facing strings are extracted from program code into Java resource bundles.
- The application loads strings using `ResourceBundle.getBundle("labels", locale)`.
- Messages containing values are composed using `MessageFormat`.
- Unsupported locales fall back to the default English resource bundle.
- A new locale can be added by creating another appropriately named `labels_<language>.properties` file without changing `Messages`.

## Resource Bundles

- `src/main/resources/labels.properties`: default English strings
- `src/main/resources/labels_es.properties`: Spanish strings
