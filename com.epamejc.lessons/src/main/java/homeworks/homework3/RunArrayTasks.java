package homeworks.homework3;

import homeworks.homework3.task3_1.CharArrayOperation;
import homeworks.homework3.task3_2.ABStrategy;
import homeworks.homework3.task3_3.PyramidArray;
import utils.InputReaderUtils;

public class RunArrayTasks {

  public static void main(String[] args) {
    System.out.println("Input task number from 1 to 3");
    String task = InputReaderUtils.nextString();
    CharArrayOperation operation = new CharArrayOperation();
    char[][] chars;
    int rows;
    int columns;
    switch (task) {
      case "1":
        System.out.println("Insert rows and columns amount");
        System.out.println("For example:");
        System.out.println("5 3");
        String nextString = InputReaderUtils.nextString();
        chars = operation.getRandomFilledArrayOfArrays(nextString);
        operation.printTwoDimensionalCharArray(chars);
        break;
      case "2":
        System.out.println("Input rows");
        rows = InputReaderUtils.nextInt();
        System.out.println("Input columns");
        columns = InputReaderUtils.nextInt();
        chars = operation.getRandomFilledArrayOfArrays(rows, columns);
        operation.printTwoDimensionalCharArray(chars);
        ABStrategy strategy = new ABStrategy();
        System.out.println("Input \"A\" or \"B\" to select specific strategy");
        String str = InputReaderUtils.nextString();
        System.out.println(strategy.selectStrategy(chars, str));
        break;
      case "3":
        PyramidArray pyramidArray = new PyramidArray();
        System.out.println("Input height of pyramid");
        rows = InputReaderUtils.nextInt();
        chars = pyramidArray.initializePyramidArray(rows);
        operation.printTwoDimensionalCharArray(chars);
        break;
    }
  }

}
