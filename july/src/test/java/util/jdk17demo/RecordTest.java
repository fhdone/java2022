package util.jdk17demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    public void test(){
        
        Person p1 = new Person("小黑说Java", 18, "北京市西城区");
        Person p2 = new Person("小白说Java", 28, "北京市东城区");
        // 使用record定义
        record PersonRecord(String name,int age){}

        PersonRecord p1Record = new PersonRecord(p1.getName(), p1.getAge());
        PersonRecord p2Record = new PersonRecord(p2.getName(), p2.getAge());
        PersonRecord p3Record = new PersonRecord("peter",13);
        System.out.println(p1Record);
        System.out.println(p2Record);
        System.out.println(p3Record);
        
    }



    @Data
    @AllArgsConstructor
    public class Person {
        private String name;

        private int age;

        private String address;
    }
}
