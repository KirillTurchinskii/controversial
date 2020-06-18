package lessons.lesson14.example5;

public class Cat {

  private int id;
  private int age;

  public Cat(int id, int age) {
    this.id = id;
    this.age = age;
  }

  @Override
  public int hashCode() {
    return 31 * id * age;
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
    return id == cat.id &&
           age == cat.age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

}
