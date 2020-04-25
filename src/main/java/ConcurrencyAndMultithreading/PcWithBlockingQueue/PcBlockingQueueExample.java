package ConcurrencyAndMultithreading.PcWithBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PcBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue sharedQ = new LinkedBlockingQueue();

        Thread consumerThread = new Thread(new Consumer(sharedQ, 4), "CONSUMER");
        Thread producerThread = new Thread(new Producer(sharedQ, 4), "PRODUCER");


        producerThread.start();
        consumerThread.start();

        producerThread.join();
    }
}

class Producer implements Runnable {

    private final BlockingQueue sharedQ;
    private int maxSize;

    public Producer(BlockingQueue sharedQ, int maxSize) {
        this.sharedQ = sharedQ;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Random random = new Random();
                int number = random.nextInt(100);
                System.out.println("Producing value " + number);
                sharedQ.put(number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private final BlockingQueue sharedQ;
    private int maxSize;

    public Consumer(BlockingQueue sharedQ, int maxSize) {
        this.sharedQ = sharedQ;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(" Consumed Value " + sharedQ.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
