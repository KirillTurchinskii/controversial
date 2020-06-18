package seabattle;

public class Main {

    public static void main(String[] args) {
      final BattleShipGame battleShipGame = new BattleShipGame();
      //battleShipGame.start();
      long startTime = System.currentTimeMillis();
      {
        battleShipGame.startBotPlay();
      }
      long time = System.currentTimeMillis() - startTime;
      System.out.println(time);
    }

}
