package com.chatrobot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chatrobot.pojo.ResultEntity;
import com.chatrobot.service.IRobotService;
import com.chatrobot.utils.HttpClientUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RobotServiceImpl implements IRobotService {

    private static final String API_KEY = "";
    private static final String USER_ID = "";
    private static final String ROBOT_URL = "http://openapi.tuling123.com/openapi/api/v2";


    public String getTuLinResult(String content) {
        String param = getRequestJsonParam(content);
        String resp = HttpClientUtils.sendPost(ROBOT_URL, param);
        //返回解析json结果
        return parseResponse(resp);
    }

    /**
     * 解析图灵机器人返回结果
     *
     * @param resp
     * @return
     */
    private String parseResponse(String resp) {
        if (StringUtils.isEmpty(resp)) {
            return "";
        }
        JSONObject jsonObject = JSONObject.parseObject(resp);
        JSONArray array = jsonObject.getJSONArray("results");
        String res = "";
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            ResultEntity entity = JSON.toJavaObject(obj, ResultEntity.class);
            if (entity.getResultType().equals("text")) {
                res = entity.getValues().get("text");
                break;
            }
        }
        return res;
    }

    /**
     * 返回json类型请求参数
     *
     * @param text
     * @return
     */
    private String getRequestJsonParam(String text) { // 构造JSON请求参数
        JSONObject root = new JSONObject();
        root.put("reqType", 0);

        JSONObject perception = new JSONObject();
        JSONObject inputText = new JSONObject();
        inputText.put("text", text);
        perception.put("inputText", inputText);
        root.put("perception", perception);

        JSONObject userInfo = new JSONObject();
        userInfo.put("apiKey", API_KEY);
        userInfo.put("userId", USER_ID);
        root.put("userInfo", userInfo);

//        JSONObject selfInfo = new JSONObject();
//        JSONObject location = new JSONObject();
//        location.put("city", "xxx");
//        location.put("province", "xxx");
//        location.put("street", "xxx");
//        selfInfo.put("location", location);
//        perception.put("selfInfo", selfInfo);

        return root.toString();
    }
}
