package util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class JsonPathTest {

    private String json = """
        {"arr":[{"name":"zpl0","id":0},{"name":"zpl1","id":1},
        {"name":"zpl2","id":2},{"name":"zpl3","id":3},
        {"name":"zpl4","id":4}],"name":"zpl","id":1}
        """;


    /**
     * https://juejin.cn/post/6844904143916630023
     * https://github.com/alibaba/fastjson/wiki/JSONPath
     */
    @Test
    public void eval(){

        //取值操作
        Object rootName = JSONPath.eval(json,"$.name");
        log.info(rootName.toString());
        Object rootArr = JSONPath.eval(json,"$.arr");
        log.info(rootArr.toString());

        //判断操作
        // 判断有没有id>2的内容
        //boolean isExist = JSONPath.contains(json,"$[id>0]");
        //System.out.println(isExist);
        // 判断有没有id>6的内容
        //boolean isExist = JSONPath.contains(json,"$[id>1]");
        //System.out.println(isExist);

        //size操作
        //int size = JSONPath.size(json,"$.arr");
        //System.out.println(size);


    }

    /**
     * https://github.com/json-path/JsonPath
     */
    @Test
    public void read(){
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        List<String> list = JsonPath.read(document, "$..name");
        log.info(JSON.toJSONString(list));

        document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        list = JsonPath.read(document, "$..[?(@.id > 1)]");
        log.info(JSON.toJSONString(list));
    }


}
