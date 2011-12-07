package exam.trywithresources;

public class Example5 {

    static class Resource implements AutoCloseable {
        private String val;

        public Resource(String val) {
            this.val = val;
        }

        public void close() {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public static void main(String[] args) {
        try(Resource r1 = new Resource("a"); Resource r2 = null) {
            System.out.println("main");

            throw new IllegalArgumentException("Get me out of here");
        }
    }
}
