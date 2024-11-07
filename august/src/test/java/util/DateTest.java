package util;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class DateTest {

    @Test
    public void test(){
        Temporal temporal1 = LocalDate.parse("2024-10-07");
        Temporal temporal2 = LocalDate.parse("2024-10-15");
        long l = ChronoUnit.DAYS.between(temporal1, temporal2);
        System.out.println(l);
    }

}
