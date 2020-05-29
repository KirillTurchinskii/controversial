package seabattle;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

  @Test
  public void isCoordinatesCorrectInLeftTopCorner() {
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 0);
    assertTrue(Coordinate.isCoordinatesCorrect(first, second));
  }

  @Test
  public void isCoordinatesCorrectInRightTopCorner() {
    Coordinate first = new Coordinate(9, 0);
    Coordinate second = new Coordinate(9, 0);
    assertTrue(Coordinate.isCoordinatesCorrect(first, second));
  }

  @Test
  public void isCoordinatesCorrectInLeftBottomCorner() {
    Coordinate first = new Coordinate(0, 9);
    Coordinate second = new Coordinate(0, 9);
    assertTrue(Coordinate.isCoordinatesCorrect(first, second));
  }

  @Test
  public void isCoordinatesCorrectInRightBottomCorner() {
    Coordinate first = new Coordinate(9, 9);
    Coordinate second = new Coordinate(9, 9);
    assertTrue(Coordinate.isCoordinatesCorrect(first, second));
  }

  @Test
  public void isCoordinatesCorrectPassingBorder() {
    Coordinate first = new Coordinate(3, 0);
    Coordinate second = new Coordinate(-1, 0);
    assertFalse(Coordinate.isCoordinatesCorrect(first, second));
  }

  @Test
  public void generateRandomCoordinate() {
    Coordinate coordinate = Coordinate.generateRandomCoordinate();
    assertTrue(Coordinate.isCoordinatesCorrect(coordinate));
  }

  @Test
  public void isCoordinateCorrectInLeftTopCorner() {
    Coordinate first = new Coordinate(0, 0);
    assertTrue(Coordinate.isCoordinatesCorrect(first));
  }

  @Test
  public void isCoordinateCorrectInRightTopCorner() {
    Coordinate first = new Coordinate(9, 0);
    assertTrue(Coordinate.isCoordinatesCorrect(first));
  }

  @Test
  public void isCoordinateCorrectInLeftBottomCorner() {
    Coordinate first = new Coordinate(0, 9);
    assertTrue(Coordinate.isCoordinatesCorrect(first));
  }

  @Test
  public void isCoordinateCorrectInRightBottomCorner() {
    Coordinate first = new Coordinate(9, 9);
    assertTrue(Coordinate.isCoordinatesCorrect(first));
  }

  @Test
  public void Coordinate() {
    Coordinate coordinate = new Coordinate();
    assertTrue(coordinate.getX() == 0 && coordinate.getY() == 0);
  }

  @Test
  public void getXFromNormalCoordinate() {
    Coordinate coordinate = new Coordinate(4, 3);
    assertEquals(4, coordinate.getX());
  }

  @Test
  public void getXFromNegativeCoordinate() {
    Coordinate coordinate = new Coordinate(-4, -3);
    assertEquals(-4, coordinate.getX());
  }

  @Test
  public void getYFromNormalCoordinate() {
    Coordinate coordinate = new Coordinate(4, 3);
    assertEquals(3, coordinate.getY());
  }

  @Test
  public void getYFromNegativeCoordinate() {
    Coordinate coordinate = new Coordinate(-4, -3);
    assertEquals(-3, coordinate.getY());
  }

  @Test
  public void input() {
    String data = "C" + System.lineSeparator() + System.lineSeparator() + "7" + System.lineSeparator();
    try {
      Coordinate coordinate =
        new Coordinate().input(new BufferedInputStream(new FileInputStream("src/test/resources/input.txt")));
      Coordinate expected = new Coordinate(2, 6);
      assertEquals(expected, coordinate);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testHashCode() {
    Coordinate coordinate = new Coordinate(3, 7);
    int expected = 1;
    expected = 31 * expected + coordinate.getX();
    expected = 31 * expected + coordinate.getY();
    assertEquals(expected, coordinate.hashCode());

  }

  @Test
  public void testWrongEquals() {
    Coordinate coordinate = new Coordinate(3, 7);
    Coordinate coordinate2 = new Coordinate(7, 3);
    assertNotEquals(coordinate, coordinate2);
  }

  @Test
  public void testRightEquals() {
    Coordinate coordinate = new Coordinate(3, 7);
    Coordinate coordinate2 = new Coordinate(3, 7);
    assertEquals(coordinate, coordinate2);
  }

  @Test
  public void testEqualsWithItself() {
    Coordinate coordinate = new Coordinate(3, 7);
    assertEquals(coordinate, coordinate);
  }

  @Test
  public void testEqualsWithNull() {
    Coordinate coordinate = new Coordinate(3, 7);
    assertNotEquals(null, coordinate);
  }

}