package homeworks.homework4.task4_3;

public class SwapSymbols {

  public int[] getSwapNumsFromInput(String string) {
    int[] swapNums = new int[2];
    if (string == null) {
      return swapNums;
    }
    String[] splitted = string.split(", ");
    for (int i = 0; i < splitted.length && i < swapNums.length; i++) {
      if (isNumeric(splitted[i])) {
        swapNums[i] = Integer.parseInt(splitted[i]);
      }
    }
    return swapNums;
  }

  public String swapCharsInString(int[] swapNums, String inputString) {
    char swapChar;
    if ((swapNums[0] < inputString.length()) && (swapNums[0] >= 0) && (swapNums[1] < inputString.length()) &&
        (swapNums[1] > 0)) {
      StringBuilder stringBuilder = new StringBuilder(inputString);
      swapChar = stringBuilder.charAt(swapNums[0]);
      stringBuilder.setCharAt(swapNums[0], stringBuilder.charAt(swapNums[1]));
      stringBuilder.setCharAt(swapNums[1], swapChar);
      return stringBuilder.toString();
    } else {
      System.out.println("Nums are too big for this string");
      return "";
    }
  }

  private boolean isNumeric(String strToNum) {
    if (strToNum == null) {
      return false;
    }
    try {
      Integer.parseInt(strToNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

}
