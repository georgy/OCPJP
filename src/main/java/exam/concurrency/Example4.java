package exam.concurrency;

/**
 * User: Anton Koscejev
 * Created: 11.12.11 20:01
 */
public class Example4 {
    static Object object = new Object();
    public static void main(String... args) throws InterruptedException {
        Thread t1 = new Thread() {
            public void run() {
                try {
                    synchronized (object) {
                        object.wait();
                        System.out.println("t1: done waiting");
                    }
                } catch (InterruptedException e) {
                    System.out.println("t1: interrupted");
                }
            }
        }; t1.start();
        Thread t2 = new Thread() {
            public void run() {
                try {
                    synchronized (object) {
                        object.wait();
                        System.out.println("t2: done waiting");
                    }
                } catch (InterruptedException e) {
                    System.out.println("t2: interrupted");
                }
            }
        }; t2.start();
        Thread t3 = new Thread() {
            public void run() {
                synchronized (object) {
                    object.notify();
                }
            }
        }; t3.start();
        
        t1.join();
        t2.join();
    }
}
