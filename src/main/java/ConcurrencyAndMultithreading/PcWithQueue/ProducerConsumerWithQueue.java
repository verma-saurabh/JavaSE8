package ConcurrencyAndMultithreading.PcWithQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerWithQueue {
    public static void main(String[] args) throws InterruptedException {
        final Queue sharedQ = new LinkedList<Integer>();

        Thread consumerThread = new Thread(new Consumer(sharedQ, 4), "CONSUMER");
        Thread producerThread = new Thread(new Producer(sharedQ, 4), "PRODUCER");


        producerThread.start();
        consumerThread.start();

        producerThread.join();
    }
}

class Producer implements Runnable {

    private final Queue sharedQ;
    private int maxSize;

    public Producer(Queue sharedQ, int maxSize) {
        this.sharedQ = sharedQ;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedQ) {
                while (sharedQ.size() == maxSize) {
                    try {
                        System.out.println("Queue is FULL!!");
                        sharedQ.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int number = random.nextInt(100);
                System.out.println("Producing value " + number);
                sharedQ.add(number);
                sharedQ.notify();
            }
        }
    }
}

class Consumer implements Runnable {

    private final Queue sharedQ;
    private int maxSize;

    public Consumer(Queue sharedQ, int maxSize) {
        this.sharedQ = sharedQ;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedQ) {
                while (sharedQ.isEmpty()) {
                    try {
                        System.out.println("Queue is Empty!!");
                        sharedQ.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int number = (int) sharedQ.poll();
                System.out.println("removing Element " + number);
                sharedQ.notify();
            }
        }

    }
}
