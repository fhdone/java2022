package com.fhdone.java2022.july.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {


    /** http连接池对象 */
    public static PoolingHttpClientConnectionManager cm;

    /**
     * 初始化连接池
     */
    static{
        //创建http连接池，可以同时指定连接超时时间
        cm = new PoolingHttpClientConnectionManager(60000, TimeUnit.MILLISECONDS);
        //最多同时连接20个请求
        cm.setMaxTotal(20);
        //每个路由最大连接数，路由指IP+PORT或者域名
        cm.setDefaultMaxPerRoute(50);
    }

    /**
     * 从连接池中获取httpClient连接
     */
    public static CloseableHttpClient getHttpClient(PoolingHttpClientConnectionManager cm, int socketTimeout ){
        //设置请求参数配置，创建连接时间、从连接池获取连接时间、数据传输时间、是否测试连接可用、构建配置对象
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(1000)
            .setConnectionRequestTimeout(3000)
            .setSocketTimeout(socketTimeout * 1000)
            .setStaleConnectionCheckEnabled(true)
            .build();

        //创建httpClient时从连接池中获取，并设置连接失败时自动重试（也可以自定义重试策略：setRetryHandler()）
        CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .setConnectionManager(cm)
            .disableAutomaticRetries()
            .build();
        return httpClient;
    }

    /**
     * 执行请求
     */
    public static void doGetRequest(CloseableHttpClient httpClient,String url){
        //创建http请求类型
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if(200 == httpResponse.getStatusLine().getStatusCode()){
                //System.out.println("请求返回数据内容：" + EntityUtils.toString(httpResponse.getEntity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(httpResponse != null){
                //执行httpResponse.close关闭对象会关闭连接池，
                //如果需要将连接释放到连接池，可以使用EntityUtils.consume()方法
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





    public static void doPostRequest(CloseableHttpClient httpClient,String url){
        //创建http请求类型
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            if(200 == httpResponse.getStatusLine().getStatusCode()){
                System.out.println("请求返回数据内容：" + EntityUtils.toString(httpResponse.getEntity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(httpResponse != null){
                //执行httpResponse.close关闭对象会关闭连接池，
                //如果需要将连接释放到连接池，可以使用EntityUtils.consume()方法
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
