package seabattle;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final Coordinate firstCoordinate;
    private final Coordinate secondCoordinate;
    private final List<Coordinate> shipParts;
    private int length;
    private final int direction;

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

    public void setLength(final int length) {
        this.length = length;
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    private int calculateLength() {
        return (firstCoordinate.getX() == secondCoordinate.getX() ?
                Math.abs(firstCoordinate.getY() - secondCoordinate.getY()) :
                Math.abs(firstCoordinate.getX() - secondCoordinate.getX())) + 1;
    }
    
    private int calculateDirection() {
        if (firstCoordinate.getY() - secondCoordinate.getY() < 0) {
            return 2;
        }
        if (firstCoordinate.getX() - secondCoordinate.getX() < 0) {
            return 1;
        }
        return 0;
    }
    
    private List<Coordinate> fillShipPartsList() {
        final List<Coordinate> coordinates = new ArrayList<>();
        switch (direction) {
            case 0:
                coordinates.add(firstCoordinate);
                break;
            case 1:
                fillHorizontally(coordinates);
                break;
            case 2:
                fillVertically(coordinates);
                break;
            default:
                System.out.println("Unexpected value: " + direction);
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
