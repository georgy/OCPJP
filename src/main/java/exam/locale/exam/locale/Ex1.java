package exam.locale;

import java.text.DateFormat;
import java.util.Date;
// NumberFormat, DateFormat

public class Ex1 {
	public static void main(String[] args) {
		DateFormat f = DateFormat.getInstance();
		//DateFormat f = DateFormat.getTimeInstance();
		// Insert code here
		Date d = new Date();
		System.out.println(f.format(d));
	}
}
