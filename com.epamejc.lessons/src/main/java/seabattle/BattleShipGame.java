package seabattle;

import java.util.Locale;

import seabattle.players.Bot;
import seabattle.players.Player;

public class BattleShipGame {

    private static void checkWinner(final Player player1, final Player player2) {
        if (player1.getMyField().ships
              .size() == 0) {
            System.out.println("Player 2 win!");
        }
        if (player2.getMyField().ships
              .size() == 0) {
            System.out.println("Player 1 win!");
        }
    }
    
    private static Player chooseSecondPlayer() {
        System.out.println("Choose second Player\n'1' BOT\nAny other key PERSON");
        if ("1".equals(InputReaderUtils.nextString()
                                       .toLowerCase(Locale.ENGLISH))) {
            return new Bot();
        } else {
            return new Player();
        }
    }
    
    public void start() {
        final Player player1 = new Player();
        System.out.println("Player 1");
        player1.fieldFillStrategy();
        final Player player2 = chooseSecondPlayer();
        System.out.println("Player2");
        player2.fieldFillStrategy();
        player1.setOpponentsFieldData(player2.getMyField());
        player2.setOpponentsFieldData(player1.getMyField());
        System.out.println("My");
        player1.getView().printField(player1.getMyField());
        System.out.println("Opo");
        player1.getView().printField(player1.getOpponentsFieldData());
        player1.getView().printField(player1.getOpponentsField());
        run(player1, player2);
        checkWinner(player1, player2);
    }

    public void startBotPlay() {
        final Player player1 = new Bot();
        System.out.println("Player 1");
        player1.fieldFillStrategy();
        final Player player2 = new Bot();
        System.out.println("Player2");
        player2.fieldFillStrategy();
        player1.setOpponentsFieldData(player2.getMyField());
        player2.setOpponentsFieldData(player1.getMyField());
        System.out.println("My");
        player1.getView().printField(player1.getMyField());
        System.out.println("Opo");
        player1.getView().printField(player1.getOpponentsFieldData());
        player1.getView().printField(player1.getOpponentsField());
        run(player1, player2);
        checkWinner(player1, player2);
    }

    private void run(final Player player1, final Player player2) {
        while (isOneOfFieldsIsEmpty(player1, player2)) {
            player1.turn("Player one.");
            if (isOneOfFieldsIsEmpty(player1, player2)) {
                player2.turn("Player two.");
            }
        }
    }

    private boolean isOneOfFieldsIsEmpty(final Player player1, final Player player2) {
        return player1.getMyField().ships
                 .size() > 0 && player2.getMyField().ships
                                  .size() > 0;
    }
    
}
