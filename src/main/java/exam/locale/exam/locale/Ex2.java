package exam.locale;

import java.text.DateFormat;
import java.util.Date;
// NumberFormat, DateFormat

public class Ex2 {
	public static void main(String[] args) {
		DateFormat f = DateFormat.getInstance();
		Date d = new Date();
		System.out.println(f.format(d));

		DateFormat tf = DateFormat.getDateInstance(DateFormat.SHORT);
		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
		System.out.println(tf.format(d) + " " + df.format(d));
	}
}
