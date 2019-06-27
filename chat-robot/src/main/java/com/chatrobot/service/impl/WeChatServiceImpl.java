package com.chatrobot.service.impl;

import com.chatrobot.pojo.ReceiveEntity;
import com.chatrobot.service.IWeChatService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;

@Service
public class WeChatServiceImpl implements IWeChatService {


    public String getWeChatXmlFormatResponse(ReceiveEntity receiveEntity, String tuLinResult) {
        String res = "<xml>\n"
                + "  <ToUserName><![CDATA[" + receiveEntity.getFromUserName() + "]]></ToUserName>\n"
                + "  <FromUserName><![CDATA[" + receiveEntity.getToUserName() + "]]></FromUserName>\n"
                + "  <CreateTime>" + new Date().toString() + "</CreateTime>\n"
                + "  <MsgType><![CDATA[text]]></MsgType>\n"
                + "  <Content><![CDATA[" + tuLinResult + "]]></Content>\n"
                + "</xml>";
        return res;
    }

    public ReceiveEntity parseWeChatMsgAndReturnEntity(String content) {
        ReceiveEntity res = null;
        try {
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            //反射注入属性
            Class clazz = Class.forName("com.chatrobot.pojo.ReceiveEntity");
            res = (ReceiveEntity) clazz.newInstance();
            while (iter.hasNext()) {
                Element element = iter.next();
                Field field = clazz.getDeclaredField(element.getName());
                Method method = clazz.getDeclaredMethod("set" + element.getName(), field.getType());
                method.invoke(res, element.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
