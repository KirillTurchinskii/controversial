package seabattle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seabattle.states.Assist;
import seabattle.states.Miss;
import seabattle.states.Shot;

public class Field {

  public List<Ship> ships;
  public Set<Assist> assistSet;
  public List<Shot> shots;
  public Set<Miss> misses;

  public Field() {
    ships = new ArrayList<>(10);
    assistSet = new HashSet<>();
    shots = new ArrayList<>();
    misses = new HashSet<>();
  }

  public Set<Miss> getMisses() {
    return misses;
  }

  public List<Ship> getShips() {
    return ships;
  }

  public List<Shot> getShots() {
    return shots;
  }

  public void addAssistPointsAroundShip(final Ship ship) {
    for (final Coordinate coordinate :
      ship.getShipParts()) {
      addToUpperLeft(coordinate);
      addToUp(ship, coordinate);
      addToUpperRight(coordinate);
      addToLeft(ship, coordinate);
      addToRight(ship, coordinate);
      addToLowerLeft(coordinate);
      addToDown(ship, coordinate);
      addToLowerRight(coordinate);
    }
  }

  public void addAssistPointsAroundShot(final Coordinate shotCoordinate) {
    addAssistPoint(shotCoordinate.getX() - 1, shotCoordinate.getY() - 1);
    addAssistPoint(shotCoordinate.getX() + 1, shotCoordinate.getY() - 1);
    addAssistPoint(shotCoordinate.getX() - 1, shotCoordinate.getY() + 1);
    addAssistPoint(shotCoordinate.getX() + 1, shotCoordinate.getY() + 1);
  }

  public void addAssistPointsAroundKill(final Coordinate shotCoordinate, final Ship ship) {
    addAssistPointsAroundShot(shotCoordinate);
    final Coordinate firstCoordinate = ship.getFirstCoordinate();
    final Coordinate secondCoordinate = ship.getSecondCoordinate();
    switch (ship.getDirection()) {
      case ONE_CELL:
        addAssistPointOnXAxis(firstCoordinate, secondCoordinate);
        addAssistPointOnYAxis(firstCoordinate, secondCoordinate);
        break;
      case HORIZONTAL:
        addAssistPointOnXAxis(firstCoordinate, secondCoordinate);
        break;
      case VERTICAL:
        addAssistPointOnYAxis(firstCoordinate, secondCoordinate);
        break;
      default:
        System.out.println("Unexpected value: " + ship.getDirection());
        break;
    }

  }

  public boolean checkShotsCollision(final Coordinate shotCoordinate) {
    final Stream<Coordinate> shotsCoordinates = shots.stream()
                                                     .map(Shot::getCoordinate);
    final Stream<Coordinate> assistCoordinates = assistSet.stream()
                                                          .map(Assist::getAssistPoint);
    Stream<Coordinate> missesCoordinates;
    missesCoordinates = misses.stream()
                              .map(Miss::getCoordinate);
    final Stream<Coordinate> concat = Stream.concat(shotsCoordinates, assistCoordinates);
    final Stream<Coordinate> concat2 = Stream.concat(concat, missesCoordinates);
    final List<Coordinate> coordinateList = concat2.collect(Collectors.toList());
    return coordinateList.contains(shotCoordinate);
  }

  public boolean checkNoShipCollision(final Ship ship) {
    final Stream<Coordinate> coordinateOneStream = ships.stream()
                                                        .map(Ship::getShipParts)
                                                        .flatMap(Collection::stream);
    final Stream<Coordinate> coordinateTwoStream = assistSet.stream()
                                                            .map(Assist::getAssistPoint);
    final Stream<Coordinate> concat = Stream.concat(coordinateOneStream, coordinateTwoStream);
    final List<Coordinate> coordinateList = concat.collect(Collectors.toList());
    final List<Coordinate> shipParts = ship.getShipParts();
    return Collections.disjoint(coordinateList, shipParts);
  }

  public boolean checkCorrectAmountOfShips(final Ship ship) {
    boolean result;
    switch (ship.getLength()) {
      case 1:
        result = getCountByLength(1) < 4;
        break;
      case 2:
        result = getCountByLength(2) < 3;
        break;
      case 3:
        result = getCountByLength(3) < 2;
        break;
      case 4:
        result = getCountByLength(4) < 1;
        break;
      default:
        return false;
    }
    return result;
  }

  public void printState() {
    System.out.printf("There are %s ship(s) on a filed.%n%s Battleship (length of 4)%n%s Cruiser (length of 3)" +
                      "%n%s Destroyer (length of 2)%n%s Torpedo boat (length of 1)%n%n", ships.size(),
                      getCountByLength(4),
                      getCountByLength(3),
                      getCountByLength(2),
                      getCountByLength(1));

  }

  public void fillListOfShipsDependingOnLength(final int length, final int requiredAmountOfLength) {
    int state;
    final int firstX = new Random().nextInt(10);
    final int firstY = new Random().nextInt(10);
    final Coordinate firstCoordinate = new Coordinate(firstX, firstY);
    if (getCountByLength(length) < requiredAmountOfLength) {
      state = length - 1;
      final List<Coordinate> possibleVariants = new ArrayList<>();
      addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, 0, -state);
      addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, state, 0);
      addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, 0, state);
      addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, -state, 0);
      if (!possibleVariants.isEmpty()) {
        final Ship ship = selectRandomShip(firstCoordinate, possibleVariants);
        ships.add(ship);
        addAssistPointsAroundShip(ship);
      }
    }
  }

  public int getCountByLength(final int length) {
    return (int)ships.stream()
                     .map(Ship::getLength)
                     .filter(integer -> integer == length)
                     .count();
  }

  private void addToLowerRight(final Coordinate coordinate) {
    if (coordinate.getX() < 9 && coordinate.getY() < 9) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1)));
    }
  }

  private void addToDown(final Ship ship, final Coordinate coordinate) {
    if (coordinate.getY() < 9 && !ship.getShipParts()
                                      .contains(new Coordinate(coordinate.getX(),
                                                               coordinate.getY() + 1))) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX(), coordinate.getY() + 1)));
    }
  }

  private void addToLowerLeft(final Coordinate coordinate) {
    if (coordinate.getX() > 0 && coordinate.getY() < 9) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1)));
    }
  }

  private void addToRight(final Ship ship, final Coordinate coordinate) {
    if (coordinate.getX() < 9 && !ship.getShipParts()
                                      .contains(new Coordinate(coordinate.getX() + 1,
                                                               coordinate.getY()))) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX() + 1, coordinate.getY())));
    }
  }

  private void addToLeft(final Ship ship, final Coordinate coordinate) {
    if (coordinate.getX() > 0 && !ship.getShipParts()
                                      .contains(new Coordinate(coordinate.getX() - 1,
                                                               coordinate.getY()))) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX() - 1, coordinate.getY())));
    }
  }

  private void addToUpperRight(final Coordinate coordinate) {
    if (coordinate.getX() < 9 && coordinate.getY() > 0) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX() + 1, coordinate.getY() - 1)));
    }
  }

  private void addToUp(final Ship ship, final Coordinate coordinate) {
    if (coordinate.getY() > 0 && !ship.getShipParts()
                                      .contains(new Coordinate(coordinate.getX(),
                                                               coordinate.getY() - 1))) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX(), coordinate.getY() - 1)));
    }
  }

  private void addToUpperLeft(final Coordinate coordinate) {
    if (coordinate.getX() > 0 && coordinate.getY() > 0) {
      assistSet.add(new Assist(new Coordinate(coordinate.getX() - 1, coordinate.getY() - 1)));
    }
  }

  private void addAssistPointOnYAxis(final Coordinate firstCoordinate, final Coordinate secondCoordinate) {
    if (firstCoordinate.getY() > 0) {
      assistSet.add(new Assist(new Coordinate(firstCoordinate
                                                .getX(), firstCoordinate.getY() - 1)));
    }
    if (secondCoordinate.getY() < 9) {
      assistSet.add(new Assist(new Coordinate(secondCoordinate
                                                .getX(), secondCoordinate.getY() + 1)));
    }
  }

  private void addAssistPointOnXAxis(final Coordinate firstCoordinate, final Coordinate secondCoordinate) {
    if (firstCoordinate.getX() > 0) {
      assistSet.add(new Assist(new Coordinate(firstCoordinate
                                                .getX() - 1, firstCoordinate.getY())));
    }
    if (secondCoordinate.getX() < 9) {
      assistSet.add(new Assist(new Coordinate(secondCoordinate
                                                .getX() + 1, secondCoordinate.getY())));
    }
  }

  private void addAssistPoint(final int xValue, final int yValue) {
    final Coordinate coordinate1 = new Coordinate(xValue, yValue);
    if (Coordinate.isCoordinatesCorrect(coordinate1)) {
      final Assist assist1 = new Assist(coordinate1);
      assistSet.add(assist1);
    }
  }

  private Ship selectRandomShip(final Coordinate firstCoordinate, final List<Coordinate> possibleVariants) {
    final int random = new Random().nextInt(possibleVariants.size());
    final Coordinate secondCoordinate = possibleVariants.get(random);
    return new Ship(firstCoordinate, secondCoordinate);
  }

  private void addPossibleVariantsToList(
    final int firstX, final int firstY, final Coordinate firstCoordinate, final List<Coordinate> possibleVariants,
    final int addToXState,
    final int addToYState) {
    Coordinate secondCoordinate;
    secondCoordinate = new Coordinate(firstX + addToXState, firstY + addToYState);
    if (Coordinate.isCoordinatesCorrect(firstCoordinate, secondCoordinate)) {
      final Ship ship = new Ship(firstCoordinate, secondCoordinate);
      if (checkNoShipCollision(ship) && checkCorrectAmountOfShips(ship)) {
        possibleVariants.add(secondCoordinate);

      }
    }
  }

}
