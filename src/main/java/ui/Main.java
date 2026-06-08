package ui;

import domain.Game;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in, StandardCharsets.UTF_8.name());

        System.out.println(Messages.forLocale(Locale.ENGLISH).getString("languagePrompt"));
        Locale locale = Locale.forLanguageTag(input.nextLine().trim());
        Messages messages = Messages.forLocale(locale);

        Game game = new Game();
        game.initializeGame();

        System.out.println(messages.getString("welcome"));
        String currentTurn = messages.getString("color." + game.getCurrentTurn());
        System.out.println(messages.format("currentTurn", currentTurn));
    }
}
