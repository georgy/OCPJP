package exam.diamond;

import java.util.ArrayList;
import java.util.List;

public class Example1 {
    public static void main(String[] args) {
//        new ArrayList<>(String).add(new String("A"));
        new ArrayList<String>().add(new String("A"));
        new ArrayList<String>().add("A");
        new ArrayList<>().add("A");
//        new List<String>().add("A");
    }
}
