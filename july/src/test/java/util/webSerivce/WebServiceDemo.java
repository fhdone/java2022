package util.webSerivce;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Test;

@Slf4j
public class WebServiceDemo {

    @Test
    public void sendWsdl() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:1212/hello?wsdl");
        ObjectMapper mapper = new ObjectMapper();
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            Object[] objects = client.invoke("hello", "json");
            System.out.println(mapper.writeValueAsString(objects[0]));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
