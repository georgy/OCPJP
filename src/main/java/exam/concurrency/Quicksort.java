package exam.concurrency;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 26.11.11
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 */
public class Quicksort {
    protected final static int MAX = 10000000;
    protected final static int POOL = 2;
    protected final static int SEQLIMIT = 10000;

    public static void main(String[] args) throws Exception {
        Quicksort q = new Quicksort();
        for (int i = 0; i < 10; i++) {
            q.runP();
            q.runS();
            q.runJ();
        }
    }

    private void runP() {
        long time = System.currentTimeMillis();

        ForkJoinPool fjp =  new ForkJoinPool(POOL);

        int[] buffer = new int[MAX];

        RAction action = new RAction(0, MAX, buffer);
        long init = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        fjp.invoke(action);
        long rand = System.currentTimeMillis() - time;

        QAction qaction = new QAction(0, MAX - 1, buffer);
        time = System.currentTimeMillis();
        fjp.invoke(qaction);
        long quick = System.currentTimeMillis() - time;

        System.out.println("MT Init: " + init + " Rand: " + rand + " Sort: " + quick);
    }
    private void runS() {
        long time = System.currentTimeMillis();

        int[] buffer = new int[MAX];

        Random r = new Random();
        long init = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            buffer[i] = r.nextInt(10000);
        }
        long rand = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        quicksort(0, MAX-1, buffer);
        long quick = System.currentTimeMillis() - time;

        System.out.println("ST Init: " + init + " Rand: " + rand + " Sort: " + quick);
    }
    private void runJ() {
        long time = System.currentTimeMillis();

        int[] buffer = new int[MAX];

        Random r = new Random();
        long init = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        RAction action = new RAction(0, MAX, buffer);
        ForkJoinPool fjp =  new ForkJoinPool(POOL);
        fjp.invoke(action);
        //System.out.println(Arrays.toString(buffer));
        long rand = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        Arrays.sort(buffer);
        long quick = System.currentTimeMillis() - time;

        System.out.println("JV Init: " + init + " Rand: " + rand + " Sort: " + quick);
    }

    protected void quicksort(int start, int end, int[] buffer) {
        int middle = (end + start) / 2;
        int pivot = buffer[middle];

        int rindex = end;
        int lindex = start;

        while (lindex < rindex) {
            while (buffer[lindex] < pivot && lindex < end) {
                lindex++;
            }
            while (buffer[rindex] > pivot && rindex > start) {
                rindex--;
            }

            if (lindex <= rindex) {
                int pom = buffer[lindex];
                buffer[lindex] = buffer[rindex];
                buffer[rindex] = pom;

                if (lindex < end) {
                    lindex++;
                }
                if (rindex > start) {
                    rindex--;
                }
            }
        }

        if (rindex > start) {
            quicksort(start, rindex, buffer);
        }
        if (lindex < end) {
            quicksort(lindex, end, buffer);
        }
    }


}

class QAction extends RecursiveAction {
    private int start,end;
    private int[] buffer;

    QAction(int start, int end, int[] buffer) {
        this.start = start;
        this.end = end;
        this.buffer = buffer;
    }

    @Override
    protected void compute() {
        int middle = (end + start) / 2;
        int pivot = buffer[middle];

        int rindex = end;
        int lindex = start;

        while (lindex < rindex) {
            while (buffer[lindex] < pivot && lindex < end) {
                lindex++;
            }
            while (buffer[rindex] > pivot && rindex > start) {
                rindex--;
            }

            if (lindex <= rindex) {
                int pom = buffer[lindex];
                buffer[lindex] = buffer[rindex];
                buffer[rindex] = pom;

                if (lindex < end) {
                    lindex++;
                }
                if (rindex > start) {
                    rindex--;
                }
            }
        }

        QAction left = null, right = null;
        if (rindex > start) {
            left = new QAction(start, rindex, buffer);
        }
        if (lindex < end) {
            right = new QAction(lindex, end, buffer);
        }

        if (left != null && right != null) {
            invokeAll(left, right);
        } else if (left != null) {
            left.invoke();
        } else if (right != null) {
            right.invoke();
        }
    }
}

class RAction extends RecursiveAction {
    private int start,end;
    private int[] buffer;

    RAction(int start, int end, int[] buffer) {
        this.start = start;
        this.end = end;
        this.buffer = buffer;
    }

    @Override
    protected void compute() {
        if (end - start < Quicksort.SEQLIMIT) {
            for (int i = start; i < end; i++) {
                buffer[i] = ThreadLocalRandom.current().nextInt(10000);
            }
        } else {
            int middle = (end - start) / 2;
            RAction left = new RAction(start, start + middle, buffer);
            RAction right = new RAction(start + middle + 1, end, buffer);

            if (left != null && right != null) {
                invokeAll(left, right);
            } else if (left != null) {
                left.invoke();
            } else if (right != null) {
                right.invoke();
            }
        }
    }
}