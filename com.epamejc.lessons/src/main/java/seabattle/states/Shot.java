package seabattle.states;

import seabattle.Coordinate;

public class Shot {
    
    private final Coordinate coordinate;

  public Shot(final Coordinate coordinate) {
    this.coordinate = coordinate;
  }
    
    public Coordinate getCoordinate() {
        return coordinate;
    }
    
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + coordinate.getX();
        result = prime * result + coordinate.getY();
        return result;
    }

  @Override public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Shot shot = (Shot)o;
    return getCoordinate().equals(shot.getCoordinate());
  }
    
}
