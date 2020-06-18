package lessons.lesson8.example3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) {
    try {
      getLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void getLine() throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

    }
  }

}
