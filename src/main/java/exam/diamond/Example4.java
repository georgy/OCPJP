package exam.diamond;

import java.util.ArrayList;
import java.util.List;

public class Example4<T extends List<String>> {
//    T item = new ArrayList< >();

    public static void main(String[] args) {
        new Example4<List<String>>();
        System.out.println("main");
    }
}
