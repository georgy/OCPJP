package exam.trywithresources;

public class Example6 {

    static class Resource implements AutoCloseable {
        private String val;

        public Resource(String val) throws Exception {
            this.val = val;
        }

        public void close() {
            System.out.println("close");
        }
    }

    public static void main(String[] args) {
//        try(Resource r1 = new Resource("a")) {
//            System.out.println("main");
//        }
    }
}
