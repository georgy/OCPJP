package exam.concurrency;

import java.util.concurrent.atomic.*;

/**
 * User: Anton Koscejev
 * Created: 11.12.11 20:27
 */
public class Example5 {
    int a = Integer.MAX_VALUE;
    float b = Float.MAX_VALUE;
    short c = Short.MAX_VALUE;
    long d = Long.MAX_VALUE;
    double e = Double.MAX_VALUE;
    AtomicInteger f = new AtomicInteger(Integer.MAX_VALUE);
    AtomicLong g = new AtomicLong(Long.MAX_VALUE);
    volatile long h = Long.MAX_VALUE;

    public void doSomething() {
        //insert code here
    }
}
