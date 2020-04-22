package ConcurrencyAndMultithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLockExample {
    static final int MAX_T = 2;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        Runnable w1 = new Worker("Job1", lock);
        Runnable w2 = new Worker("Job2", lock);
        Runnable w3 = new Worker("Job3", lock);
        Runnable w4 = new Worker("Job4", lock);

        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
    }
}

class Worker implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Worker.class);
    String name;
    ReentrantLock lock;

    public Worker(String name, ReentrantLock lock) {
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        boolean done = false;

        while (!done) {
            //getting the outer lock
            boolean tryLock = lock.tryLock();
            if (tryLock) {
                try {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    logger.info("task name - " + name
                            + " outer lock acquired at "
                            + ft.format(d)
                            + " Doing outer work");

                    Thread.sleep(1500);
                    lock.lock();
                    try {
                        d = new Date();
                        ft = new SimpleDateFormat("hh:mm:ss");
                        logger.info("task name - " + name
                                + " Inner lock acquired at "
                                + ft.format(d)
                                + " Doing Inner work");

                        logger.info("Lock Hold count" + lock.getHoldCount());
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        logger.info("\"task name - \"" + name +
                                " releasing the inner lock");
                        lock.unlock();
                    }
                    logger.info("Lock Hold count" + lock.getHoldCount());
                    logger.info("task name - " + name + " work done");
                    done = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    logger.info("\"task name - \" + name" + name +
                            " releasing the inner lock");
                    lock.unlock();
                    logger.info("Lock Hold count" + lock.getHoldCount());
                }

            } else {
                logger.info("task name - " + name +
                        " waiting for lock");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
