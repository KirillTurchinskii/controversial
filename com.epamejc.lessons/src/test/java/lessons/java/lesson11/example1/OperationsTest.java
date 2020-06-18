package lessons.java.lesson11.example1;

import org.junit.*;

import lessons.lesson11.example1.Operations;

import static org.junit.Assert.assertEquals;

public class OperationsTest {

  private static Operations operations;
  @Rule
  public PrepareRule prepareRule = new PrepareRule();

  @BeforeClass
  public static void setup() {
    operations = new Operations();
//        System.out.println("SETUP BEFORE CLASS");
  }

  @AfterClass
  public static void after2() {
    System.out.println("AFTER CLASS");
  }
//
//    @After
//    public void after() {
//        System.out.println("AFTER");
//    }

  @Before
  public void setup2() {
    System.out.println("SETUP BEFORE");
  }

  @Test
  public void testGetSum() {
    System.out.println("TEST 1");
    int actual = operations.getSum(3, 5);
    int actualSecond = operations.getSum(3, 5);
    int expected = 8;
    int expectedSecond = 10;
    assertEquals(expected, actual);
    assertEquals(expectedSecond, actualSecond);
  }

  @Test
  public void testGetSumOfStringsWithCorrectValues() {
    System.out.println("TEST 2");
    int actual = operations.getSumOfStrings("10", "12");
    int expected = 22;
    assertEquals(expected, actual);
  }

  @Test (expected = NumberFormatException.class)
  public void testGetSumOfStringsWithIncorrectValues() {
    System.out.println("TEST 3");
    operations.getSumOfStrings("String", "12");
  }

}
