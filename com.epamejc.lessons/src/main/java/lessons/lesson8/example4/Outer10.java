package lessons.lesson8.example4;

public class Outer10 {

  private static final int y = 4;
  private final int x = 3;

  static class Inner1 {

    public void method() {
      System.out.println("y=" + y);
//            System.out.println("x=" + x);// ERROR
    }

  }

}

