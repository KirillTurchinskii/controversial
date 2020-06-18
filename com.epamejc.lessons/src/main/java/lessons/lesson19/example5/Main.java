package lessons.lesson19.example5;

public class Main {

  public static void main(String[] args) {
    Object lock1 = new Object();
    Object lock2 = new Object();
    final ThreadA threadA = new ThreadA(lock1, lock2);
    final ThreadB threadB = new ThreadB(lock1, lock2);
    threadA.start();
    threadB.start();
  }

}

class ThreadA extends Thread {

  private final Object lock1;
  private final Object lock2;

  public ThreadA(Object lock1, Object lock2) {
    this.lock1 = lock1;
    this.lock2 = lock2;
  }

  @Override
  public void run() {
    synchronized (lock1) {
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread()
                               .getName() + " LOCKED LOCK1 ");

      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("WE ARE WAITING FOR UNLOCK1");
      synchronized (lock2) {
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread()
                                 .getName() + " UNLOCKED LOCK1 AND LOCKED LOCK2");
      }
    }
  }

}

class ThreadB extends Thread {

  private final Object lock1;
  private final Object lock2;

  public ThreadB(Object lock1, Object lock2) {
    this.lock1 = lock1;
    this.lock2 = lock2;
  }

  @Override
  public void run() {
    synchronized (lock2) {
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread()
                               .getName() + " LOCKED LOCK2 ");

      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("WE ARE WAITING FOR UNLOCK2");
      synchronized (lock1) {
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread()
                                 .getName() + " UNLOCKED LOCK2 AND LOCKED LOCK1");
      }
    }
  }

}