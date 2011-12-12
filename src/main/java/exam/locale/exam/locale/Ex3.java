package exam.locale;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Ex3 {
	public static void main(String[] args) {
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL, Locale.UK);
		NumberFormat nf = f.getNumberFormat();
		System.out.println(nf.getCurrency().getCurrencyCode() + " "+ nf.format(15000000L));
	}
}
