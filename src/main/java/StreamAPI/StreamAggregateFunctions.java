package StreamAPI;

import ComparableVsComparator.Employee;
import ComparableVsComparator.EmployeeComparator;

import java.util.List;
import java.util.stream.Collectors;

public class StreamAggregateFunctions {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeComparator.createEmployees();

        //getting Emp Names
        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        //getting Emp ids
        List<Integer> ids = employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        //getting employee names length
        int totalLength = employees.stream()
                .map(Employee::getName)
                .mapToInt(String::length).sum();

        // getting the count of
        long count = employees.stream().count();

        names.forEach(System.out::println);

        ids.forEach(System.out::println);

        System.out.println(totalLength);

        System.out.println(count);
    }
}
