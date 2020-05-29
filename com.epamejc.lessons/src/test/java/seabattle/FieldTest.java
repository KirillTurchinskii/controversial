package seabattle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seabattle.states.Assist;
import seabattle.states.Miss;
import seabattle.states.Shot;

import static org.junit.Assert.*;

public class FieldTest {

  @Test
  public void getMisses() {
    Field field = new Field();
    field.misses.add(new Miss(new Coordinate(3, 4)));
    field.misses.add(new Miss(new Coordinate(3, 5)));
    Set<Miss> expected = new HashSet<>();
    expected.add(new Miss(new Coordinate(3, 4)));
    expected.add(new Miss(new Coordinate(3, 5)));
    assertEquals(expected, field.getMisses());
  }

  @Test
  public void getShips() {
    Field field = new Field();
    List<Ship> ships = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      Coordinate first = new Coordinate(i + 3, 7 - i);
      Coordinate second = new Coordinate(i + 3, 5 - i);
      Ship ship = new Ship(first, second);
      field.ships.add(ship);
    }

    List<Ship> fieldShips = field.getShips();
    for (int i = 0; i < fieldShips.size(); i++) {
      Ship actual = fieldShips.get(i);
      Coordinate first = new Coordinate(i + 3, 7 - i);
      Coordinate second = new Coordinate(i + 3, 5 - i);
      Ship expected = new Ship(first, second);
      assertEquals(expected, actual);
    }
  }

  @Test
  public void getShots() {
    Field field = new Field();
    field.shots.add(new Shot(new Coordinate(3, 4)));
    field.shots.add(new Shot(new Coordinate(3, 5)));
    List<Shot> expected = new ArrayList<>();
    expected.add(new Shot(new Coordinate(3, 4)));
    expected.add(new Shot(new Coordinate(3, 5)));
    assertEquals(expected, field.getShots());
  }

  //|1|2|3|
  //|4|*|5|
  //|6|7|8|

  //     A B C D E F G J I K
//  0 |@|*|_|_|_|_|_|_|_|_|
//  1 |@|*|_|_|_|_|_|_|_|_|
//  2 |@|*|_|_|_|_|_|_|_|_|
//  3 |*|*|_|_|_|_|_|_|_|_|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|_|_|_|_|_|_|_|
//  7 |_|_|_|_|_|_|_|_|_|_|
//  8 |_|_|_|_|_|_|_|_|_|_|
//  9 |_|_|_|_|_|_|_|_|_|_|
  @Test
  public void addUpperLeftAssistPointsAroundShip() {
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 2);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(1, 0)));
    assists.add(new Assist(new Coordinate(1, 1)));
    assists.add(new Assist(new Coordinate(1, 2)));
    assists.add(new Assist(new Coordinate(0, 3)));
    assists.add(new Assist(new Coordinate(1, 3)));

    assertEquals(assists, field.assistSet);
  }

//     A B C D E F G J I K
//  0 |*|*|_|_|_|_|_|_|_|_|
//  1 |@|*|_|_|_|_|_|_|_|_|
//  2 |@|*|_|_|_|_|_|_|_|_|
//  3 |*|*|_|_|_|_|_|_|_|_|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|_|_|_|_|_|_|_|
//  7 |_|_|_|_|_|_|_|_|_|_|
//  8 |_|_|_|_|_|_|_|_|_|_|
//  9 |_|_|_|_|_|_|_|_|_|_|

  @Test
  public void addLeftAssistPointsAroundShip() {
    Coordinate first = new Coordinate(0, 1);
    Coordinate second = new Coordinate(0, 2);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(0, 0)));
    assists.add(new Assist(new Coordinate(1, 0)));
    assists.add(new Assist(new Coordinate(1, 1)));
    assists.add(new Assist(new Coordinate(1, 2)));
    assists.add(new Assist(new Coordinate(0, 3)));
    assists.add(new Assist(new Coordinate(1, 3)));

    assertEquals(assists, field.assistSet);
  }

//     A B C D E F G J I K
//  0 |_|_|_|_|_|_|_|_|_|_|
//  1 |_|_|_|_|_|_|_|_|_|_|
//  2 |_|_|_|_|_|_|_|_|_|_|
//  3 |_|_|_|_|_|_|_|_|_|_|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |*|*|_|_|_|_|_|_|_|_|
//  7 |@|*|_|_|_|_|_|_|_|_|
//  8 |@|*|_|_|_|_|_|_|_|_|
//  9 |@|*|_|_|_|_|_|_|_|_|

  @Test
  public void addBottomLeftAssistPointsAroundShip() {
    Coordinate first = new Coordinate(0, 7);
    Coordinate second = new Coordinate(0, 9);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(0, 6)));
    assists.add(new Assist(new Coordinate(1, 6)));
    assists.add(new Assist(new Coordinate(1, 7)));
    assists.add(new Assist(new Coordinate(1, 8)));
    assists.add(new Assist(new Coordinate(1, 9)));

    assertEquals(assists, field.assistSet);
  }

//     A B C D E F G J I K
//  0 |_|_|_|*|@|*|_|_|_|_|
//  1 |_|_|_|*|@|*|_|_|_|_|
//  2 |_|_|_|*|@|*|_|_|_|_|
//  3 |_|_|_|*|*|*|_|_|_|_|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|_|_|_|_|_|_|_|
//  7 |_|_|_|_|_|_|_|_|_|_|
//  8 |_|_|_|_|_|_|_|_|_|_|
//  9 |_|_|_|_|_|_|_|_|_|_|

  @Test
  public void addTopAssistPointsAroundShip() {
    Coordinate first = new Coordinate(4, 0);
    Coordinate second = new Coordinate(4, 2);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(3, 0)));
    assists.add(new Assist(new Coordinate(5, 0)));
    assists.add(new Assist(new Coordinate(3, 1)));
    assists.add(new Assist(new Coordinate(5, 1)));
    assists.add(new Assist(new Coordinate(3, 2)));
    assists.add(new Assist(new Coordinate(5, 2)));
    assists.add(new Assist(new Coordinate(3, 3)));
    assists.add(new Assist(new Coordinate(4, 3)));
    assists.add(new Assist(new Coordinate(5, 3)));

    assertEquals(assists, field.assistSet);
  }

  //     A B C D E F G J I K
//  0 |_|_|_|*|*|*|_|_|_|_|
//  1 |_|_|_|*|@|*|_|_|_|_|
//  2 |_|_|_|*|@|*|_|_|_|_|
//  3 |_|_|_|*|*|*|_|_|_|_|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|_|_|_|_|_|_|_|
//  7 |_|_|_|_|_|_|_|_|_|_|
//  8 |_|_|_|_|_|_|_|_|_|_|
//  9 |_|_|_|_|_|_|_|_|_|_|
  @Test
  public void addCenterAssistPointsAroundShip() {
    Coordinate first = new Coordinate(4, 1);
    Coordinate second = new Coordinate(4, 2);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(3, 0)));
    assists.add(new Assist(new Coordinate(4, 0)));
    assists.add(new Assist(new Coordinate(5, 0)));
    assists.add(new Assist(new Coordinate(3, 1)));
    assists.add(new Assist(new Coordinate(5, 1)));
    assists.add(new Assist(new Coordinate(3, 2)));
    assists.add(new Assist(new Coordinate(5, 2)));
    assists.add(new Assist(new Coordinate(3, 3)));
    assists.add(new Assist(new Coordinate(4, 3)));
    assists.add(new Assist(new Coordinate(5, 3)));

    assertEquals(assists, field.assistSet);
  }

  //     A B C D E F G J I K
//  0 |_|_|_|_|_|_|_|_|_|_|
//  1 |_|_|_|_|_|_|_|_|_|_|
//  2 |_|_|_|_|_|_|_|_|_|_|
//  3 |_|_|_|_|_|_|_|_|_|_|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|*|*|*|_|_|_|_|
//  7 |_|_|_|*|@|*|_|_|_|_|
//  8 |_|_|_|*|@|*|_|_|_|_|
//  9 |_|_|_|*|@|*|_|_|_|_|
  @Test
  public void addBottomAssistPointsAroundShip() {
    Coordinate first = new Coordinate(4, 7);
    Coordinate second = new Coordinate(4, 9);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(3, 6)));
    assists.add(new Assist(new Coordinate(4, 6)));
    assists.add(new Assist(new Coordinate(5, 6)));
    assists.add(new Assist(new Coordinate(3, 7)));
    assists.add(new Assist(new Coordinate(5, 7)));
    assists.add(new Assist(new Coordinate(3, 8)));
    assists.add(new Assist(new Coordinate(5, 8)));
    assists.add(new Assist(new Coordinate(3, 9)));
    assists.add(new Assist(new Coordinate(5, 9)));

    assertEquals(assists, field.assistSet);
  }

  //     A B C D E F G J I K
//  0 |_|_|_|_|_|_|_|_|*|@|
//  1 |_|_|_|_|_|_|_|_|*|@|
//  2 |_|_|_|_|_|_|_|_|*|@|
//  3 |_|_|_|_|_|_|_|_|*|*|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|_|_|_|_|_|_|_|
//  7 |_|_|_|_|_|_|_|_|_|_|
//  8 |_|_|_|_|_|_|_|_|_|_|
//  9 |_|_|_|_|_|_|_|_|_|_|
  @Test
  public void addRightTopAssistPointsAroundShip() {
    int i = 9;
    Coordinate first = new Coordinate(i, 0);
    Coordinate second = new Coordinate(i, 2);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(i - 1, 0)));
    assists.add(new Assist(new Coordinate(i - 1, 1)));
    assists.add(new Assist(new Coordinate(i - 1, 2)));
    assists.add(new Assist(new Coordinate(i - 1, 3)));
    assists.add(new Assist(new Coordinate(i, 3)));

    assertEquals(assists, field.assistSet);

  }

  //     A B C D E F G J I K
//  0 |_|_|_|_|_|_|_|_|*|*|
//  1 |_|_|_|_|_|_|_|_|*|@|
//  2 |_|_|_|_|_|_|_|_|*|@|
//  3 |_|_|_|_|_|_|_|_|*|*|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|_|_|_|_|_|_|_|
//  7 |_|_|_|_|_|_|_|_|_|_|
//  8 |_|_|_|_|_|_|_|_|_|_|
//  9 |_|_|_|_|_|_|_|_|_|_|
  @Test
  public void addRightAssistPointsAroundShip() {
    int i = 9;
    Coordinate first = new Coordinate(i, 1);
    Coordinate second = new Coordinate(i, 2);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(i - 1, 0)));
    assists.add(new Assist(new Coordinate(i, 0)));
    assists.add(new Assist(new Coordinate(i - 1, 1)));
    assists.add(new Assist(new Coordinate(i - 1, 2)));
    assists.add(new Assist(new Coordinate(i - 1, 3)));
    assists.add(new Assist(new Coordinate(i, 3)));

    assertEquals(assists, field.assistSet);
  }

  //     A B C D E F G J I K
//  0 |_|_|_|_|_|_|_|_|_|_|
//  1 |_|_|_|_|_|_|_|_|_|_|
//  2 |_|_|_|_|_|_|_|_|_|_|
//  3 |_|_|_|_|_|_|_|_|_|_|
//  4 |_|_|_|_|_|_|_|_|_|_|
//  5 |_|_|_|_|_|_|_|_|_|_|
//  6 |_|_|_|_|_|_|_|_|*|*|
//  7 |_|_|_|_|_|_|_|_|*|@|
//  8 |_|_|_|_|_|_|_|_|*|@|
//  9 |_|_|_|_|_|_|_|_|*|@|
  @Test
  public void addRightBottomAssistPointsAroundShip() {
    int i = 9;
    Coordinate first = new Coordinate(i, 7);
    Coordinate second = new Coordinate(i, 9);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.ships.add(ship);
    field.addAssistPointsAroundShip(ship);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(i - 1, 6)));
    assists.add(new Assist(new Coordinate(i, 6)));
    assists.add(new Assist(new Coordinate(i - 1, 7)));
    assists.add(new Assist(new Coordinate(i - 1, 8)));
    assists.add(new Assist(new Coordinate(i - 1, 9)));

    assertEquals(assists, field.assistSet);
  }

  @Test
  public void addLeftTopAssistPointsAroundShot() {
    Coordinate coordinate = new Coordinate(0, 0);
    Field field = new Field();
    field.addAssistPointsAroundShot(coordinate);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(1, 1)));

    assertEquals(assists, field.assistSet);
  }

  @Test
  public void addLeftBottomAssistPointsAroundShot() {
    Coordinate coordinate = new Coordinate(0, 9);
    Field field = new Field();
    field.addAssistPointsAroundShot(coordinate);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(1, 8)));

    assertEquals(assists, field.assistSet);
  }

  @Test
  public void addRightTopAssistPointsAroundShot() {
    Coordinate coordinate = new Coordinate(9, 0);
    Field field = new Field();
    field.addAssistPointsAroundShot(coordinate);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(8, 1)));

    assertEquals(assists, field.assistSet);
  }

  @Test
  public void addRightBottomAssistPointsAroundShot() {
    Coordinate coordinate = new Coordinate(9, 9);
    Field field = new Field();
    field.addAssistPointsAroundShot(coordinate);
    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(8, 8)));

    assertEquals(assists, field.assistSet);
  }

  @Test
  public void addAssistPointsAroundStandKill() {
    int i = 4;
    Coordinate first = new Coordinate(i, i);
    Coordinate second = new Coordinate(i, i);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.addAssistPointsAroundKill(first, ship);

    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(i - 1, i - 1)));
    assists.add(new Assist(new Coordinate(i, i - 1)));
    assists.add(new Assist(new Coordinate(i + 1, i - 1)));
    assists.add(new Assist(new Coordinate(i - 1, i)));
    assists.add(new Assist(new Coordinate(i + 1, i)));
    assists.add(new Assist(new Coordinate(i - 1, i + 1)));
    assists.add(new Assist(new Coordinate(i, i + 1)));
    assists.add(new Assist(new Coordinate(i + 1, i + 1)));
    assertEquals(assists, field.assistSet);
  }

  @Test
  public void addAssistPointsAroundVerticalKill() {
    int i = 4;
    Coordinate first = new Coordinate(i, i);
    Coordinate second = new Coordinate(i, i + 1);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.addAssistPointsAroundShot(first);
    field.addAssistPointsAroundKill(second, ship);

    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(i - 1, i - 1)));
    assists.add(new Assist(new Coordinate(i, i - 1)));
    assists.add(new Assist(new Coordinate(i + 1, i - 1)));

    assists.add(new Assist(new Coordinate(i - 1, i)));
    assists.add(new Assist(new Coordinate(i + 1, i)));

    assists.add(new Assist(new Coordinate(i - 1, i + 1)));
    assists.add(new Assist(new Coordinate(i + 1, i + 1)));

    assists.add(new Assist(new Coordinate(i - 1, i + 2)));
    assists.add(new Assist(new Coordinate(i, i + 2)));
    assists.add(new Assist(new Coordinate(i + 1, i + 2)));

    assertEquals(assists, field.assistSet);
  }

//     A B C D E F G J I K
//  0 |_|_|_|_|_|_|_|_|_|_|
//  1 |_|_|_|_|_|_|_|_|_|_|
//  2 |_|_|_|_|_|_|_|_|_|_|
//  3 |_|_|_|*|*|*|*|_|_|_|
//  4 |_|_|_|*|@|@|*|_|_|_|
//  5 |_|_|_|*|*|*|*|_|_|_|
//  6 |_|_|_|_|_|_|_|_|_|_|
//  7 |_|_|_|_|_|_|_|_|_|_|
//  8 |_|_|_|_|_|_|_|_|_|_|
//  9 |_|_|_|_|_|_|_|_|_|_|

  @Test
  public void addAssistPointsAroundHorizontalKill() {
    int i = 4;
    Coordinate first = new Coordinate(i, i);
    Coordinate second = new Coordinate(i + 1, i);
    Ship ship = new Ship(first, second);
    Field field = new Field();
    field.addAssistPointsAroundShot(first);
    field.addAssistPointsAroundKill(second, ship);

    Set<Assist> assists = new HashSet<>();
    assists.add(new Assist(new Coordinate(i - 1, i - 1)));
    assists.add(new Assist(new Coordinate(i, i - 1)));
    assists.add(new Assist(new Coordinate(i + 1, i - 1)));
    assists.add(new Assist(new Coordinate(i + 2, i - 1)));

    assists.add(new Assist(new Coordinate(i - 1, i)));
    assists.add(new Assist(new Coordinate(i + 2, i)));

    assists.add(new Assist(new Coordinate(i - 1, i + 1)));
    assists.add(new Assist(new Coordinate(i, i + 1)));
    assists.add(new Assist(new Coordinate(i + 1, i + 1)));
    assists.add(new Assist(new Coordinate(i + 2, i + 1)));

    assertEquals(assists, field.assistSet);
  }

  @Test
  public void checkShotsCollision() {
    Coordinate coordinate = new Coordinate(3, 3);
    Field field = new Field();
    field.shots.add(new Shot(coordinate));
    boolean actual = field.checkShotsCollision(coordinate);
    assertTrue(actual);
  }

  @Test
  public void checkShotsNoCollision() {
    Coordinate coordinate = new Coordinate(3, 3);
    Field field = new Field();
    field.shots.add(new Shot(new Coordinate(3, 4)));
    boolean actual = field.checkShotsCollision(coordinate);
    assertFalse(actual);
  }

  @Test
  public void checkShipCollision() {
    Coordinate coordinate1 = new Coordinate(3, 6);
    Coordinate coordinate2 = new Coordinate(3, 8);
    Field field = new Field();
    field.ships.add(new Ship(coordinate1, coordinate2));
    Coordinate coordinate3 = new Coordinate(2, 7);
    Coordinate coordinate4 = new Coordinate(4, 7);
    Ship ship = new Ship(coordinate3, coordinate4);
    boolean actual = field.checkNoShipCollision(ship);
    assertFalse(actual);
  }

  @Test
  public void checkNoShipCollision() {
    Coordinate coordinate1 = new Coordinate(3, 6);
    Coordinate coordinate2 = new Coordinate(3, 8);
    Field field = new Field();
    field.ships.add(new Ship(coordinate1, coordinate2));
    Coordinate coordinate3 = new Coordinate(7, 7);
    Coordinate coordinate4 = new Coordinate(8, 7);
    Ship ship = new Ship(coordinate3, coordinate4);
    boolean actual = field.checkNoShipCollision(ship);
    assertTrue(actual);
  }

  @Test
  public void checkCorrectAmountOfShipsLengthOne() {
    Field field = new Field();
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 0);
    field.ships.add(new Ship(first, second));
    assertTrue(field.checkCorrectAmountOfShips(new Ship(first, second)));

  }

  @Test
  public void checkCorrectAmountOfShipsLengthTwo() {
    Field field = new Field();
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 1);
    field.ships.add(new Ship(first, second));
    assertTrue(field.checkCorrectAmountOfShips(new Ship(first, second)));

  }

  @Test
  public void checkCorrectAmountOfShipsLengthThree() {
    Field field = new Field();
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 2);
    field.ships.add(new Ship(first, second));
    assertTrue(field.checkCorrectAmountOfShips(new Ship(first, second)));
  }

  @Test
  public void checkCorrectAmountOfShipsLengthFour() {
    Field field = new Field();
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 3);
    boolean actual = field.checkCorrectAmountOfShips(new Ship(first, second));
    assertTrue(actual);

  }

  @Test
  public void checkWrongAmountOfShips() {
    Field field = new Field();
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 3);
    field.ships.add(new Ship(first, second));
    Coordinate first1 = new Coordinate(5, 0);
    Coordinate second1 = new Coordinate(5, 3);
    boolean actual = field.checkCorrectAmountOfShips(new Ship(first1, second1));
    assertFalse(actual);
  }

  @Test
  public void getCountByLength() {
    Field field = new Field();
    Coordinate first = new Coordinate(0, 0);
    Coordinate second = new Coordinate(0, 3);
    field.ships.add(new Ship(first, second));
    int actual = field.getCountByLength(4);
    int expected = 1;
    assertEquals(expected, actual);
  }

}