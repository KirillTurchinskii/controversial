package lessons.lesson12.example3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import lombok.SneakyThrows;

public class Main {

  public static void main(String[] args) throws IOException {
    writeIntoFile();
    readFile();
  }

  @SneakyThrows
  private static void readFile() throws IOException {
    FileReader fileReader = new FileReader("my_text.txt");
    char[] chars = new char[100];
    fileReader.read(chars, 0, 5);
    System.out.println(Arrays.toString(chars));
  }

  @SneakyThrows
  private static void writeIntoFile() throws IOException {
    FileWriter fileWriter = new FileWriter("my_text.txt");
    fileWriter.write("Hello world");
    fileWriter.flush();
  }

}
