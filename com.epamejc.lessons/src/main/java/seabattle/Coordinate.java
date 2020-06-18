package seabattle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import utils.InputReaderUtils;

public class Coordinate {

  private final static Map<String, Integer> lettersToNumbers = createMap();
  private int x;
  private int y;

  public Coordinate(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  public Coordinate() {
  }

  private static Map<String, Integer> createMap() {
    final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    map.put("a", 0);
    map.put("b", 1);
    map.put("c", 2);
    map.put("d", 3);
    map.put("e", 4);
    map.put("f", 5);
    map.put("g", 6);
    map.put("h", 7);
    map.put("i", 8);
    map.put("j", 9);
    return map;
  }

  public static boolean isCoordinatesCorrect(final Coordinate first, final Coordinate second) {
    return isCoordinatesCorrect(first) && isCoordinatesCorrect(second) &&
           (first.getX() == second.getX() || first.getY() == second.getY()) &&
           Math.abs(first.getX() - second.getX()) <= 3 && Math.abs(first.getY() - second.getY()) <= 3;
  }

  public static Coordinate generateRandomCoordinate() {
    final int firstX = new Random().nextInt(10);
    final int firstY = new Random().nextInt(10);
    return new Coordinate(firstX, firstY);
  }

  public static boolean isCoordinatesCorrect(final Coordinate coordinate) {
    return coordinate.getX() >= 0 && coordinate.getX() < 10 && coordinate.getY() >= 0 && coordinate.getY() < 10;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Coordinate input(InputStream inputStream) {
    int x = -1;
    int y = -1;
    final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    System.out.println("Input column letter");
    final String columnString = InputReaderUtils.nextString(reader);
    if (lettersToNumbers.containsKey(columnString.toLowerCase(Locale.ENGLISH))) {
      x = lettersToNumbers.get(columnString.toLowerCase(Locale.ENGLISH));
    }
    System.out.println("Input row number");
    final int rowInt = InputReaderUtils.nextInt(reader);
    if (rowInt - 1 >= 0 && rowInt - 1 < 10) {
      y = rowInt - 1;
    }
    return new Coordinate(x, y);
  }

  @Override public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getX();
    result = prime * result + getY();
    return result;
  }

  @Override public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Coordinate that = (Coordinate)o;
    return getX() == that.getX() &&
           getY() == that.getY();
  }

}
