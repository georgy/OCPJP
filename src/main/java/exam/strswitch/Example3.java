package exam.strswitch;

public class Example3 {
    public static void main(String[] args) {
        doswitch("aa");
    }

    private static void doswitch(String str) {
        switch (str) {
            case "a" + "a":
                System.out.println("aa");
                break;
            case "ab":
                System.out.println("ab");
                break;
//            case "aa":
//                System.out.println("ab");
//                break;
            default:
                System.out.println("default");
        }
    }
}
