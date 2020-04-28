package ConcurrencyAndMultithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class BarrierTest {

    public static void main(String[] args) {

        class Friend implements Callable<String> {
            private CyclicBarrier barrier;

            public Friend(CyclicBarrier barrier) {
                this.barrier = barrier;
            }

            @Override
            public String call() throws Exception {

                Random random = new Random();
                Thread.sleep(random.nextInt(20) * 100 + 100);
                System.out.println("I just arrived , waiting for others");
                barrier.await(5, TimeUnit.SECONDS);
                System.out.println("We can proceed now");
                return "Ok";
            }
        }

        ExecutorService service = Executors.newFixedThreadPool(4);
        List<Future<String>> futures = new ArrayList<>();
        CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Barrier Opening"));


        try {
            for (int i = 0; i < 4; i++) {
                Friend friend = new Friend(barrier);
                futures.add(service.submit(friend));
            }

            futures.forEach(
                    future -> {
                        try {
                            future.get(200, TimeUnit.MILLISECONDS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
