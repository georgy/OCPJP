package exam.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 26.11.11
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 */
public class Example1 {
    public static void main(String[] args) throws Exception {
        new Example1().run();
    }

    private void run() {
        ForkJoinPool fjp =  new ForkJoinPool(40);

        MyTask task = new MyTask(0, 1000000);
        Long result = fjp.invoke(task);

        System.out.println(result);

    }

}

class MyTask extends RecursiveTask<Long> {
    private int start,end;

    MyTask(int start, int end) {
        this.start = start;
        this.end = end;
        System.out.println(start + " " + end);
    }

    @Override
    protected Long compute() {
        if (end - start < 10000) {
            long result = 0L;
            for (int i = start; i < end; i++) {
                result += i * ThreadLocalRandom.current().nextInt(100);
            }

            return result;
        } else {
            int middle = (end - start) / 2;
            MyTask left = new MyTask(start, start + middle);
            MyTask right = new MyTask(start + middle + 1, end);

            left.fork();

            return right.compute() + left.join();
        }
    }
}
