package homeworks.homework8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static homeworks.homework8.GenerateData.getEmployees;

@SuppressWarnings({"unused"})
public class StreamOperations {

    void findPersonsEverWorkedInEpam() {
        List<Employee> employees = getEmployees();
        Person person;

        // TODO реализация, использовать Collectors.toList()
        List<Person> personsEverWorkedInEpam = employees.stream()
                                                       .filter(employee -> employee.getJobHistory().stream()
                                                                                   .anyMatch(jobHistoryEntry -> jobHistoryEntry.getCompany().equals("EPAM")))
                                                       .map(Employee::getPerson)
                                                       .collect(Collectors.toList());
        System.out.println(personsEverWorkedInEpam);
//        personsEverWorkedInEpam - should contain employees.get(0),  employees.get(1),
//                employees.get(4), employees.get(5)
    }

    void findPersonsBeganCareerInEpam() {
        List<Employee> employees = getEmployees();

        // TODO реализация, использовать Collectors.toList()
        List<Person> personsEverWorkedInEpam = employees.stream()
                                                       .filter(employee -> employee.getJobHistory().get(0).getCompany().equals("EPAM"))
                                                       .map(Employee::getPerson)
                                                       .collect(Collectors.toList());
        System.out.println(personsEverWorkedInEpam);
//        startedFromEpam - should contain
//                employees.get(0).getPerson(),
//                employees.get(1).getPerson(),
//                employees.get(4).getPerson()
    }

    void findAllCompanies() {
        List<Employee> employees = getEmployees();

        // TODO реализация, использовать Collectors.toSet()
//        Set<String> companies = employees.stream()
//                .map(employee -> employee.getJobHistory().stream()
//                                         .map(JobHistoryEntry::getCompany))
//                .
//                                        .collect(Collectors.toSet());

//        companies - should contain "EPAM", "google", "yandex", "mail.ru", "T-Systems"
    }

    void findMinimalAgeOfEmployees() {
        List<Employee> employees = getEmployees();

        // TODO реализация
        Integer minimalAge = null;

        // minmalAge = 21
    }

    // Посчитать средний возраст работников
    void calcAverageAgeOfEmployees() {
        List<Employee> employees = getEmployees();

        Double expected = null;

    }

    // Найти Person с самым длинным fullName
    void findPersonWithLongestFullName() {
        List<Employee> employees = getEmployees();

        Person expected = null;

    }

    // Найти работника с самой большой продолжительность на одной же позиции
    void findEmployeeWithMaximumDurationAtOnePosition() {
        List<Employee> employees = getEmployees();

        Employee expected = null;

    }

}
