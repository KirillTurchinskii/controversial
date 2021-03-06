package homeworks.homework8;

import java.util.List;

public class Employee {

  private final Person person;
  private final List<JobHistoryEntry> jobHistory;

  public Employee(Person person, List<JobHistoryEntry> jobHistory) {
    this.person = person;
    this.jobHistory = jobHistory;
  }

  public Person getPerson() {
    return person;
  }

  public List<JobHistoryEntry> getJobHistory() {
    return jobHistory;
  }

}