package exam.locale;

import java.text.NumberFormat;
import java.text.ParseException;

public class Ex4 {
	public static void main(String[] args) {
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(0); // 1
		try {
			System.out.println( f.parse("3.14") );
		} catch (ParseException e) {
			System.out.println("HUHU");
		}
	}
}
