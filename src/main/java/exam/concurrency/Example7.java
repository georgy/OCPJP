package exam.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * User: Anton Koscejev
 * Created: 11.12.11 22:08
 */
public class Example7 {
	public static void main(String[] args) throws Exception {
		MyTask task = new MyTask(0, 1000000);
		Long result = task.compute();
		System.out.println(result);
	}

	static class MyTask extends RecursiveTask<Long> {
		private int start, end;
		MyTask(int start, int end) {
			this.start = start;
			this.end = end;
		}
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
}
