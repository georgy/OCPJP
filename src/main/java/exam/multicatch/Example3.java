package exam.multicatch;

import java.io.IOException;

public class Example3 {
    public static void main(String[] args) {
        try{
            throw new IOException("try");
        }catch (IOException| IllegalArgumentException e) {
//            e = new IllegalArgumentException(); throw e;
        }
    }
}
