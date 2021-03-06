package homeworks.homework5.task5_2;

public class Power {

  public static double calcPow(int n, int p) {
    if (p > 0) {
      return n * calcPow(n, p - 1);
    } else if (p < 0) {
      return 1 / (n * calcPow(n, -(p + 1)));
    } else {
      return 1;
    }
  }

}
