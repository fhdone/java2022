package util.jdk17demo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void oldStyle() {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        List<String> stringList =  stringStream.collect(Collectors.toList());
        for(String s : stringList) {
            System.out.println(s);
        }
    }

    @Test
    public void streamToList() {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        List<String> stringList =  stringStream.toList();
        for(String s : stringList) {
            System.out.println(s);
        }
    }


}
