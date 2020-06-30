package AsynchronousProgramming;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureTest {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println(Thread.currentThread().getName() + " I am running asynchronously");
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture.runAsync(task, executorService);
        executorService.shutdown();
    }
}
