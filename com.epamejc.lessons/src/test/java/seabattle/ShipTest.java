package seabattle;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShipTest {

  @Test
  public void getFirstCoordinate() {
    Coordinate first = new Coordinate(3, 5);
    Coordinate second = new Coordinate(3, 7);
    Ship ship = new Ship(first, second);
    assertEquals(new Coordinate(3, 5), ship.getFirstCoordinate());
  }

  @Test
  public void getImpossibleFirstCoordinate() {
    Coordinate first = new Coordinate(-3, -5);
    Coordinate second = new Coordinate(3, 7);
    Ship ship = new Ship(first, second);
    assertEquals(new Coordinate(-3, -5), ship.getFirstCoordinate());
  }

  @Test
  public void getSecondCoordinate() {
    Coordinate first = new Coordinate(3, 7);
    Coordinate second = new Coordinate(3, 5);
    Ship ship = new Ship(first, second);
    assertEquals(new Coordinate(3, 7), ship.getSecondCoordinate());
  }

  @Test
  public void getShipParts() {
    Coordinate first = new Coordinate(3, 5);
    Coordinate second = new Coordinate(3, 7);
    Ship ship = new Ship(first, second);
    List<Coordinate> actualShipParts = ship.getShipParts();
    List<Coordinate> expectedShipParts = new LinkedList<>();

    expectedShipParts.add(first);
    expectedShipParts.add(new Coordinate(3, 6));
    expectedShipParts.add(second);
    assertEquals(expectedShipParts, actualShipParts);
  }

  @Test
  public void getLength() {
    Coordinate first = new Coordinate(3, 5);
    Coordinate second = new Coordinate(3, 7);
    Ship ship = new Ship(first, second);
    int expected = 3;
    int actual = ship.getLength();
    assertEquals(expected, actual);
  }

  @Test
  public void getHorizontalDirection() {
    Coordinate first = new Coordinate(3, 5);
    Coordinate second = new Coordinate(5, 5);
    Ship ship = new Ship(first, second);
    int expected = 1;
    int actual = ship.getDirection();
    assertEquals(expected, actual);
  }

  @Test
  public void getVerticalDirection() {
    Coordinate first = new Coordinate(3, 5);
    Coordinate second = new Coordinate(3, 7);
    Ship ship = new Ship(first, second);
    int expected = 2;
    int actual = ship.getDirection();
    assertEquals(expected, actual);
  }

  @Test
  public void getZeroDirection() {
    Coordinate first = new Coordinate(3, 5);
    Coordinate second = new Coordinate(3, 5);
    Ship ship = new Ship(first, second);
    int expected = 0;
    int actual = ship.getDirection();
    assertEquals(expected, actual);
  }

}