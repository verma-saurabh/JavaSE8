package ExceptionHandling;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ExceptionInsideLambdaExp {
    public static void main(String[] args) {

        // Arithmetic Exception
        List<Integer> dividend = Arrays.asList(1, 0, 2, 3, 4, 5, 6);
        int divisor = 50;
        dividend.forEach(i -> {
            try {
                System.out.println(divisor / i);
            } catch (ArithmeticException e) {
                System.err.println(e.getMessage());
            }

        });

        System.out.println("using exception wrapper");
        dividend.forEach(exceptionWrapper(i -> System.out.println(divisor / i)));

        System.out.println("using Generic exception wrapper");
        dividend.forEach(genericExceptionWrapper(i -> System.out.println(divisor / i)));

    }

    public static Consumer<Integer> exceptionWrapper(Consumer<Integer> consumer) {

        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                System.err.println(e.getMessage());
            }
        };

    }

    public static <T> Consumer<T> genericExceptionWrapper(Consumer<T> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        };
    }
}
