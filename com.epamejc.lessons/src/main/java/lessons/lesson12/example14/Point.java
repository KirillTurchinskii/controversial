package lessons.lesson12.example14;

public class Point implements java.io.Serializable {

  private final double x;
  private final double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return "(" + x + "," + y + ") reference=" + super.toString();
  }

}
