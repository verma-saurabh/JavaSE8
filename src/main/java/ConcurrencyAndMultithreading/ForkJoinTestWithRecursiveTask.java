package ConcurrencyAndMultithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTestWithRecursiveTask {
    public static void main(String[] args) {
        int[] arr = {10, 11, 12, 13, 44, 15, 66, 17, 77, 18, 88, 19, 99};
        MyRecursiveTask recursiveTask = new MyRecursiveTask(arr);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        //forkJoinPool.execute(recursiveTask);
        recursiveTask.fork();
        int result = recursiveTask.join();
        System.out.println("Result is " + result);

    }
}

class MyRecursiveTask extends RecursiveTask<Integer> {

    private int[] arr;
    private static final int THREASHOLD = 10;

    public MyRecursiveTask(int[] arr) {
        this.arr = arr;
    }

    private Collection<MyRecursiveTask> createSubTasks() {
        List<MyRecursiveTask> subTasks = new ArrayList<>();

        subTasks.add(new MyRecursiveTask(Arrays.copyOfRange(arr, 0, arr.length / 2)));
        subTasks.add(new MyRecursiveTask(Arrays.copyOfRange(arr, arr.length / 2, arr.length)));

        return subTasks;
    }

    private Integer process(int[] arr) {
        return Arrays.stream(arr)
                .filter(a -> a > 10 && a < 20)
                .map(a -> a * 10)
                .sum();
    }

    @Override
    protected Integer compute() {
        if (arr.length > THREASHOLD) {
            return ForkJoinTask.invokeAll(createSubTasks())
                    .stream().mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            return process(arr);
        }
    }
}