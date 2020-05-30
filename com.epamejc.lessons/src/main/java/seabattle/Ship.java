package seabattle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seabattle.states.Direction;

public class Ship {

    private final Coordinate firstCoordinate;
    private final Coordinate secondCoordinate;
    private final List<Coordinate> shipParts;
    private final int length;
    private final Direction direction;

    public Ship(final Coordinate firstCoordinate, final Coordinate secondCoordinate) {
        if (firstCoordinate.getX() > secondCoordinate.getX() || firstCoordinate.getY() > secondCoordinate.getY()) {
            this.firstCoordinate = secondCoordinate;
            this.secondCoordinate = firstCoordinate;
        } else {
            this.firstCoordinate = firstCoordinate;
            this.secondCoordinate = secondCoordinate;
        }
        this.length = calculateLength();
        this.direction = calculateDirection();
        this.shipParts = fillShipPartsList();
    }
    
    public Coordinate getFirstCoordinate() {
        return firstCoordinate;
    }
    
    public Coordinate getSecondCoordinate() {
        return secondCoordinate;
    }
    
    public List<Coordinate> getShipParts() {
        return shipParts;
    }

    public int getLength() {
        return length;
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override public int hashCode() {
        return Objects.hash(getFirstCoordinate(), getSecondCoordinate(), getShipParts(), getLength(), getDirection());
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship)o;
        return getLength() == ship.getLength() &&
               getDirection() == ship.getDirection() &&
               getFirstCoordinate().equals(ship.getFirstCoordinate()) &&
               getSecondCoordinate().equals(ship.getSecondCoordinate()) &&
               Objects.equals(getShipParts(), ship.getShipParts());
    }

    private int calculateLength() {
        return (firstCoordinate.getX() == secondCoordinate.getX() ?
                Math.abs(firstCoordinate.getY() - secondCoordinate.getY()) :
                Math.abs(firstCoordinate.getX() - secondCoordinate.getX())) + 1;
    }

    private Direction calculateDirection() {
        if (firstCoordinate.getY() - secondCoordinate.getY() < 0) {
            return Direction.VERTICAL;
        }
        if (firstCoordinate.getX() - secondCoordinate.getX() < 0) {
            return Direction.HORIZONTAL;
        }
        return Direction.ONE_CELL;
    }
    
    private List<Coordinate> fillShipPartsList() {
        final List<Coordinate> coordinates = new ArrayList<>();
        switch (direction) {
            case ONE_CELL:
                coordinates.add(firstCoordinate);
                break;
            case HORIZONTAL:
                fillHorizontally(coordinates);
                break;
            case VERTICAL:
                fillVertically(coordinates);
                break;
            default:
                System.out.println("Unexpected value: " + direction);
                break;
        }
    
        return coordinates;
    }

    private void fillHorizontally(final List<Coordinate> coordinates) {
        for (int i = 0; i < length; i++) {
            coordinates.add(new Coordinate(firstCoordinate.getX() + i, firstCoordinate.getY()));
        }
    }

    private void fillVertically(final List<Coordinate> coordinates) {
        for (int i = 0; i < length; i++) {
            coordinates.add(new Coordinate(firstCoordinate.getX(), firstCoordinate.getY() + i));
        }
    }
    
}
