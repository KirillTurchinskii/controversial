package lessons.lesson15.example4;

import java.util.List;

public class Book {

  private String bookName;
  private List<Author> authorList;

  public Book(String bookName, List<Author> authorList) {
    this.bookName = bookName;
    this.authorList = authorList;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public List<Author> getAuthorList() {
    return authorList;
  }

  public void setAuthorList(List<Author> authorList) {
    this.authorList = authorList;
  }

}
