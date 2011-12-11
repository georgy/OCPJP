package exam.concurrency;

/**
 * User: Anton Koscejev
 * Created: 11.12.11 16:51
 * <a href="http://docs.oracle.com/javase/tutorial/essential/concurrency">Lesson: Concurrency</a>
 */
public class Example3 {
    public static void main(String... args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();
    }
    static class Friend {
        private final String name;
        Friend(String name) { this.name = name; }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n", this.name, bower.name);
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.name);
        }
    }
}
