package exam.multicatch;

import java.io.IOException;

public class Example2 {
    public static void main(String[] args) {
        try{
            throw new IOException("try");
        }catch (IOException| IllegalArgumentException e) {
            System.out.println("catch");
        }
    }
}
