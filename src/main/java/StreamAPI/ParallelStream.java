package StreamAPI;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
Parallel streams divide the provided task into many and run them in different threads
, utilizing multiple cores of the computer. On the other hand sequential streams work
 just like for-loop using a single core.

The tasks provided to the streams are typically the iterative operations performed
 on the elements of a collection or array or from other dynamic sources.
  Parallel execution of streams run multiple iterations simultaneously in different available cores.
 */
public class ParallelStream {
    public static void main(String[] args) {
        List<String> vehicles = Arrays.asList("bus", "car", "bicycle", "flight", "train", "train");

        run(vehicles.stream());
        run(vehicles.parallelStream());

    }

    public static void run(Stream<String> stream) {

        stream.forEach(s -> {
            System.out.println(LocalTime.now() + " - value: " + s +
                    " - thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
