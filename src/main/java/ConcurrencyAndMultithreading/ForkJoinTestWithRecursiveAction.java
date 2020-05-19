package ConcurrencyAndMultithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class ForkJoinTestWithRecursiveAction {

    public static void main(String[] args) {
        String str = "hallelujah";

        MyRecursiveAction recursiveAction = new MyRecursiveAction(str);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.execute(recursiveAction);

        recursiveAction.join();
    }
}

class MyRecursiveAction extends RecursiveAction {
    private String workload;
    private static final int THRESHOLD = 4;
    private static Logger logger =
            Logger.getAnonymousLogger();

    public MyRecursiveAction(String workload) {
        this.workload = workload;
    }

    private List<MyRecursiveAction> createSubTasks() {
        List<MyRecursiveAction> subTasks = new ArrayList<>();

        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());
        subTasks.add(new MyRecursiveAction(partOne));
        subTasks.add(new MyRecursiveAction(partTwo));

        return subTasks;
    }

    private void processing(String work) {
        String result = work.toUpperCase();
        logger.info("This result - (" + result + ") - was processed by "
                + Thread.currentThread().getName());
    }

    @Override
    protected void compute() {
        if (workload.length() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubTasks());
        } else {
            processing(workload);
        }
    }
}
