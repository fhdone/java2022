package com.fhdone.java2022.july.utils;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


public class HttpClientUtilsTest {
    @Test
    public void doGetRequest() throws Exception {
        String url = "https://www.baidu.com";
        for(int i = 0; i < 5; i++){
            //连接池中获取httpClient
            CloseableHttpClient httpClient = HttpClientUtils.getHttpClient(HttpClientUtils.cm, 10);
            //单线程执行请求
            HttpClientUtils.doGetRequest(httpClient, url);
            System.out.println(HttpClientUtils.cm.getTotalStats());
        }
    }


} 
