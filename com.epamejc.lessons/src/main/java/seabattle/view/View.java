package seabattle.view;

import java.util.Arrays;

import seabattle.Coordinate;
import seabattle.Field;
import seabattle.Ship;
import seabattle.states.Assist;
import seabattle.states.Miss;
import seabattle.states.Shot;

public class View {

    private final String[][] textView = new String[10][10];
    private final String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

    public View() {
        for (final String[] strings : textView) {
            Arrays.fill(strings, GameObjectView.EMPTY.getState());
        }
    }

    public void printField(final Field field) {
        eraseData();
        updateFieldView(field);
        System.out.print("\t");
        for (final String name : names) {
            System.out.print(name + "\t");
        }
        System.out.println();
        for (int i = 0; i < textView.length; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < textView[i].length; j++) {
                System.out.print(textView[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    public void updateFieldView(final Field field) {
        updateShipView(field);
        updateAssistantView(field);
        updateShotsView(field);
        updateMissesView(field);
        //update();
        //printField(field);
    }

    private void eraseData() {
        for (final String[] strings : textView) {
            Arrays.fill(strings, GameObjectView.EMPTY.getState());
        }
    }

    private void updateMissesView(final Field field) {
        for (final Miss miss : field.misses) {
            textView[miss.getCoordinate()
                         .getY()][miss.getCoordinate()
                                      .getX()] = GameObjectView.MISS.getState();

        }
    }

    private void updateShipView(final Field field) {
        for (final Ship ship :
          field.ships) {
            for (final Coordinate coordinate :
              ship.getShipParts()) {
                textView[coordinate.getY()][coordinate.getX()] = GameObjectView.UNBROKEN.getState();
            }
        }
    }

    private void updateAssistantView(final Field field) {
        for (final Assist assist : field.assistSet) {
            textView[assist.getAssistPoint()
                           .getY()][assist.getAssistPoint()
                                          .getX()] = GameObjectView.IMPOSSIBLE.getState();

        }
    }

    private void updateShotsView(final Field field) {
        for (final Shot shot :
          field.shots) {
            textView[shot.getCoordinate()
                         .getY()][shot.getCoordinate()
                                      .getX()] = GameObjectView.BROKEN.getState();
        }
    }

}
