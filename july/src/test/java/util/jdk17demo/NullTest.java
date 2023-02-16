package util.jdk17demo;

import org.junit.Test;

import java.math.BigDecimal;

public class NullTest {
    
    @Test
    public void nullTest(){
        BigDecimal b = new BigDecimal(10);
        System.out.println(b.longValue());
        
        b=null;
        System.out.println(b.longValue());
    }
    
}
