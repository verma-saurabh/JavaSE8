package StreamAPI;

import java.util.*;

public class StreamReduceMethod {
    public static void main(String[] args) {
        int[] arr = new int[10];
        List<Integer> numbers = new ArrayList<>();

        for (int i = 2; i < 20; i++) {
            numbers.add(i);
        }

        Optional<Integer> output = numbers.stream().reduce((a, b) -> a > b ? a : b);
        output.ifPresent(System.out::println);

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);


        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        sum = Arrays.stream(num).reduce(0, (a, b) -> a + b);

        System.out.println(sum);

    }
}
