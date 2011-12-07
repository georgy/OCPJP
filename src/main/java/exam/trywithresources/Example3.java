package exam.trywithresources;

public class Example3 {

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
        try(Resource r1 = new Resource("a"); Resource r2 = new Resource("b")) {
            System.out.println("main");
        }
    }
}
