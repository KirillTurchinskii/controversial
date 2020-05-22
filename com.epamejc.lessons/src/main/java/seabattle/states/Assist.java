package seabattle.states;

import seabattle.Coordinate;

public class Assist {
    
    private final Coordinate assistPoint;

  public Assist(final Coordinate assistPoint) {
    this.assistPoint = assistPoint;
  }
    
    public Coordinate getAssistPoint() {
        return assistPoint;
    }
    
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + assistPoint.getX();
        result = prime * result + assistPoint.getY();
        return result;
    }

  @Override public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Assist assist = (Assist)o;
    return getAssistPoint().equals(assist.getAssistPoint());
  }
    
}
