package homeworks.homework5;

import homeworks.homework5.task5_1.Fibonacci;
import homeworks.homework5.task5_2.Power;
import homeworks.homework5.task5_3.Digits;
import utils.InputReaderUtils;

public class RunRecursionTasks {

  public static void main(String[] args) {
    System.out.println("Input task number from 1 to 3");
    String task = InputReaderUtils.nextString();
    int number;
    switch (task) {
      case "1":
        System.out.println("Input n to get N-th number in fibonacci");
        number = InputReaderUtils.nextInt();
        System.out.println(Fibonacci.calculateFibonacci(number));
        break;
      case "2":
        System.out.println("Input number");
        number = InputReaderUtils.nextInt();
        System.out.println("Input pow");
        int pow = InputReaderUtils.nextInt();
        System.out.println(Power.calcPow(number, pow));
        break;
      case "3":
        System.out.println("Input number to calculate ammount of Digits");
        number = InputReaderUtils.nextInt();
        System.out.println(Digits.getAmountOfDigits(number));
        break;
    }
  }

}
