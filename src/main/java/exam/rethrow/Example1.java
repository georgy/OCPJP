package exam.rethrow;

public class Example1 {

    static class SomethingWrong extends Exception {}

    static class RealFubar extends Exception {}

    public static void main(String[] args) throws SomethingWrong, RealFubar {
        try {
            if (true) {
                throw new SomethingWrong();
            } else {
                throw new RealFubar();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
