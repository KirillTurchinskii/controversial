package lessons.lesson12.example13;

import java.io.Serializable;

import lombok.Data;

@Data
public class ExtendedCat extends Cat implements Serializable {

  private final String type;
//    private String height;

  public ExtendedCat(int id, String name, String type) {
    super(id, name);
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
