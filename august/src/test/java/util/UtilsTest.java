package util;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class UtilsTest {
    
    @Test
    public void  isAssignableFromTest(){

        BigDecimal num1 = new BigDecimal(1);
        Assert.assertTrue( Number.class.isAssignableFrom(num1.getClass()) );
        
        Integer num2 = 2;
        Assert.assertTrue( Number.class.isAssignableFrom(num2.getClass()) );
        
        
    }
    
}
