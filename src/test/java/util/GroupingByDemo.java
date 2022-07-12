package util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



public class GroupingByDemo {


    // https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
    @Test
    public void groupBy() {
        //3 apple, 2 banana, others 1
        List<String> items =
            Arrays.asList("apple", "apple", "banana",
                "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
            items.stream().collect(
                Collectors.groupingBy(
                    Function.identity(), Collectors.counting()
                )
            );

        System.out.println(result);


        Map<String, Long> finalMap = new LinkedHashMap<>();

        //Sort a map and add to finalMap
        result.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue()
                .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);

    }




    @Test
    public void groupBy2() {

        //3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("banana", 20, new BigDecimal("19.99")),
            new Item("orang", 10, new BigDecimal("29.99")),
            new Item("watermelon", 10, new BigDecimal("29.99")),
            new Item("papaya", 20, new BigDecimal("9.99")),
            new Item("apple", 10, new BigDecimal("9.99")),
            new Item("banana", 10, new BigDecimal("19.99")),
            new Item("apple", 20, new BigDecimal("9.99"))
        );

        Map<String, Long> counting = items.stream().collect(
            Collectors.groupingBy(Item::getName, Collectors.counting()));
        System.out.println(counting);

        Map<String, Integer> sum = items.stream().collect(
            Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println(sum);


        //group by price
        Map<BigDecimal, List<Item>> groupByPriceMap =
            items.stream().collect(Collectors.groupingBy(Item::getPrice));
        System.out.println(groupByPriceMap);

        // group by price, uses 'mapping' to convert List<Item> to Set<String>
        Map<BigDecimal, Set<String>> result =
            items.stream().collect(
                Collectors.groupingBy(Item::getPrice,
                    Collectors.mapping(Item::getName, Collectors.toSet())
                )
            );
        System.out.println(result);
        

    }





    @Data
    @AllArgsConstructor
    public class Item {

        private String name;
        private int qty;
        private BigDecimal price;

    }



}
