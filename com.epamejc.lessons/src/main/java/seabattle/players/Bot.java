package seabattle.players;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import seabattle.Coordinate;
import seabattle.Ship;
import seabattle.states.Shot;

public class Bot extends Player {
    
    private Shot previousHit;
    private List<Coordinate> possibleVariants = new ArrayList<>();
    
    
    @Override public void fieldFillStrategy() {
        super.fillListOfShipsRandomly();
    }

    @Override
    public void turn(final String s) {
        skip40rows();
        shotMethod();
    }
    
    @Override public void shotMethod() {
        System.out.println("My field");
        view.printField(myField);
        System.out.println("Opponents field");
        view.printField(opponentsField);
        shotWhileHit();
    }

    @Override
    protected int iterateShipParts(
      final Coordinate shotCoordinate, final Iterator shipIterator, final Ship ship, final Iterator shipPartsIterator) {
        while (shipPartsIterator.hasNext()) {
            final Coordinate shipCoordinate = (Coordinate)shipPartsIterator.next();
            if (shotCoordinate.equals(shipCoordinate)) {
                return hitOrKill(shotCoordinate, shipIterator, ship, shipPartsIterator, shipCoordinate);
            }
        }
        return 0;
    }

    @Override
    protected int hitOrKill(
      final Coordinate shotCoordinate, final Iterator shipIterator, final Ship ship, final Iterator shipPartsIterator,
      final Coordinate shipCoordinate) {
        if (ship.getShipParts().size() > 1) {
            hit(shotCoordinate, shipPartsIterator);
            return 1;
        } else {
            kill(shotCoordinate, shipIterator, ship, shipPartsIterator, shipCoordinate);
            return 2;
        }
    }
    
    @Override
    protected void shotWhileHit() {
        Coordinate shotCoordinate;
        int fireResult = -1;
        while (fireResult != 0 && getOpponentsFieldData().ships.size() > 0 && getMyField().ships.size() > 0) {
            shotCoordinate = getShotCoordinate();
            final Shot shot = new Shot(shotCoordinate);
            getOpponentsField().shots.add(shot);
            possibleVariants.remove(shotCoordinate);
            fireResult = takeAShot(getOpponentsFieldData(), shotCoordinate);
            if (fireResult > 0) {
                addPossibleVariantsOfShots(shotCoordinate);
                previousHit = shot;
                System.out.println("My Field");
                getView().printField(getMyField());
                System.out.println("My view of opponents field");
                getView().printField(getOpponentsField());
            }
        }
    }

    @Override
    protected void kill(
      final Coordinate shotCoordinate, final Iterator shipIterator, final Ship ship, final Iterator shipPartsIterator,
      final Coordinate shipCoordinate) {
        System.out.println("kill");
        shipPartsIterator.remove();
        getOpponentsField().addAssistPointsAroundKill(shotCoordinate, ship);
        shipIterator.remove();
        opponentsFieldData.getShots().add(new Shot(shipCoordinate));
        possibleVariants = new ArrayList<>();
    }

    @Override
    protected void hit(final Coordinate shotCoordinate, final Iterator shipPartsIterator) {
        shipPartsIterator.remove();
        System.out.println("hit");
        getOpponentsField().addAssistPointsAroundShot(shotCoordinate);
        final Shot shot = new Shot(shotCoordinate);
        getOpponentsFieldData().shots.add(shot);
        removeRedundantPossibleVariants(shotCoordinate, shot);
    }
    
    private Coordinate getShotCoordinate() {
        Coordinate shotCoordinate;
        if (possibleVariants.isEmpty()) {
            do {
                shotCoordinate = Coordinate.generateRandomCoordinate();
            } while (!Coordinate.isCoordinatesCorrect(shotCoordinate) || getOpponentsField().checkShotsCollision(
                    shotCoordinate));
        } else {
            shotCoordinate = possibleVariants.get(new Random().nextInt(possibleVariants.size()));
        }
        return shotCoordinate;
    }

    private void removeRedundantPossibleVariants(final Coordinate shotCoordinate, final Shot shot) {
        if (possibleVariants.size() > 1 && isNearPrevious(shot)) {
            final Ship hypotheticalShip = new Ship(shotCoordinate, previousHit.getCoordinate());
            switch (hypotheticalShip.getDirection()) {
                case 1:
                    removePossibleVerticalPoints();
                    break;
                case 2:
                    removePossibleHorizontalPoints();
                    break;
                default:
                    System.out.println("Unexpected value: " + hypotheticalShip.getDirection());
                    break;
            }
        }
    }
    
    private List<Coordinate> getPossibleVariants() {
        return possibleVariants;
    }
    
    private void removePossibleVerticalPoints() {
        final Iterator possibleVariantsIterator = getPossibleVariants().iterator();
        while (possibleVariantsIterator.hasNext()) {
            final Coordinate coordinate = (Coordinate)possibleVariantsIterator.next();
            if (coordinate.getY() > previousHit.getCoordinate().getY() ||
                coordinate.getY() < previousHit.getCoordinate().getY()) {
                possibleVariantsIterator.remove();
            }
        }
    }
    
    private void removePossibleHorizontalPoints() {
        final Iterator possibleVariantsIterator = getPossibleVariants().iterator();
        while (possibleVariantsIterator.hasNext()) {
            final Coordinate coordinate = (Coordinate)possibleVariantsIterator.next();
            if (coordinate.getX() > previousHit.getCoordinate().getX() ||
                coordinate.getX() < previousHit.getCoordinate().getX()) {
                possibleVariantsIterator.remove();
            }
        }
    }

    private boolean isNearPrevious(final Shot shot) {
        return Math.abs(previousHit.getCoordinate().getX() - shot.getCoordinate().getX()) < 2 && Math.abs(
          previousHit.getCoordinate().getY() - shot.getCoordinate().getY()) < 2;
    }

    private void addPossibleVariantsOfShots(final Coordinate shotCoordinate) {
        addPossibleVariants(shotCoordinate, 0, -1);
        addPossibleVariants(shotCoordinate, 1, 0);
        addPossibleVariants(shotCoordinate, 0, 1);
        addPossibleVariants(shotCoordinate, -1, 0);
    }

    private void addPossibleVariants(final Coordinate shotCoordinate, final int addToX, final int addToY) {
        Coordinate possibleCoordinate;
        possibleCoordinate = new Coordinate(shotCoordinate.getX() + addToX, shotCoordinate.getY() + addToY);
        if (Coordinate.isCoordinatesCorrect(possibleCoordinate) && !getOpponentsField().checkShotsCollision(
          possibleCoordinate)) {
            possibleVariants.add(possibleCoordinate);
        }
    }
    
}
