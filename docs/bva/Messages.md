# BVA Analysis for Messages

## Method under test: `Messages.forLocale(Locale locale)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC1 | `forLocale`, `getString` | English locale and `welcome` key | Returns the English welcome message | ✅ |
| TC2 | `forLocale`, `getString` | Spanish locale and `welcome` key | Returns the Spanish welcome message | ✅ |
| TC3 | `forLocale`, `getString` | Unsupported French locale and `welcome` key | Falls back to the default English bundle | ✅ |
| TC4 | `forLocale` | Null locale | Throws `IllegalArgumentException` | ✅ |

## Method under test: `format(String key, Object... arguments)`

| ID | Method(s) under test | System under test | Expected output | Implemented? |
|---|---|---|---|---|
| TC5 | `format` | English locale, `currentTurn` key, and translated `White` argument | Returns the formatted English current-turn message | ✅ |
| TC6 | `format` | Spanish locale, `currentTurn` key, and translated `Blanco` argument | Returns the formatted Spanish current-turn message | ✅ |
