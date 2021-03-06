package homeworks.homework4;

import homeworks.homework4.task4_1.OddEvenSubstring;
import homeworks.homework4.task4_2.StringGenerator;
import homeworks.homework4.task4_3.SwapSymbols;
import homeworks.homework4.task4_4.ReverseWords;
import utils.InputReaderUtils;

public class RunStringTasks {

  public static void main(String[] args) {
    System.out.println("Input source string");
    String inputString = InputReaderUtils.nextString();
    System.out.println("Input task number from 1 to 4");
    String task = InputReaderUtils.nextString();
    switch (task) {
      case "1":
        OddEvenSubstring oddEvenSubstring = new OddEvenSubstring();
        System.out.println("Input \"odd\" or \"even\"");
        String mod = InputReaderUtils.nextString();
        System.out.println(oddEvenSubstring.useStrategy(inputString, mod));
        break;
      case "2":
        StringGenerator stringGenerator = new StringGenerator();
        int nextInt;
        StringBuilder stringBuilder = new StringBuilder();
        do {
          System.out.println("Input index to continue OR negative number to stop");
          nextInt = InputReaderUtils.nextInt();
          System.out.println(stringGenerator.generateString(inputString, stringBuilder, nextInt));
        } while (nextInt >= 0);
        break;
      case "3":
        System.out.println("Input two swapNums nums");
        System.out.println("For example");
        System.out.println("4, 1");
        String setup = InputReaderUtils.nextString();
        SwapSymbols swapSymbols = new SwapSymbols();
        int[] swapNums = swapSymbols.getSwapNumsFromInput(setup);
        System.out.println(swapSymbols.swapCharsInString(swapNums, inputString));
        break;
      case "4":
        ReverseWords reverseWords = new ReverseWords();
        System.out.println(reverseWords.getReversedString(inputString));
        break;
    }
  }

}
