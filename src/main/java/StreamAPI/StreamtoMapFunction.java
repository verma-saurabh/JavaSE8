package StreamAPI;

import ComparableVsComparator.Employee;
import ComparableVsComparator.EmployeeComparator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamtoMapFunction {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeComparator.createEmployees();

        // from object to map
        Map<Integer, Employee> empMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));

        empMap.forEach((k, v) -> {
            System.out.println(k + "->" + v);
        });


        // sort the empMap object using the id

        empMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    System.out.println(entry.getKey() + "->" + entry.getValue());
                });

        //Sort employyes by name
        empMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName)))
                .forEach(entry->{
                    System.out.println(entry.getKey() + "->" + entry.getValue());
                });

        //reverse sort employees by id
        empMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(entry -> {
                    System.out.println(entry.getKey() + "->" + entry.getValue());
                });

        //reverse Sort employyes by name
        empMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName).reversed()))
                .forEach(entry->{
                    System.out.println(entry.getKey() + "->" + entry.getValue());
                });

    }
}
