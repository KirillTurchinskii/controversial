package lessons.lesson12.example8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

import lombok.SneakyThrows;

public class Main {

  @SneakyThrows
  public static void main(String[] args) throws IOException {
    FileInputStream inFile1 = new FileInputStream("file 1.txt");
    FileInputStream inFile2 = new FileInputStream("file 2.txt");
    SequenceInputStream sequenceStream = new SequenceInputStream(inFile1, inFile2);
    FileOutputStream outFile = new FileOutputStream("file 3.txt");

    int readedByte = sequenceStream.read();
    while (readedByte != -1) {
      outFile.write(readedByte);
      readedByte = sequenceStream.read();
    }

  }

}
