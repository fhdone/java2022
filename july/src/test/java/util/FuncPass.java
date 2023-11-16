package util;

import org.junit.jupiter.api.Test;
import java.util.function.Function;

public class FuncPass {
    
    @Test
    public void t1(){
        Function<Integer, Integer> increase = e -> e + 7;  // lambda表达式
        System.out.println(increase.getClass());
        funcPlus(3, increase);
    }

    public void funcPlus(int value, Function<Integer, Integer> func) {
        System.out.println(func.apply(value));
    }

}
