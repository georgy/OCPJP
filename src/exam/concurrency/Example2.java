package exam.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 26.11.11
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 */
public class Example2 {
    public static void main(String[] args) throws Exception {
        new Example2().run();
    }

    private void run() {
        ForkJoinPool fjp =  new ForkJoinPool(40);

        MyAction action = new MyAction(0, 1000000);
        fjp.invoke(action);

    }

}

class MyAction extends RecursiveAction {
    private int start,end;

    MyAction(int start, int end) {
        this.start = start;
        this.end = end;
        System.out.println(start + " " + end);
    }

    @Override
    protected void compute() {
        if (end - start < 10000) {
            System.out.print('*');
        } else {
            int middle = (end - start) / 2;
            MyAction left = new MyAction(start, start + middle);
            MyAction right = new MyAction(start + middle + 1, end);

            invokeAll(left, right);
        }
    }
}
