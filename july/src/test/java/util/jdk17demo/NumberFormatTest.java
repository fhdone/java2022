package util.jdk17demo;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {

    @Test
    public void format() {
//        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, 
//            NumberFormat.Style.SHORT);
//        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.CHINESE, 
//            NumberFormat.Style.SHORT);
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.UK,
            NumberFormat.Style.SHORT);
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));

//        fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, 
//            NumberFormat.Style.LONG);
//        fmt = NumberFormat.getCompactNumberInstance(Locale.CHINESE, 
//            NumberFormat.Style.LONG);
        fmt = NumberFormat.getCompactNumberInstance(Locale.UK,
            NumberFormat.Style.LONG);
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));

    }

}
