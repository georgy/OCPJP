package exam.strswitch;

public class Example5 {
    public static void main(String[] args) {
        switch ("AA".toLowerCase().substring(1)) {
            case "aa" + "a":
                System.out.println("aa+a");
                break;
            case "aa":
                System.out.println("aa");
                break;
            case (!true ? "a": "A"):
                System.out.println("a");
                break;
            default:
                System.out.println("Missed!");
                break;
        }
    }
}
