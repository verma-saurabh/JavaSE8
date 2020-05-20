package ConcurrencyAndMultithreading;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);
        Thread cacheService = new Thread(new MyWorker("CacheService", 1000, latch));
        Thread alertService = new Thread(new MyWorker("AlertService", 1000, latch));
        Thread validationService = new Thread(new MyWorker("ValidationService", 1000, latch));

        cacheService.start();
        alertService.start();
        validationService.start();
        try {
            latch.await();  //main thread is waiting on CountDownLatch to finish
            System.out.println("All services are up, Application is starting now");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

@Slf4j
class MyWorker implements Runnable {
    private final String name;
    private final int timeToStart;
    private CountDownLatch countDownLatch;

    public MyWorker(String name, int timeToStart, CountDownLatch countDownLatch) {
        this.name = name;
        this.timeToStart = timeToStart;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeToStart);
        } catch (InterruptedException ex) {
            System.out.println((MyWorker.class.getName()));
        }
        System.out.println(name + " is Up");
        countDownLatch.countDown();
    }
}