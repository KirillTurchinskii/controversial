package homeworks.homework6;

import java.lang.reflect.Constructor;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

  private final String name;
  private final int age;

  @CustomAnnotation (name = "Barsik", age = 6)
  public Person() {
    CustomAnnotation customAnnotation = getCustomAnnotation();
    this.name = customAnnotation.name();
    this.age = customAnnotation.age();
  }

  public String getStr() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Player[" + "name: " + name + ", age = " + age + "]";
  }

  private CustomAnnotation getCustomAnnotation() {
    Constructor<Person> personConstructor = null;
    try {
      personConstructor = Person.class.getDeclaredConstructor();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    return Objects.requireNonNull(personConstructor).getAnnotation(CustomAnnotation.class);

  }

}
