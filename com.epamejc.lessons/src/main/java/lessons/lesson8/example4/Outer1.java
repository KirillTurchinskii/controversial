package lessons.lesson8.example4;

import java.util.Date;

public class Outer1 {

  String str;
  Date date;

  public Outer1(String str, Date date) {
    this.str = str;
    this.date = date;
  }

  class Inner {

    public void method() {
      System.out.println(str);
      System.out.println(date.getTime());
    }

    class Inner2 {

      public void method2() {
        System.out.println(str);
        System.out.println(date.getTime());
      }

    }

  }

}

class Outer2 extends Outer1 {

  public Outer2(String str, Date date) {
    super(str, date);
  }

  class InnerTest extends Inner {

    public void method() {
      System.out.println(str);
      System.out.println(date.getTime());
    }

    class Inner2 {

      public void method2() {
        System.out.println(str);
        System.out.println(date.getTime());
      }

    }

  }

}

class Main {

  public static void main(String[] args) {
    Outer2.Inner.Inner2 inner2 = new Outer2("Hello", new Date()).new Inner().new Inner2();
    inner2.method2();
  }

}
