package util;


import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ShowName {

    @Test
    public void t1(){
        // 获取当前方法名
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        // 打印当前方法名
        System.out.println("当前方法名：" + methodName);
    }

    @Test
    public void t2(){
        // 获取当前方法名
        String methodName = new Exception().getStackTrace()[0].getMethodName();
        // 打印当前方法名
        System.out.println("当前方法名：" + methodName);
    }

    @Test
    public void t3(){
        // 获取当前方法名
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        // 打印当前方法名
        System.out.println("当前方法名：" + methodName);
    }

    @Test
    public void t4(){
        // 获取当前方法名
        StackWalker walker = StackWalker.getInstance();
        Optional<String> optional = walker.walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName));
        // 打印当前方法名
        System.out.println("当前方法名：" + optional.get());
    }



}
