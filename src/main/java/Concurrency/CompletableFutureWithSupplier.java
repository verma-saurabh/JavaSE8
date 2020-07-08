package Concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureWithSupplier {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Supplier<String> supplier = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        };
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier, executor);

        String string = completableFuture.join();
        completableFuture.obtrudeValue("Too Long");
        System.out.println(string);


        string = completableFuture.join();
        System.out.println(string);
        executor.shutdown();
    }
}
