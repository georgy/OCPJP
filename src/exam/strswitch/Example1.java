package exam.strswitch;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 25.11.11
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class Example1 {
    public static void main(String[] args) {
        String str = "aaha";
        switch (str) {
            case "aaa":
                System.out.println("a");
                break;
            case "xxx":
                System.out.println("x");
                break;
            default:
                System.out.println("default");
        }
    }
}
