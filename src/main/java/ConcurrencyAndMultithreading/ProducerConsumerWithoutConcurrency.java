package ConcurrencyAndMultithreading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerWithoutConcurrency {
    public static void main(String[] args) {
        ExecutorService application = Executors.newFixedThreadPool(2);
        Buffer buffer = new UnSyncBuffer();
        System.out.println("Action\t\tValue\tProduced\tConsumed");
        System.out.println("------\t\t-----\t--------\t--------\n");

        try {
            application.execute(new Producer(buffer));
            application.execute(new Consumer(buffer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        application.shutdown();
    }
}

class Producer implements Runnable {
    private static Random generator = new Random();
    private Buffer sharedLocation;

    public Producer(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                sharedLocation.set(i);
                sum += i;
                System.out.println(" sum = " + sum);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.printf("\n%s\n%s\n", "Producer done producing.",
                "Terminating Producer.");
    }
}

class Consumer implements Runnable {
    private static Random generator = new Random();
    private Buffer sharedLocation;

    public Consumer(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            try {

                Thread.sleep(generator.nextInt(3000));
                sum += sharedLocation.get();
                System.out.println(" sum = " + sum);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.printf("\n%s\n%s\n", "Consumer read a total of " + sum + " done consuming.",
                "Terminating Consumer.");
    }

}

class UnSyncBuffer implements Buffer {

    private int buffer = -1;

    @Override
    public int get() {
        System.out.printf("Consumer reads\t%2d", buffer);
        return buffer;
    }

    @Override
    public void set(int i) {
        System.out.printf("Producer writes\t%2d", i);
        buffer = i;
    }
}

interface Buffer {

    public int get();

    public void set(int i);
}