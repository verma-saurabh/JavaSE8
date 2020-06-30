package AsynchronousProgramming;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableFutureWithSupplier {
    public static void main(String[] args) {
        Supplier<String> task = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName();
        };

        CompletableFuture<String> future = CompletableFuture.supplyAsync(task);

        String output = future.join();
        System.out.println("Result is -> " + output);

        future.obtrudeValue("Took too long to complete");
        output = future.join();

        System.out.println("Result is -> " + output);
    }
}
