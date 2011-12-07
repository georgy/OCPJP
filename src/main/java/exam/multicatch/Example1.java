package exam.multicatch;

public class Example1 {
    public static void main(String[] args) {
        try{
            throw new RuntimeException("try");
        }catch (Exception e) {
            System.out.println("catch");
        }
    }
}
