package util.jdk17demo;

import org.junit.jupiter.api.Test;

/**
 * https://juejin.cn/post/7019952895999246366
 */
public class SwitchTest {

    @Test
    public void switchTest(){
        System.out.println("####################");
        SwitchTest.switchTest("APPLE");
        
        System.out.println("####################");
        SwitchTest.switchTest("MANGO");
        
        System.out.println("####################");
        SwitchTest.switchTest("BANANA");
    }

    public static void switchTest(String str){

        switch (str) {
            case "APPLE", "PEAR":
                System.out.println(str+"普通水果");
                break;
            case "MANGO", "AVOCADO":
                System.out.println(str+"进口水果");
                break;
            default:
                System.out.println(str+"未知水果");
        }

        switch (str) {
            case "APPLE", "PEAR" -> System.out.println(str+"普通水果");
            case "MANGO", "AVOCADO" -> System.out.println(str+"进口水果");
            default -> System.out.println(str+"未知水果");
        }


        String text = switch (str) {
            case "APPLE", "PEAR" -> {
                System.out.println("给的水果是: " + str);
                yield "普通水果";
            }
            case "MANGO", "AVOCADO" -> "进口水果";
            default -> "未知水果";
        };
        System.out.println(text);


    }

}
