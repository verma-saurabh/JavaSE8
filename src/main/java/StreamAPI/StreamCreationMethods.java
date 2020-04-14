package StreamAPI;

import ComparableVsComparator.Employee;
import ComparableVsComparator.EmployeeComparator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCreationMethods {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeComparator.createEmployees();

        //creation of stream from collection

        Stream<Employee> stream = employees.stream();

        // creation of stream from an array

        String[] arr = new String[]{"test", "Array", "of", "String"};

        Stream<String> stringStream = Arrays.stream(arr);

        // changing the array stream to list

        List<String> words = stringStream.map(w -> w.toUpperCase()).collect(Collectors.toList());

        ///// Using stream class

        Stream<Integer> integerStream = Stream.of(5, 4, 3, 2, 1);

        integerStream.limit(5).forEach(System.out::println);


        Stream.Builder<Integer> integerBuilder = Stream.<Integer>builder();
        Stream<Integer> intStream = integerBuilder.add(1).add(2).build();

        int sum = intStream.mapToInt(Integer::intValue).sum();


        //using stream iterate method

        Stream<Integer> infiniteStream = Stream.iterate(10, n -> n + 1);
        long sumOfSomeNumbers= infiniteStream.filter(n->n<20).mapToInt(Integer::intValue).sum();


    }
}
