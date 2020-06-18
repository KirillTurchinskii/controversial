package lessons.lesson19.example3;

public class ATM {

  private int balance;

  public ATM(int balance) {
    this.balance = balance;
  }

  public synchronized void deposit(int amount) {
    balance += amount;
    System.out.println("User name: " + Thread.currentThread()
                                             .getName() + " deposit: " + amount);
    System.out.println("ATM balance: " + getBalance());
  }

  public void withdraw(int amount) {
    synchronized (this) {
      balance -= amount;
      System.out.println("User name: " + Thread.currentThread()
                                               .getName() + " withdraw: " + amount);
      System.out.println("ATM balance: " + getBalance());
    }
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

}
