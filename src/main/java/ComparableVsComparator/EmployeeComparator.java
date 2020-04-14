package ComparableVsComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EmployeeComparator {
    public List<Employee> employees = createEmployees();

    public static List<Employee> createEmployees() {
        return Arrays.asList(new Employee(1, "Test Test"),
                new Employee(2, "Hello World"),
                new Employee(3, "Java Eight"),
                new Employee(4, "Java Streams"));
    }

    public static void main(String[] args) {
        List<Employee> employees = createEmployees();

        Employee maxId = employees.stream()
                .max(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee employee1, Employee employee2) {
                        return employee1.getId() - employee2.getId();
                    }
                }).orElse(Employee.DEFAULT_EMPLOYEE);

        Employee maxName = employees.stream()
                .max(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee employee1, Employee employee2) {
                        return employee1.toString().compareTo(employee2.toString());
                    }
                }).orElse(Employee.DEFAULT_EMPLOYEE);

        ///// Using Lambdas

        maxId = employees.stream().
                max((e1, e2) -> {
                    return e1.getId() - e2.getId();
                }).
                orElse(Employee.DEFAULT_EMPLOYEE);

        //

        maxId = employees.stream().
                max(Comparator.comparingInt(Employee::getId)).orElse(Employee.DEFAULT_EMPLOYEE);


    }

    public void defaultSort() {
        employees.stream()
                .sorted().forEach(System.out::println);
    }

    public void reverseSort() {
        employees.stream().sorted();
    }

    public void sortedByIdAndThenName() {
        employees.stream().sorted(Comparator.comparingInt(Employee::getId)
                .thenComparing(Employee::getName)).forEach(System.out::println);
    }
}
