package exam.rethrow;

public class Example2 {

    static class SomethingWrong extends Exception {
    }

    public static void main(String[] args) throws SomethingWrong {
        try {
            throw new SomethingWrong();
        } catch (Exception e) {
//            throw e;

//            throw new Exception();

//            e = new Exception(); throw e;

//            throw new SomethingWrong();

//              e = new SomethingWrong(); throw e;
        }
    }
}
