package lessons.lesson20.example10;

import java.util.concurrent.Semaphore;

public class Main {

  public static void main(String[] args) {
//    final Phaser phaser = new Phaser();
//    final Exchanger<Object> objectExchanger = new Exchanger<Object>();
//    final CountDownLatch countDownLatch = new CountDownLatch();
//    final CyclicBarrier cyclicBarrier = new CyclicBarrier();
    final Semaphore semaphore = new Semaphore(2);
    final Cat barsik = new Cat("Barsik");
    final Cat murzik = new Cat("Murzik");
    final Cat kitty = new Cat("Kitty");
    final Cat rijik = new Cat("Rijik");
    final Cat bagira = new Cat("Bagira");

    new MySemaphoreThread(barsik, semaphore).start();
    new MySemaphoreThread(murzik, semaphore).start();
    new MySemaphoreThread(kitty, semaphore).start();
    new MySemaphoreThread(rijik, semaphore).start();
    new MySemaphoreThread(bagira, semaphore).start();
  }

}

class MySemaphoreThread extends Thread {

  private final Semaphore semaphore;

  public MySemaphoreThread(Cat cat, Semaphore semaphore) {
    super(cat.getName());
    this.semaphore = semaphore;
  }

  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread()
                             .getName() + " ready to get dish");
    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread()
                             .getName() + " is eating");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread()
                             .getName() + " ready to leave");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread()
                             .getName() + " left");
    semaphore.release();
  }

}

class Cat {

  private String name;

  public Cat(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}