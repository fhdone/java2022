package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ListTest {

    @Test
    public void demo() throws Exception {

        List<String> list  = new ArrayList<>(Collections.nCopies(100, null)); // 创建一个固定大小的列表
        for(int i=99; i>0; i--){
            list.set(i, i+"");
        }
        
        for(String s:list){
//            System.out.println(s);
            log.info(s);
        }

    }
}
