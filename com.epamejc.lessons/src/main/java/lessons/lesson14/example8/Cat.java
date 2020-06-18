package lessons.lesson14.example8;

import java.util.Comparator;

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int compareTo(Cat o) {
    Comparator<Cat> catComparator = Comparator.comparingInt(Cat::getId);
    return catComparator.compare(this, o);
  }

}
