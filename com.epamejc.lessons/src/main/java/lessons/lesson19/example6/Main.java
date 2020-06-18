package lessons.lesson19.example6;

import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    final Cat cat = new Cat();
    final CatThread catThread = new CatThread(cat);
    catThread.start();

    TimeUnit.SECONDS.sleep(2);
    cat.setName("BARSIK");
    synchronized (Resource.lock) {
      Resource.lock.notify();
//            cat.notify();
    }
  }

}

class Resource {

  static Object lock = new Object();

}

class CatThread extends Thread {

  private final Cat cat;

  public CatThread(Cat cat) {
    this.cat = cat;
  }

  @Override
  @SneakyThrows
  public void run() {
    System.out.println("THREAD CAT STARTED");
    synchronized (Resource.lock) {
      try {
        Resource.lock.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//            cat.wait();
    }
    System.out.println(cat.getName());
    System.out.println("THREAD CAT ENDED!");
  }

}

class Cat {

  private String name;

  public Cat() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}