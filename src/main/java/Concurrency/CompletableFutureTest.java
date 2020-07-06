package Concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName() + " I am running async"));
        executorService.shutdown();
    }
}
