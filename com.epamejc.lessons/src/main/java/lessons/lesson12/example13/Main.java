package lessons.lesson12.example13;

import java.io.*;

import lombok.SneakyThrows;

public class Main {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    ExtendedCat cat = new ExtendedCat(1, "Barsik", "BIG");
    writeInFile(cat);
    ExtendedCat readedCat = (ExtendedCat)readInFile();
//        System.out.println(readedCat.getId() + " " + readedCat.getName() + " " + readedCat.getAge()
//        + " " + readedCat.getType());

    System.out.println(readedCat.getId() + " " + readedCat.getName() + " " + readedCat.getType());
  }

  @SneakyThrows
  private static void writeInFile(Cat cat) throws IOException {
    ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream("file 2.txt"));
    objectOutput.writeObject(cat);
  }

  @SneakyThrows
  private static Object readInFile() throws IOException, ClassNotFoundException {
    ObjectInput objectInput = new ObjectInputStream(new FileInputStream("file 2.txt"));
    return objectInput.readObject();
  }

}
