package ConcurrencyAndMultithreading.PcWithCallable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PcWithCallable {
    public static void main(String[] args) throws InterruptedException {

        List<Integer> buffer = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Condition isEmpty = lock.newCondition();
        Condition isFull = lock.newCondition();


        class Producer implements Callable<String> {

            @Override
            public String call() throws Exception {
                int count = 0;

                while (count++ < 50) {
                    try {
                        lock.lock();
                        while (isFull(buffer)) {
                            if (!isFull.await(10, TimeUnit.MILLISECONDS)) {
                                throw new TimeoutException("Producer Timeout");
                            }
                        }
                        buffer.add(1);
                        isEmpty.signalAll();
                    } finally {
                        lock.unlock();
                    }

                }

                return "Produced " + (count - 1);
            }
        }

        class Consumer implements Callable<String> {

            @Override
            public String call() throws Exception {
                int count = 0;
                while (count++ < 50) {
                    try {

                        lock.lock();
                        while (isEmpty(buffer)) {
                            if (!isEmpty.await(10, TimeUnit.MILLISECONDS)) {
                                throw new TimeoutException("Producer Timeout");
                            }
                        }
                        buffer.remove(buffer.size() - 1);
                        isFull.signalAll();
                    } finally {
                        lock.unlock();
                    }

                }
                return "Consumed " + (count - 1);
            }
        }

        List<Producer> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            producers.add(new Producer());
            consumers.add(new Consumer());
        }


        List<Callable<String>> procucersconsumers = new ArrayList<>();
        procucersconsumers.addAll(producers);
        procucersconsumers.addAll(consumers);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try {
            List<Future<String>> futures = executorService.invokeAll(procucersconsumers);

            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } finally {
            executorService.shutdown();
            System.out.println("Executor Service Shutdown");
        }

    }

    static boolean isFull(List<Integer> buffer) {
        return buffer.size() > 50;
    }

    static boolean isEmpty(List<Integer> buffer) {
        return buffer.size() == 0;
    }
}