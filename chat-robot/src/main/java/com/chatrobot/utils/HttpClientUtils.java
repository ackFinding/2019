package com.chatrobot.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtils {

    /**
     * 发送post请求
     *
     * @param url
     * @return
     */
    public static String sendPost(String url, String param) {
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        String res = "";
        CloseableHttpResponse response = null;
        try {
            //装填参数
            httpPost.setEntity(new ByteArrayEntity(param.getBytes("UTF-8")));
            //执行post请求
             response = HttpClients.createDefault().execute(httpPost);
            //获取响应实体
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                res = EntityUtils.toString(response.getEntity());
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
