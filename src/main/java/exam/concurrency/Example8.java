package exam.concurrency;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: Anton Koscejev
 * Created: 11.12.11 22:21
 */
public class Example8 {
	static Set<Thread> uniqueThreads = new CopyOnWriteArraySet<>();

	public static void main(String[] args) throws InterruptedException {
		uniqueThreads.add(Thread.currentThread());
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			executor.submit(new MyWorker());
		}
		/*
		executor.shutdown();
		while (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
			Thread.sleep(1);
		}
		*/
		System.out.println("Unique threads: " + uniqueThreads.size());
	}

	static class MyWorker implements Runnable {
		public void run() {
			uniqueThreads.add(Thread.currentThread());
			try {
				Thread.sleep(10);
			} catch (InterruptedException exc) {
				//ignore
			}
		}
	}
}
