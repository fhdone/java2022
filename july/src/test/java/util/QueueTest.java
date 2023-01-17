package util;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.Throughput) // 测试类型：吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class QueueTest {

    @Param({"3", "100", "10000"})
    private int n=10;

    @Test
    @Benchmark
    public void queue(){
        Queue<String> q = new LinkedList<>();
        commonTest(q);
    }

    @Test
    @Benchmark
    public void priorityQueue(){
        Queue<String> q = new PriorityQueue<>();
        commonTest(q);
    }


    private void commonTest(Queue<String> q) {
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        for (int i = 0; i < n-3; i++) {
            q.offer(i+"");
        }
        for(int i=0; i<n; i++){
//            System.out.println(q.poll());
            q.poll();
        }
    }


    @Test
    @Benchmark
    public void priorityQueueCustom(){
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        for (int i = 0; i < n-3; i++) {
            q.offer(new User(i+"", i+""));
            
        }
        for(int i=0; i<n; i++){
//            System.out.println(q.poll());
            q.poll();
        }
    }


    public static void main(String[] args) throws Exception {

        // 启动基准测试
        Options opt = new OptionsBuilder()
            .include(QueueTest.class.getSimpleName()) // 要导入的测试类
            //.output("/Users/fhdone/Documents/jmh-map.log") // 输出测试结果的文件
            .build();
        new Runner(opt).run(); // 执行测试
    }

}



class UserComparator implements Comparator<User> {
    public int compare(User u1, User u2) {
        if (u1.number.charAt(0) == u2.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小:
            return u1.number.compareTo(u2.number);
        }
        if (u1.number.charAt(0) == 'V') {
            // u1的号码是V开头,优先级高:
            return -1;
        } else {
            return 1;
        }
    }
}

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String toString() {
        return name + "/" + number;
    }
}



