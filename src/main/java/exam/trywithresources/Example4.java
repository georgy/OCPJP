package exam.trywithresources;

public class Example4 {

    static class Resource implements AutoCloseable {
        private String val;

        public Resource(String val) {
            this.val = val;
        }

        public void close() {
            System.out.println(val);
        }
    }

    public static void main(String[] args) {
        try(Resource r1 = new Resource("a"); Resource r2 = null) {
            System.out.println("main");
        }
    }
}
