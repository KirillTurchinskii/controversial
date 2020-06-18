package lessons.lesson12.example2;

public class WrapperCat {

  private Cat cat;

  public WrapperCat(Cat cat) {
    this.cat = cat;
  }

  public Cat getCat() {
    return cat;
  }

  public void setCat(Cat cat) {
    this.cat = cat;
  }

}
