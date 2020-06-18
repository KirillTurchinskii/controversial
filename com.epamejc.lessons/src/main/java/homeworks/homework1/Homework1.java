package homeworks.homework1;

import utils.InputReaderUtils;

public class Homework1 {

  public static void main(String[] args) {
    TrafficLight trafficLight = new TrafficLight();
    int minutes;
    do {
      System.out.println("Input minutes value");
      minutes = InputReaderUtils.nextInt();
      System.out.println(trafficLight.getColor(minutes));
    } while (minutes >= 0);
  }

}