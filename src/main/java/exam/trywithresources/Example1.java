package exam.trywithresources;

public class Example1 {

    static class MyResource implements AutoCloseable {
        @Override
        public void close() {}
    }

    public static void main(String[] args) {
        try (MyResource res = new MyResource()) {
            // code here
        }
    }

}
