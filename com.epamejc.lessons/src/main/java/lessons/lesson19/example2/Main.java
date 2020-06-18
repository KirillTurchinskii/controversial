package lessons.lesson19.example2;

public class Main {

  public static void main(String[] args) {
    final Animal barsik = new Animal(1, "Barsik");
    final Animal rijik = new Animal(2, "Rijik");
    final Animal murzik = new Animal(3, "Murzik");

    new MyThread(barsik).start();
    new MyThread(rijik).start();
    new MyThread(murzik).start();
  }

}

class MyThread extends Thread {

  private final Animal animal;

  public MyThread(Animal animal) {
    this.animal = animal;
  }

  public void run() {
    animal.printInfo();
  }

}

class Animal {

  private int id;
  private String name;

  public Animal(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public synchronized void printInfo() {
    System.out.println(Thread.currentThread());
    System.out.println(this.id);
    System.out.println(this.name);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
