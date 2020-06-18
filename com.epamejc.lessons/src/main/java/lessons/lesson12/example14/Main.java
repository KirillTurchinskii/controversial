package lessons.lesson12.example14;

import java.io.*;

import lombok.SneakyThrows;

public class Main {

  @SneakyThrows
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Point p1 = new Point(1.0, 1.0);
    Point p2 = new Point(2.0, 2.0);
    Point p3 = new Point(3.0, 3.0);
    Line line1 = new Line(p1, p2, 1);
    Line line2 = new Line(p2, p3, 2);

    System.out.println("line 1 = " + line1);
    System.out.println("line 2 = " + line2);
    String fileName = "file 3.txt";

    FileOutputStream os = new FileOutputStream(fileName);
    ObjectOutputStream oos = new ObjectOutputStream(os);
    oos.writeObject(line1);

    oos.reset();
    line1.setIndex(3);

    oos.writeObject(line1);
    oos.close();
    os.close();

    System.out.println("Read objects:");
    FileInputStream is = new FileInputStream(fileName);
    ObjectInputStream ois = new ObjectInputStream(is);

    while (is.available() > 0) {
      Line line = (Line)ois.readObject();
      line.printInfo();
    }

  }

}
