package com.chatrobot.service;

import com.chatrobot.pojo.ReceiveEntity;

public interface IWeChatService {

    ReceiveEntity parseWeChatMsgAndReturnEntity(String content);

    String getWeChatXmlFormatResponse(ReceiveEntity receiveEntity, String tuLinResult);
}
