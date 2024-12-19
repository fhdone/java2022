package com.fhdone.java2022.august.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.sf.json.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SendSmsApi {
    public static final int TIMEOUT = 10000;
    private static String host = "http://221.216.104.2:8000/umcinterface/sendtempletmsg";

    public SendSmsApi() {
    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        String cpcode = "AABNUV";
        String mobiles = "17701638501";
        String msg = "123456";
        String excode = "";
        String templetid = "159518";
        String key = "49197e89c95c9c6be46a1b420ff0d84d";
        String md5source = cpcode + msg + mobiles + excode + templetid + key;
        System.out.println("--签名串-" + md5source);
        String sign = "";

        try {
            sign = makeMD5(new String(md5source.getBytes("UTF-8"), "UTF-8")).toLowerCase();
        } catch (UnsupportedEncodingException var11) {
            UnsupportedEncodingException e = var11;
            e.printStackTrace();
        }

        json.put("cpcode", cpcode);
        json.put("msg", msg);
        json.put("excode", excode);
        json.put("mobiles", mobiles);
        json.put("templetid", templetid);
        json.put("sign", sign);
        System.out.println("---sign---" + sign);
        System.out.println(post(json.toString(), "UTF-8"));
    }

    public static String send(String mobiles, String msg, String templetid) {
        JSONObject json = new JSONObject();
        String cpcode = "AABNUV";
        String excode = "";
        String key = "49197e89c95c9c6be46a1b420ff0d84d";
        String md5source = cpcode + msg + mobiles + excode + templetid + key;
        System.out.println("--签名串-" + md5source);
        String sign = "";

        try {
            sign = makeMD5(new String(md5source.getBytes("UTF-8"), "UTF-8")).toLowerCase();
        } catch (UnsupportedEncodingException var10) {
            UnsupportedEncodingException e = var10;
            e.printStackTrace();
        }

        json.put("cpcode", cpcode);
        json.put("msg", msg);
        json.put("excode", excode);
        json.put("mobiles", mobiles);
        json.put("templetid", templetid);
        json.put("sign", sign);
        System.out.println("---sign---" + sign);
        System.out.println("---msg---" + msg);
        return post(json.toString(), "UTF-8");
    }

    public static String makeMD5(String plainText) {
        String re_md5 = new String();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes("UTF-8"));
            byte[] b = md.digest();
            StringBuffer buf = new StringBuffer("");

            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        } catch (UnsupportedEncodingException var8) {
            UnsupportedEncodingException e = var8;
            e.printStackTrace();
        }

        return re_md5;
    }

    public static String post(String datastr, String charset) {
        if (host != null && host.toLowerCase().startsWith("http")) {
            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
                HttpPost post = new HttpPost(host.trim());
                post.setConfig(requestConfig);
                if (datastr != null) {
                    if (charset != null) {
                        post.setEntity(new StringEntity(datastr, charset));
                    } else {
                        post.setEntity(new StringEntity(datastr));
                    }
                }

                CloseableHttpResponse response = httpclient.execute(post);
                String result = null;
                if (response.getStatusLine().getStatusCode() == 200) {
                    result = EntityUtils.toString(response.getEntity());
                }

                httpclient.close();
                return result;
            } catch (ClientProtocolException var7) {
                ClientProtocolException e = var7;
                e.printStackTrace();
            } catch (IOException var8) {
                IOException e = var8;
                e.printStackTrace();
            }

            return null;
        } else {
            return null;
        }
    }
}
