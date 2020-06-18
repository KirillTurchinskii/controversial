package lessons.lesson19.example7;

import lombok.SneakyThrows;

public class Main {

  public static void main(String[] args) {
    final Cat cat = new Cat();
    final CatThread catThread = new CatThread(cat);
    final CatThreadNotifySetName catThreadNotify = new CatThreadNotifySetName(cat);
    final CatThreadNotifySetAge catThreadNotifySetAge = new CatThreadNotifySetAge(cat);
    catThread.start();
    catThreadNotify.start();
    catThreadNotifySetAge.start();

    try {
      catThread.join();
      catThreadNotify.join();
      catThreadNotifySetAge.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}

class CatThreadNotifySetName extends Thread {

  private final Cat cat;

  public CatThreadNotifySetName(Cat cat) {
    this.cat = cat;
  }

  @Override
  @SneakyThrows
  public void run() {
    cat.setName("BARSIK");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    synchronized (cat) {
      System.out.println("NOTIFY THREAD NAME");
      cat.notifyAll();
    }
  }

}

class CatThreadNotifySetAge extends Thread {

  private final Cat cat;

  public CatThreadNotifySetAge(Cat cat) {
    this.cat = cat;
  }

  @Override
  public void run() {
    System.out.println("NOTIFY THREAD AGE");
    cat.setAge(10);
    synchronized (cat) {
      try {
        cat.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("THREAD CAT AGE ENDED!");
  }

}

class CatThread extends Thread {

  private final Cat cat;

  public CatThread(Cat cat) {
    this.cat = cat;
  }

  @Override
  public void run() {
    System.out.println("THREAD CAT INFO");
    synchronized (cat) {
      try {
        cat.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(cat.getName());
    System.out.println(cat.getAge());
    System.out.println("THREAD CAT ENDED!");
  }

}

class Cat {

  private String name;
  private int age;

  public Cat() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

}