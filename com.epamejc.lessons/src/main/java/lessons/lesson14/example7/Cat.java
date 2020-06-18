package lessons.lesson14.example7;

public class Cat implements Comparable<Cat> {

  private int id;

  public Cat(int id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cat cat = (Cat)o;
    return id == cat.id;
  }

  @Override public String toString() {
    return super.toString();
  }

  @Override
  public int compareTo(Cat o) {
    return this.id - o.id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
