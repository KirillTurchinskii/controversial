package lessons.lesson17.example3;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

  public static void main(String[] args) {
    Animal<Integer> animal1 = new Animal<>(100);
    Animal<Double> animal2 = new Animal<>(300.345);
    animal1.resultWeight(() -> animal1.getWeight(), number -> System.out.println(number));
    animal2.resultWeight(() -> animal2.getWeight(), number -> System.out.println(number));
  }

}

class Animal <T> {

  private T weight;

  public Animal(T weight) {
    this.weight = weight;
  }

  public T getWeight() {
    return weight;
  }

  public void resultWeight(Supplier<? extends T> supplier, Consumer<? super T> consumer) {
    consumer.accept(supplier.get());
  }

  public void setWeight(T weight) {
    this.weight = weight;
  }

}
