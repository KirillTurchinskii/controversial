package seabattle;

import seabattle.states.Assist;
import seabattle.states.Miss;
import seabattle.states.Shot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Field {
    
    public List<Ship> ships = new ArrayList<>(10);
    public Set<Assist> assistSet = new HashSet<>();
    public List<Shot> shots = new ArrayList<>();
    public Set<Miss> misses = new HashSet<>();
    
    public Field() {
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
    
    public void addAssistPointsAroundShip(Ship ship) {
        for (Coordinate coordinate :
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
    
    public void addAssistPointsAroundShot(Coordinate shotCoordinate) {
        addAssistPoint(shotCoordinate.getX() - 1, shotCoordinate.getY() - 1);
        addAssistPoint(shotCoordinate.getX() + 1, shotCoordinate.getY() - 1);
        addAssistPoint(shotCoordinate.getX() - 1, shotCoordinate.getY() + 1);
        addAssistPoint(shotCoordinate.getX() + 1, shotCoordinate.getY() + 1);
    }
    
    public void addAssistPointsAroundKill(Coordinate shotCoordinate, Ship ship) {
        addAssistPointsAroundShot(shotCoordinate);
        Coordinate firstCoordinate = ship.getFirstCoordinate();
        Coordinate secondCoordinate = ship.getSecondCoordinate();
        switch (ship.getDirection()) {
            case 0:
                addAssistPointOnXAxis(firstCoordinate, secondCoordinate);
                addAssistPointOnYAxis(firstCoordinate, secondCoordinate);
                break;
            case 1:
                addAssistPointOnXAxis(firstCoordinate, secondCoordinate);
                break;
            case 2:
                addAssistPointOnYAxis(firstCoordinate, secondCoordinate);
                break;
        }
        
    }
    
    public boolean checkShotsCollision(Coordinate shotCoordinate) {
        Stream<Coordinate> shotsCoordinates = shots.stream()
                                                   .map(Shot::getCoordinate);
        Stream<Coordinate> assistCoordinates = assistSet.stream()
                                                        .map(Assist::getAssistPoint);
        Stream<Coordinate> missesCoordinates;
        missesCoordinates = misses.stream()
                                  .map(Miss::getCoordinate);
        Stream<Coordinate> concat = Stream.concat(shotsCoordinates, assistCoordinates);
        Stream<Coordinate> concat2 = Stream.concat(concat, missesCoordinates);
        List<Coordinate> coordinateList = concat2.collect(Collectors.toList());
        return coordinateList.contains(shotCoordinate);
    }
    
    public boolean checkNoShipCollision(Ship ship) {
        Stream<Coordinate> coordinateOneStream = ships.stream()
                                                      .map(Ship::getShipParts)
                                                      .flatMap(Collection::stream);
        Stream<Coordinate> coordinateTwoStream = assistSet.stream()
                                                          .map(Assist::getAssistPoint);
        Stream<Coordinate> concat = Stream.concat(coordinateOneStream, coordinateTwoStream);
        List<Coordinate> coordinateList = concat.collect(Collectors.toList());
        List<Coordinate> shipParts = ship.getShipParts();
        return Collections.disjoint(coordinateList, shipParts);
    }
    
    public boolean checkCorrectAmountOfShips(Ship ship) {
        switch (ship.getLength()) {
            case 1:
                return getCountByLength(1) < 4;
            case 2:
                return getCountByLength(2) < 3;
            case 3:
                return getCountByLength(3) < 2;
            case 4:
                return getCountByLength(4) < 1;
            default:
                return false;
        }
    }
    
    public void printState() {
        System.out.printf("There are %s ship(s) on a filed.\n%s Battleship (length of 4)\n%s Cruiser (length of 3)" +
                          "\n%s Destroyer (length of 2)\n%s Torpedo boat (length of 1)\n\n", ships.size(),
                          getCountByLength(4),
                          getCountByLength(3),
                          getCountByLength(2),
                          getCountByLength(1));
        
    }
    
    public void fillListOfShipsDependingOnLength(int length, int requiredAmountOfLength) {
        int state;
        int firstX = new Random().nextInt(10);
        int firstY = new Random().nextInt(10);
        Coordinate firstCoordinate = new Coordinate(firstX, firstY);
        if (getCountByLength(length) < requiredAmountOfLength) {
            state = length - 1;
            List<Coordinate> possibleVariants = new ArrayList<>();
            addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, 0, -state);
            addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, state, 0);
            addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, 0, state);
            addPossibleVariantsToList(firstX, firstY, firstCoordinate, possibleVariants, -state, 0);
            if (possibleVariants.size() > 0) {
                Ship ship = selectRandomShip(firstCoordinate, possibleVariants);
                ships.add(ship);
                addAssistPointsAroundShip(ship);
            }
        }
    }
    
    public long getCountByLength(int length) {
        return ships.stream()
                    .map(Ship::getLength)
                    .filter(integer -> integer == length)
                    .count();
    }
    
    private void addToLowerRight(Coordinate coordinate) {
        if (coordinate.getX() < 9 && coordinate.getY() < 9) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1)));
        }
    }
    
    private void addToDown(Ship ship, Coordinate coordinate) {
        if (coordinate.getY() < 9 && !ship.getShipParts()
                                          .contains(new Coordinate(coordinate.getX(),
                                                                   coordinate.getY() + 1))) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX(), coordinate.getY() + 1)));
        }
    }
    
    private void addToLowerLeft(Coordinate coordinate) {
        if (coordinate.getX() > 0 && coordinate.getY() < 9) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1)));
        }
    }
    
    private void addToRight(Ship ship, Coordinate coordinate) {
        if (coordinate.getX() < 9 && !ship.getShipParts()
                                          .contains(new Coordinate(coordinate.getX() + 1,
                                                                   coordinate.getY()))) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX() + 1, coordinate.getY())));
        }
    }
    
    private void addToLeft(Ship ship, Coordinate coordinate) {
        if (coordinate.getX() > 0 && !ship.getShipParts()
                                          .contains(new Coordinate(coordinate.getX() - 1,
                                                                   coordinate.getY()))) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX() - 1, coordinate.getY())));
        }
    }
    
    private void addToUpperRight(Coordinate coordinate) {
        if (coordinate.getX() < 9 && coordinate.getY() > 0) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX() + 1, coordinate.getY() - 1)));
        }
    }
    
    private void addToUp(Ship ship, Coordinate coordinate) {
        if (coordinate.getY() > 0 && !ship.getShipParts()
                                          .contains(new Coordinate(coordinate.getX(),
                                                                   coordinate.getY() - 1))) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX(), coordinate.getY() - 1)));
        }
    }
    
    private void addToUpperLeft(Coordinate coordinate) {
        if (coordinate.getX() > 0 && coordinate.getY() > 0) {
            assistSet.add(new Assist(new Coordinate(coordinate.getX() - 1, coordinate.getY() - 1)));
        }
    }
    
    private void addAssistPointOnYAxis(Coordinate firstCoordinate, Coordinate secondCoordinate) {
        if (firstCoordinate.getY() > 0) {
            assistSet.add(new Assist(new Coordinate(firstCoordinate
                                                            .getX(), firstCoordinate.getY() - 1)));
        }
        if (secondCoordinate.getY() < 9) {
            assistSet.add(new Assist(new Coordinate(secondCoordinate
                                                            .getX(), secondCoordinate.getY() + 1)));
        }
    }
    
    private void addAssistPointOnXAxis(Coordinate firstCoordinate, Coordinate secondCoordinate) {
        if (firstCoordinate.getX() > 0) {
            assistSet.add(new Assist(new Coordinate(firstCoordinate
                                                            .getX() - 1, firstCoordinate.getY())));
        }
        if (secondCoordinate.getX() < 9) {
            assistSet.add(new Assist(new Coordinate(secondCoordinate
                                                            .getX() + 1, secondCoordinate.getY())));
        }
    }
    
    private void addAssistPoint(int i, int i2) {
        Coordinate coordinate1 = new Coordinate(i, i2);
        if (Coordinate.isCoordinatesCorrect(coordinate1)) {
            Assist assist1 = new Assist(coordinate1);
            assistSet.add(assist1);
        }
    }
    
    private Ship selectRandomShip(Coordinate firstCoordinate, List<Coordinate> possibleVariants) {
        int random = new Random().nextInt(possibleVariants.size());
        Coordinate secondCoordinate = possibleVariants.get(random);
        return new Ship(firstCoordinate, secondCoordinate);
    }
    
    private void addPossibleVariantsToList(
            int firstX, int firstY, Coordinate firstCoordinate, List<Coordinate> possibleVariants, int addToXState,
            int addToYState) {
        Coordinate secondCoordinate;
        secondCoordinate = new Coordinate(firstX + addToXState, firstY + addToYState);
        if (Coordinate.isCoordinatesCorrect(firstCoordinate, secondCoordinate)) {
            Ship ship = new Ship(firstCoordinate, secondCoordinate);
            if (checkNoShipCollision(ship) && checkCorrectAmountOfShips(ship)) {
                possibleVariants.add(secondCoordinate);
    
            }
        }
    }
    
}