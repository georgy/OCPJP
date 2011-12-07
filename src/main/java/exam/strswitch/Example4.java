package exam.strswitch;

public class Example4 {
    public static void main(String[] args) {
        doswitch("aa");
    }

    private static void doswitch(String str) {
        switch (str) {
            case "aa":
                System.out.println("aa");
                break;
//            case null:
//                System.out.println("Look ma, null!");
//                break;
        }
    }
}
