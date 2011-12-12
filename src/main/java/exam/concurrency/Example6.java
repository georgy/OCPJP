package exam.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Anton Koscejev
 * Created: 11.12.11 21:14
 */
public class Example6 extends ReentrantLock {
    final Lock lock = this;
    int[] counters = new int[20];
    public void doA(int i) {
        synchronized (lock) {
            counters[i]++;
        }
    }
    public void doB(int i) {
        lock.lock();
        counters[i]++;
        lock.unlock();
    }
    public synchronized void doC(int i) {
        counters[i]++;
    }

    public static void main(String... args) throws InterruptedException {
        final Example6 ex = new Example6();
        Runnable r = new Runnable() {
            public void run() {
                for (int j = 0; j < 1000; j++)
                for (int i = 0; i < ex.counters.length; i++) {
                    ex.doA(i);
                    ex.doB(i);
                    ex.doC(i);
                }
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        for (int i : ex.counters) {
            System.out.print(i + " ");
        }
    }
}
