package lessons.lesson16.example4;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    User user = getUserById(2);
    System.out.println(user);
  }

  public static User getUserById(int id) {
    List<User> users = getUsers();
    return users.stream()
                .parallel()
                .filter(user -> user.getId() == id)
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("User not found by Id"));
  }

  // DB
  public static List<User> getUsers() {
    List<User> users = new ArrayList<>();
    users.add(new User(2, "Boris"));
    users.add(new User(2, "Vladimir"));
    users.add(new User(2, "Alexey"));
    users.add(new User(2, "Dmitrii"));
    users.add(new User(2, "Masha"));
    users.add(new User(2, "Nastya"));
    return users;
  }

}

class User {

  private int id;
  private String name;

  public User(int id, String name) {
    this.id = id;
    this.name = name;
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

}
