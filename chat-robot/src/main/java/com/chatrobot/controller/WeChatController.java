package com.chatrobot.controller;

import com.chatrobot.pojo.ReceiveEntity;
import com.chatrobot.service.IRobotService;
import com.chatrobot.service.IWeChatService;
import com.chatrobot.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class WeChatController {

    @Autowired
    private IWeChatService weChatService;

    @Autowired
    private IRobotService robotService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
//        //验证
//        String echostr = req.getParameter("echostr");
//        PrintWriter print;
//        print = resp.getWriter();
//        if (echostr != null) {
//            print.write(echostr);
//            print.flush();
//        }
        //解析请求
        InputStream inputStream = req.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String str;
        StringBuilder builder = new StringBuilder();
        while (null != (str = br.readLine())) {
            builder.append(str);
        }
        str = builder.toString();
        //处理
        ReceiveEntity receiveEntity = weChatService.parseWeChatMsgAndReturnEntity(str);
        String tuLinResult = robotService.getTuLinResult(receiveEntity.getContent());
        String res = weChatService.getWeChatXmlFormatResponse(receiveEntity, tuLinResult);

        OutputStream outputStream = resp.getOutputStream();
        outputStream.write(res.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }



}
