package lessons.lesson12.example13;

public class Cat {

  private static final long serialVersionUID = 1568764800L;

  private int id;
  private String name;

  public Cat(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Cat() {
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
//    private int age;

}
