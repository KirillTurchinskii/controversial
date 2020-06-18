package lessons.lesson12.example4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import lombok.SneakyThrows;

public class Main {

  public static void main(String[] args) throws IOException {
    readFile();
  }

  @SneakyThrows
  private static void readFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("my_text.txt"));
    String s = reader.readLine();
    System.out.println(s);
  }

  @SneakyThrows
  private static void writeIntoFile() throws IOException {
    FileWriter fileWriter = new FileWriter("my_text.txt");
    fileWriter.write("Hello world");
    fileWriter.flush();
  }

}
