package homeworks.homework4.task4_4;

public class ReverseWords {

  public String getReversedString(String inputString) {
    String[] splitted = inputString.split(" ");
    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder reversedStringBuilder = new StringBuilder();

    for (int i = 0; i < splitted.length; i++) {
      String s = splitted[i];
      reversedStringBuilder.append(s);
      stringBuilder.append(reversedStringBuilder.reverse());
      if (i < splitted.length - 1) {
        stringBuilder.append(" ");
      }
      reversedStringBuilder.delete(0, reversedStringBuilder.length());
    }
    return stringBuilder.toString();
  }

}
