package exam.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class Ex6 {
	public static void main(String[] args) {
		Locale loc = Locale.CANADA_FRENCH;
		ResourceBundle rb = ResourceBundle.getBundle("Ex6", loc);
		System.out.println(rb.getString("some"));
		System.out.println( rb.getString("another") );
	}
}
