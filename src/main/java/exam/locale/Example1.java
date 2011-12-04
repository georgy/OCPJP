package exam.locale;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 26.11.11
 * Time: 2:22
 * To change this template use File | Settings | File Templates.
 */
public class Example1 {
    public static void main(String[] args) throws Exception {
        new Example1().run();
    }

    private void run() {
        Locale en = new Locale.Builder().setLanguage("en").setRegion("US").build();
        Locale cz = new Locale.Builder().setLanguage("cs").setRegion("CZ").build();
        for (Locale x : Locale.getAvailableLocales()) {
            System.out.println(x);
        }

        Date d = new Date();
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, en).format(d));
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, cz).format(d));
    }
}
