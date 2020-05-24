package seabattle.view;

public enum GameObjectView {
  UNBROKEN(ObjectColors.ANSI_YELLOW_UNBROKEN.getState() + ObjectColors.UNBROKEN_SYMBOL.getState() +
           ObjectColors.ANSI_RESET.getState()),
  BROKEN(ObjectColors.ANSI_RED_BROKEN.getState() + ObjectColors.BROKEN_SYMBOL.getState() +
         ObjectColors.ANSI_RESET.getState()),
  MISS(ObjectColors.ANSI_PURPLE_MISS.getState() + ObjectColors.MISS_SYMBOL.getState() +
       ObjectColors.ANSI_RESET.getState()),
  IMPOSSIBLE("▪"),
  EMPTY("☐");

  private final String state;

  GameObjectView(final String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

}
